package de.nexusrealms.carry.item;

import de.nexusrealms.carry.NexusCarry;
import dev.emi.trinkets.api.SlotReference;
import dev.emi.trinkets.api.TrinketItem;
import dev.emi.trinkets.api.TrinketsApi;
import net.minecraft.component.ComponentType;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.ContainerComponent;
import net.minecraft.component.type.DyedColorComponent;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.DyeItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.tag.ItemTags;
import net.minecraft.screen.GenericContainerScreenHandler;
import net.minecraft.screen.NamedScreenHandlerFactory;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.text.Text;
import net.minecraft.util.Pair;
import net.minecraft.util.math.ColorHelper;
import org.jspecify.annotations.Nullable;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public class BagItem extends TrinketItem {
    private final int rows;
    public static Set<UUID> awaitsCleaningPass= HashSet.newHashSet(1);
    public BagItem(int rows, Item.Settings settings) {
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

    @Override
    public void inventoryTick(ItemStack stack, ServerWorld world, Entity entity, @Nullable EquipmentSlot slot) {
        if(!awaitsCleaningPass.isEmpty() && awaitsCleaningPass.contains(stack.get(CarryItems.Components.BAG_ID))) {
            ContainerComponent inv = stack.get(DataComponentTypes.CONTAINER);
            if(inv == null){
                return;
            }
            inv.stream().forEach(itemStack -> entity.dropStack(world, itemStack));
            stack.set(DataComponentTypes.CONTAINER, ContainerComponent.DEFAULT);
            awaitsCleaningPass.remove(stack.get(CarryItems.Components.BAG_ID));
        }
    }

    @Override
    public void onEquip(ItemStack stack, SlotReference slot, LivingEntity entity) {
        if(!stack.contains(CarryItems.Components.BAG_ID)){
            stack.set(CarryItems.Components.BAG_ID, UUID.randomUUID());
        }
    }

    @Override
    public void onUnequip(ItemStack stack, SlotReference slot, LivingEntity entity) {
        if(entity.getEntityWorld() instanceof ServerWorld world && world.getGameRules().getValue(NexusCarry.DROP_ITEMS_WHEN_UNEQUIPPED)) {
            awaitsCleaningPass.add(stack.get(CarryItems.Components.BAG_ID));
        }
    }

    public static int getColor(ItemStack stack, ComponentType<DyedColorComponent> componentType, int fallback) {
        DyedColorComponent dyedColorComponent = stack.get(componentType);
        return dyedColorComponent != null ? ColorHelper.fullAlpha(dyedColorComponent.rgb()) : ColorHelper.fullAlpha(fallback);
    }
    public static ItemStack setColorFromDyes(ItemStack stack, List<DyeItem> dyes, ComponentType<DyedColorComponent> componentType) {
        if (!stack.isIn(ItemTags.DYEABLE)) {
            return ItemStack.EMPTY;
        } else {
            ItemStack itemStack = stack.copyWithCount(1);
            int i = 0;
            int j = 0;
            int k = 0;
            int l = 0;
            int m = 0;
            DyedColorComponent dyedColorComponent = itemStack.get(componentType);
            if (dyedColorComponent != null) {
                int n = ColorHelper.getRed(dyedColorComponent.rgb());
                int o = ColorHelper.getGreen(dyedColorComponent.rgb());
                int p = ColorHelper.getBlue(dyedColorComponent.rgb());
                l += Math.max(n, Math.max(o, p));
                i += n;
                j += o;
                k += p;
                ++m;
            }

            for(DyeItem dyeItem : dyes) {
                int p = dyeItem.getColor().getEntityColor();
                int q = ColorHelper.getRed(p);
                int r = ColorHelper.getGreen(p);
                int s = ColorHelper.getBlue(p);
                l += Math.max(q, Math.max(r, s));
                i += q;
                j += r;
                k += s;
                ++m;
            }

            int n = i / m;
            int o = j / m;
            int p = k / m;
            float f = (float)l / (float)m;
            float g = (float)Math.max(n, Math.max(o, p));
            n = (int)((float)n * f / g);
            o = (int)((float)o * f / g);
            p = (int)((float)p * f / g);
            int s = ColorHelper.getArgb(0, n, o, p);
            setColor(itemStack, componentType, s);
            return itemStack;
        }
    }
    public static void setColorFromDyesNoCopy(ItemStack itemStack, List<DyeItem> dyes, ComponentType<DyedColorComponent> componentType) {
        if (!itemStack.isIn(ItemTags.DYEABLE)) {
        } else {
            int i = 0;
            int j = 0;
            int k = 0;
            int l = 0;
            int m = 0;
            DyedColorComponent dyedColorComponent = itemStack.get(componentType);
            if (dyedColorComponent != null) {
                int n = ColorHelper.getRed(dyedColorComponent.rgb());
                int o = ColorHelper.getGreen(dyedColorComponent.rgb());
                int p = ColorHelper.getBlue(dyedColorComponent.rgb());
                l += Math.max(n, Math.max(o, p));
                i += n;
                j += o;
                k += p;
                ++m;
            }

            for(DyeItem dyeItem : dyes) {
                int p = dyeItem.getColor().getEntityColor();
                int q = ColorHelper.getRed(p);
                int r = ColorHelper.getGreen(p);
                int s = ColorHelper.getBlue(p);
                l += Math.max(q, Math.max(r, s));
                i += q;
                j += r;
                k += s;
                ++m;
            }

            int n = i / m;
            int o = j / m;
            int p = k / m;
            float f = (float)l / (float)m;
            float g = (float)Math.max(n, Math.max(o, p));
            n = (int)((float)n * f / g);
            o = (int)((float)o * f / g);
            p = (int)((float)p * f / g);
            int s = ColorHelper.getArgb(0, n, o, p);
            setColor(itemStack, componentType, s);
        }
    }
    public static void setColor(ItemStack stack, ComponentType<DyedColorComponent> componentType, int color) {
        stack.set(componentType, new DyedColorComponent(color));
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
