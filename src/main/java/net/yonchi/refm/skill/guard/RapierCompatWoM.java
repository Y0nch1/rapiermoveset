package net.yonchi.refm.skill.guard;

//import net.minecraftforge.eventbus.api.Event;
//import net.minecraftforge.eventbus.api.IEventBus;
//import net.minecraftforge.registries.RegisterEvent;

import net.neoforged.bus.api.IEventBus;

//import reascer.wom.gameasset.WOMSkills;

//import yesman.epicfight.api.client.forgeevent.WeaponCategoryIconRegisterEvent;
import yesman.epicfight.compat.ICompatModule;
//import yesman.epicfight.gameasset.EpicFightSkills;


public class RapierCompatWoM implements ICompatModule {
    @Override
    public void onGameEventBus(IEventBus iEventBus) {}
    @Override
    public void onModEventBusClient(IEventBus iEventBus) {}
    @Override
    public void onGameEventBusClient(IEventBus iEventBus) {}
    @Override
    public void onModEventBus(IEventBus iEventBus) {}
    /*
    public static void regIcon(WeaponCategoryIconRegisterEvent event) {
        event.registerCategory(RapierWeaponCategories.RAPIER, new ItemStack(RapierAddonItems.IRON_RAPIER.get()));
        event.registerCategory(RapierWeaponCategories.ENDER_RAPIER, new ItemStack(RapierAddonItems.ENDERITE_RAPIER.get()));
        event.registerCategory(RapierWeaponCategories.OCEAN_RAPIER, new ItemStack(RapierAddonItems.OCEANITE_RAPIER.get()));
        event.registerCategory(RapierWeaponCategories.WITHER_RAPIER, new ItemStack(RapierAddonItems.WITHERITE_RAPIER.get()));
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
        Map<WeaponCategory, BiFunction<CapabilityItem, PlayerPatch<?>, ?>> guardMotions = new HashMap<>();
        Map<WeaponCategory, BiFunction<CapabilityItem, PlayerPatch<?>, ?>> guardBreakMotions = new HashMap<>();
        Map<WeaponCategory, BiFunction<CapabilityItem, PlayerPatch<?>, ?>> advancedGuardMotions = new HashMap<>();

        //Normal
        guardMotions.put(RapierWeaponCategories.RAPIER, (item, player) ->
                RapierAnimations.RAPIER_GUARD_HIT);
        guardBreakMotions.put(RapierWeaponCategories.RAPIER, (item, player) ->
                Animations.BIPED_COMMON_NEUTRALIZED);
        advancedGuardMotions.put(RapierWeaponCategories.RAPIER, (itemCap, playerpatch) ->
                RapierAnimations.RAPIER_GUARD_PARRY);
        //Ender
        guardMotions.put(RapierWeaponCategories.ENDER_RAPIER, (item, player) ->
                RapierAnimations.RAPIER_GUARD_HIT);
        guardBreakMotions.put(RapierWeaponCategories.ENDER_RAPIER, (item, player) ->
                Animations.BIPED_COMMON_NEUTRALIZED);
        advancedGuardMotions.put(RapierWeaponCategories.ENDER_RAPIER, (itemCap, playerpatch) ->
                RapierAnimations.RAPIER_GUARD_PARRY_ENDER);
        //Ocean
        guardMotions.put(RapierWeaponCategories.OCEAN_RAPIER, (item, player) ->
                RapierAnimations.RAPIER_GUARD_HIT);
        guardBreakMotions.put(RapierWeaponCategories.OCEAN_RAPIER, (item, player) ->
                Animations.BIPED_COMMON_NEUTRALIZED);
        advancedGuardMotions.put(RapierWeaponCategories.OCEAN_RAPIER, (itemCap, playerpatch) ->
                RapierAnimations.RAPIER_GUARD_PARRY_OCEAN);
        //Wither
        guardMotions.put(RapierWeaponCategories.WITHER_RAPIER, (item, player) ->
                RapierAnimations.RAPIER_GUARD_HIT);
        guardBreakMotions.put(RapierWeaponCategories.WITHER_RAPIER, (item, player) ->
                Animations.BIPED_COMMON_NEUTRALIZED);
        advancedGuardMotions.put(RapierWeaponCategories.WITHER_RAPIER, (itemCap, playerpatch) ->
                RapierAnimations.RAPIER_GUARD_PARRY_WITHER);

        Field temp;
        Map<WeaponCategory, BiFunction<CapabilityItem, PlayerPatch<?>, ?>> target;
        temp = GuardSkill.class.getDeclaredField("guardMotions");
        temp.setAccessible(true);
        target = (Map) temp.get(WOMSkills.COUNTER_ATTACK);
        for (WeaponCategory weaponCapability : guardMotions.keySet()) {
            target.put(weaponCapability, guardMotions.get(weaponCapability));
        }
        target = (Map) temp.get(WOMSkills.VENGEFUL_PARRY);
        for (WeaponCategory weaponCapability : guardMotions.keySet()) {
            target.put(weaponCapability, guardMotions.get(weaponCapability));
        }

        temp = GuardSkill.class.getDeclaredField("guardBreakMotions");
        temp.setAccessible(true);
        target = (Map) temp.get(WOMSkills.COUNTER_ATTACK);
        for (WeaponCategory weaponCapability : guardBreakMotions.keySet()) {
            target.put(weaponCapability, guardBreakMotions.get(weaponCapability));
        }
        target = (Map) temp.get(WOMSkills.VENGEFUL_PARRY);
        for (WeaponCategory weaponCapability : guardBreakMotions.keySet()) {
            target.put(weaponCapability, guardBreakMotions.get(weaponCapability));
        }

        temp = GuardSkill.class.getDeclaredField("advancedGuardMotions");
        temp.setAccessible(true);
        target = (Map) temp.get(WOMSkills.COUNTER_ATTACK);
        for (WeaponCategory weaponCapability : advancedGuardMotions.keySet()) {
            target.put(weaponCapability, advancedGuardMotions.get(weaponCapability));
        }

    }
    */
}
