package dev.codezey.projectjujutsu.util;


import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.common.util.ITeleporter;

import java.util.function.Function;

public class util {
     /*

    public static void teleport(Entity entity, ResourceLocation destination, final Vec3 pos) {

                    if (entity instanceof ServerPlayer) {
                        ((ServerPlayer)entity).teleportTo(sw, pos.x(), pos.y(), pos.z(), entity.getYRot(), entity.getXRot());
                    } else {

                        {
                            public Entity placeEntity(Entity entity, ServerLevel currentWorld, ServerLevel sw, float yaw, Function<Boolean, Entity> repositionEntity)
                            {
                                entity = repositionEntity.apply(Boolean.valueOf(false));
                                entity.teleportTo(pos.x(), pos.y(), pos.z());
                                return entity;
                            }
                        });
                    }
                }
            }

 */
}

