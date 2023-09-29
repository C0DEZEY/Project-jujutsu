package dev.codezey.projectjujutsu.util;

import com.mojang.blaze3d.platform.GlStateManager;
import net.minecraft.client.Minecraft;
import net.minecraft.world.entity.Entity;


public class glow {

    public void setglow(Entity entity) {
        entity.setGlowingTag(true);
    }

}
