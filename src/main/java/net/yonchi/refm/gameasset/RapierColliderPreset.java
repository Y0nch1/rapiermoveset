package net.yonchi.refm.gameasset;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;

import net.minecraft.resources.ResourceLocation;
import net.yonchi.refm.RapierForEpicfight;
import yesman.epicfight.api.collider.Collider;
import yesman.epicfight.api.collider.MultiOBBCollider;

public class RapierColliderPreset {

    private static final BiMap<ResourceLocation, Collider> PRESETS = HashBiMap.create();

    public static Collider registerCollider(ResourceLocation rl, Collider collider) {
        if (PRESETS.containsKey(rl)) {
            throw new IllegalStateException("Collider named " + rl + " already registered.");
        }
        PRESETS.put(rl, collider);

        return collider;
    }

        public static final Collider RAPIER = registerCollider(new ResourceLocation(RapierForEpicfight.MOD_ID, "rapier"), new MultiOBBCollider(3, 0.6D, 0.5D, 1.2D, 0D, 0.1D, -1.0D));
    public static final Collider KICK = registerCollider(new ResourceLocation(RapierForEpicfight.MOD_ID, "kick"), new MultiOBBCollider(3, 0.7D, 0.7D, 0.6D, 0D, 0D, 0D));
}

//https://github.com/Yesssssman/epicfightmod/blob/1.20.1/src/main/java/yesman/epicfight/gameasset/ColliderPreset.java#L74