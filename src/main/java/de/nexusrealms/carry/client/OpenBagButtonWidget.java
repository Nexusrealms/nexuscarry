package de.nexusrealms.carry.client;

import de.nexusrealms.carry.NexusCarry;
import de.nexusrealms.carry.network.OpenBagPacket;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gl.RenderPipelines;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.ButtonTextures;
import net.minecraft.client.gui.screen.narration.NarrationMessageBuilder;
import net.minecraft.client.gui.widget.PressableWidget;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.ColorHelper;

public class OpenBagButtonWidget extends PressableWidget {
    public static final ButtonTextures BUTTON_TEXTURES = new ButtonTextures(NexusCarry.id("bag_button"), NexusCarry.id("bag_button_highlighted"));
    public OpenBagButtonWidget(int x, int y, int width, int height, Text text) {
        super(x, y, width, height, text);
    }

    protected void renderWidget(DrawContext context, int mouseX, int mouseY, float deltaTicks) {
        context.drawGuiTexture(RenderPipelines.GUI_TEXTURED, BUTTON_TEXTURES.get(true, isHovered()),  this.getX(), this.getY(),  20, 20);
        //int i = ColorHelper.withAlpha(this.alpha, this.active ? -1 : -6250336);
    }

    @Override
    public void onPress() {
        ClientPlayNetworking.send(OpenBagPacket.INSTANCE);
    }

    @Override
    protected void appendClickableNarrations(NarrationMessageBuilder builder) {

    }
}
