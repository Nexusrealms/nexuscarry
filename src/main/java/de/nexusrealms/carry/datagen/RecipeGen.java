package de.nexusrealms.carry.datagen;


import de.nexusrealms.carry.NexusCarry;
import de.nexusrealms.carry.item.CarryItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.data.recipe.RecipeExporter;
import net.minecraft.data.recipe.RecipeGenerator;
import net.minecraft.data.recipe.ShapedRecipeJsonBuilder;
import net.minecraft.item.Items;
import net.minecraft.recipe.book.RecipeCategory;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.RegistryWrapper;

import java.util.concurrent.CompletableFuture;

public class RecipeGen extends FabricRecipeProvider {
    public RecipeGen(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected RecipeGenerator getRecipeGenerator(RegistryWrapper.WrapperLookup wrapperLookup, RecipeExporter recipeExporter) {
        return new Generator(wrapperLookup, recipeExporter);
    }
    @Override
    public String getName() {
        return NexusCarry.MOD_ID;
    }

    private static class Generator extends RecipeGenerator {

        protected Generator(RegistryWrapper.WrapperLookup registries, RecipeExporter exporter) {
            super(registries, exporter);
        }

        @Override
        public void generate() {
            ShapedRecipeJsonBuilder.create(registries.getOrThrow(RegistryKeys.ITEM), RecipeCategory.TOOLS, CarryItems.BACKPACK)
                    .pattern("LS ")
                    .pattern("I L")
                    .pattern(" LS")
                    .input('L', Items.RABBIT_HIDE)
                    .input('S', Items.LEATHER)
                    .input('I', Items.COPPER_INGOT)
                    .criterion(hasItem(Items.LEATHER), conditionsFromItem(Items.LEATHER))
                    .offerTo(exporter);
            ShapedRecipeJsonBuilder.create(registries.getOrThrow(RegistryKeys.ITEM), RecipeCategory.TOOLS, CarryItems.SATCHEL)
                    .pattern("SLS")
                    .pattern("L L")
                    .pattern(" I ")
                    .input('L', Items.RABBIT_HIDE)
                    .input('S', Items.LEATHER)
                    .input('I', Items.COPPER_INGOT)
                    .criterion(hasItem(Items.LEATHER), conditionsFromItem(Items.LEATHER))
                    .offerTo(exporter);
        }
    }
}