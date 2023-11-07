package dev.codezey.projectjujutsu.client;

import dev.codezey.projectjujutsu.Main;
import dev.codezey.projectjujutsu.util.util;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.event.entity.living.LivingEntityUseItemEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = Main.MODID, value = Dist.CLIENT)
public class HotBarData {
    private static int HotBarSlot = 0;
    private static boolean ActiveHotBar = false;
    private static int HotBarSize = 4; 
    // TODO chancge the system to use a json file we an read and write to this will take some time but this is good for a prototype
    public static int[] Moves = new int[]{1, 0, 0, 0}; // Create a Array of all the moves using ID's
 

    // Scrolling code

    @SubscribeEvent
    public static void onHotbarScroll(InputEvent.MouseScrollingEvent event) {
        double scrollvaule = event.getScrollDelta();
        if (ActiveHotBar) {
            if (scrollvaule < 0) {
                HotBarSlot = HotBarSlot + 1;
                if (HotBarSlot > HotBarSize - 1) {
                    HotBarSlot = 0;
                }

            } else {
                HotBarSlot = HotBarSlot - 1;
                if (HotBarSlot == -1) {
                    HotBarSlot = HotBarSize - 1;
                }
            }

            event.setCanceled(true);
        }
    }
    public static void setActive(boolean b) {
        ActiveHotBar = b;
    }

    public static int ReturnSlot() {
        return HotBarSlot; 
    }

    public static int ReturnSize() {
        return HotBarSize;
    }

    public static boolean ReturnIsActive() {
        return ActiveHotBar;
    }
    
    public static int ReturnMove(int i) {
        int t = Moves[i];
        return t; 
    }

    public static void SetId(int id, int slot) {
        Moves[slot] = id;
    }

}
