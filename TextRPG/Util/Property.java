package TextRPG.Util;

import java.util.List;

public class Property {
    public record Weapond(String name, String Description, int damage, int critRate) {}
        /*
        Crit rate is out of 100, so a 60, would be 60 times out of 100;

        This automatically creates:
        constructor
        getters
        equals()
        hashCode()
        toString()       
        */

    public class Door {

        private String name;
        private String description;
        private boolean isOpen;

        public Door(String name, String description) {
            this.name = name;
            this.description = description;
            this.isOpen = false; // starts closed
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
            if (!isOpen) {
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
    }

    public class Chest {
        private String name;

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
}
