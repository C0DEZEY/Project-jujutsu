package dev.codezey.projectjujutsu.util;


import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;

public class util {

    // This code is for General Utily. So things ilke Putting stuff in chat basicly math etc etc
    // Should make sence ngl
    public static void Chat(String args) {
        Minecraft.getInstance().player.sendSystemMessage(Component.literal(args));
    }
    public static void ShowActionBar(Player player,String args) {
        player.displayClientMessage(Component.literal(args), true);
    }

}