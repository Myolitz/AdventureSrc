package main.PlayerData;

import java.util.ArrayList;

public class PlayerData {
    private String name;
    private ArrayList<String> inventory;
    private String location;
    private boolean wet = true;
    public String whitespace = "";

    public void defaultValues() {
        name = "none";
        inventory = new ArrayList<String>(); //Prohibits NullPointerException, ran by main.PlayerData.main.PlayerData
        inventory.add("Coat");
        location = "Front Entrance";
    }

    public PlayerData(String name) { //Constructor/Setter, calls upon defaultValues
        defaultValues();
        this.name = name;
    }

    public String getName() { //Simple Getter
        return name;
    }

    public void getInventory() { //Checks player inventory, accessible via commands
        if (inventory.isEmpty()) {
            System.out.println("You got some pocket lint...that's about it\n");
        }
        else {
            for (int i = 0; i < inventory.size(); i++) {
                System.out.println("Slot " + (i + 1) + ": " + inventory.get(i));
            }
            System.out.println();
        }
    }
    public String getLocation() {
        return location;
    } //TODO: Francisco shenanigans, go bug him :troll:

    //Using location within the giant "while" loop for having the game run :thonk: check possible redundancy
//    public void setLocation(String newLocation) {
//        this.location = newLocation;
//    }

    public String outOfBounds() { //DNT, failsafe of sorts
        return "How did you get here?";
    }

    public boolean isWet() { return wet; }

    public void addItem(String item) {

        inventory.add(item);

        System.out.printf("%1$45s|%2$s|", whitespace, item);
    }

    public void useItem(String item) {

        for (int i = 0; i < inventory.size(); i++) {
            if (item.equals(inventory.get(i))) {
                inventory.remove(i);
                String itemUse = "You used ["+item+"]";
                System.out.printf("%1$45s|%2$s|\n\n1", whitespace, itemUse);
            }
            else {
                System.out.printf("%1$45s|You do not have that item!|", whitespace);
            }
        }
    }

    public void useItemGlobal(String item) {
        for (int i = 0; i < inventory.size(); i++) {
            if (item.equals(inventory.get(i))) {
                switch (item) {
                    case "Towel" -> {
                        wet = false;
                        useItem(item);
                    }
                    case "Iron Key", "Rusted Key", "Coat", "Handle" -> {
                        System.out.println("""
                                                        There isn't anything to use this on.\n
                        """);
                    }
                }
            }
        }
    }
    public boolean hasItem(String item) {
        boolean available = false;

        for (int i = 0; i < inventory.size(); i++) {
            if (item.equalsIgnoreCase(inventory.get(i))) {
                available = true;
                break;
            }
        }
        if (!available) {
            System.out.printf("%1$45s|You do not have that item!|\n", whitespace);
        }

        return available;
    }

    public void setLocation(String location) {
        this.location = location;
    }

}
