package com.mefiddzy.lmod.entity;

import com.mefiddzy.lmod.LMod;
import com.mefiddzy.lmod.entity.custom.GigaRoachEntity;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class ModEntities {
    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES = DeferredRegister.create(BuiltInRegistries.ENTITY_TYPE, LMod.MOD_ID);

    public static final Supplier<EntityType<GigaRoachEntity>> GIGA_ROACH = ENTITY_TYPES.register("giga_roach",
            () -> EntityType.Builder.of(GigaRoachEntity::new, MobCategory.CREATURE)
                    .sized(0.55f, 0.05f).build("giga_roach"));

    public static void reg(IEventBus bus) {
        ENTITY_TYPES.register(bus);
    }
}
