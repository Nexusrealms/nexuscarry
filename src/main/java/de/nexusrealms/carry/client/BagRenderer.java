package de.nexusrealms.carry.client;

import de.nexusrealms.carry.item.BagItem;
import de.nexusrealms.carry.item.CarryItems;
import dev.emi.trinkets.api.SlotReference;
import dev.emi.trinkets.api.client.TrinketRenderer;
import net.minecraft.client.model.ModelPart;
import net.minecraft.client.model.TexturedModelData;
import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.RenderLayers;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.command.OrderedRenderCommandQueue;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.render.entity.state.EntityRenderState;
import net.minecraft.client.render.entity.state.LivingEntityRenderState;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;

import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Supplier;

public class BagRenderer implements TrinketRenderer {
    private final Supplier<TexturedModelData> overlayLayer;
    private final Supplier<TexturedModelData> baseLayer;
    private final Supplier<TexturedModelData> strapLayer;
    private final Identifier overlayTexture;
    private final Identifier baseTexture;
    private final Identifier strapTexture;
    private final BiFunction<ModelPart, Part, EntityModel<EntityRenderState>> modelFactory;
    private EntityModel<EntityRenderState> overlayModel;
    private EntityModel<EntityRenderState> baseModel;
    private EntityModel<EntityRenderState> strapModel;

    public BagRenderer(BiFunction<ModelPart, Part, EntityModel<EntityRenderState>> modelFactory, Supplier<TexturedModelData> overlayLayer, Supplier<TexturedModelData> baseLayer, Supplier<TexturedModelData> strapLayer, Identifier overlayTexture, Identifier baseTexture, Identifier strapTexture) {
        this.overlayLayer = overlayLayer;
        this.baseLayer = baseLayer;
        this.strapLayer = strapLayer;
        this.overlayTexture = overlayTexture;
        this.baseTexture = baseTexture;
        this.strapTexture = strapTexture;
        this.modelFactory = modelFactory;
    }

    @Override
    public void render(ItemStack stack, SlotReference slotReference, EntityModel<? extends LivingEntityRenderState> contextModel, MatrixStack matrices, OrderedRenderCommandQueue vertexConsumers, int light, LivingEntityRenderState state, float limbAngle, float limbDistance) {
        if(overlayModel == null){
            overlayModel = modelFactory.apply(overlayLayer.get().createModel(), Part.OVERLAY);
        }
        if(baseModel == null){
            baseModel = modelFactory.apply(baseLayer.get().createModel(), Part.BASE);
        }
        if(strapModel == null){
            strapModel = modelFactory.apply(strapLayer.get().createModel(), Part.STRAP);
        }
        int color = BagItem.getColor(stack, DataComponentTypes.DYED_COLOR, 0xffcba4);
        int strapColor = BagItem.getColor(stack, CarryItems.Components.BAG_STRAP_COLOR, 0xffa8a8);
        vertexConsumers.submitModel(overlayModel, state, matrices, RenderLayers.entityTranslucent(overlayTexture), light, OverlayTexture.DEFAULT_UV, 0xffffffff, null, 0, null);
        vertexConsumers.submitModel(baseModel, state, matrices, RenderLayers.entityTranslucent(baseTexture), light, OverlayTexture.DEFAULT_UV, color, null, 0,null);
        vertexConsumers.submitModel(strapModel, state, matrices, RenderLayers.entityTranslucent(strapTexture), light, OverlayTexture.DEFAULT_UV, strapColor, null, 0,null);
    }
    public enum Part {
        BASE,
        OVERLAY,
        STRAP
    }
}
