package main.RoomData;
import main.PlayerData.*;

public class Entrance {
    private String name;
    private String description;
    private boolean plantInteraction = false;
    private boolean rackInteraction = false;
    public String whitespace = "";


    public Entrance() {
        this.name = "Front Entrance";
        formatDescription();
    }

    public String getRoomDesc() {
        return description;
    }

    /**
     * @see formatDescription()
     * Method deprecated in favor of one that already has the formatting
     * (And doesn't rely on passing these 2 due to their global scope)
     */
//    public void setRoomDesc(boolean rackInteraction, boolean plantInteraction) {
//        if (rackInteraction && !plantInteraction) {
//            formatDescription();
//        }
//        else if (!rackInteraction && plantInteraction) {
//            formatDescription();
//        }
//        else if (rackInteraction && plantInteraction) {
//            formatDescription();
//        }
//    }
    /**
     * @param object is user-input for whatever item they wanted to interact with
     * @param Player used for accessing the inventory in order to remove the item
     */
    public void interaction(String object, PlayerData Player) {
        String str;

        //Use system below on other interact-able objects down the line, its simple, though might require more interacted boolean vars :)
        //WTF are you talking about past MYO ^
        if (object.equalsIgnoreCase("Rack")) {
            if (!rackInteraction) {
                String desc1 = "You hang your coat on the rack, hearing a loud \"click\" come from further in the house.";
                String desc2 = "Well at least you can stop wearing that wet coat...";

                System.out.printf("""
                        %1$12s|%2$s|
                        %1$25s|%3$s|
                        """, whitespace, desc1, desc2);
                Player.useItem("Coat");
                rackInteraction = true;
                formatDescription();
            }
            else {
                String desc1 = "You only came in with one coat";
                System.out.printf("%1$35s|%2$s|", whitespace, desc1);
            }
        }
        else if (object.equalsIgnoreCase("Plant")) {
            if (!plantInteraction) {
                //Adding temporary "gameplay" for once you get an equally temporary towel and key you get from the back hallway (you use this iron key to finish the game).
                if (!Player.isWet()) { //FIXME properly implement once the "endless" hallway portion is done
                    String desc1 = "You notice something hiding in between the dead leaves and dig it up.";

                    System.out.printf("%1$18s|%2$s|", whitespace, desc1);

                    Player.addItem("Iron Key");
                    this.plantInteraction = true;
                    formatDescription();
                }
                else {
                    String desc1 = "Probably not the best idea to mess with dirt while wet.";
                    System.out.printf("%1$22s|%2$s|", whitespace, desc1);
                }
            }
            else {
                System.out.println("You've already searched this\n");
            }
        }
    }
    public String getRoomName() {
        return this.name;
    }
    //There's probably a better way of doing this but oh well
    public void formatDescription() {

        String desc1 = "Almost as if you were transported to a Victorian mansion, you are hit with an immediate sense of luxury.";
        String desc2;
        String desc3;
        String desc4;

        if (rackInteraction && !plantInteraction) {
            desc2 = "Intricate patterns line the wall, opening up to a hallway straight ahead, past a wilted potted plant.";
            desc3 = "A coat rack opposite the plant towers over you, now adorned with your wet coat, seems taller than ever.";

            description = String.format("""
                |%2$-10s|
                %1$1s|%3$-10s|
                |%4$-10s|
                """, whitespace, desc1, desc2, desc3);

        } else if (!rackInteraction && plantInteraction) { //I don't think this is executed EVER rn, though maybe later
            desc2 = "Intricate patterns line the wall, opening up to a hallway straight ahead, past a now dug up potted plant";
            desc3 = "A coat rack opposite the plant towers over you, leaving you wondering how one is supposed to hang their coat on it.";

            description = String.format("""
                %1$6s|%2$-10s|
                %1$6s|%3$-10s|
                |%4$-10s|
                """, whitespace, desc1, desc2, desc3);


        } else if (rackInteraction && plantInteraction) {
            desc2 = "Intricate patterns line the wall, opening up to a hallway straight ahead, past a now dug up potted plant";
            desc3 = "and a coat rack that towers over you, now adorned with your wet coat, though it now seems taller than ever.";

            description = String.format("""
                    %1$1.5s|%2$-10s|
                    %1$1.5s|%3$-10s|
                    |%4$-10s|
                    """, whitespace, desc1, desc2, desc3);
        }
        else {
            desc2 = "Are you meant to be here?";
            desc3 = "Intricate patterns line the wall, opening up to a hallway straight ahead, past a wilted potted plant.";
            desc4 = "A coat rack opposite the plant towers over you, leaving you wondering how one is supposed to hang their coat on it.";

            description = String.format("""
                %1$6s|%2$-10s|
                %1$40s|%3$-10s|
                %1$8s|%4$-10s|
                |%5$-10s|
                """, whitespace, desc1, desc2, desc3, desc4);
        }
    }
}
