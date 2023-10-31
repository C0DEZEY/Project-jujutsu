package dev.codezey.projectjujutsu.client;

public class HotBarData {
    private static int HotBarSlot = 1;
    private static boolean ActiveHotBar = true;
    private static int HotBarSize = 4; 
    public static int[] Moves = new int[]{1, 0, 0, 0}; // Create a Array of all the moves using ID's
 


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
