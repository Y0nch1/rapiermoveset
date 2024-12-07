package net.yonchi.refm.skill.weaponpassive;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;

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
        container.getExecuter().getEventListener().addEventListener(PlayerEventListener.EventType.DEALT_DAMAGE_EVENT_DAMAGE, EVENT_UUID, (event) -> {
            LivingEntity target = event.getPlayerPatch().getOriginal();
            if (target == null) target.getCommandSenderWorld();
            if (event.getAttackDamage() < 1.5) {
                return;
            }
            if (!target.isInWater()) {
                return;
            }
            int duration = 146;
            int amplifier = 0;
            MobEffectInstance dolphinEffect = new MobEffectInstance(MobEffects.DOLPHINS_GRACE, duration, amplifier, true, false);
            MobEffectInstance breathingEffect = new MobEffectInstance(MobEffects.WATER_BREATHING, duration, amplifier, true, false);
            target.addEffect(dolphinEffect);
            target.addEffect(breathingEffect);
        });
    }

    @Override
    public void onRemoved(SkillContainer container) {
        PlayerEventListener listener = container.getExecuter().getEventListener();
        listener.removeListener(PlayerEventListener.EventType.ACTION_EVENT_CLIENT, EVENT_UUID);
    }
}
