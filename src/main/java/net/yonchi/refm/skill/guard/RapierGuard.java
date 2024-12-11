package net.yonchi.refm.skill.guard;

import net.minecraft.world.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.RegisterEvent;

import net.yonchi.refm.RapierForEpicfight;
import net.yonchi.refm.gameasset.RapierSkills;
import net.yonchi.refm.world.capabilities.item.RapierWeaponCategories;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import net.yonchi.refm.gameasset.RapierAnimations;
import net.yonchi.refm.world.item.RapierAddonItems;

import yesman.epicfight.api.animation.types.StaticAnimation;
import yesman.epicfight.api.client.forgeevent.WeaponCategoryIconRegisterEvent;
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


@Mod(RapierForEpicfight.MOD_ID)
public class RapierGuard {
    public static final String MOD_ID = "refm";
    public static final Logger LOGGER = LogManager.getLogger("refm");

    public RapierGuard() {
        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
        bus.addListener(RapierAnimations::registerAnimations);
        bus.addListener(RapierSkills::registerRapierSkills);
        MinecraftForge.EVENT_BUS.register(this);
        bus.addListener(RapierGuard::buildSkillEvent);
        bus.addListener(RapierGuard::regIcon);
    }

    public static void registerGuard(Event event) {
    }

    public static void regIcon(WeaponCategoryIconRegisterEvent event){
        event.registerCategory(RapierWeaponCategories.RAPIER,new ItemStack(RapierAddonItems.IRON_RAPIER.get()));
    }
    public static void registerRenderer(Event event) {
    }
    public static void addPackFindersEvent(Event event) {
    }
    @Mod.EventBusSubscriber(modid = MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event) {
        }
    }
    public static boolean regGuarded=false;
    public static void buildSkillEvent(RegisterEvent event){
        if (EpicFightSkills.GUARD==null){return;}
        if (regGuarded){return;}
        try {
            regGuard();
        }catch (Exception e){
            e.printStackTrace();
        }
        regGuarded=true;
    }
    public static void regGuard() throws NoSuchFieldException, IllegalAccessException {
        LOGGER.info("buildSkillEvent");
        Map<WeaponCategory, BiFunction<CapabilityItem, PlayerPatch<?>, ?>> guardMotions=new HashMap<>();
        Map<WeaponCategory, BiFunction<CapabilityItem, PlayerPatch<?>, ?>> guardBreakMotions=new HashMap<>();
        Map<WeaponCategory, BiFunction<CapabilityItem, PlayerPatch<?>, ?>> advancedGuardMotions=new HashMap<>();

        //Normal
        guardMotions.put(RapierWeaponCategories.RAPIER, (item, player) ->
                RapierAnimations.RAPIER_GUARD_HIT);
        guardBreakMotions.put(RapierWeaponCategories.RAPIER, (item, player) ->
                Animations.BIPED_COMMON_NEUTRALIZED);
        advancedGuardMotions.put(RapierWeaponCategories.RAPIER, (itemCap, playerpatch) ->
                new StaticAnimation[] {RapierAnimations.RAPIER_GUARD_PARRY});
        //Ender
        guardMotions.put(RapierWeaponCategories.ENDER_RAPIER, (item, player) ->
                RapierAnimations.RAPIER_GUARD_HIT);
        guardBreakMotions.put(RapierWeaponCategories.ENDER_RAPIER, (item, player) ->
                Animations.BIPED_COMMON_NEUTRALIZED);
        advancedGuardMotions.put(RapierWeaponCategories.ENDER_RAPIER, (itemCap, playerpatch) ->
                new StaticAnimation[] {RapierAnimations.RAPIER_GUARD_PARRY_ENDER});
        //Ocean
        guardMotions.put(RapierWeaponCategories.OCEAN_RAPIER, (item, player) ->
                RapierAnimations.RAPIER_GUARD_HIT);
        guardBreakMotions.put(RapierWeaponCategories.OCEAN_RAPIER, (item, player) ->
                Animations.BIPED_COMMON_NEUTRALIZED);
        advancedGuardMotions.put(RapierWeaponCategories.OCEAN_RAPIER, (itemCap, playerpatch) ->
                new StaticAnimation[] {RapierAnimations.RAPIER_GUARD_PARRY_OCEAN});
        //Wither
        guardMotions.put(RapierWeaponCategories.WITHER_RAPIER, (item, player) ->
                RapierAnimations.RAPIER_GUARD_HIT);
        guardBreakMotions.put(RapierWeaponCategories.WITHER_RAPIER, (item, player) ->
                Animations.BIPED_COMMON_NEUTRALIZED);
        advancedGuardMotions.put(RapierWeaponCategories.WITHER_RAPIER, (itemCap, playerpatch) ->
                new StaticAnimation[] {RapierAnimations.RAPIER_GUARD_PARRY_WITHER});
        //Amethyst
        guardMotions.put(RapierWeaponCategories.AMETHYST_RAPIER, (item, player) ->
                RapierAnimations.RAPIER_GUARD_HIT);
        guardBreakMotions.put(RapierWeaponCategories.AMETHYST_RAPIER, (item, player) ->
                Animations.BIPED_COMMON_NEUTRALIZED);
        advancedGuardMotions.put(RapierWeaponCategories.AMETHYST_RAPIER, (itemCap, playerpatch) ->
                new StaticAnimation[] {RapierAnimations.RAPIER_GUARD_PARRY_AMETHYST});

        Field temp;
        Map<WeaponCategory, BiFunction<CapabilityItem, PlayerPatch<?>, ?>> target;
        temp= GuardSkill.class.getDeclaredField("guardMotions");
        temp.setAccessible(true);
        target= (Map) temp.get(EpicFightSkills.GUARD);
        for (WeaponCategory weaponCapability:guardMotions.keySet()){
            target.put(weaponCapability,guardMotions.get(weaponCapability));
        }
        target=(Map) temp.get(EpicFightSkills.PARRYING);
        for (WeaponCategory weaponCapability:guardMotions.keySet()){
            target.put(weaponCapability,guardMotions.get(weaponCapability));
        }
        target=(Map) temp.get(EpicFightSkills.IMPACT_GUARD);
        for (WeaponCategory weaponCapability:guardMotions.keySet()){
            target.put(weaponCapability,guardMotions.get(weaponCapability));
        }

        temp=GuardSkill.class.getDeclaredField("guardBreakMotions");
        temp.setAccessible(true);
        target= (Map) temp.get(EpicFightSkills.GUARD);
        for (WeaponCategory weaponCapability:guardBreakMotions.keySet()){
            target.put(weaponCapability,guardBreakMotions.get(weaponCapability));
        }
        target=(Map) temp.get(EpicFightSkills.PARRYING);
        for (WeaponCategory weaponCapability:guardBreakMotions.keySet()){
            target.put(weaponCapability,guardBreakMotions.get(weaponCapability));
        }
        target=(Map) temp.get(EpicFightSkills.IMPACT_GUARD);
        for (WeaponCategory weaponCapability:guardBreakMotions.keySet()){
            target.put(weaponCapability,guardBreakMotions.get(weaponCapability));
        }

        temp=GuardSkill.class.getDeclaredField("advancedGuardMotions");
        temp.setAccessible(true);
        target=(Map) temp.get(EpicFightSkills.PARRYING);
        for (WeaponCategory weaponCapability:advancedGuardMotions.keySet()){
            target.put(weaponCapability,advancedGuardMotions.get(weaponCapability));
        }
    }
}
