package TextRPG.Util;

import TextRPG.Util.Property.Chest;
import TextRPG.Util.Property.Door;
import TextRPG.Util.Property.Weapond;
// import TextRPG.Util.Property.interactableObject;
import java.util.Scanner;

public class GameFunctions {

    static Scanner input = new Scanner(System.in);

    public static boolean easyYesNo(String dialog) {
        System.out.println(dialog + " (Y/N)");

        String userIp = input.nextLine();
        boolean X = false;
        while (true) {
            if (X) {
                System.out.println("Try again (Y/N): ");
            }
            if ("Y".equals(userIp)) {
                return true;
            } else if ("N".equals(userIp)) {
                return false;
            }
        }
    }

    public static boolean easyYesNo() {
        String userIp = input.nextLine();
        boolean X = false;
        while (true) {
            if (X) {
                System.out.println("Try again (Y/N): ");
            }
            if ("Y".equals(userIp)) {
                return true;
            } else if ("N".equals(userIp)) {
                return false;
            }
        }
    }

    public String inspect(Weapond object) {
        return object.Description();
    }

    // public String inspect(interactableObject object) {
    //     return object.Description();
    // }

    public void inspect(Door door) {
        if (easyYesNo("Would you like to inspect")) {
            System.out.println(door.getDescription());
        }

        if (door.isOpen()) {
            if (easyYesNo("Would you like to close the" + door.getName() + " door")) {
                door.close();
            }
        } else {
            if (easyYesNo("Would you like to open the" + door.getName() + " door")) {
                door.open();

                if (easyYesNo("Would you like to go through the" + door.getName() + " door")) {
                    door.goThrough();
                }
            }
        }

    }

    public void inspect(Chest chest) {

    }

    public void Update() {
        // Updates the player if they enter a new room.
    }
}
