package net.yonchi.refm.gameasset;

import net.minecraft.core.particles.ParticleType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import net.yonchi.refm.RapierForEpicfight;

import yesman.epicfight.particle.HitParticleType;


@Mod.EventBusSubscriber(modid = RapierForEpicfight.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE, value = Dist.CLIENT)
public class ClientEvents {
    @SubscribeEvent
    public static void onClientTick(TickEvent.ClientTickEvent event) {
        if (event.phase == TickEvent.Phase.END) {
            RapierAnimations.ReusableEvents.spawnParticlesOcean();
            RapierAnimations.ReusableEvents.spawnParticlesOceanTiny();
            RapierAnimations.ReusableEvents.spawnParticlesWither();
            RapierAnimations.ReusableEvents.spawnParticlesWitherTiny();
        }
    }
    public static final DeferredRegister<ParticleType<?>> PARTICLES = DeferredRegister.create(ForgeRegistries.PARTICLE_TYPES, RapierForEpicfight.MOD_ID);

    public static final RegistryObject<HitParticleType> BUBBLE_SPLASH = PARTICLES.register("bubbles", () -> new HitParticleType(true, HitParticleType.RANDOM_WITHIN_BOUNDING_BOX, HitParticleType.ZERO));
}
