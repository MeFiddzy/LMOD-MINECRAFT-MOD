package com.mefiddzy.lmod.block;

import com.mefiddzy.lmod.LMod;
import com.mefiddzy.lmod.block.custom.BatteryBlock;
import com.mefiddzy.lmod.block.custom.BatteryEncaserBlock;
import com.mefiddzy.lmod.block.custom.PermanentEmpowererBlock;
import com.mefiddzy.lmod.block.custom.PlateApplierBlock;
import com.mefiddzy.lmod.item.ModItems;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class ModBlocks {
    public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(LMod.MOD_ID);

    public static final DeferredBlock<Block> HARD_STONE = regB("hard_stone",
            () -> new Block(BlockBehaviour.Properties.of()
                    .strength(14f)
                    .requiresCorrectToolForDrops()
                    .sound(SoundType.DEEPSLATE)));

    public static final DeferredBlock<Block> POLISHED_HARD_STONE = regB("polished_hard_stone",
            () -> new Block(BlockBehaviour.Properties.of()
                    .strength(12.5f)
                    .requiresCorrectToolForDrops()
                    .sound(SoundType.DEEPSLATE)));

    public static final DeferredBlock<Block> ENPOWERED_GOLD_BLOCK = regB("enpowered_gold_block",
            () -> new Block(BlockBehaviour.Properties.of()
                    .strength(20f)
                    .requiresCorrectToolForDrops()
                    .sound(SoundType.NETHERITE_BLOCK)));

    public static final DeferredBlock<Block> ENPOWERMENT_POWDER_BLOCK = regB("enpowered_powder_block",
            () -> new Block(BlockBehaviour.Properties.of()
                    .strength(2f)
                    .requiresCorrectToolForDrops()
                    .sound(SoundType.GRASS)));

    public static final DeferredBlock<Block> PERMA_EMPOWERER = regB("perma_empowerer",
            () -> new PermanentEmpowererBlock(BlockBehaviour.Properties.of()
                    .strength(100f)
                    .noLootTable()
                    .sound(SoundType.AMETHYST)));

    public static final DeferredBlock<Block> PINK_DIAMOND_BLOCK = regB("pink_diamond_block",
            () -> new Block(BlockBehaviour.Properties.of()
                    .strength(5f)
                    .requiresCorrectToolForDrops()
                    .sound(SoundType.METAL)));

    public static final DeferredBlock<Block> PINK_DIAMOND_ORE = regB("pink_diamond_ore",
            () -> new DropExperienceBlock(UniformInt.of(3, 7),
                    BlockBehaviour.Properties.of()
                    .strength(3f)
                    .requiresCorrectToolForDrops()
                    .sound(SoundType.STONE)));

    public static final DeferredBlock<Block> DEEPSLATE_PINK_DIAMOND_ORE = regB("deepslate_pink_diamond_ore",
            () -> new DropExperienceBlock(UniformInt.of(3, 7), BlockBehaviour.Properties.of()
                    .strength(4.5f)
                    .requiresCorrectToolForDrops()
                    .sound(SoundType.DEEPSLATE)));

    public static final DeferredBlock<Block> PLATE_APPLIER = regB("plate_applier",
            () -> new PlateApplierBlock(BlockBehaviour.Properties.of()
                    .strength(4.5f)
                    .requiresCorrectToolForDrops()
                    .noOcclusion()
                    .sound(SoundType.STONE)));

    public static final DeferredBlock<Block> DURACELL_BATTERY_PACK = regB("duracell_battery_pack",
            () -> new BatteryBlock(BlockBehaviour.Properties.of()
                    .strength(3f)
                    .sound(SoundType.METAL),
                    4));

    public static final DeferredBlock<Block> BATTERY_ENCASER = regB("battery_encaser",
            () -> new BatteryEncaserBlock(BlockBehaviour.Properties.of()
                    .strength(4.5f)
                    .noOcclusion()
                    .requiresCorrectToolForDrops()
                    .sound(SoundType.METAL)));


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
