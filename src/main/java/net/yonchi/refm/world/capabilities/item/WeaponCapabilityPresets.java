package net.yonchi.refm.world.capabilities.item;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.item.Item;

import net.yonchi.refm.RapierForEpicfight;
import net.yonchi.refm.gameasset.*;

import net.yonchi.refm.registry.entries.RapierSkills;
import net.yonchi.refm.registry.entries.RapierSounds;
import yesman.epicfight.api.animation.LivingMotions;
import yesman.epicfight.api.event.types.registry.WeaponCapabilityPresetRegistryEvent;
import yesman.epicfight.world.capabilities.item.WeaponCapability;
import yesman.epicfight.world.capabilities.item.CapabilityItem.Styles;

import java.util.function.Function;

public class WeaponCapabilityPresets {
    public static final Function<Item, WeaponCapability.Builder> RAPIER = (item) -> {
        WeaponCapability.Builder builder = WeaponCapability.builder()
                .category(RapierWeaponCategories.RAPIER) // Updated to use custom category
                .styleProvider((playerpatch) -> Styles.OCHS)
                .collider(RapierColliderPreset.RAPIER)
                .swingSound(RapierSounds.RAPIER_STAB.get())
                .hitSound(RapierSounds.RAPIER_HIT.get())
                .canBePlacedOffhand(true)
                .newStyleCombo(Styles.OCHS, RapierAnimations.RAPIER_AUTO1, RapierAnimations.RAPIER_AUTO2, RapierAnimations.RAPIER_AUTO3, RapierAnimations.RAPIER_DASH, RapierAnimations.RAPIER_AIR_SLASH)
                .innateSkill(Styles.OCHS, (itemstack) -> RapierSkills.DEADLYBACKFLIP.get())
                .livingMotionModifier(Styles.OCHS, LivingMotions.IDLE, RapierAnimations.BIPED_HOLD_RAPIER)
                .livingMotionModifier(Styles.OCHS, LivingMotions.WALK, RapierAnimations.BIPED_WALK_RAPIER)
                .livingMotionModifier(Styles.OCHS, LivingMotions.CHASE, RapierAnimations.BIPED_WALK_RAPIER)
                .livingMotionModifier(Styles.OCHS, LivingMotions.RUN, RapierAnimations.BIPED_RUN_RAPIER)
                .livingMotionModifier(Styles.OCHS, LivingMotions.JUMP, RapierAnimations.BIPED_HOLD_RAPIER)
                .livingMotionModifier(Styles.OCHS, LivingMotions.KNEEL, RapierAnimations.BIPED_SNEAK_RAPIER)
                .livingMotionModifier(Styles.OCHS, LivingMotions.SNEAK, RapierAnimations.BIPED_SNEAK_RAPIER)
                .livingMotionModifier(Styles.OCHS, LivingMotions.SWIM, RapierAnimations.BIPED_HOLD_RAPIER)
                .livingMotionModifier(Styles.OCHS, LivingMotions.BLOCK, RapierAnimations.RAPIER_GUARD);

        return builder;
    };
    public static final Function<Item, WeaponCapability.Builder> ENDER_RAPIER = (item) -> {
        WeaponCapability.Builder builder = WeaponCapability.builder()
                .category(RapierWeaponCategories.ENDER_RAPIER) // Updated to use custom category
                .styleProvider((playerpatch) -> Styles.OCHS)
                .collider(RapierColliderPreset.RAPIER)
                .swingSound(RapierSounds.RAPIER_STAB.get())
                .hitSound(RapierSounds.RAPIER_HIT.get())
                .canBePlacedOffhand(true)
                .newStyleCombo(Styles.OCHS, RapierAnimations.RAPIER_AUTO1, RapierAnimations.RAPIER_AUTO2_ENDER, RapierAnimations.RAPIER_AUTO3_ENDER, RapierAnimations.RAPIER_DASH_ENDER, RapierAnimations.RAPIER_AIR_SLASH_ENDER)
                .innateSkill(Styles.OCHS, (itemstack) -> RapierSkills.DEADLYBACKFLIP_ENDER.get())
                .passiveSkill(RapierSkills.ENDER_PASSIVE.get())
                .livingMotionModifier(Styles.OCHS, LivingMotions.IDLE, RapierAnimations.BIPED_HOLD_RAPIER)
                .livingMotionModifier(Styles.OCHS, LivingMotions.WALK, RapierAnimations.BIPED_WALK_RAPIER)
                .livingMotionModifier(Styles.OCHS, LivingMotions.CHASE, RapierAnimations.BIPED_WALK_RAPIER)
                .livingMotionModifier(Styles.OCHS, LivingMotions.RUN, RapierAnimations.BIPED_RUN_RAPIER)
                .livingMotionModifier(Styles.OCHS, LivingMotions.JUMP, RapierAnimations.BIPED_HOLD_RAPIER)
                .livingMotionModifier(Styles.OCHS, LivingMotions.KNEEL, RapierAnimations.BIPED_SNEAK_RAPIER)
                .livingMotionModifier(Styles.OCHS, LivingMotions.SNEAK, RapierAnimations.BIPED_SNEAK_RAPIER)
                .livingMotionModifier(Styles.OCHS, LivingMotions.SWIM, RapierAnimations.BIPED_HOLD_RAPIER)
                .livingMotionModifier(Styles.OCHS, LivingMotions.BLOCK, RapierAnimations.RAPIER_GUARD);

        return builder;
    };
    public static final Function<Item, WeaponCapability.Builder> OCEAN_RAPIER = (item) -> {
        WeaponCapability.Builder builder = WeaponCapability.builder()
                .category(RapierWeaponCategories.OCEAN_RAPIER) // Updated to use custom category
                .styleProvider((playerpatch) -> Styles.OCHS)
                .collider(RapierColliderPreset.RAPIER)
                .swingSound(RapierSounds.RAPIER_STAB.get())
                .hitSound(SoundEvents.FISH_SWIM)
                .canBePlacedOffhand(true)
                .newStyleCombo(Styles.OCHS, RapierAnimations.RAPIER_AUTO1, RapierAnimations.RAPIER_AUTO2_OCEAN, RapierAnimations.RAPIER_AUTO3_OCEAN, RapierAnimations.RAPIER_DASH_OCEAN, RapierAnimations.RAPIER_AIR_SLASH_OCEAN)
                .innateSkill(Styles.OCHS, (itemstack) -> RapierSkills.DEADLYBACKFLIP_OCEAN.get())
                .passiveSkill(RapierSkills.OCEAN_PASSIVE.get())
                .livingMotionModifier(Styles.OCHS, LivingMotions.IDLE, RapierAnimations.BIPED_HOLD_RAPIER)
                .livingMotionModifier(Styles.OCHS, LivingMotions.WALK, RapierAnimations.BIPED_WALK_RAPIER)
                .livingMotionModifier(Styles.OCHS, LivingMotions.CHASE, RapierAnimations.BIPED_WALK_RAPIER)
                .livingMotionModifier(Styles.OCHS, LivingMotions.RUN, RapierAnimations.BIPED_RUN_RAPIER)
                .livingMotionModifier(Styles.OCHS, LivingMotions.JUMP, RapierAnimations.BIPED_HOLD_RAPIER)
                .livingMotionModifier(Styles.OCHS, LivingMotions.KNEEL, RapierAnimations.BIPED_SNEAK_RAPIER)
                .livingMotionModifier(Styles.OCHS, LivingMotions.SNEAK, RapierAnimations.BIPED_SNEAK_RAPIER)
                .livingMotionModifier(Styles.OCHS, LivingMotions.SWIM, RapierAnimations.BIPED_SWIM_RAPIER)
                .livingMotionModifier(Styles.OCHS, LivingMotions.BLOCK, RapierAnimations.RAPIER_GUARD);

        return builder;
    };
    public static final Function<Item, WeaponCapability.Builder> WITHER_RAPIER = (item) -> {
        WeaponCapability.Builder builder = WeaponCapability.builder()
                .category(RapierWeaponCategories.WITHER_RAPIER) // Updated to use custom category
                .styleProvider((playerpatch) -> Styles.OCHS)
                .collider(RapierColliderPreset.RAPIER)
                .swingSound(RapierSounds.RAPIER_STAB.get())
                .hitSound(RapierSounds.RAPIER_WITHER_HIT.get())
                .canBePlacedOffhand(true)
                .newStyleCombo(Styles.OCHS, RapierAnimations.RAPIER_AUTO1, RapierAnimations.RAPIER_AUTO2_WITHER, RapierAnimations.RAPIER_AUTO3_WITHER, RapierAnimations.RAPIER_DASH_WITHER, RapierAnimations.RAPIER_AIR_SLASH_WITHER)
                .innateSkill(Styles.OCHS, (itemstack) -> RapierSkills.DEADLYBACKFLIP_WITHER.get())
                .passiveSkill(RapierSkills.WITHER_PASSIVE.get())
                .livingMotionModifier(Styles.OCHS, LivingMotions.IDLE, RapierAnimations.BIPED_HOLD_RAPIER)
                .livingMotionModifier(Styles.OCHS, LivingMotions.WALK, RapierAnimations.BIPED_WALK_RAPIER)
                .livingMotionModifier(Styles.OCHS, LivingMotions.CHASE, RapierAnimations.BIPED_WALK_RAPIER)
                .livingMotionModifier(Styles.OCHS, LivingMotions.RUN, RapierAnimations.BIPED_RUN_RAPIER_WITHER)
                .livingMotionModifier(Styles.OCHS, LivingMotions.JUMP, RapierAnimations.BIPED_HOLD_RAPIER)
                .livingMotionModifier(Styles.OCHS, LivingMotions.KNEEL, RapierAnimations.BIPED_SNEAK_RAPIER)
                .livingMotionModifier(Styles.OCHS, LivingMotions.SNEAK, RapierAnimations.BIPED_SNEAK_RAPIER)
                .livingMotionModifier(Styles.OCHS, LivingMotions.SWIM, RapierAnimations.BIPED_HOLD_RAPIER)
                .livingMotionModifier(Styles.OCHS, LivingMotions.BLOCK, RapierAnimations.RAPIER_GUARD);

        return builder;
    };
    public static final Function<Item, WeaponCapability.Builder> AMETHYST_RAPIER = (item) -> {
        WeaponCapability.Builder builder = WeaponCapability.builder()
                .category(RapierWeaponCategories.AMETHYST_RAPIER) // Updated to use custom category
                .styleProvider((playerpatch) -> Styles.OCHS)
                .collider(RapierColliderPreset.RAPIER)
                .swingSound(SoundEvents.AMETHYST_BLOCK_RESONATE)
                .hitSound(SoundEvents.AMETHYST_BLOCK_HIT)
                .canBePlacedOffhand(true)
                .newStyleCombo(Styles.OCHS, RapierAnimations.RAPIER_AUTO1, RapierAnimations.RAPIER_AUTO2_AMETHYST, RapierAnimations.RAPIER_AUTO3_AMETHYST, RapierAnimations.RAPIER_DASH_AMETHYST, RapierAnimations.RAPIER_AIR_SLASH_AMETHYST)
                .innateSkill(Styles.OCHS, (itemstack) -> RapierSkills.DEADLYBACKFLIP_AMETHYST.get())
                .livingMotionModifier(Styles.OCHS, LivingMotions.IDLE, RapierAnimations.BIPED_HOLD_RAPIER)
                .livingMotionModifier(Styles.OCHS, LivingMotions.WALK, RapierAnimations.BIPED_WALK_RAPIER)
                .livingMotionModifier(Styles.OCHS, LivingMotions.CHASE, RapierAnimations.BIPED_WALK_RAPIER)
                .livingMotionModifier(Styles.OCHS, LivingMotions.RUN, RapierAnimations.BIPED_RUN_RAPIER)
                .livingMotionModifier(Styles.OCHS, LivingMotions.JUMP, RapierAnimations.BIPED_HOLD_RAPIER)
                .livingMotionModifier(Styles.OCHS, LivingMotions.KNEEL, RapierAnimations.BIPED_SNEAK_RAPIER)
                .livingMotionModifier(Styles.OCHS, LivingMotions.SNEAK, RapierAnimations.BIPED_SNEAK_RAPIER)
                .livingMotionModifier(Styles.OCHS, LivingMotions.SWIM, RapierAnimations.BIPED_HOLD_RAPIER)
                .livingMotionModifier(Styles.OCHS, LivingMotions.BLOCK, RapierAnimations.RAPIER_GUARD);

        return builder;
    };

    public static void registerMovesets(WeaponCapabilityPresetRegistryEvent event) {
            event.getTypeEntry().put(ResourceLocation.fromNamespaceAndPath(RapierForEpicfight.MOD_ID, "rapier"), RAPIER);
            event.getTypeEntry().put(ResourceLocation.fromNamespaceAndPath(RapierForEpicfight.MOD_ID, "ender_rapier"), ENDER_RAPIER);
            event.getTypeEntry().put(ResourceLocation.fromNamespaceAndPath(RapierForEpicfight.MOD_ID, "ocean_rapier"), OCEAN_RAPIER);
            event.getTypeEntry().put(ResourceLocation.fromNamespaceAndPath(RapierForEpicfight.MOD_ID, "wither_rapier"), WITHER_RAPIER);
            event.getTypeEntry().put(ResourceLocation.fromNamespaceAndPath(RapierForEpicfight.MOD_ID, "amethyst_rapier"), AMETHYST_RAPIER);
    }
}
