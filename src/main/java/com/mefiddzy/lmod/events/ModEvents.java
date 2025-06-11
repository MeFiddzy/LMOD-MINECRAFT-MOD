package com.mefiddzy.lmod.events;

import com.mefiddzy.lmod.LMod;
import com.mefiddzy.lmod.block.ModBlocks;
import com.mefiddzy.lmod.effect.ModEffects;
import com.mefiddzy.lmod.enchantment.ModEnchantments;
import com.mefiddzy.lmod.item.ModItems;
import com.mefiddzy.lmod.potion.ModPotions;
import com.mefiddzy.lmod.util.ModTags;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LightningBolt;
import net.minecraft.world.entity.animal.Sheep;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.alchemy.PotionBrewing;
import net.minecraft.world.item.alchemy.Potions;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.common.Tags;
import net.neoforged.neoforge.event.brewing.RegisterBrewingRecipesEvent;
import net.neoforged.neoforge.event.entity.living.LivingDamageEvent;
import net.neoforged.neoforge.event.level.BlockEvent;
import net.neoforged.neoforge.event.level.BlockEvent.EntityPlaceEvent;

import java.util.List;
import java.util.Map;

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

    @SubscribeEvent
    public static void oreCollectorFunc(BlockEvent.BreakEvent e) {
        boolean haveench = false;
        Player pl = e.getPlayer();
        ItemStack item = pl.getMainHandItem();
        if (e.getLevel().isClientSide())
            return;

        var server = pl.getServer();
        if (server == null)
            return;

        var registry = server.registryAccess().registryOrThrow(Registries.ENCHANTMENT);
        var holder = registry.getHolder(ModEnchantments.ORE_COLLECTOR).orElse(null);

        int enchLv = 0;

        if (holder != null) {
            enchLv = EnchantmentHelper.getItemEnchantmentLevel(holder, item);
            if (enchLv > 0) {
                haveench = true;
            }
        }

        BlockPos p = e.getPos();
        Block block = e.getLevel().getBlockState(p).getBlock();

        if ((block == Blocks.STONE || block == Blocks.DEEPSLATE) && haveench && !pl.isCreative()) {
            int rand = (int)(Math.random() * (26 - (enchLv - 1) * 6));
            if (enchLv >= 6)
                rand = 0;
            if (rand == 0) {
                final List<Item> itemChances = BuiltInRegistries.ITEM
                        .entrySet()
                        .stream()
                        .filter(entry -> entry.getValue().builtInRegistryHolder().is(ModTags.Items.POSSIBLE_FOR_ORE_COLLECTOR))
                        .map(Map.Entry::getValue)
                        .toList();

                if (!itemChances.isEmpty()) {
                    int itemIndex = (int)(Math.random() * itemChances.size());
                    Item selectedItem = itemChances.get(itemIndex);
                    ItemEntity ore = new ItemEntity((Level) e.getLevel(), p.getX(), p.getY(), p.getZ(), new ItemStack(selectedItem, 1));
                    e.getLevel().addFreshEntity(ore);
                }
            }
        }
    }


    @SubscribeEvent
    public static void potionRez(LivingDamageEvent.Pre e) {
        if (e.getEntity().hasEffect(ModEffects.POTION_REZ_EFFECT)) {
            if (e.getSource().is(Tags.DamageTypes.IS_MAGIC)) {
                e.setNewDamage(0.0f);
            }
        }
    }

    @SubscribeEvent
    public static void lifeLeachEnch(LivingDamageEvent.Pre e) {
        if (e.getSource().getDirectEntity() instanceof Player pl) {
            ItemStack item = pl.getMainHandItem();

            if (e.getEntity().level().isClientSide)
                return;

            var server = pl.getServer();
            if (server == null)
                return;

            var registry = server.registryAccess().registryOrThrow(Registries.ENCHANTMENT);
            var holder = registry.getHolder(ModEnchantments.LIFE_LEECH).orElse(null);

            if (holder != null) {
                int enchLv = EnchantmentHelper.getItemEnchantmentLevel(holder, item);
                if (enchLv > 0) {
                    item.hurtAndBreak(enchLv / 2, pl, EquipmentSlot.MAINHAND);
                    pl.heal((0.1f * enchLv) / 2 * e.getNewDamage());
                }
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

