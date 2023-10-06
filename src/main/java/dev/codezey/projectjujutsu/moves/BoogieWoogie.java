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

import java.util.function.Predicate;

public class BoogieWoogie {

    public void Target(Player player) {
        Predicate<Entity> entityPredicate = entity -> {
            return true;
        };


        Entity e = raycast.selectEntityNearCursor(player,50,player.level(),entityPredicate,true);
    }

    @Mod.EventBusSubscriber(modid = Main.MODID, value = Dist.CLIENT)
    public static class ClientForgeEvents {
        // On Keybind (B)
        @SubscribeEvent
        public static void onKeyInput(InputEvent.Key event) {
            if(KeyBinding.BW_KEY.consumeClick()) {
                Minecraft.getInstance().player.sendSystemMessage(Component.literal("Doing shit? "));



            }
        }
    }

}
