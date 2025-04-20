package net.yonchi.refm.skill.guard;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import net.yonchi.refm.RapierForEpicfight;
import net.yonchi.refm.gameasset.RapierAnimations;
import net.yonchi.refm.world.capabilities.item.RapierWeaponCategories;
import net.yonchi.refm.world.item.RapierAddonItems;

import yesman.epicfight.api.client.forgeevent.WeaponCategoryIconRegisterEvent;
import yesman.epicfight.api.forgeevent.SkillBuildEvent;
import yesman.epicfight.api.forgeevent.SkillBuildEvent.ModRegistryWorker.SkillCreateEvent;
import yesman.epicfight.gameasset.Animations;
import yesman.epicfight.skill.guard.GuardSkill;
import yesman.epicfight.skill.guard.ParryingSkill;
import yesman.epicfight.skill.passive.EmergencyEscapeSkill;
import yesman.epicfight.skill.passive.SwordmasterSkill;

import java.util.List;

@Mod.EventBusSubscriber(modid = RapierForEpicfight.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class RapierCompatSkills {
    public static void forceGuard(SkillBuildEvent bus) {
    }
    @SubscribeEvent
    public static void onGuardSkillCreate(SkillCreateEvent<GuardSkill.Builder> event) {
        System.out.println("[RapierCompatSkills] Skill being builded: " + event.getRegistryName());
        if (event.getRegistryName().equals(ResourceLocation.fromNamespaceAndPath("epicfight","guard"))) {
            GuardSkill.Builder builder = event.getSkillBuilder();
            builder.addGuardMotion(RapierWeaponCategories.RAPIER, (item, player) -> {
                return RapierAnimations.RAPIER_GUARD_HIT;
            }).addGuardMotion(RapierWeaponCategories.ENDER_RAPIER, (item, player) -> {
                return RapierAnimations.RAPIER_GUARD_HIT;
            }).addGuardMotion(RapierWeaponCategories.OCEAN_RAPIER, (item, player) -> {
                return RapierAnimations.RAPIER_GUARD_HIT;
            }).addGuardMotion(RapierWeaponCategories.WITHER_RAPIER, (item, player) -> {
                return RapierAnimations.RAPIER_GUARD_HIT;
            }).addGuardBreakMotion(RapierWeaponCategories.RAPIER, (item, player) -> {
                return Animations.BIPED_COMMON_NEUTRALIZED;
            }).addGuardBreakMotion(RapierWeaponCategories.ENDER_RAPIER, (item, player) -> {
                return Animations.BIPED_COMMON_NEUTRALIZED;
            }).addGuardBreakMotion(RapierWeaponCategories.OCEAN_RAPIER, (item, player) -> {
                return Animations.BIPED_COMMON_NEUTRALIZED;
            }).addGuardBreakMotion(RapierWeaponCategories.WITHER_RAPIER, (item, player) -> {
                return Animations.BIPED_COMMON_NEUTRALIZED;
            });
            System.out.println("[RapierCompatSkills] Rapier Guard animations has been implemented");
        }
    }

    @SubscribeEvent
    public static void onParrySkillCreate(SkillCreateEvent<ParryingSkill.Builder> event) {
        if (event.getRegistryName().equals(ResourceLocation.fromNamespaceAndPath("epicfight","parrying"))) {
            GuardSkill.Builder builder = event.getSkillBuilder();
            builder.addGuardMotion(RapierWeaponCategories.RAPIER, (item, player) -> {
                return RapierAnimations.RAPIER_GUARD_HIT;
            }).addGuardMotion(RapierWeaponCategories.ENDER_RAPIER, (item, player) -> {
                return RapierAnimations.RAPIER_GUARD_HIT;
            }).addGuardMotion(RapierWeaponCategories.OCEAN_RAPIER, (item, player) -> {
                return RapierAnimations.RAPIER_GUARD_HIT;
            }).addGuardMotion(RapierWeaponCategories.WITHER_RAPIER, (item, player) -> {
                return RapierAnimations.RAPIER_GUARD_HIT;
            }).addGuardBreakMotion(RapierWeaponCategories.RAPIER, (item, player) -> {
                return Animations.BIPED_COMMON_NEUTRALIZED;
            }).addGuardBreakMotion(RapierWeaponCategories.ENDER_RAPIER, (item, player) -> {
                return Animations.BIPED_COMMON_NEUTRALIZED;
            }).addGuardBreakMotion(RapierWeaponCategories.OCEAN_RAPIER, (item, player) -> {
                return Animations.BIPED_COMMON_NEUTRALIZED;
            }).addGuardBreakMotion(RapierWeaponCategories.WITHER_RAPIER, (item, player) -> {
                return Animations.BIPED_COMMON_NEUTRALIZED;
            }).addAdvancedGuardMotion(RapierWeaponCategories.RAPIER, (item, player) -> {
                return List.of(RapierAnimations.RAPIER_GUARD_PARRY);
            }).addAdvancedGuardMotion(RapierWeaponCategories.ENDER_RAPIER, (item, player) -> {
                return List.of(RapierAnimations.RAPIER_GUARD_PARRY_ENDER);
            }).addAdvancedGuardMotion(RapierWeaponCategories.OCEAN_RAPIER, (item, player) -> {
                return List.of(RapierAnimations.RAPIER_GUARD_PARRY_OCEAN);
            }).addAdvancedGuardMotion(RapierWeaponCategories.WITHER_RAPIER, (item, player) -> {
                return List.of(RapierAnimations.RAPIER_GUARD_PARRY_WITHER);
            });
            System.out.println("[RapierCompatSkills] Rapier Parry animations has been implemented");
        }
    }

    @SubscribeEvent
    public static void onScapeSkillCreate(SkillCreateEvent<EmergencyEscapeSkill.Builder> event) {
        if (event.getRegistryName().equals(ResourceLocation.fromNamespaceAndPath("epicfight","emergency_escape"))) {
            EmergencyEscapeSkill.Builder builder = event.getSkillBuilder();
            builder.addAvailableWeaponCategory(RapierWeaponCategories.RAPIER)
            .addAvailableWeaponCategory(RapierWeaponCategories.ENDER_RAPIER)
            .addAvailableWeaponCategory(RapierWeaponCategories.OCEAN_RAPIER)
            .addAvailableWeaponCategory(RapierWeaponCategories.WITHER_RAPIER);
            System.out.println("[RapierCompatSkills] Emergency escape has been implemented");
        }
    }

    @SubscribeEvent
    public static void onSwordSkillCreate(SkillCreateEvent<SwordmasterSkill.Builder> event) {
        if (event.getRegistryName().equals(ResourceLocation.fromNamespaceAndPath("epicfight","swordmaster"))) {
            SwordmasterSkill.Builder builder = event.getSkillBuilder();
            builder.addAvailableWeaponCategory(RapierWeaponCategories.RAPIER)
                    .addAvailableWeaponCategory(RapierWeaponCategories.ENDER_RAPIER)
                    .addAvailableWeaponCategory(RapierWeaponCategories.OCEAN_RAPIER)
                    .addAvailableWeaponCategory(RapierWeaponCategories.WITHER_RAPIER);
            System.out.println("[RapierCompatSkills] SwordMaster has been implemented");
        }
    }

    @SubscribeEvent
    @OnlyIn(Dist.CLIENT)
    public static void onIconCreate(WeaponCategoryIconRegisterEvent icon){
        icon.registerCategory(RapierWeaponCategories.RAPIER, new ItemStack(RapierAddonItems.IRON_RAPIER.get()));
        icon.registerCategory(RapierWeaponCategories.ENDER_RAPIER, new ItemStack(RapierAddonItems.ENDERITE_RAPIER.get()));
        icon.registerCategory(RapierWeaponCategories.OCEAN_RAPIER, new ItemStack(RapierAddonItems.OCEANITE_RAPIER.get()));
        icon.registerCategory(RapierWeaponCategories.WITHER_RAPIER, new ItemStack(RapierAddonItems.WITHERITE_RAPIER.get()));
        System.out.println("[RapierCompatSkills] Skill icons has been implemented");
    }
}