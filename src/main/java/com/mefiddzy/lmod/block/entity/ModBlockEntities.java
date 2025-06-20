package com.mefiddzy.lmod.block.entity;

import com.mefiddzy.lmod.LMod;
import com.mefiddzy.lmod.block.ModBlocks;
import com.mefiddzy.lmod.screen.custom.PlateApplierMenu;
import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;
import org.jetbrains.annotations.Nullable;

import java.util.function.Supplier;

public class ModBlockEntities extends BlockEntity implements MenuProvider {
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES = DeferredRegister.create(BuiltInRegistries.BLOCK_ENTITY_TYPE, LMod.MOD_ID);

    public static final Supplier<BlockEntityType<PlateApplierBlockEntity>> PLATE_APPLIER_BE = BLOCK_ENTITIES.register("plate_applier_be", () -> BlockEntityType.Builder.of(
            PlateApplierBlockEntity::new, ModBlocks.PLATE_APPLIER.get()).build(null));

    public ModBlockEntities(BlockEntityType<?> type, BlockPos pos, BlockState blockState) {
        super(type, pos, blockState);
    }

    public static void reg(IEventBus bus) {
        BLOCK_ENTITIES.register(bus);
    }

    @Override
    public Component getDisplayName() {
        return Component.translatable("lmod.block.plate_applier");
    }

    @Override
    public @Nullable AbstractContainerMenu createMenu(int containerId, Inventory playerInventory, Player player) {
        return new PlateApplierMenu(containerId, playerInventory, this);
    }
}
