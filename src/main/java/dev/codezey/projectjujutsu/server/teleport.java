package dev.codezey.projectjujutsu.server;

import dev.codezey.projectjujutsu.moves.BoogieWoogie;
import dev.codezey.projectjujutsu.util.raycast;
import dev.codezey.projectjujutsu.util.util;
import net.minecraft.server.level.ServerEntity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.LevelAccessor;
import net.minecraftforge.fml.common.Mod;


import net.minecraft.world.entity.Entity;
import net.minecraft.server.level.ServerPlayer;

import java.util.ArrayList;
import java.util.List;

@Mod.EventBusSubscriber

public class teleport {

    public static void TeleportTo(double x, double y, double z, Entity entity) {
        if (entity == null)
            return;
        {
            entity.teleportTo(x, y, z);
            if (entity instanceof ServerPlayer s)
                s.connection.teleport(x, y, z, entity.getYRot(), entity.getXRot());
        }
    }
    public static void pause(int ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
            System.err.format("InterruptedException : %s%n", e);
        }
    }
    public static void SwapEntity(Player player) {
        Entity e1 = BoogieWoogie.Target1;
        Entity e2 = raycast.rayTraceEyes(player, 50);

        if (e1 != null && e2 != null) {




                double x = e1.getX();
                double y = e1.getY();
                double z = e1.getZ();
                double id = e1.getId();
                double x2 = e2.getX();
                double y2 = e2.getY();
                double z2 = e2.getZ();
               e2.teleportTo(x, y, z);

                List<Entity> entities = player.level().getEntities(player, player.getBoundingBox().inflate(50));
            for (Entity e : entities) {
                if (e.getId() == id) {
                    e.setPos(x2,y2,z2);
                }
            }




        }
    }
}
