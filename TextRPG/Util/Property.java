package TextRPG.Util;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class Property {

    public interface Item {
        public String getName();
        public String getDescription();
    }

    public interface SpecialObject {
        public String getName();
        public String getDescription();
    }

    public class Player {

        private Room currentRoom;
        private List<Item> inventory;
        private static final int MAX_INVENTORY_SIZE = 10;
        
        public Player(Room currentRoom) {
            this.currentRoom = currentRoom;
            this.inventory = new ArrayList<>();
        }
        
        // Inventory management methods
        public boolean addItem(Item item) {
            if (inventory.size() < MAX_INVENTORY_SIZE) {
                inventory.add(item);
                System.out.println(item.getName() + " added to inventory.");
                return true;
            } else {
                System.out.println("Inventory is full! Can't add " + item.getName());
                return false;
            }
        }
        
        public Item removeItem(int index) {
            if (index >= 0 && index < inventory.size()) {
                Item item = inventory.remove(index);
                System.out.println(item.getName() + " removed from inventory.");
                return item;
            }
            System.out.println("Invalid item index.");
            return null;
        }
        
        public Item removeItem(String itemName) {
            for (int i = 0; i < inventory.size(); i++) {
                if (inventory.get(i).getName().equalsIgnoreCase(itemName)) {
                    return removeItem(i);
                }
            }
            System.out.println("You don't have " + itemName + ".");
            return null;
        }
        
        public boolean hasItem(String itemName) {
            return inventory.stream()
                .anyMatch(item -> item.getName().equalsIgnoreCase(itemName));
        }
        
        public boolean hasKey(Key targetKey) {
            return inventory.stream()
                .filter(item -> item instanceof Key)
                .map(item -> (Key) item)
                .anyMatch(key -> targetKey.canUnlock(key) || key == targetKey);
        }
        
        public Item getItem(String itemName) {
            return inventory.stream()
                .filter(item -> item.getName().equalsIgnoreCase(itemName))
                .findFirst()
                .orElse(null);
        }
        
        public List<Item> getItemsByType(Class<?> type) {
            return inventory.stream()
                .filter(type::isInstance)
                .collect(Collectors.toList());
        }
        
        public void showInventory() {
            if (inventory.isEmpty()) {
                System.out.println("Your inventory is empty.");
            } else {
                System.out.println("=== Inventory ===");
                for (int i = 0; i < inventory.size(); i++) {
                    Item item = inventory.get(i);
                    String type = item instanceof Weapon ? "Weapon" : 
                                item instanceof Key ? "Key" : "Item";
                    System.out.printf("%d: %s (%s) - %s%n", 
                        i, item.getName(), type, item.getDescription());
                    
                    if (item instanceof Weapon) {
                        Weapon weapon = (Weapon) item;
                        System.out.printf("   Damage: %d, Crit Rate: %d%%%n", 
                            weapon.getDamage(), weapon.getCritRate());
                    }
                }
                System.out.printf("Space: %d/%d%n", inventory.size(), MAX_INVENTORY_SIZE);
            }
        }
        
        public void useItem(String itemName) {
            Item item = getItem(itemName);
            if (item == null) {
                System.out.println("You don't have " + itemName + ".");
                return;
            }
            
            if (item instanceof Key) {
                System.out.println("You can use keys on doors or chests.");
            } else if (item instanceof Weapon) {
                System.out.println("You equip the " + item.getName() + ".");
                // Handle weapon equipping logic
            } else {
                System.out.println("You can't use that item right now.");
            }
        }

        public Room getCurrentRoom() {
            return currentRoom;
        }

        public void transformCurrentRoom(Room room) {
            currentRoom = room;
        }
    }

    public static class Door implements SpecialObject {

        private String name;
        private String description;
        private boolean isOpen;
        private boolean isLocked;
        private Room room1;
        private Room room2;
        private Key key;

        public Door(String name, String description, Room room1, Room room2) {
            this.name = name;
            this.description = description;
            this.isOpen = false;
            this.room1 = room1;
            this.room2 = room2;
            this.key = null;
        }

        public Door(String name, String description, Room room1, Room room2, Key key) {
            this.name = name;
            this.description = description;
            this.isOpen = false;
            this.isLocked = true;
            this.room1 = room1;
            this.room2 = room2;
            this.key = key;
        }

        public void open() {
            if (!isLocked) {
                if (!isOpen) {
                    isOpen = true;
                    System.out.println("You open the " + name + ".");
                } else {
                    System.out.println("The " + name + " is already open.");
                }
            } else {
                System.out.println("The " + name + " is locked.");
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

        public void unlock(Player player) {
            if (player.hasKey(this.key)) {
                if (isLocked) {
                    isLocked = false;
                    isOpen = true;
                    System.out.println("You unlock and open the " + name + ".");
                } else {
                    System.out.println("The " + name + " is already unlocked.");
                }
            } else {
                System.out.println("You don't have the correct key, you need " + key.getName() + ".");
            }
        }

        public void lock(Player player) {
            if (player.hasKey(this.key)) {
                if (!isLocked) {
                    isLocked = true;
                    isOpen = false;
                    System.out.println("You lock the " + name + ".");
                } else {
                    System.out.println("The " + name + " is already locked.");
                }
            } else {
                System.out.println("You don't have the correct key, you need " + key.getName() + ".");
            }
        }


        public void goThrough(Player player) {
            if (isOpen) {
                System.out.println("You go through the " + name + ".");
                Room helper = player.getCurrentRoom();
                player.transformCurrentRoom(getOtherRoom(helper));
            } else {
                System.out.println("The " + name + " is closed.");
            }
        }

        public boolean isOpen() {
            return isOpen;
        }
        
        public Room getOtherRoom(Room currentRoom) {
            if (currentRoom == room1) {
                return room2;
            } else if (currentRoom == room2) {
                return room1;
            } else {
                return null;
            }
        }

        @Override
        public String getName() {
            return name;
        }

        @Override
        public String getDescription() {
            return description;
        }

        public Key getKey() {
            return key;
        }
    }

    public static class Chest implements SpecialObject {
        private String name;
        private String description;
        private Key key;
        private boolean isLocked;
        private List<Item> itemList;

        public Chest(String name, String description) {
            this.name = name;
            this.description = description;
            key = null;
            this.isLocked = (key != null);
            this.itemList = new ArrayList<>();
        }

        public Chest(String name, String description, Key key) {
            this.name = name;
            this.description = description;
            this.isLocked = (key != null);
            this.key = key;
            this.itemList = new ArrayList<>();
        }

        public void addItem(Item item) {
            itemList.add(item);
        }

        public void lockInteract(Key key) {
            if (this.key == key) {
                if (isLocked) {System.out.println("Locked chest " + name);}
                         else {System.out.println("UnLocked chest " + name);}
                isLocked = !isLocked;
            } else if (this.key == null) {
                System.out.println("Does not have a lock on it");
            }else {
                System.out.println("You do not have the proper key, this chest requires the " + this.key.getName() + ".");
            }
        }

        public void removeItem(int index) {
            if (index >= 0 && index < itemList.size()) {
                itemList.remove(index); // removes and returns
            }
        }

        public Item takeItem(int index) {
            if (index >= 0 && index < itemList.size()) {
                return itemList.remove(index); // removes and returns
            }
            return null;
        }

        public void showContents() {
            System.out.println("Contents of " + name);

            if(!isLocked) {
                if (itemList.isEmpty()) {
                    System.out.println("The chest is empty.");
                } else {
                    for (int i = 0; i < itemList.size(); i++) {
                        System.out.println(i + ": " + itemList.get(i));
                    }
                }
            } else {
                System.out.println(name + " is locked.");
                System.out.println("You need the " + key + ".");
            }
        }

        public boolean isLocked() {
            return isLocked;
        }

        @Override
        public String getName() {
            return name;
        }

        @Override
        public String getDescription() {
            return description;
        }

        public Key getKey() {
            return key;
        }
    }

    public static class Room {
        private String name;
        private String description;
        private List<SpecialObject> objects;

        public Room(String name, String description) {
            this.name = name;
            this.description = description;
            this.objects = new ArrayList<>();
        }

        public void addObject(SpecialObject object) {
            this.objects.add(object);
        }

        public SpecialObject getObject(int num) {
            return this.objects.get(num);
        }

        public int sizeObject() {
            return this.objects.size();
        }

        public void removeObject(int num) {
            this.objects.remove(num);
        }

        public void removeObject(SpecialObject object) {
            int num = 0;

            while (true) {
                if (getObject(num) == object) {
                    removeObject(num);
                    break;
                }
                num += 1;
            }
        }

        public String getName() {
            return name;
        }

        public String getDescription() {
            return description;
        }

        public List<SpecialObject> getObjects() {
            return objects;
        }
    }

    public static class Weapon implements Item {
        private String name;
        private String description;
        private int damage;
        private int critRate;

        public Weapon(String name, String description, int damage, int critRate) {
            this.name = name;
            this.description = description;
            this.damage = damage;
            this.critRate = critRate;
        }

        @Override
        public String getName() {
            return name;
        }

        @Override
        public String getDescription() {
            return description;
        }

        public int getDamage() {
            return damage;
        }

        public int getCritRate() {
            return critRate;
        }
    }

    public static class Key implements Item {
        private String name;
        private String description;
        private String keyId;  // Unique identifier for key matching
        
        public Key(String name, String description) {
            this(name, description, UUID.randomUUID().toString());
        }
        
        public Key(String name, String description, String keyId) {
            this.name = name;
            this.description = description;
            this.keyId = keyId;
        }
        
        @Override
        public String getName() {
            return name;
        }
        
        @Override
        public String getDescription() {
            return description;
        }
        
        public String getKeyId() {
            return keyId;
        }
        
        public boolean canUnlock(Key key) {
            return key != null && this.keyId.equals(key.keyId);
        }
    }
}
