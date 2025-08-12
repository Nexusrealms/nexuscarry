package de.nexusrealms.carry.client;

import net.minecraft.client.model.*;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.render.entity.state.EntityRenderState;

public class SatchelModel extends EntityModel<EntityRenderState> {
    protected SatchelModel(ModelPart root) {
        super(root);
    }
    public static TexturedModelData getBaseTexturedModeData() {
        ModelData modelData = new ModelData();
        ModelPartData modelPartData = modelData.getRoot();
        ModelPartData bb_main = modelPartData.addChild("bb_main", ModelPartBuilder.create(), ModelTransform.origin(0.0F, 14f, 0.0F));

        ModelPartData cube_r1 = bb_main.addChild("cube_r1", ModelPartBuilder.create().uv(24, 17).cuboid(-1.0F, -1.0F, -3.0F, 1.0F, 14.0F, 5.0F, new Dilation(0.0F)), ModelTransform.of(-2.5F, -13.65F, 0.5F, 0.0F, 0.0F, -0.6545F));

        ModelPartData cube_r2 = bb_main.addChild("cube_r2", ModelPartBuilder.create().uv(0, 0).cuboid(-1.0F, -2.0F, -5.0F, 3.0F, 7.0F, 10.0F, new Dilation(0.0F))
                .uv(0, 17).cuboid(0.0F, -2.0F, -5.0F, 2.0F, 4.0F, 10.0F, new Dilation(0.2F)), ModelTransform.of(4.75F, -3.0F, 0.0F, 0.0F, 0.0F, -0.0873F));
        return TexturedModelData.of(modelData, 64, 64);
    }
    public static TexturedModelData getDyedTexturedModeData() {
        ModelData modelData = new ModelData();
        ModelPartData modelPartData = modelData.getRoot();
        ModelPartData bb_main = modelPartData.addChild("bb_main", ModelPartBuilder.create(), ModelTransform.origin(0.0F, 14f, 0.0F));

        ModelPartData cube_r1 = bb_main.addChild("cube_r1", ModelPartBuilder.create().uv(0, 0).cuboid(-1.0F, -2.0F, -5.0F, 3.0F, 7.0F, 10.0F, new Dilation(0.0F))
                .uv(0, 17).cuboid(0.0F, -2.0F, -5.0F, 2.0F, 4.0F, 10.0F, new Dilation(0.2F)), ModelTransform.of(4.75F, -3.0F, 0.0F, 0.0F, 0.0F, -0.0873F));

        ModelPartData cube_r2 = bb_main.addChild("cube_r2", ModelPartBuilder.create().uv(24, 17).cuboid(-1.0F, -1.0F, -3.0F, 1.0F, 14.0F, 5.0F, new Dilation(0.0F)), ModelTransform.of(-2.5F, -13.65F, 0.5F, 0.0F, 0.0F, -0.6545F));
        return TexturedModelData.of(modelData, 64, 64);
    }
    public static TexturedModelData getStrapTexturedModeData() {
        ModelData modelData = new ModelData();
        ModelPartData modelPartData = modelData.getRoot();
        ModelPartData bb_main = modelPartData.addChild("bb_main", ModelPartBuilder.create(), ModelTransform.origin(0.0F, 14f, 0.0F));

        ModelPartData cube_r1 = bb_main.addChild("cube_r1", ModelPartBuilder.create().uv(0, 0).cuboid(-1.0F, -2.0F, -5.0F, 3.0F, 7.0F, 10.0F, new Dilation(0.0F))
                .uv(0, 17).cuboid(0.0F, -2.0F, -5.0F, 2.0F, 4.0F, 10.0F, new Dilation(0.2F)), ModelTransform.of(4.75F, -3.0F, 0.0F, 0.0F, 0.0F, -0.0873F));

        ModelPartData cube_r2 = bb_main.addChild("cube_r2", ModelPartBuilder.create().uv(24, 17).cuboid(-1.0F, -1.0F, -3.0F, 1.0F, 14.0F, 5.0F, new Dilation(0.0F)), ModelTransform.of(-2.5F, -13.65F, 0.5F, 0.0F, 0.0F, -0.6545F));
        return TexturedModelData.of(modelData, 64, 64);
    }
}
