package com.mefiddzy.lmod.enchantment;

import com.mefiddzy.lmod.LMod;
import com.mefiddzy.lmod.enchantment.custom.GuardianAngelEnch;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.EnchantmentTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentEffectComponents;
import net.minecraft.world.item.enchantment.EnchantmentTarget;

public class ModEnchantments {
    public static final ResourceKey<Enchantment> GUARDIAN_ANGEL = ResourceKey.create(Registries.ENCHANTMENT, ResourceLocation.fromNamespaceAndPath(LMod.MOD_ID, "guardian_angel"));

    public static void bs(BootstrapContext<Enchantment> c) {
        var ench = c.lookup(Registries.ENCHANTMENT);
        var it = c.lookup(Registries.ITEM);

        reg(c, GUARDIAN_ANGEL, Enchantment.enchantment(Enchantment.definition(
                it.getOrThrow(ItemTags.WEAPON_ENCHANTABLE),
                5,
                3,
                Enchantment.dynamicCost(5, 7),
                Enchantment.dynamicCost(25, 7),
                2,
                EquipmentSlotGroup.MAINHAND
                ))
                .exclusiveWith(ench.getOrThrow(EnchantmentTags.DAMAGE_EXCLUSIVE))
                .withEffect(EnchantmentEffectComponents.POST_ATTACK, EnchantmentTarget.ATTACKER,
                        EnchantmentTarget.VICTIM, new GuardianAngelEnch())
        );
    }

    public static void reg(BootstrapContext<Enchantment> registry, ResourceKey<Enchantment> key, Enchantment.Builder b) {
        registry.register(key, b.build(key.location()));
    }

}
