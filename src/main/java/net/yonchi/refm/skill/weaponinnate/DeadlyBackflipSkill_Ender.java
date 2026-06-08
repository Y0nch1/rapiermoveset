package net.yonchi.refm.skill.weaponinnate;

import java.util.List;
import java.util.Map;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;

import net.yonchi.refm.gameasset.RapierAnimations;

import yesman.epicfight.api.animation.types.AttackAnimation;
import yesman.epicfight.api.asset.AssetAccessor;
import yesman.epicfight.api.event.EntityEventListener;
import yesman.epicfight.api.event.EpicFightEventHooks;
import yesman.epicfight.registry.entries.EpicFightMobEffects;
import yesman.epicfight.skill.SkillContainer;
import yesman.epicfight.skill.SkillSlots;
import yesman.epicfight.skill.weaponinnate.WeaponInnateSkill;
import yesman.epicfight.world.capabilities.entitypatch.player.PlayerPatch;
import yesman.epicfight.world.capabilities.item.CapabilityItem;


public class DeadlyBackflipSkill_Ender extends WeaponInnateSkill {
    public final AssetAccessor<? extends AttackAnimation> first;
    public final AssetAccessor<? extends AttackAnimation> second;
    public final AssetAccessor<? extends AttackAnimation> fail;

    public DeadlyBackflipSkill_Ender(WeaponInnateSkill.Builder<?> builder) {
        super(builder);
        this.first = RapierAnimations.DEADLYBACKFLIP_FIRST;
        this.second = RapierAnimations.DEADLYBACKFLIP_SECOND_ENDER;
        this.fail = RapierAnimations.DEADLYBACKFLIP_FAIL;
    }

    @Override
    public void onInitiate(SkillContainer container, EntityEventListener eventListener) {
        super.onInitiate(container, eventListener);
        List<LivingEntity> hurtEntities = container.getExecutor().getCurrentlyActuallyHitEntities();
        SkillContainer innateSkill = container.getExecutor().getSkill(SkillSlots.WEAPON_INNATE);
        eventListener.registerEvent(EpicFightEventHooks.Animation.END, (event) -> {
            if (this.first.equals(event.getAnimation()) && !this.fail.equals(event.getAnimation())) {
                if (!hurtEntities.isEmpty() && hurtEntities.getFirst().isAlive()) {
                    container.getExecutor().reserveAnimation(this.second);
                    container.getExecutor().getServerAnimator().getPlayerFor(null).reset();
                }
                if (!eventListener.getEntityPatch().isLastAttackSuccess() && !this.second.equals(event.getAnimation()) && !this.fail.equals(event.getAnimation())) {
                    container.getExecutor().reserveAnimation(this.fail);
                }
                if (!container.getExecutor().isLogicalClient()) {
                    if (innateSkill != null && innateSkill.getSkill() != null && eventListener.getEntityPatch().isLastAttackSuccess() && hurtEntities.getFirst().isDeadOrDying()) {
                        innateSkill.getSkill().setConsumptionSynchronize(innateSkill, this.consumption * 0.75F);
                    }
                }
            }
            if (this.second.equals(event.getAnimation()) || this.fail.equals(event.getAnimation())) {container.getExecutor().getCurrentlyActuallyHitEntities().clear();}
        }, this);
    }

    @Override
    public void executeOnServer(SkillContainer container, CompoundTag arguments) {
        container.getExecutor().playAnimationSynchronized(this.first, 0);
        super.executeOnServer(container, arguments);
        ((ServerPlayer) container.getExecutor().getOriginal()).addEffect(new MobEffectInstance(
                BuiltInRegistries.MOB_EFFECT.wrapAsHolder(EpicFightMobEffects.STUN_IMMUNITY.get()),
                38, 0, true, false, false
        ));
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