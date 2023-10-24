package dev.codezey.projectjujutsu.moves;

public class ID {
public static String IdToMove(int id) {
    if (id == 1) {
        return ("Boogie Woogie");
    } else if (id == 2) {
        return ("Limitless"); 
    } else {
        return ("Nil"); 
    }
}
// Keep Create else if statments foer each move listed.  ^ 

    // If the Code is to be ran when the key is just press then use Executed ID 
public static void executeId(int id) {

}

// If the code needs to have to checks (Up then down use these to functions) simuler to how Boogie Woogie Workks.
public static void PressDown(int id) {
    if (id == 1) {
    KeyBindMessages.pressAction(Minecraft.getInstance().player, 0, 0);
    }

}
public static void KeyUp(int id) {
    // Boogie woogie 
    if (id == 1) {
    KeyBindMessages.pressAction(Minecraft.getInstance().player, 1, 0);
    }

}


} 