package com.mefiddzy.lmod.sounds;

import com.mefiddzy.lmod.LMod;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.item.JukeboxSong;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class ModSounds {
    public static final DeferredRegister<SoundEvent> SOUND_EVENTS = DeferredRegister.create(BuiltInRegistries.SOUND_EVENT, LMod.MOD_ID);

    public static final Supplier<SoundEvent> ENPOWERERS_USE = regSe("enpowerers_use");

    public static final Supplier<SoundEvent> AN_ENIGMATIC_ENCOUNTER = regSe("an_enigmatic_encounter");
    public static final ResourceKey<JukeboxSong> AN_ENIGMATIC_ENCOUNTER_KEY = createSong("an_enigmatic_encounter");

    private static Supplier<SoundEvent> regSe(String name) {
        return SOUND_EVENTS.register(name, () ->
                SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath(LMod.MOD_ID, name)));
    }

    private static ResourceKey<JukeboxSong> createSong(String name) {
        return ResourceKey.create(Registries.JUKEBOX_SONG, ResourceLocation.fromNamespaceAndPath(LMod.MOD_ID, name));
    }

    public static void reg(IEventBus bus) {
        SOUND_EVENTS.register(bus);
    }
            
}
