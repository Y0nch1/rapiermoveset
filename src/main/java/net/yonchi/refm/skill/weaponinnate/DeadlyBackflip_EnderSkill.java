package net.yonchi.refm.skill.weaponinnate;

import java.util.List;
import java.util.Map;
import java.util.UUID;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
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
import yesman.epicfight.world.entity.eventlistener.PlayerEventListener.EventType;

public class DeadlyBackflip_EnderSkill extends WeaponInnateSkill {
    private static final UUID EVENT_UUID = UUID.fromString("1f6aea85-2194-4761-af8e-1a5c99c4f415");
    private final AnimationProvider<AttackAnimation> first;
    private final AnimationProvider<AttackAnimation> second;

    public DeadlyBackflip_EnderSkill(Builder<? extends Skill> builder) {
        super(builder);
        this.first = () -> (AttackAnimation)RapierAnimations.DEADLYBACKFLIP_FIRST;
        this.second = () -> (AttackAnimation)RapierAnimations.DEADLYBACKFLIP_SECOND_ENDER;
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
            //Check distance able to do the attack
            double distance = executer.getOriginal().distanceTo(target);
            double minDistance = 1.5;
            double maxDistance = 8.0;

            //Grades for innate to be able depending on the head of the player
            Vec3 playerPos = executer.getOriginal().position();
            Vec3 targetPos = target.position();
            Vec3 directionToTarget = targetPos.subtract(playerPos).normalize();
            Vec3 playerLook = executer.getOriginal().getLookAngle().normalize();

            double angle = Math.acos(playerLook.dot(directionToTarget));
            double angleInDegrees = Math.toDegrees(angle);
            double angleThreshold = 92.0;

            //DEBUG distance and angle
            //System.out.println("Distance to target: " + distance);
            //System.out.println("Angle to target: " + angleInDegrees + " degrees");

            //Check distance and angle of the player
            if (distance >= minDistance && distance <= maxDistance && angleInDegrees <= angleThreshold) {
                return true;
            }
        }
        return false;
    }


    @Override
    public void executeOnServer(ServerPlayerPatch executer, FriendlyByteBuf args) {
        executer.playAnimationSynchronized(this.first.get(), 0);
        LivingEntity target = (LivingEntity) executer.getTarget();
        if (target != null && target.isAlive()) {
            target.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 12, 30));
        }
        super.executeOnServer(executer, args);
    }

    @Override
    public List<Component> getTooltipOnItem(ItemStack itemStack, CapabilityItem cap, PlayerPatch<?> playerCap) {
        List<Component> list = super.getTooltipOnItem(itemStack, cap, playerCap);
        this.generateTooltipforPhase(list, itemStack, cap, playerCap, (Map) this.properties.get(0), "Kick:");
        this.generateTooltipforPhase(list, itemStack, cap, playerCap, (Map) this.properties.get(1), "Dance:");
        this.generateTooltipforPhase(list, itemStack, cap, playerCap, (Map) this.properties.get(2), "Ballete:");
        return list;
    }

    @Override
    public WeaponInnateSkill registerPropertiesToAnimation() {
        this.first.get().phases[0].addProperties(this.properties.get(0).entrySet());
        this.second.get().phases[0].addProperties(this.properties.get(1).entrySet());
        this.second.get().phases[1].addProperties(this.properties.get(1).entrySet());
        this.second.get().phases[2].addProperties(this.properties.get(2).entrySet());

        return this;
    }
}