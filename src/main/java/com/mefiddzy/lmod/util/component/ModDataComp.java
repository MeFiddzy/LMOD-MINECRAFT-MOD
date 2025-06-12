package com.mefiddzy.lmod.util.component;

import com.mefiddzy.lmod.LMod;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.core.registries.Registries;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import com.mojang.serialization.Codec;

import java.util.function.UnaryOperator;

public class ModDataComp {
    public static final DeferredRegister<DataComponentType<?>> DATA_COMPONENT_TYPES = DeferredRegister.createDataComponents(Registries.DATA_COMPONENT_TYPE, LMod.MOD_ID);

    public static final DeferredHolder<DataComponentType<?>, DataComponentType<Integer>> KILLS_WITH_ITEM = reg("kills_with_item",
            integerBuilder -> integerBuilder.persistent(Codec.INT));

    private static <T>DeferredHolder<DataComponentType<?>, DataComponentType<T>> reg(String name, UnaryOperator<DataComponentType.Builder<T>> builderOp) {
        return DATA_COMPONENT_TYPES.register(name, () -> builderOp.apply(DataComponentType.builder()).build());
    }

    public static void reg(IEventBus bus) {
        DATA_COMPONENT_TYPES.register(bus);
    }

}
