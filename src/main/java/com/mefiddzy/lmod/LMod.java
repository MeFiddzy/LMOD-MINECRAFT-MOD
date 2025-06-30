package com.mefiddzy.lmod;

import com.mefiddzy.lmod.block.ModBlocks;
import com.mefiddzy.lmod.block.entity.ModBlockEntities;
import com.mefiddzy.lmod.effect.ModMobEffects;
import com.mefiddzy.lmod.enchantment.ModEnchantmentEffects;
import com.mefiddzy.lmod.entity.ModEntities;
import com.mefiddzy.lmod.entity.client.GigaRoachRenderer;
import com.mefiddzy.lmod.item.ModCreaiveModTabs;
import com.mefiddzy.lmod.item.ModItems;
import com.mefiddzy.lmod.potion.ModPotions;
import com.mefiddzy.lmod.recipe.ModRecipes;
import com.mefiddzy.lmod.screen.ModMenuTypes;
import com.mefiddzy.lmod.screen.custom.battery_encaser.BatteryEncaserMenu;
import com.mefiddzy.lmod.screen.custom.battery_encaser.BatteryEncaserScreen;
import com.mefiddzy.lmod.screen.custom.plate_applier.PlateApplierScreen;
import com.mefiddzy.lmod.sounds.ModSounds;
import com.mefiddzy.lmod.util.ModItemProp;
import com.mefiddzy.lmod.util.component.ModDataComp;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraft.world.item.CreativeModeTabs;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.neoforge.client.event.RegisterMenuScreensEvent;
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
        ModMobEffects.reg(modEventBus);
        ModPotions.reg(modEventBus);
        ModEnchantmentEffects.reg(modEventBus);
        ModEntities.reg(modEventBus);
        ModDataComp.reg(modEventBus);
        ModMenuTypes.reg(modEventBus);
        ModBlockEntities.reg(modEventBus);
        ModRecipes.reg(modEventBus);
        LMod.LOGGER.info("Registering Deferred Registers!");

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
        if (event.getTabKey() == CreativeModeTabs.BUILDING_BLOCKS) {
            event.accept(ModBlocks.POLISHED_HARD_STONE);
        }
    }

    // You can use SubscribeEvent and let the Event Bus discover methods to call
    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event)
    {
    }

    @EventBusSubscriber(modid = MOD_ID, bus = EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ModClientModEvents {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent e) {
            ModItemProp.addCustomItemP();

            EntityRenderers.register(ModEntities.GIGA_ROACH.get(), GigaRoachRenderer::new);
        }

        @SubscribeEvent
        public static void regScreens(RegisterMenuScreensEvent e) {
            e.register(ModMenuTypes.PLATE_APPLIER_MENU.get(), PlateApplierScreen::new);
            e.register(ModMenuTypes.BATTERY_ENCASER_MENU.get(), BatteryEncaserScreen::new);
        }

    }
}
