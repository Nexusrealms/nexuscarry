package de.nexusrealms.carry.client;

import de.nexusrealms.carry.NexusCarry;
import de.nexusrealms.carry.item.CarryItems;
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
                NexusCarry.id("textures/item/satchel_base.png"),
                NexusCarry.id("textures/item/satchel_dye.png"),
                NexusCarry.id("textures/item/satchel_straps.png")
        ));
    }
}
