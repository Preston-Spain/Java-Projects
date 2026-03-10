package TextRPG.Util;

import TextRPG.Util.Property.Chest;
import TextRPG.Util.Property.Door;
import TextRPG.Util.Property.Player;
import TextRPG.Util.Property.Room;
import TextRPG.Util.Property.SpecialObject;
import java.util.Scanner;

public class GameFunctions {
    static Scanner input = new Scanner(System.in);

    public static boolean easyYesNo(String dialog) {
        System.out.println(dialog + " (Y/N)");
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

    public static int easyNumber(String dialog, int max) {
        System.out.println(dialog + " num: ");
        while (true) {
            String userIp = input.nextLine().trim();
            try {
                int i = Integer.parseInt(userIp);
                if (i < max) {
                    return i;
                }
            } finally {
            }
            System.out.println("Please enter a number between (0 & " + max + "): ");
        }
    }

    public static int easyNumber(String dialog, String falseReply) {
        System.out.println(dialog + " num: ");
        while (true) {
            String userIp = input.nextLine().trim();
            try {
                int i = Integer.parseInt(userIp);
                return i;
            } finally {
                System.out.println(falseReply);
            }
        }
    }

    private static void debugPrint(String x) {
        System.out.print(Constants.debug ? x + "\n": "");
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
        debugPrint("Inspect - Chest: 1");
        if (chest.getKey() != null || !chest.isLocked()) {
            debugPrint("Inspect - Chest: 2: True");
            chest.showContents(player);
        } else {
            debugPrint("Inspect - Chest: 2: False");
            if (easyYesNo("Would you like to try to unlock it?")) {
                debugPrint("Inspect - Chest: 3: true");
                chest.showContents(player);
            }
        }
    }

    public static void inspect(SpecialObject object, Player player) {
        if (object instanceof Chest || object.getClass() == Chest.class) {
            inspect((Chest) object, player);
        } else if (object instanceof Door || object.getClass() == Door.class) {
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

        while (true) {

            System.out.println("");
            for (int i = 0; i < roomSize; i++) {
                System.out.println((i + 1) + " : " + room.getObject(i).getName());
            }

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