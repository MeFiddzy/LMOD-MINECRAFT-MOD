package com.mefiddzy.lmod.commands;

import com.mefiddzy.lmod.LMod;
import com.mefiddzy.lmod.commands.custom.LmodCommandTree;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.RegisterCommandsEvent;

@EventBusSubscriber(modid = LMod.MOD_ID)
public class ModCommands {

    @SubscribeEvent
    public static void onRegisterCommands(RegisterCommandsEvent event) {
        new LmodCommandTree(event.getDispatcher());
    }
}

