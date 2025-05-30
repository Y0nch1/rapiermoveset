package net.yonchi.refm.skill;

import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

import net.yonchi.refm.RapierForEpicfight;
import net.yonchi.refm.skill.guard.AmethystCompatSkills;
import net.yonchi.refm.skill.guard.RapierCompatSkills;
import net.yonchi.refm.skill.weaponinnate.*;

import yesman.epicfight.api.utils.PacketBufferCodec;
import yesman.epicfight.main.EpicFightMod;
import yesman.epicfight.skill.SkillDataKey;


public class RapierSkillDataKeys {
    public static final DeferredRegister<SkillDataKey<?>> DATA_KEYS = DeferredRegister.create(ResourceLocation.fromNamespaceAndPath(EpicFightMod.MODID, "skill_data_keys"), RapierForEpicfight.MOD_ID);

    public static final RegistryObject<SkillDataKey<Integer>> COMBO_COUNTER;
    public static final RegistryObject<SkillDataKey<Integer>> LAST_HIT_COUNT;
    public static final RegistryObject<SkillDataKey<Integer>> PENALTY_RESTORE_COUNTER;
    public static final RegistryObject<SkillDataKey<Float>> PENALTY;

    public RapierSkillDataKeys() {
    }
    static{
        COMBO_COUNTER = DATA_KEYS.register("combo_counter", () ->
                SkillDataKey.createSkillDataKey(PacketBufferCodec.INTEGER, 0, false, DeadlyBackflipSkill.class, DeadlyBackflip_EnderSkill.class, DeadlyBackflipSkill_OceanSkill.class, DeadlyBackflipSkill_WitherSkill.class, DeadlyBackflipSkill_AmethystSkill.class));
        LAST_HIT_COUNT = DATA_KEYS.register("last_hit_count", () ->
                SkillDataKey.createSkillDataKey(PacketBufferCodec.INTEGER, 0, false, DeadlyBackflipSkill.class, DeadlyBackflip_EnderSkill.class, DeadlyBackflipSkill_OceanSkill.class, DeadlyBackflipSkill_WitherSkill.class, DeadlyBackflipSkill_AmethystSkill.class));
        PENALTY_RESTORE_COUNTER = DATA_KEYS.register("penalty_restore_counter", () ->
                SkillDataKey.createSkillDataKey(PacketBufferCodec.INTEGER, 0, false, RapierCompatSkills.class, AmethystCompatSkills.class));
        PENALTY = DATA_KEYS.register("penalty", () ->
                SkillDataKey.createSkillDataKey(PacketBufferCodec.FLOAT, 0.0F, false, RapierCompatSkills.class, AmethystCompatSkills.class));
    }
}