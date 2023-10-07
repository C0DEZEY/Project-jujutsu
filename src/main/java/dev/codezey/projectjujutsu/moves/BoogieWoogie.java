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
import net.minecraft.world.entity.LivingEntity;
public class BoogieWoogie {


    
    @Mod.EventBusSubscriber(modid = Main.MODID, value = Dist.CLIENT)
    public static class ClientForgeEvents {

        public static LivingEntity getEnemy(Player player) {
            Entity e = raycast.selectEntityNearCursor(player, 5, player.level(), a -> raycast.getPredicate(raycast.MAHOU_SELECTION.SELECTIVE_DISPLACEMENT, a, player), true);
            if (e instanceof LivingEntity) {
                return (LivingEntity)e;
            }
            return null;}
        
        // On Keybind
        @SubscribeEvent
        public static void onKeyInput(InputEvent.Key event) {
            // Do Actions on Keyboard Input
            if(KeyBinding.BW_KEY.consumeClick()) {
                Minecraft.getInstance().player.sendSystemMessage(Component.literal("Boogie Woogie "));
                getEnemy(Minecraft.getInstance().player);

            }
        }
    }

}
