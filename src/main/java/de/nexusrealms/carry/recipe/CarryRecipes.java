package de.nexusrealms.carry.recipe;

import de.nexusrealms.carry.NexusCarry;
import net.minecraft.recipe.ArmorDyeRecipe;
import net.minecraft.recipe.Recipe;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.recipe.SpecialCraftingRecipe;
import net.minecraft.recipe.book.CraftingRecipeCategory;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;

public class CarryRecipes {
    public static final RecipeSerializer<CraftBagRecipe> CRAFT_BAG = createSerializer("crafting_special_craftbag", new CraftBagRecipe.Serializer());
    public static final RecipeSerializer<TwoDyesRecipe> TWO_DYES = createSerializer("crafting_special_twodyes", new SpecialCraftingRecipe.SpecialRecipeSerializer<>(TwoDyesRecipe::new));

    private static <S extends RecipeSerializer<T>, T extends Recipe<?>> RecipeSerializer<T> createSerializer(String name, S serializer){
        return Registry.register(Registries.RECIPE_SERIALIZER, NexusCarry.id(name), serializer);
    }
    public static void init(){}
}
