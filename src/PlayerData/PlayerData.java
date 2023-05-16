package PlayerData;

import java.util.ArrayList;

public class PlayerData {
    private String name;
    private ArrayList<String> inventory;

    public void defaultValues() {
        name = "none";
        inventory = new ArrayList<String>(); //Prohibits NullPointerException, ran by PlayerData.PlayerData
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
            System.out.println("You got some picket lint...that's about it");
        }
        else {
            for (int i = 0; i < inventory.size(); i++) {
                System.out.println("Slot " + i + ": " + inventory.get(i));
            }
        }
    }
}
