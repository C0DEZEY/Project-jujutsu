package dev.codezey.projectjujutsu.util;

import com.mojang.blaze3d.platform.InputConstants;
import net.minecraft.client.KeyMapping;
import net.minecraftforge.client.settings.KeyConflictContext;
import org.lwjgl.glfw.GLFW;

public class KeyBinding {
    public static final String KEY_CATRGORY_JUJUTSU = "key.category.Jujutsu.jujutsu";
    public static final String KEY_BOOGIE_WOOGIE = "key.Jujutsu.boogie_woogie";

    public static final KeyMapping BW_KEY = new KeyMapping(KEY_BOOGIE_WOOGIE, KeyConflictContext.IN_GAME,
            InputConstants.Type.KEYSYM, GLFW.GLFW_KEY_F, KEY_CATRGORY_JUJUTSU);
}
