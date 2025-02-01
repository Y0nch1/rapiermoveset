package net.yonchi.refm.skill.weaponinnate;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;

import net.yonchi.refm.gameasset.RapierAnimations;

import yesman.epicfight.api.animation.AnimationProvider;
import yesman.epicfight.api.animation.types.AttackAnimation;
import yesman.epicfight.skill.Skill;
import yesman.epicfight.skill.SkillContainer;
import yesman.epicfight.skill.weaponinnate.WeaponInnateSkill;
import yesman.epicfight.world.capabilities.entitypatch.player.PlayerPatch;
import yesman.epicfight.world.capabilities.entitypatch.player.ServerPlayerPatch;
import yesman.epicfight.world.capabilities.item.CapabilityItem;
import yesman.epicfight.world.effect.EpicFightMobEffects;
import yesman.epicfight.world.entity.eventlistener.PlayerEventListener.EventType;

import java.util.List;
import java.util.Map;
import java.util.UUID;

public class DeadlyBackflipSkill_WitherSkill extends WeaponInnateSkill {
    private static final UUID EVENT_UUID = UUID.fromString("1f6aea85-2194-4761-af8e-1a5c99c4f345");
    private final AnimationProvider<AttackAnimation> first;
    private final AnimationProvider<AttackAnimation> second;

    public DeadlyBackflipSkill_WitherSkill(Builder<? extends Skill> builder) {
        super(builder);
        this.first = () -> (AttackAnimation)RapierAnimations.DEADLYBACKFLIP_FIRST;
        this.second = () -> (AttackAnimation)RapierAnimations.DEADLYBACKFLIP_SECOND_WITHER;
    }

    @Override
    public void onInitiate(SkillContainer container) {
        super.onInitiate(container);
        container.getExecuter().getEventListener().addEventListener(EventType.ATTACK_ANIMATION_END_EVENT, EVENT_UUID, (event) -> {
            if (RapierAnimations.DEADLYBACKFLIP_FIRST.equals(event.getAnimation())) {
                List<LivingEntity> hurtEntities = event.getPlayerPatch().getCurrenltyHurtEntities();

                if (!hurtEntities.isEmpty() && hurtEntities.get(0).isAlive()) {
                    event.getPlayerPatch().getServerAnimator().getPlayerFor(null).reset();
                    event.getPlayerPatch().playAnimationSynchronized(this.second.get(), 0);
                    event.getPlayerPatch().getCurrenltyHurtEntities().clear();
                }
            }
        });
    }

    @Override
    public void onRemoved(SkillContainer container) {
        container.getExecuter().getEventListener().removeListener(EventType.ATTACK_ANIMATION_END_EVENT, EVENT_UUID);
    }

    @Override
    public boolean checkExecuteCondition(PlayerPatch<?> executer) {
        Entity target = executer.getTarget();
        if (target != null && target.isAlive()) {
            // Check distance
            double distance = executer.getOriginal().distanceTo(target);
            double minDistance = 1.2;
            double maxDistance = 8.0;

            // Obtain hitbox for accuracy
            AABB targetBox = target.getBoundingBox();
            Vec3 targetPos = new Vec3(
                    (targetBox.minX + targetBox.maxX) / 2.0,
                    (targetBox.minY + targetBox.maxY) / 2.0,
                    (targetBox.minZ + targetBox.maxZ) / 2.0
            );
            Vec3 playerPos = executer.getOriginal().position();
            // DEBUG
            // System.out.println("Distance to target: " + distance);
            return (distance >= minDistance && distance <= maxDistance);
        }
        return false;
    }

    @Override
    public void executeOnServer(ServerPlayerPatch executer, FriendlyByteBuf args) {
        executer.playAnimationSynchronized(this.first.get(), 0);
        ((ServerPlayer)executer.getOriginal()).addEffect(new MobEffectInstance((MobEffect) EpicFightMobEffects.STUN_IMMUNITY.get(), 66, 0, true, false, false));
        LivingEntity target = (LivingEntity) executer.getTarget();
        if (target != null && target.isAlive()) {
            target.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 36, 50));
        }
        super.executeOnServer(executer, args);
    }

    @Override
    public List<Component> getTooltipOnItem(ItemStack itemStack, CapabilityItem cap, PlayerPatch<?> playerCap) {
        List<Component> list = super.getTooltipOnItem(itemStack, cap, playerCap);
        this.generateTooltipforPhase(list, itemStack, cap, playerCap, (Map) this.properties.get(0), "Kick:");
        this.generateTooltipforPhase(list, itemStack, cap, playerCap, (Map) this.properties.get(1), "Rage Strikes:");
        this.generateTooltipforPhase(list, itemStack, cap, playerCap, (Map) this.properties.get(2), "Wither Fury:");
        return list;
    }

    @Override
    public WeaponInnateSkill registerPropertiesToAnimation() {
        this.first.get().phases[0].addProperties(this.properties.get(0).entrySet());
        this.second.get().phases[0].addProperties(this.properties.get(1).entrySet());
        this.second.get().phases[1].addProperties(this.properties.get(1).entrySet());
        this.second.get().phases[2].addProperties(this.properties.get(1).entrySet());
        this.second.get().phases[3].addProperties(this.properties.get(1).entrySet());
        this.second.get().phases[4].addProperties(this.properties.get(2).entrySet());

        return this;
    }
}