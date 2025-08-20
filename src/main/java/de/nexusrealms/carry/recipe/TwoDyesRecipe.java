package de.nexusrealms.carry.recipe;

import de.nexusrealms.carry.item.BagItem;
import de.nexusrealms.carry.item.CarryItems;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.item.DyeItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.recipe.SpecialCraftingRecipe;
import net.minecraft.recipe.book.CraftingRecipeCategory;
import net.minecraft.recipe.input.CraftingRecipeInput;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.ItemTags;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.List;

public class TwoDyesRecipe extends SpecialCraftingRecipe {
    public TwoDyesRecipe(CraftingRecipeCategory category) {
        super(category);
    }

    @Override
    public boolean matches(CraftingRecipeInput input, World world) {
        if (input.getStackCount() < 2 || input.getWidth() < 2) {
            return false;
        } else {
            boolean bl = false;
            boolean bl2 = false;

            for(int i = 0; i < input.size(); ++i) {
                ItemStack itemStack = input.getStackInSlot(i);
                if (!itemStack.isEmpty()) {
                    if (itemStack.isIn(CarryItems.Tags.DOUBLE_DYEABLE) && isBagInValidSlot(i, input.getWidth())) {
                        if (bl) {
                            return false;
                        }

                        bl = true;
                    } else {
                        if (!(itemStack.getItem() instanceof DyeItem)) {
                            return false;
                        }

                        bl2 = true;
                    }
                }
            }

            return bl2 && bl;
        }
    }
    private boolean isBagInValidSlot(int slot, int width){
        return switch (width){
            case 2 -> true;
            case 3 -> slot % 3 == 1;
            default -> false;
        };
    }
    private boolean isInRightmostColumn(int slot, int width){
        return switch (width){
            case 2 -> slot % 2 == 1;
            case 3 -> slot % 3 == 2;
            default -> false;
        };
    }
    @Override
    public ItemStack craft(CraftingRecipeInput input, RegistryWrapper.WrapperLookup registries) {
        List<DyeItem> leftStacks = new ArrayList<>(3);
        List<DyeItem> rightStacks = new ArrayList<>(3);
        ItemStack result = ItemStack.EMPTY;
        for (int i = 0; i < input.size(); i++) {
            ItemStack stack = input.getStackInSlot(i);
            if(stack.isEmpty()) continue;
            if(stack.isIn(CarryItems.Tags.DOUBLE_DYEABLE)) {
                result = stack.copy();
            } else if (i % input.getWidth() == 0) {
                leftStacks.add((DyeItem) stack.getItem());
            } else if (isInRightmostColumn(i, input.getWidth())) {
                rightStacks.add((DyeItem) stack.getItem());
            }
        }
        if(!leftStacks.isEmpty()) BagItem.setColorFromDyesNoCopy(result, leftStacks, DataComponentTypes.DYED_COLOR);
        if(!rightStacks.isEmpty())BagItem.setColorFromDyesNoCopy(result, rightStacks, CarryItems.Components.BAG_STRAP_COLOR);
        return result;
    }

    @Override
    public RecipeSerializer<? extends SpecialCraftingRecipe> getSerializer() {
        return CarryRecipes.TWO_DYES;
    }
}
