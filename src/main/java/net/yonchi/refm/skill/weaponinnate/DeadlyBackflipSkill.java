package net.yonchi.refm.skill.weaponinnate;

import java.util.List;
import java.util.Map;
import java.util.UUID;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import yesman.epicfight.api.animation.AnimationProvider;
import yesman.epicfight.api.animation.types.AttackAnimation;
import yesman.epicfight.api.animation.types.DynamicAnimation;
import yesman.epicfight.gameasset.Animations;
import yesman.epicfight.skill.Skill;
import yesman.epicfight.skill.SkillContainer;
import yesman.epicfight.skill.weaponinnate.WeaponInnateSkill;
import yesman.epicfight.world.capabilities.entitypatch.player.PlayerPatch;
import yesman.epicfight.world.capabilities.entitypatch.player.ServerPlayerPatch;
import yesman.epicfight.world.capabilities.item.CapabilityItem;
import yesman.epicfight.world.entity.eventlistener.PlayerEventListener.EventType;
import net.yonchi.refm.gameasset.RapierAnimations;


public class DeadlyBackflipSkill extends WeaponInnateSkill {
    private static final UUID EVENT_UUID = UUID.fromString("1f6aea85-2194-4761-af8e-1a5c99c4f414");
    private AnimationProvider.AttackAnimationProvider first = () -> {
        return (AttackAnimation) RapierAnimations.DEADLYBACKFLIP_FIRST;
    };
    private AnimationProvider.AttackAnimationProvider second = () -> {
        return (AttackAnimation) RapierAnimations.DEADLYBACKFLIP_SECOND;
    };

    public DeadlyBackflipSkill(Builder<? extends Skill> builder) { super(builder); }

    public void onInitiate(SkillContainer container) {
        super.onInitiate(container);
        container.getExecuter().getEventListener().addEventListener(EventType.ATTACK_ANIMATION_END_EVENT, EVENT_UUID, (event) -> {
            if (RapierAnimations.DEADLYBACKFLIP_FIRST.equals(event.getAnimation())) {
                List<LivingEntity> hurtEntities = ((ServerPlayerPatch)event.getPlayerPatch()).getCurrenltyHurtEntities();
                if (hurtEntities.size() > 0 && ((LivingEntity)hurtEntities.get(0)).isAlive()) {
                    ((ServerPlayerPatch)event.getPlayerPatch()).reserveAnimation(this.second.get());
                    ((ServerPlayerPatch)event.getPlayerPatch()).getServerAnimator().getPlayerFor((DynamicAnimation)null).reset();
                    ((ServerPlayerPatch)event.getPlayerPatch()).getCurrenltyHurtEntities().clear();
                    this.second.get().tick(event.getPlayerPatch());
                }
            }
        });
    }

    public void onRemoved(SkillContainer container) {
        container.getExecuter().getEventListener().removeListener(EventType.ATTACK_ANIMATION_END_EVENT, EVENT_UUID);
    }

    public boolean checkExecuteCondition(PlayerPatch<?> executer) {
        return executer.getTarget() != null && executer.getTarget().isAlive();
    }

    public void executeOnServer(ServerPlayerPatch executer, FriendlyByteBuf args) {
        executer.playAnimationSynchronized(this.first.get(), 0.0F);
        super.executeOnServer(executer, args);
    }

    public List<Component> getTooltipOnItem(ItemStack itemStack, CapabilityItem cap, PlayerPatch<?> playerCap) {
        List<Component> list = super.getTooltipOnItem(itemStack, cap, playerCap);
        this.generateTooltipforPhase(list, itemStack, cap, playerCap, (Map) this.properties.get(0), "Kick:");
        this.generateTooltipforPhase(list, itemStack, cap, playerCap, (Map) this.properties.get(1), "Stab:");
        return list;
    }

    public WeaponInnateSkill registerPropertiesToAnimation() {
        this.first.get().phases[0].addProperties(((Map) this.properties.get(0)).entrySet());
        this.second.get().phases[0].addProperties(((Map) this.properties.get(1)).entrySet());
        return this;
    }
}
