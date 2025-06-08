package com.mefiddzy.lmod.item;

import com.mefiddzy.lmod.LMod;
import com.mefiddzy.lmod.item.custom.DustEnpowererItem;
import com.mefiddzy.lmod.item.custom.FuelItem;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.UseAnim;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.List;

public class ModItems {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(LMod.MOD_ID);

    public static final DeferredItem<Item> ENPOWERED_GOLD_INGOT = ITEMS.register("enpowered_gold_ingot",
            () -> new Item(new Item.Properties()));

    public static final DeferredItem<Item> ENPOWERMENT_POWDER = ITEMS.register("enpowerment_powder",
            () -> new FuelItem(new Item.Properties(), 6400)); //200 - 1 item

    public static final DeferredItem<Item> TOUGH_POWDER = ITEMS.register("tough_powder",
            () -> new Item(new Item.Properties()));

    public static final DeferredItem<Item> DUST_EMPOWERER = ITEMS.register("dust_enpowerer",
            () -> new DustEnpowererItem(new Item.Properties().durability(150)));

    public static final DeferredItem<Item> DURACELL = ITEMS.register("duracell",
            () -> new Item(new Item.Properties().food(ModFoodPropr.DURACELL)){
                @Override
                public void appendHoverText(ItemStack stack, Item.TooltipContext context, List<Component> tooltipComponents, TooltipFlag tooltipFlag) {
                    if (!Screen.hasShiftDown()) {
                        tooltipComponents.add(Component.translatable("tooltip.lmod.noshift"));
                    }
                    else {
                        tooltipComponents.add(Component.translatable("tooltip.lmod.item.duracell"));
                    }
                    super.appendHoverText(stack, context, tooltipComponents, tooltipFlag);
                }

                @Override
                public UseAnim getUseAnimation(ItemStack stack) {
                    return UseAnim.DRINK;
                }
            });

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }

}
