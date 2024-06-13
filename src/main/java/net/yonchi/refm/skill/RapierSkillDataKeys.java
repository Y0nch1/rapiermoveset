package net.yonchi.refm.skill;

import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;
import yesman.epicfight.skill.SkillDataKey;
import net.yonchi.refm.skill.weaponinnate.DeadlyBackflipSkill;


public class RapierSkillDataKeys {
    public static final DeferredRegister<SkillDataKey<?>> DATA_KEYS = DeferredRegister.create(new ResourceLocation("epicfight", "skill_data_keys"), "refm");
    public static final RegistryObject<SkillDataKey<Integer>> LAST_HIT_COUNT;

    public RapierSkillDataKeys() {
    }

    static{
        LAST_HIT_COUNT = DATA_KEYS.register("last_hit_count", () -> {
            return SkillDataKey.createIntKey(0, false, new Class[]{DeadlyBackflipSkill.class});
        });
    }
}