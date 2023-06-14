/**
 * Lazy Terminology Key:
 * DNT = DO NOT TOUCH (aka its working as intended)
 * NYI = Not yet implemented
 * IT = In Testing
 * PI = Partially Implemented
 * -------------------------------------
 * Ideas for future:
 * player orientation affecting left/right prompts
 * switching to interfaces for movement or something similar
 * switching the "straight" paths (think front entrance - back entrance) to linked lists
 * -------------------------------------
 * TODO:
 * Clean up main.java (ik its using generally "basic" things but jfc) <- also part of why I want to switch to not using switches for everything lol
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
        boolean gameEnd = false;
        String userStr = "";

        //Keeps you in the menu as long as you haven't selected the "play" or "quit" options
        //Added the gameEnd thing cause having '4' be your choice wouldn't exit the program *IT*
        while (!gameStart && !gameEnd) {
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
                        System.out.println("You are currently on version Alpha 0.4 - Central Hallway Finished");
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
                                        - You can type 'Quit' at any given type to exit the game once you start (PI)
                                        - Left/Right (PI @ room1 & room2)
                                        - Use (PI)"""
                        );
                        validChoice = true;
                        userStr = userChoiceStr(input);
                    }
                    case 4 -> { //Quit
                        validChoice = true;
                        gameEnd = true;
                        System.out.println("Hope you'll come play again!");
                        userStr = "Quit";
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

    //Might delete these 2 as I don't seem to call upon them like at all lmao (rewrite the input code for shit if swapping to this long-term)
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

        //Going to initialize all room objects, so they aren't created every time in the while loop
        //Probably prevented some bugs with interactions
        Entrance roomOne = new Entrance();
        FrontHallway roomTwo = new FrontHallway();
        CenterHallway roomThree = new CenterHallway();

        //For reference, this is map layout if you're looking over this code
        /* n = null, r = room, i = implemented
         * n n r r n
         * r n r n n
         * r r r n n
         * r n i n r
         * n r i r r
         */
        //TODO maybe move actions to the room classes rather than Main.java to de-clog
        //Background, maybe replace with object elsewhere (Game class? maybe for stuff like game.printMenu() n stuff)
        //Moved outside the while loop to not have it print every time I tried quitting mid-game
        System.out.println("""
                            You had no idea where you were going after a night of drinking.
                                    Was it even your house that you stumbled upon?
                A feeling of uneasiness washes over, but with it raining you disregard the possible concerns.
                   The door opens without so much as a speck of resistance and throw yourself into the house.
                """);

        //REMEMBER TO FUCKING UPDATE THE VARIABLE, YOU YES YOU FUTURE MYO
        while (gameRunning) {
            //The logic in this entire switch statement might bite me in the ass later
            //All commented gameRunning  = false statements are to test, remove later
            switch (playerLocation) { //DNT UNLESS ADDING ROOMS, INTER-ROOM TRANSITIONS WORK AS INTENDED
                case "Front Entrance" -> {
                    playerLocation = frontEntrance(in, roomOne, player); //IT indefinitely, check for bugs along the way or ask friends to QA for you lol
                    //System.out.println("\nTest Room 1 Complete\n"); //Room 1 Complete so commented
                    //gameRunning = false;
                    }
                case "Front Hallway" -> {    //TODO: finish puzzle implementation once rest of rooms are done
                    playerLocation = frontHallway(in, roomTwo, player);
                    //System.out.println("Test Room 2 Complete");
                    //gameRunning = false;
                }
                case "Center Hallway" -> { //TODO: add "left" functionality later @see map.blend or ASCII above
                    playerLocation = centerHallway(in, roomThree, player);
                    System.out.println("Test Room 3");
                    gameRunning = false;
                }
                case "Room 4" -> {System.out.println("Test Room 4");} //TODO: implement
                case "Room 5" -> {System.out.println("Test Room 5");} //TODO: implement
                case "Quit" -> {
                    System.out.println("Sorry to see you go, hope you can play again :)");
                    gameRunning = false;
                } //TODO implement "Quit" checks at any time so that its a universally accepted command
                default -> {
                    System.out.println("How did you even get this?");
                    throw new ArrayIndexOutOfBoundsException(); //TODO: make this make sense by switching userpos to 2D array. or whatever francisco has recommended
                    //DO NOT DO THE 2D ARRAY BULLSHIT PLEASE FOR THE LOVE OF GOD DO A LINKED LIST OR SMTH
                }
            }
        }
    }

    public static String frontEntrance(Scanner in, Entrance roomOne, PlayerData player) {
        //Removed giant fucking comment block, object worked as intended

        //Nested while loops lets fucking GOOOOOOOOOOOOOOOO (forgot what this was even bout lol, keeping comment cause why not)
        String choice = "";
        System.out.println(roomOne.getRoomDesc());
        player.setLocation(roomOne.getRoomName());

        //This entire thing is probably eternally IT, uh check necessity of the commented code later
        while (!(choice.equalsIgnoreCase("Forward"))) {
            if (!(choice.equals(""))) {
                System.out.println(roomOne.getRoomDesc());
            }
            choice = userChoiceStr(in);
            switch (choice) {
                case "Forward" -> {
                    System.out.println("""
                                                           You go further into the house
                            """);
                    return "Front Hallway";
                }
                case "Interact" -> {
                    System.out.println("What would you like to interact with?");
                    String objectInteraction = in.next();
                    roomOne.interaction(objectInteraction, player);
                }
                case "Right" -> {
                    System.out.println("""
                                        A seemingly endless hallway is now before you, you dare not go into it.
                                                    (Yes its NYI, but dw come back later ;))
                            """);
                }
                case "Left" -> {
                    System.out.println("""
                                                 You enter a lavishly designed office space.
                                    Simply entering the room makes your head hurt due to how shiny everything is.
                                          Better not touch anything and try to get out of this house
                            """);
                }
                case "Backward" -> {    //DNT
                    System.out.println("It's raining outside, probably not a smart idea to go back outside.\n");
                }
                //TODO: Re-check in case "global" item use is implemented later
                 case "Inventory" -> {  //DNT
                    player.getInventory();
                }
                case "Quit" -> {       //DNT
                    return "Quit";
                }
                case "Location" -> {
                    System.out.println(player.getLocation());
                }
                default -> {              //DNT
                    System.out.println("Not a valid choice");
                }
            }
        }
        return "Front Entrance";
    }

    public static String frontHallway(Scanner in, FrontHallway roomTwo, PlayerData player) {

//        System.out.println("Room Two is now accessible and ready to build");
//        System.out.println("Begin test session");

        System.out.println(roomTwo.getRoomDesc());
        player.setLocation(roomTwo.getRoomName());

        String choice = "";

        while (!(choice.equalsIgnoreCase("Forward"))) {
            if (!(choice.equals(""))) {
                System.out.println(roomTwo.getRoomDesc());
            }
            choice = userChoiceStr(in);
            switch (choice) {
                case "Forward" -> {
                    System.out.println("""
                                                           You go further into the house
                            """);
                    return "Center Hallway";
                }
                case "Interact" -> {
                    System.out.println("What would you like to interact with?");
                    String objectInteraction = in.next();
                    roomTwo.interaction(objectInteraction, player, in);
                }
                case "Right" -> {
                    System.out.println("""
                                             The desk with the typewriter blocks your path.
                               Though considering there's a wall behind them, not sure what the plan was.
                            """);

                }
                case "Left" -> {
                    System.out.println("""
                            Seeing as the paintings are hung on the wall, walking towards the wall would yield
                                                    nothing but a face-full of wall
                            """);

                }
                case "Backward" -> {    //DNT
                    System.out.println("Maybe you missed something in the entrance, lets go back and check.\n");
                    return "Front Entrance";
                }
                //TODO: Inventory interactions (keeping this here now that room 1 is done JUST as a reminder in case I add shit that would require "global" item use
                case "Inventory" -> {  //DNT
                    player.getInventory();
                }
                case "Quit" -> {       //DNT
                    return "Quit";
                }
                case "Location" -> {
                    System.out.println(player.getLocation());
                }
                default -> {              //DNT
                    System.out.println("Not a valid choice");
                }
            }
        }
        return  "Front Hallway";
    }

    private static String centerHallway(Scanner in, CenterHallway roomThree, PlayerData player) {

        //YOLO-ing the last couple of rooms probably cause I've been kinda lazy on actually dev-ing lol
        //Probably going to be releasing them a lot faster since its just copy-pasting

        System.out.println(roomThree.getRoomDesc());
        player.setLocation(roomThree.getRoomName());

        String choice = "";

        while (!(choice.equalsIgnoreCase("Forward"))) {
            if (!(choice.equals(""))) {
                System.out.println(roomThree.getRoomDesc());
            }
            choice = userChoiceStr(in);
            switch (choice) {
                case "Forward" -> {
                    System.out.println("You go towards the back of the house.");
                    return "Back Hallway";
                }
                case "Interact" -> {
                    System.out.println("What would you like to interact with?");
                    String objectInteraction = in.next();
                    roomThree.interaction(objectInteraction, player);
                }
                case "Right" -> {
                    System.out.println("""
                                             There sits a couch blocking your path.
                               Though considering there's a wall behind it, not sure what the plan was.
                            """);

                }
                case "Left" -> {
                    System.out.println("""
                                    There is a path branching to the left though best to not dilly-dally.
                            """);

                }
                case "Backward" -> {    //DNT
                    System.out.println("                Might've missed something back there...\n");
                    return "Front Hallway";
                }
                //TODO: Inventory interactions (keeping this here now that room 1 is done JUST as a reminder in case I add shit that would require "global" item use
                case "Inventory" -> {  //DNT
                    player.getInventory();
                }
                case "Quit" -> {       //DNT
                    return "Quit";
                }
                case "Location" -> {
                    System.out.println(player.getLocation());
                }
                default -> {              //DNT
                    System.out.println("Not a valid choice");
                }
            }
        }
        return "Center Hallway";
    }

}