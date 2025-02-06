package net.yonchi.refm.skill.weaponpassive;

import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.Particle;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.phys.Vec3;

import yesman.epicfight.api.utils.math.Vec3f;
import yesman.epicfight.gameasset.Armatures;
import yesman.epicfight.skill.Skill;
import yesman.epicfight.skill.SkillContainer;
import yesman.epicfight.skill.passive.PassiveSkill;
import yesman.epicfight.world.entity.eventlistener.PlayerEventListener;

import java.util.UUID;

import static net.yonchi.refm.api.animation.JointTrack.getJointWithTranslation;

public class OceanRapierPassive extends PassiveSkill {
    private static final UUID EVENT_UUID = UUID.fromString("09a20178-a51c-4062-80f4-1618e30672fc");

    public OceanRapierPassive(Builder<? extends Skill> builder) {
        super(builder);
    }

    @Override
    public void onInitiate(SkillContainer container) {
        super.onInitiate(container);
        container.getExecuter().getEventListener().addEventListener(PlayerEventListener.EventType.MOVEMENT_INPUT_EVENT, EVENT_UUID, (event) -> {
            LivingEntity target = event.getPlayerPatch().getOriginal();
            if (target == null) return;
            if (target.isInWater()) {
                int duration = 60;
                int amplifier1 = 1;
                int amplifier2 = 0;
                MobEffectInstance dolphinEffect = new MobEffectInstance(MobEffects.DOLPHINS_GRACE, duration, amplifier1, true, false);
                MobEffectInstance breathingEffect = new MobEffectInstance(MobEffects.CONDUIT_POWER, duration, amplifier2, true, false);
                target.addEffect(dolphinEffect);
                target.addEffect(breathingEffect);
                    if (target.isSprinting()) {
                        Vec3 velocity = target.getDeltaMovement();
                        double speed = Math.sqrt(velocity.x * velocity.x + velocity.y * velocity.y + velocity.z * velocity.z);
                        double speedThreshold = 0.286;
                        if (speed > speedThreshold) {
                            int numParticles = 2;
                            for (int i = 0; i < numParticles; i++) {
                                float x_L = -0.08F;
                                float x_R = 0.08F;
                                float dynamicY = getDynamicYRotation(target.getXRot());
                                float dynamicX = getDynamicXOffset(target.getXRot());
                                Vec3 pos_L = getJointWithTranslation(Minecraft.getInstance().player, target, new Vec3f(x_L, dynamicY, dynamicX), Armatures.BIPED.legL);
                                Vec3 pos_R = getJointWithTranslation(Minecraft.getInstance().player, target, new Vec3f(x_R, dynamicY, dynamicX), Armatures.BIPED.legR);

                                if (pos_L != null) {
                                    Particle particle = Minecraft.getInstance().particleEngine.createParticle(ParticleTypes.BUBBLE, pos_L.x, pos_L.y, pos_L.z,
                                            target.getDeltaMovement().x, target.getDeltaMovement().y, target.getDeltaMovement().z);
                                    if (particle != null) {
                                        particle.scale(0.92f);
                                        particle.setLifetime(12);
                                    }
                                }
                                if (pos_R != null) {
                                    Particle particle = Minecraft.getInstance().particleEngine.createParticle(ParticleTypes.BUBBLE, pos_R.x, pos_R.y, pos_R.z,
                                            target.getDeltaMovement().x, target.getDeltaMovement().y, target.getDeltaMovement().z);
                                    if (particle != null) {
                                        particle.scale(0.92f);
                                        particle.setLifetime(12);
                                    }
                                }
                            }
                        }
                    }
            } else {
                target.removeEffect(MobEffects.DOLPHINS_GRACE);
                target.removeEffect(MobEffects.CONDUIT_POWER);
            }
            if (!target.isInWater()) {
                target.removeEffect(MobEffects.DOLPHINS_GRACE);
                target.removeEffect(MobEffects.CONDUIT_POWER);
            }
        });
    }

    @Override
    public void onRemoved(SkillContainer container) {
        PlayerEventListener listener = container.getExecuter().getEventListener();
        LivingEntity target = container.getExecuter().getOriginal();
        if (target != null) {
            target.removeEffect(MobEffects.DOLPHINS_GRACE);
            target.removeEffect(MobEffects.CONDUIT_POWER);
        }
        listener.removeListener(PlayerEventListener.EventType.MOVEMENT_INPUT_EVENT, EVENT_UUID);
    }

    public static float getDynamicXOffset(float pitch) {
        float lookingUp = 0.2F;
        float lookingDown = -1.12F;
        float lookingStraight = -0.72F;
        if (pitch <= 0) {
            return lookingUp + (pitch + 90) / 90 * (-0.92F);
        } else {
            return lookingStraight + (pitch / 90) * (lookingDown + 0.72F);
        }
    }
    public static float getDynamicYRotation(float pitch) {
        float lookingUp = 0.1F;
        float lookingDown = -0.52F;
        float lookingStraight = 0.52F;
        if (pitch <= 0) {
            return lookingUp + (pitch + 90) / 90 * (0.42F);
        } else {
            return lookingStraight + (pitch / 90) * (lookingDown - 0.52F);
        }
    }
}