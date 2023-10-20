package dev.codezey.projectjujutsu.server;

import net.minecraftforge.fml.common.Mod;


import net.minecraft.world.entity.Entity;
import net.minecraft.server.level.ServerPlayer;

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
}
