package dev.codezey.projectjujutsu.util;


import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;

import net.minecraft.world.level.ClipContext;

import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;


public class raycast {



    public static boolean rayTraceEyes(final LivingEntity entity, final double length) {
        final Vec3 startPos = new Vec3(entity.getX(), entity.getY() + entity.getEyeHeight(), entity.getZ());
        final Vec3 endPos = startPos.add(new Vec3(entity.getLookAngle().x * length, entity.getLookAngle().y * length, entity.getLookAngle().z * length));
        HitResult hitResult = (HitResult) entity.level().clip(new ClipContext(startPos, endPos, ClipContext.Block.COLLIDER, ClipContext.Fluid.SOURCE_ONLY, (Entity) entity));
        return hitResult.getType() != HitResult.Type.MISS;
    }


}
