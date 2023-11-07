package dev.codezey.projectjujutsu.keybinds;

import com.mojang.blaze3d.platform.InputConstants;
import dev.codezey.projectjujutsu.networking.packets.KeyBindMessages;
import net.minecraft.client.KeyMapping;
import net.minecraft.client.Minecraft;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RegisterKeyMappingsEvent;
import net.minecraftforge.client.settings.KeyConflictContext;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.lwjgl.glfw.GLFW;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, value = {Dist.CLIENT})
public class HotBarUse {
    public static final String KEY_CATRGORY_JUJUTSU = "key.category.Jujutsu.jujutsu";
    public static final String KEY_SCROLL_HOTBAR = "key.Jujutsu.hotbar_use";

    public static final KeyMapping HB_KEY = new KeyMapping(KEY_SCROLL_HOTBAR, KeyConflictContext.IN_GAME,
            InputConstants.Type.KEYSYM, GLFW.GLFW_KEY_F, KEY_CATRGORY_JUJUTSU) { // This Create the KeyBind In Game PLease copy this in remove the code after setdown




        private static long BW_LASTPRESS = 0;
        private boolean isDownOld = false;


        // Boogie Woogie Override (TO BE REMOVED LTR)
        @Override
        public void setDown(boolean isDown) {
            super.setDown(isDown);
            if (isDownOld != isDown && isDown) {
                KeyBindMessages.pressAction(Minecraft.getInstance().player, 2, 0);
                BW_LASTPRESS = System.currentTimeMillis();
            } else if (isDownOld != isDown && !isDown) {
                int dt = (int) (System.currentTimeMillis() - BW_LASTPRESS);
                KeyBindMessages.pressAction(Minecraft.getInstance().player, 3, 0);
            }
            isDownOld = isDown;
        }
    };



    @SubscribeEvent
    public static void registerKeyMappings(RegisterKeyMappingsEvent event) {
        event.register(HB_KEY);
        ///event.register(HB_KEY);
        // Make sure to register your Keybind
    }

    @Mod.EventBusSubscriber({Dist.CLIENT})
    public static class KeyEventListener {
        @SubscribeEvent
        public static void onClientTick(TickEvent.ClientTickEvent event) {
            if (Minecraft.getInstance().screen == null) {
                HB_KEY.consumeClick();
                // Also makesure to Comsume click (This Checks Every tick) if your keybind has been pressed
            }
        }
    }

}
