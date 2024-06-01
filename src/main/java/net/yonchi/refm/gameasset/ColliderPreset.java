package net.yonchi.refm.gameasset;

import yesman.epicfight.api.collider.Collider;
import yesman.epicfight.api.collider.MultiOBBCollider;
import yesman.epicfight.api.collider.OBBCollider;

public class ColliderPreset {
    public static final Collider RAPIER = new MultiOBBCollider(5, 0.4D, 0.4D, 0.7D, 0D, 0D, -0.7D);
}
