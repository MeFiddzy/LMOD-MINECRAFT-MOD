package com.mefiddzy.lmod.entity.client;

import com.mefiddzy.lmod.LMod;
import com.mefiddzy.lmod.entity.custom.GigaRoachEntity;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;

import javax.swing.text.html.parser.Entity;

public class GigaRoachModel<T extends GigaRoachEntity> extends HierarchicalModel<T> {
    public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath(LMod.MOD_ID, "gigi_roach"), "main");
    private final ModelPart bone10;
    private final ModelPart bone9;
    private final ModelPart bone8;
    private final ModelPart bone7;
    private final ModelPart bone6;
    private final ModelPart bone5;
    private final ModelPart bone4;
    private final ModelPart bone;
    private final ModelPart bone2;
    private final ModelPart bone3;

    public GigaRoachModel(ModelPart root) {
        this.bone10 = root.getChild("bone10");
        this.bone9 = root.getChild("bone9");
        this.bone8 = root.getChild("bone8");
        this.bone7 = root.getChild("bone7");
        this.bone6 = root.getChild("bone6");
        this.bone5 = root.getChild("bone5");
        this.bone4 = root.getChild("bone4");
        this.bone = root.getChild("bone");
        this.bone2 = root.getChild("bone2");
        this.bone3 = root.getChild("bone3");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition bone10 = partdefinition.addOrReplaceChild("bone10", CubeListBuilder.create(), PartPose.offset(-0.101F, 23.4572F, 2.3164F));

        PartDefinition foot3_r1 = bone10.addOrReplaceChild("foot3_r1", CubeListBuilder.create().texOffs(8, 10).addBox(-0.5F, -1.5F, -0.5F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.8283F, 0.1984F, -0.2244F));

        PartDefinition bone9 = partdefinition.addOrReplaceChild("bone9", CubeListBuilder.create(), PartPose.offset(4.899F, 23.4572F, 2.3164F));

        PartDefinition foot4_r1 = bone9.addOrReplaceChild("foot4_r1", CubeListBuilder.create().texOffs(4, 14).addBox(-0.5F, -1.5F, -0.5F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.8283F, 0.1984F, -0.2244F));

        PartDefinition bone8 = partdefinition.addOrReplaceChild("bone8", CubeListBuilder.create(), PartPose.offset(-2.899F, 23.5428F, -2.6836F));

        PartDefinition foot2_r1 = bone8.addOrReplaceChild("foot2_r1", CubeListBuilder.create().texOffs(0, 10).addBox(-0.5F, -1.5F, -0.5F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -0.8283F, -0.1984F, -0.2244F));

        PartDefinition bone7 = partdefinition.addOrReplaceChild("bone7", CubeListBuilder.create(), PartPose.offset(0.101F, 23.5428F, -2.6836F));

        PartDefinition foot1_r1 = bone7.addOrReplaceChild("foot1_r1", CubeListBuilder.create().texOffs(8, 6).addBox(-0.5F, -1.5F, -0.5F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -0.8283F, -0.1984F, -0.2244F));

        PartDefinition bone6 = partdefinition.addOrReplaceChild("bone6", CubeListBuilder.create(), PartPose.offset(5.101F, 23.5428F, -2.6836F));

        PartDefinition foot2_r2 = bone6.addOrReplaceChild("foot2_r2", CubeListBuilder.create().texOffs(12, 6).addBox(-0.5F, -1.5F, -0.5F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -0.8283F, -0.1984F, -0.2244F));

        PartDefinition bone5 = partdefinition.addOrReplaceChild("bone5", CubeListBuilder.create(), PartPose.offset(-4.0F, 23.0F, 1.0F));

        PartDefinition foot2_r3 = bone5.addOrReplaceChild("foot2_r3", CubeListBuilder.create().texOffs(4, 10).addBox(-0.5F, -1.5F, -0.5F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.899F, 0.4572F, 1.3164F, 0.8283F, 0.1984F, -0.2244F));

        PartDefinition bone4 = partdefinition.addOrReplaceChild("bone4", CubeListBuilder.create(), PartPose.offset(-5.0F, 24.0F, 0.0F));

        PartDefinition body_r1 = bone4.addOrReplaceChild("body_r1", CubeListBuilder.create().texOffs(0, 0).addBox(-5.0F, -1.0F, -2.0F, 10.0F, 2.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(5.909F, -2.0834F, 0.0F, 0.0F, 0.0F, -0.0873F));

        PartDefinition bone = partdefinition.addOrReplaceChild("bone", CubeListBuilder.create(), PartPose.offset(1.899F, 23.4572F, 2.3164F));

        PartDefinition foot3_r2 = bone.addOrReplaceChild("foot3_r2", CubeListBuilder.create().texOffs(0, 14).addBox(-0.5F, -1.5F, -0.5F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.8283F, 0.1984F, -0.2244F));

        PartDefinition bone2 = partdefinition.addOrReplaceChild("bone2", CubeListBuilder.create(), PartPose.offset(2.101F, 23.5428F, -2.6836F));

        PartDefinition foot3_r3 = bone2.addOrReplaceChild("foot3_r3", CubeListBuilder.create().texOffs(12, 10).addBox(-0.5F, -1.5F, -0.5F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -0.8283F, -0.1984F, -0.2244F));

        PartDefinition bone3 = partdefinition.addOrReplaceChild("bone3", CubeListBuilder.create().texOffs(0, 6).addBox(-1.0F, -2.0F, -1.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(-5.0F, 24.0F, 0.0F));

        return LayerDefinition.create(meshdefinition, 32, 32);
    }

    @Override
    public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, int c) {
        bone10.render(poseStack, vertexConsumer, packedLight, packedOverlay, c);
        bone9.render(poseStack, vertexConsumer, packedLight, packedOverlay, c);
        bone8.render(poseStack, vertexConsumer, packedLight, packedOverlay, c);
        bone7.render(poseStack, vertexConsumer, packedLight, packedOverlay, c);
        bone6.render(poseStack, vertexConsumer, packedLight, packedOverlay, c);
        bone5.render(poseStack, vertexConsumer, packedLight, packedOverlay, c);
        bone4.render(poseStack, vertexConsumer, packedLight, packedOverlay, c);
        bone.render(poseStack, vertexConsumer, packedLight, packedOverlay, c);
        bone2.render(poseStack, vertexConsumer, packedLight, packedOverlay, c);
        bone3.render(poseStack, vertexConsumer, packedLight, packedOverlay, c);
    }

    @Override
    public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        this.root().getAllParts().forEach(ModelPart::resetPose);
        this.applyHeadRotation(netHeadYaw, headPitch);

        this.animateWalk(GigaRoachAnims.WALK, limbSwing, limbSwingAmount, 2f, 2.5f);
        this.animate(entity.idleAnimS, GigaRoachAnims.IDLE, ageInTicks, 1f);
    }

    private void applyHeadRotation(float headYaw, float headPitch) {
        headYaw = Mth.clamp(headYaw, -30f, 30f);
        headPitch = Mth.clamp(headPitch, -25f, 45);

        this.bone3.yRot = headYaw * ((float) Math.PI / 180f);
        this.bone3.xRot = headPitch *  ((float) Math.PI / 180f);
    }

    @Override
    public ModelPart root() {
        return bone4;
    }

}
