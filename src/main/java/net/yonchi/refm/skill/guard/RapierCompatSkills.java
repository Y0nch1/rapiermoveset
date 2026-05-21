package net.yonchi.refm.skill.guard;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;

import net.neoforged.fml.ModList;

import net.yonchi.refm.gameasset.RapierAnimations;
import net.yonchi.refm.world.capabilities.item.RapierWeaponCategories;
import net.yonchi.refm.registry.entries.RapierAddonItems;

import yesman.epicfight.main.EpicFightSharedConstants;
import yesman.epicfight.registry.entries.EpicFightSkills;
import yesman.epicfight.api.client.event.types.registry.RegisterWeaponCategoryIconEvent;
import yesman.epicfight.api.event.types.registry.SkillBuilderModificationEvent;
import yesman.epicfight.gameasset.Animations;
import yesman.epicfight.skill.guard.GuardSkill;
import yesman.epicfight.skill.guard.ParryingSkill;
import yesman.epicfight.skill.passive.EmergencyEscapeSkill;
import yesman.epicfight.skill.passive.SwordmasterSkill;

import io.redspace.ironsspellbooks.registries.ItemRegistry;

import java.util.List;

public class RapierCompatSkills {

    public static void onEmergencyEscapeSkillCreation(SkillBuilderModificationEvent event) {
        if (event.getRegistryName().equals(EpicFightSkills.EMERGENCY_ESCAPE.getId())) {
            if (event.getSkillBuilder() instanceof EmergencyEscapeSkill.Builder builder) {
                builder.addAvailableWeaponCategory(RapierWeaponCategories.RAPIER)
                        .addAvailableWeaponCategory(RapierWeaponCategories.ENDER_RAPIER)
                        .addAvailableWeaponCategory(RapierWeaponCategories.OCEAN_RAPIER)
                        .addAvailableWeaponCategory(RapierWeaponCategories.WITHER_RAPIER);
                System.out.println("[RapierCompatSkills] Emergency escape has been implemented");
                if (EpicFightSharedConstants.isPhysicalClient() && ModList.get().isLoaded("irons_spellbooks")) {
                    builder.addAvailableWeaponCategory(RapierWeaponCategories.AMETHYST_RAPIER);
                    System.out.println("[RapierCompatSkills] Amethyst EmergencyEscape has been implemented");
                }
            }
        }
    }

    public static void onSwordMasterSkillCreation(SkillBuilderModificationEvent event) {
        if (event.getRegistryName().equals(EpicFightSkills.SWORD_MASTER.getId())) {
            if (event.getSkillBuilder() instanceof SwordmasterSkill.Builder builder) {
                builder.addAvailableWeaponCategory(RapierWeaponCategories.RAPIER)
                        .addAvailableWeaponCategory(RapierWeaponCategories.ENDER_RAPIER)
                        .addAvailableWeaponCategory(RapierWeaponCategories.OCEAN_RAPIER)
                        .addAvailableWeaponCategory(RapierWeaponCategories.WITHER_RAPIER);
                System.out.println("[RapierCompatSkills] SwordMaster has been implemented");
                if (EpicFightSharedConstants.isPhysicalClient() && ModList.get().isLoaded("irons_spellbooks")) {
                    builder.addAvailableWeaponCategory(RapierWeaponCategories.AMETHYST_RAPIER);
                    System.out.println("[RapierCompatSkills] Amethyst SwordMaster has been implemented");
                }
            }
        }
    }

    public static  void onEFNParrySkillCreation(SkillBuilderModificationEvent event) {
        if (event.getRegistryName().equals(ResourceLocation.fromNamespaceAndPath("efn","efn_parry"))) {
            GuardSkill.Builder builder = (ParryingSkill.Builder) event.getSkillBuilder();
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
                return List.of(RapierAnimations.RAPIER_GUARD_DEFLECT1, RapierAnimations.RAPIER_GUARD_DEFLECT2);
            }).addAdvancedGuardMotion(RapierWeaponCategories.ENDER_RAPIER, (item, player) -> {
                return List.of(RapierAnimations.RAPIER_GUARD_DEFLECT1, RapierAnimations.RAPIER_GUARD_DEFLECT2);
            }).addAdvancedGuardMotion(RapierWeaponCategories.OCEAN_RAPIER, (item, player) -> {
                return List.of(RapierAnimations.RAPIER_GUARD_DEFLECT1, RapierAnimations.RAPIER_GUARD_DEFLECT2);
            }).addAdvancedGuardMotion(RapierWeaponCategories.WITHER_RAPIER, (item, player) -> {
                return List.of(RapierAnimations.RAPIER_GUARD_DEFLECT1, RapierAnimations.RAPIER_GUARD_DEFLECT2);
            });
            System.out.println("[RapierCompatSkills] Enhanced Parry animations has been implemented");
            if (EpicFightSharedConstants.isPhysicalClient() && ModList.get().isLoaded("irons_spellbooks")) {
                builder.addGuardMotion(RapierWeaponCategories.AMETHYST_RAPIER, (item, player) -> {
                    return RapierAnimations.RAPIER_GUARD_HIT;
                }).addGuardBreakMotion(RapierWeaponCategories.AMETHYST_RAPIER, (item, player) -> {
                    return Animations.BIPED_COMMON_NEUTRALIZED;
                }).addAdvancedGuardMotion(RapierWeaponCategories.AMETHYST_RAPIER, (item, player) -> {
                    return List.of(RapierAnimations.RAPIER_GUARD_DEFLECT1, RapierAnimations.RAPIER_GUARD_DEFLECT2);
                });
                System.out.println("[RapierCompatSkills] Amethyst Enhanced Parry animations has been implemented");
            }
        }
    }

    public static void onWeaponCategoryIconCreation(RegisterWeaponCategoryIconEvent icon) {
        icon.registerCategory(RapierWeaponCategories.RAPIER, new ItemStack(RapierAddonItems.IRON_RAPIER.get()));
        icon.registerCategory(RapierWeaponCategories.ENDER_RAPIER, new ItemStack(RapierAddonItems.ENDERITE_RAPIER.get()));
        icon.registerCategory(RapierWeaponCategories.OCEAN_RAPIER, new ItemStack(RapierAddonItems.OCEANITE_RAPIER.get()));
        icon.registerCategory(RapierWeaponCategories.WITHER_RAPIER, new ItemStack(RapierAddonItems.WITHERITE_RAPIER.get()));
        System.out.println("[RapierCompatSkills] Skill icons has been implemented");
        if (EpicFightSharedConstants.isPhysicalClient() && ModList.get().isLoaded("irons_spellbooks")) {
                icon.registerCategory(RapierWeaponCategories.AMETHYST_RAPIER, new ItemStack(ItemRegistry.AMETHYST_RAPIER.get()));
                System.out.println("[RapierCompatSkills] Amethyst Skill icons has been implemented");
        }
    }
}