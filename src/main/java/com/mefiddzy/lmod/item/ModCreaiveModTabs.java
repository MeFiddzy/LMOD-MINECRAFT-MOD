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

    public static final Supplier<CreativeModeTab> TECHICAL_ITEMS = CREATIVE_MOD_TABS.register("technical_items", () -> CreativeModeTab.builder()
            .icon(() -> new ItemStack(ModItems.ENPOWERED_GOLD_INGOT.get()))
            .title(Component.translatable("creativetab.lmod.technical_items"))
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

                output.accept(ModBlocks.HARD_STONE);
                output.accept(ModBlocks.PERMA_EMPOWERER);
                output.accept(ModBlocks.ENPOWERED_GOLD_BLOCK);
            })
            .build());

    public static final Supplier<CreativeModeTab> BUILDING_BLOCKS = CREATIVE_MOD_TABS.register("building_blocks", () -> CreativeModeTab.builder()
            .icon(() -> new ItemStack(ModBlocks.POLISHED_HARD_STONE.get()))
            .title(Component.translatable("creativetab.lmod.building_blocks"))
            .displayItems((itemDisplayParameters, output) -> {
                output.accept(ModBlocks.POLISHED_HARD_STONE.get());
                output.accept(ModItems.AN_ENIGMATIC_ENCOUNTER_MUSIC_DISC);
            })
            .build());

    public static void reg(IEventBus eventBus){
        CREATIVE_MOD_TABS.register(eventBus);
    }
}
