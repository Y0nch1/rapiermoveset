package net.yonchi.refm.skill;

import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;
import net.yonchi.refm.RapierForEpicfight;
import yesman.epicfight.main.EpicFightMod;
import yesman.epicfight.skill.BasicAttack;
import yesman.epicfight.skill.SkillDataKey;
import net.yonchi.refm.skill.weaponinnate.DeadlyBackflipSkill;


public class RapierSkillDataKeys {
    public static final DeferredRegister<SkillDataKey<?>> DATA_KEYS = DeferredRegister.create(new ResourceLocation(EpicFightMod.MODID, "skill_data_keys"), RapierForEpicfight.MOD_ID);

    public static final RegistryObject<SkillDataKey<Integer>> COMBO_COUNTER;
    public static final RegistryObject<SkillDataKey<Integer>> LAST_HIT_COUNT;

    public RapierSkillDataKeys() {
    }

    static{
        COMBO_COUNTER = DATA_KEYS.register("combo_counter", () -> {
            return SkillDataKey.createIntKey(0, false, new Class[]{BasicAttack.class, DeadlyBackflipSkill.class});
        });
        LAST_HIT_COUNT = DATA_KEYS.register("last_hit_count", () -> {
            return SkillDataKey.createIntKey(0, false, new Class[]{DeadlyBackflipSkill.class});
        });
    }
}