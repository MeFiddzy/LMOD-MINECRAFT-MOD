package com.mefiddzy.lmod.screen;

import com.mefiddzy.lmod.LMod;
import com.mefiddzy.lmod.screen.custom.PlateApplierMenu;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.MenuType;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.common.extensions.IMenuTypeExtension;
import net.neoforged.neoforge.network.IContainerFactory;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModMenuTypes {
    public static final DeferredRegister<MenuType<?>> MENUS = DeferredRegister.create(Registries.MENU, LMod.MOD_ID);

    public static final DeferredHolder<MenuType<?>, MenuType<PlateApplierMenu>> PLATE_APPLIER_MENU =
            regMenType("plate_applier_menu", PlateApplierMenu::new);

    private static <T extends AbstractContainerMenu>DeferredHolder<MenuType<?>, MenuType<T>> regMenType(String name, IContainerFactory factory) {
        return MENUS.register(name, () -> IMenuTypeExtension.create(factory));
    }

    public static void reg(IEventBus bus) {
        MENUS.register(bus);
    }
}
