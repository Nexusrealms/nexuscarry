package de.nexusrealms.carry;

import com.mojang.brigadier.arguments.BoolArgumentType;
import com.mojang.serialization.Codec;
import de.nexusrealms.carry.item.BagItem;
import de.nexusrealms.carry.item.CarryItems;
import de.nexusrealms.carry.network.CarryPackets;
import de.nexusrealms.carry.recipe.CarryRecipes;
import de.nexusrealms.dipdye.api.CauldronDipApi;
import dev.emi.trinkets.api.event.TrinketEquipCallback;
import dev.emi.trinkets.api.event.TrinketUnequipCallback;
import net.fabricmc.api.ModInitializer;

import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.component.ComponentType;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.ContainerComponent;
import net.minecraft.component.type.DyedColorComponent;
import net.minecraft.inventory.Inventory;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.resource.featuretoggle.FeatureFlags;
import net.minecraft.resource.featuretoggle.FeatureSet;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.Hand;
import net.minecraft.util.Identifier;
import net.minecraft.world.rule.GameRule;
import net.minecraft.world.rule.GameRuleCategory;
import net.minecraft.world.rule.GameRuleType;
import net.minecraft.world.rule.GameRuleVisitor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class NexusCarry implements ModInitializer {
	public static final String MOD_ID = "nexuscarry";

	// This logger is used to write text to the console and the log file.
	// It is considered best practice to use your mod id as the logger's name.
	// That way, it's clear which mod wrote info, warnings, and errors.
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);
	public static final GameRule<Boolean> DROP_ITEMS_WHEN_UNEQUIPPED = Registry.register(Registries.GAME_RULE, Identifier.of(MOD_ID, "drop_items_when_unequipped"), new GameRule<>(GameRuleCategory.MISC, GameRuleType.BOOL, BoolArgumentType.bool(), GameRuleVisitor::visitBoolean, Codec.BOOL, (value) -> value ? 1 : 0, false, FeatureSet.empty()));
	public static Identifier id(String name){
		return Identifier.of(MOD_ID, name);
	}
	@Override
	public void onInitialize() {
		// This code runs as soon as Minecraft is in a mod-load-ready state.
		// However, some things (like resources) may still be uninitialized.
		// Proceed with mild caution.
		CarryItems.init();
		CarryPackets.init();
		CarryRecipes.init();

		LOGGER.info("Hello Fabric world!");
		if(FabricLoader.getInstance().isModLoaded("dip-dye")) DipDyeCompat.init();
	}


}