package net.yonchi.refm.world.capabilities.item;


import java.util.function.Function;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import net.yonchi.refm.RapierForEpicfight;
import net.yonchi.refm.gameasset.*;

import yesman.epicfight.api.forgeevent.WeaponCapabilityPresetRegistryEvent;
import yesman.epicfight.api.animation.LivingMotions;
import yesman.epicfight.world.capabilities.item.CapabilityItem;
import yesman.epicfight.world.capabilities.item.CapabilityItem.Styles;
import yesman.epicfight.world.capabilities.item.WeaponCapability;

@Mod.EventBusSubscriber(modid = RapierForEpicfight.MOD_ID , bus = Mod.EventBusSubscriber.Bus.MOD)
public class WeaponCapabilityPresets {
    public static final Function<Item, CapabilityItem.Builder> RAPIER = (item) -> {
        WeaponCapability.Builder builder = WeaponCapability.builder()
                .category(RapierWeaponCategories.RAPIER) // Updated to use custom category
                .styleProvider((playerpatch) -> Styles.OCHS)
                .collider(RapierColliderPreset.RAPIER)
                .swingSound(RapierSounds.RAPIER_STAB.get())
                .hitSound(RapierSounds.RAPIER_HIT.get())
                .canBePlacedOffhand(true)
                .newStyleCombo(Styles.OCHS, RapierAnimations.RAPIER_AUTO1, RapierAnimations.RAPIER_AUTO2, RapierAnimations.RAPIER_AUTO3, RapierAnimations.RAPIER_DASH, RapierAnimations.RAPIER_AIR_SLASH)
                .innateSkill(Styles.OCHS, (itemstack) -> RapierSkills.DEADLYBACKFLIP)
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
    public static final Function<Item, CapabilityItem.Builder> ENDER_RAPIER = (item) -> {
        WeaponCapability.Builder builder = WeaponCapability.builder()
                .category(RapierWeaponCategories.ENDER_RAPIER) // Updated to use custom category
                .styleProvider((playerpatch) -> Styles.OCHS)
                .collider(RapierColliderPreset.RAPIER)
                .swingSound(RapierSounds.RAPIER_STAB.get())
                .hitSound(RapierSounds.RAPIER_HIT.get())
                .canBePlacedOffhand(true)
                .newStyleCombo(Styles.OCHS, RapierAnimations.RAPIER_AUTO1, RapierAnimations.RAPIER_AUTO2, RapierAnimations.RAPIER_AUTO3_ENDER, RapierAnimations.RAPIER_DASH_ENDER, RapierAnimations.RAPIER_AIR_SLASH_ENDER)
                .innateSkill(Styles.OCHS, (itemstack) -> RapierSkills.DEADLYBACKFLIP_ENDER)
                .passiveSkill(RapierSkills.ENDER_PASSIVE)
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
    public static final Function<Item, CapabilityItem.Builder> OCEAN_RAPIER = (item) -> {
        WeaponCapability.Builder builder = WeaponCapability.builder()
                .category(RapierWeaponCategories.OCEAN_RAPIER) // Updated to use custom category
                .styleProvider((playerpatch) -> Styles.OCHS)
                .collider(RapierColliderPreset.RAPIER)
                .swingSound(RapierSounds.RAPIER_STAB.get())
                .hitSound(SoundEvents.FISH_SWIM)
                .canBePlacedOffhand(true)
                .newStyleCombo(Styles.OCHS, RapierAnimations.RAPIER_AUTO1, RapierAnimations.RAPIER_AUTO2, RapierAnimations.RAPIER_AUTO3_OCEAN, RapierAnimations.RAPIER_DASH_OCEAN, RapierAnimations.RAPIER_AIR_SLASH_OCEAN)
                .innateSkill(Styles.OCHS, (itemstack) -> RapierSkills.DEADLYBACKFLIP_OCEAN)
                .passiveSkill(RapierSkills.OCEAN_PASSIVE)
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
    public static final Function<Item, CapabilityItem.Builder> WITHER_RAPIER = (item) -> {
        WeaponCapability.Builder builder = WeaponCapability.builder()
                .category(RapierWeaponCategories.WITHER_RAPIER) // Updated to use custom category
                .styleProvider((playerpatch) -> Styles.OCHS)
                .collider(RapierColliderPreset.RAPIER)
                .swingSound(RapierSounds.RAPIER_STAB.get())
                .hitSound(RapierSounds.RAPIER_WITHER_HIT.get())
                .canBePlacedOffhand(true)
                .newStyleCombo(Styles.OCHS, RapierAnimations.RAPIER_AUTO1, RapierAnimations.RAPIER_AUTO2, RapierAnimations.RAPIER_AUTO3_WITHER, RapierAnimations.RAPIER_DASH_WITHER, RapierAnimations.RAPIER_AIR_SLASH_WITHER)
                .innateSkill(Styles.OCHS, (itemstack) -> RapierSkills.DEADLYBACKFLIP_WITHER)
                .passiveSkill(RapierSkills.WITHER_PASSIVE)
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
    public static final Function<Item, CapabilityItem.Builder> AMETHYST_RAPIER = (item) -> {
        WeaponCapability.Builder builder = WeaponCapability.builder()
                .category(RapierWeaponCategories.AMETHYST_RAPIER) // Updated to use custom category
                .styleProvider((playerpatch) -> Styles.OCHS)
                .collider(RapierColliderPreset.RAPIER)
                .swingSound(SoundEvents.AMETHYST_BLOCK_RESONATE)
                .hitSound(SoundEvents.AMETHYST_BLOCK_HIT)
                .canBePlacedOffhand(true)
                .newStyleCombo(Styles.OCHS, RapierAnimations.RAPIER_AUTO1, RapierAnimations.RAPIER_AUTO2_AMETHYST, RapierAnimations.RAPIER_AUTO3_AMETHYST, RapierAnimations.RAPIER_DASH_AMETHYST, RapierAnimations.RAPIER_AIR_SLASH_AMETHYST)
                .innateSkill(Styles.OCHS, (itemstack) -> RapierSkills.DEADLYBACKFLIP_AMETHYST)
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

    @SubscribeEvent
    public static void registerMovesets(WeaponCapabilityPresetRegistryEvent event) {
        event.getTypeEntry().put(new ResourceLocation(RapierForEpicfight.MOD_ID,"rapier"), RAPIER);
        event.getTypeEntry().put(new ResourceLocation(RapierForEpicfight.MOD_ID,"ender_rapier"), ENDER_RAPIER);
        event.getTypeEntry().put(new ResourceLocation(RapierForEpicfight.MOD_ID,"ocean_rapier"), OCEAN_RAPIER);
        event.getTypeEntry().put(new ResourceLocation(RapierForEpicfight.MOD_ID,"wither_rapier"), WITHER_RAPIER);
        event.getTypeEntry().put(new ResourceLocation(RapierForEpicfight.MOD_ID,"amethyst_rapier"), AMETHYST_RAPIER);
    }
}

//https://github.com/Yesssssman/epicfightmod/blob/1.20.1/src/main/java/yesman/epicfight/world/capabilities/item/WeaponCapabilityPresets.java