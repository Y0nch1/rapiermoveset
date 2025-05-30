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
import yesman.epicfight.skill.SkillBuilder;
import yesman.epicfight.skill.SkillContainer;
import yesman.epicfight.skill.passive.PassiveSkill;
import yesman.epicfight.skill.weaponinnate.WeaponInnateSkill;
import yesman.epicfight.world.entity.eventlistener.PlayerEventListener;

import java.util.UUID;

import static net.yonchi.refm.api.animation.JointTrack.getJointWithTranslation;

public class OceanRapierPassive extends PassiveSkill {
    private static final UUID EVENT_UUID = UUID.fromString("09a20178-a51c-4062-80f4-1618e30672fc");

    public OceanRapierPassive(SkillBuilder<? extends PassiveSkill> builder) {
        super(builder);
    }

    @Override
    public void onInitiate(SkillContainer container) {
        super.onInitiate(container);
        container.getExecutor().getEventListener().addEventListener(PlayerEventListener.EventType.MOVEMENT_INPUT_EVENT, EVENT_UUID, (event) -> {
            LivingEntity player = event.getPlayerPatch().getOriginal();
            if (player == null) return;
            if (player.isInWater()) {
                int duration = 5;
                int amplifier1 = 1;
                int amplifier2 = 0;
                MobEffectInstance dolphinEffect = new MobEffectInstance(MobEffects.DOLPHINS_GRACE, duration, amplifier1, true, false);
                MobEffectInstance breathingEffect = new MobEffectInstance(MobEffects.CONDUIT_POWER, duration, amplifier2, true, false);
                player.addEffect(dolphinEffect);
                player.addEffect(breathingEffect);
                    if (player.isSprinting()) {
                        Vec3 velocity = player.getDeltaMovement();
                        double speed = Math.sqrt(velocity.x * velocity.x + velocity.y * velocity.y + velocity.z * velocity.z);
                        double speedThreshold = 0.286;
                        if (speed > speedThreshold) {
                            int numParticles = 2;
                            for (int i = 0; i < numParticles; i++) {
                                float x_L = -0.08F;
                                float x_R = 0.08F;
                                float dynamicY = getDynamicYRotation(player.getXRot());
                                float dynamicX = getDynamicXOffset(player.getXRot());
                                Vec3 pos_L = getJointWithTranslation(Minecraft.getInstance().player, player, new Vec3f(x_L, dynamicY, dynamicX), Armatures.BIPED.get().legL);
                                Vec3 pos_R = getJointWithTranslation(Minecraft.getInstance().player, player, new Vec3f(x_R, dynamicY, dynamicX), Armatures.BIPED.get().legR);

                                if (pos_L != null) {
                                    Particle particle = Minecraft.getInstance().particleEngine.createParticle(ParticleTypes.BUBBLE, pos_L.x, pos_L.y, pos_L.z,
                                            player.getDeltaMovement().x, player.getDeltaMovement().y, player.getDeltaMovement().z);
                                    if (particle != null) {
                                        particle.scale(0.92f);
                                        particle.setLifetime(12);
                                    }
                                }
                                if (pos_R != null) {
                                    Particle particle = Minecraft.getInstance().particleEngine.createParticle(ParticleTypes.BUBBLE, pos_R.x, pos_R.y, pos_R.z,
                                            player.getDeltaMovement().x, player.getDeltaMovement().y, player.getDeltaMovement().z);
                                    if (particle != null) {
                                        particle.scale(0.92f);
                                        particle.setLifetime(12);
                                    }
                                }
                            }
                        }
                    }
            } else {
                if (player.hasEffect(MobEffects.DOLPHINS_GRACE)) {
                    MobEffectInstance effect = player.getEffect(MobEffects.DOLPHINS_GRACE);
                    if (effect != null && effect.getDuration() < 10) {
                        player.removeEffect(MobEffects.DOLPHINS_GRACE);
                        player.removeEffect(MobEffects.CONDUIT_POWER);
                    }
                }
            }
            if (!player.isInWater()) {
                if (player.hasEffect(MobEffects.DOLPHINS_GRACE)) {
                    MobEffectInstance effect = player.getEffect(MobEffects.DOLPHINS_GRACE);
                    if (effect != null && effect.getDuration() < 10) {
                        player.removeEffect(MobEffects.DOLPHINS_GRACE);
                        player.removeEffect(MobEffects.CONDUIT_POWER);
                    }
                }
            }
        });
    }

    @Override
    public void onRemoved(SkillContainer container) {
        PlayerEventListener listener = container.getExecutor().getEventListener();
        LivingEntity player = container.getExecutor().getOriginal();
        if (player != null && player.hasEffect(MobEffects.DOLPHINS_GRACE)) {
            MobEffectInstance effect = player.getEffect(MobEffects.DOLPHINS_GRACE);
            if (effect != null && effect.getDuration() < 10) {
                player.removeEffect(MobEffects.DOLPHINS_GRACE);
                player.removeEffect(MobEffects.CONDUIT_POWER);
            }
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