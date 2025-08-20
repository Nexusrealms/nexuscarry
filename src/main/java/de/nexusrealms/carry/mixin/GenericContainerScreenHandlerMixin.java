package de.nexusrealms.carry.mixin;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.llamalad7.mixinextras.sugar.Local;
import de.nexusrealms.carry.item.BagInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.screen.GenericContainerScreenHandler;
import net.minecraft.screen.slot.ShulkerBoxSlot;
import net.minecraft.screen.slot.Slot;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(GenericContainerScreenHandler.class)
public abstract class GenericContainerScreenHandlerMixin {
    @ModifyExpressionValue(method = "addInventorySlots", at = @At(value = "NEW", target = "(Lnet/minecraft/inventory/Inventory;III)Lnet/minecraft/screen/slot/Slot;"))
    public Slot getProperSlot(Slot original, Inventory inventory, int left, int top, @Local(ordinal = 2) int i, @Local(ordinal = 3) int j){
        return inventory instanceof BagInventory ? new ShulkerBoxSlot(inventory, j + i * 9, left + j * 18, top + i * 18) : original;
    }
}
