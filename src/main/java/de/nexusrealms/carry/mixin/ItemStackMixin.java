package de.nexusrealms.carry.mixin;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import de.nexusrealms.carry.item.BagItem;
import de.nexusrealms.carry.item.CarryItems;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

import java.util.Objects;
import java.util.UUID;

@Mixin(ItemStack.class)
public class ItemStackMixin {
    @ModifyReturnValue(method = "areEqual", at = @At("RETURN"))
    private static boolean youAndIAreNotSoDifferent(boolean original, ItemStack left, ItemStack right){
        if(!original && left.getItem() instanceof BagItem && left.getItem() == right.getItem()){
            UUID a = left.get(CarryItems.Components.BAG_ID);
            UUID b = right.get(CarryItems.Components.BAG_ID);
            return !(a == null && b == null) && Objects.equals(a, b);
        }
        return original;
    }
}
