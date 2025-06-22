package com.mefiddzy.lmod.recipe;

import com.mefiddzy.lmod.LMod;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModRecipes {
    public static final DeferredRegister<RecipeSerializer<?>> SERIALIZERS =
            DeferredRegister.create(Registries.RECIPE_SERIALIZER, LMod.MOD_ID);
    public static final DeferredRegister<RecipeType<?>> TYPES =
            DeferredRegister.create(Registries.RECIPE_TYPE, LMod.MOD_ID);

    public static final DeferredHolder<RecipeSerializer<?>, RecipeSerializer<PlateApplierRecipe>> PLATE_APPLIER_SERIALIZER =
            SERIALIZERS.register("plate_applier", PlateApplierRecipe.Serializer::new);

    public static final DeferredHolder<RecipeType<?>, RecipeType<PlateApplierRecipe>> PLATE_APPLIER_TYPE =
            TYPES.register("plate_applier", () -> new RecipeType<PlateApplierRecipe>() {
                @Override
                public String toString() {
                    return "plate_applier";
                }
            });

    public static void reg(IEventBus bus) {
        SERIALIZERS.register(bus);
        TYPES.register(bus);
    }
}
