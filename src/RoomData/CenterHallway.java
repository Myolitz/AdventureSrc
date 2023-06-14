package RoomData;
import java.util.Scanner;
import PlayerData.*;
public class CenterHallway {
    private String name;
    private String description;
    private boolean couchInteraction;


    public CenterHallway () {
        this.name = "Center Hallway";
        this.description = """
                                    What seems to be the heart of the house.
                             Connecting the front, back and western part of the house.
                                       What a comfortable looking couch...
                """;
        this.couchInteraction = false;
    }

    public void interaction(String object, PlayerData player) {
        if (player.isWet()) {
            System.out.println("""
                               As comfortable as it looks, sitting while wet is bad manners.
                    """);
        }
        else {
            if (!couchInteraction) {
                System.out.println("""
                              You sit down, you can feel your consciousness fading with how comfortable it is...
                                    Best to not keep extending your visit though, so you stand back up.
                    """);
            }
            else {
                System.out.println("""
                               The comfort of the couch beckons, though you must not falter in getting out.
                        """);
            }

        }
    }

    public String getRoomDesc() {
        return this.description;
    }

    public String getRoomName() {
        return this.name;
    }

}
