package net.yonchi.refm.gameasset;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import net.yonchi.refm.RapierForEpicfight;

public class RapierSounds {
    public static final DeferredRegister<SoundEvent> SOUNDS =
            DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, RapierForEpicfight.MOD_ID);

    public static final RegistryObject<SoundEvent> RAPIER_HIT = registerRapierSound("entity.weapon.rapier_hit");
    public static final RegistryObject<SoundEvent> RAPIER_SWING = registerRapierSound("entity.weapon.rapier_swing");
    public static final RegistryObject<SoundEvent> RAPIER_STAB = registerRapierSound("entity.weapon.rapier_stab");
    public static final RegistryObject<SoundEvent> RAPIER_JUMP = registerRapierSound("entity.weapon.rapier_jump");
    public static final RegistryObject<SoundEvent> RAPIER_SKILL = registerRapierSound("entity.weapon.rapier_skill");
    public static final RegistryObject<SoundEvent> RAPIER_OCEAN_JUMP = registerRapierSound("entity.weapon.rapier_ocean_jump");
    public static final RegistryObject<SoundEvent> RAPIER_OCEAN_WAVE = registerRapierSound("entity.weapon.rapier_ocean_wave");
    public static final RegistryObject<SoundEvent> RAPIER_WITHER_HIT = registerRapierSound("entity.weapon.rapier_wither_hit");

    public RapierSounds() {
    }

    private static RegistryObject<SoundEvent> registerRapierSound(String name) {
        ResourceLocation refms = new ResourceLocation(RapierForEpicfight.MOD_ID, name);
        return SOUNDS.register(name, () -> SoundEvent.createVariableRangeEvent(refms));
    }
}
