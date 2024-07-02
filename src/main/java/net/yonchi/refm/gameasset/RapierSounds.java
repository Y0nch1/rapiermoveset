package net.yonchi.refm.gameasset;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraftforge.common.util.ForgeSoundType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.yonchi.refm.RapierForEpicfight;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class RapierSounds {
    private static final Logger LOGGER = LogManager.getLogger();

    public static final DeferredRegister<SoundEvent> SOUNDS =
            DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, RapierForEpicfight.MOD_ID);

    public static final RegistryObject<SoundEvent> RAPIER_HIT = registerRapierSound("entity.weapon.rapier_hit");
    public static final RegistryObject<SoundEvent> RAPIER_SWING = registerRapierSound("entity.weapon.rapier_swing");
    public static final RegistryObject<SoundEvent> RAPIER_STAB = registerRapierSound("entity.weapon.rapier_stab");
    public static final RegistryObject<SoundEvent> RAPIER_JUMP = registerRapierSound("entity.weapon.rapier_jump");
    public static final RegistryObject<SoundEvent> RAPIER_SKILL = registerRapierSound("entity.weapon.rapier_skill");

    public static final ForgeSoundType SOUND_RAPIER_SOUNDS = new ForgeSoundType(1f, 1f,
            RapierSounds.RAPIER_HIT,
            RapierSounds.RAPIER_SWING,
            RapierSounds.RAPIER_STAB,
            RapierSounds.RAPIER_JUMP,
            RapierSounds.RAPIER_SKILL
    );

    public RapierSounds() {
    }

    private static RegistryObject<SoundEvent> registerRapierSound(String name) {
        LOGGER.debug("Registering rapier sound: " + name);
        return SOUNDS.register(name, () -> {
            LOGGER.debug("Creating SoundEvent for: " + name);
            return SoundEvent.createVariableRangeEvent(new ResourceLocation(RapierForEpicfight.MOD_ID, name));
        });
    }

    public static void register(IEventBus eventBus) {
        LOGGER.debug("Registering sounds to event bus");
        SOUNDS.register(eventBus);
    }
}
