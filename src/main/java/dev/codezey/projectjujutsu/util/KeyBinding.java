package dev.codezey.projectjujutsu.util;

import com.mojang.blaze3d.platform.InputConstants;
import dev.codezey.projectjujutsu.Main;
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
public class KeyBinding {

    // To Create a Keybind Please Follow this template belove and go Into Resources -> Assests - > Juju > Lang and add the proper Keybind (Copy paste)
    public static final String KEY_CATRGORY_JUJUTSU = "key.category.Jujutsu.jujutsu";
    public static final String KEY_BOOGIE_WOOGIE = "key.Jujutsu.boogie_woogie";



    public static final KeyMapping BW_KEY = new KeyMapping(KEY_BOOGIE_WOOGIE, KeyConflictContext.IN_GAME,
            InputConstants.Type.KEYSYM, GLFW.GLFW_KEY_F, KEY_CATRGORY_JUJUTSU) { // This Create the KeyBind In Game PLease copy this in remove the code after setdown

    private static long BW_LASTPRESS = 0;
    private boolean isDownOld = false;

    @Override
    public void setDown(boolean isDown) {
        super.setDown(isDown);
        if (isDownOld != isDown && isDown) {
          KeyBindMessages.pressAction(Minecraft.getInstance().player, 0, 0);
            BW_LASTPRESS = System.currentTimeMillis();
        } else if (isDownOld != isDown && !isDown) {
            int dt = (int) (System.currentTimeMillis() - BW_LASTPRESS);
            KeyBindMessages.pressAction(Minecraft.getInstance().player, 1, 0);
        }
        isDownOld = isDown;
    }
};

    @SubscribeEvent
    public static void registerKeyMappings(RegisterKeyMappingsEvent event) {
        event.register(BW_KEY);
        // Make sure to register your Keybind
    }

    @Mod.EventBusSubscriber({Dist.CLIENT})
    public static class KeyEventListener {
        @SubscribeEvent
        public static void onClientTick(TickEvent.ClientTickEvent event) {
            if (Minecraft.getInstance().screen == null) {
                BW_KEY.consumeClick();
                // Also makesure to Comsume click (This Checks Every tick) if your keybind has been pressed
            }
        }
    }


}
