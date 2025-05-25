package net.yonchi.refm.skill.weaponpassive;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.Particle;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.util.RandomSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.Vec3;

import yesman.epicfight.api.utils.math.Vec3f;
import yesman.epicfight.gameasset.Armatures;
import yesman.epicfight.skill.SkillBuilder;
import yesman.epicfight.skill.SkillContainer;
import yesman.epicfight.skill.passive.PassiveSkill;
import yesman.epicfight.world.entity.eventlistener.PlayerEventListener;

import static net.yonchi.refm.api.animation.JointTrack.getJointWithTranslation;

public class WitherRapierPassive extends PassiveSkill {
    private static final UUID EVENT_UUID = UUID.fromString("f068901b-b297-4194-9c94-970c8a7030e4");

    public WitherRapierPassive(SkillBuilder<? extends PassiveSkill> builder) {
        super(builder);
    }

    @Override
    public void onInitiate(SkillContainer container) {
        super.onInitiate(container);

        container.getExecutor().getEventListener().addEventListener(PlayerEventListener.EventType.DEALT_DAMAGE_EVENT_DAMAGE, EVENT_UUID, (event) -> {
            LivingEntity target = event.getTarget();
            if (event.getAttackDamage() < 1.5) {
                return;
            }
            int duration = 33;
            int amplifier = target instanceof Player ? 0 : 2;

            MobEffectInstance witherEffect = new MobEffectInstance(MobEffects.WITHER, duration, amplifier, false, false);
            target.addEffect(witherEffect);
        });

        container.getExecutor().getEventListener().addEventListener(PlayerEventListener.EventType.MOVEMENT_INPUT_EVENT, EVENT_UUID, (event) -> {
            LivingEntity player = event.getPlayerPatch().getOriginal();
            RandomSource random = RandomSource.create();
            if (player == null) return;
            if (!player.isInWater() && !player.isCrouching() && !player.isFallFlying() && player.isSprinting()) {
                int numParticles = 3;
                for (int i = 0; i < numParticles; i++) {
                    float L = -0.1F;
                    float R = 0.1F;
                    double xOffset = (random.nextDouble() - 0.3) * 0.3;
                    double yOffset = (random.nextDouble() - random.nextDouble()) * 0.3D;
                    double zOffset = (random.nextDouble() - 0.3) * 0.3;
                    Vec3 basePos = getJointWithTranslation(Minecraft.getInstance().player, player, new Vec3f(0F, -1F, -0.3F), Armatures.BIPED.get().rootJoint);
                    List<Vec3> positions = new ArrayList<>();
                    positions.add(getJointWithTranslation(Minecraft.getInstance().player, player, new Vec3f(L, 0F, 0.6F), Armatures.BIPED.get().head));
                    positions.add(getJointWithTranslation(Minecraft.getInstance().player, player, new Vec3f(R, 0F, 0.6F), Armatures.BIPED.get().head));
                    positions.add(getJointWithTranslation(Minecraft.getInstance().player, player, new Vec3f(L, 0.06F, 0.1F), Armatures.BIPED.get().chest));
                    positions.add(getJointWithTranslation(Minecraft.getInstance().player, player, new Vec3f(R, 0.06F, 0.1F), Armatures.BIPED.get().chest));
                    positions.add(getJointWithTranslation(Minecraft.getInstance().player, player, new Vec3f(0F, 0.6F, 0F), Armatures.BIPED.get().armL));
                    positions.add(getJointWithTranslation(Minecraft.getInstance().player, player, new Vec3f(0F, 0.6F, 0F), Armatures.BIPED.get().armR));
                    positions.add(getJointWithTranslation(Minecraft.getInstance().player, player, new Vec3f(0F, 0.2F, 0.2F), Armatures.BIPED.get().thighL));
                    positions.add(getJointWithTranslation(Minecraft.getInstance().player, player, new Vec3f(0F, 0.2F, 0.2F), Armatures.BIPED.get().thighR));
                    for (Vec3 pos : positions) {
                        if (pos != null) {
                            Vec3 ovalPos = pos.add(xOffset, yOffset, zOffset);
                            Particle particle = Minecraft.getInstance().particleEngine.createParticle(ParticleTypes.SMOKE, ovalPos.x, ovalPos.y, ovalPos.z, player.getDeltaMovement().x, 0.052F, player.getDeltaMovement().z);
                            if (particle != null) {
                                particle.setLifetime(6);
                            }
                        }
                    }
                    if (basePos != null) {
                        Particle particle1 = Minecraft.getInstance().particleEngine.createParticle(ParticleTypes.LARGE_SMOKE, basePos.x, basePos.y, basePos.z, player.getDeltaMovement().x, 0.02F, player.getDeltaMovement().z);
                        Particle particle2 = Minecraft.getInstance().particleEngine.createParticle(ParticleTypes.LARGE_SMOKE, basePos.x, basePos.y + 0.26F, basePos.z, player.getDeltaMovement().x, 0.012F, player.getDeltaMovement().z);
                        if (particle1 != null) {
                            particle1.scale(0.92F);
                            particle1.setLifetime(13);
                        }
                        if (particle2 != null) {
                            particle2.scale(0.96F);
                            particle2.setLifetime(3);
                        }
                    }
                }
                MobEffectInstance slowfallEffect = new MobEffectInstance(MobEffects.SLOW_FALLING, 5, 0, true, false);
                player.addEffect(slowfallEffect);
            } else {
                if (player.hasEffect(MobEffects.SLOW_FALLING)) {
                    MobEffectInstance effect = player.getEffect(MobEffects.SLOW_FALLING);
                    if (effect != null && effect.getDuration() < 10) {
                        player.removeEffect(MobEffects.SLOW_FALLING);
                    }
                }
            }
        });
    }

    @Override
    public void onRemoved(SkillContainer container) {
        super.onRemoved(container);
        LivingEntity player = container.getExecutor().getOriginal();
        if (player != null && player.hasEffect(MobEffects.SLOW_FALLING)) {
            MobEffectInstance effect = player.getEffect(MobEffects.SLOW_FALLING);
            if (effect != null && effect.getDuration() < 10) {
                player.removeEffect(MobEffects.SLOW_FALLING);
            }
        }
        container.getExecutor().getEventListener().removeListener(PlayerEventListener.EventType.DEALT_DAMAGE_EVENT_DAMAGE, EVENT_UUID);
        container.getExecutor().getEventListener().removeListener(PlayerEventListener.EventType.MOVEMENT_INPUT_EVENT, EVENT_UUID);
    }
}