package de.nexusrealms.carry.client;

import net.minecraft.client.model.*;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.render.entity.state.EntityRenderState;

public class BackpackModel extends EntityModel<EntityRenderState> {
    protected BackpackModel(ModelPart root, BagRenderer.Part part) {
        super(root);
    }
    public static TexturedModelData getBaseTexturedModeData() {
        ModelData modelData = new ModelData();
        ModelPartData modelPartData = modelData.getRoot();
        ModelPartData bb_main = modelPartData.addChild("bb_main", ModelPartBuilder.create().uv(24, 8).cuboid(-3.0F, -11.0F, 3.15F, 6.0F, 4.0F, 2.0F, new Dilation(0.4F)), ModelTransform.origin(0.0F, 12.0F, 0.0F));
        return TexturedModelData.of(modelData, 64, 64);
    }

    @Override
    public void setAngles(EntityRenderState state) {
        super.setAngles(state);
        if(state.sneaking){
            this.root.pitch = 0.5F;
            this.root.originY += 3.2F;
        }
    }

    public static TexturedModelData getDyedTexturedModeData() {
        ModelData modelData = new ModelData();
        ModelPartData modelPartData = modelData.getRoot();
        ModelPartData bb_main = modelPartData.addChild("bb_main", ModelPartBuilder.create().uv(0, 0).cuboid(-4.0F, -12.0F, -2.0F, 8.0F, 12.0F, 4.0F, new Dilation(0.0F))
                .uv(24, 0).cuboid(-3.0F, -11.0F, 2.15F, 6.0F, 5.0F, 3.0F, new Dilation(0.2F))
                .uv(0, 16).cuboid(-3.5F, -6.0F, 2.0F, 7.0F, 5.0F, 4.0F, new Dilation(0.2F))
                .uv(24, 8).cuboid(-3.0F, -11.0F, 3.15F, 6.0F, 4.0F, 2.0F, new Dilation(0.4F))
                .uv(22, 16).cuboid(-3.5F, -6.0F, 2.0F, 7.0F, 5.0F, 4.0F, new Dilation(0.0F)), ModelTransform.origin(0.0F, 12.0F, 0.0F));
        return TexturedModelData.of(modelData, 64, 64);
    }
    public static TexturedModelData getStrapTexturedModeData() {
        ModelData modelData = new ModelData();
        ModelPartData modelPartData = modelData.getRoot();
        ModelPartData bb_main = modelPartData.addChild("bb_main", ModelPartBuilder.create().uv(0, 0).cuboid(-4.0F, -12.0F, -2.0F, 8.0F, 12.0F, 4.0F, new Dilation(0.0F))
                .uv(24, 0).cuboid(-3.0F, -11.0F, 2.15F, 6.0F, 5.0F, 3.0F, new Dilation(0.2F))
                .uv(0, 16).cuboid(-3.5F, -6.0F, 2.0F, 7.0F, 5.0F, 4.0F, new Dilation(0.2F))
                .uv(24, 8).cuboid(-3.0F, -11.0F, 3.15F, 6.0F, 4.0F, 2.0F, new Dilation(0.4F))
                .uv(22, 16).cuboid(-3.5F, -6.0F, 2.0F, 7.0F, 5.0F, 4.0F, new Dilation(0.0F)), ModelTransform.origin(0.0F, 12.0F, 0.0F));
        return TexturedModelData.of(modelData, 64, 64);
    }
}
