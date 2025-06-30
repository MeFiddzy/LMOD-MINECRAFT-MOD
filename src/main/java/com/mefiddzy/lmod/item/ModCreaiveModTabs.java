package com.mefiddzy.lmod.item;

import com.mefiddzy.lmod.LMod;
import com.mefiddzy.lmod.block.ModBlocks;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class ModCreaiveModTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MOD_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, LMod.MOD_ID);

    public static final Supplier<CreativeModeTab> TECHICAL_ITEMS = CREATIVE_MOD_TABS.register("lmod", () -> CreativeModeTab.builder()
            .icon(() -> new ItemStack(ModItems.ENPOWERED_GOLD_INGOT.get()))
            .title(Component.translatable("creativetab.lmod"))
            .displayItems((itemDisplayParameters, output) -> {
                output.accept(ModItems.ENPOWERED_GOLD_INGOT);
                output.accept(ModItems.ENPOWERMENT_POWDER);
                output.accept(ModItems.TOUGH_POWDER);
                output.accept(ModItems.DUST_EMPOWERER);
                output.accept(ModItems.DURACELL);
                output.accept(ModItems.ENPOWERED_GOLD_SWORD);
                output.accept(ModItems.ENPOWERED_GOLD_AXE);
                output.accept(ModItems.ENPOWERED_GOLD_PICKAXE);
                output.accept(ModItems.ENPOWERED_GOLD_SHOVEL);
                output.accept(ModItems.ENPOWERED_GOLD_HOE);
                output.accept(ModItems.ENPOWERED_GOLD_STICK);
                output.accept(ModItems.ENPOWERED_GOLD_HELMET);
                output.accept(ModItems.ENPOWERED_GOLD_BOOTS);
                output.accept(ModItems.ENPOWERED_GOLD_CHESTPLATE);
                output.accept(ModItems.ENPOWERED_GOLD_LEGGINGS);
                output.accept(ModItems.PINK_DIAMOND);
                output.accept(ModItems.KILLSTREAK_SWORD);
                output.accept(ModItems.ENPOWERED_GOLD_PLATE);
                output.accept(ModItems.BATTERY_RECEIVER);
                output.accept(ModItems.ENPOWERED_GOLD_HEART);
                output.accept(ModItems.AN_ENIGMATIC_ENCOUNTER_MUSIC_DISC);

                output.accept(ModBlocks.HARD_STONE);
                output.accept(ModBlocks.DURACELL_BATTERY_PACK);
                output.accept(ModBlocks.PINK_DIAMOND_BLOCK);
                output.accept(ModBlocks.PINK_DIAMOND_ORE);
                output.accept(ModBlocks.DEEPSLATE_PINK_DIAMOND_ORE);
                output.accept(ModBlocks.ENPOWERMENT_POWDER_BLOCK);
                output.accept(ModBlocks.PERMA_EMPOWERER);
                output.accept(ModBlocks.ENPOWERED_GOLD_BLOCK);
                output.accept(ModBlocks.BATTERY_ENCASER);
                output.accept(ModBlocks.PLATE_APPLIER);
            })
            .build());

    public static void reg(IEventBus eventBus){
        CREATIVE_MOD_TABS.register(eventBus);
    }
}
