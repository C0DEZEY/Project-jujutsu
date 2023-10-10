package dev.codezey.projectjujutsu.moves;

import dev.codezey.projectjujutsu.Main;
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
import dev.codezey.projectjujutsu.networking.ModMessages;



public class BoogieWoogie {


    @Mod.EventBusSubscriber(modid = Main.MODID, value = Dist.CLIENT)
    public static class ClientForgeEvents {

        public static Entity getEnemy(Player player) {
            return raycast.rayTraceEyes(player,50);
        }
        @SubscribeEvent
        public static void onKeyInput(InputEvent.Key event) {
            // Do Actions on Keyboard Input
            if(KeyBinding.BW_KEY.consumeClick()) {
                Minecraft mc = Minecraft.getInstance();
                if (mc.player != null) {
                    Player player = mc.player;
                    Entity target = getEnemy(player);
                        swapPositions(player, target);
                    Minecraft.getInstance().player.sendSystemMessage(Component.literal("Boogie Woogie "));
                }







            }
        }
    }


    public static void swapPositions(Player player, Entity target) {
        // Save all the player datda
        double playerX = player.getX();
        double playerY = player.getY();
        double playerZ = player.getZ();

        double targetX = target.getX();
        double targetY = target.getY();
        double targetZ = target.getZ();

        // Send a packet to our server that we need to call the teleportion method

        Main.PACKET_HANDLER.sendToServer(new ModMessages(0, 0));

        // Send our pressAction Packet which is for the Teleportaion Function. To Teleport the player To the last saved Target Pos
        SwapMessage.pressAction(Minecraft.getInstance().player, 0, targetX,targetY,targetZ);
        // Send it again to teleport the taret to the last player cords
        SwapMessage.pressAction(target, 0, playerX,playerY,playerZ);


}


}
