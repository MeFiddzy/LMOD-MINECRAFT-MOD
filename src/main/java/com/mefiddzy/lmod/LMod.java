package com.mefiddzy.lmod;

import com.mefiddzy.lmod.block.ModBlocks;
import com.mefiddzy.lmod.effect.ModEffects;
import com.mefiddzy.lmod.item.ModCreaiveModTabs;
import com.mefiddzy.lmod.item.ModItems;
import com.mefiddzy.lmod.potion.ModPotions;
import com.mefiddzy.lmod.sounds.ModSounds;
import org.slf4j.Logger;

import com.mojang.logging.LogUtils;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.block.Blocks;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;
import net.neoforged.neoforge.event.server.ServerStartingEvent;

// The value here should match an entry in the META-INF/neoforge.mods.toml file
@Mod(LMod.MOD_ID)
public class LMod
{
    // Define mod id in a common place for everything to reference
    public static final String MOD_ID = "lmod";
    // Directly reference a slf4j logger
    private static final Logger LOGGER = LogUtils.getLogger();

    // The constructor for the mod class is the first code that is run when your mod is loaded.
    // FML will recognize some parameter types like IEventBus or ModContainer and pass them in automatically.
    public LMod(IEventBus modEventBus, ModContainer modContainer) {
        modEventBus.addListener(this::commonSetup);


        NeoForge.EVENT_BUS.register(this);

        ModItems.register(modEventBus);
        ModBlocks.reg(modEventBus);
        ModCreaiveModTabs.reg(modEventBus);
        ModSounds.reg(modEventBus);
        ModEffects.reg(modEventBus);
        ModPotions.reg(modEventBus);

        modEventBus.addListener(this::addCreative);

        modContainer.registerConfig(ModConfig.Type.COMMON, Config.SPEC);
    }


    private void commonSetup(final FMLCommonSetupEvent event)
    {
        // Some common setup code
        LOGGER.info("HELLO FROM COMMON SETUP");

        if (Config.logDirtBlock)
            LOGGER.info("DIRT BLOCK >> {}", BuiltInRegistries.BLOCK.getKey(Blocks.DIRT));

        LOGGER.info(Config.magicNumberIntroduction + Config.magicNumber);

        Config.items.forEach((item) -> LOGGER.info("ITEM >> {}", item.toString()));
    }

    // Add the example block item to the building blocks tab
    private void addCreative(BuildCreativeModeTabContentsEvent event)
    {
    }

    // You can use SubscribeEvent and let the Event Bus discover methods to call
    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event)
    {
    }
}
