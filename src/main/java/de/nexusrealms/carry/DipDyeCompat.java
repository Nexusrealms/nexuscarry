package de.nexusrealms.carry;

import de.nexusrealms.carry.item.BagItem;
import de.nexusrealms.carry.item.CarryItems;
import de.nexusrealms.dipdye.api.CauldronDipApi;
import de.nexusrealms.dipdye.api.CauldronDipCallback;
import net.minecraft.component.ComponentType;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.DyedColorComponent;
import net.minecraft.util.Hand;

public class DipDyeCompat {
    private static final CauldronDipCallback DIP_CALLBACK = (itemStack, colorCauldronBlockEntity, playerEntity, hand) -> {
        ComponentType<DyedColorComponent> componentType = hand == Hand.MAIN_HAND ? DataComponentTypes.DYED_COLOR : CarryItems.Components.BAG_STRAP_COLOR;
        if (!colorCauldronBlockEntity.isAdditive()) {
            return BagItem.setColorFromDyes(itemStack, colorCauldronBlockEntity.getDyes(), componentType);
        }
        BagItem.setColor(itemStack, componentType, colorCauldronBlockEntity.getAdditiveColor());
        return itemStack;
    };
    public static void init(){
        CauldronDipApi.register(CarryItems.SATCHEL, DIP_CALLBACK);
        CauldronDipApi.register(CarryItems.BACKPACK, DIP_CALLBACK);
    }
}
