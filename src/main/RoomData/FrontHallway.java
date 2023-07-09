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
        formatDescription();
        this.typeWriterInteraction = false;
        this.drawerInteraction = false;
    }

    public void interaction(String object, PlayerData player, Scanner in) {

        if (object.equalsIgnoreCase("Painting") || object.equalsIgnoreCase("Paintings")) {
            String desc1 = "A painting of an empty coat rack and another of a populated one";
            String desc2 = "Quite the odd choice of art to display, but you aren't in a position to judge";

            System.out.printf("""
                    %1$25s|%2$s|
                    %1$18s|%3$s|
                    """, whitespace, desc1, desc2);
        }
        else if (object.equalsIgnoreCase("Drawer")) {
            String desc1 = "It's a drawer like many you've seen before, keyhole and all";
            System.out.printf("%1$27s|%2$s|\n", whitespace, desc1);
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
                                    String desc2 = "The key fits perfectly in the keyhole, unlocking the mechanism";
                                    player.useItem(item3);
                                    System.out.printf("%1$25.5s|%2$s|", whitespace, desc2);
                                    player.addItem("Handle");
                                    drawerInteraction = true;
                                    validChoice = true;
                                }
                                case "N" -> {
                                    System.out.printf("%1$38s|You stuff the key back in your pocket|\n", whitespace);
                                    validChoice = true;
                                }
                                default -> System.out.println("That is not an option");
                            }
                        }

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
            String desc1 = "Quite nostalgic seeing a mint condition type-writer";
            String desc2 = "If only you had an ink ribbon";

            System.out.printf("""
                    %1$33s|%2$s|
                    %1$45s|%3$s|
                    """, whitespace, desc1, desc2);
            // }
        }


    }
    public String getRoomDesc() {
        return this.description;
    }

    public String getRoomName() {
        return this.name;
    }

    /**
     * @see Entrance Line 22 or so for comment; same reason here and all other room files.
     */
//    public void setRoomDesc() { //Implement later
//        if (!typeWriterInteraction && drawerInteraction){
//           System.out.println("Option 1");
//        }
//        //Next 2 currently impossible, though will now mention the easter egg
//        else if (typeWriterInteraction && !drawerInteraction) {
//            System.out.println("Option 2");
//        }
//        else if (typeWriterInteraction && drawerInteraction) {
//            System.out.println("Option 3");
//        }
//    }

    public void formatDescription() {
        String desc1 = "";
        String desc2 = "";
        String desc3 = "";
        String desc4 = "";

        if (!typeWriterInteraction && drawerInteraction) {
            desc1 = "Going further into the house you are now in a quaint little hallway, adorned with some paintings.";
            desc2 = "On the opposite wall you see a nice little desk with two drawers and a typewriter on top of it.";
            desc3 = "With the drawer now open and empty, you still wonder how old must this place be if typewriters are still in use?";

            this.description = String.format("""
                    %1$10s|%2$s|
                    %1$11s|%3$s|
                    %1$2s|%4$s|
                    """, whitespace, desc1, desc2, desc3);

        } else if (typeWriterInteraction && !drawerInteraction) {
            //TODO: write proper descriptions
            System.out.println("Typewriter == true, drawer == false");
        } else if (typeWriterInteraction && drawerInteraction) {
            //TODO: write proper descriptions
            System.out.println("Typewriter && drawer == false;");
        } else {
            desc1 = "Going further into the house you are now in a quaint little hallway, adorned with some paintings.";
            desc2 = "On the opposite wall you see a nice little desk with two drawers and a typewriter on top of it.";
            desc3 = "How old must this place be if typewriters are still in use?";

            this.description = String.format("""
                    %1$10s|%2$s|
                    %1$11s|%3$s|
                    %1$28s|%4$s|
                    """, whitespace, desc1, desc2, desc3);
        }
    }
}
