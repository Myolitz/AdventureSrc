package main.RoomData;
import java.util.Scanner;
import main.PlayerData.*;

public class BackEntrance {
    public boolean isDoorUnlocked;
    private String name;
    private String description;
    private boolean doorHandle;

    public BackEntrance() {
        this.name = "Back Entrance";
        formatDescription();
        isDoorUnlocked = false;
    }

    public String getRoomDesc() {
        return this.description;
    }

    public String getRoomName() {
        return this.name;
    }

    public void interaction(Scanner in, String object, PlayerData player) {
        if (object.equalsIgnoreCase("Door")) {
            System.out.println("                    A door befitting of an outdoor shack rather than a fancy mansion");
            String action = in.next();
            if (action.equalsIgnoreCase("Use")) {
                System.out.println("What item do you want to use?");
                String userItem = in.next();
                if (userItem.equalsIgnoreCase("Handle")) {
                    System.out.println("Do you want to use this item? [Y/N]");
                    boolean validChoice = false;
                    while (!validChoice) {
                        String option = in.next();
                        switch (option) {
                            case "Y" -> {
                                System.out.println("""
                                                                You place the handle on the door, letting you at least grip it
                                                                
                                                                                        You used [Handle]
                                        """);
                                player.useItem("Handle");
                                doorHandle = true;
                                setRoomDesc();
                                validChoice = true;
                            }
                            case "N" -> {
                                System.out.println("                    You put the handle back in your pocket");
                            }
                            default -> {
                                System.out.println("                            That is not a valid choice");
                            }
                        }
                    }
                }
                else if (userItem.equalsIgnoreCase("Iron")) { //TODO: fix by making it detect "iron key" fully (working as intended, so *IT*)
                    if (!doorHandle) {
                        System.out.println("""
                                                        There isn't anything to use the key on, you put it back in your pocket
                                """);
                    }
                    else {
                        boolean validChoice = false;
                        while (!validChoice) {
                            System.out.println("Would you like to use this item? [Y/N]");
                            String option = in.next();
                            switch (option) {
                                case "Y" -> {
                                    System.out.println("""
                                                                        You insert the key into the lock and turn it...
                                                                                          it unlocks!
                                                                                          
                                                                                      You used [Iron Key]
                                                    """);
                                    player.useItem("Iron Key");
                                    isDoorUnlocked = true;
                                    setRoomDesc();
                                    validChoice = true;
                                }
                                case "N" -> {
                                    System.out.println("                            You put the key back in your pocket");
                                }
                                default -> {
                                    System.out.println("                                  This is not a valid option");
                                }
                            }
                        }
                    }
                }

            }
        }
    }

    public void setRoomDesc() {
        if (doorHandle && !isDoorUnlocked) {
            formatDescription();
        }
        else if (doorHandle && isDoorUnlocked) {
            formatDescription();
        }
    }
    public boolean isDoorUnlocked() {
        return isDoorUnlocked;
    }

    public void formatDescription() {
        String whitespace = "";
        if (doorHandle && !isDoorUnlocked) {
            String desc1 = "You reach a door that you can only assume leads outside";
            String desc2 = "Its got a handle now, though how to unlock it...";
            this.description = String.format("""
                |%2$-10s|
                %1$4s|%3$30s|
                """, whitespace, desc1, desc2);
        }
        else if (doorHandle && isDoorUnlocked) {
            String desc1 = "You reach a door that you can only assume leads outside";
            String desc2 = "It's now unlocked...you can only wonder what waits ahead...";
            String.format("""
                %1$2s|%2$-10s|
                |%3$30s|
                """, whitespace, desc1, desc2);
        }
        else {
            String desc1 = "You reach a door that you can only assume leads outside";
            String desc2 = "It's missing a handle though...";
            this.description = String.format("""
                        |%2$-10s|
                        %1$10s|%3$30s|
                        """, whitespace, desc1, desc2);
        }

    }
}
