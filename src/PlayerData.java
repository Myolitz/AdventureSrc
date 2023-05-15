import java.util.ArrayList;

public class PlayerData {
    private String name;
    private ArrayList<String> inventory;

    public void defaultValues() {
        name = "none";
        inventory = new ArrayList<String>();
    }

    public PlayerData(String name) {
        this.name = name;
    }
    //FIXME get this working
    public void addInventory(String item) {
        inventory.add(item);
    }

    public String getName() {
        return name;
    }
    //FIXME stop this from giving errors
    public int getInventorySize() {
        return inventory.size();
    }
}
