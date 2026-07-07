package de.nexusrealms.carry.item;

import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.ContainerComponent;
import net.minecraft.entity.ContainerUser;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.Inventories;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.util.collection.DefaultedList;

public class BagInventory implements Inventory {
    private final ItemStack stack;
    private final int capacity;
    private final DefaultedList<ItemStack> stacks;
    public BagInventory(ItemStack stack, int capacity) {
        this.stack = stack;
        stacks = DefaultedList.ofSize(capacity, ItemStack.EMPTY);
        this.capacity = capacity;
        stack.getOrDefault(DataComponentTypes.CONTAINER, ContainerComponent.DEFAULT).copyTo(stacks);

    }
    @Override
    public int size() {
        return capacity;
    }

    @Override
    public boolean isEmpty() {
        return stacks.isEmpty();
    }

    @Override
    public ItemStack getStack(int slot) {
        return stacks.get(slot);
    }

    @Override
    public ItemStack removeStack(int slot, int amount) {
        ItemStack itemStack = Inventories.splitStack(this.stacks, slot, amount);
        if (!itemStack.isEmpty()) {
            this.markDirty();
        }

        return itemStack;
    }

    @Override
    public ItemStack removeStack(int slot) {
        ItemStack itemStack = this.stacks.get(slot);
        if (itemStack.isEmpty()) {
            return ItemStack.EMPTY;
        } else {
            this.stacks.set(slot, ItemStack.EMPTY);
            return itemStack;
        }
    }

    @Override
    public void setStack(int slot, ItemStack stack) {
        this.stacks.set(slot, stack);
        if (!stack.isEmpty() && stack.getCount() > this.getMaxCountPerStack()) {
            stack.setCount(this.getMaxCountPerStack());
        }

        this.markDirty();
    }

    @Override
    public void markDirty() {
        stack.set(DataComponentTypes.CONTAINER, ContainerComponent.fromStacks(stacks));
    }

    @Override
    public boolean canPlayerUse(PlayerEntity player) {
        return true;
    }

    @Override
    public void clear() {
        stacks.clear();
    }

    @Override
    public void onClose(ContainerUser player) {
        markDirty();
    }
}
