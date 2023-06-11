/**
 * Lazy Terminology Key:
 * DNT = DO NOT TOUCH (aka its working as intended)
 * NYI = Not yet implemented
 * IT = In Testing
 **/

import PlayerData.PlayerData;
import RoomData.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        //Program Initialization
        printMenu();
        int menuChoice = userChoice(input);
        boolean validChoice = false;
        boolean gameStart = false;
        String userStr = "";

        //Keeps you in the menu as long as you haven't selected the "play" option
        while (!gameStart) {
        //Ensures one of 4 choices, supports "Back" command
            while (!validChoice || (userStr.equalsIgnoreCase("Back") || !(menuChoice == 4))) {
                if (userStr.equalsIgnoreCase("Back")) {
                    printMenu();
                    menuChoice = userChoice(input);
                    userStr = "";
                }
                switch (menuChoice) {
                    case 1 -> { //The start of the game, create separate class later for levels
                        System.out.println("Let's get started shall we then?");
                        validChoice = true;
                        gameStart = true;
                        String name = charName(input);
                        PlayerData player = new PlayerData(name);
                        startGame(input, player);
                        menuChoice = 4; //I have 0 idea why, but not having this makes it loop, so yeah. *IT*
                    }
                    case 2 -> { //Gives version. TODO: Manually change this
                        System.out.println("You are currently on version Alpha 0.1.2.1 - Front Entrance WIP");
                        validChoice = true;
                        userStr = userChoiceStr(input);
                    }
                    case 3 -> {
                        //TODO: Update as you develop game
                        System.out.println(
                                """
                                        There aren't many controls, through the basic ones include (They are case-sensitive btw)
                                        - Forward
                                        - Backward
                                        - Inventory
                                        - Interact
                                        - Back, to back out of menus like this one
                                        - Location
                                        - You can type 'Quit' at any given type to exit the game once you start (NYI)
                                        - Left/Right NYI"""
                        );
                        validChoice = true;
                        userStr = userChoiceStr(input);
                    }
                    case 4 -> { //Quit
                        System.out.println("Hope you'll come play again!");
                        validChoice = true;
                    }
                    default -> {
                        System.out.println("Oops, that's not an option!");
                        menuChoice = userChoice(input);
                        validChoice = false;
                    }
                }
            }
        }
        input.close();
    }

    //Prints out menu using perfect size array - DNT
    public static void printMenu() {
        String[] menuOptions = new String[] {"1. Play", "2. Version", "3. Controls", "4. Quit"};
        //noinspection SpellCheckingInspection
        System.out.println("""
                             Welcome to
                           The Adventureᵀᴹ
                           
                         Created by me, Myo :)
                """
        );
        for (String menuOption : menuOptions) {
            System.out.println(menuOption);
        }
    }


    public static int userChoice(Scanner in) {
        return in.nextInt();
    }
    //Yes I have to have 2 cause one returns number and the other String - DNT either
    public static String userChoiceStr(Scanner in) {
        return in.next();
    }

    //Unsure if any dialogue will even happen, so method might get yeeted - DNT (for now)
    public static String charName (Scanner in) {
        System.out.println("Enter your characters name (Won't affect story that much).");
        return in.next();
    }

    public static void startGame(Scanner in, PlayerData player) {

        //Keeps the loop going ofc, only returning false and exiting once game is done
        boolean gameRunning = true;
        String playerLocation = player.getLocation();

        //Going to initialize all room objects as to not have them created every time as previous builds.

        //REMEMBER TO FUCKING UPDATE THE VARIABLE, YOU YES YOU FUTURE MYO
        while (gameRunning) {
            //Background, maybe replace with object elsewhere idk
            System.out.println("""
                            You had no idea where you were going after a night of drinking.
                                    Was it even your house that you stumbled upon?
                A feeling of uneasiness washes over, but with it raining you disregard the possible concerns.
                   The door opens without so much as a speck of resistance and throw yourself into the house.
                """);

            //Since its a while loop, remember to add
            switch (playerLocation) {
                case "Front Entrance" -> {
                    Entrance roomOne = new Entrance();
                    playerLocation = frontEntrance(in, roomOne, player); //TODO: finish implementation, *IT*
                    System.out.println("\nTest Room 1 Complete\n");
                    gameRunning = false; //For test purposes, setting to false here, the logic in this entire switch statement might bite me in the ass later
                    }
                case "Room 2" -> {        //TODO: implement
                    Hallway1 roomTwo = new Hallway1();
                    //playerLocation = frontHallway(in, roomTwo, player); //TODO implement using same methodology as front entrance, maybe move actions to Entrance.java rather than Main.java
                    System.out.println("Test Room 2");
                }
                case "Room 3" -> {System.out.println("Test Room 3");} //TODO: implement
                case "Room 4" -> {System.out.println("Test Room 4");} //TODO: implement
                case "Room 5" -> {System.out.println("Test Room 5");} //TODO: implement
                case "Quit" -> {
                    System.out.println("Sorry to see you go, hope you can play again :)");
                    gameRunning = false;
                    break; } //TODO implement "Quit" checks at any time so that its a universally accepted command
                default -> {
                    System.out.println("How did you even get this?");
                    throw new ArrayIndexOutOfBoundsException(); //TODO: make this make sense by switching userpos to 2D array. or whatever francisco has recommended
                }
            }
        }
    }

    public static String frontEntrance(Scanner in, Entrance roomOne, PlayerData player) {
        //Commented to try out the object implementation
//        System.out.println("""
//                                    Almost as if you were transported to a victorian mansion,
//                            you are hit with an immediate sense of luxury, are you meant to be here?
//
//                     Intricate patterns line the wall, opening up to a hallway straight head, past a potted plant
//                and a coat rack that towers over you, leaving you wondering how one is supposed to hang their coat on it.
//                """);

        //Nested while loops lets fucking GOOOOOOOOOOOOOOOO
        String choice = "";
        System.out.println(roomOne.getRoomDesc());

        //This entire thing is probably eternally IT, uh check necessity of the commented code later
        while (!(choice.equalsIgnoreCase("Forward"))) {
            if (!(choice.equals(""))) {
                System.out.println(roomOne.getRoomDesc());
            }
            choice = userChoiceStr(in);
            switch (choice) {
                case "Forward" -> {
                    System.out.println("You go further into the house");
                    String nextRoom = "Front Hallway";
                    return nextRoom;
                }
                case "Interact" -> {
                    System.out.println("What would you like to interact with?");
                    String objectInteraction = in.next();
                    roomOne.interaction(objectInteraction);
                    continue;
//                    choice = userChoiceStr(in);
                }
                //TODO: Implement
                case "Left", "Right" -> {
                    System.out.println("NYI");
                    continue;
//                    choice = userChoiceStr(in);
                }
                case "Backward" -> {    //DNT
                    System.out.println("It's raining outside, probably not a smart idea to go back outside.\n");
                    continue;
//                    choice = userChoiceStr(in);
                }
                //TODO: Inventory interactions
                 case "Inventory" -> {  //DNT
                    player.getInventory();
                    continue;
//                    choice = userChoiceStr(in);
                }
                case "Quit" -> {break;}   //DNT
                default -> {              //DNT
                    System.out.println("Not a valid choice");
                    continue;
//                    choice = userChoiceStr(in);
                }
            }
            if (choice.equalsIgnoreCase("Quit")) { //DNT
                break;
            }
        }
        return "Front Entrance";
    }
}