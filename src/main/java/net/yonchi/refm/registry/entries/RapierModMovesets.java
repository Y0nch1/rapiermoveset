package net.yonchi.refm.registry.entries;

import net.yonchi.refm.RapierForEpicfight;
import net.yonchi.refm.gameasset.RapierAnimations;
import yesman.epicfight.api.animation.LivingMotions;
import yesman.epicfight.api.ex_cap.data.Moveset;
import yesman.epicfight.registry.deferred.MovesetRegister;
import yesman.epicfight.registry.deferred.holders.DeferredMoveset;
import yesman.epicfight.skill.guard.GuardSkill;

public final class RapierModMovesets
{
    private RapierModMovesets() {}
    public static final MovesetRegister REGISTRY = MovesetRegister.create(RapierForEpicfight.MOD_ID);
    public static final DeferredMoveset RAPIER = REGISTRY.registerMoveset("rapier", () -> Moveset.builder()
            .addComboAttacks( RapierAnimations.RAPIER_AUTO1, RapierAnimations.RAPIER_AUTO2,
                    RapierAnimations.RAPIER_AUTO3, RapierAnimations.RAPIER_DASH, RapierAnimations.RAPIER_AIR_SLASH)
            .addInnateSkill((itemStack, patch) -> RapierSkills.DEADLYBACKFLIP.get())
            .addLivingMotionsRecursive(RapierAnimations.BIPED_HOLD_RAPIER, LivingMotions.IDLE, LivingMotions.JUMP,
                    LivingMotions.SWIM)
            .addLivingMotionsRecursive(RapierAnimations.BIPED_WALK_RAPIER, LivingMotions.WALK, LivingMotions.CHASE)
            .addLivingMotionsRecursive(RapierAnimations.BIPED_SNEAK_RAPIER, LivingMotions.KNEEL, LivingMotions.SNEAK)
            .addLivingMotionModifier(LivingMotions.BLOCK, RapierAnimations.RAPIER_GUARD)
            .addLivingMotionModifier(LivingMotions.RUN, RapierAnimations.BIPED_RUN_RAPIER)
            .addGuardAnimations(GuardSkill.BlockType.GUARD, RapierAnimations.RAPIER_GUARD_HIT)
            .addGuardAnimations(GuardSkill.BlockType.ADVANCED_GUARD, RapierAnimations.RAPIER_GUARD_DEFLECT1,
                    RapierAnimations.RAPIER_GUARD_DEFLECT2));

    public static final DeferredMoveset ENDER_RAPIER = REGISTRY.registerMoveset("ender_rapier", () -> Moveset.builder()
            .parent(RAPIER)
            .setPassiveSkill(RapierSkills.ENDER_PASSIVE)
            .addComboAttacks(RapierAnimations.RAPIER_AUTO1, RapierAnimations.RAPIER_AUTO2_ENDER, RapierAnimations.RAPIER_AUTO3_ENDER, RapierAnimations.RAPIER_DASH_ENDER, RapierAnimations.RAPIER_AIR_SLASH_ENDER)
            .revelationAttack(RapierAnimations.RAPIER_GUARD_PARRY_ENDER)
            .addInnateSkill((itemStack, patch) -> RapierSkills.DEADLYBACKFLIP_ENDER.get()));

    public static final DeferredMoveset OCEAN_RAPIER = REGISTRY.registerMoveset("ocean_rapier", () -> Moveset.builder()
            .parent(RAPIER)
            .setPassiveSkill(RapierSkills.OCEAN_PASSIVE)
            .addComboAttacks(RapierAnimations.RAPIER_AUTO1, RapierAnimations.RAPIER_AUTO2_OCEAN, RapierAnimations.RAPIER_AUTO3_OCEAN, RapierAnimations.RAPIER_DASH_OCEAN, RapierAnimations.RAPIER_AIR_SLASH_OCEAN)
            .revelationAttack(RapierAnimations.RAPIER_GUARD_PARRY_OCEAN)
            .addInnateSkill((itemStack, patch) -> RapierSkills.DEADLYBACKFLIP_OCEAN.get()));

    public static final DeferredMoveset WITHER_RAPIER = REGISTRY.registerMoveset("wither_rapier", () -> Moveset.builder()
            .parent(RAPIER)
            .setPassiveSkill(RapierSkills.WITHER_PASSIVE)
            .addComboAttacks(RapierAnimations.RAPIER_AUTO1, RapierAnimations.RAPIER_AUTO2_WITHER, RapierAnimations.RAPIER_AUTO3_WITHER, RapierAnimations.RAPIER_DASH_WITHER, RapierAnimations.RAPIER_AIR_SLASH_WITHER)
            .revelationAttack(RapierAnimations.RAPIER_GUARD_PARRY_WITHER)
            .addInnateSkill((itemStack, patch) -> RapierSkills.DEADLYBACKFLIP_WITHER.get()));

    public static final DeferredMoveset AMETHYST_RAPIER = REGISTRY.registerMoveset("amethyst_rapier", () -> Moveset.builder()
            .parent(RAPIER)
            .addComboAttacks(RapierAnimations.RAPIER_AUTO1, RapierAnimations.RAPIER_AUTO2_AMETHYST, RapierAnimations.RAPIER_AUTO3_AMETHYST, RapierAnimations.RAPIER_DASH_AMETHYST, RapierAnimations.RAPIER_AIR_SLASH_AMETHYST)
            .revelationAttack(RapierAnimations.RAPIER_GUARD_PARRY_AMETHYST)
            .addInnateSkill((itemStack, patch) -> RapierSkills.DEADLYBACKFLIP_AMETHYST.get()));
}
