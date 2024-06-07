package net.yonchi.refm.world.capabilities.item;

import java.util.function.Function;

import net.minecraft.world.item.Item;
import net.yonchi.refm.gameasset.RapierAnimations;
import net.yonchi.refm.gameasset.RapierColliderPreset;
import net.yonchi.refm.gameasset.RapierSkills;
import yesman.epicfight.api.animation.LivingMotions;
import yesman.epicfight.gameasset.EpicFightSkills;
import yesman.epicfight.gameasset.EpicFightSounds;
import yesman.epicfight.world.capabilities.item.CapabilityItem.Styles;
import yesman.epicfight.world.capabilities.item.CapabilityItem.WeaponCategories;


public class RapierWeaponCapabilityPresets {
    public static final Function<Item, RapierCapabilityItem.Builder> RAPIER = (item) ->
            RapierWeaponCapability.builder()
                    .category(RapierCapabilityItem.RapierWeaponCategories.RAPIER)
                    .styleProvider((playerpatch) -> Styles.ONE_HAND)
                    .collider(RapierColliderPreset.RAPIER)
                    .swingSound(EpicFightSounds.WHOOSH_BIG.get())
                    .hitSound(EpicFightSounds.BLADE_HIT.get())
                    .canBePlacedOffhand(false)
                    .newStyleCombo(Styles.ONE_HAND, RapierAnimations.RAPIER_AUTO1, RapierAnimations.RAPIER_AUTO2, RapierAnimations.RAPIER_DASH, RapierAnimations.RAPIER_AIR_SLASH)
                    .innateSkill(Styles.ONE_HAND, (itemstack) -> RapierSkills.DEADLYBACKFLIP)
                    .livingMotionModifier(Styles.ONE_HAND, LivingMotions.IDLE, RapierAnimations.BIPED_HOLD_RAPIER)
                    .livingMotionModifier(Styles.ONE_HAND, LivingMotions.WALK, RapierAnimations.BIPED_WALK_RAPIER)
                    .livingMotionModifier(Styles.ONE_HAND, LivingMotions.CHASE, RapierAnimations.BIPED_WALK_RAPIER)
                    .livingMotionModifier(Styles.ONE_HAND, LivingMotions.RUN, RapierAnimations.BIPED_RUN_RAPIER)
                    .livingMotionModifier(Styles.ONE_HAND, LivingMotions.JUMP, RapierAnimations.BIPED_HOLD_RAPIER)
                    .livingMotionModifier(Styles.ONE_HAND, LivingMotions.KNEEL, RapierAnimations.BIPED_HOLD_RAPIER)
                    .livingMotionModifier(Styles.ONE_HAND, LivingMotions.SNEAK, RapierAnimations.BIPED_HOLD_RAPIER)
                    .livingMotionModifier(Styles.ONE_HAND, LivingMotions.SWIM, RapierAnimations.BIPED_HOLD_RAPIER)
                    .livingMotionModifier(Styles.ONE_HAND, LivingMotions.BLOCK, RapierAnimations.RAPIER_GUARD);
}

//https://github.com/Yesssssman/epicfightmod/blob/1.20.1/src/main/java/yesman/epicfight/world/capabilities/item/WeaponCapabilityPresets.java