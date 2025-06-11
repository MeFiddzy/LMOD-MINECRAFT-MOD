package com.mefiddzy.lmod.entity.client;

import com.mefiddzy.lmod.LMod;
import com.mefiddzy.lmod.entity.custom.GigaRoachEntity;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class GigaRoachRenderer extends MobRenderer<GigaRoachEntity, GigaRoachModel<GigaRoachEntity>> {

    public GigaRoachRenderer(EntityRendererProvider.Context context) {
        super(context, new GigaRoachModel<>(context.bakeLayer(GigaRoachModel.LAYER_LOCATION)), 0.25f);
    }

    @Override
    public ResourceLocation getTextureLocation(GigaRoachEntity entity) {
        return ResourceLocation.fromNamespaceAndPath(LMod.MOD_ID, "textures/entity/giga_roach/giga_roach.png");
    }

    @Override
    public void render(GigaRoachEntity entity, float entityYaw, float partialTicks, PoseStack poseStack, MultiBufferSource buffer, int packedLight) {
        if (entity.isBaby())
            poseStack.scale(0.45f, 0.45f, 0.45f);
        else
            poseStack.scale(1f, 1f, 1f);

        super.render(entity, entityYaw, partialTicks, poseStack, buffer, packedLight);
    }
}
