package com.mefiddzy.lmod.item;

import com.mefiddzy.lmod.LMod;
import net.minecraft.Util;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.crafting.Ingredient;

import java.util.EnumMap;
import java.util.List;
import java.util.function.Supplier;

public class ModArmorMateterial {
    public static final Holder<ArmorMaterial> ENPOWERED_GOLD_ARMOR_MAT = reg("enpowered_gold", Util.make(new EnumMap<>(ArmorItem.Type.class), attr -> {
        attr.put(ArmorItem.Type.BOOTS, 5);
        attr.put(ArmorItem.Type.LEGGINGS, 8);
        attr.put(ArmorItem.Type.HELMET, 5);
        attr.put(ArmorItem.Type.CHESTPLATE, 10);
        attr.put(ArmorItem.Type.BODY, 13);
    }), 32, 5f, 0.1f, () -> ModItems.ENPOWERED_GOLD_INGOT.get());


    private static Holder<ArmorMaterial> reg(String name, EnumMap<ArmorItem.Type, Integer> typeProtection,
                                                  int enchantability, float toughness, float knockbackResistance,
                                                  Supplier<Item> ingredientItem) {
        ResourceLocation location = ResourceLocation.fromNamespaceAndPath(LMod.MOD_ID, name);
        Holder<SoundEvent> equipSound = SoundEvents.ARMOR_EQUIP_NETHERITE;
        Supplier<Ingredient> ingredient = () -> Ingredient.of(ingredientItem.get());
        List<ArmorMaterial.Layer> layers = List.of(new ArmorMaterial.Layer(location));

        EnumMap<ArmorItem.Type, Integer> typeMap = new EnumMap<>(ArmorItem.Type.class);
        for (ArmorItem.Type type : ArmorItem.Type.values()) {
            typeMap.put(type, typeProtection.get(type));
        }

        return Registry.registerForHolder(BuiltInRegistries.ARMOR_MATERIAL, location,
                new ArmorMaterial(typeProtection, enchantability, equipSound, ingredient, layers, toughness, knockbackResistance));
    }
}
