package TextRPG.Util;

import TextRPG.Util.Property.Chest;
import TextRPG.Util.Property.Door;
import TextRPG.Util.Property.Key;
import TextRPG.Util.Property.Player;
import TextRPG.Util.Property.Room;
import TextRPG.Util.Property.Weapon;

public class Library {
    public static final Weapon ironSword = new Weapon("Iron Sword", "A rusty iron sword.", 3, 10);

    public static final Key testKey = new Key("Test Key D", "Test key for Doors");
    public static final Key testKeyUnlock = new Key("Test Key C", "Test key for Chests");

    public static final Room startRoom = new Room("Start", "");
    public static final Room exitRoom = new Room("Exit", "Room will automaticly end the game");

    public static final Door firstDoor = new Door("exit Door", "Leaves the game.", startRoom, exitRoom, testKey);

    public static final Chest testChest = new Chest("Test Chest", "Test of inventory acquisition");
    public static final Chest keyChest = new Chest("Key Chest", "Test of key unlocking", testKeyUnlock);

    public static final Player player = new Player(startRoom);

    static {
        // Initialize Rooms
        // starting room
        startRoom.addObject(firstDoor);
        startRoom.addObject(testChest);
        startRoom.addObject(keyChest);
        // ending room
        // Initialize chest contents
        testChest.addItem(ironSword);
        testChest.addItem(testKeyUnlock);
        keyChest.addItem(testKey);
    }
}
