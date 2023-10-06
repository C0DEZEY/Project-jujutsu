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
import dev.codezey.projectjujutsu.util.glow;
import java.util.function.Predicate;

public class BoogieWoogie {

    public static void Boogie(Player player) {


        // Get the Entity and define it as E then pass in our inputs for raycastings
        Entity e = raycast.selectEntityNearCursor(player,50,player.level(),entityPredicate,true);

        // Set the Entity to Glow

        glow.setglow(e);


    }

    @Mod.EventBusSubscriber(modid = Main.MODID, value = Dist.CLIENT)
    public static class ClientForgeEvents {
        // On Keybind
        @SubscribeEvent
        public static void onKeyInput(InputEvent.Key event) {
            // Do Actions on Keyboard Input
            if(KeyBinding.BW_KEY.consumeClick()) {
                Minecraft.getInstance().player.sendSystemMessage(Component.literal("Boogie Woogie "));

                Boogie(Minecraft.getInstance().player);

            }
        }
    }

}
