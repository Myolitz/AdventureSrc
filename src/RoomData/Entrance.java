package RoomData;

public class Entrance {
    private String name;
    private String description;
    public boolean hasInteracted = false;


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

    //TODO: Make separate interactions for plant and rack
    public void interaction() {
        String str;

        //Use system below on other interactable objects down the line, its simple, though might require more interacted boolean vars :)
        if (!hasInteracted) {
            System.out.println("""
                    Bruh its a fucking plant gtfo
                    """);
            this.hasInteracted = true;
        }
        else {
            System.out.println("You've already searched this\n");

        }
    }



}
