package de.nexusrealms.carry.mixin;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.llamalad7.mixinextras.sugar.Local;
import de.nexusrealms.carry.item.CarryItems;
import net.minecraft.item.ItemStack;
import net.minecraft.recipe.ArmorDyeRecipe;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(ArmorDyeRecipe.class)
public class ArmorDyeRecipeMixin {
    @ModifyExpressionValue(method = "matches(Lnet/minecraft/recipe/input/CraftingRecipeInput;Lnet/minecraft/world/World;)Z", at = @At(value = "INVOKE", target = "Lnet/minecraft/item/ItemStack;isIn(Lnet/minecraft/registry/tag/TagKey;)Z"))
    public boolean isNotIn(boolean original, @Local ItemStack stack){
        return !stack.isIn(CarryItems.Tags.DOUBLE_DYEABLE) && original;
    }
}
