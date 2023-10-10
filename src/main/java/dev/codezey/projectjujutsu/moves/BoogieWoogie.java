package dev.codezey.projectjujutsu.moves;

import dev.codezey.projectjujutsu.Main;
import dev.codezey.projectjujutsu.util.glow;
import dev.codezey.projectjujutsu.util.raycast;
import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import dev.codezey.projectjujutsu.util.KeyBinding;
import net.minecraft.world.entity.player.Player;

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
        double playerX = player.getX();
        double playerY = player.getY();
        double playerZ = player.getZ();
        double playerYRot = player.getYRot();
        double playerXRot = player.getXRot();


        double targetX = target.getX();
        double targetY = target.getY();
        double targetZ = target.getZ();
        double targetYRot = target.getYRot();
        double targetXRot = target.getXRot();


}

}
