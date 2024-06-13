package net.yonchi.refm.skill.weaponinnate;

import java.util.List;
import java.util.Map;
import java.util.UUID;

import com.google.common.collect.Maps;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.yonchi.refm.skill.RapierSkillDataKeys;
import yesman.epicfight.api.animation.AnimationProvider;
import yesman.epicfight.api.animation.types.AttackAnimation;
import yesman.epicfight.api.animation.types.StaticAnimation;
import yesman.epicfight.client.world.capabilites.entitypatch.player.LocalPlayerPatch;
import yesman.epicfight.gameasset.Animations;
import yesman.epicfight.skill.Skill;
import yesman.epicfight.skill.SkillCategories;
import yesman.epicfight.skill.SkillContainer;
import yesman.epicfight.skill.SkillDataKey;
import yesman.epicfight.skill.weaponinnate.BladeRushSkill;
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
            if (this.first.get().equals(event.getAnimation())) {
                container.getDataManager().setDataSync((SkillDataKey) RapierSkillDataKeys.LAST_HIT_COUNT.get(), ((ServerPlayerPatch) event.getPlayerPatch()).getCurrenltyHurtEntities().size(), (ServerPlayer) ((ServerPlayerPatch) event.getPlayerPatch()).getOriginal());
            }

        });
        container.getExecuter().getEventListener().addEventListener(EventType.DEALT_DAMAGE_EVENT_PRE, EVENT_UUID, (event) -> {
            if (this.second.get().equals(event.getDamageSource().getAnimation())) {
                float impact = event.getDamageSource().getImpact();
                event.getDamageSource().setImpact(impact + (float) (Integer) container.getDataManager().getDataValue((SkillDataKey) RapierSkillDataKeys.LAST_HIT_COUNT.get()) * 0.4F);
            }

        });
    }

    public void onRemoved(SkillContainer container) {
        container.getExecuter().getEventListener().removeListener(EventType.DEALT_DAMAGE_EVENT_PRE, EVENT_UUID);
    }

    public void executeOnServer(ServerPlayerPatch executer, FriendlyByteBuf args) {
        executer.playAnimationSynchronized(this.first.get(), 0.0F);
        super.executeOnServer(executer, args);
    }

    public List<Component> getTooltipOnItem(ItemStack itemStack, CapabilityItem cap, PlayerPatch<?> playerCap) {
        List<Component> list = super.getTooltipOnItem(itemStack, cap, playerCap);
        this.generateTooltipforPhase(list, itemStack, cap, playerCap, (Map) this.properties.get(0), "Front Kick:");
        this.generateTooltipforPhase(list, itemStack, cap, playerCap, (Map) this.properties.get(1), "Backflip Stab:");
        return list;
    }

    public WeaponInnateSkill registerPropertiesToAnimation() {
        this.first.get().phases[0].addProperties(((Map) this.properties.get(0)).entrySet());
        this.second.get().phases[0].addProperties(((Map) this.properties.get(1)).entrySet());
        return this;
    }
}
