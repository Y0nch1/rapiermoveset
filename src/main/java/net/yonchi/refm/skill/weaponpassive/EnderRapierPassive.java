package net.yonchi.refm.skill.weaponpassive;

import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;

import yesman.epicfight.skill.SkillBuilder;
import yesman.epicfight.skill.SkillContainer;
import yesman.epicfight.skill.passive.PassiveSkill;
import yesman.epicfight.world.entity.eventlistener.PlayerEventListener;

import java.util.UUID;
import java.util.Random;

public class EnderRapierPassive extends PassiveSkill {
    private static final UUID EVENT_UUID = UUID.fromString("06542d5a-7b38-4db8-9959-fa6a4f6cbbc0");
    private long lastTeleportTime = 0;
    private static final long COOLDOWN_TIME = 3600;

    public EnderRapierPassive(SkillBuilder<? extends PassiveSkill> builder) {
        super(builder);
    }

    @Override
    public void onInitiate(SkillContainer container) {
        super.onInitiate(container);
        container.getExecutor().getEventListener().addEventListener(PlayerEventListener.EventType.PROJECTILE_HIT_EVENT, EVENT_UUID, (event) -> {
            LivingEntity target = event.getPlayerPatch().getOriginal();
            if (target == null || !target.isAlive()) return;

            long currentTime = System.currentTimeMillis();
            if (currentTime - lastTeleportTime < COOLDOWN_TIME) {
                event.setCanceled(false);
            } else {
                event.setCanceled(true);
                teleportToSafeLocation(target);
            }
        });
    }

    @Override
    public void onRemoved(SkillContainer container) {
        PlayerEventListener listener = container.getExecutor().getEventListener();
        listener.removeListener(PlayerEventListener.EventType.PROJECTILE_HIT_EVENT, EVENT_UUID);
    }

    private void teleportToSafeLocation(LivingEntity entity) {
        lastTeleportTime = System.currentTimeMillis();

        Random random = new Random();
        int attempts = 24;

        for (int i = 0; i < attempts; i++) {
            float yaw = entity.getYHeadRot();
            float adjustedYaw = yaw + 90;
            if (adjustedYaw > 180) adjustedYaw -= 360;
            if (adjustedYaw < -180) adjustedYaw += 360;

            double distance = 1.5 + random.nextDouble();
            double offsetX = Math.cos(Math.toRadians(adjustedYaw)) * distance;
            double offsetZ = Math.sin(Math.toRadians(adjustedYaw)) * distance;
            double newX = entity.getX() + offsetX;
            double newZ = entity.getZ() + offsetZ;
            int newY = (int) Math.floor(entity.getY());

            BlockPos pos = new BlockPos((int) Math.floor(newX), newY, (int) Math.floor(newZ));
            while (pos.getY() > entity.level().getMinBuildHeight() && !entity.level().getBlockState(pos).isAir()) {
                pos = pos.below();
            }

            BlockPos above = pos.above();
            if (entity.level().getBlockState(pos).isAir() && entity.level().getBlockState(above).isAir()) {
                spawnPortalParticles(entity, entity.level().random);
                entity.teleportTo(newX, pos.getY(), newZ);
                spawnPortalParticles(entity, entity.level().random);
                entity.level().playSound(null, entity.blockPosition(), SoundEvents.CHORUS_FRUIT_TELEPORT, SoundSource.PLAYERS, 1.0F, 1.0F);
                return;
            }
        }
    }

    private void spawnPortalParticles(Entity entity, RandomSource random) {
        ClientLevel clientLevel = Minecraft.getInstance().level;
        if (clientLevel != null) {
            double horizontalRadius = 1.2;
            for (int i = 0; i < 69; i++) {
                double xOffset = (random.nextDouble() - 0.4) * horizontalRadius;
                double yOffset = (random.nextDouble() - random.nextDouble()) * 1.4D;
                double zOffset = (random.nextDouble() - 0.4) * horizontalRadius;

                clientLevel.addParticle(ParticleTypes.PORTAL,
                        entity.getX() + xOffset,
                        entity.getY() + yOffset,
                        entity.getZ() + zOffset,
                        0,
                        0.2,
                        0
                );
            }
        }
    }
}
