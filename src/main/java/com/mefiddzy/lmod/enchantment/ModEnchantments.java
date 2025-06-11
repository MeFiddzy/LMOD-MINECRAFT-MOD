package com.mefiddzy.lmod.enchantment;

import com.mefiddzy.lmod.LMod;
import com.mefiddzy.lmod.enchantment.custom.DiscHunterEnch;
import com.mefiddzy.lmod.enchantment.custom.GuardianAngelEnch;
import com.mefiddzy.lmod.enchantment.custom.LifeLeechEnch;
import com.mefiddzy.lmod.enchantment.custom.OreCollectorEnch;
import net.minecraft.core.HolderSet;
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
import net.minecraft.world.item.enchantment.Enchantments;

public class ModEnchantments {
    public static final ResourceKey<Enchantment> GUARDIAN_ANGEL = ResourceKey.create(Registries.ENCHANTMENT, ResourceLocation.fromNamespaceAndPath(LMod.MOD_ID, "guardian_angel"));
    public static final ResourceKey<Enchantment> DISC_HUNTER = ResourceKey.create(Registries.ENCHANTMENT, ResourceLocation.fromNamespaceAndPath(LMod.MOD_ID, "disc_hunter"));
    public static final ResourceKey<Enchantment> LIFE_LEECH = ResourceKey.create(Registries.ENCHANTMENT, ResourceLocation.fromNamespaceAndPath(LMod.MOD_ID, "life_leech"));
    public static final ResourceKey<Enchantment> ORE_COLLECTOR = ResourceKey.create(Registries.ENCHANTMENT, ResourceLocation.fromNamespaceAndPath(LMod.MOD_ID, "ore_collector"));

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

        reg(c, DISC_HUNTER, Enchantment.enchantment(Enchantment.definition(
                                it.getOrThrow(ItemTags.WEAPON_ENCHANTABLE),
                                7,
                                1,
                                Enchantment.dynamicCost(15, 17),
                                Enchantment.dynamicCost(29, 30),
                                4,
                                EquipmentSlotGroup.MAINHAND
                        ))
                        .exclusiveWith(HolderSet.direct(ench.getOrThrow(Enchantments.LOOTING)))
                        .withEffect(EnchantmentEffectComponents.POST_ATTACK, EnchantmentTarget.ATTACKER,
                                EnchantmentTarget.VICTIM, new DiscHunterEnch())
        );

        reg(c, LIFE_LEECH, Enchantment.enchantment(Enchantment.definition(
                                it.getOrThrow(ItemTags.WEAPON_ENCHANTABLE),
                                3,
                                5,
                                Enchantment.dynamicCost(20, 27),
                                Enchantment.dynamicCost(29, 30),
                                4,
                                EquipmentSlotGroup.MAINHAND
                        ))
                .exclusiveWith(ench.getOrThrow(EnchantmentTags.DAMAGE_EXCLUSIVE))
                .exclusiveWith(HolderSet.direct(ench.getOrThrow(GUARDIAN_ANGEL)))
                .withEffect(EnchantmentEffectComponents.POST_ATTACK, EnchantmentTarget.ATTACKER,
                                EnchantmentTarget.VICTIM, new LifeLeechEnch())
        );

        reg(c, ORE_COLLECTOR, Enchantment.enchantment(Enchantment.definition(
                                it.getOrThrow(ItemTags.PICKAXES),
                                1,
                                2,
                                Enchantment.dynamicCost(10, 27),
                                Enchantment.dynamicCost(24, 27),
                                5,
                                EquipmentSlotGroup.MAINHAND
                        ))
                        .exclusiveWith(HolderSet.direct(ench.getOrThrow(Enchantments.FORTUNE)))
                        .withEffect(EnchantmentEffectComponents.POST_ATTACK, EnchantmentTarget.ATTACKER,
                                EnchantmentTarget.VICTIM, new OreCollectorEnch())
        );
    }

    public static void reg(BootstrapContext<Enchantment> registry, ResourceKey<Enchantment> key, Enchantment.Builder b) {
        registry.register(key, b.build(key.location()));
    }

}
