package net.yonchi.refm.api.animation.configs;

import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.RegisterEvent;

import net.yonchi.refm.world.capabilities.item.RapierWeaponCategories;

import yesman.epicfight.compat.ICompatModule;
import yesman.epicfight.world.capabilities.item.WeaponCategory;

import com.tatunement.efstaminainteractions.EpicFightStaminaInteractionsMod;
import com.tatunement.efstaminainteractions.events.RegisterWeaponStaminaCostEvent;
import com.tatunement.efstaminainteractions.registries.WeaponStaminaCostRegistry;

@Mod.EventBusSubscriber(modid = EpicFightStaminaInteractionsMod.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class RapierStaminaConfig implements ICompatModule {
    @SubscribeEvent
    public static void onStaminaRegistration(RegisterWeaponStaminaCostEvent event) {
        Object[][] rapierCostMap = {
                {RapierWeaponCategories.RAPIER, RapierStaminaPresets.RAPIER_STAMINA_COST.get()},
                {RapierWeaponCategories.ENDER_RAPIER, RapierStaminaPresets.RAPIER_STAMINA_COST.get()},
                {RapierWeaponCategories.OCEAN_RAPIER, RapierStaminaPresets.RAPIER_STAMINA_COST.get()},
                {RapierWeaponCategories.WITHER_RAPIER, RapierStaminaPresets.RAPIER_STAMINA_COST.get()},
                {RapierWeaponCategories.AMETHYST_RAPIER, RapierStaminaPresets.RAPIER_STAMINA_COST.get()},
        };
        for(Object[] category : rapierCostMap) {
            WeaponStaminaCostRegistry.addWeaponStamina((WeaponCategory) category[0], (float) category[1]);
        }
    }
    public static void registerStamina(RegisterEvent bus) {
    }

    @Override
    public void onModEventBus(IEventBus iEventBus) {
    }
    @Override
    public void onForgeEventBus(IEventBus iEventBus) {
    }
    @Override
    public void onModEventBusClient(IEventBus iEventBus) {
    }
    @Override
    public void onForgeEventBusClient(IEventBus iEventBus) {
    }
}
