package dev.codezey.projectjujutsu.util;


import net.minecraft.world.entity.Entity;


public class glow {
 // WIP :skull:
    public static void setglow(Entity entity) {
        entity.setGlowingTag(true);
    }
    public static void unglow(Entity entity) {
        entity.setGlowingTag(false);
    }
}
