package com.mefiddzy.lmod.commands.custom;

import com.mefiddzy.lmod.util.classVariables.Interval;
import com.mefiddzy.lmod.util.enums.KillstreakPhases;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.commands.arguments.EntityArgument;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.network.chat.Component;
import net.minecraft.ChatFormatting;
import net.minecraft.world.item.ItemStack;
import com.mefiddzy.lmod.item.ModItems;
import com.mefiddzy.lmod.util.component.ModDataComp;

public class EditKillsKillstreakCommand {
    public EditKillsKillstreakCommand(CommandDispatcher<CommandSourceStack> dispatcher) {
        dispatcher.register(
                Commands.literal("lmod")
                        .then(Commands.literal("killstreak")
                                .then(Commands.argument("player", EntityArgument.player())
                                        .then(Commands.literal("set")
                                                .then(Commands.argument("kills", IntegerArgumentType.integer(0))
                                                        .executes(ctx -> {
                                                            ServerPlayer target = EntityArgument.getPlayer(ctx, "player");
                                                            int kills = IntegerArgumentType.getInteger(ctx, "kills");
                                                            return setKillstreakKills(ctx.getSource(), target, kills);
                                                        })
                                                )
                                        )
                                        .then(Commands.literal("add")
                                                .then(Commands.argument("kills", IntegerArgumentType.integer(0))
                                                        .executes(ctx -> {
                                                            ServerPlayer target = EntityArgument.getPlayer(ctx, "player");
                                                            int kills = IntegerArgumentType.getInteger(ctx, "kills");
                                                            return addKillstreakKills(ctx.getSource(), target, kills);
                                                        })
                                                )
                                        )
                                        .then(Commands.literal("remove")
                                                .then(Commands.argument("kills", IntegerArgumentType.integer(0))
                                                        .executes(ctx -> {
                                                            ServerPlayer target = EntityArgument.getPlayer(ctx, "player");
                                                            int kills = IntegerArgumentType.getInteger(ctx, "kills");
                                                            return removeKillstreakKills(ctx.getSource(), target, kills);
                                                        })
                                                )
                                        )
                                        .then(Commands.literal("nextPhase")
                                                .executes(ctx -> {
                                                    ServerPlayer target = EntityArgument.getPlayer(ctx, "player");
                                                    return nextPhase(ctx.getSource(), target);
                                                })
                                        )
                                        .then(Commands.literal("previousPhase")
                                                .executes(ctx -> {
                                                    ServerPlayer target = EntityArgument.getPlayer(ctx, "player");
                                                    return previousPhase(ctx.getSource(), target);
                                                })
                                        )
                                        .then(Commands.literal("selectPhaseByNumber")
                                                .then(Commands.argument("number", IntegerArgumentType.integer(1, KillstreakPhases.getAllPhases().length))
                                                        .executes(ctx -> {
                                                            ServerPlayer target = EntityArgument.getPlayer(ctx, "player");
                                                            int number = IntegerArgumentType.getInteger(ctx, "number");
                                                            return selectPhase(ctx.getSource(), target, number);
                                                        })
                                                )
                                        )

                                )
                        )
        );

    }

    private int setKillstreakKills(CommandSourceStack source, ServerPlayer target, int kills) {
        ItemStack held = target.getMainHandItem();

        if (!held.is(ModItems.KILLSTREAK_SWORD.get())) {
            source.sendFailure(Component.literal("Player is not holding a Killstreak Sword.")
                    .withStyle(ChatFormatting.RED));
            return 0;
        }

        held.set(ModDataComp.KILLS_WITH_ITEM, kills);

        source.sendSuccess(() -> Component.literal("Set " + kills + " kills on " + target.getName().getString() + "'s sword"),true);
        return 1;
    }

    private int addKillstreakKills(CommandSourceStack source, ServerPlayer target, int kills) {
        ItemStack held = target.getMainHandItem();

        if (!held.is(ModItems.KILLSTREAK_SWORD.get())) {
            source.sendFailure(Component.literal("Player is not holding a Killstreak Sword.")
                    .withStyle(ChatFormatting.RED));
            return 0;
        }


        held.set(ModDataComp.KILLS_WITH_ITEM, held.getOrDefault(ModDataComp.KILLS_WITH_ITEM, 0) + kills);

        source.sendSuccess(() -> Component.literal("Added " + kills + " kills to " + target.getName().getString() + "'s sword"),true);
        return 1;
    }

    private int removeKillstreakKills(CommandSourceStack source, ServerPlayer target, int kills) {
        ItemStack held = target.getMainHandItem();

        if (!held.is(ModItems.KILLSTREAK_SWORD.get())) {
            source.sendFailure(Component.literal("Player is not holding a Killstreak Sword.")
                    .withStyle(ChatFormatting.RED));
            return 0;
        }

        held.set(ModDataComp.KILLS_WITH_ITEM, held.getOrDefault(ModDataComp.KILLS_WITH_ITEM, 0) - kills);

        source.sendSuccess(() -> Component.literal("Removed " + kills + " kills from " + target.getName().getString() + "'s sword"),true);
        return 1;
    }

    private int nextPhase(CommandSourceStack source, ServerPlayer target) {
        ItemStack held = target.getMainHandItem();

        if (!held.is(ModItems.KILLSTREAK_SWORD.get())) {
            source.sendFailure(Component.literal("Player is not holding a Killstreak Sword.")
                    .withStyle(ChatFormatting.RED));
            return 0;
        }

        int currentKills = held.getOrDefault(ModDataComp.KILLS_WITH_ITEM, 0);
        KillstreakPhases currentPhase = KillstreakPhases.getType(currentKills);
        KillstreakPhases[] allPhases = KillstreakPhases.getAllPhases();

        if (currentPhase.getPhaseNumber() >= allPhases.length) {
            source.sendFailure(Component.literal("Player is already at the highest killstreak phase.")
                    .withStyle(ChatFormatting.RED));
            return 0;
        }

        KillstreakPhases nextPhase = allPhases[currentPhase.getPhaseNumber()];
        held.set(ModDataComp.KILLS_WITH_ITEM, nextPhase.getKills().getStartPoint());

        source.sendSuccess(() -> Component.literal(
                "Set " + target.getName().getString() + "'s sword to phase " + nextPhase.getPhaseNumber()
        ), true);
        return 1;
    }

    private int previousPhase(CommandSourceStack source, ServerPlayer target) {
        ItemStack held = target.getMainHandItem();

        if (!held.is(ModItems.KILLSTREAK_SWORD.get())) {
            source.sendFailure(Component.literal("Player is not holding a Killstreak Sword.")
                    .withStyle(ChatFormatting.RED));
            return 0;
        }

        int currentKills = held.getOrDefault(ModDataComp.KILLS_WITH_ITEM, 0);
        KillstreakPhases currentPhase = KillstreakPhases.getType(currentKills);
        KillstreakPhases[] allPhases = KillstreakPhases.getAllPhases();

        if (currentPhase.getPhaseNumber() - 2 < 0) {
            source.sendFailure(Component.literal("Player is already at the lowest killstreak phase.")
                    .withStyle(ChatFormatting.RED));
            return 0;
        }

        KillstreakPhases nextPhase = allPhases[currentPhase.getPhaseNumber() - 2];
        held.set(ModDataComp.KILLS_WITH_ITEM, nextPhase.getKills().getStartPoint());

        source.sendSuccess(() -> Component.literal(
                "Set " + target.getName().getString() + "'s sword to phase " + nextPhase.getPhaseNumber()
        ), true);
        return 1;
    }

    private int selectPhase(CommandSourceStack source, ServerPlayer target, int number) {
        ItemStack held = target.getMainHandItem();

        if (!held.is(ModItems.KILLSTREAK_SWORD.get())) {
            source.sendFailure(Component.literal("Player is not holding a Killstreak Sword.")
                    .withStyle(ChatFormatting.RED));
            return 0;
        }

        number--;

        int currentKills = held.getOrDefault(ModDataComp.KILLS_WITH_ITEM, 0);
        KillstreakPhases currentPhase = KillstreakPhases.getType(currentKills);
        KillstreakPhases[] allPhases = KillstreakPhases.getAllPhases();



        if (!(new Interval(0, allPhases.length).between(number))) {
            source.sendFailure(Component.literal("That killstreak phase is non existent")
                    .withStyle(ChatFormatting.RED));
            return 0;
        }

        held.set(ModDataComp.KILLS_WITH_ITEM, allPhases[number].getKills().getStartPoint());

        int finalNumber = number;
        source.sendSuccess(() -> Component.literal(
                "Set " + target.getName().getString() + "'s sword to phase " + allPhases[finalNumber].getPhaseNumber()
        ), true);
        return 1;
    }

}
