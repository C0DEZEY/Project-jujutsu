package dev.codezey.projectjujutsu.moves;

import dev.codezey.projectjujutsu.Main;
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

public class BoogieWoogie {


    
    @Mod.EventBusSubscriber(modid = Main.MODID, value = Dist.CLIENT)
    public static class ClientForgeEvents {

        public static boolean getEnemy(Player player) {
            return raycast.rayTraceEyes(player,50);
        }
        
        // On Keybind
        @SubscribeEvent
        public static void onKeyInput(InputEvent.Key event) {
            // Do Actions on Keyboard Input
            if(KeyBinding.BW_KEY.consumeClick()) {
                //Minecraft.getInstance().player.sendSystemMessage(Component.literal("Boogie Woogie "));
                boolean target = getEnemy(Minecraft.getInstance().player);
               Minecraft.getInstance().player.sendSystemMessage(Component.literal(String.valueOf(target)));

            }
        }
    }

}
