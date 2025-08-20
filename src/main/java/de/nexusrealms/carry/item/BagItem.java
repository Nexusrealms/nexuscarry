package de.nexusrealms.carry.item;

import dev.emi.trinkets.api.*;
import net.minecraft.component.ComponentType;
import net.minecraft.component.type.DyedColorComponent;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.DyeItem;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.tag.ItemTags;
import net.minecraft.screen.GenericContainerScreenHandler;
import net.minecraft.screen.NamedScreenHandlerFactory;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.Pair;
import net.minecraft.util.math.ColorHelper;

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
