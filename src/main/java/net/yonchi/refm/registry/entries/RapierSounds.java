package net.yonchi.refm.registry.entries;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.sounds.SoundEvent;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

import net.yonchi.refm.RapierForEpicfight;

public final class RapierSounds {
    public static final DeferredRegister<SoundEvent> REGISTRY =
            DeferredRegister.create(BuiltInRegistries.SOUND_EVENT, RapierForEpicfight.MOD_ID);

    private RapierSounds() {}

    public static final DeferredHolder<SoundEvent, SoundEvent> RAPIER_HIT = registerSound("entity.weapon.rapier_hit");
    public static final DeferredHolder<SoundEvent, SoundEvent> RAPIER_SWING = registerSound("entity.weapon.rapier_swing");
    public static final DeferredHolder<SoundEvent, SoundEvent> RAPIER_STAB = registerSound("entity.weapon.rapier_stab");
    public static final DeferredHolder<SoundEvent, SoundEvent> RAPIER_JUMP = registerSound("entity.weapon.rapier_jump");
    public static final DeferredHolder<SoundEvent, SoundEvent> RAPIER_SKILL = registerSound("entity.weapon.rapier_skill");
    public static final DeferredHolder<SoundEvent, SoundEvent> RAPIER_OCEAN_JUMP = registerSound("entity.weapon.rapier_ocean_jump");
    public static final DeferredHolder<SoundEvent, SoundEvent> RAPIER_OCEAN_WAVE = registerSound("entity.weapon.rapier_ocean_wave");
    public static final DeferredHolder<SoundEvent, SoundEvent> RAPIER_WITHER_HIT = registerSound("entity.weapon.rapier_wither_hit");

    private static DeferredHolder<SoundEvent, SoundEvent> registerSound(String name) {
        return REGISTRY.register(name, () -> SoundEvent.createVariableRangeEvent(RapierForEpicfight.identifier(name)));
    }

}
