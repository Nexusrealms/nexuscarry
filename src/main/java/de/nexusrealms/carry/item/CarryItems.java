package de.nexusrealms.carry.item;


import com.mojang.serialization.Codec;
import de.nexusrealms.carry.NexusCarry;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroupEntries;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.component.ComponentType;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.ContainerComponent;
import net.minecraft.component.type.DyedColorComponent;
import net.minecraft.entity.EntityType;
import net.minecraft.item.*;
import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Rarity;
import net.minecraft.util.Unit;

import java.util.function.BiConsumer;
import java.util.function.Function;

public class CarryItems {
    public static final Item SATCHEL = create("satchel", BagItem::new, new Item.Settings().maxCount(1), ItemGroups.TOOLS, (fabricItemGroupEntries, bagItem) -> fabricItemGroupEntries.addAfter(Items.PINK_BUNDLE, bagItem));
    public static final Item BACKPACK = create("backpack", BagItem::new, new Item.Settings().maxCount(1), ItemGroups.TOOLS, (fabricItemGroupEntries, bagItem) -> fabricItemGroupEntries.addAfter(SATCHEL, bagItem));

    private static <T extends Item> T create(String name, Function<Item.Settings, T> constructor, Item.Settings settings, RegistryKey<ItemGroup> itemGroup){
        return create(name, constructor, settings, itemGroup, FabricItemGroupEntries::add);
    }
    private static <T extends Item> T create(String name, Function<Item.Settings, T> constructor, Item.Settings settings, RegistryKey<ItemGroup> itemGroup, BiConsumer<FabricItemGroupEntries, T> itemGrouper){
        RegistryKey<Item> key = RegistryKey.of(RegistryKeys.ITEM, NexusCarry.id(name));
        settings.registryKey(key);
        T item = Registry.register(Registries.ITEM, key, constructor.apply(settings));
        ItemGroupEvents.modifyEntriesEvent(itemGroup).register(fabricItemGroupEntries -> itemGrouper.accept(fabricItemGroupEntries, item));
        return item;
    }
    public static void init(){
        Components.init();
    }

    public static class Components {
        public static final ComponentType<DyedColorComponent> BAG_STRAP_COLOR = create("bag_strap_color", DyedColorComponent.CODEC, DyedColorComponent.PACKET_CODEC);
        public static final ComponentType<Unit> CLEAR_ITEMS_ON_UPDATE = create("clear_items_on_update", Unit.CODEC, Unit.PACKET_CODEC);

        private static <T> ComponentType<T> create(String name, Codec<T> codec, PacketCodec<? super RegistryByteBuf, T> packetCodec){
            return Registry.register(Registries.DATA_COMPONENT_TYPE, NexusCarry.id(name), ComponentType.<T>builder().codec(codec).packetCodec(packetCodec).build());
        }
        public static void init(){}
    }
    public static class Tags {
        public static final TagKey<Item> TRANSFERS_PRIMARY_COLOR_AS_SECONDARY = TagKey.of(RegistryKeys.ITEM, NexusCarry.id("transfers_primary_color_as_secondary"));
        public static final TagKey<Item> DOUBLE_DYEABLE = TagKey.of(RegistryKeys.ITEM, NexusCarry.id("double_dyeable"));

    }
}