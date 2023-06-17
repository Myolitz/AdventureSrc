package RoomData;
import PlayerData.*;
import java.util.Scanner;

public class BackHallway {
    private String name;
    private String description;
    private boolean drawerInteraction;

    public BackHallway() {
        this.name = "Back Hallway";
        this.description = """
                            The back entrance is now visible, however you are now in a hallway
                              that is populated only by a roll-top desk and a mirror above it.
                                        Reminds you of the bread boxes of old
                """;
    }

    public void setRoomDesc() {
        if (drawerInteraction) {
            this.description = """
                            The back entrance is now visible, however you are now in a hallway
                             that is populated only by a roll-top desk and a mirror above it.
                     Reminds you of the bread boxes of old, just needing the bread to fill the space inside.
                    """;
        }
    }

    public void interaction(String object, PlayerData player) {
        if (object.equalsIgnoreCase("Desk")) {
            if (!player.hasItem("Coat")) {
                if (!drawerInteraction) {
                    System.out.println("                    It's open! Revealing a towel alongside a rusted key");
                    System.out.println("                            You found a [Towel] and [Rusted Key]\n");
                    drawerInteraction = true;
                    player.addItem("Towel");
                    player.addItem("Rusted Key");
                } else {
                    System.out.println("                    You already grabbed everything here");
                }
            } else {
                System.out.println("             Hmm, it's closed...there must a be a way to get inside the desk.\n");
            }
        }
        else if (object.equalsIgnoreCase("Mirror")) {
            if (!player.isWet()) {
                System.out.println("""
                           While you are no longer fully wet, your clothes still are...Hope you got a change of clothes
                        """);
            }
            else {
                System.out.println("        You see your wet-disheveled self staring right back at you\n");
            }
        }
    }

    public String getRoomName() {
        return this.name;
    }

    public String getRoomDesc() {
        return this.description;
    }
}
