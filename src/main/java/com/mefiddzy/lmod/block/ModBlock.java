package com.mefiddzy.lmod.block;

import com.mefiddzy.lmod.LMod;
import com.mefiddzy.lmod.block.custom.PermanentEmpowererBlock;
import com.mefiddzy.lmod.item.ModItems;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraft.world.level.block.state.properties.WoodType;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class ModBlock {
    public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(LMod.MOD_ID);

    public static final DeferredBlock<Block> HARD_STONE = regB("hard_stone",
            () -> new Block(BlockBehaviour.Properties.of()
                    .strength(15f)
                    .requiresCorrectToolForDrops()
                    .sound(SoundType.DEEPSLATE)));

    public static final DeferredBlock<Block> POLISHED_HARD_STONE = regB("polished_hard_stone",
            () -> new Block(BlockBehaviour.Properties.of()
                    .strength(12.5f)
                    .requiresCorrectToolForDrops()
                    .sound(SoundType.DEEPSLATE)));

    public static final DeferredBlock<Block> PERMA_EMPOWERER = regB("perma_empowerer",
            () -> new PermanentEmpowererBlock(BlockBehaviour.Properties.of()
                    .strength(100f)
                    .noLootTable()
                    .sound(SoundType.AMETHYST)));


    private static <T extends Block> DeferredBlock<T> regB(String name, Supplier<T> block) {
        DeferredBlock<T> toReturn = BLOCKS.register(name, block);
        regBI(name, toReturn);
        return toReturn;
    }

    private static <T extends Block> void  regBI(String name, DeferredBlock<T> block) {
        ModItems.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties()));
    }

    public static void reg(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }
}
