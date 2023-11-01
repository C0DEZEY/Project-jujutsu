package dev.codezey.projectjujutsu.moves;

import dev.codezey.projectjujutsu.networking.packets.KeyBindMessages;
import net.minecraft.client.Minecraft;

public class ID {
    // For Every Move We Create we can use this to return the data of the move 
// Please enter the IMG file name as the move for it to return this should be a pretty long list 
// Please note this will be changed later on to a JSON File but this works for RN. 
public static String IdToMove(int id) {
    String imgName = "";
    if ((int) id == 0) {
        imgName = "null";
     } else if ((int) id == 1) {
        imgName = "boogiewoogie";

    } else if ((int) id == 2) {
       imgName = "limitless";
    } else {
        imgName = "null";
    }

    return (String) imgName;
    
    
}
// Keep Create else if statments for each move listed.  ^ 

    // If the Code is to be ran when the key is just press then use Executed ID 
    // Use this for each new move. This may get a rework later on in time. 
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