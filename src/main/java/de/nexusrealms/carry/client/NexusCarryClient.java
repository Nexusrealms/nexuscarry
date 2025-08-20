package de.nexusrealms.carry.client;

import de.nexusrealms.carry.NexusCarry;
import de.nexusrealms.carry.item.CarryItems;
import de.nexusrealms.carry.network.CarryPackets;
import dev.emi.trinkets.api.client.TrinketRendererRegistry;
import net.fabricmc.api.ClientModInitializer;

public class NexusCarryClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        initTrinketRenderers();
    }
    private static void initTrinketRenderers(){
        TrinketRendererRegistry.registerRenderer(CarryItems.SATCHEL, new BagRenderer(
                SatchelModel::getBaseTexturedModeData,
                SatchelModel::getDyedTexturedModeData,
                SatchelModel::getStrapTexturedModeData,
                NexusCarry.id("textures/item/wearable/satchel_overlay.png"),
                NexusCarry.id("textures/item/wearable/satchel_base.png"),
                NexusCarry.id("textures/item/wearable/satchel_straps.png")
        ));
        TrinketRendererRegistry.registerRenderer(CarryItems.BACKPACK, new BagRenderer(
                BackpackModel::getBaseTexturedModeData,
                BackpackModel::getDyedTexturedModeData,
                BackpackModel::getStrapTexturedModeData,
                NexusCarry.id("textures/item/wearable/backpack_overlay.png"),
                NexusCarry.id("textures/item/wearable/backpack_base.png"),
                NexusCarry.id("textures/item/wearable/backpack_straps.png")
        ));
    }
}
