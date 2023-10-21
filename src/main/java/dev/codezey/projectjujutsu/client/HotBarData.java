package dev.codezey.projectjujutsu.client;

public class HotBarData {
    private static int HotBarSlot = 0;
    private static boolean ActiveHotBar = false; 
    private static int HotBarSize = 4; 
    private static int[] Moves = new int[]{0, 0, 0, 0}; // Create a Array of all the moves using ID's
 


    public static int ReturnSlot() {
        return HotBarSlot;
    }

    public static int ReturnSize() {
        return HotBarSize;
    }

    public static boolean ReturnIsActive() {
        return ActiveHotBar;
    }
    
    public static void SetId(int id, int slot) {
        Moves[slot] = id;
    }

}
