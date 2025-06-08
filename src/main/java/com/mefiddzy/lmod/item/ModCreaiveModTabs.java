package com.mefiddzy.lmod.item;

import com.mefiddzy.lmod.LMod;
import com.mefiddzy.lmod.block.ModBlock;
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

                output.accept(ModBlock.HARD_STONE);
                output.accept(ModBlock.PERMA_EMPOWERER);
            })
            .build());

    public static final Supplier<CreativeModeTab> BUILDING_BLOCKS = CREATIVE_MOD_TABS.register("building_blocks", () -> CreativeModeTab.builder()
            .icon(() -> new ItemStack(ModBlock.POLISHED_HARD_STONE.get()))
            .title(Component.translatable("creativetab.lmod.building_blocks"))
            .displayItems((itemDisplayParameters, output) -> {
                output.accept(ModBlock.POLISHED_HARD_STONE);
            })
            .build());

    public static void reg(IEventBus eventBus){
        CREATIVE_MOD_TABS.register(eventBus);
    }
}
