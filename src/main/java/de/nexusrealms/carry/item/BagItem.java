package de.nexusrealms.carry.item;

import de.nexusrealms.carry.NexusCarry;
import dev.emi.trinkets.TrinketsMain;
import dev.emi.trinkets.api.*;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.GenericContainerScreenHandler;
import net.minecraft.screen.NamedScreenHandlerFactory;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.Pair;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class BagItem extends TrinketItem {
    private final int rows;
    public BagItem(int rows, Settings settings) {
        super(settings);
        this.rows = rows;
    }
    public BagItem(Settings settings) {
        this(3, settings);
    }
    public static void tryOpenScreen(ServerPlayerEntity player){
        List<Pair<SlotReference, ItemStack>> list = player.getComponent(TrinketsApi.TRINKET_COMPONENT)
                .getEquipped(stack -> stack.getItem() instanceof BagItem);
        list.stream()
                .map(Pair::getRight)
                .findFirst()
                .ifPresent(stack -> ((BagItem) stack.getItem()).openScreen(player, stack));
    }
    public void openScreen(PlayerEntity player, ItemStack stack){
        player.openHandledScreen(new ScreenHandlerFactory(stack));
    }
    @Override
    public boolean canBeNested() {
        return false;
    }


    private record ScreenHandlerFactory(ItemStack stack) implements NamedScreenHandlerFactory {
        @Override
        public Text getDisplayName() {
            return stack.getName();
        }

        @Override
        public ScreenHandler createMenu(int syncId, PlayerInventory playerInventory, PlayerEntity player) {
            return GenericContainerScreenHandler.createGeneric9x3(syncId, playerInventory, new BagInventory(stack, 27));
        }
    }
}
