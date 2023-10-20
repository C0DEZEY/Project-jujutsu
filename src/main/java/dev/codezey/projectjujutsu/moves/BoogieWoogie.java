package dev.codezey.projectjujutsu.moves;

import dev.codezey.projectjujutsu.Main;
import dev.codezey.projectjujutsu.client.ManaBarData;
import dev.codezey.projectjujutsu.networking.SwapMessage;
import dev.codezey.projectjujutsu.networking.packets.KeyBindMessages;
import dev.codezey.projectjujutsu.util.raycast;
import net.minecraft.client.Minecraft;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import dev.codezey.projectjujutsu.util.util;


public class BoogieWoogie {
    private static Entity Target1 = null;
    private static Entity Target2 = null;
    public static Entity getEnemy(Player player) {
        return raycast.rayTraceEyes(player, 50);
    }

    private static boolean toggled = false;

    public static void run() {
        if (!toggled) {
            Minecraft mc = Minecraft.getInstance();
            Player player = mc.player;
            Target1 = getEnemy(player);
            if (Target1 != null) {
                util.ShowActionBar(mc.player, "§a Target 1 selected");
            }
            toggled = true;
        }
    }

    // This function will tell the server if there is 2 entitys. if so then it will grab both!
    public static void set() {
        Minecraft mc = Minecraft.getInstance();
        Player player = mc.player;
        Target2 = getEnemy(player);


        if (ManaBarData.getPlayerMana() >= 10) {
            // If players has move then 10 mana or eqaul to.
            if (Target1 != null) {
                if (Target2 != null) {
                    double num1 = Target1.getX() + Target1.getZ() + Target1.getY();
                    double num2 = Target2.getX() + Target2.getZ() + Target2.getY();
                    if (num1 == num2) {
                        SwapMessage.setTwoentitys(false);
                        Main.PACKET_HANDLER.sendToServer(new SwapMessage(0, 0));
                        // Remove Mana After use and return any errors.
                        ManaBarData.remove(10);
                        util.ShowActionBar(mc.player, "§a Swapped");
                    } else {
                        SwapMessage.setTwoentitys(true);
                        SwapMessage.FirstEntity(Target1);
                        Main.PACKET_HANDLER.sendToServer(new SwapMessage(1, 0));
                        ManaBarData.remove(10);
                        util.ShowActionBar(mc.player, "§a Swapped 2");

                    }
                } else {
                    util.ShowActionBar(mc.player, "§c No Second Target. ");
                }
            } else {
                util.ShowActionBar(mc.player, "§c No First Target.");
            }
        }else {
            util.ShowActionBar(mc.player, "§9 Not Enough Mana.");
        }
        toggled = false;
    }

    }



// ManaBarData.remove(10);
//  Main.PACKET_HANDLER.sendToServer(new SwapMessage(0, 0));
//  util.ShowActionBar(mc.player, "No Targets Found.");

// SwapMessage.SetSecondEntity(true);