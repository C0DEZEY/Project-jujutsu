package dev.codezey.projectjujutsu.server;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.Event;


import net.minecraft.world.entity.Entity;
import net.minecraft.server.level.ServerPlayer;

import javax.annotation.Nullable;
@Mod.EventBusSubscriber

public class teleport {

    public static void TeleportTo(double x, double y, double z, Entity entity) {
        if (entity == null)
            return;
        {
            Entity _ent = entity;
            _ent.teleportTo(x, y , z);
            if (_ent instanceof ServerPlayer _serverPlayer)
                _serverPlayer.connection.teleport(x, y, z, _ent.getYRot(), _ent.getXRot());
        }
    }
}
