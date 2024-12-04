package net.yonchi.refm.skill.weaponpassive;

import java.util.UUID;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;

import yesman.epicfight.skill.Skill;
import yesman.epicfight.skill.SkillContainer;
import yesman.epicfight.skill.passive.PassiveSkill;
import yesman.epicfight.world.entity.eventlistener.PlayerEventListener;

public class WitherRapierPassive extends PassiveSkill {
    private static final UUID EVENT_UUID = UUID.fromString("f068901b-b297-4194-9c94-970c8a7030e4");

    public WitherRapierPassive(Builder<? extends Skill> builder) {
        super(builder);
    }

    @Override
    public void onInitiate(SkillContainer container) {
        super.onInitiate(container);
        container.getExecuter().getEventListener().addEventListener(PlayerEventListener.EventType.DEALT_DAMAGE_EVENT_DAMAGE, EVENT_UUID, (event) -> {
            LivingEntity target = event.getTarget();
            if (target == null || target.getCommandSenderWorld().isClientSide){
                return;
            }
            if (event.getAttackDamage() < 1.5) {
                return;
            }
            // First is player, second is mobs
            int duration = 33;
            int amplifier = target instanceof Player ? 0 : 2;
            // Add wither effect
            MobEffectInstance witherEffect = new MobEffectInstance(MobEffects.WITHER, duration, amplifier);
            target.addEffect(witherEffect);
        });
    }

    @Override
    public void onRemoved(SkillContainer container) {
        super.onRemoved(container);
        container.getExecuter().getEventListener().removeListener(PlayerEventListener.EventType.DEALT_DAMAGE_EVENT_DAMAGE, EVENT_UUID);
    }
}