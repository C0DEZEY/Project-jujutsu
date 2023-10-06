package dev.codezey.projectjujutsu.util;


import net.minecraft.core.BlockPos;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.function.Predicate;

public class raycast {

    public static double distanceToLine(final Vec3 v, final Vec3 w, final Vec3 p) {
        final Vec3 VW = v.subtract(w);
        if (VW.lengthSqr() == 0.0) {
            return p.distanceTo(v);
        }
        final Vec3 z = v.subtract(VW.scale(v.subtract(p).dot(VW) / VW.dot(VW)));
        final double dv = z.distanceToSqr(v);
        final double dw = z.distanceToSqr(w);
        final double dvw = v.distanceToSqr(w);
        if (dv > dvw) {
            return p.distanceTo(w);
        }
        if (dw > dvw) {
            return p.distanceTo(v);
        }
        return p.distanceTo(z);
    }


    public static HitResult rayTraceEyes(final LivingEntity entity, final double length) {
        final Vec3 startPos = new Vec3(entity.getX(), entity.getY() + entity.getEyeHeight(), entity.getZ());
        final Vec3 endPos = startPos.add(new Vec3(entity.getLookAngle().x * length, entity.getLookAngle().y * length, entity.getLookAngle().z * length));
        return (HitResult)entity.level().clip(new ClipContext(startPos, endPos, ClipContext.Block.COLLIDER, ClipContext.Fluid.SOURCE_ONLY, (Entity)entity));
    }
    public static class Vec3H {
        public Vec3 v;

        public Vec3H(final Vec3 vec) {
            this.v = vec;
        }
    }


    public static boolean CheckLineBox(final Vec3 B1, final Vec3 B2, final Vec3 L1, final Vec3 L2) {
        return CheckLineBox(B1, B2, L1, L2);
    }


    public static boolean isLookingAtMe(final Entity me, final LivingEntity player, final int limit) {
        final Vec3 mv = me.position();
        float angleToMe = (float)Math.atan2(mv.z - player.getZ(), mv.x - player.getX()) * 57.2958f;
        float yaw = player.getYRot();
        yaw %= 360.0f;
        angleToMe %= 360.0f;
        if (yaw < 0.0f) {
            yaw += 360.0f;
        }
        if (angleToMe < 0.0f) {
            angleToMe += 360.0f;
        }
        float boundUpper = (yaw + 90.0f + 90.0f - limit) % 360.0f;
        float boundLower = (yaw + limit) % 360.0f;
        if (boundLower < 0.0f) {
            boundLower += 360.0f;
        }
        if (boundUpper < 0.0f) {
            boundUpper += 360.0f;
        }
        return (angleToMe < boundUpper && angleToMe > boundLower) || (boundUpper < boundLower && (angleToMe < boundUpper || angleToMe > boundLower));
    }

    public static Entity selectEntityNearCursor(final Player player, final double distance, final Level world, final Predicate<Entity> pred, final boolean nearAllowed) {
        Entity ret = null;
        if (player != null) {
            final List<Entity> lst = (List<Entity>)world.getEntities((Entity)player, player.getBoundingBox().inflate(distance), (Predicate)pred);
            final Vec3 eyes = player.getEyePosition(1.0f);
            final Vec3 look = player.getLookAngle();
            final Vec3 sightEnd = eyes.add(look.scale(distance));
            double minDToLine = Double.MAX_VALUE;
            double minD = Double.MAX_VALUE;
            boolean intersects = false;
            for (final Entity e : lst) {
                final AABB aabb = e.getBoundingBox();
                final double d = e.position().distanceTo(eyes);
                if (CheckLineBox(new Vec3(aabb.minX, aabb.minY, aabb.minZ), new Vec3(aabb.maxX, aabb.maxY, aabb.maxZ), eyes, sightEnd)) {
                    intersects = true;
                    if (d < minD && !blockBetween(eyes, e.getBoundingBox().getCenter(), e.level())) {
                        ret = e;
                        minD = d;
                    }
                }
                if (nearAllowed && !intersects) {
                    final Vec3 center = aabb.getCenter();
                    final double dToLine = distanceToLine(eyes, sightEnd, center);
                    if (dToLine >= minDToLine || blockBetween(eyes, e.getBoundingBox().getCenter(), e.level()) || !isLookingAtMe(e, (LivingEntity)player, 45)) {
                        continue;
                    }
                    minDToLine = dToLine;
                    ret = e;
                }
            }
        }
        return ret;
    }
    public static BlockPos toBlockPos(final Vec3 v) {
        return new BlockPos(Mth.floor(v.x), Mth.floor(v.y), Mth.floor(v.z));
    }

    public static boolean blockBetween(final Vec3 e1, final Vec3 e2, final Level world) {
        final Vec3 subtracted = e2.subtract(e1);
        final Vec3 unit = subtracted.normalize();
        if (subtracted.length() > 1.0) {
            for (int i = 0; i < subtracted.length(); ++i) {
                final BlockPos pos = toBlockPos(e1.add(unit.scale((double)i)));
                final BlockState bs = world.getBlockState(pos);
                if (!bs.isAir() && !bs.getBlock().propagatesSkylightDown(bs, (BlockGetter)world, pos)) {
                    return true;
                }
            }
            return false;
        }
        return false;
    }
public static boolean getPredicate(MAHOU_SELECTION s, Entity e, Player player) {
        return getPredicate(new HashSet<>(Collections.singleton(s)), e, player);
    }
  public static boolean getPredicate(HashSet<MAHOU_SELECTION> set, Entity e, Player player) {
if (set.contains(MAHOU_SELECTION.SELECTIVE_DISPLACEMENT))
      return (e != player);
 if (set.contains(MAHOU_SELECTION.SCROLL_PROJECTION))
    return (e != player && (e instanceof LivingEntity || e instanceof net.minecraft.world.entity.projectile.ThrownTrident || e instanceof net.minecraft.world.entity.decoration.ItemFrame));
  if (set.contains(MAHOU_SELECTION.SCROLL_GEAS))
       return (e != player && (e instanceof LivingEntity || e instanceof net.minecraft.world.entity.item.ItemEntity));
   if (set.size() > 0) {
     return (e != player && e instanceof LivingEntity);
   }
   return false;
    }

    public enum MAHOU_SELECTION
    {
        SELECTIVE_DISPLACEMENT,
        SCROLL_PROJECTION,
        SCROLL_GEAS,
    }
}
