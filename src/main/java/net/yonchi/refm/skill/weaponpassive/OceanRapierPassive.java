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
        container.getExecuter().getEventListener().addEventListener(PlayerEventListener.EventType.CLIENT_ITEM_USE_EVENT, EVENT_UUID, (event) -> {
            LivingEntity target = event.getPlayerPatch().getOriginal();
            if (target == null || target.getCommandSenderWorld().isClientSide){
                return;
            }
            MobEffectInstance waterSpeed = new MobEffectInstance (MobEffects.DOLPHINS_GRACE, 200, 1 );
            target.addEffect(waterSpeed);
        });
    }

    @Override
    public void onRemoved(SkillContainer container) {
        PlayerEventListener listener = container.getExecuter().getEventListener();
        listener.removeListener(PlayerEventListener.EventType.CLIENT_ITEM_USE_EVENT, EVENT_UUID);
    }
}
