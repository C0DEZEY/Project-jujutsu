package dev.codezey.projectjujutsu.util;


import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;

import net.minecraft.world.level.ClipContext;

import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;

import java.util.List;
import java.util.Optional;


public class raycast {


    // To raycast just call the function rayTraceEyes and input the player (Minecraft.Getinstance().player or whatever) then input the distance and it will reutrn the target!
    public static Entity rayTraceEyes(final LivingEntity entity, final double length) {
        // Get your Eye Pose 

        Vec3 start = entity.getEyePosition(1.0F);

        // Get the entitys angle of yaw
        Vec3 lookVec = entity.getLookAngle();

        Vec3 end = start.add(lookVec.x * length, lookVec.y * length, lookVec.z * length);

        Level world = entity.getCommandSenderWorld();

        BlockHitResult blockHitResult = world.clip(new ClipContext(start, end, ClipContext.Block.COLLIDER, ClipContext.Fluid.NONE, entity));
        Vec3 rayEnd = blockHitResult.getType() != HitResult.Type.MISS ? blockHitResult.getLocation() : end;
        // Vec 3 takes there cords similer to roblox's vetcor 3 and vectors 2 stored like (x,y,z)

        // Create a list of alo entitys in the world in a 50 block range ( this is the possisble targets it could hit)
        List<Entity> entities = world.getEntities(entity, entity.getBoundingBox().expandTowards(lookVec.scale(length)).inflate(1.0), e -> e != entity);
        
        Entity closestEntity = null;
        double closestDistance = Double.MAX_VALUE;

        for (Entity e : entities) {

            // Do some basic math
            Vec3 entityVec = e.position().add(0, e.getEyeHeight(), 0);

            // Calculate the intersection of the ray with the entity's bounding box
            AABB entityBox = e.getBoundingBox();
            Optional<Vec3> intersection = entityBox.clip(start, rayEnd);

            if (intersection.isPresent()) {
                double intersectionDistance = start.distanceToSqr(intersection.get());
                if (intersectionDistance < closestDistance) {
                    closestEntity = e;
                    closestDistance = intersectionDistance;
                }
            }
        }

        return closestEntity;
    }


}
