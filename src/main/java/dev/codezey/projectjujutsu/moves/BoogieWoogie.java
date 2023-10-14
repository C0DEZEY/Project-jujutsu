package dev.codezey.projectjujutsu.moves;

import dev.codezey.projectjujutsu.Main;
import dev.codezey.projectjujutsu.client.ManaBarData;
import dev.codezey.projectjujutsu.networking.SwapMessage;
import dev.codezey.projectjujutsu.util.raycast;
import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.Entity;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import dev.codezey.projectjujutsu.util.KeyBinding;
import net.minecraft.world.entity.player.Player;
import dev.codezey.projectjujutsu.util.util;

public class BoogieWoogie {


    @Mod.EventBusSubscriber(modid = Main.MODID, value = Dist.CLIENT)
    public static class ClientForgeEvents {
        private static boolean isDownOld = false;


        public static Entity getEnemy(Player player) {
            return raycast.rayTraceEyes(player,50);
        }
        @SubscribeEvent
        public static void onKeyInput(InputEvent.Key event) {
            // Do Actions on Keyboard Input
            if(KeyBinding.BW_KEY.isDown()) {
                boolean isDown = KeyBinding.BW_KEY.isDown();
                Minecraft mc = Minecraft.getInstance();
                if (mc.player != null) {
                    // Checks if player is loaded (IE = null means not)
                    if (ManaBarData.getPlayerMana() >= 10) {
                        // If players has move then 10 mana or eqaul to.
                            Player player = mc.player;
                            if (getEnemy(player)!= null) {
                                // If the Raycast Returned a Person then DO action.

                                // Send the Packet to your Server Code Found in Networking (Packet Handler)
                                // Which then goes to Server to teleport.

                                Main.PACKET_HANDLER.sendToServer(new SwapMessage(0, 0));
                                // Remove Mana After use and return any errors.
                                ManaBarData.remove(10);
                            } else {
                                util.ShowActionBar(mc.player,"No Targets Found.");
                            }
                            }else {
                        util.ShowActionBar(mc.player,"Not Enough Mana.");
                    }
                    }
                isDownOld = isDown;
                }







            }
        }
    }


