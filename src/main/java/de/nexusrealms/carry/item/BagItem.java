package de.nexusrealms.carry.item;

import dev.emi.trinkets.api.TrinketItem;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class BagItem extends TrinketItem {
    private final int rows;
    public BagItem(int rows, Settings settings) {
        super(settings);
        this.rows = rows;
    }
    public BagItem(Settings settings) {
        this(3, settings);
    }

    public void openScreen(PlayerEntity player, ItemStack stack){

    }
    @Override
    public boolean canBeNested() {
        return false;
    }
}
