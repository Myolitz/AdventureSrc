package main.RoomData;
import main.PlayerData.*;
import java.util.Scanner;

public class FrontHallway extends BaseRoom{
//    private String name;
//    private String description;
    private boolean typeWriterInteraction;
    private boolean drawerInteraction;

    public FrontHallway() {
        name = "Front Hallway";
        description = """
                       Going further into the house you are now in a quaint little hallway, adorned with some paintings.
                        On the opposite wall you see a nice little desk with two drawers and a typewriter on top of it.
                                          How old must this place be if typewriters are still in use?
                """;
        this.typeWriterInteraction = false;
        this.drawerInteraction = false;
    }

    public void interaction(String object, PlayerData player, Scanner in) {

        if (object.equalsIgnoreCase("Painting") || object.equalsIgnoreCase("Paintings")) {
            System.out.println("""
                                A painting of an empty coat rack and another of a used coat rack...
                                  Quite the odd choice of art to display, but to each their own
                """);
        }
        else if (object.equalsIgnoreCase("Drawer")) {
            System.out.println("It's a drawer like many you've seen before in your everyday life, keyhole and all");
            String action = in.next();
            if (action.equalsIgnoreCase("Use")) {
                System.out.println("What item do you want to use?");
                String item1 = in.next();
                String item2 = in.next();
                String item3 = item1 + " " + item2; //It wouldn't work w/o doing this...uhh TODO: Look into this
                if (item3.equalsIgnoreCase("Rusted Key")) {
                    if (player.hasItem(item3)) {
                        boolean validChoice = false;
                        while (!validChoice) {
                            System.out.println("Do you want to use the item? [Y/N]");
                            String option = in.next();
                            switch (option) {
                                case "Y" -> {
                                    player.useItem(item3);
                                    System.out.println("""
                                                                The key fits perfectly in the keyhole, unlocking the mechanism.
                                                                
                                                                                    You found a [Handle]!
                                            """);
                                    player.addItem("Handle");
                                    drawerInteraction = true;
                                    validChoice = true;
                                }
                                case "N" -> {
                                    System.out.println("""
                                                                            You stuff the key back in your pockets.
                                            """);
                                    validChoice = true;
                                }
                                default -> System.out.println("That is not an option");
                            }
                        }

                    }
                    else {
                        System.out.println("""
                                        You do not have that item in your inventory!""");
                    }
                } else {
                    System.out.println("""
                                        It doesn't budge as if something is blocking its exit from the desk.
                            """);
                }
            }
            else if (action.equalsIgnoreCase("Back")) {
                System.out.println("""
                                        You can check the drawer again later\n""");
            }
        }
        else if (object.equalsIgnoreCase("Typewriter")) {
            /*
            TODO add this easter egg section later
            if (player.hasItem("Ink Ribbon")) {
                System.out.println("Would you like to use the ink ribbon? [Y/N]");

                boolean validChoice = false;
                String option = in.next();
                while (!validChoice) {
                    switch (option) {
                        case "Y" -> {
                            System.out.println("""
                                                A wave of nostalgia rushes over you.
                                   The safe haven in the midst of the horrors dwelling inside the spencer mansion.
                                           What ever did happen to Chris and Jill you wonder...
                            """);
                            typewriterInteraction = true;
                            validChoice = true;
                        }
                        case "N" -> {
                            System.out.println("You put the ink ribbon back in your pocket");
                            validChoice = true;
                        }
                        default -> {
                            System.out.println("Not an option");
                        }
                   }
                }
            }
            else if (!player.hasItem("Ink Ribbon") { */
            //Indent later once easter egg implemented TODO put ink ribbon @ room right of exit (name it "Back Corner" or something lol)
            System.out.println("""
                                       Quite nostalgic seeing a mint condition type-writer.
                                                 If only you had an ink ribbon.
                    """);
            // }
        }


    }
    public String getRoomDesc() {
        return this.description;
    }

    public String getRoomName() {
        return this.name;
    }

    public void setRoomDesc() { //Implement later
        if (!typeWriterInteraction && drawerInteraction){
           System.out.println("Option 1");
        }
        else if (typeWriterInteraction && !drawerInteraction) {
            System.out.println("Option 2");
        }
        else if (typeWriterInteraction && drawerInteraction) {
            System.out.println("Option 3");
        }
    }

}
