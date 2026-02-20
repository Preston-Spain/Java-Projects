package TextRPG.Util;

import java.util.List;

public class Property {
    public record Weapond(String name, String Description, int damage, int critRate) {
        /*
        Crit rate is out of 100, so a 60, would be 60 times out of 100;

        This automatically creates:
        constructor
        getters
        equals()
        hashCode()
        toString()       
        */
    }

    public static class Door { // TODO add door area, where there are between

        @SuppressWarnings("FieldMayBeFinal")
        private String name;
        @SuppressWarnings("FieldMayBeFinal")
        private String description;
        private boolean isOpen;
        private Room room1;
        private Room room2;

        public Door(String name, String description, Room room1, Room room2) {
            this.name = name;
            this.description = description;
            this.isOpen = false; // starts closed
            this.room1 = room1;
            this.room2 = room2;
        }

        public void open() {
            if (!isOpen) {
                isOpen = true;
                System.out.println("You open the " + name + ".");
            } else {
                System.out.println("The " + name + " is already open.");
            }
        }

        public void close() {
            if (isOpen) {
                isOpen = false;
                System.out.println("You close the " + name + ".");
            } else {
                System.out.println("The " + name + " is already closed.");
            }
        }

        public void goThrough() {
            if (isOpen) {
                System.out.println("You go through the " + name + ".");
            } else {
                System.out.println("The " + name + " is closed.");
            }
        }

        public boolean isOpen() {
            return isOpen;
        }

        public String getName() {
            return name;
        }

        public String getDescription() {
            return description;
        }

        public Room getOtherRoom(Room currentRoom) {
            if (currentRoom == room1) {
                return room2;
            } else if (currentRoom == room2) {
                return room1;
            } else {
                return null; // door not connected to this room
            }
        }
    }

    public static class Chest {
        @SuppressWarnings("FieldMayBeFinal")
        private String name;

        public Chest(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }

        private List<Weapond> weapondsLst;

        public void addWeapond(Weapond weaponds) {
            weapondsLst.add(weaponds);
        }

        public Weapond takeWeapond(int index) {
            if (index >= 0 && index < weapondsLst.size()) {
                return weapondsLst.remove(index); // removes and returns
            }
            return null;
        }

        public void showContents() {
            System.out.println("Contents of " + name);

            if (weapondsLst.isEmpty()) {
                System.out.println("The chest is empty.");
            } else {
                for (int i = 0; i < weapondsLst.size(); i++) {
                    System.out.println(i + ": " + weapondsLst.get(i));
                }
            }
        }
    }

    public static class Room {
        private String name;

        public Room(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }
    }

    public class Player {

    private Room currentRoom;

    public Player(Room startingRoom) {
        this.currentRoom = startingRoom;
    }

    public void goThrough(Door door) {
        if (!door.isOpen()) {
            System.out.println("The door is closed.");
            return;
        }

        Room nextRoom = door.getOtherRoom(currentRoom);

        if (nextRoom != null) {
            currentRoom = nextRoom;
            System.out.println("You enter the " + currentRoom.getName() + ".");
        } else {
            System.out.println("You can't go through that door from here.");
        }
    }

    public Room getCurrentRoom() {
        return currentRoom;
    }
}
}
