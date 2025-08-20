package de.nexusrealms.carry.client;

import de.nexusrealms.carry.item.BagItem;
import de.nexusrealms.carry.item.CarryItems;
import dev.emi.trinkets.api.SlotReference;
import dev.emi.trinkets.api.client.TrinketRenderer;
import net.minecraft.client.model.TexturedModelData;
import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.render.entity.state.EntityRenderState;
import net.minecraft.client.render.entity.state.LivingEntityRenderState;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;

import java.util.function.Supplier;

public class BagRenderer implements TrinketRenderer {
    private final Supplier<TexturedModelData> overlayLayer;
    private final Supplier<TexturedModelData> baseLayer;
    private final Supplier<TexturedModelData> strapLayer;
    private final Identifier overlayTexture;
    private final Identifier dyeTexture;
    private final Identifier strapTexture;

    private EntityModel<EntityRenderState> baseModel;
    private EntityModel<EntityRenderState> dyeModel;
    private EntityModel<EntityRenderState> strapModel;

    public BagRenderer(Supplier<TexturedModelData> baseLayer, Supplier<TexturedModelData> dyeLayer, Supplier<TexturedModelData> strapLayer, Identifier baseTexture, Identifier dyeTexture, Identifier strapTexture) {
        this.overlayLayer = baseLayer;
        this.baseLayer = dyeLayer;
        this.strapLayer = strapLayer;
        this.overlayTexture = baseTexture;
        this.dyeTexture = dyeTexture;
        this.strapTexture = strapTexture;
    }


    @Override
    public void render(ItemStack stack, SlotReference slotReference, EntityModel<? extends LivingEntityRenderState> contextModel, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, LivingEntityRenderState state, float limbAngle, float limbDistance) {
        if(baseModel == null){
            baseModel = new SatchelModel(overlayLayer.get().createModel());
        }
        if(dyeModel == null){
            dyeModel = new SatchelModel(baseLayer.get().createModel());
        }
        if(strapModel == null){
            strapModel = new SatchelModel(strapLayer.get().createModel());
        }
        int color = BagItem.getColor(stack, DataComponentTypes.DYED_COLOR, 0xffcba4);
        int strapColor = BagItem.getColor(stack, CarryItems.Components.BAG_STRAP_COLOR, 0xffa8a8);
        baseModel.render(matrices, vertexConsumers.getBuffer(RenderLayer.getEntityTranslucent(overlayTexture)), light, OverlayTexture.DEFAULT_UV, 0xffffffff);
        dyeModel.render(matrices, vertexConsumers.getBuffer(RenderLayer.getEntityTranslucent(dyeTexture)), light, OverlayTexture.DEFAULT_UV, color);
        strapModel.render(matrices, vertexConsumers.getBuffer(RenderLayer.getEntityTranslucent(strapTexture)), light, OverlayTexture.DEFAULT_UV, strapColor);

    }

}
