package de.nexusrealms.carry.mixin.client;

import de.nexusrealms.carry.client.OpenBagButtonWidget;
import net.minecraft.client.gui.ScreenPos;
import net.minecraft.client.gui.screen.ingame.InventoryScreen;
import net.minecraft.client.gui.screen.ingame.RecipeBookScreen;
import net.minecraft.client.gui.screen.recipebook.RecipeBookWidget;
import net.minecraft.client.gui.widget.TexturedButtonWidget;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.screen.PlayerScreenHandler;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(InventoryScreen.class)
public abstract class InventoryScreenMixin extends RecipeBookScreen<PlayerScreenHandler> {

    public InventoryScreenMixin(PlayerScreenHandler handler, RecipeBookWidget<?> recipeBook, PlayerInventory inventory, Text title) {
        super(handler, recipeBook, inventory, title);
    }

    @Inject(method = "init", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/screen/ingame/RecipeBookScreen;init()V", shift = At.Shift.AFTER))
    public void onInit(CallbackInfo ci){
        ScreenPos screenPos = this.getRecipeBookButtonPos();
        OpenBagButtonWidget openBagButtonWidget = new OpenBagButtonWidget(screenPos.x() + 28, screenPos.y() - 1, 20, 20, Text.literal("bag"));
        this.addDrawableChild(openBagButtonWidget);
        this.addSelectableChild(openBagButtonWidget);
    }
}
