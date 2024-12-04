package net.yonchi.refm.api.animation.property;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.phys.Vec3;

import yesman.epicfight.api.animation.Keyframe;
import yesman.epicfight.api.animation.TransformSheet;
import yesman.epicfight.api.animation.property.AnimationProperty.AttackAnimationProperty;
import yesman.epicfight.api.animation.property.MoveCoordFunctions;
import yesman.epicfight.api.utils.math.MathUtils;
import yesman.epicfight.api.utils.math.Vec3f;

public class RapierMoveCoordFunctions {

    public static final MoveCoordFunctions.MoveCoordSetter TRACE_LOC_TARGET = (self, entitypatch, transformSheet) -> {
        LivingEntity attackTarget = entitypatch.getTarget();

        if (attackTarget != null && !self.getRealAnimation().getProperty(AttackAnimationProperty.FIXED_MOVE_DISTANCE).orElse(false)) {
            TransformSheet transform = self.getCoord().copyAll();
            Keyframe[] keyframes = transform.getKeyframes();
            int startFrame = 0;
            int endFrame = keyframes.length - 1;
            Vec3f keyLast = keyframes[endFrame].transform().translation();
            Vec3 pos = entitypatch.getOriginal().position();
            Vec3 targetpos = attackTarget.position();
            Vec3 toTarget = targetpos.subtract(pos);
            Vec3 viewVec = entitypatch.getOriginal().getViewVector(1.0F);
            float horizontalDistance = Math.max((float)toTarget.horizontalDistance() - (attackTarget.getBbWidth() + entitypatch.getOriginal().getBbWidth()) * 0.75F, 0.0F);
            Vec3f worldPosition = new Vec3f(keyLast.x, 0.0F, -horizontalDistance);
            float scale = worldPosition.length();
            float yRot = (float)MathUtils.getYRotOfVector(toTarget);
            float clampedYRot = MathUtils.rotlerp(entitypatch.getYRot(), yRot, entitypatch.getYRotLimit());
            entitypatch.setYRot(clampedYRot);

            for (int i = startFrame; i <= endFrame; i++) {
                Vec3f translation = keyframes[i].transform().translation();

                if (translation.z < 0.0F) {
                    translation.z *= scale;
                }
            }
            transformSheet.readFrom(transform);
        } else {
            transformSheet.readFrom(self.getCoord().copyAll());
        }
    };

    public static final MoveCoordFunctions.MoveCoordSetter TRACE_LOCROT_TARGET = (self, entitypatch, transformSheet) -> {
        LivingEntity attackTarget = entitypatch.getTarget();

        if (attackTarget != null) {
            TransformSheet transform = self.getCoord().copyAll();
            Keyframe[] keyframes = transform.getKeyframes();
            int startFrame = 0;
            int endFrame = keyframes.length - 1;
            Vec3f keyLast = keyframes[endFrame].transform().translation();
            Vec3 pos = entitypatch.getOriginal().position();
            Vec3 targetpos = attackTarget.position();
            Vec3 toTarget = targetpos.subtract(pos);
            float horizontalDistance = Math.max((float)toTarget.horizontalDistance() - (attackTarget.getBbWidth() + entitypatch.getOriginal().getBbWidth()) * 0.75F, 0.0F);
            Vec3f worldPosition = new Vec3f(keyLast.x, 0.0F, -horizontalDistance);
            float scale = worldPosition.length();
            float yRot = (float)MathUtils.getYRotOfVector(toTarget);
            float clampedYRot = MathUtils.rotlerp(entitypatch.getYRot(), yRot, entitypatch.getYRotLimit());
            entitypatch.setYRot(clampedYRot);

            for (int i = startFrame; i <= endFrame; i++) {
                Vec3f translation = keyframes[i].transform().translation();

                if (translation.z < 0.0F) {
                    translation.z *= scale;
                }
            }
            transformSheet.readFrom(transform);
        } else {
            transformSheet.readFrom(self.getCoord().copyAll());
        }
    };
}

