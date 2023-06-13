package PlayerData;

import java.util.ArrayList;

public class PlayerData {
    private String name;
    private ArrayList<String> inventory;
    private String location;
    private boolean wet = true;

    public void defaultValues() {
        name = "none";
        inventory = new ArrayList<String>(); //Prohibits NullPointerException, ran by PlayerData.PlayerData
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
    }

    public void useItem(String item) {
        for (int i = 0; i < inventory.size(); i++) {
            if (item.equals(inventory.get(i))) {
                inventory.remove(i);
            }
            else {
                continue;
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

        return available;
    }

}
