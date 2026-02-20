package TextRPG.Util;

import TextRPG.Util.Property.Weapond;
import TextRPG.Util.Property.Door;
import TextRPG.Util.Property.Chest;
import TextRPG.Util.Property.Room;

public class Library {
    @SuppressWarnings("unused")
    Weapond ironSword = new Weapond("Iron Sword", "A rusty iron sword your father gave you.", 3, 10);
    
    Room startRoom = new Room("Start");
    Room exitRoom = new Room("Exit");

    @SuppressWarnings("unused")
    Door firstDoor = new Door("exit", "Leaves the game.", startRoom, exitRoom);
    
    @SuppressWarnings("unused")
    Chest Inventory = new Chest("Inventory");
}
