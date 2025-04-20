package net.yonchi.refm.gameasset;

import java.util.*;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.Particle;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.RandomSource;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import net.yonchi.refm.RapierForEpicfight;

import yesman.epicfight.api.animation.AnimationClip;
import yesman.epicfight.api.animation.AnimationManager;
import yesman.epicfight.api.animation.AnimationManager.AnimationRegistryEvent;
import yesman.epicfight.api.animation.AnimationManager.AnimationAccessor;
import yesman.epicfight.api.animation.property.AnimationEvent;
import yesman.epicfight.api.animation.property.AnimationProperty.*;
import yesman.epicfight.api.animation.property.MoveCoordFunctions;
import yesman.epicfight.api.animation.types.*;
import yesman.epicfight.api.animation.types.AttackAnimation;
import yesman.epicfight.api.utils.HitEntityList;
import yesman.epicfight.api.utils.TimePairList;
import yesman.epicfight.api.utils.math.ValueModifier;
import yesman.epicfight.api.utils.math.Vec3f;
import yesman.epicfight.gameasset.Armatures;
import yesman.epicfight.gameasset.EpicFightSounds;
import yesman.epicfight.particle.EpicFightParticles;
import yesman.epicfight.world.damagesource.EpicFightDamageType;
import yesman.epicfight.world.damagesource.StunType;
import yesman.epicfight.world.effect.EpicFightMobEffects;

import javax.annotation.Nullable;

import static net.yonchi.refm.api.animation.JointTrack.getJointWithTranslation;

public class RapierAnimations {
    public static DirectStaticAnimation EMPTY_ANIMATION = new DirectStaticAnimation() {
        @Override
        public void loadAnimation() {
        }

        @Override
        public AnimationClip getAnimationClip() {
            return AnimationClip.EMPTY_CLIP;
        }
    };

    public static AnimationAccessor<AirSlashAnimation> RAPIER_AIR_SLASH;
    public static AnimationAccessor<AirSlashAnimation> RAPIER_AIR_SLASH_ENDER;
    public static AnimationAccessor<AirSlashAnimation> RAPIER_AIR_SLASH_OCEAN;
    public static AnimationAccessor<AirSlashAnimation> RAPIER_AIR_SLASH_WITHER;
    public static AnimationAccessor<DashAttackAnimation> RAPIER_DASH;
    public static AnimationAccessor<DashAttackAnimation> RAPIER_DASH_ENDER;
    public static AnimationAccessor<DashAttackAnimation> RAPIER_DASH_OCEAN;
    public static AnimationAccessor<DashAttackAnimation> RAPIER_DASH_WITHER;
    public static AnimationAccessor<BasicAttackAnimation> RAPIER_AUTO1;
    public static AnimationAccessor<BasicAttackAnimation> RAPIER_AUTO2;
    public static AnimationAccessor<BasicAttackAnimation> RAPIER_AUTO3;
    public static AnimationAccessor<BasicAttackAnimation> RAPIER_AUTO3_ENDER;
    public static AnimationAccessor<BasicAttackAnimation> RAPIER_AUTO3_OCEAN;
    public static AnimationAccessor<BasicAttackAnimation> RAPIER_AUTO3_WITHER;

    public static AnimationAccessor<StaticAnimation> RAPIER_GUARD;
    public static AnimationAccessor<GuardAnimation> RAPIER_GUARD_HIT;
    public static AnimationAccessor<BasicAttackAnimation> RAPIER_GUARD_PARRY;
    public static AnimationAccessor<BasicAttackAnimation> RAPIER_GUARD_PARRY_ENDER;
    public static AnimationAccessor<BasicAttackAnimation> RAPIER_GUARD_PARRY_OCEAN;
    public static AnimationAccessor<BasicAttackAnimation> RAPIER_GUARD_PARRY_WITHER;
    public static AnimationAccessor<BasicAttackAnimation> RAPIER_GUARD_PARRY_AMETHYST;

    public static AnimationAccessor<StaticAnimation> BIPED_HOLD_RAPIER;
    public static AnimationAccessor<StaticAnimation> BIPED_SNEAK_RAPIER;
    public static AnimationAccessor<MovementAnimation> BIPED_WALK_RAPIER;
    public static AnimationAccessor<MovementAnimation> BIPED_RUN_RAPIER;
    public static AnimationAccessor<MovementAnimation> BIPED_RUN_RAPIER_WITHER;
    public static AnimationAccessor<MovementAnimation> BIPED_SWIM_RAPIER;

    public static AnimationAccessor<AttackAnimation> DEADLYBACKFLIP_FIRST;
    public static AnimationAccessor<AttackAnimation> DEADLYBACKFLIP_SECOND;
    public static AnimationAccessor<AttackAnimation> DEADLYBACKFLIP_SECOND_ENDER;
    public static AnimationAccessor<AttackAnimation> DEADLYBACKFLIP_SECOND_OCEAN;
    public static AnimationAccessor<AttackAnimation> DEADLYBACKFLIP_SECOND_WITHER;

    public static AnimationAccessor<AirSlashAnimation> RAPIER_AIR_SLASH_AMETHYST;
    public static AnimationAccessor<DashAttackAnimation> RAPIER_DASH_AMETHYST;
    public static AnimationAccessor<BasicAttackAnimation> RAPIER_AUTO2_AMETHYST;
    public static AnimationAccessor<BasicAttackAnimation> RAPIER_AUTO3_AMETHYST;
    public static AnimationAccessor<AttackAnimation> DEADLYBACKFLIP_SECOND_AMETHYST;

    @SubscribeEvent
    public static void registerAnimations(AnimationRegistryEvent event) {
        event.newBuilder(RapierForEpicfight.MOD_ID, RapierAnimations::build);
    }

    public static void build(AnimationManager.AnimationBuilder builder) {
        //Every animation defined with his own hitbox timers and Properties
        BIPED_HOLD_RAPIER = builder.nextAccessor("biped/living/hold_rapier", (accessor) -> new StaticAnimation(true, accessor, Armatures.BIPED));
        BIPED_SNEAK_RAPIER = builder.nextAccessor("biped/living/sneak_rapier", (accessor) -> new StaticAnimation(true, accessor, Armatures.BIPED));
        BIPED_WALK_RAPIER = builder.nextAccessor("biped/living/walk_rapier", (accessor) -> new MovementAnimation(true, accessor, Armatures.BIPED));
        BIPED_RUN_RAPIER = builder.nextAccessor("biped/living/run_rapier", (accessor) -> new MovementAnimation(true, accessor, Armatures.BIPED));
        BIPED_RUN_RAPIER_WITHER = builder.nextAccessor("biped/living/run_rapier_wither", (accessor) -> new MovementAnimation(0.26F,true, accessor, Armatures.BIPED));
        BIPED_SWIM_RAPIER = builder.nextAccessor("biped/living/swim_rapier", (accessor) -> new MovementAnimation(0.5F,true, accessor, Armatures.BIPED));

        RAPIER_AIR_SLASH = builder.nextAccessor("biped/combat/rapier_airslash", (accessor) ->
                new AirSlashAnimation(0.12F, 0.2F, 0.5F, 0.5F, null, Armatures.BIPED.get().toolR, accessor, Armatures.BIPED)
                        .addProperty(AttackPhaseProperty.STUN_TYPE, StunType.SHORT)
                        .addProperty(AttackPhaseProperty.IMPACT_MODIFIER, ValueModifier.adder(4))
                        .addProperty(AttackPhaseProperty.SOURCE_TAG, Set.of(EpicFightDamageType.FINISHER))
                        .addProperty(AttackPhaseProperty.SWING_SOUND, RapierSounds.RAPIER_JUMP.get())
                        .addProperty(AttackAnimationProperty.BASIS_ATTACK_SPEED, 2.3F)
                        .addProperty(ActionAnimationProperty.MOVE_VERTICAL, false));
        RAPIER_AIR_SLASH_ENDER = builder.nextAccessor("biped/combat/rapier_airslash_ender", (accessor) ->
                new AirSlashAnimation(0.18F, accessor, Armatures.BIPED,
                        new AttackAnimation.Phase(0.0F, 0.2F, 0.44F, 0.64F, 0.78F, 0.78F, Armatures.BIPED.get().toolR, null)
                                .addProperty(AttackPhaseProperty.STUN_TYPE, StunType.SHORT)
                                .addProperty(AttackPhaseProperty.SOURCE_TAG, Set.of(EpicFightDamageType.FINISHER)),
                        new AttackAnimation.Phase(0.78F, 0.2F, 0.78F, 1.1F, 1.6F, 1.5F, Armatures.BIPED.get().toolR, null)
                                .addProperty(AttackPhaseProperty.STUN_TYPE, StunType.LONG)
                                .addProperty(AttackPhaseProperty.SOURCE_TAG, Set.of(EpicFightDamageType.FINISHER)))
                        .addProperty(AttackPhaseProperty.DAMAGE_MODIFIER, ValueModifier.multiplier(0.6F))
                        .addProperty(AttackPhaseProperty.SWING_SOUND, RapierSounds.RAPIER_JUMP.get())
                        .addProperty(AttackAnimationProperty.BASIS_ATTACK_SPEED, 2.3F)
                        .addProperty(ActionAnimationProperty.CANCELABLE_MOVE, false)
                        .addProperty(ActionAnimationProperty.MOVE_VERTICAL, true)
                        .addEvents(
                                AnimationEvent.InTimeEvent.create(0.24F, ReusableEvents.ENDER_PARTICLES, AnimationEvent.Side.CLIENT),
                                AnimationEvent.InTimeEvent.create(0.66F, ReusableEvents.ENDER_PARTICLES, AnimationEvent.Side.CLIENT)
                        )
        );
        RAPIER_AIR_SLASH_OCEAN = builder.nextAccessor("biped/combat/rapier_airslash_ocean", (accessor) ->
                new AirSlashAnimation(0.12F, 0.38F, 0.56F, 0.56F, null, Armatures.BIPED.get().toolR, accessor, Armatures.BIPED)
                        .addProperty(AttackPhaseProperty.STUN_TYPE, StunType.SHORT)
                        .addProperty(AttackPhaseProperty.IMPACT_MODIFIER, ValueModifier.adder(4))
                        .addProperty(AttackPhaseProperty.SOURCE_TAG, Set.of(EpicFightDamageType.FINISHER))
                        .addProperty(AttackPhaseProperty.SWING_SOUND, RapierSounds.RAPIER_JUMP.get())
                        .addProperty(AttackAnimationProperty.BASIS_ATTACK_SPEED, 2.3F)
                        .addProperty(ActionAnimationProperty.MOVE_VERTICAL, false)
                        .addEvents(AnimationEvent.InTimeEvent.create(0.1F, ReusableEvents.OCEAN_PARTICLES, AnimationEvent.Side.CLIENT))
        );
        RAPIER_AIR_SLASH_WITHER = builder.nextAccessor("biped/combat/rapier_airslash_wither", (accessor) ->
                new AirSlashAnimation(0.12F, 0.3F, 0.49F, 0.51F, null, Armatures.BIPED.get().toolR, accessor, Armatures.BIPED)
                        .addProperty(AttackPhaseProperty.STUN_TYPE, StunType.SHORT)
                        .addProperty(AttackPhaseProperty.IMPACT_MODIFIER, ValueModifier.adder(4))
                        .addProperty(AttackPhaseProperty.SOURCE_TAG, Set.of(EpicFightDamageType.FINISHER))
                        .addProperty(AttackPhaseProperty.SWING_SOUND, SoundEvents.WITHER_SHOOT)
                        .addProperty(AttackAnimationProperty.BASIS_ATTACK_SPEED, 2.8F)
                        .addProperty(ActionAnimationProperty.MOVE_VERTICAL, false)
                        .addEvents(AnimationEvent.InTimeEvent.create(0.09F, ReusableEvents.WITHER_PARTICLES_TINY, AnimationEvent.Side.CLIENT))
        );
        RAPIER_DASH = builder.nextAccessor("biped/combat/rapier_dash", (accessor) ->
                new DashAttackAnimation(0.15F, accessor, Armatures.BIPED, new AttackAnimation.Phase(0.0F, 0.42F, 0.69F, 0.8F, 1F, Armatures.BIPED.get().toolR, RapierColliderPreset.RAPIER_DASH))
                .addProperty(AttackPhaseProperty.SWING_SOUND, RapierSounds.RAPIER_SWING.get())
                .addProperty(AttackPhaseProperty.IMPACT_MODIFIER, ValueModifier.adder(1))
                .addProperty(AttackPhaseProperty.MAX_STRIKES_MODIFIER, ValueModifier.adder(1))
                .addProperty(AttackAnimationProperty.BASIS_ATTACK_SPEED, 1.2F)
                .addProperty(ActionAnimationProperty.CANCELABLE_MOVE, true)
                .addProperty(ActionAnimationProperty.AFFECT_SPEED, true)
                .addProperty(StaticAnimationProperty.FIXED_HEAD_ROTATION, false));
        RAPIER_DASH_ENDER = builder.nextAccessor("biped/combat/rapier_dash_ender", (accessor) ->
                new DashAttackAnimation(0.15F, accessor, Armatures.BIPED, new AttackAnimation.Phase(0.0F, 0.5F, 0.78F, 1F, 1F, Armatures.BIPED.get().toolR, RapierColliderPreset.RAPIER_DASH))
                .addProperty(AttackPhaseProperty.SWING_SOUND, RapierSounds.RAPIER_SWING.get())
                .addProperty(AttackPhaseProperty.IMPACT_MODIFIER, ValueModifier.adder(1))
                .addProperty(AttackPhaseProperty.MAX_STRIKES_MODIFIER, ValueModifier.adder(1))
                .addProperty(AttackPhaseProperty.STUN_TYPE, StunType.LONG)
                .addProperty(AttackAnimationProperty.BASIS_ATTACK_SPEED, 1.2F)
                .addProperty(AttackAnimationProperty.FIXED_MOVE_DISTANCE, true)
                .addProperty(ActionAnimationProperty.COORD_SET_BEGIN, MoveCoordFunctions.TRACE_TARGET_DISTANCE)
                .addProperty(ActionAnimationProperty.CANCELABLE_MOVE, true)
                .addEvents(AnimationEvent.InTimeEvent.create(0.31F, ReusableEvents.ENDER_PARTICLES, AnimationEvent.Side.CLIENT))
                .addState(EntityState.MOVEMENT_LOCKED, true));
        RAPIER_DASH_OCEAN = builder.nextAccessor("biped/combat/rapier_dash_ocean", (accessor) ->
                new DashAttackAnimation(0.15F, accessor, Armatures.BIPED, new AttackAnimation.Phase(0.0F, 0.2F, 0.78F, 1.2F, 1.2F, Armatures.BIPED.get().toolR, RapierColliderPreset.RAPIER_DASH))
                .addProperty(AttackPhaseProperty.SWING_SOUND, RapierSounds.RAPIER_OCEAN_WAVE.get())
                .addProperty(AttackPhaseProperty.IMPACT_MODIFIER, ValueModifier.adder(1))
                .addProperty(AttackPhaseProperty.MAX_STRIKES_MODIFIER, ValueModifier.adder(4))
                .addProperty(AttackPhaseProperty.STUN_TYPE, StunType.LONG)
                .addProperty(AttackAnimationProperty.BASIS_ATTACK_SPEED, 1.2F)
                .addProperty(AttackAnimationProperty.FIXED_MOVE_DISTANCE, true)
                .addProperty(ActionAnimationProperty.AFFECT_SPEED, true)
                .addProperty(ActionAnimationProperty.CANCELABLE_MOVE, true)
                .addEvents(
                        AnimationEvent.InTimeEvent.create(0.2F, ReusableEvents.OCEAN_PARTICLES, AnimationEvent.Side.CLIENT)
                )
                .addEvents(AnimationEvent.InPeriodEvent.create(0.18F, 0.82F, (entitypatch, self, params) -> {
                    ((LivingEntity)entitypatch.getOriginal()).isInWater();
                    if (entitypatch.getOriginal() instanceof Player) {
                        Player player = (Player)entitypatch.getOriginal();
                        int numParticles = 2;
                        for (int i = 0; i < numParticles; i++) {
                            Vec3 pos = getJointWithTranslation(Minecraft.getInstance().player, player, new Vec3f(0, 0, 0), Armatures.BIPED.get().torso);
                            if (pos != null) {
                                Random random = new Random();
                                double angle = random.nextDouble() * Math.PI * 2;
                                double phi = Math.acos(2 * random.nextDouble() - 1);
                                double speed = 0.82;
                                double vx = speed * Math.sin(phi) * Math.cos(angle);
                                double vy = speed * Math.sin(phi) * Math.sin(angle);
                                double vz = Math.cos(phi) * speed;

                                Particle particle = Minecraft.getInstance().particleEngine.createParticle(
                                        ParticleTypes.BUBBLE, pos.x, pos.y, pos.z, vx, vy, vz
                                );
                                if (particle != null) {
                                    particle.scale(0.92f);
                                    particle.setLifetime(9);
                                }
                            }
                        }
                    }
                }, AnimationEvent.Side.CLIENT))
                .addProperty(StaticAnimationProperty.FIXED_HEAD_ROTATION, false));
        RAPIER_DASH_WITHER = builder.nextAccessor("biped/combat/rapier_dash_wither", (accessor) ->
                new DashAttackAnimation(0.15F, accessor, Armatures.BIPED, new AttackAnimation.Phase(0.0F, 0.56F, 0.72F, 0.96F, 1F, Armatures.BIPED.get().toolR, null))
                .addProperty(AttackPhaseProperty.SWING_SOUND, SoundEvents.WITHER_HURT)
                .addProperty(AttackPhaseProperty.HIT_SOUND, RapierSounds.RAPIER_HIT.get())
                .addProperty(AttackPhaseProperty.IMPACT_MODIFIER, ValueModifier.adder(1))
                .addProperty(AttackPhaseProperty.MAX_STRIKES_MODIFIER, ValueModifier.adder(2))
                .addProperty(AttackPhaseProperty.STUN_TYPE, StunType.LONG)
                .addProperty(AttackAnimationProperty.BASIS_ATTACK_SPEED, 1.2F)
                .addProperty(ActionAnimationProperty.COORD_SET_BEGIN, MoveCoordFunctions.TRACE_TARGET_LOCATION_ROTATION)
                .addProperty(ActionAnimationProperty.NO_GRAVITY_TIME, TimePairList.create(0.15F, 0.22F))
                .addProperty(ActionAnimationProperty.AFFECT_SPEED, true)
                .addProperty(ActionAnimationProperty.CANCELABLE_MOVE, true)
                .addEvents(
                        AnimationEvent.InTimeEvent.create(0.09F, ReusableEvents.WITHER_PARTICLES_TINY, AnimationEvent.Side.CLIENT),
                        AnimationEvent.InTimeEvent.create(0.18F, ReusableEvents.WITHER_PARTICLES_INSTANT, AnimationEvent.Side.CLIENT),
                        AnimationEvent.InTimeEvent.create(0.36F, ReusableEvents.WITHER_PARTICLES_INSTANT, AnimationEvent.Side.CLIENT)
                ));

        RAPIER_AUTO1 = builder.nextAccessor("biped/combat/rapier_auto1", (accessor) ->
                new BasicAttackAnimation(0.12F, 0.3F, 0.5F, 0.72F, 0.52F, null, Armatures.BIPED.get().toolR, accessor, Armatures.BIPED)
                        .addProperty(AttackAnimationProperty.BASIS_ATTACK_SPEED, 1.8F)
                        .addProperty(ActionAnimationProperty.CANCELABLE_MOVE, true));
        RAPIER_AUTO2 = builder.nextAccessor("biped/combat/rapier_auto2", (accessor) ->
                new BasicAttackAnimation(0.12F, 0.3F, 0.38F, 0.48F, 0.54F, null, Armatures.BIPED.get().toolR, accessor, Armatures.BIPED)
                        .addProperty(AttackAnimationProperty.BASIS_ATTACK_SPEED, 1.8F)
                        .addProperty(ActionAnimationProperty.CANCELABLE_MOVE, true));
        RAPIER_AUTO3 = builder.nextAccessor("biped/combat/rapier_auto3", (accessor) ->
                new BasicAttackAnimation(0.18F, accessor, Armatures.BIPED,
                        new AttackAnimation.Phase(0.0F, 0.3F, 0.25F, 0.42F, 0.5F, 0.5F, Armatures.BIPED.get().toolR, null)
                                .addProperty(AttackPhaseProperty.DAMAGE_MODIFIER, ValueModifier.multiplier(0.5F))
                                .addProperty(AttackPhaseProperty.STUN_TYPE, StunType.NONE),
                        new AttackAnimation.Phase(0.5F, 0.6F, 0.58F, 0.8F, 0.82F, 0.82F, Armatures.BIPED.get().toolR, null)
                                .addProperty(AttackPhaseProperty.DAMAGE_MODIFIER, ValueModifier.multiplier(0.75F)))
                        .addProperty(AttackAnimationProperty.BASIS_ATTACK_SPEED, 2.1F));
        RAPIER_AUTO3_ENDER = builder.nextAccessor("biped/combat/rapier_auto3_ender", (accessor) ->
                new BasicAttackAnimation(0.18F, accessor, Armatures.BIPED,
                        new AttackAnimation.Phase(0.0F, 0.3F, 0.25F, 0.42F, 0.5F, 0.5F, Armatures.BIPED.get().toolR, null)
                                .addProperty(AttackPhaseProperty.DAMAGE_MODIFIER, ValueModifier.multiplier(0.5F))
                                .addProperty(AttackPhaseProperty.STUN_TYPE, StunType.NONE),
                        new AttackAnimation.Phase(0.5F, 0.6F, 0.58F, 0.8F, 0.82F, 0.82F, Armatures.BIPED.get().toolR, null)
                                .addProperty(AttackPhaseProperty.DAMAGE_MODIFIER, ValueModifier.multiplier(0.75F)))
                        .addProperty(AttackAnimationProperty.BASIS_ATTACK_SPEED, 2.1F)
                        .addEvents(
                                AnimationEvent.InTimeEvent.create(0.08F, ReusableEvents.ENDER_PARTICLES, AnimationEvent.Side.CLIENT),
                                AnimationEvent.InTimeEvent.create(0.36F, ReusableEvents.ENDER_PARTICLES, AnimationEvent.Side.CLIENT)
                        ));
        RAPIER_AUTO3_OCEAN = builder.nextAccessor("biped/combat/rapier_auto3_ocean", (accessor) ->
                new BasicAttackAnimation(0.18F, accessor, Armatures.BIPED,
                        new AttackAnimation.Phase(0.0F, 0.3F, 0.28F, 0.52F, 0.6F, 0.6F, Armatures.BIPED.get().toolR, null)
                                .addProperty(AttackPhaseProperty.DAMAGE_MODIFIER, ValueModifier.multiplier(0.5F))
                                .addProperty(AttackPhaseProperty.STUN_TYPE, StunType.NONE),
                        new AttackAnimation.Phase(0.6F, 0.6F, 0.86F, 1.1F, 1.1F, 1.1F, Armatures.BIPED.get().toolR, null)
                                .addProperty(AttackPhaseProperty.DAMAGE_MODIFIER, ValueModifier.multiplier(0.75F)))
                        .addProperty(AttackAnimationProperty.BASIS_ATTACK_SPEED, 2.1F)
                        .addEvents(
                                AnimationEvent.InTimeEvent.create(0.46F, ReusableEvents.OCEAN_PARTICLES, AnimationEvent.Side.CLIENT)
                        ));
        RAPIER_AUTO3_WITHER = builder.nextAccessor("biped/combat/rapier_auto3_wither", (accessor) ->
                new BasicAttackAnimation(0.18F, accessor, Armatures.BIPED,
                        new AttackAnimation.Phase(0.0F, 0.3F, 0.33F, 0.46F, 0.48F, 0.5F, Armatures.BIPED.get().toolR, null)
                                .addProperty(AttackPhaseProperty.DAMAGE_MODIFIER, ValueModifier.multiplier(0.33F))
                                .addProperty(AttackPhaseProperty.SWING_SOUND, SoundEvents.WITHER_SHOOT)
                                .addProperty(AttackPhaseProperty.STUN_TYPE, StunType.NONE),
                        new AttackAnimation.Phase(0.5F, 0.6F, 0.66F, 0.72F, 0.72F, 1F, Armatures.BIPED.get().toolR, null)
                                .addProperty(AttackPhaseProperty.DAMAGE_MODIFIER, ValueModifier.multiplier(0.66F))
                                .addProperty(AttackPhaseProperty.SWING_SOUND, SoundEvents.WITHER_SHOOT)
                                .addProperty(AttackPhaseProperty.STUN_TYPE, StunType.SHORT))
                        .addProperty(AttackAnimationProperty.BASIS_ATTACK_SPEED, 2.1F)
                        .addEvents(
                                AnimationEvent.InTimeEvent.create(0.32F, ReusableEvents.WITHER_PARTICLES_TINY, AnimationEvent.Side.CLIENT),
                                AnimationEvent.InTimeEvent.create(0.56F, ReusableEvents.WITHER_PARTICLES_TINY, AnimationEvent.Side.CLIENT)
                        ));

        RAPIER_GUARD = builder.nextAccessor("biped/skill/guard_rapier", (accessor) -> new StaticAnimation(true, accessor, Armatures.BIPED));
        RAPIER_GUARD_HIT = builder.nextAccessor("biped/skill/guard_rapier_hit", (accessor) -> new GuardAnimation(0, accessor, Armatures.BIPED));
        RAPIER_GUARD_PARRY = builder.nextAccessor("biped/skill/guard_rapier_parry", (accessor) ->
                new BasicAttackAnimation(0.12F, 0.42F, 0.56F, 0.6F, null, Armatures.BIPED.get().toolR, accessor, Armatures.BIPED)
                        .addProperty(AttackPhaseProperty.DAMAGE_MODIFIER, ValueModifier.setter(3.8F))
                        .addProperty(AttackPhaseProperty.ARMOR_NEGATION_MODIFIER, ValueModifier.setter(99))
                        .addProperty(AttackPhaseProperty.IMPACT_MODIFIER, ValueModifier.setter(3))
                        .addProperty(AttackPhaseProperty.STUN_TYPE, StunType.LONG)
                        .addProperty(AttackPhaseProperty.PARTICLE, EpicFightParticles.EVISCERATE)
                        .addProperty(AttackAnimationProperty.BASIS_ATTACK_SPEED, 2F)
                        .addProperty(ActionAnimationProperty.CANCELABLE_MOVE, false)
                        .addState(EntityState.MOVEMENT_LOCKED, true));
        RAPIER_GUARD_PARRY_ENDER = builder.nextAccessor("biped/skill/guard_rapier_parry_ender", (accessor) ->
                new BasicAttackAnimation(0.12F, 0.56F, 0.76F, 0.78F, null, Armatures.BIPED.get().toolR, accessor, Armatures.BIPED)
                        .addProperty(AttackPhaseProperty.DAMAGE_MODIFIER, ValueModifier.setter(3.8F))
                        .addProperty(AttackPhaseProperty.ARMOR_NEGATION_MODIFIER, ValueModifier.setter(99))
                        .addProperty(AttackPhaseProperty.IMPACT_MODIFIER, ValueModifier.setter(3))
                        .addProperty(AttackPhaseProperty.STUN_TYPE, StunType.LONG)
                        .addProperty(AttackPhaseProperty.PARTICLE, EpicFightParticles.EVISCERATE)
                        .addProperty(AttackAnimationProperty.BASIS_ATTACK_SPEED, 2F)
                        .addProperty(AttackAnimationProperty.FIXED_MOVE_DISTANCE, true)
                        .addProperty(ActionAnimationProperty.CANCELABLE_MOVE, false)
                        .addProperty(ActionAnimationProperty.NO_GRAVITY_TIME, TimePairList.create(0.2F, 1F))
                        .addEvents(AnimationEvent.InTimeEvent.create(0.28F, ReusableEvents.ENDER_PARTICLES, AnimationEvent.Side.CLIENT))
                        .addState(EntityState.TURNING_LOCKED, true)
                        .addState(EntityState.MOVEMENT_LOCKED, true)
        );
        RAPIER_GUARD_PARRY_OCEAN = builder.nextAccessor("biped/skill/guard_rapier_parry_ocean", (accessor) ->
                new BasicAttackAnimation(0.18F, accessor, Armatures.BIPED,
                        new AttackAnimation.Phase(0.0F, 0.3F, 0.28F, 0.32F, 0.35F, 0.4F, Armatures.BIPED.get().toolR, null)
                                .addProperty(AttackPhaseProperty.DAMAGE_MODIFIER, ValueModifier.setter(0.76F))
                                .addProperty(AttackPhaseProperty.ARMOR_NEGATION_MODIFIER, ValueModifier.setter(99))
                                .addProperty(AttackPhaseProperty.STUN_TYPE, StunType.NONE)
                                .addProperty(AttackPhaseProperty.PARTICLE, EpicFightParticles.EVISCERATE),
                        new AttackAnimation.Phase(0.4F, 0.3F, 0.42F, 0.48F, 0.56F, 0.6F, Armatures.BIPED.get().toolR, null)
                                .addProperty(AttackPhaseProperty.DAMAGE_MODIFIER, ValueModifier.setter(0.76F))
                                .addProperty(AttackPhaseProperty.ARMOR_NEGATION_MODIFIER, ValueModifier.setter(99))
                                .addProperty(AttackPhaseProperty.STUN_TYPE, StunType.SHORT),
                        new AttackAnimation.Phase(0.6F, 0.3F, 0.66F, 0.84F, 0.92F, 1F, Armatures.BIPED.get().toolR, null)
                                .addProperty(AttackPhaseProperty.DAMAGE_MODIFIER, ValueModifier.setter(0.76F))
                                .addProperty(AttackPhaseProperty.ARMOR_NEGATION_MODIFIER, ValueModifier.setter(99))
                                .addProperty(AttackPhaseProperty.STUN_TYPE, StunType.NONE)
                                .addProperty(AttackPhaseProperty.PARTICLE, EpicFightParticles.EVISCERATE),
                        new AttackAnimation.Phase(1F, 0.3F, 1.1F, 1.24F, 1.3F, 1.32F, Armatures.BIPED.get().toolR, null)
                                .addProperty(AttackPhaseProperty.DAMAGE_MODIFIER, ValueModifier.setter(0.76F))
                                .addProperty(AttackPhaseProperty.ARMOR_NEGATION_MODIFIER, ValueModifier.setter(99))
                                .addProperty(AttackPhaseProperty.STUN_TYPE, StunType.NONE),
                        new AttackAnimation.Phase(1.32F, 0.3F, 1.38F, 1.5F, 1.5F, 1.52F, Armatures.BIPED.get().toolR, null)
                                .addProperty(AttackPhaseProperty.DAMAGE_MODIFIER, ValueModifier.setter(0.76F))
                                .addProperty(AttackPhaseProperty.HIT_SOUND, SoundEvents.PLAYER_SPLASH_HIGH_SPEED)
                                .addProperty(AttackPhaseProperty.ARMOR_NEGATION_MODIFIER, ValueModifier.setter(99))
                                .addProperty(AttackPhaseProperty.IMPACT_MODIFIER, ValueModifier.setter(3))
                                .addProperty(AttackPhaseProperty.STUN_TYPE, StunType.LONG)
                                .addProperty(AttackPhaseProperty.PARTICLE, EpicFightParticles.EVISCERATE)
                )
                        .addProperty(AttackAnimationProperty.BASIS_ATTACK_SPEED, 1.56F)
                        .addProperty(ActionAnimationProperty.CANCELABLE_MOVE, false)
                        .addEvents(AnimationEvent.InTimeEvent.create(0.32F, ReusableEvents.OCEAN_PARTICLES_TINY, AnimationEvent.Side.CLIENT))
                        .addState(EntityState.MOVEMENT_LOCKED, true)
        );
        RAPIER_GUARD_PARRY_WITHER = builder.nextAccessor("biped/skill/guard_rapier_parry_wither", (accessor) ->
                new BasicAttackAnimation(0.18F, accessor, Armatures.BIPED,
                        new AttackAnimation.Phase(0.0F, 0.3F, 0.25F, 0.3F, 0.35F, 0.4F, Armatures.BIPED.get().rootJoint, RapierColliderPreset.SCREAM)
                                .addProperty(AttackPhaseProperty.DAMAGE_MODIFIER, ValueModifier.setter(0.5F))
                                .addProperty(AttackPhaseProperty.MAX_STRIKES_MODIFIER, ValueModifier.setter(33))
                                .addProperty(AttackPhaseProperty.SWING_SOUND, SoundEvents.WITHER_DEATH)
                                .addProperty(AttackPhaseProperty.HIT_SOUND, EpicFightSounds.NO_SOUND.get())
                                .addProperty(AttackPhaseProperty.STUN_TYPE, StunType.SHORT)
                                .addProperty(AttackPhaseProperty.PARTICLE, EpicFightParticles.EVISCERATE),
                        new AttackAnimation.Phase(0.4F, 0.3F, 0.5F, 0.65F, 0.7F, 0.75F, Armatures.BIPED.get().rootJoint, RapierColliderPreset.SCREAM)
                                .addProperty(AttackPhaseProperty.DAMAGE_MODIFIER, ValueModifier.setter(0.5F))
                                .addProperty(AttackPhaseProperty.MAX_STRIKES_MODIFIER, ValueModifier.setter(33))
                                .addProperty(AttackPhaseProperty.SWING_SOUND, EpicFightSounds.NO_SOUND.get())
                                .addProperty(AttackPhaseProperty.HIT_SOUND, EpicFightSounds.NO_SOUND.get())
                                .addProperty(AttackPhaseProperty.STUN_TYPE, StunType.NONE)
                                .addProperty(AttackPhaseProperty.PARTICLE, EpicFightParticles.EVISCERATE),
                        new AttackAnimation.Phase(0.75F, 0.3F, 0.9F, 1F, 1.05F, 1.1F, Armatures.BIPED.get().rootJoint, RapierColliderPreset.SCREAM)
                                .addProperty(AttackPhaseProperty.DAMAGE_MODIFIER, ValueModifier.setter(0.5F))
                                .addProperty(AttackPhaseProperty.MAX_STRIKES_MODIFIER, ValueModifier.setter(33))
                                .addProperty(AttackPhaseProperty.SWING_SOUND, EpicFightSounds.NO_SOUND.get())
                                .addProperty(AttackPhaseProperty.HIT_SOUND, EpicFightSounds.NO_SOUND.get())
                                .addProperty(AttackPhaseProperty.STUN_TYPE, StunType.NONE)
                                .addProperty(AttackPhaseProperty.PARTICLE, EpicFightParticles.EVISCERATE),
                        new AttackAnimation.Phase(1.1F, 0.3F, 1.25F, 1.4F, 1.45F, 1.5F, Armatures.BIPED.get().rootJoint, RapierColliderPreset.SCREAM)
                                .addProperty(AttackPhaseProperty.DAMAGE_MODIFIER, ValueModifier.setter(1.6F))
                                .addProperty(AttackPhaseProperty.MAX_STRIKES_MODIFIER, ValueModifier.setter(33))
                                .addProperty(AttackPhaseProperty.ARMOR_NEGATION_MODIFIER, ValueModifier.setter(99))
                                .addProperty(AttackPhaseProperty.IMPACT_MODIFIER, ValueModifier.setter(3))
                                .addProperty(AttackPhaseProperty.SWING_SOUND, EpicFightSounds.NO_SOUND.get())
                                .addProperty(AttackPhaseProperty.HIT_SOUND, SoundEvents.WITHER_HURT)
                                .addProperty(AttackPhaseProperty.STUN_TYPE, StunType.LONG)
                                .addProperty(AttackPhaseProperty.PARTICLE, EpicFightParticles.EVISCERATE)
                )
                        .addProperty(AttackAnimationProperty.BASIS_ATTACK_SPEED, 2F)
                        .addProperty(ActionAnimationProperty.CANCELABLE_MOVE, false)
                        .addEvents(
                                AnimationEvent.InTimeEvent.create(0.12F, (entitypatch, param2, param3) -> {
                                    ((ServerPlayer) entitypatch.getOriginal()).addEffect(
                                            new MobEffectInstance((MobEffect) EpicFightMobEffects.STUN_IMMUNITY.get(), 33, 0, true, false, false)
                                    );
                                }, AnimationEvent.Side.SERVER),
                                AnimationEvent.InTimeEvent.create(0.24F, ReusableEvents.WITHER_PARTICLES_BIG, AnimationEvent.Side.CLIENT),
                                AnimationEvent.InTimeEvent.create(0.26F, ReusableEvents.WITHER_PARTICLES, AnimationEvent.Side.CLIENT),
                                AnimationEvent.InTimeEvent.create(0.26F, (entitypatch, param2, param3) -> {
                                    ((ServerPlayer) entitypatch.getOriginal()).addEffect(
                                            new MobEffectInstance((MobEffect) MobEffects.DARKNESS, 48, 1, true, false, false)
                                    );
                                    ((ServerPlayer) entitypatch.getOriginal()).addEffect(
                                            new MobEffectInstance((MobEffect) MobEffects.MOVEMENT_SLOWDOWN, 42, 2, true, false, false)
                                    );
                                }, AnimationEvent.Side.SERVER)
                        )
                        .addState(EntityState.MOVEMENT_LOCKED, true)
        );

        DEADLYBACKFLIP_FIRST = builder.nextAccessor("biped/skill/rapier_backflip_first", (accessor) ->
                new AttackAnimation(0.1F, 0.2F, 0.25F, 2.8F, 3F, RapierColliderPreset.KICK, Armatures.BIPED.get().thighL, accessor, Armatures.BIPED)
                        .addProperty(AttackPhaseProperty.HIT_PRIORITY, HitEntityList.Priority.TARGET)
                        .addProperty(AttackPhaseProperty.SWING_SOUND, EpicFightSounds.WHOOSH_BIG.get())
                        .addProperty(AttackPhaseProperty.HIT_SOUND, EpicFightSounds.BLUNT_HIT.get())
                        .addProperty(AttackAnimationProperty.BASIS_ATTACK_SPEED, 2.4F)
                        .addProperty(ActionAnimationProperty.DEST_LOCATION_PROVIDER, MoveCoordFunctions.ATTACK_TARGET_LOCATION)
                        .addProperty(ActionAnimationProperty.COORD_SET_BEGIN, MoveCoordFunctions.TRACE_ORIGIN_AS_DESTINATION)
                        .addProperty(ActionAnimationProperty.COORD_SET_TICK, MoveCoordFunctions.TRACE_TARGET_DISTANCE)
                        .addProperty(ActionAnimationProperty.NO_GRAVITY_TIME, TimePairList.create(0.08F, 0.8F))
                        .addProperty(ActionAnimationProperty.CANCELABLE_MOVE, false)
                        .addEvents(
                                AnimationEvent.InPeriodEvent.create(0.0F, 0.32F, (entitypatch, self, params) -> {
                                    ((LivingEntity) entitypatch.getOriginal()).resetFallDistance();
                                    if (entitypatch.getOriginal() instanceof Player) {
                                        Player player = (Player) entitypatch.getOriginal();
                                        player.yCloak = 0.0;
                                        player.yCloakO = 0.0;
                                    }
                                }, AnimationEvent.Side.BOTH)
                        )
                        .addState(EntityState.LOCKON_ROTATE, true)
                        .addState(EntityState.MOVEMENT_LOCKED, true)
        );
        DEADLYBACKFLIP_SECOND = builder.nextAccessor("biped/skill/rapier_backflip_second", (accessor) ->
                new AttackAnimation(0.1F, 0.15F, 1.36F, 1.82F, 1.92F, null, Armatures.BIPED.get().toolR, accessor, Armatures.BIPED)
                        .addProperty(AttackPhaseProperty.STUN_TYPE, StunType.KNOCKDOWN)
                        .addProperty(AttackPhaseProperty.HIT_SOUND, RapierSounds.RAPIER_SKILL.get())
                        .addProperty(AttackPhaseProperty.PARTICLE, EpicFightParticles.BLADE_RUSH_SKILL)
                        .addProperty(AttackAnimationProperty.BASIS_ATTACK_SPEED, 2F)
                        .addProperty(ActionAnimationProperty.COORD_SET_TICK, MoveCoordFunctions.TRACE_TARGET_LOCATION_ROTATION)
                        .addProperty(ActionAnimationProperty.NO_GRAVITY_TIME, TimePairList.create(0.0F, 0.5F))
                        .addProperty(ActionAnimationProperty.MOVE_VERTICAL, true)
                        .addProperty(ActionAnimationProperty.CANCELABLE_MOVE, false)
                        .addState(EntityState.MOVEMENT_LOCKED, true)
        );
        DEADLYBACKFLIP_SECOND_ENDER = builder.nextAccessor("biped/skill/rapier_backflip_second_ender", (accessor) ->
                new AttackAnimation(0.1F, accessor, Armatures.BIPED,
                        new AttackAnimation.Phase(0.0F, 0.2F, 1.06F, 1.32F, 1.4F, 1.4F, Armatures.BIPED.get().toolR, null)
                                .addProperty(AttackPhaseProperty.STUN_TYPE, StunType.FALL)
                                .addProperty(AttackPhaseProperty.HIT_SOUND, RapierSounds.RAPIER_HIT.get()),
                        new AttackAnimation.Phase(1.4F, 0.2F, 1.52F, 1.8F, 1.92F, 1.92F, Armatures.BIPED.get().toolR, null)
                                .addProperty(AttackPhaseProperty.STUN_TYPE, StunType.LONG)
                                .addProperty(AttackPhaseProperty.HIT_SOUND, RapierSounds.RAPIER_HIT.get()),
                        new AttackAnimation.Phase(1.92F, 0.2F, 2F, 2.3F, 2.4F, 2.4F, Armatures.BIPED.get().toolR, RapierColliderPreset.RAPIER_DASH)
                                .addProperty(AttackPhaseProperty.STUN_TYPE, StunType.KNOCKDOWN)
                                .addProperty(AttackPhaseProperty.HIT_SOUND, RapierSounds.RAPIER_SKILL.get())
                                .addProperty(AttackPhaseProperty.PARTICLE, EpicFightParticles.BLADE_RUSH_SKILL)
                )
                        .addProperty(AttackAnimationProperty.BASIS_ATTACK_SPEED, 2.1F)
                        .addProperty(AttackAnimationProperty.FIXED_MOVE_DISTANCE, true)
                        .addProperty(ActionAnimationProperty.COORD_SET_BEGIN, MoveCoordFunctions.TRACE_TARGET_LOCATION_ROTATION)
                        .addProperty(ActionAnimationProperty.NO_GRAVITY_TIME, TimePairList.create(0.1F, 2.2F))
                        .addProperty(ActionAnimationProperty.MOVE_VERTICAL, true)
                        .addProperty(ActionAnimationProperty.CANCELABLE_MOVE, false)
                        .addEvents(
                                AnimationEvent.InTimeEvent.create(0.44F, ReusableEvents.ENDER_PARTICLES, AnimationEvent.Side.CLIENT),
                                AnimationEvent.InTimeEvent.create(1.24F, ReusableEvents.ENDER_PARTICLES, AnimationEvent.Side.CLIENT),
                                AnimationEvent.InTimeEvent.create(1.66F, ReusableEvents.ENDER_PARTICLES, AnimationEvent.Side.CLIENT),
                                AnimationEvent.InTimeEvent.create(2.16F, ReusableEvents.ENDER_PARTICLES, AnimationEvent.Side.CLIENT),
                                AnimationEvent.InPeriodEvent.create(0.4F, 2F, (entitypatch, self, params) -> {
                                    ((LivingEntity) entitypatch.getOriginal()).resetFallDistance();
                                    if (entitypatch.getOriginal() instanceof Player) {
                                        Player player = (Player) entitypatch.getOriginal();
                                        player.yCloak = 0.0;
                                        player.yCloakO = 0.0;
                                    }
                                }, AnimationEvent.Side.BOTH)
                        )
                        .addState(EntityState.MOVEMENT_LOCKED, true)
        );
        DEADLYBACKFLIP_SECOND_OCEAN = builder.nextAccessor("biped/skill/rapier_backflip_second_ocean", (accessor) ->
                new AttackAnimation(0.1F, 0.15F, 0.86F, 1.28F, 1.32F, null, Armatures.BIPED.get().toolR, accessor, Armatures.BIPED)
                        .addProperty(AttackPhaseProperty.STUN_TYPE, StunType.KNOCKDOWN)
                        .addProperty(AttackPhaseProperty.HIT_SOUND, RapierSounds.RAPIER_OCEAN_WAVE.get())
                        .addProperty(AttackPhaseProperty.PARTICLE, EpicFightParticles.BLADE_RUSH_SKILL)
                        .addProperty(AttackAnimationProperty.BASIS_ATTACK_SPEED, 2F)
                        .addProperty(AttackAnimationProperty.FIXED_MOVE_DISTANCE, true)
                        .addProperty(ActionAnimationProperty.COORD_SET_TICK, MoveCoordFunctions.TRACE_TARGET_LOCATION_ROTATION)
                        .addProperty(ActionAnimationProperty.NO_GRAVITY_TIME, TimePairList.create(0.1F, 1.2F))
                        .addProperty(ActionAnimationProperty.MOVE_VERTICAL, true)
                        .addProperty(ActionAnimationProperty.CANCELABLE_MOVE, false)
                        .addEvents(
                                AnimationEvent.InTimeEvent.create(0.24F, ReusableEvents.OCEAN_PARTICLES_SOUND, AnimationEvent.Side.CLIENT),
                                AnimationEvent.InTimeEvent.create(0.24F, ReusableEvents.OCEAN_PARTICLES, AnimationEvent.Side.CLIENT),
                                AnimationEvent.InTimeEvent.create(0.78F, ReusableEvents.OCEAN_PARTICLES_TINY, AnimationEvent.Side.CLIENT),
                                AnimationEvent.InPeriodEvent.create(0.0F, 1.2F, (entitypatch, self, params) -> {
                                    ((LivingEntity) entitypatch.getOriginal()).resetFallDistance();
                                    if (entitypatch.getOriginal() instanceof Player) {
                                        Player player = (Player) entitypatch.getOriginal();
                                        player.yCloak = 0.0;
                                        player.yCloakO = 0.0;
                                    }
                                }, AnimationEvent.Side.BOTH)
                        )
                        .addState(EntityState.MOVEMENT_LOCKED, true)
        );
        DEADLYBACKFLIP_SECOND_WITHER = builder.nextAccessor("biped/skill/rapier_backflip_second_wither", (accessor) ->
                new AttackAnimation(0.1F, accessor, Armatures.BIPED,
                        new AttackAnimation.Phase(0.0F, 0.2F, 1.86F, 2.10F, 2.12F, 2.12F, Armatures.BIPED.get().toolR, null)
                                .addProperty(AttackPhaseProperty.STUN_TYPE, StunType.NONE)
                                .addProperty(AttackPhaseProperty.SWING_SOUND, SoundEvents.WITHER_HURT)
                                .addProperty(AttackPhaseProperty.HIT_SOUND, SoundEvents.WITHER_SHOOT),
                        new AttackAnimation.Phase(2.12F, 0.2F, 2.12F, 2.22F, 2.24F, 2.24F, Armatures.BIPED.get().toolR, null)
                                .addProperty(AttackPhaseProperty.STUN_TYPE, StunType.SHORT)
                                .addProperty(AttackPhaseProperty.SWING_SOUND, SoundEvents.WITHER_SHOOT),
                        new AttackAnimation.Phase(2.24F, 0.2F, 2.24F, 2.34F, 2.36F, 2.36F, Armatures.BIPED.get().toolR, null)
                                .addProperty(AttackPhaseProperty.STUN_TYPE, StunType.NONE)
                                .addProperty(AttackPhaseProperty.SWING_SOUND, SoundEvents.WITHER_SHOOT),
                        new AttackAnimation.Phase(2.36F, 0.2F, 2.36F, 2.46F, 2.48F, 2.48F, Armatures.BIPED.get().toolR, null)
                                .addProperty(AttackPhaseProperty.STUN_TYPE, StunType.NONE)
                                .addProperty(AttackPhaseProperty.SWING_SOUND, SoundEvents.WITHER_SHOOT),
                        new AttackAnimation.Phase(2.48F, 0.2F, 2.86F, 3.1F, 3.2F, 3.2F, Armatures.BIPED.get().toolR, null)
                                .addProperty(AttackPhaseProperty.HIT_SOUND, RapierSounds.RAPIER_SKILL.get())
                                .addProperty(AttackPhaseProperty.PARTICLE, EpicFightParticles.BLADE_RUSH_SKILL)
                                .addProperty(AttackPhaseProperty.SWING_SOUND, SoundEvents.WITHER_SPAWN)
                )
                        .addProperty(AttackAnimationProperty.BASIS_ATTACK_SPEED, 2F)
                        .addProperty(ActionAnimationProperty.COORD_SET_BEGIN, MoveCoordFunctions.TRACE_TARGET_LOCATION_ROTATION)
                        .addProperty(ActionAnimationProperty.MOVE_VERTICAL, true)
                        .addProperty(ActionAnimationProperty.CANCELABLE_MOVE, false)
                        .addEvents(
                                AnimationEvent.InTimeEvent.create(0.66F, ReusableEvents.WITHER_PARTICLES_SOUND, AnimationEvent.Side.CLIENT),
                                AnimationEvent.InTimeEvent.create(0.76F, ReusableEvents.WITHER_PARTICLES, AnimationEvent.Side.CLIENT),
                                AnimationEvent.InTimeEvent.create(1.26F, ReusableEvents.WITHER_PARTICLES, AnimationEvent.Side.CLIENT),
                                AnimationEvent.InTimeEvent.create(1.6F, ReusableEvents.WITHER_PARTICLES_TINY, AnimationEvent.Side.CLIENT),
                                AnimationEvent.InTimeEvent.create(2.66F, ReusableEvents.WITHER_PARTICLES, AnimationEvent.Side.CLIENT)
                        )
                        .addState(EntityState.MOVEMENT_LOCKED, true)
        );

        RAPIER_AIR_SLASH_AMETHYST = builder.nextAccessor("biped/combat/rapier_airslash_amethyst", (accessor) ->
                new AirSlashAnimation(0.1F, 0.38F, 0.56F, 0.56F, null, Armatures.BIPED.get().toolR, accessor, Armatures.BIPED)
                        .addProperty(AttackPhaseProperty.STUN_TYPE, StunType.SHORT)
                        .addProperty(AttackPhaseProperty.SOURCE_TAG, Set.of(EpicFightDamageType.FINISHER))
                        .addProperty(AttackPhaseProperty.SWING_SOUND, SoundEvents.AMETHYST_BLOCK_FALL)
                        .addProperty(AttackAnimationProperty.BASIS_ATTACK_SPEED, 2.3F)
                        .addProperty(ActionAnimationProperty.MOVE_VERTICAL, false)
                        .addEvents(AnimationEvent.InTimeEvent.create(0.1F, ReusableEvents.AMETHYST_PARTICLES_TINY, AnimationEvent.Side.CLIENT))
        );
        RAPIER_DASH_AMETHYST = builder.nextAccessor("biped/combat/rapier_dash_amethyst", (accessor) ->
                new DashAttackAnimation(0.15F, accessor, Armatures.BIPED,
                        new AttackAnimation.Phase(0.0F, 0.5F, 0.78F, 1F, 1F, Armatures.BIPED.get().toolR, null))
                        .addProperty(AttackPhaseProperty.SWING_SOUND, SoundEvents.AMETHYST_CLUSTER_FALL)
                        .addProperty(AttackAnimationProperty.BASIS_ATTACK_SPEED, 1.2F)
                        .addProperty(ActionAnimationProperty.COORD_SET_TICK, MoveCoordFunctions.TRACE_TARGET_LOCATION_ROTATION)
                        .addProperty(ActionAnimationProperty.CANCELABLE_MOVE, true)
                        .addEvents(AnimationEvent.InTimeEvent.create(0.25F, ReusableEvents.AMETHYST_IMAGE_PARTICLES_TINY, AnimationEvent.Side.CLIENT))
                        .addState(EntityState.MOVEMENT_LOCKED, true)
        );
        RAPIER_AUTO2_AMETHYST = builder.nextAccessor("biped/combat/rapier_auto2_amethyst", (accessor) ->
                new BasicAttackAnimation(0.18F, accessor, Armatures.BIPED,
                        new AttackAnimation.Phase(0.0F, 0.3F, 0.33F, 0.46F, 0.48F, 0.5F, Armatures.BIPED.get().toolR, null)
                                .addProperty(AttackPhaseProperty.DAMAGE_MODIFIER, ValueModifier.multiplier(0.35F))
                                .addProperty(AttackPhaseProperty.STUN_TYPE, StunType.NONE),
                        new AttackAnimation.Phase(0.5F, 0.6F, 0.66F, 0.72F, 0.72F, 1F, Armatures.BIPED.get().toolR, null)
                                .addProperty(AttackPhaseProperty.DAMAGE_MODIFIER, ValueModifier.multiplier(0.68F))
                                .addProperty(AttackPhaseProperty.STUN_TYPE, StunType.SHORT)
                )
                        .addProperty(AttackAnimationProperty.BASIS_ATTACK_SPEED, 2.1F)
                        .addEvents(
                                AnimationEvent.InTimeEvent.create(0.32F, ReusableEvents.AMETHYST_PARTICLES_TINY, AnimationEvent.Side.CLIENT),
                                AnimationEvent.InTimeEvent.create(0.56F, ReusableEvents.AMETHYST_PARTICLES_TINY, AnimationEvent.Side.CLIENT)
                        )
        );
        RAPIER_AUTO3_AMETHYST = builder.nextAccessor("biped/combat/rapier_auto3_amethyst", (accessor) ->
                new BasicAttackAnimation(0.18F, accessor, Armatures.BIPED,
                        new AttackAnimation.Phase(0.0F, 0.3F, 0.25F, 0.42F, 0.5F, 0.5F, Armatures.BIPED.get().toolR, null)
                                .addProperty(AttackPhaseProperty.DAMAGE_MODIFIER, ValueModifier.multiplier(0.35F))
                                .addProperty(AttackPhaseProperty.STUN_TYPE, StunType.NONE),
                        new AttackAnimation.Phase(0.5F, 0.6F, 0.58F, 0.8F, 0.82F, 0.82F, Armatures.BIPED.get().toolR, null)
                                .addProperty(AttackPhaseProperty.DAMAGE_MODIFIER, ValueModifier.multiplier(0.7F))
                )
                        .addProperty(AttackAnimationProperty.BASIS_ATTACK_SPEED, 2.1F)
                        .addEvents(
                                AnimationEvent.InTimeEvent.create(0.075F, ReusableEvents.AMETHYST_IMAGE_PARTICLES_TINY, AnimationEvent.Side.CLIENT),
                                AnimationEvent.InTimeEvent.create(0.36F, ReusableEvents.AMETHYST_IMAGE_PARTICLES_TINY, AnimationEvent.Side.CLIENT)
                        )
        );
        DEADLYBACKFLIP_SECOND_AMETHYST = builder.nextAccessor("biped/skill/rapier_backflip_second_amethyst", (accessor) ->
                new AttackAnimation(0.1F, 0.15F, 0.86F, 1.28F, 1.32F, null, Armatures.BIPED.get().toolR, accessor, Armatures.BIPED)
                        .addProperty(AttackPhaseProperty.HIT_SOUND, SoundEvents.AMETHYST_BLOCK_PLACE)
                        .addProperty(AttackPhaseProperty.PARTICLE, EpicFightParticles.BLADE_RUSH_SKILL)
                        .addProperty(AttackAnimationProperty.BASIS_ATTACK_SPEED, 2F)
                        .addProperty(AttackAnimationProperty.FIXED_MOVE_DISTANCE, true)
                        .addProperty(ActionAnimationProperty.COORD_SET_TICK, MoveCoordFunctions.TRACE_TARGET_LOCATION_ROTATION)
                        .addProperty(ActionAnimationProperty.NO_GRAVITY_TIME, TimePairList.create(0.1F, 1.2F))
                        .addProperty(ActionAnimationProperty.MOVE_VERTICAL, true)
                        .addProperty(ActionAnimationProperty.CANCELABLE_MOVE, false)
                        .addEvents(
                                AnimationEvent.InTimeEvent.create(0.24F, ReusableEvents.OCEAN_PARTICLES_SOUND, AnimationEvent.Side.CLIENT),
                                AnimationEvent.InTimeEvent.create(0.24F, ReusableEvents.AMETHYST_PARTICLES, AnimationEvent.Side.CLIENT),
                                AnimationEvent.InTimeEvent.create(0.69F, ReusableEvents.AMETHYST_PARTICLES_TINY, AnimationEvent.Side.CLIENT)
                        )
                        .addEvents(AnimationEvent.InPeriodEvent.create(0.0F, 1.2F, (entitypatch, self, params) -> {
                            ((LivingEntity) entitypatch.getOriginal()).resetFallDistance();
                            if (entitypatch.getOriginal() instanceof Player player) {
                                player.yCloak = 0.0;
                                player.yCloakO = 0.0;
                            }
                        }, AnimationEvent.Side.BOTH))
                        .addState(EntityState.MOVEMENT_LOCKED, true)
        );
        RAPIER_GUARD_PARRY_AMETHYST = builder.nextAccessor("biped/skill/guard_rapier_parry_amethyst", (accessor) ->
                new BasicAttackAnimation(0.12F, 0.56F, 0.76F, 0.78F, null, Armatures.BIPED.get().toolR, accessor, Armatures.BIPED)
                        .addProperty(AttackPhaseProperty.DAMAGE_MODIFIER, ValueModifier.setter(3.8F))
                        .addProperty(AttackPhaseProperty.ARMOR_NEGATION_MODIFIER, ValueModifier.setter(99))
                        .addProperty(AttackPhaseProperty.IMPACT_MODIFIER, ValueModifier.setter(3))
                        .addProperty(AttackPhaseProperty.STUN_TYPE, StunType.LONG)
                        .addProperty(AttackPhaseProperty.PARTICLE, EpicFightParticles.EVISCERATE)
                        .addProperty(AttackAnimationProperty.BASIS_ATTACK_SPEED, 2F)
                        .addProperty(AttackAnimationProperty.FIXED_MOVE_DISTANCE, true)
                        .addState(EntityState.MOVEMENT_LOCKED, true)
                        .addState(EntityState.TURNING_LOCKED, true)
                        .addEvents(AnimationEvent.InTimeEvent.create(0.28F, ReusableEvents.AMETHYST_IMAGE_PARTICLES_TINY, AnimationEvent.Side.CLIENT))
        );
    }

    public interface IProxy {
        @Nullable
        Entity getClientPlayer();
    }
    public static class ClientProxy implements IProxy {
        @Override
        public Entity getClientPlayer() {
            return Minecraft.getInstance().player;
        }
    }
    public static class ServerProxy implements IProxy {
        @Override
        public Entity getClientPlayer() {
            return null;
        }
    }
    // Particles and stuff
    public static class ReusableEvents {
        private static final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
        private static final int ENDER_PARTICLE_COUNT = 69;
        private static final int PARTICLE_COUNT = 20;
        private static final int FOLLOW_DURATION = 18;
        private static final int PARTICLE_COUNT_TINY = 12;
        private static final int FOLLOW_DURATION_TINY = 12;
        private static final int SPAWN_ALWAYS = 1;
        private static final int SPAWN_INTERVAL = 2;
        private static int tickCounter = 1;
        private static final Map<Entity, Integer> activeParticles = new HashMap<>();
        private static final Map<Entity, Integer> activeParticlesWither = new HashMap<>();
        private static final Map<Entity, Integer> activeParticlesWitherBig = new HashMap<>();
        private static final Map<Entity, Integer> activeParticlesWitherTiny = new HashMap<>();
        private static final Map<Entity, Integer> activeParticlesAmethyst = new HashMap<>();

        //ENDER
        private static final AnimationEvent.E0 ENDER_PARTICLES = (entitypatch, self, params) -> {
            Entity entity = entitypatch.getOriginal();
            RandomSource random = entitypatch.getOriginal().getRandom();
            entity.playSound(SoundEvents.FOX_TELEPORT, 1F, 1.2F);
            scheduler.schedule(() -> {
                spawnParticlesEnder(entity, random);
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
            spawnParticlesEnderDelayed(entity, random);
        };

        //OCEAN
        private static final AnimationEvent.E0 OCEAN_PARTICLES = (entitypatch, self, params) -> {
            Entity playerEntity = RapierForEpicfight.proxy.getClientPlayer();
            if (playerEntity != null) {
                spawnOceanParticlesFollowingPlayer(playerEntity);
            }
        };
        private static final AnimationEvent.E0 OCEAN_PARTICLES_TINY = (entitypatch, self, params) -> {
            Entity playerEntity = RapierForEpicfight.proxy.getClientPlayer();
            if (playerEntity != null) {
                spawnOceanParticlesFollowingPlayer_Tiny(playerEntity);
            }
        };
        private static final AnimationEvent.E0 OCEAN_PARTICLES_SOUND = (entitypatch, self, params) -> {
            Entity entity = entitypatch.getOriginal();
            Entity playerEntity = RapierForEpicfight.proxy.getClientPlayer();
            if (playerEntity != null) {
                entity.playSound(RapierSounds.RAPIER_OCEAN_JUMP.get());
            }
        };

        //WITHER
        private static final AnimationEvent.E0 WITHER_PARTICLES = (entitypatch, self, params) -> {
            Entity playerEntity = RapierForEpicfight.proxy.getClientPlayer();
            if (playerEntity != null) {
                spawnWitherParticlesFollowingPlayer(playerEntity);
            }
        };
        private static final AnimationEvent.E0 WITHER_PARTICLES_TINY = (entitypatch, self, params) -> {
            Entity playerEntity = RapierForEpicfight.proxy.getClientPlayer();
            if (playerEntity != null) {
                spawnWitherParticlesFollowingPlayer_Tiny(playerEntity);
            }
        };
        private static final AnimationEvent.E0 WITHER_PARTICLES_BIG = (entitypatch, self, params) -> {
            Entity playerEntity = RapierForEpicfight.proxy.getClientPlayer();
            if (playerEntity != null) {
                spawnWitherParticlesFollowingPlayer_Big(playerEntity);
            }
        };
        private static final AnimationEvent.E0 WITHER_PARTICLES_INSTANT = (entitypatch, self, params) -> {
            Entity playerEntity = RapierForEpicfight.proxy.getClientPlayer();
            Entity entity = entitypatch.getOriginal();
            RandomSource random = entitypatch.getOriginal().getRandom();
            if (playerEntity != null) {
                spawnParticlesWitherInstant(entity, random);
            }
        };
        private static final AnimationEvent.E0 WITHER_PARTICLES_SOUND = (entitypatch, self, params) -> {
            Entity entity = entitypatch.getOriginal();
            Entity playerEntity = RapierForEpicfight.proxy.getClientPlayer();
            if (playerEntity != null) {
                entity.playSound(SoundEvents.WITHER_AMBIENT);
            }
        };

        //AMETHYST
        private static final AnimationEvent.E0 AMETHYST_PARTICLES = (entitypatch, self, params) -> {
            Entity playerEntity = RapierForEpicfight.proxy.getClientPlayer();
            if (playerEntity != null) {
                spawnAmethystParticlesFollowingPlayer(playerEntity);
            }
        };
        private static final AnimationEvent.E0 AMETHYST_IMAGE_PARTICLES_TINY = (entitypatch, self, params) -> {
            Entity entity = entitypatch.getOriginal();
            Entity playerEntity = RapierForEpicfight.proxy.getClientPlayer();
            if (playerEntity != null) {
                spawnAmethystParticlesFollowingPlayer_Tiny(playerEntity);
                scheduler.schedule(() -> entity.level().addParticle(
                        EpicFightParticles.ENTITY_AFTER_IMAGE.get(),
                        entity.getX(),
                        entity.getY(),
                        entity.getZ(),
                        Double.longBitsToDouble(entity.getId()),
                        0,
                        0
                ), 52, TimeUnit.MILLISECONDS);
            }
        };
        private static final AnimationEvent.E0 AMETHYST_PARTICLES_TINY = (entitypatch, self, params) -> {
            Entity playerEntity = RapierForEpicfight.proxy.getClientPlayer();
            if (playerEntity != null) {
                spawnAmethystParticlesFollowingPlayer_Tiny(playerEntity);
            }
        };

        //PARTICLE SPAWNERS
        private static void spawnParticlesEnder(Entity entity, RandomSource random) {
            ClientLevel clientLevel = Minecraft.getInstance().level;
            if (clientLevel != null) {
                double horizontalRadius = 1.2;
                for (int i = 0; i < ENDER_PARTICLE_COUNT; i++) {
                    double xOffset = (random.nextDouble() - 0.4) * horizontalRadius;
                    double yOffset = (random.nextDouble() - random.nextDouble()) * 1.4D;
                    double zOffset = (random.nextDouble() - 0.4) * horizontalRadius;

                    clientLevel.addParticle(ParticleTypes.PORTAL,
                            entity.getX() + xOffset,
                            entity.getY() + yOffset,
                            entity.getZ() + zOffset,
                            0,
                            0.6,
                            0
                    );
                }
            }
        }

        public static void spawnParticlesOcean() {
            ClientLevel clientLevel = Minecraft.getInstance().level;
            if (clientLevel != null) {

                RandomSource random = RandomSource.create();
                tickCounter++;

                activeParticles.entrySet().removeIf(entry -> {
                    Entity entity = entry.getKey();
                    int ticksRemaining = entry.getValue();
                    if (ticksRemaining <= 0 || !entity.isAlive()) {
                        return true;
                    }
                    if (tickCounter % SPAWN_INTERVAL == 0) {
                        double sphereRadius = 0.69;
                        double yStretchFactor = 1.42;
                        for (int i = 0; i < PARTICLE_COUNT; i++) {
                            double theta = random.nextDouble() * 2 * Math.PI;
                            double phi = Math.acos(2 * random.nextDouble() - 1);
                            double xOffset = sphereRadius * Math.sin(phi) * Math.cos(theta);
                            double yOffset = sphereRadius * Math.sin(phi) * Math.sin(theta) * yStretchFactor + 0.5;
                            double zOffset = sphereRadius * Math.cos(phi);
                            double vxOffset = xOffset * -0.1;
                            double vyOffset = yOffset * -0.1;
                            double vzOffset = zOffset * -0.1;
                            clientLevel.addParticle(ParticleTypes.RAIN,
                                    entity.getX() + xOffset,
                                    entity.getY() + yOffset + 0.3,
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
        }
        public static void spawnParticlesOceanTiny() {
            ClientLevel clientLevel = Minecraft.getInstance().level;
            if (clientLevel != null) {

                RandomSource random = RandomSource.create();
                tickCounter++;

                activeParticles.entrySet().removeIf(entry -> {
                    Entity entity = entry.getKey();
                    int ticksRemaining = entry.getValue();

                    if (ticksRemaining <= 0 || !entity.isAlive()) {
                        return true;
                    }
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
        }

        public static void spawnParticlesWither() {
            ClientLevel clientLevel = Minecraft.getInstance().level;
            if (clientLevel != null) {

                RandomSource random = RandomSource.create();
                tickCounter++;

                activeParticlesWither.entrySet().removeIf(entry -> {
                    Entity entity = entry.getKey();
                    int ticksRemaining = entry.getValue();
                    if (ticksRemaining <= 0 || !entity.isAlive()) {
                        return true;
                    }
                    if (tickCounter % SPAWN_INTERVAL == 0) {
                        double sphereRadius = 0.66;
                        for (int i = 0; i < PARTICLE_COUNT_TINY; i++) {
                            double theta = random.nextDouble() * 2 * Math.PI;
                            double phi = Math.acos(2 * random.nextDouble() - 1);
                            double xOffset = sphereRadius * Math.sin(phi) * Math.cos(theta);
                            double yOffset = sphereRadius * Math.sin(phi) * Math.sin(theta);
                            double zOffset = sphereRadius * Math.cos(phi);
                            double vxOffset = xOffset * 0.2;
                            double vyOffset = yOffset * 0.2;
                            double vzOffset = zOffset * 0.2;
                            clientLevel.addParticle(ParticleTypes.LARGE_SMOKE,
                                    entity.getX() + xOffset,
                                    entity.getY() + yOffset + 0.6,
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
        }
        public static void spawnParticlesWitherBig() {
            ClientLevel clientLevel = Minecraft.getInstance().level;
            if (clientLevel != null) {

                RandomSource random = RandomSource.create();
                tickCounter++;

                activeParticlesWitherBig.entrySet().removeIf(entry -> {
                    Entity entity = entry.getKey();
                    int ticksRemaining = entry.getValue();
                    if (ticksRemaining <= 0 || !entity.isAlive()) {
                        return true;
                    }
                    if (tickCounter % SPAWN_ALWAYS == 0) {
                        double sphereRadius = 2;
                        for (int i = 0; i < PARTICLE_COUNT; i++) {
                            double theta = random.nextDouble() * 2 * Math.PI;
                            double phi = Math.acos(2 * random.nextDouble() - 1);
                            double xOffset = sphereRadius * Math.sin(phi) * Math.cos(theta);
                            double yOffset = sphereRadius * Math.sin(phi) * Math.sin(theta);
                            double zOffset = sphereRadius * Math.cos(phi);
                            double vxOffset = xOffset * 0.2;
                            double vyOffset = yOffset * 0.2;
                            double vzOffset = zOffset * 0.2;
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
        }
        public static void spawnParticlesWitherTiny() {
            ClientLevel clientLevel = Minecraft.getInstance().level;
            if (clientLevel != null) {

                RandomSource random = RandomSource.create();
                tickCounter++;

                activeParticlesWitherTiny.entrySet().removeIf(entry -> {
                    Entity entity = entry.getKey();
                    int ticksRemaining = entry.getValue();

                    if (ticksRemaining <= 0 || !entity.isAlive()) {
                        return true;
                    }
                    if (tickCounter % SPAWN_INTERVAL == 0) {
                        double horizontalRadius = 1.2;
                        for (int i = 0; i < PARTICLE_COUNT; i++) {
                            double hxOffset = (random.nextDouble() - 0.6) * horizontalRadius;
                            double hyOffset = (random.nextDouble() - random.nextDouble()) * 1.8D;
                            double hzOffset = (random.nextDouble() - 0.6) * horizontalRadius;
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
        }
        private static void spawnParticlesWitherInstant(Entity entity, RandomSource random) {
            ClientLevel clientLevel = Minecraft.getInstance().level;
            if (clientLevel != null) {
                double sphereRadius = 0.66;
                for (int i = 0; i < 18; i++) {
                    double theta = random.nextDouble() * 2 * Math.PI;
                    double phi = Math.acos(2 * random.nextDouble() - 1);
                    double xOffset = sphereRadius * Math.sin(phi) * Math.cos(theta);
                    double yOffset = sphereRadius * Math.sin(phi) * Math.sin(theta);
                    double zOffset = sphereRadius * Math.cos(phi);
                    double vxOffset = xOffset * 0.2;
                    double vyOffset = yOffset * 0.2;
                    double vzOffset = zOffset * 0.2;

                    clientLevel.addParticle(ParticleTypes.LARGE_SMOKE,
                            entity.getX() + xOffset,
                            entity.getY() + yOffset + 0.6,
                            entity.getZ() + zOffset,
                            vxOffset,
                            vyOffset,
                            vzOffset
                    );
                }
            }
        }

        public static void spawnParticlesAmethyst() {
            ClientLevel clientLevel = Minecraft.getInstance().level;
            if (clientLevel != null) {

                RandomSource random = RandomSource.create();
                tickCounter++;

                activeParticlesAmethyst.entrySet().removeIf(entry -> {
                    Entity entity = entry.getKey();
                    int ticksRemaining = entry.getValue();
                    if (ticksRemaining <= 0 || !entity.isAlive()) {
                        return true;
                    }
                    if (tickCounter % SPAWN_INTERVAL == 0) {
                        double sphereRadius = 1;
                        double yStretchFactor = 1.2;
                        for (int i = 0; i < PARTICLE_COUNT; i++) {
                            double theta = random.nextDouble() * 2 * Math.PI;
                            double phi = Math.acos(2 * random.nextDouble() - 1);
                            double xOffset = sphereRadius * Math.sin(phi) * Math.cos(theta);
                            double yOffset = sphereRadius * Math.sin(phi) * Math.sin(theta) * yStretchFactor + 0.4;
                            double zOffset = sphereRadius * Math.cos(phi);
                            double vxOffset = xOffset * -0.64;
                            double vyOffset = yOffset * -0.64;
                            double vzOffset = zOffset * -0.64;
                            clientLevel.addParticle(ParticleTypes.ENCHANT,
                                    entity.getX() + xOffset,
                                    entity.getY() + yOffset + 0.7,
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
        }

        private static void spawnParticlesEnderDelayed(Entity entity, RandomSource random) {
            scheduler.schedule(() -> spawnParticlesEnder(entity, random), 144, TimeUnit.MILLISECONDS);
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
        public static void spawnWitherParticlesFollowingPlayer_Big(Entity entity) {
            if (!activeParticlesWitherBig.containsKey(entity)) {
                activeParticlesWitherBig.put(entity, FOLLOW_DURATION);
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