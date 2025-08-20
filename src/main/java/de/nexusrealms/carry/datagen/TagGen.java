package de.nexusrealms.carry.datagen;


import de.nexusrealms.carry.item.CarryItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.registry.tag.ItemTags;

import java.util.concurrent.CompletableFuture;

public class TagGen {

    public static class ItemGen extends FabricTagProvider.ItemTagProvider {

        public ItemGen(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
            super(output, registriesFuture);
        }

        @Override
        protected void configure(RegistryWrapper.WrapperLookup wrapperLookup) {
            valueLookupBuilder(ItemTags.DYEABLE)
                    .add(CarryItems.SATCHEL, CarryItems.BACKPACK, CarryItems.LEATHER_STRAPS);
            valueLookupBuilder(CarryItems.Tags.TRANSFERS_PRIMARY_COLOR_AS_SECONDARY)
                    .add(CarryItems.LEATHER_STRAPS);
        }
    }

    public static class BlockGen extends FabricTagProvider.BlockTagProvider {
        public BlockGen(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
            super(output, registriesFuture);
        }

        @Override
        protected void configure(RegistryWrapper.WrapperLookup wrapperLookup) {

        }

    }

    public static class EntityGen extends FabricTagProvider.EntityTypeTagProvider {

        public EntityGen(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
            super(output, registriesFuture);
        }

        @Override
        protected void configure(RegistryWrapper.WrapperLookup wrapperLookup) {

        }
    }
}
