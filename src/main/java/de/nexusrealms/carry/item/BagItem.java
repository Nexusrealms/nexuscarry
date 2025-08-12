package de.nexusrealms.carry.item;

import dev.emi.trinkets.api.TrinketItem;
import net.minecraft.item.Item;

public class BagItem extends TrinketItem {
    private final int rows;
    public BagItem(int rows, Settings settings) {
        super(settings);
        this.rows = rows;
    }
    public BagItem(Settings settings) {
        this(3, settings);
    }


    @Override
    public boolean canBeNested() {
        return false;
    }
}
