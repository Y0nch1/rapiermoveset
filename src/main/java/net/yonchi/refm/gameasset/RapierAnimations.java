package net.yonchi.refm.gameasset;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import net.yonchi.refm.RapierForEpicfight;
import net.yonchi.refm.api.animation.property.RapierMoveCoordFunctions;

import yesman.epicfight.api.animation.property.AnimationEvent;
import yesman.epicfight.api.animation.property.AnimationProperty;
import yesman.epicfight.api.animation.property.MoveCoordFunctions;
import yesman.epicfight.api.animation.types.*;
import yesman.epicfight.api.animation.types.AttackAnimation;
import yesman.epicfight.api.animation.property.AnimationProperty.AttackAnimationProperty;
import yesman.epicfight.api.forgeevent.AnimationRegistryEvent;
import yesman.epicfight.api.utils.HitEntityList;
import yesman.epicfight.api.utils.TimePairList;
import yesman.epicfight.api.utils.math.ValueModifier;
import yesman.epicfight.gameasset.Armatures;
import yesman.epicfight.gameasset.EpicFightSounds;
import yesman.epicfight.model.armature.HumanoidArmature;
import yesman.epicfight.particle.EpicFightParticles;
import yesman.epicfight.world.damagesource.EpicFightDamageType;
import yesman.epicfight.world.damagesource.StunType;

public class RapierAnimations {

    public static StaticAnimation RAPIER_AIR_SLASH;
    public static StaticAnimation RAPIER_AIR_SLASH_ENDER;
    public static StaticAnimation RAPIER_AIR_SLASH_OCEAN;
    public static StaticAnimation RAPIER_AIR_SLASH_WITHER;
    public static StaticAnimation RAPIER_DASH;
    public static StaticAnimation RAPIER_DASH_ENDER;
    public static StaticAnimation RAPIER_DASH_OCEAN;
    public static StaticAnimation RAPIER_DASH_WITHER;
    public static StaticAnimation RAPIER_AUTO1;
    public static StaticAnimation RAPIER_AUTO2;
    public static StaticAnimation RAPIER_AUTO3;
    public static StaticAnimation RAPIER_AUTO3_ENDER;
    public static StaticAnimation RAPIER_AUTO3_OCEAN;
    public static StaticAnimation RAPIER_AUTO3_WITHER;
    public static StaticAnimation RAPIER_GUARD;
    public static StaticAnimation RAPIER_GUARD_HIT;
    public static StaticAnimation RAPIER_GUARD_ACTIVE_HIT1;
    public static StaticAnimation RAPIER_GUARD_ACTIVE_HIT2;
    public static StaticAnimation BIPED_HOLD_RAPIER;
    public static StaticAnimation BIPED_SNEAK_RAPIER;
    public static StaticAnimation BIPED_WALK_RAPIER;
    public static StaticAnimation BIPED_RUN_RAPIER;
    public static StaticAnimation DEADLYBACKFLIP_FIRST;
    public static StaticAnimation DEADLYBACKFLIP_SECOND;
    public static StaticAnimation DEADLYBACKFLIP_SECOND_ENDER;
    public static StaticAnimation DEADLYBACKFLIP_SECOND_OCEAN;
    public static StaticAnimation DEADLYBACKFLIP_SECOND_WITHER;

    public static StaticAnimation RAPIER_AIR_SLASH_AMETHYST;
    public static StaticAnimation RAPIER_DASH_AMETHYST;
    public static StaticAnimation RAPIER_AUTO2_AMETHYST;
    public static StaticAnimation RAPIER_AUTO3_AMETHYST;
    public static StaticAnimation DEADLYBACKFLIP_SECOND_AMETHYST;

    @SubscribeEvent
    public static void registerAnimations(AnimationRegistryEvent event) {
        event.getRegistryMap().put(RapierForEpicfight.MOD_ID, RapierAnimations::build);
    }

    private static void build() {
        HumanoidArmature biped = Armatures.BIPED;

        //Every animation defined with his own hitbox timers and Properties
        RAPIER_AIR_SLASH = new AirSlashAnimation(0.1F, 0.2F, 0.5F, 0.5F, null, biped.toolR, "biped/combat/rapier_airslash", biped)
                .addProperty(AttackAnimationProperty.BASIS_ATTACK_SPEED, 2.3F)
                .addProperty(AnimationProperty.AttackPhaseProperty.STUN_TYPE, StunType.NONE)
                .addProperty(AnimationProperty.AttackPhaseProperty.SOURCE_TAG, Set.of(EpicFightDamageType.FINISHER))
                .addProperty(AnimationProperty.AttackPhaseProperty.SWING_SOUND, RapierSounds.RAPIER_JUMP.get())
                .addProperty(AnimationProperty.ActionAnimationProperty.MOVE_VERTICAL, false);
        RAPIER_AIR_SLASH_ENDER = new AttackAnimation(0.18F, "biped/combat/rapier_airslash_ender", biped,
                new AttackAnimation.Phase(0.0F, 0.2F, 0.44F, 0.75F, 0.85F, 0.8F, biped.toolR, null)
                        .addProperty(AnimationProperty.AttackPhaseProperty.STUN_TYPE, StunType.SHORT),
                new AttackAnimation.Phase(0.8F, 0.5F, 1.1F, 1.6F, 1.5F, biped.toolR, null)
                        .addProperty(AnimationProperty.AttackPhaseProperty.STUN_TYPE, StunType.LONG))
                .addProperty(AttackAnimationProperty.BASIS_ATTACK_SPEED, 2.3F)
                .addProperty(AnimationProperty.AttackPhaseProperty.DAMAGE_MODIFIER, ValueModifier.multiplier(0.6F))
                .addProperty(AnimationProperty.AttackPhaseProperty.SWING_SOUND, RapierSounds.RAPIER_JUMP.get())
                .addProperty(AnimationProperty.ActionAnimationProperty.CANCELABLE_MOVE, false)
                .addProperty(AnimationProperty.ActionAnimationProperty.MOVE_VERTICAL, true)
                .addEvents(AnimationEvent.TimeStampedEvent.create(0.18F, ReusableEvents.ENDER_PARTICLES_AIRSLASH, AnimationEvent.Side.CLIENT))
                .addState(EntityState.MOVEMENT_LOCKED, true);
        RAPIER_AIR_SLASH_OCEAN = new AirSlashAnimation(0.1F, 0.38F, 0.56F, 0.56F, null, biped.toolR, "biped/combat/rapier_airslash_ocean", biped)
                .addProperty(AttackAnimationProperty.BASIS_ATTACK_SPEED, 2.3F)
                .addProperty(AnimationProperty.AttackPhaseProperty.STUN_TYPE, StunType.SHORT)
                .addProperty(AnimationProperty.AttackPhaseProperty.SOURCE_TAG, Set.of(EpicFightDamageType.FINISHER))
                .addProperty(AnimationProperty.AttackPhaseProperty.SWING_SOUND, RapierSounds.RAPIER_JUMP.get())
                .addProperty(AnimationProperty.ActionAnimationProperty.MOVE_VERTICAL, false)
                .addEvents(AnimationEvent.TimeStampedEvent.create(0.1F, ReusableEvents.OCEAN_PARTICLES_STABS, AnimationEvent.Side.CLIENT));
        RAPIER_AIR_SLASH_WITHER = new AirSlashAnimation(0.1F, 0.3F, 0.49F, 0.51F, null, biped.toolR, "biped/combat/rapier_airslash_wither", biped)
                .addProperty(AttackAnimationProperty.BASIS_ATTACK_SPEED, 2.8F)
                .addProperty(AnimationProperty.AttackPhaseProperty.STUN_TYPE, StunType.SHORT)
                .addProperty(AnimationProperty.AttackPhaseProperty.SOURCE_TAG, Set.of(EpicFightDamageType.FINISHER))
                .addProperty(AnimationProperty.AttackPhaseProperty.SWING_SOUND, RapierSounds.RAPIER_JUMP.get())
                .addProperty(AnimationProperty.AttackPhaseProperty.SWING_SOUND, SoundEvents.WITHER_SHOOT)
                .addProperty(AnimationProperty.ActionAnimationProperty.MOVE_VERTICAL, false)
                .addEvents(AnimationEvent.TimeStampedEvent.create(0.09F, ReusableEvents.WITHER_PARTICLES_AIRSLASH, AnimationEvent.Side.CLIENT));

        RAPIER_DASH = new DashAttackAnimation(0.15F, "biped/combat/rapier_dash", biped, new AttackAnimation.Phase(0.0F, 0.42F, 0.69F, 0.8F, 1F, biped.toolR, null))
                .addProperty(AnimationProperty.AttackPhaseProperty.SWING_SOUND, RapierSounds.RAPIER_SWING.get())
                .addProperty(AttackAnimationProperty.BASIS_ATTACK_SPEED, 1.2F);
        RAPIER_DASH_ENDER = new DashAttackAnimation(0.15F, "biped/combat/rapier_dash_ender", biped, new AttackAnimation.Phase(0.0F, 0.5F, 0.78F, 1F, 1F, biped.toolR, null))
                .addProperty(AttackAnimationProperty.BASIS_ATTACK_SPEED, 1.2F)
                .addProperty(AnimationProperty.AttackPhaseProperty.SWING_SOUND, RapierSounds.RAPIER_SWING.get())
                .addProperty(AnimationProperty.ActionAnimationProperty.COORD_SET_TICK, RapierMoveCoordFunctions.TRACE_LOCROT_TARGET)
                .addProperty(AnimationProperty.ActionAnimationProperty.CANCELABLE_MOVE, false)
                .addEvents(AnimationEvent.TimeStampedEvent.create(0.25F, ReusableEvents.ENDER_PARTICLES_DASH, AnimationEvent.Side.CLIENT))
                .addState(EntityState.MOVEMENT_LOCKED, true);
        RAPIER_DASH_OCEAN = new DashAttackAnimation(0.15F, "biped/combat/rapier_dash_ocean", biped, new AttackAnimation.Phase(0.0F, 0.69F, 1.2F, 1.24F, 1.24F, biped.toolR, null))
                .addProperty(AnimationProperty.AttackPhaseProperty.SWING_SOUND, RapierSounds.RAPIER_STAB.get())
                .addProperty(AttackAnimationProperty.BASIS_ATTACK_SPEED, 1.2F)
                .addEvents(AnimationEvent.TimeStampedEvent.create(0.32F, ReusableEvents.OCEAN_PARTICLES_STABS, AnimationEvent.Side.CLIENT));
        RAPIER_DASH_WITHER = new DashAttackAnimation(0.15F, "biped/combat/rapier_dash_wither", biped, new AttackAnimation.Phase(0.0F, 0.36F, 0.68F, 0.74F, 0.8F, biped.toolR, null))
                .addProperty(AnimationProperty.AttackPhaseProperty.SWING_SOUND, SoundEvents.WITHER_HURT)
                .addProperty(AnimationProperty.AttackPhaseProperty.HIT_SOUND, RapierSounds.RAPIER_HIT.get())
                .addProperty(AttackAnimationProperty.BASIS_ATTACK_SPEED, 1.2F)
                .addEvents(AnimationEvent.TimeStampedEvent.create(0.15F, ReusableEvents.WITHER_PARTICLES_DASH, AnimationEvent.Side.CLIENT));

        RAPIER_AUTO1 = new BasicAttackAnimation(0.12F, 0.5F, 0.8F, 0.5F, null, biped.toolR, "biped/combat/rapier_auto1", biped)
                .addProperty(AttackAnimationProperty.BASIS_ATTACK_SPEED, 1.8F);
        RAPIER_AUTO2 = new BasicAttackAnimation(0.12F, 0.4F, 0.4F, 0.56F, null, biped.toolR, "biped/combat/rapier_auto2", biped)
                .addProperty(AttackAnimationProperty.BASIS_ATTACK_SPEED, 1.8F);
        RAPIER_AUTO3 = new BasicAttackAnimation(0.18F, "biped/combat/rapier_auto3", biped,
                new AttackAnimation.Phase(0.0F, 0.3F, 0.25F, 0.42F, 0.5F, 0.5F, biped.toolR, null)
                        .addProperty(AnimationProperty.AttackPhaseProperty.DAMAGE_MODIFIER, ValueModifier.multiplier(0.5F))
                        .addProperty(AnimationProperty.AttackPhaseProperty.STUN_TYPE, StunType.NONE),
                new AttackAnimation.Phase(0.5F, 0.6F, 0.58F, 0.8F, 0.82F, 0.82F, biped.toolR, null)
                        .addProperty(AnimationProperty.AttackPhaseProperty.DAMAGE_MODIFIER, ValueModifier.multiplier(0.75F)))
                .addProperty(AttackAnimationProperty.BASIS_ATTACK_SPEED, 2.1F);
        RAPIER_AUTO3_ENDER = new BasicAttackAnimation(0.18F, "biped/combat/rapier_auto3_ender", biped,
                new AttackAnimation.Phase(0.0F, 0.3F, 0.25F, 0.42F, 0.5F, 0.5F, biped.toolR, null)
                        .addProperty(AnimationProperty.AttackPhaseProperty.DAMAGE_MODIFIER, ValueModifier.multiplier(0.5F))
                        .addProperty(AnimationProperty.AttackPhaseProperty.STUN_TYPE, StunType.NONE),
                new AttackAnimation.Phase(0.5F, 0.6F, 0.58F, 0.8F, 0.82F, 0.82F, biped.toolR, null)
                        .addProperty(AnimationProperty.AttackPhaseProperty.DAMAGE_MODIFIER, ValueModifier.multiplier(0.75F)))
                .addProperty(AttackAnimationProperty.BASIS_ATTACK_SPEED, 2.1F)
                .addEvents(AnimationEvent.TimeStampedEvent.create(0.075F, ReusableEvents.ENDER_PARTICLES_AUTO3, AnimationEvent.Side.CLIENT));
        RAPIER_AUTO3_OCEAN = new BasicAttackAnimation(0.18F, "biped/combat/rapier_auto3_ocean", biped,
                new AttackAnimation.Phase(0.0F, 0.3F, 0.28F, 0.52F, 0.6F, 0.6F, biped.toolR, null)
                        .addProperty(AnimationProperty.AttackPhaseProperty.DAMAGE_MODIFIER, ValueModifier.multiplier(0.5F))
                        .addProperty(AnimationProperty.AttackPhaseProperty.STUN_TYPE, StunType.NONE),
                new AttackAnimation.Phase(0.6F, 0.6F, 0.86F, 1.2F, 1.2F, 1.2F, biped.toolR, null)
                        .addProperty(AnimationProperty.AttackPhaseProperty.DAMAGE_MODIFIER, ValueModifier.multiplier(0.75F)))
                .addProperty(AttackAnimationProperty.BASIS_ATTACK_SPEED, 2.1F)
                .addEvents(AnimationEvent.TimeStampedEvent.create(0.46F, ReusableEvents.OCEAN_PARTICLES_STABS, AnimationEvent.Side.CLIENT));
        RAPIER_AUTO3_WITHER = new BasicAttackAnimation(0.18F, "biped/combat/rapier_auto3_wither", biped,
                new AttackAnimation.Phase(0.0F, 0.3F, 0.33F, 0.46F, 0.48F, 0.5F, biped.toolR, null)
                        .addProperty(AnimationProperty.AttackPhaseProperty.DAMAGE_MODIFIER, ValueModifier.multiplier(0.33F))
                        .addProperty(AnimationProperty.AttackPhaseProperty.SWING_SOUND, SoundEvents.WITHER_SHOOT)
                        .addProperty(AnimationProperty.AttackPhaseProperty.STUN_TYPE, StunType.NONE),
                new AttackAnimation.Phase(0.5F, 0.6F, 0.66F, 0.72F, 0.72F, 1F, biped.toolR, null)
                        .addProperty(AnimationProperty.AttackPhaseProperty.DAMAGE_MODIFIER, ValueModifier.multiplier(0.66F))
                        .addProperty(AnimationProperty.AttackPhaseProperty.SWING_SOUND, SoundEvents.WITHER_SHOOT)
                        .addProperty(AnimationProperty.AttackPhaseProperty.STUN_TYPE, StunType.SHORT))
                .addProperty(AttackAnimationProperty.BASIS_ATTACK_SPEED, 2.1F)
                .addEvents(AnimationEvent.TimeStampedEvent.create(0.32F, ReusableEvents.WITHER_PARTICLES_AUTO3, AnimationEvent.Side.CLIENT));

        RAPIER_GUARD = new StaticAnimation(true, "biped/skill/guard_rapier", biped);
        RAPIER_GUARD_HIT = new GuardAnimation(0.05F, "biped/skill/guard_rapier_hit", biped);
        RAPIER_GUARD_ACTIVE_HIT1 = new GuardAnimation(0.05F, 0.2F, "biped/skill/guard_rapier_hit_active1", biped);
        RAPIER_GUARD_ACTIVE_HIT2 = new GuardAnimation(0.05F, 0.2F, "biped/skill/guard_rapier_hit_active2", biped);

        BIPED_HOLD_RAPIER = new StaticAnimation(true, "biped/living/hold_rapier", biped);
        BIPED_SNEAK_RAPIER = new StaticAnimation(true, "biped/living/sneak_rapier", biped);
        BIPED_WALK_RAPIER = new MovementAnimation(true, "biped/living/walk_rapier", biped);
        BIPED_RUN_RAPIER = new MovementAnimation(true, "biped/living/run_rapier", biped);

        DEADLYBACKFLIP_FIRST = new AttackAnimation(0.1F, 0.2F, 0.0F, 2.8F, 3F, RapierColliderPreset.KICK, biped.thighL, "biped/skill/rapier_backflip_first", biped)
                .addProperty(AttackAnimationProperty.BASIS_ATTACK_SPEED, 2.9F)
                .addProperty(AnimationProperty.AttackPhaseProperty.STUN_TYPE, StunType.HOLD)
                .addProperty(AnimationProperty.AttackPhaseProperty.IMPACT_MODIFIER, ValueModifier.setter(8))
                .addProperty(AnimationProperty.AttackPhaseProperty.HIT_PRIORITY, HitEntityList.Priority.TARGET)
                .addProperty(AnimationProperty.AttackPhaseProperty.SWING_SOUND, EpicFightSounds.WHOOSH_BIG.get())
                .addProperty(AnimationProperty.AttackPhaseProperty.HIT_SOUND, EpicFightSounds.BLUNT_HIT.get())
                .addProperty(AnimationProperty.ActionAnimationProperty.COORD_SET_BEGIN, RapierMoveCoordFunctions.TRACE_LOCROT_TARGET)
                .addProperty(AnimationProperty.ActionAnimationProperty.COORD_SET_TICK, RapierMoveCoordFunctions.TRACE_LOCROT_TARGET)
                .addProperty(AnimationProperty.ActionAnimationProperty.CANCELABLE_MOVE, false);
        DEADLYBACKFLIP_SECOND = new AttackAnimation(0.1F, 0.15F, 1.36F, 1.82F, 1.80F, null, biped.toolR, "biped/skill/rapier_backflip_second", biped)
                .addProperty(AttackAnimationProperty.BASIS_ATTACK_SPEED, 1.8F)
                .addProperty(AnimationProperty.AttackPhaseProperty.STUN_TYPE, StunType.KNOCKDOWN)
                .addProperty(AnimationProperty.AttackPhaseProperty.HIT_SOUND, RapierSounds.RAPIER_SKILL.get())
                .addProperty(AnimationProperty.AttackPhaseProperty.PARTICLE, EpicFightParticles.BLADE_RUSH_SKILL)
                .addProperty(AnimationProperty.ActionAnimationProperty.COORD_SET_TICK, MoveCoordFunctions.TRACE_LOC_TARGET)
                .addProperty(AnimationProperty.ActionAnimationProperty.CANCELABLE_MOVE, false)
                .addState(EntityState.MOVEMENT_LOCKED, true);
        DEADLYBACKFLIP_SECOND_ENDER = new BasicAttackAnimation(0.1F, "biped/skill/rapier_backflip_second_ender", biped,
                new AttackAnimation.Phase(0.0F, 0.2F, 0.8F, 1.12F, 1.12F, 1.12F, biped.toolR, null)
                        .addProperty(AnimationProperty.AttackPhaseProperty.STUN_TYPE, StunType.NONE),
                new AttackAnimation.Phase(1.12F, 0.2F, 1.12F, 1.46F, 1.5F, 1.5F, biped.toolR, null)
                        .addProperty(AnimationProperty.AttackPhaseProperty.STUN_TYPE, StunType.LONG),
                new AttackAnimation.Phase(1.5F, 0.2F, 1.8F, 2.18F, 2.2F, 2.2F, biped.toolR, null)
                        .addProperty(AnimationProperty.AttackPhaseProperty.STUN_TYPE, StunType.KNOCKDOWN)
                        .addProperty(AnimationProperty.AttackPhaseProperty.PARTICLE, EpicFightParticles.BLADE_RUSH_SKILL)
                        .addProperty(AnimationProperty.AttackPhaseProperty.ARMOR_NEGATION_MODIFIER, ValueModifier.setter(32)))
                .addProperty(AnimationProperty.AttackPhaseProperty.HIT_SOUND, RapierSounds.RAPIER_SKILL.get())
                .addProperty(AttackAnimationProperty.BASIS_ATTACK_SPEED, 2.1F)
                .addProperty(AttackAnimationProperty.FIXED_MOVE_DISTANCE, true)
                .addProperty(AnimationProperty.ActionAnimationProperty.COORD_SET_BEGIN, RapierMoveCoordFunctions.TRACE_LOCROT_TARGET)
                .addProperty(AnimationProperty.ActionAnimationProperty.NO_GRAVITY_TIME, TimePairList.create(0.0F, 2.2F))
                .addProperty(AnimationProperty.ActionAnimationProperty.MOVE_VERTICAL, true)
                .addProperty(AnimationProperty.ActionAnimationProperty.CANCELABLE_MOVE, false)
                .addEvents(AnimationEvent.TimeStampedEvent.create(0.28F, ReusableEvents.ENDER_PARTICLES_DEADLYBACKFLIP, AnimationEvent.Side.CLIENT))
                .addState(EntityState.MOVEMENT_LOCKED, true);
        DEADLYBACKFLIP_SECOND_OCEAN = new AttackAnimation(0.1F, 0.15F, 0.86F, 1.28F, 1.32F, null, biped.toolR, "biped/skill/rapier_backflip_second_ocean", biped)
                .addProperty(AttackAnimationProperty.BASIS_ATTACK_SPEED, 1.8F)
                .addProperty(AttackAnimationProperty.FIXED_MOVE_DISTANCE, true)
                .addProperty(AnimationProperty.AttackPhaseProperty.STUN_TYPE, StunType.KNOCKDOWN)
                .addProperty(AnimationProperty.AttackPhaseProperty.HIT_SOUND, RapierSounds.RAPIER_SKILL.get())
                .addProperty(AnimationProperty.AttackPhaseProperty.PARTICLE, EpicFightParticles.BLADE_RUSH_SKILL)
                .addProperty(AnimationProperty.ActionAnimationProperty.COORD_SET_TICK, RapierMoveCoordFunctions.TRACE_LOC_TARGET)
                .addProperty(AnimationProperty.ActionAnimationProperty.NO_GRAVITY_TIME, TimePairList.create(0.0F, 1.2F))
                .addProperty(AnimationProperty.ActionAnimationProperty.MOVE_VERTICAL, true)
                .addProperty(AnimationProperty.ActionAnimationProperty.CANCELABLE_MOVE, false)
                .addEvents(AnimationEvent.TimeStampedEvent.create(0.24F, ReusableEvents.OCEAN_PARTICLES_DEADLYBACKFLIP, AnimationEvent.Side.CLIENT))
                .addState(EntityState.MOVEMENT_LOCKED, true);
        DEADLYBACKFLIP_SECOND_WITHER = new BasicAttackAnimation(0.1F, "biped/skill/rapier_backflip_second_wither", biped,
                new AttackAnimation.Phase(0.0F, 0.2F, 1.86F, 2.10F, 2.12F, 2.12F, biped.toolR, null)
                        .addProperty(AnimationProperty.AttackPhaseProperty.STUN_TYPE, StunType.SHORT)
                        .addProperty(AnimationProperty.AttackPhaseProperty.DAMAGE_MODIFIER, ValueModifier.setter(3))
                        .addProperty(AnimationProperty.AttackPhaseProperty.MAX_STRIKES_MODIFIER, ValueModifier.setter(1))
                        .addProperty(AnimationProperty.AttackPhaseProperty.SWING_SOUND, SoundEvents.WITHER_AMBIENT),
                new AttackAnimation.Phase(2.12F, 0.2F, 2.12F, 2.22F, 2.24F, 2.24F, biped.toolR, null)
                        .addProperty(AnimationProperty.AttackPhaseProperty.STUN_TYPE, StunType.NONE)
                        .addProperty(AnimationProperty.AttackPhaseProperty.DAMAGE_MODIFIER, ValueModifier.setter(3))
                        .addProperty(AnimationProperty.AttackPhaseProperty.MAX_STRIKES_MODIFIER, ValueModifier.setter(1))
                        .addProperty(AnimationProperty.AttackPhaseProperty.SWING_SOUND, SoundEvents.WITHER_SHOOT),
                new AttackAnimation.Phase(2.24F, 0.2F, 2.24F, 2.34F, 2.36F, 2.36F, biped.toolR, null)
                        .addProperty(AnimationProperty.AttackPhaseProperty.STUN_TYPE, StunType.SHORT)
                        .addProperty(AnimationProperty.AttackPhaseProperty.DAMAGE_MODIFIER, ValueModifier.setter(3))
                        .addProperty(AnimationProperty.AttackPhaseProperty.MAX_STRIKES_MODIFIER, ValueModifier.setter(1))
                        .addProperty(AnimationProperty.AttackPhaseProperty.SWING_SOUND, SoundEvents.WITHER_SHOOT),
                new AttackAnimation.Phase(2.36F, 0.2F, 2.36F, 2.46F, 2.48F, 2.48F, biped.toolR, null)
                        .addProperty(AnimationProperty.AttackPhaseProperty.STUN_TYPE, StunType.NONE)
                        .addProperty(AnimationProperty.AttackPhaseProperty.DAMAGE_MODIFIER, ValueModifier.setter(3))
                        .addProperty(AnimationProperty.AttackPhaseProperty.MAX_STRIKES_MODIFIER, ValueModifier.setter(1))
                        .addProperty(AnimationProperty.AttackPhaseProperty.SWING_SOUND, SoundEvents.WITHER_SHOOT),
                new AttackAnimation.Phase(2.48F, 0.2F, 2.86F, 3.1F, 3.2F, 3.2F, biped.toolR, null)
                        .addProperty(AnimationProperty.AttackPhaseProperty.STUN_TYPE, StunType.NEUTRALIZE)
                        .addProperty(AnimationProperty.AttackPhaseProperty.DAMAGE_MODIFIER, ValueModifier.setter(12))
                        .addProperty(AnimationProperty.AttackPhaseProperty.ARMOR_NEGATION_MODIFIER, ValueModifier.setter(36))
                        .addProperty(AnimationProperty.AttackPhaseProperty.HIT_SOUND, RapierSounds.RAPIER_SKILL.get())
                        .addProperty(AnimationProperty.AttackPhaseProperty.PARTICLE, EpicFightParticles.BLADE_RUSH_SKILL)
                        .addProperty(AnimationProperty.AttackPhaseProperty.SWING_SOUND, SoundEvents.WITHER_SPAWN))
                .addProperty(AttackAnimationProperty.BASIS_ATTACK_SPEED, 1.8F)
                .addProperty(AnimationProperty.ActionAnimationProperty.COORD_SET_BEGIN, RapierMoveCoordFunctions.TRACE_LOCROT_TARGET)
                .addProperty(AnimationProperty.ActionAnimationProperty.MOVE_VERTICAL, true)
                .addProperty(AnimationProperty.ActionAnimationProperty.CANCELABLE_MOVE, false)
                .addEvents(AnimationEvent.TimeStampedEvent.create(0.86F, ReusableEvents.WITHER_PARTICLES_DEADLYBACKFLIP, AnimationEvent.Side.CLIENT))
                .addState(EntityState.MOVEMENT_LOCKED, true);

        RAPIER_AIR_SLASH_AMETHYST = new AirSlashAnimation(0.1F, 0.38F, 0.56F, 0.56F, null, biped.toolR, "biped/combat/rapier_airslash_amethyst", biped)
                .addProperty(AttackAnimationProperty.BASIS_ATTACK_SPEED, 2.3F)
                .addProperty(AnimationProperty.AttackPhaseProperty.STUN_TYPE, StunType.SHORT)
                .addProperty(AnimationProperty.AttackPhaseProperty.SOURCE_TAG, Set.of(EpicFightDamageType.FINISHER))
                .addProperty(AnimationProperty.AttackPhaseProperty.SWING_SOUND, SoundEvents.AMETHYST_BLOCK_FALL)
                .addProperty(AnimationProperty.ActionAnimationProperty.MOVE_VERTICAL, false)
                .addEvents(AnimationEvent.TimeStampedEvent.create(0.1F, ReusableEvents.AMETHYST_PARTICLES_AIRSLASH, AnimationEvent.Side.CLIENT));
        RAPIER_DASH_AMETHYST = new DashAttackAnimation(0.15F, "biped/combat/rapier_dash_amethyst", biped, new AttackAnimation.Phase(0.0F, 0.5F, 0.78F, 1F, 1F, biped.toolR, null))
                .addProperty(AttackAnimationProperty.BASIS_ATTACK_SPEED, 1.2F)
                .addProperty(AnimationProperty.AttackPhaseProperty.SWING_SOUND, SoundEvents.AMETHYST_CLUSTER_FALL)
                .addProperty(AnimationProperty.ActionAnimationProperty.COORD_SET_TICK, RapierMoveCoordFunctions.TRACE_LOCROT_TARGET)
                .addProperty(AnimationProperty.ActionAnimationProperty.CANCELABLE_MOVE, false)
                .addEvents(AnimationEvent.TimeStampedEvent.create(0.25F, ReusableEvents.AMETHYST_PARTICLES_DASH, AnimationEvent.Side.CLIENT))
                .addState(EntityState.MOVEMENT_LOCKED, true);
        RAPIER_AUTO2_AMETHYST = new BasicAttackAnimation(0.18F, "biped/combat/rapier_auto2_amethyst", biped,
                new AttackAnimation.Phase(0.0F, 0.3F, 0.33F, 0.46F, 0.48F, 0.5F, biped.toolR, null)
                        .addProperty(AnimationProperty.AttackPhaseProperty.DAMAGE_MODIFIER, ValueModifier.multiplier(0.40F))
                        .addProperty(AnimationProperty.AttackPhaseProperty.STUN_TYPE, StunType.NONE),
                new AttackAnimation.Phase(0.5F, 0.6F, 0.66F, 0.72F, 0.72F, 1F, biped.toolR, null)
                        .addProperty(AnimationProperty.AttackPhaseProperty.DAMAGE_MODIFIER, ValueModifier.multiplier(0.70F))
                        .addProperty(AnimationProperty.AttackPhaseProperty.STUN_TYPE, StunType.SHORT))
                .addProperty(AttackAnimationProperty.BASIS_ATTACK_SPEED, 2.1F)
                .addEvents(AnimationEvent.TimeStampedEvent.create(0.32F, ReusableEvents.AMETHYST_PARTICLES_AUTO2, AnimationEvent.Side.CLIENT));
        RAPIER_AUTO3_AMETHYST = new BasicAttackAnimation(0.18F, "biped/combat/rapier_auto3_amethyst", biped,
                new AttackAnimation.Phase(0.0F, 0.3F, 0.25F, 0.42F, 0.5F, 0.5F, biped.toolR, null)
                        .addProperty(AnimationProperty.AttackPhaseProperty.DAMAGE_MODIFIER, ValueModifier.multiplier(0.4F))
                        .addProperty(AnimationProperty.AttackPhaseProperty.STUN_TYPE, StunType.NONE),
                new AttackAnimation.Phase(0.5F, 0.6F, 0.58F, 0.8F, 0.82F, 0.82F, biped.toolR, null)
                        .addProperty(AnimationProperty.AttackPhaseProperty.DAMAGE_MODIFIER, ValueModifier.multiplier(0.7F)))
                .addProperty(AttackAnimationProperty.BASIS_ATTACK_SPEED, 2.1F)
                .addEvents(AnimationEvent.TimeStampedEvent.create(0.075F, ReusableEvents.AMETHYST_PARTICLES_AUTO3, AnimationEvent.Side.CLIENT));
        DEADLYBACKFLIP_SECOND_AMETHYST = new AttackAnimation(0.1F, 0.15F, 0.86F, 1.28F, 1.32F, null, biped.toolR, "biped/skill/rapier_backflip_second_amethyst", biped)
                .addProperty(AttackAnimationProperty.BASIS_ATTACK_SPEED, 1.8F)
                .addProperty(AttackAnimationProperty.FIXED_MOVE_DISTANCE, true)
                .addProperty(AnimationProperty.AttackPhaseProperty.STUN_TYPE, StunType.KNOCKDOWN)
                .addProperty(AnimationProperty.AttackPhaseProperty.HIT_SOUND, RapierSounds.RAPIER_SKILL.get())
                .addProperty(AnimationProperty.AttackPhaseProperty.PARTICLE, EpicFightParticles.BLADE_RUSH_SKILL)
                .addProperty(AnimationProperty.ActionAnimationProperty.COORD_SET_TICK, RapierMoveCoordFunctions.TRACE_LOC_TARGET)
                .addProperty(AnimationProperty.ActionAnimationProperty.NO_GRAVITY_TIME, TimePairList.create(0.0F, 1.2F))
                .addProperty(AnimationProperty.ActionAnimationProperty.MOVE_VERTICAL, true)
                .addProperty(AnimationProperty.ActionAnimationProperty.CANCELABLE_MOVE, false)
                .addEvents(AnimationEvent.TimeStampedEvent.create(0.24F, ReusableEvents.AMETHYST_PARTICLES_DEADLYBACKFLIP, AnimationEvent.Side.CLIENT))
                .addState(EntityState.MOVEMENT_LOCKED, true);
    }

    // Particles and stuff
    public static class ReusableEvents {
        private static final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
        private static final int ENDER_PARTICLE_COUNT = 69;
        private static final int PARTICLE_COUNT = 20;
        private static final int FOLLOW_DURATION = 18;
        private static final int PARTICLE_COUNT_TINY = 12;
        private static final int FOLLOW_DURATION_TINY = 12;
        private static final int SPAWN_INTERVAL = 2;
        private static int tickCounter = 1;
        private static final Map<Entity, Integer> activeParticles = new HashMap<>();
        private static final Map<Entity, Integer> activeParticlesWither = new HashMap<>();
        private static final Map<Entity, Integer> activeParticlesWitherTiny = new HashMap<>();
        private static final Map<Entity, Integer> activeParticlesAmethyst = new HashMap<>();

        //ENDER
        private static final AnimationEvent.AnimationEventConsumer ENDER_PARTICLES_AUTO3 = (entitypatch, self, params) -> {
            Entity entity = entitypatch.getOriginal();
            RandomSource random = entitypatch.getOriginal().getRandom();
            entity.playSound(SoundEvents.FOX_TELEPORT, 1F, 1.2F);
            spawnParticlesEnder(entity, random);
            scheduleParticleSpawn(entity, random, 215);// First Sound
            scheduler.schedule(() -> {
                entity.level().addParticle(
                        EpicFightParticles.ENTITY_AFTER_IMAGE.get(),
                        entity.getX(),
                        entity.getY(),
                        entity.getZ(),
                        Double.longBitsToDouble(entity.getId()),
                        0,
                        0
                );
            }, 52, TimeUnit.MILLISECONDS);

            scheduler.schedule(() -> {
                entity.playSound(SoundEvents.FOX_TELEPORT, 1F, 1.2F); // Second Sound
                scheduler.schedule(() -> {
                    entity.level().addParticle(
                            EpicFightParticles.ENTITY_AFTER_IMAGE.get(),
                            entity.getX(),
                            entity.getY(),
                            entity.getZ(),
                            Double.longBitsToDouble(entity.getId()),
                            0,
                            0
                    );
                }, 120, TimeUnit.MILLISECONDS);
                spawnParticlesEnder(entity, random);
                scheduleParticleSpawn(entity, random, 215);
            }, 169, TimeUnit.MILLISECONDS); //Second Sound Delay
        };
        private static final AnimationEvent.AnimationEventConsumer ENDER_PARTICLES_AIRSLASH = (entitypatch, self, params) -> {
            Entity entity = entitypatch.getOriginal();
            RandomSource random = entitypatch.getOriginal().getRandom();
            entity.playSound(SoundEvents.FOX_TELEPORT, 1F, 1.2F); // First Sound
            scheduler.schedule(() -> {
                entity.level().addParticle(
                        EpicFightParticles.ENTITY_AFTER_IMAGE.get(),
                        entity.getX(),
                        entity.getY(),
                        entity.getZ(),
                        Double.longBitsToDouble(entity.getId()),
                        0,
                        0
                );
            }, 52, TimeUnit.MILLISECONDS);
            spawnParticlesEnder(entity, random);
            scheduleParticleSpawn(entity, random, 215);

            scheduler.schedule(() -> {
                entity.playSound(SoundEvents.FOX_TELEPORT, 1F, 1.2F); // Second Sound
                scheduler.schedule(() -> {
                    entity.level().addParticle(
                            EpicFightParticles.ENTITY_AFTER_IMAGE.get(),
                            entity.getX(),
                            entity.getY(),
                            entity.getZ(),
                            Double.longBitsToDouble(entity.getId()),
                            0,
                            0
                    );
                }, 120, TimeUnit.MILLISECONDS);
                spawnParticlesEnder(entity, random);
                scheduleParticleSpawn(entity, random, 215);
            }, 396, TimeUnit.MILLISECONDS); //Second Sound Delay
        };
        private static final AnimationEvent.AnimationEventConsumer ENDER_PARTICLES_DASH = (entitypatch, self, params) -> {
            Entity entity = entitypatch.getOriginal();
            RandomSource random = entitypatch.getOriginal().getRandom();
            entity.playSound(SoundEvents.FOX_TELEPORT, 1F, 1.2F); // First Sound
            scheduler.schedule(() -> {
                entity.level().addParticle(
                        EpicFightParticles.ENTITY_AFTER_IMAGE.get(),
                        entity.getX(),
                        entity.getY(),
                        entity.getZ(),
                        Double.longBitsToDouble(entity.getId()),
                        0,
                        0
                );
            }, 56, TimeUnit.MILLISECONDS);
            spawnParticlesEnder(entity, random);
            scheduleParticleSpawn(entity, random, 215);
        };
        private static final AnimationEvent.AnimationEventConsumer ENDER_PARTICLES_DEADLYBACKFLIP = (entitypatch, self, params) -> {
            Entity entity = entitypatch.getOriginal();
            RandomSource random = entitypatch.getOriginal().getRandom();
            entity.playSound(SoundEvents.FOX_TELEPORT, 1F, 1.2F); // First Sound
            scheduler.schedule(() -> {
                entity.level().addParticle(
                        EpicFightParticles.ENTITY_AFTER_IMAGE.get(),
                        entity.getX(),
                        entity.getY(),
                        entity.getZ(),
                        Double.longBitsToDouble(entity.getId()),
                        0,
                        0
                );
            }, 142, TimeUnit.MILLISECONDS);
            spawnParticlesEnder(entity, random);
            scheduleParticleSpawn(entity, random, 260);

            scheduler.schedule(() -> {
                entity.playSound(SoundEvents.FOX_TELEPORT, 1F, 1.2F); // Second Sound
                scheduler.schedule(() -> {
                    entity.level().addParticle(
                            EpicFightParticles.ENTITY_AFTER_IMAGE.get(),
                            entity.getX(),
                            entity.getY(),
                            entity.getZ(),
                            Double.longBitsToDouble(entity.getId()),
                            0,
                            0
                    );
                }, 96, TimeUnit.MILLISECONDS);
                spawnParticlesEnder(entity, random);
                scheduleParticleSpawn(entity, random, 215);

                scheduler.schedule(() -> {
                    entity.playSound(SoundEvents.FOX_TELEPORT, 1F, 1.2F); // Third Sound
                    scheduler.schedule(() -> {
                        entity.level().addParticle(
                                EpicFightParticles.ENTITY_AFTER_IMAGE.get(),
                                entity.getX(),
                                entity.getY(),
                                entity.getZ(),
                                Double.longBitsToDouble(entity.getId()),
                                0,
                                0
                        );
                    }, 116, TimeUnit.MILLISECONDS);
                    spawnParticlesEnder(entity, random);
                    scheduleParticleSpawn(entity, random, 215);
                    if (entity instanceof LivingEntity livingEntity) {
                        float originalFallDistance = livingEntity.fallDistance;
                        livingEntity.fallDistance = 0;
                        scheduler.schedule(() -> livingEntity.fallDistance = originalFallDistance, 500, TimeUnit.MILLISECONDS);
                    }
                }, 260, TimeUnit.MILLISECONDS); // Third Sound Delay
            }, 580, TimeUnit.MILLISECONDS); //Second sound Delay
        };

        //OCEAN
        private static final AnimationEvent.AnimationEventConsumer OCEAN_PARTICLES_STABS = (entitypatch, self, params) -> {
            Entity entity = entitypatch.getOriginal();
            Entity playerEntity = Minecraft.getInstance().player;
            RandomSource random = entitypatch.getOriginal().getRandom();
            spawnOceanParticlesFollowingPlayer_Tiny(playerEntity);
        };
        private static final AnimationEvent.AnimationEventConsumer OCEAN_PARTICLES_DEADLYBACKFLIP = (entitypatch, self, params) -> {
            Entity entity = entitypatch.getOriginal();
            Entity playerEntity = Minecraft.getInstance().player;
            RandomSource random = entitypatch.getOriginal().getRandom();
            entity.playSound(RapierSounds.RAPIER_OCEAN_JUMP.get()); // First Sound
            spawnOceanParticlesFollowingPlayer(playerEntity);
            scheduler.schedule(() -> {
                entity.playSound(RapierSounds.RAPIER_OCEAN_WAVE.get(), 1F, 1.2F); // Second Sound
                spawnOceanParticlesFollowingPlayer_Tiny(playerEntity);
                if (entity instanceof LivingEntity livingEntity) {
                    float originalFallDistance = livingEntity.fallDistance;
                    livingEntity.fallDistance = 0;
                    scheduler.schedule(() -> livingEntity.fallDistance = originalFallDistance, 500,TimeUnit.MILLISECONDS);
                }
            }, 468, TimeUnit.MILLISECONDS); //Second Sound Delay
        };

        //WITHER
        private static final AnimationEvent.AnimationEventConsumer WITHER_PARTICLES_AUTO3 = (entitypatch, self, params) -> {
            Entity entity = entitypatch.getOriginal();
            Entity playerEntity = Minecraft.getInstance().player;
            RandomSource random = entitypatch.getOriginal().getRandom();
            spawnWitherParticlesFollowingPlayer_Tiny(playerEntity);
            scheduler.schedule(() -> {
                spawnWitherParticlesFollowingPlayer_Tiny(playerEntity);
            }, 50, TimeUnit.MILLISECONDS);
        };
        private static final AnimationEvent.AnimationEventConsumer WITHER_PARTICLES_AIRSLASH = (entitypatch, self, params) -> {
            Entity entity = entitypatch.getOriginal();
            Entity playerEntity = Minecraft.getInstance().player;
            RandomSource random = entitypatch.getOriginal().getRandom();
            spawnWitherParticlesFollowingPlayer_Tiny(playerEntity);
        };
        private static final AnimationEvent.AnimationEventConsumer WITHER_PARTICLES_DASH = (entitypatch, self, params) -> {
            Entity entity = entitypatch.getOriginal();
            Entity playerEntity = Minecraft.getInstance().player;
            RandomSource random = entitypatch.getOriginal().getRandom();
            spawnWitherParticlesFollowingPlayer_Tiny(playerEntity);
        };
        private static final AnimationEvent.AnimationEventConsumer WITHER_PARTICLES_DEADLYBACKFLIP = (entitypatch, self, params) -> {
            Entity entity = entitypatch.getOriginal();
            Entity playerEntity = Minecraft.getInstance().player;
            RandomSource random = entitypatch.getOriginal().getRandom();
            entity.playSound(SoundEvents.WITHER_AMBIENT); // First Sound
            spawnWitherParticlesFollowingPlayer(playerEntity);
            spawnWitherParticlesFollowingPlayer(playerEntity);
            scheduler.schedule(() -> {
                entity.playSound(SoundEvents.WITHER_HURT, 1F, 1.2F); // Second Sound
                spawnWitherParticlesFollowingPlayer(playerEntity);
            }, 320, TimeUnit.MILLISECONDS); //Second Sound Delay
        };

        //AMETHYST
        private static final AnimationEvent.AnimationEventConsumer AMETHYST_PARTICLES_AUTO2 = (entitypatch, self, params) -> {
            Entity entity = entitypatch.getOriginal();
            Entity playerEntity = Minecraft.getInstance().player;
            RandomSource random = entitypatch.getOriginal().getRandom();
            spawnAmethystParticlesFollowingPlayer_Tiny(playerEntity);
            scheduler.schedule(() -> {
                spawnAmethystParticlesFollowingPlayer_Tiny(playerEntity);
            }, 50, TimeUnit.MILLISECONDS);
        };
        private static final AnimationEvent.AnimationEventConsumer AMETHYST_PARTICLES_AUTO3 = (entitypatch, self, params) -> {
            Entity entity = entitypatch.getOriginal();
            Entity playerEntity = Minecraft.getInstance().player;
            RandomSource random = entitypatch.getOriginal().getRandom();
            entity.playSound(EpicFightSounds.WHOOSH_ROD.get(), 1F, 1.2F);
            spawnAmethystParticlesFollowingPlayer_Tiny(playerEntity);
            scheduler.schedule(() -> {
                entity.level().addParticle(
                        EpicFightParticles.ENTITY_AFTER_IMAGE.get(),
                        entity.getX(),
                        entity.getY(),
                        entity.getZ(),
                        Double.longBitsToDouble(entity.getId()),
                        0,
                        0
                );
            }, 52, TimeUnit.MILLISECONDS);
            scheduler.schedule(() -> {
                entity.playSound(EpicFightSounds.WHOOSH_ROD.get(), 1F, 1.2F); // Second Sound
                scheduler.schedule(() -> {
                    entity.level().addParticle(
                            EpicFightParticles.ENTITY_AFTER_IMAGE.get(),
                            entity.getX(),
                            entity.getY(),
                            entity.getZ(),
                            Double.longBitsToDouble(entity.getId()),
                            0,
                            0
                    );
                }, 120, TimeUnit.MILLISECONDS);
            }, 169, TimeUnit.MILLISECONDS); //Second Sound Delay
        };
        private static final AnimationEvent.AnimationEventConsumer AMETHYST_PARTICLES_AIRSLASH = (entitypatch, self, params) -> {
            Entity entity = entitypatch.getOriginal();
            Entity playerEntity = Minecraft.getInstance().player;
            RandomSource random = entitypatch.getOriginal().getRandom();
            spawnAmethystParticlesFollowingPlayer_Tiny(playerEntity);
        };
        private static final AnimationEvent.AnimationEventConsumer AMETHYST_PARTICLES_DASH = (entitypatch, self, params) -> {
            Entity entity = entitypatch.getOriginal();
            Entity playerEntity = Minecraft.getInstance().player;
            RandomSource random = entitypatch.getOriginal().getRandom();
            entity.playSound(EpicFightSounds.WHOOSH_ROD.get(), 1F, 1.2F); // First Sound
            scheduler.schedule(() -> {
                entity.level().addParticle(
                        EpicFightParticles.ENTITY_AFTER_IMAGE.get(),
                        entity.getX(),
                        entity.getY(),
                        entity.getZ(),
                        Double.longBitsToDouble(entity.getId()),
                        0,
                        0
                );
            }, 56, TimeUnit.MILLISECONDS);
            spawnAmethystParticlesFollowingPlayer_Tiny(playerEntity);
        };
        private static final AnimationEvent.AnimationEventConsumer AMETHYST_PARTICLES_DEADLYBACKFLIP = (entitypatch, self, params) -> {
            Entity entity = entitypatch.getOriginal();
            Entity playerEntity = Minecraft.getInstance().player;
            RandomSource random = entitypatch.getOriginal().getRandom();
            entity.playSound(RapierSounds.RAPIER_OCEAN_JUMP.get()); // First Sound
            spawnAmethystParticlesFollowingPlayer(playerEntity);
            scheduler.schedule(() -> {
                entity.playSound(SoundEvents.AMETHYST_BLOCK_PLACE, 1F, 1.2F); // Second Sound
                spawnOceanParticlesFollowingPlayer_Tiny(playerEntity);
                if (entity instanceof LivingEntity livingEntity) {
                    float originalFallDistance = livingEntity.fallDistance;
                    livingEntity.fallDistance = 0;
                    scheduler.schedule(() -> livingEntity.fallDistance = originalFallDistance, 500,TimeUnit.MILLISECONDS);
                }
            }, 472, TimeUnit.MILLISECONDS); //Second Sound Delay
        };

        private static void spawnParticlesEnder(Entity entity, RandomSource random) {
            ClientLevel clientLevel = Minecraft.getInstance().level;
            if (clientLevel != null) {
                //Particle Radius
                double horizontalRadius = 1.2;
                for (int i = 0; i < ENDER_PARTICLE_COUNT; i++) {
                    double x = (random.nextDouble() - 0.5) * horizontalRadius;
                    double y = entity.getY() + (random.nextDouble() - random.nextDouble()) * 1.5D;
                    double z = (random.nextDouble() - 0.5) * horizontalRadius;
                    //Particle speed, position, and type
                    clientLevel.addParticle(ParticleTypes.PORTAL,
                            entity.getX() + x,
                            y,
                            entity.getZ() + z,
                            0,
                            0.3,
                            0
                    );
                }
            }
        }

        public static void spawnParticlesOcean() {
            ClientLevel clientLevel = Minecraft.getInstance().level;
            if (clientLevel == null) return;

            RandomSource random = RandomSource.create();
            tickCounter++;

            activeParticles.entrySet().removeIf(entry -> {
                Entity entity = entry.getKey();
                int ticksRemaining = entry.getValue();

                if (ticksRemaining <= 0 || !entity.isAlive()) {
                    return true;
                }

                // Generate Particles
                if (tickCounter % SPAWN_INTERVAL == 0) {
                    double horizontalRadius = 1.2;
                    for (int i = 0; i < PARTICLE_COUNT; i++) {
                        double xOffset = (random.nextDouble() - 0.5) * horizontalRadius;
                        double yOffset = (random.nextDouble() - random.nextDouble()) * 1.5D;
                        double zOffset = (random.nextDouble() - 0.5) * horizontalRadius;

                        clientLevel.addParticle(ParticleTypes.FISHING,
                                entity.getX() + xOffset,
                                entity.getY() + yOffset,
                                entity.getZ() + zOffset,
                                0,
                                0,
                                0
                        );
                    }
                }
                entry.setValue(ticksRemaining - 1);
                return false;
            });
        }
        public static void spawnParticlesOceanTiny() {
            ClientLevel clientLevel = Minecraft.getInstance().level;
            if (clientLevel == null) return;

            RandomSource random = RandomSource.create();
            tickCounter++;

            activeParticles.entrySet().removeIf(entry -> {
                Entity entity = entry.getKey();
                int ticksRemaining = entry.getValue();

                if (ticksRemaining <= 0 || !entity.isAlive()) {
                    return true;
                }

                // Generate Particles
                if (tickCounter % SPAWN_INTERVAL == 0) {
                    double horizontalRadius = 1.2;
                    for (int i = 0; i < PARTICLE_COUNT_TINY; i++) {
                        double xOffset = (random.nextDouble() - 0.5) * horizontalRadius;
                        double yOffset = (random.nextDouble() - random.nextDouble()) * 1.5D;
                        double zOffset = (random.nextDouble() - 0.5) * horizontalRadius;

                        clientLevel.addParticle(ParticleTypes.FALLING_WATER,
                                entity.getX() + xOffset,
                                entity.getY() + yOffset,
                                entity.getZ() + zOffset,
                                0,
                                0.5,
                                0
                        );
                    }
                }
                entry.setValue(ticksRemaining - 1);
                return false;
            });
        }
        public static void spawnParticlesWither() {
            ClientLevel clientLevel = Minecraft.getInstance().level;
            if (clientLevel == null) return;

            RandomSource random = RandomSource.create();
            tickCounter++;

            activeParticlesWither.entrySet().removeIf(entry -> {
                Entity entity = entry.getKey();
                int ticksRemaining = entry.getValue();

                if (ticksRemaining <= 0 || !entity.isAlive()) {
                    return true;
                }

                // Generate Particles
                if (tickCounter % 1 == 0) {
                    double horizontalRadius = 1.2;
                    for (int i = 0; i < PARTICLE_COUNT_TINY; i++) {
                        double xOffset = (random.nextDouble() - 0.2) * horizontalRadius;
                        double yOffset = (random.nextDouble() - random.nextDouble()) * 1.8D;
                        double zOffset = (random.nextDouble() - 0.2) * horizontalRadius;
                        double vxOffset = random.nextDouble() * (0.03 - (-0.03)) - 0.03;
                        double vyOffset = 0.066;
                        double vzOffset = random.nextDouble() * (0.03 - (-0.03)) - 0.03;

                        clientLevel.addParticle(ParticleTypes.LARGE_SMOKE,
                                entity.getX() + xOffset,
                                entity.getY() + yOffset,
                                entity.getZ() + zOffset,
                                vxOffset,
                                vyOffset,
                                vzOffset
                        );
                    }
                }
                entry.setValue(ticksRemaining - 1);
                return false;
            });
        }
        public static void spawnParticlesWitherTiny() {
            ClientLevel clientLevel = Minecraft.getInstance().level;
            if (clientLevel == null) return;

            RandomSource random = RandomSource.create();
            tickCounter++;

            activeParticlesWitherTiny.entrySet().removeIf(entry -> {
                Entity entity = entry.getKey();
                int ticksRemaining = entry.getValue();

                if (ticksRemaining <= 0 || !entity.isAlive()) {
                    return true;
                }

                // Generate Particles
                if (tickCounter % 1 == 0) {
                    double horizontalRadius = 1.2;
                    for (int i = 0; i < PARTICLE_COUNT; i++) {
                        double hxOffset = (random.nextDouble() - 0.2) * horizontalRadius;
                        double hyOffset = (random.nextDouble() - random.nextDouble()) * 1.8D;
                        double hzOffset = (random.nextDouble() - 0.2) * horizontalRadius;
                        double vxOffset = random.nextDouble() * (0.06 - (-0.06)) - 0.06;
                        double vyOffset = 0.066;
                        double vzOffset = random.nextDouble() * (0.06 - (-0.06)) - 0.06;

                        clientLevel.addParticle(ParticleTypes.SMOKE,
                                entity.getX() + hxOffset,
                                entity.getY() + hyOffset,
                                entity.getZ() + hzOffset,
                                vxOffset,
                                vyOffset,
                                vzOffset
                        );
                    }
                }
                entry.setValue(ticksRemaining - 1);
                return false;
            });
        }
        public static void spawnParticlesAmethyst() {
            ClientLevel clientLevel = Minecraft.getInstance().level;
            if (clientLevel == null) return;

            RandomSource random = RandomSource.create();
            tickCounter++;

            activeParticlesAmethyst.entrySet().removeIf(entry -> {
                Entity entity = entry.getKey();
                int ticksRemaining = entry.getValue();

                if (ticksRemaining <= 0 || !entity.isAlive()) {
                    return true;
                }

                // Generate Particles
                if (tickCounter % 1 == 0) {
                    double horizontalRadius = 1.2;
                    for (int i = 0; i < PARTICLE_COUNT; i++) {
                        double hxOffset = (random.nextDouble() - 0.2) * horizontalRadius;
                        double hyOffset = (random.nextDouble() - random.nextDouble()) * 1.8D;
                        double hzOffset = (random.nextDouble() - 0.2) * horizontalRadius;
                        double vxOffset = random.nextDouble() * (0.06 - (-0.06)) - 0.06;
                        double vyOffset = 0.066;
                        double vzOffset = random.nextDouble() * (0.06 - (-0.06)) - 0.06;

                        clientLevel.addParticle(ParticleTypes.ENCHANT,
                                entity.getX() + hxOffset,
                                entity.getY() + hyOffset,
                                entity.getZ() + hzOffset,
                                vxOffset,
                                vyOffset,
                                vzOffset
                        );
                    }
                }
                entry.setValue(ticksRemaining - 1);
                return false;
            });
        }

        private static void scheduleParticleSpawn(Entity entity, RandomSource random, long delay) {
            scheduler.schedule(() -> spawnParticlesEnder(entity, random), delay, TimeUnit.MILLISECONDS);
        }
        public static void spawnOceanParticlesFollowingPlayer(Entity entity) {
            if (!activeParticles.containsKey(entity)) {
                activeParticles.put(entity, FOLLOW_DURATION);
            }
        }
        public static void spawnOceanParticlesFollowingPlayer_Tiny(Entity entity) {
            if (!activeParticles.containsKey(entity)) {
                activeParticles.put(entity, FOLLOW_DURATION_TINY);
            }
        }
        public static void spawnWitherParticlesFollowingPlayer(Entity entity) {
            if (!activeParticlesWither.containsKey(entity)) {
                activeParticlesWither.put(entity, FOLLOW_DURATION_TINY);
            }
        }
        public static void spawnWitherParticlesFollowingPlayer_Tiny(Entity entity) {
            if (!activeParticlesWitherTiny.containsKey(entity)) {
                activeParticlesWitherTiny.put(entity, FOLLOW_DURATION_TINY);
            }
        }
        public static void spawnAmethystParticlesFollowingPlayer(Entity entity) {
            if (!activeParticlesAmethyst.containsKey(entity)) {
                activeParticlesAmethyst.put(entity, FOLLOW_DURATION);
            }
        }
        public static void spawnAmethystParticlesFollowingPlayer_Tiny(Entity entity) {
            if (!activeParticlesAmethyst.containsKey(entity)) {
                activeParticlesAmethyst.put(entity, FOLLOW_DURATION_TINY);
            }
        }
    }
}