package com.mefiddzy.lmod.item.custom;

import com.mefiddzy.lmod.effect.ModMobEffects;
import com.mefiddzy.lmod.util.component.ModDataComp;
import com.mefiddzy.lmod.util.enums.KillstreakPhases;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.level.Level;

import java.util.List;

public class KillstreakSwordItem extends SwordItem {

    public KillstreakSwordItem(Tier tier, Properties properties) {
        super(tier, properties);
    }
    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltipComponents, TooltipFlag tooltipFlag) {
        int k = 0;
        if (stack.get(ModDataComp.KILLS_WITH_ITEM) != null) {
            k = stack.get(ModDataComp.KILLS_WITH_ITEM);
        }
        tooltipComponents.add(Component.literal("Kills: " + Math.min(k, KillstreakPhases.killstreakPhasesEnd)));

        if (k > KillstreakPhases.killstreakPhasesEnd) {
            tooltipComponents.add(Component.literal("§4§lOVERKILLS: " + (k - KillstreakPhases.killstreakPhasesEnd)));
        }

        int kills = stack.getOrDefault(ModDataComp.KILLS_WITH_ITEM, 0);
        KillstreakPhases curPhase = KillstreakPhases.getType(kills);

        tooltipComponents.add(Component.translatable("tooltip.lmod.item.killstreak.phase" + curPhase.getPhaseNumber()));


        super.appendHoverText(stack, context, tooltipComponents, tooltipFlag);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand usedHand) {

        ItemStack weapon = player.getWeaponItem();
        KillstreakPhases phase = KillstreakPhases.getType(weapon.getOrDefault(ModDataComp.KILLS_WITH_ITEM, 0));
        int phaseNr = phase.getPhaseNumber();

        if (phaseNr == 10) {
            player.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 100, 1));
            player.addEffect(new MobEffectInstance(ModMobEffects.POTION_REZ_EFFECT, 100));
        }

        return super.use(level, player, usedHand);
    }

    @Override
    public boolean isEnchantable(ItemStack stack) {
        return false;
    }
}
