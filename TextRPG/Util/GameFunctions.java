package TextRPG.Util;

import TextRPG.Util.Property.Chest;
import TextRPG.Util.Property.Door;
import TextRPG.Util.Property.Item;
import TextRPG.Util.Property.Player;
import TextRPG.Util.Property.Room;
import TextRPG.Util.Property.SpecialObject;
import java.util.Scanner;

public class GameFunctions {
    static Scanner input = new Scanner(System.in);

    public static boolean easyYesNo(String dialog) {
        System.out.println(dialog + " (Y/N)");
        return easyYesNo();
    }

    public static boolean easyYesNo() {
        while (true) {
            String userIp = input.nextLine().trim().toUpperCase();
            if ("Y".equals(userIp)) {
                return true;
            } else if ("N".equals(userIp)) {
                return false;
            }
            System.out.println("Please enter Y or N: ");
        }
    }

    public static void inspect(Door door, Player player) {
        System.out.println(door.getDescription());

        if (door.isOpen()) {
            if (easyYesNo("Would you like to close the " + door.getName() + " door?")) {
                door.close();
            } else if (easyYesNo("Would you like to go through the " + door.getName() + " door?")) {
                door.goThrough(player);
            }
        } else {
            if (easyYesNo("Would you like to open the " + door.getName() + " door?")) {
                door.open();
                if (door.isOpen() && easyYesNo("Would you like to go through it?")) {
                    door.goThrough(player);
                }
            } else if (door.getKey() != null && easyYesNo("Would you like to try to unlock it?")) {
                door.unlock(player);
            }
        }
    }

    public static void inspect(Chest chest, Player player) {
        System.out.println(chest.getDescription());

        if (chest.getKey() != null || !chest.isLocked()) {
            chest.showContents(player);
        } else {
            if (easyYesNo("Would you like to try to unlock it?")) {
                chest.showContents(player);
            }
        }
    }

    public static void inspect(SpecialObject object, Player player) {
        if (object instanceof Chest) {
            inspect((Chest) object, player);
        } else if (object instanceof Door) {
            inspect((Door) object, player);
        } else {
            System.out.println(object.getDescription());
        }
    }

    public void inspect(Room room) {
        System.out.println(room.getDescription());
    }

    public static boolean render(Player player) {
        System.out.println("\n=== Current Location: " + player.getCurrentRoom().getName() + " ===");
        System.out.println(player.getCurrentRoom().getDescription());
        return Update(player);
    }

    public static boolean Update(Player player) {
        Room room = player.getCurrentRoom();
        int roomSize = room.sizeObject();

        for (int i = 0; i < roomSize; i++) {
            System.out.println((i + 1) + " : " + room.getObject(i).getName());
        }

        while (true) {
            System.out.println("Q : quit ");
            System.out.println("enter a number of object you wish to inspect: ");

            String ip = input.nextLine();
            try {
                int i = Integer.parseInt(ip) - 1;
                inspect(room.getObject(i), player);
            } catch (Exception e) {
                if (ip.toLowerCase().equals("q")) {
                    return true;
                } else {
                    break;
                }
            }
        }

        return false;
    }
}