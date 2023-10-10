package dev.codezey.projectjujutsu.util;


import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;

import net.minecraft.world.level.ClipContext;

import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;

import java.util.List;


public class raycast {



    public static Entity rayTraceEyes(final LivingEntity entity, final double length) {
        Vec3 start = entity.getEyePosition(1.0F); // Get the entity's eye position
        Vec3 lookVec = entity.getLookAngle(); // Get the entity's look vector
        Vec3 end = start.add(lookVec.x * length, lookVec.y * length, lookVec.z * length);

        AABB rayAABB = new AABB(start, end);

        Level world = entity.getCommandSenderWorld();
        List<Entity> entities = world.getEntities(entity, rayAABB, e -> e != entity);

        Entity closestEntity = null;
        double closestDistance = Double.MAX_VALUE;

        for (Entity e : entities) {
            double distance = start.distanceToSqr(e.position().add(0, e.getEyeHeight(), 0));
            if (distance < closestDistance) {
                closestEntity = e;
                closestDistance = distance;
            }
        }

        return closestEntity;
    }


}
