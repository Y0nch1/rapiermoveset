package net.yonchi.refm.skill.weaponpassive;

import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;

import net.minecraft.world.phys.Vec3;
import yesman.epicfight.skill.Skill;
import yesman.epicfight.skill.SkillContainer;
import yesman.epicfight.skill.passive.PassiveSkill;
import yesman.epicfight.world.entity.eventlistener.PlayerEventListener;

import java.util.UUID;

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
                        double speed = Math.sqrt(velocity.x * velocity.x + velocity.z * velocity.z);
                        double speedThreshold = 0.299;
                        if (speed > speedThreshold) {
                            double hipY = target.getEyeY() - 0.16;
                            int numParticles = 5;
                            double radius = 0.2;
                            for (int i = 0; i < numParticles; i++) {
                                double angle = (Math.PI * 2 * i) / numParticles;
                                double particleX = target.getX() + (Math.cos(angle) * radius);
                                double particleZ = target.getZ() + (Math.sin(angle) * radius);
                                target.level().addParticle(ParticleTypes.BUBBLE, particleX, hipY, particleZ, 0, 0.1, 0);
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
}
