package com.mefiddzy.lmod.events;

import com.mefiddzy.lmod.LMod;
import com.mefiddzy.lmod.entity.ModEntities;
import com.mefiddzy.lmod.entity.client.GigaRoachModel;
import com.mefiddzy.lmod.entity.custom.GigaRoachEntity;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import net.neoforged.neoforge.event.entity.EntityAttributeCreationEvent;

@EventBusSubscriber(modid = LMod.MOD_ID, bus = EventBusSubscriber.Bus.MOD)
public class ModEventBusEvents {

    @SubscribeEvent
    public static void regLay(EntityRenderersEvent.RegisterLayerDefinitions e) {
        e.registerLayerDefinition(GigaRoachModel.LAYER_LOCATION, GigaRoachModel::createBodyLayer);
    }

    @SubscribeEvent
    public static void regAtr(EntityAttributeCreationEvent e) {
        e.put(ModEntities.GIGA_ROACH.get(), GigaRoachEntity.createAtr().build());
    }
}
