package com.mefiddzy.lmod.events;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.mefiddzy.lmod.LMod;
import com.mefiddzy.lmod.block.ModBlocks;
import com.mefiddzy.lmod.effect.ModMobEffects;
import com.mefiddzy.lmod.enchantment.ModEnchantments;
import com.mefiddzy.lmod.item.ModItems;
import com.mefiddzy.lmod.potion.ModPotions;
import com.mefiddzy.lmod.util.ModTags;
import com.mefiddzy.lmod.util.component.ModDataComp;
import com.mefiddzy.lmod.util.enums.KillstreakPhases;
import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
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
import net.neoforged.neoforge.client.event.ClientChatReceivedEvent;
import net.neoforged.neoforge.common.Tags;
import net.neoforged.neoforge.event.ItemAttributeModifierEvent;
import net.neoforged.neoforge.event.brewing.RegisterBrewingRecipesEvent;
import net.neoforged.neoforge.event.entity.living.LivingDamageEvent;
import net.neoforged.neoforge.event.entity.living.LivingDeathEvent;
import net.neoforged.neoforge.event.level.BlockEvent;
import net.neoforged.neoforge.event.level.BlockEvent.EntityPlaceEvent;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Map;

@EventBusSubscriber(modid = LMod.MOD_ID, bus = EventBusSubscriber.Bus.GAME)
public class ModEvents {



    @SubscribeEvent
    public static void livingDmg(LivingDamageEvent.Pre e) {
        if (e.getEntity() instanceof Sheep) {
            if (e.getSource().getDirectEntity() instanceof Player pl) {
                if (pl.getMainHandItem().getItem() == Items.END_ROD) {
                    pl.sendSystemMessage(Component.literal("EWWWWWWW! You just hit a sheep with an end rod!!!"));
                    pl.sendSystemMessage(Component.literal("You will feel my wrath"));
                    pl.addEffect(new MobEffectInstance(MobEffects.CONFUSION, 600 * pl.getMainHandItem().getCount(), 2 * pl.getMainHandItem().getCount()));
                    pl.addEffect(new MobEffectInstance(MobEffects.POISON, 600 * pl.getMainHandItem().getCount(), 2 * pl.getMainHandItem().getCount()));
                    pl.addEffect(new MobEffectInstance(MobEffects.DARKNESS, 600 * pl.getMainHandItem().getCount(), 2 * pl.getMainHandItem().getCount()));
                    pl.addEffect(new MobEffectInstance(MobEffects.WEAKNESS, 600 * pl.getMainHandItem().getCount(), pl.getMainHandItem().getCount() - 1));
                    if (!pl.isCreative())
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
    public static void killstreakSwordFunc(LivingDeathEvent e) {
        Entity victim = e.getEntity();
        if (e.getSource().getEntity() instanceof Player killer) {
            ItemStack weapon = killer.getMainHandItem();
            if (weapon.getItem() == ModItems.KILLSTREAK_SWORD.get()) {
                int data;
                if (weapon.get(ModDataComp.KILLS_WITH_ITEM) == null) {
                    data = 0;
                }
                else {
                    data = weapon.get(ModDataComp.KILLS_WITH_ITEM);
                }
                weapon.set(ModDataComp.KILLS_WITH_ITEM, data + 1);


            }
        }
        if (victim instanceof Player plVictim) {
            int invSize = plVictim.getInventory().getContainerSize();
            for (int i = 0; i < invSize; i++) {
                ItemStack cur = plVictim.getInventory().getItem(i);
                if (cur.getItem() == ModItems.KILLSTREAK_SWORD.get()) {
                    cur.set(ModDataComp.KILLS_WITH_ITEM, 0);
                }
            }
        }
    }

    @SubscribeEvent
    public static void potionRez(LivingDamageEvent.Pre e) {
        if (e.getEntity().hasEffect(ModMobEffects.POTION_REZ_EFFECT)) {
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
                    pl.heal(0.1f * enchLv * e.getNewDamage());
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

    @SubscribeEvent
    public static void attrModKillstreak(ItemAttributeModifierEvent e) {
        ItemStack stack = e.getItemStack();

        if (stack.getItem() != ModItems.KILLSTREAK_SWORD.get())
            return;

        int kills = stack.getOrDefault(ModDataComp.KILLS_WITH_ITEM, 0);
        KillstreakPhases curPhase = KillstreakPhases.getType(kills);

        e.replaceModifier(Attributes.ATTACK_DAMAGE, new AttributeModifier(ResourceLocation.fromNamespaceAndPath(LMod.MOD_ID, "killstreak_mod"), curPhase.getAttackDmg(), AttributeModifier.Operation.ADD_VALUE), EquipmentSlotGroup.MAINHAND);
    }

    public static String getPlayerName(String uuidString) throws IOException {
        String urlString = "https://api.mojang.com/user/profiles/" + uuidString.replace("-", "") + "/names";
        URL url = new URL(urlString);

        try (java.util.Scanner s = new java.util.Scanner(url.openStream()).useDelimiter("\\A")) {
            String jsonString = s.hasNext() ? s.next() : "";

            Gson gson = new Gson();
            JsonArray nameHistory = gson.fromJson(jsonString, JsonArray.class);

            if (nameHistory != null && nameHistory.size() > 0) {
                JsonObject lastNameEntry = nameHistory.get(nameHistory.size() - 1).getAsJsonObject();
                return lastNameEntry.get("name").getAsString();
            } else {
                return null;
            }
        }
    }
}

