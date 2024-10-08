package net.yonchi.refm.gameasset;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.yonchi.refm.RapierForEpicfight;
import net.yonchi.refm.api.animation.property.RapierMoveCoordFunctions;
import yesman.epicfight.api.animation.property.AnimationEvent;
import yesman.epicfight.api.animation.property.AnimationProperty;
import yesman.epicfight.api.animation.property.MoveCoordFunctions;
import yesman.epicfight.api.animation.types.*;
import yesman.epicfight.api.animation.types.AttackAnimation;
import yesman.epicfight.api.animation.property.AnimationProperty.AttackAnimationProperty;
import yesman.epicfight.api.animation.types.datapack.FakeAttackAnimation;
import yesman.epicfight.api.forgeevent.AnimationRegistryEvent;
import yesman.epicfight.api.utils.HitEntityList;
import yesman.epicfight.api.utils.TimePairList;
import yesman.epicfight.api.utils.math.ValueModifier;
import yesman.epicfight.gameasset.Armatures;
import yesman.epicfight.gameasset.ColliderPreset;
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
    public static StaticAnimation RAPIER_GUARD_ACTIVE_HIT1;
    public static StaticAnimation RAPIER_GUARD_ACTIVE_HIT2;
    public static StaticAnimation BIPED_HOLD_RAPIER;
    public static StaticAnimation BIPED_SNEAK_RAPIER;
    public static StaticAnimation BIPED_WALK_RAPIER;
    public static StaticAnimation BIPED_RUN_RAPIER;
    public static StaticAnimation DEADLYBACKFLIP_FIRST;
    public static StaticAnimation DEADLYBACKFLIP_SECOND;
    public static StaticAnimation RAPIER_AIR_SLASH_ENDER;
    public static StaticAnimation RAPIER_DASH_ENDER;
    public static StaticAnimation DEADLYBACKFLIP_SECOND_ENDER;

    @SubscribeEvent
    public static void registerAnimations(AnimationRegistryEvent event){
        event.getRegistryMap().put(RapierForEpicfight.MOD_ID, RapierAnimations::build);
    }

    private static void build() {
        HumanoidArmature biped = Armatures.BIPED;

        //Every animation defined with his own hitbox timers and Properties
        RAPIER_AIR_SLASH = new AirSlashAnimation(0.1F, 0.2F, 0.5F, 0.5F, null, biped.toolR, "biped/combat/rapier_airslash", biped)
                .addProperty(AttackAnimationProperty.BASIS_ATTACK_SPEED, 2.4F)
                .addProperty(AnimationProperty.AttackPhaseProperty.SWING_SOUND, RapierSounds.RAPIER_JUMP.get());
        RAPIER_DASH = new DashAttackAnimation(0.15F, "biped/combat/rapier_dash", biped, new AttackAnimation.Phase(0.0F, 0.42F, 0.69F, 0.8F, 1F, biped.toolR,null))
                .addProperty(AnimationProperty.AttackPhaseProperty.SWING_SOUND, RapierSounds.RAPIER_SWING.get())
                .addProperty(AttackAnimationProperty.BASIS_ATTACK_SPEED, 1.2F);
        
        RAPIER_AUTO1 = new BasicAttackAnimation(0.12F, 0.5F, 0.8F, 0.5F, null, biped.toolR, "biped/combat/rapier_auto1", biped)
                .addProperty(AttackAnimationProperty.BASIS_ATTACK_SPEED, 1.8F);
        RAPIER_AUTO2 = new BasicAttackAnimation(0.12F, 0.4F, 0.4F, 0.56F, null, biped.toolR, "biped/combat/rapier_auto2", biped)
                .addProperty(AttackAnimationProperty.BASIS_ATTACK_SPEED, 1.8F);
        RAPIER_AUTO3 = new BasicAttackAnimation(0.18F,"biped/combat/rapier_auto3", biped,
            new AttackAnimation.Phase(0.0F, 0.3F, 0.25F, 0.5F, 0.5F, 0.5F, biped.toolR, null)
            .addProperty(AnimationProperty.AttackPhaseProperty.DAMAGE_MODIFIER, ValueModifier.multiplier(-0.3F))
            ,new AttackAnimation.Phase(0.5F, 0.6F, 0.8F, 0.8F, 1F, biped.toolR, null))
            .addProperty(AttackAnimationProperty.BASIS_ATTACK_SPEED, 2.1F);

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

        RAPIER_AIR_SLASH_ENDER = new AttackAnimation(0.18F,"biped/combat/rapier_airslash_ender", biped,
                new AttackAnimation.Phase(0.0F, 0.2F, 0.44F, 0.75F, 0.85F, 0.8F, biped.toolR, null)
                .addProperty(AnimationProperty.AttackPhaseProperty.STUN_TYPE, StunType.LONG)
                ,new AttackAnimation.Phase(0.8F, 0.5F, 1.1F, 1.6F, 1.5F, biped.toolR, null))
                .addProperty(AttackAnimationProperty.BASIS_ATTACK_SPEED, 2.3F)
                .addProperty(AnimationProperty.AttackPhaseProperty.STUN_TYPE, StunType.SHORT)
                .addProperty(AnimationProperty.AttackPhaseProperty.DAMAGE_MODIFIER, ValueModifier.multiplier(0.6F))
                .addProperty(AnimationProperty.AttackPhaseProperty.SWING_SOUND, RapierSounds.RAPIER_JUMP.get())
                .addProperty(AnimationProperty.ActionAnimationProperty.CANCELABLE_MOVE, false)
                .addEvents(AnimationEvent.TimeStampedEvent.create(0.18F, ReusableEvents.ENDER_PARTICLES_AIRSLASH, AnimationEvent.Side.CLIENT))
                .addState(EntityState.MOVEMENT_LOCKED, true);
        RAPIER_DASH_ENDER = new DashAttackAnimation(0.15F, "biped/combat/rapier_dash_ender", biped, new AttackAnimation.Phase(0.0F, 0.5F, 0.78F, 1F, 1F, biped.toolR,null))
                .addProperty(AttackAnimationProperty.BASIS_ATTACK_SPEED, 1.2F)
                .addProperty(AnimationProperty.AttackPhaseProperty.SWING_SOUND, RapierSounds.RAPIER_SWING.get())
                .addProperty(AnimationProperty.ActionAnimationProperty.CANCELABLE_MOVE, false)
                .addEvents(AnimationEvent.TimeStampedEvent.create(0.25F, ReusableEvents.ENDER_PARTICLES_DASH, AnimationEvent.Side.CLIENT))
                .addState(EntityState.MOVEMENT_LOCKED, true);

        DEADLYBACKFLIP_SECOND_ENDER = new BasicAttackAnimation(0.1F,"biped/skill/rapier_backflip_second_ender", biped,
                new AttackAnimation.Phase(0.0F, 0.2F, 0.8F, 1.12F, 1.12F, 1.12F, biped.toolR, null)
                .addProperty(AnimationProperty.AttackPhaseProperty.STUN_TYPE, StunType.NONE)
                ,new AttackAnimation.Phase(1.12F, 0.2F, 1.12F, 1.46F, 1.5F, 1.5F, biped.toolR, null)
                .addProperty(AnimationProperty.AttackPhaseProperty.STUN_TYPE, StunType.LONG)
                ,new AttackAnimation.Phase(1.5F, 0.2F, 1.8F, 2.18F, 2.2F, 2.2F, biped.toolR, null))
                .addProperty(AnimationProperty.AttackPhaseProperty.HIT_SOUND, RapierSounds.RAPIER_SKILL.get())
                .addProperty(AttackAnimationProperty.BASIS_ATTACK_SPEED, 2.1F)
                .addProperty(AttackAnimationProperty.FIXED_MOVE_DISTANCE, true)
                .addProperty(AnimationProperty.ActionAnimationProperty.COORD_SET_BEGIN, RapierMoveCoordFunctions.TRACE_LOCROT_TARGET)
                .addProperty(AnimationProperty.ActionAnimationProperty.NO_GRAVITY_TIME, TimePairList.create(0.0F, 2.1F))
                .addProperty(AnimationProperty.ActionAnimationProperty.MOVE_VERTICAL, true)
                .addProperty(AnimationProperty.ActionAnimationProperty.CANCELABLE_MOVE, false)
                .addEvents(AnimationEvent.TimeStampedEvent.create(0.28F, ReusableEvents.ENDER_PARTICLES_DEADLYBACKFLIP, AnimationEvent.Side.CLIENT))
                .addState(EntityState.MOVEMENT_LOCKED, true);
    }

    //Particles and stuff
    public static class ReusableEvents {
        private static final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

        private static final AnimationEvent.AnimationEventConsumer ENDER_PARTICLES_AIRSLASH = (entitypatch, self, params) -> {
            Entity entity = entitypatch.getOriginal();
            RandomSource random = entitypatch.getOriginal().getRandom();
            for (int i = 0; i < 12; i++) {
                double x = entity.getX() + (random.nextDouble() - random.nextDouble()) * 1.2D;
                double y = entity.getY() + (random.nextDouble() - random.nextDouble()) * 2.1D;
                double z = entity.getZ() + (random.nextDouble() - random.nextDouble()) * 1.2D;
                ((LivingEntity) entitypatch.getOriginal()).level().addParticle((ParticleOptions) ParticleTypes.PORTAL,true, x, y, z,0.2,0.3, 0.2);
            }
            entity.playSound(SoundEvents.FOX_TELEPORT, 1F, 1.2F);
            //Makes the particle follow the player, to create a trail
            followPlayerWithParticles(entity, random, 256, 3);

            //Second particle wave at the second sound
            scheduler.schedule(() -> {
                entity.playSound(SoundEvents.FOX_TELEPORT, 1F, 1.2F);
                for (int i = 0; i < 12; i++) {
                    double x = entity.getX() + (random.nextDouble() - random.nextDouble()) * 1.2D;
                    double y = entity.getY() + (random.nextDouble() - random.nextDouble()) * 2.1D;
                    double z = entity.getZ() + (random.nextDouble() - random.nextDouble()) * 1.2D;
                    ((LivingEntity) entitypatch.getOriginal()).level().addParticle((ParticleOptions) ParticleTypes.PORTAL,true, x, y, z,0.2,0.3, 0.2);
                }

            }, 396, TimeUnit.MILLISECONDS);
        };

        private static final AnimationEvent.AnimationEventConsumer ENDER_PARTICLES_DASH = (entitypatch, self, params) -> {
            Entity entity = entitypatch.getOriginal();
            RandomSource random = entitypatch.getOriginal().getRandom();
            for (int i = 0; i < 12; i++) {
                double x = entity.getX() + (random.nextDouble() - random.nextDouble()) * 1.2D;
                double y = entity.getY() + (random.nextDouble() - random.nextDouble()) * 1.8D;
                double z = entity.getZ() + (random.nextDouble() - random.nextDouble()) * 1.2D;
                ((LivingEntity) entitypatch.getOriginal()).level().addParticle((ParticleOptions) ParticleTypes.PORTAL,true, x, y, z,0.2,0.3, 0.2);
            }
            entity.playSound(SoundEvents.FOX_TELEPORT, 1F, 1.2F);
            //Makes the particle follow the player, to create a trail
            followPlayerWithParticles(entity, random, 24, 4);
        };

        private static final AnimationEvent.AnimationEventConsumer ENDER_PARTICLES_DEADLYBACKFLIP = (entitypatch, self, params) -> {
            Entity entity = entitypatch.getOriginal();
            RandomSource random = entitypatch.getOriginal().getRandom();
            for (int i = 0; i < 12; i++) {
                double x = entity.getX() + (random.nextDouble() - random.nextDouble()) * 1.2D;
                double y = entity.getY() + (random.nextDouble() - random.nextDouble()) * 2.1D;
                double z = entity.getZ() + (random.nextDouble() - random.nextDouble()) * 1.2D;
                ((LivingEntity) entitypatch.getOriginal()).level().addParticle(ParticleTypes.PORTAL, true, x, y, z, 0.2, 0.3, 0.2);
            }
            entity.playSound(SoundEvents.FOX_TELEPORT, 1F, 1.2F);
            //Makes the particle follow the player, to create a trail
            followPlayerWithParticles(entity, random, 286, 5);

            //Second particle wave at the second sound
            scheduler.schedule(() -> {
                entity.playSound(SoundEvents.FOX_TELEPORT, 1F, 1.2F);
                for (int i = 0; i < 12; i++) {
                    double x = entity.getX() + (random.nextDouble() - random.nextDouble()) * 1.2D;
                    double y = entity.getY() + (random.nextDouble() - random.nextDouble()) * 2.1D;
                    double z = entity.getZ() + (random.nextDouble() - random.nextDouble()) * 1.2D;
                    ((LivingEntity) entitypatch.getOriginal()).level().addParticle(ParticleTypes.PORTAL, true, x, y, z, 0.2, 0.3, 0.2);
                }
            //Third particle wave at the second sound
                scheduler.schedule(() -> {
                    entity.playSound(SoundEvents.FOX_TELEPORT, 1F, 1.2F);
                }, 380 + 120, TimeUnit.MILLISECONDS);//Third sound delay

            }, 356, TimeUnit.MILLISECONDS); //Second sound delay
        };

        private static void followPlayerWithParticles(Entity entity, RandomSource random, int iterations, long intervalMillis) {
            for (int i = 0; i < iterations; i++) {
                scheduler.schedule(() -> {
                    // Generate particles
                    for (int j = 0; j < 3; j++) {
                        double x = entity.getX() + (random.nextDouble() - random.nextDouble()) * 0.8D;
                        double y = entity.getY() + (random.nextDouble() - random.nextDouble()) * 1.5D;
                        double z = entity.getZ() + (random.nextDouble() - random.nextDouble()) * 0.8D;
                        ((LivingEntity) entity).level().addParticle((ParticleOptions) ParticleTypes.PORTAL,true, x, y, z,0.2,0.3, 0.2);
                    }
                }, i * intervalMillis, TimeUnit.MILLISECONDS);
            }
        }
    }
}