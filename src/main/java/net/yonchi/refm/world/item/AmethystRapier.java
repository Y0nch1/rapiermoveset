package net.yonchi.refm.world.item;

import net.minecraft.world.item.ItemStack;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.RegisterEvent;
import net.yonchi.refm.gameasset.RapierAnimations;
import net.yonchi.refm.world.capabilities.item.RapierWeaponCategories;
import yesman.epicfight.api.animation.types.StaticAnimation;
import yesman.epicfight.api.client.forgeevent.WeaponCategoryIconRegisterEvent;
import yesman.epicfight.compat.ICompatModule;
import yesman.epicfight.gameasset.Animations;
import yesman.epicfight.gameasset.EpicFightSkills;
import yesman.epicfight.skill.guard.GuardSkill;
import yesman.epicfight.world.capabilities.entitypatch.player.PlayerPatch;
import yesman.epicfight.world.capabilities.item.CapabilityItem;
import yesman.epicfight.world.capabilities.item.WeaponCategory;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import java.util.function.BiFunction;

import static com.mojang.text2speech.Narrator.LOGGER;

public class AmethystRapier implements ICompatModule {
    public static void registerGuard(Event event) {
    }

    public static void regIcon(WeaponCategoryIconRegisterEvent event) {
        event.registerCategory(RapierWeaponCategories.AMETHYST_RAPIER, new ItemStack(RapierAddonItems.AMETHYST_RAPIER.get()));
    }

    public static boolean regGuarded = false;

    public static void buildSkillEvent(RegisterEvent event) {
        if (EpicFightSkills.GUARD == null) {
            return;
        }
        if (regGuarded) {
            return;
        }
        try {
            regGuard();
        } catch (Exception e) {
            e.printStackTrace();
        }
        regGuarded = true;
    }

    public static void regGuard() throws NoSuchFieldException, IllegalAccessException {
        LOGGER.info("buildSkillEvent");
        Map<WeaponCategory, BiFunction<CapabilityItem, PlayerPatch<?>, ?>> guardMotions = new HashMap<>();
        Map<WeaponCategory, BiFunction<CapabilityItem, PlayerPatch<?>, ?>> guardBreakMotions = new HashMap<>();
        Map<WeaponCategory, BiFunction<CapabilityItem, PlayerPatch<?>, ?>> advancedGuardMotions = new HashMap<>();

        //Amethyst
        guardMotions.put(RapierWeaponCategories.AMETHYST_RAPIER, (item, player) ->
                RapierAnimations.RAPIER_GUARD_HIT);
        guardBreakMotions.put(RapierWeaponCategories.AMETHYST_RAPIER, (item, player) ->
                Animations.BIPED_COMMON_NEUTRALIZED);
        advancedGuardMotions.put(RapierWeaponCategories.AMETHYST_RAPIER, (itemCap, playerpatch) ->
                new StaticAnimation[]{RapierAnimations.RAPIER_GUARD_PARRY_AMETHYST});

        Field temp;
        Map<WeaponCategory, BiFunction<CapabilityItem, PlayerPatch<?>, ?>> target;
        temp = GuardSkill.class.getDeclaredField("guardMotions");
        temp.setAccessible(true);
        target = (Map) temp.get(EpicFightSkills.GUARD);
        for (WeaponCategory weaponCapability : guardMotions.keySet()) {
            target.put(weaponCapability, guardMotions.get(weaponCapability));
        }
        target = (Map) temp.get(EpicFightSkills.PARRYING);
        for (WeaponCategory weaponCapability : guardMotions.keySet()) {
            target.put(weaponCapability, guardMotions.get(weaponCapability));
        }
        target = (Map) temp.get(EpicFightSkills.IMPACT_GUARD);
        for (WeaponCategory weaponCapability : guardMotions.keySet()) {
            target.put(weaponCapability, guardMotions.get(weaponCapability));
        }

        temp = GuardSkill.class.getDeclaredField("guardBreakMotions");
        temp.setAccessible(true);
        target = (Map) temp.get(EpicFightSkills.GUARD);
        for (WeaponCategory weaponCapability : guardBreakMotions.keySet()) {
            target.put(weaponCapability, guardBreakMotions.get(weaponCapability));
        }
        target = (Map) temp.get(EpicFightSkills.PARRYING);
        for (WeaponCategory weaponCapability : guardBreakMotions.keySet()) {
            target.put(weaponCapability, guardBreakMotions.get(weaponCapability));
        }
        target = (Map) temp.get(EpicFightSkills.IMPACT_GUARD);
        for (WeaponCategory weaponCapability : guardBreakMotions.keySet()) {
            target.put(weaponCapability, guardBreakMotions.get(weaponCapability));
        }

        temp = GuardSkill.class.getDeclaredField("advancedGuardMotions");
        temp.setAccessible(true);
        target = (Map) temp.get(EpicFightSkills.PARRYING);
        for (WeaponCategory weaponCapability : advancedGuardMotions.keySet()) {
            target.put(weaponCapability, advancedGuardMotions.get(weaponCapability));
        }
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
