package com.mefiddzy.lmod.events;

import com.mefiddzy.lmod.LMod;
import com.mefiddzy.lmod.block.ModBlocks;
import com.mefiddzy.lmod.effect.ModEffects;
import com.mefiddzy.lmod.enchantment.ModEnchantments;
import com.mefiddzy.lmod.item.ModItems;
import com.mefiddzy.lmod.potion.ModPotions;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.network.chat.Component;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LightningBolt;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.animal.Sheep;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.alchemy.PotionBrewing;
import net.minecraft.world.item.alchemy.Potions;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.common.Tags;
import net.neoforged.neoforge.event.brewing.RegisterBrewingRecipesEvent;
import net.neoforged.neoforge.event.entity.living.LivingDamageEvent;
import net.neoforged.neoforge.event.level.BlockEvent.EntityPlaceEvent;

@EventBusSubscriber(modid = LMod.MOD_ID, bus = EventBusSubscriber.Bus.GAME)
public class ModEvents {



    @SubscribeEvent
    public static void livingDmg(LivingDamageEvent.Pre e) {
        if (e.getEntity() instanceof Sheep s) {
            if (e.getSource().getDirectEntity() instanceof Player pl) {
                if (pl.getMainHandItem().getItem() == Items.END_ROD) {
                    pl.sendSystemMessage(Component.literal("EWWWWWWW! You just hit a sheep with an end rod!!!"));
                    pl.sendSystemMessage(Component.literal("You will feel my wrath"));
                    pl.addEffect(new MobEffectInstance(MobEffects.CONFUSION, 600 * pl.getMainHandItem().getCount(), 2 * pl.getMainHandItem().getCount()));
                    pl.addEffect(new MobEffectInstance(MobEffects.POISON, 600 * pl.getMainHandItem().getCount(), 2 * pl.getMainHandItem().getCount()));
                    pl.addEffect(new MobEffectInstance(MobEffects.DARKNESS, 600 * pl.getMainHandItem().getCount(), 2 * pl.getMainHandItem().getCount()));
                    pl.addEffect(new MobEffectInstance(MobEffects.WEAKNESS, 600 * pl.getMainHandItem().getCount(), pl.getMainHandItem().getCount() - 1));
                    pl.getMainHandItem().shrink(pl.getMainHandItem().getCount());
                }
            }
        }
    }
    /*
    @SubscribeEvent
    public static void guardianAngelDMGPrev(LivingDamageEvent.Pre e) {
        if (e.getSource().getDirectEntity() instanceof Player p) {
            int el = p.getMainHandItem().getEnchantmentLevel((Holder<Enchantment>) ModEnchantments.GUARDIAN_ANGEL);
            if (el > 0) {
                e.setNewDamage(0.0f);
                LivingEntity living = e.getEntity();

                living.addEffect(new MobEffectInstance(MobEffects.REGENERATION, 100, 0));
                if (el > 1) {
                    living.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 100, 1));
                }
                if (el == 3) {
                    living.addEffect(new MobEffectInstance(ModEffects.POTION_REZ_EFFECT, 100));
                }
            }
        }
    } */


    @SubscribeEvent
    public static void potionRez(LivingDamageEvent.Pre e) {
        if (e.getEntity().hasEffect(ModEffects.POTION_REZ_EFFECT)) {
            if (e.getSource().is(Tags.DamageTypes.IS_MAGIC)) {
                e.setNewDamage(0.0f);
            }
        }
    }



    @SubscribeEvent
    public static void deposess(EntityPlaceEvent e) {
        if (e.getPlacedBlock().getBlock() == Blocks.TORCH) {
            BlockPos pos = e.getPos();
            BlockPos below = pos.below();
            BlockPos below2 = below.below();

            if (e.getLevel().getBlockState(below).is(Blocks.OBSIDIAN) &&
                    e.getLevel().getBlockState(below2).is(ModBlocks.PERMA_EMPOWERER)) {

                Level level = (Level) e.getLevel();

                level.setBlock(below, Blocks.AIR.defaultBlockState(), 3);
                level.setBlock(pos, Blocks.AIR.defaultBlockState(), 3);
                level.setBlock(below2, Blocks.JUKEBOX.defaultBlockState(), 3);

                if (!level.isClientSide) {
                    LightningBolt lightning = EntityType.LIGHTNING_BOLT.create(level);
                    if (lightning != null) {
                        lightning.moveTo(pos.getX() + 0.5, pos.getY(), pos.getZ() + 0.5);
                        level.addFreshEntity(lightning);
                    }
                }
            }
        }
    }

    @SubscribeEvent
    public static void onBrewRcpReg(RegisterBrewingRecipesEvent e) {
        PotionBrewing.Builder b = e.getBuilder();

        b.addMix(Potions.MUNDANE, ModItems.ENPOWERED_GOLD_INGOT.get(), ModPotions.POTION_REZ_POTION);
    }
}

