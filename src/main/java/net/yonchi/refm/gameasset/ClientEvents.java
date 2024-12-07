package net.yonchi.refm.gameasset;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import net.yonchi.refm.RapierForEpicfight;

@Mod.EventBusSubscriber(modid = RapierForEpicfight.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE, value = Dist.CLIENT)
public class ClientEvents {
    @SubscribeEvent
    public static void onClientTick(TickEvent.ClientTickEvent event) {
        if (event.phase == TickEvent.Phase.END) {
            RapierAnimations.ReusableEvents.spawnParticlesOcean();
            RapierAnimations.ReusableEvents.spawnParticlesOceanTiny();
            RapierAnimations.ReusableEvents.spawnParticlesWither();
            RapierAnimations.ReusableEvents.spawnParticlesWitherTiny();
            RapierAnimations.ReusableEvents.spawnParticlesAmethyst();
        }
    }
}
