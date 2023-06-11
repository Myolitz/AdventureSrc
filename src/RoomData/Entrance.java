package RoomData;

public class Entrance {
    private String name;
    private String description;
    private boolean plantInteraction = false;
    private boolean rackInteraction = false;


    public Entrance() {
        this.name = "Front Entrance";
        this.description = """
                Almost as if you were transported to a victorian mansion, you are hit with an immediate sense of luxury. 
                                                    Are you meant to be here?  
                  Intricate patterns line the wall, opening up to a hallway straight head, past a wilted potted plant
                and a coat rack that towers over you, leaving you wondering how one is supposed to hang their coat on it.
                """;
    }

    public String getRoomDesc() {
        return description;
    }

    public void setRoomDesc(boolean rackInteraction, boolean plantInteraction) {
        if (rackInteraction && !plantInteraction) {
            this.description = """
                Almost as if you were transported to a victorian mansion, you are hit with an immediate sense of luxury. 
                                                    Are you meant to be here?  
                  Intricate patterns line the wall, opening up to a hallway straight head, past a wilted potted plant
                    and a coat rack that towers over you, now adorned with your wet coat, seeming taller than ever.
                """;
        }
        else if (!rackInteraction && plantInteraction) {
            this.description = """
                    Almost as if you were transported to a victorian mansion, you are hit with an immediate sense of luxury. 
                                                    Are you meant to be here?  
                       Intricate patterns line the wall, opening up to a hallway straight head, past a now dug up potted
                plant and a coat rack that towers over you, leaving you wondering how one is supposed to hang their coat on it.
                """;
        }
        else if (rackInteraction && plantInteraction) {
            this.description = """
                   Almost as if you were transported to a victorian mansion, you are hit with an immediate sense of luxury. 
                                                        Are you meant to be here?  
                     Intricate patterns line the wall, opening up to a hallway straight head, past a now dug up potted 
                plant and a coat rack that towers over you, now adorned with your wet coat, though it now seems taller than ever.
                """;
        }
    }

    //TODO: Make separate interactions for plant and rack
    public void interaction(String object) {
        String str;
        //Use system below on other interact-able objects down the line, its simple, though might require more interacted boolean vars :)
        if (object.equalsIgnoreCase("Rack")) {
            if (!rackInteraction) {
                System.out.println("It certainly is a coat rack");
                rackInteraction = true;
                setRoomDesc(rackInteraction, plantInteraction);
            }
            else {
                System.out.println("You only came in with one coat");
            }
        }
        else if (object.equalsIgnoreCase("Plant")) {
            if (!plantInteraction) {
                System.out.println("It's a fucking plant");
                this.plantInteraction = true;
                setRoomDesc(rackInteraction, plantInteraction);
            }
            else {
                System.out.println("You've already searched this\n");
            }
        }


    }



}
