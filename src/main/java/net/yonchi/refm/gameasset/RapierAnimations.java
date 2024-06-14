package net.yonchi.refm.gameasset;

import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.yonchi.refm.RapierForEpicfight;
import yesman.epicfight.api.animation.property.AnimationProperty;
import yesman.epicfight.api.animation.property.MoveCoordFunctions;
import yesman.epicfight.api.animation.types.*;
import yesman.epicfight.api.animation.types.AttackAnimation;
import yesman.epicfight.api.animation.property.AnimationProperty.AttackAnimationProperty;
import yesman.epicfight.api.forgeevent.AnimationRegistryEvent;
import yesman.epicfight.api.utils.HitEntityList;
import yesman.epicfight.api.utils.math.ValueModifier;
import yesman.epicfight.gameasset.Armatures;
import yesman.epicfight.gameasset.EpicFightSounds;
import yesman.epicfight.model.armature.HumanoidArmature;
import yesman.epicfight.particle.EpicFightParticles;
import yesman.epicfight.world.damagesource.StunType;

public class RapierAnimations {

    public static StaticAnimation RAPIER_AIR_SLASH;
    public static StaticAnimation RAPIER_AUTO1;
    public static StaticAnimation RAPIER_AUTO2;
    public static StaticAnimation RAPIER_AUTO3;
    public static StaticAnimation RAPIER_DASH;
    public static StaticAnimation RAPIER_GUARD;
    public static StaticAnimation RAPIER_GUARD_HIT;
    public static StaticAnimation BIPED_HOLD_RAPIER;
    public static StaticAnimation BIPED_WALK_RAPIER;
    public static StaticAnimation BIPED_RUN_RAPIER;
    public static StaticAnimation DEADLYBACKFLIP_FIRST;
    public static StaticAnimation DEADLYBACKFLIP_SECOND;

    @SubscribeEvent
    public static void registerAnimations(AnimationRegistryEvent event){
        event.getRegistryMap().put(RapierForEpicfight.MOD_ID, RapierAnimations::build);
    }

    private static void build() {
        HumanoidArmature biped = Armatures.BIPED;


        RAPIER_AIR_SLASH = new AirSlashAnimation(0.1F, 0.2F, 0.5F, 0.8F, null, biped.toolR, "biped/combat/rapier_airslash", biped);
//
        RAPIER_AUTO1 = new BasicAttackAnimation(0.1F, 0.4F, 0.5F, 0.5F, null, biped.toolR, "biped/combat/rapier_auto1", biped)
                .addProperty(AttackAnimationProperty.BASIS_ATTACK_SPEED, 1.5F);
        RAPIER_AUTO2 = new BasicAttackAnimation(0.15F,"biped/combat/rapier_auto2", biped,
                new AttackAnimation.Phase(0.0F, 0.7F, 0.1F, 0.1F, 0.4F, 0.2F, biped.toolR, null)
                ,new AttackAnimation.Phase(0.2F, 0.4F, 0.5F, 0.4F, 0.8F, biped.toolR, null))
                .addProperty(AttackAnimationProperty.BASIS_ATTACK_SPEED, 2.6F);
        RAPIER_AUTO3 = new BasicAttackAnimation(0.1F, 0.6F, 0.4F, 0.8F, null, biped.toolR, "biped/combat/rapier_auto3", biped)
                .addProperty(AttackAnimationProperty.BASIS_ATTACK_SPEED, 2F);
//
        RAPIER_DASH = new DashAttackAnimation(0.15F, "biped/combat/rapier_dash", biped, new AttackAnimation.Phase(0.0F, 0.3F, 0.8F, 0.5F, 0.7F, biped.toolR,null))
                .addProperty(AttackAnimationProperty.BASIS_ATTACK_SPEED, 1.6F)
                .addProperty(AnimationProperty.ActionAnimationProperty.CANCELABLE_MOVE, false);
        RAPIER_GUARD = new StaticAnimation(true, "biped/skill/guard_rapier", biped);
        RAPIER_GUARD_HIT = new GuardAnimation(0.05F, "biped/skill/guard_rapier_hit", biped);
//
        BIPED_HOLD_RAPIER = new StaticAnimation(true, "biped/living/hold_rapier", biped);
        BIPED_WALK_RAPIER = new MovementAnimation(true, "biped/living/walk_rapier", biped);
        BIPED_RUN_RAPIER = new MovementAnimation(true, "biped/living/run_rapier", biped);
//
        DEADLYBACKFLIP_FIRST = new AttackAnimation(0.1F, 0.25F, 0.3F, 0.4F, 0.8F, null, biped.torso, "biped/skill/rapier_backflip_first", biped)
                .addProperty(AttackAnimationProperty.BASIS_ATTACK_SPEED, 2.4F)
                .addProperty(AnimationProperty.AttackPhaseProperty.STUN_TYPE, StunType.HOLD)
                .addProperty(AnimationProperty.AttackPhaseProperty.HIT_PRIORITY, HitEntityList.Priority.TARGET)
                .addProperty(AnimationProperty.ActionAnimationProperty.COORD_SET_BEGIN, MoveCoordFunctions.TRACE_LOCROT_TARGET)
                .addProperty(AnimationProperty.ActionAnimationProperty.COORD_SET_TICK, MoveCoordFunctions.TRACE_LOCROT_TARGET)
                .addProperty(AnimationProperty.ActionAnimationProperty.CANCELABLE_MOVE, false)
                .addState(EntityState.MOVEMENT_LOCKED, true);
        DEADLYBACKFLIP_SECOND = new AttackAnimation(0.1F, 0.0F, 0.5F, 1.2F, 0.95F, null, biped.toolR, "biped/skill/rapier_backflip_second", biped)
                .addProperty(AttackAnimationProperty.BASIS_ATTACK_SPEED, 1.8F)
                .addProperty(AnimationProperty.AttackPhaseProperty.STUN_TYPE, StunType.NEUTRALIZE)
                .addProperty(AnimationProperty.AttackPhaseProperty.HIT_SOUND, EpicFightSounds.EVISCERATE.get())
                .addProperty(AnimationProperty.AttackPhaseProperty.PARTICLE, EpicFightParticles.BLADE_RUSH_SKILL)
                .addProperty(AnimationProperty.ActionAnimationProperty.CANCELABLE_MOVE, false)
                .addState(EntityState.MOVEMENT_LOCKED, true);
    }
}