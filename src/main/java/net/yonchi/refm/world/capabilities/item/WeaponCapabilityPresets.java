package net.yonchi.refm.world.capabilities.item;

import java.util.function.Function;

import net.minecraft.world.item.Item;
import net.yonchi.refm.gameasset.RapierAnimations;


public class WeaponCapabilityPresets {
    public static final Function<Item, RapierCapabilityItem.Builder> RAPIER = (item) ->
            RapierWeaponCapability.builder()
                    .category(WeaponCategories.RAPIER)
                    .styleProvider((playerpatch) -> Styles.ONE_HAND)
                    .collider(ColliderPreset.SPEAR)
                    .swingSound(EpicFightSounds.WHOOSH_BIG.get())
                    .hitSound(EpicFightSounds.BLADE_HIT.get())
                    .canBePlacedOffhand(false)
                    .newStyleCombo(Styles.ONE_HAND, RapierAnimations.RAPIER_AUTO1, RapierAnimations.RAPIER_AUTO2, RapierAnimations.RAPIER_DASH, RapierAnimations.RAPIER_AIR_SLASH)
                    .innateSkill(Styles.ONE_HAND, (itemstack) -> EpicFightSkills.STEEL_WHIRLWIND)
                    .livingMotionModifier(Styles.ONE_HAND, LivingMotions.IDLE, RapierAnimations.BIPED_HOLD_RAPIER)
                    .livingMotionModifier(Styles.ONE_HAND, LivingMotions.WALK, RapierAnimations.BIPED_WALK_RAPIER)
                    .livingMotionModifier(Styles.ONE_HAND, LivingMotions.CHASE, RapierAnimations.BIPED_WALK_RAPIER)
                    .livingMotionModifier(Styles.ONE_HAND, LivingMotions.RUN, RapierAnimations.BIPED_RUN_RAPIER)
                    .livingMotionModifier(Styles.ONE_HAND, LivingMotions.JUMP, RapierAnimations.BIPED_HOLD_RAPIER)
                    .livingMotionModifier(Styles.ONE_HAND, LivingMotions.KNEEL, RapierAnimations.BIPED_HOLD_RAPIER)
                    .livingMotionModifier(Styles.ONE_HAND, LivingMotions.SNEAK, RapierAnimations.BIPED_HOLD_RAPIER)
                    .livingMotionModifier(Styles.ONE_HAND, LivingMotions.SWIM, RapierAnimations.BIPED_HOLD_RAPIER)
                    .livingMotionModifier(Styles.ONE_HAND, LivingMotions.FLY, RapierAnimations.BIPED_HOLD_RAPIER)
                    .livingMotionModifier(Styles.ONE_HAND, LivingMotions.CREATIVE_FLY, RapierAnimations.BIPED_HOLD_RAPIER)
                    .livingMotionModifier(Styles.ONE_HAND, LivingMotions.CREATIVE_IDLE, RapierAnimations.BIPED_HOLD_RAPIER)
                    .livingMotionModifier(Styles.ONE_HAND, LivingMotions.BLOCK, RapierAnimations.RAPIER_GUARD);
}
