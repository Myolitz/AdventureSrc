/*
 * Lazy Terminology Key:
 * DNT = DO NOT TOUCH (aka its working as intended)
 * NYI = Not yet implemented
 * IT = In Testing
 * PI = Partially Implemented
 * MI = Mostly Implemented (and working as intended)
 * -------------------------------------
 * Ideas for future:
 * player orientation affecting left/right prompts
 * switching to interfaces for movement or something similar
 * switching the "straight" paths (think front entrance - back entrance) to linked lists
 * -------------------------------------
 * TODO:
 * Clean up main.java (ik its using generally "basic" things but jfc) <- also part of why I want to switch to not using switches for everything lol
 * Find a way to format text automatically without having to do a bunch of String.format shit (literally just look at any formatDescription() method within the room classes
 * Implement a dictionary of sort using a HashSet, NOT HashMap (WIP)
 * Continuation of Main clean-up -> Move the gameplay to its own "game" class or something similar
 * Once you implement the hashsets, fix the logic for deciding player movement.
 * */


import main.PlayerData.*;
import main.RoomData.*;
import java.util.Scanner;

public class Main {
    public static String whitespace = "";

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
                        System.out.println("You are currently on version Alpha 0.6.2 - Text Formatting Galore");
                        validChoice = true;
                        userStr = userChoiceStr(input);
                    }
                    case 3 -> {
                        printControls();
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
        //noinspection SpellCheckingInspection <- what the fuck was this for?????
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

    public static void printControls() { //TODO: Update as you implement things
        System.out.println(
                """
                        The basic controls include the following:
                        - Forward
                        - Backward
                        - Inventory
                        - Interact
                        - Back, to back out of menus like this one <- currently limited to main menu
                        - Location
                        - Quit - Lets you exit the game at any point (MI)
                        - Left/Right (Commands DNT, locations NYI)
                        - Use
                        - Controls - Brings up this menu (MI)
                        * They are case-sensitive until I switch to hashmaps, so make sure to capitalize (for now)"""
        );
    }

    //Might delete these 2 as I don't seem to call upon them like at all lmao (rewrite the input code for shit if swapping to this long-term)
    //FIXME crash when inputting things like "1F" during the menu n shit
    public static int userChoice(Scanner in) {
        return in.nextInt();
    }
    //Yes I have to have 2 cause one returns number and the other String - DNT either
    public static String userChoiceStr(Scanner in) {
        return in.next();
    }

    //Unsure if any dialogue will even happen, so method might get yeeted - DNT (for now)
    public static String charName (Scanner in) {
        System.out.println("Enter your characters name: (It has no effect on the story so far)");
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
        BackHallway roomFour = new BackHallway();
        BackEntrance roomFive = new BackEntrance();

        //For reference, this is map layout if you're looking over this code
        /* n = null, r = room, i = implemented
         * n n i r n
         * r n i n n
         * r r i n n
         * r n i n r
         * n r i r r
         */
        //TODO: maybe move actions to the room classes rather than Main.java to de-clog
        //Background, maybe replace with object elsewhere (Game class? maybe for stuff like game.printMenu() n stuff)
        //Moved outside the while loop to not have it print every time I tried quitting mid-game
//        System.out.println("""
//                                   You had no idea where you were going after a night of drinking.
//                                           Was it even your house that you stumbled upon?
//                       A feeling of uneasiness washes over, but with it raining you disregard the possible concerns.
//                       The door opens without so much as a speck of resistance and throw yourself into the house.
//                """);

        //Ended up being easier to just do this mess with printf, though w/e, Imma leave it as a reminder to not to text boxes
        String desc1 = "You had no idea where you were going after a night of drinking";
        String desc2 = "Was it even your house that you stumbled upon?";
        String desc3 = "A feeling of uneasiness washes over, but with it raining you disregard any possible concerns";
        String desc4 = "The door opens without so much as a spec of resistance as you throw yourself into the house";

        System.out.printf("""
                %1$26s|%2$s|
                %1$35s|%3$s|
                %1$12s|%4$s|
                %1$12s|%5$s|
                
                """, whitespace, desc1, desc2, desc3, desc4);

        //REMEMBER TO FUCKING UPDATE THE VARIABLE, YOU YES YOU FUTURE MYO
        while (gameRunning) {
            //The logic in this entire switch statement might bite me in the ass later
            //All commented gameRunning  = false statements are to test, remove later
            switch (playerLocation) { //DNT UNLESS ADDING ROOMS, INTER-ROOM TRANSITIONS WORK AS INTENDED
                case "Front Entrance" -> {
                    playerLocation = frontEntrance(in, roomOne, player); //IT indefinitely, check for bugs along the way or ask friends to QA for you lol
                    //System.out.println("\nTest Room 1 Complete\n");
                    //gameRunning = false;
                    }
                case "Front Hallway" -> {    //TODO: Finish Easter Egg once branching rooms are done
                    playerLocation = frontHallway(in, roomTwo, player);
                    //System.out.println("Test Room 2 Complete");
                    //gameRunning = false;
                }
                case "Center Hallway" -> { //TODO: add "left" functionality later @see map.blend or ASCII above
                    playerLocation = centerHallway(in, roomThree, player);
                    //System.out.println("Test Room 3 Complete");
                    //gameRunning = false;
                }
                case "Back Hallway" -> {
                    playerLocation = backHallway(in, roomFour, player);
                    //System.out.println("Test Room 4 Complete");
                    //gameRunning = false;
                }
                case "Back Entrance" -> {
                    playerLocation = backEntrance(in, roomFive, player);
//                    System.out.println("Test Room 5 Complete");
                }
                case "Game Complete" -> {
                    System.out.printf("%1$24s|Thanks for playing, hopefully you'll come back when its updated|\n", whitespace);
                    gameRunning = false;
                }
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

    //This is Room 1 - The Entrance
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
                    System.out.printf("%1$36s|You go further into the house|\n", whitespace);
                    return "Front Hallway";
                }
                case "Interact" -> {
                    System.out.println("What would you like to interact with?");
                    String objectInteraction = in.next();
                    roomOne.interaction(objectInteraction, player);
                }
                case "Right" -> {
                    System.out.printf("%1$20s|A seemingly endless hallway is now before you, you dare not go into it.|\n", whitespace);
                }
                case "Left" -> {
                    System.out.printf("%1$34s|Quite the lavish office space...|\n",whitespace);
                    System.out.printf("%1$17s|Simply entering the room makes your head hurt with how shiny everything is|\n",whitespace);
                    System.out.printf("%1$36s|Better to not touch anything|\n",whitespace);
                }
                case "Backward" -> {    //DNT
                    System.out.printf("%1$36s|It's still raining outside...|\n", whitespace);
                }
                //TODO: Re-check in case "global" item use is implemented later
                 case "Inventory" -> {  //DNT
                    player.getInventory();
                }
                case "Use" -> {
                    System.out.println("What item would you like to use?");
                    String object = in.next();
                    player.useItemGlobal(object);
                }
                case "Controls" -> {
                    printControls();
                    System.out.println();
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


    //This is room 2 - The Front Hallway
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
                case "Forward" -> { //DNT
                    System.out.printf("%1$36s|You go into the heart of the house|\n", whitespace);
                    return "Center Hallway";
                }
                case "Interact" -> { //DNT
                    System.out.println("What would you like to interact with?");
                    String objectInteraction = in.next();
                    roomTwo.interaction(objectInteraction, player, in);
                }
                case "Right" -> { //DNT
                    System.out.printf("%1$28s|The desk with the typewriter blocks your path.|\n", whitespace);
                    System.out.printf("%1$31s|Though a wall lies behind it so...|\n", whitespace);
                }
                case "Left" -> { //DNT
                    System.out.printf("%1$18s|Seeing as the paintings are hung on the wall, walking towards them would yield|\n", whitespace);
                    System.out.printf("%137$s|nothing but a face-full of wall|\n", whitespace);
                }
                case "Backward" -> {    //DNT
                    System.out.printf("%1$31s|Maybe you missed something in the entrance|\n", whitespace);
                    return "Front Entrance";
                }
                //TODO: Inventory interactions (keeping this here now that room 1 is done JUST as a reminder in case I add shit that would require "global" item use
                case "Inventory" -> {  //DNT
                    player.getInventory();
                }
                case "Use" -> { //IT
                    System.out.println("What item would you like to use?");
                    String object = in.next();
                    player.useItemGlobal(object);
                }
                case "Controls" -> { //DNT
                    printControls();
                    System.out.println();
                }
                case "Quit" -> {       //DNT
                    return "Quit";
                }
                case "Location" -> { //DNT
                    System.out.println(player.getLocation());
                }
                default -> {              //DNT
                    System.out.println("Not a valid choice");
                }
            }
        }
        return  "Front Hallway";
    }

    //This is room 3 - The Center Hallway
    public static String centerHallway(Scanner in, CenterHallway roomThree, PlayerData player) {

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
                    System.out.printf("%1$43s|You go towards the back of the house|\n", whitespace);
                    return "Back Hallway";
                }
                case "Interact" -> {
                    System.out.println("What would you like to interact with?");
                    String objectInteraction = in.next();
                    roomThree.interaction(objectInteraction, player);
                }
                case "Right" -> {
                    System.out.printf("%1$42s|There sits a couch blocking your path.|\n", whitespace);
                    System.out.printf("%1$38s|Though considering there's a wall behind it...|\n", whitespace);
                }
                case "Left" -> {
                    System.out.printf("%1$s|There is a path branching to though best to not dilly-dally.|\n", whitespace);
                }
                case "Backward" -> {    //DNT
                    System.out.printf("%1$s|Might've missed something back there... |\n", whitespace);
                    return "Front Hallway";
                }
                //TODO: Inventory interactions (keeping this here now that room 1 is done JUST as a reminder in case I add shit that would require "global" item use
                case "Inventory" -> {  //DNT
                    player.getInventory();
                }
                case "Use" -> {
                    System.out.println("What item would you like to use?");
                    String object = in.next();
                    player.useItemGlobal(object);
                }
                case "Controls" -> { //DNT
                    printControls();
                    System.out.println();
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

    //This is room 4 - The Back Hallway
    public static String backHallway(Scanner in, BackHallway roomFour, PlayerData player) {

        //YOLO-ing the last couple of rooms probably cause I've been kinda lazy on actually dev-ing lol
        //Probably going to be releasing them a lot faster since its just copy-pasting

        System.out.println(roomFour.getRoomDesc());
        player.setLocation(roomFour.getRoomName());

        String choice = "";

        while (!(choice.equalsIgnoreCase("Forward"))) {
            if (!(choice.equals(""))) {
                System.out.println(roomFour.getRoomDesc());
            }
            choice = userChoiceStr(in);
            switch (choice) {
                case "Forward" -> {
                    System.out.printf("%1$45s|You see a door up ahead|\n", whitespace);
                    return "Back Entrance";
                }
                case "Interact" -> {
                    System.out.println("What would you like to interact with?");
                    String objectInteraction = in.next();
                    roomFour.interaction(objectInteraction, player);
                }
                case "Right" -> {
                    System.out.printf("%1$40s|There is a wall blocking your path |\n", whitespace);
                }
                case "Left" -> {
                    System.out.printf("%1$40s|The mirror and desk block your path|\n", whitespace);
                    System.out.printf("%1$30s|Though they prevent you from getting a face-full of wall.|\n", whitespace);
                }
                case "Backward" -> {    //DNT
                    System.out.printf("%1$35s|Man that couch was comfortable, let's head back|\n", whitespace);
                    return "Center Hallway";
                }
                //TODO: Inventory interactions (keeping this here now that room 1 is done JUST as a reminder in case I add shit that would require "global" item use
                case "Inventory" -> {  //DNT
                    player.getInventory();
                }
                case "Use" -> {
                    System.out.println("What item would you like to use?");
                    String object = in.next();
                    player.useItemGlobal(object);
                }
                case "Controls" -> { //DNT
                    printControls();
                    System.out.println();
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
        return "Back Hallway";
    }

    //This is Room 5 - The Back Entrance
    public static String backEntrance(Scanner in, BackEntrance roomFive, PlayerData player) {

        System.out.println(roomFive.getRoomDesc());
        player.setLocation(roomFive.getRoomName());

        String choice = "";

        while (!(choice.equalsIgnoreCase("Forward"))) {
            if (!(choice.equals(""))) {
                System.out.println(roomFive.getRoomDesc());
            }
            choice = userChoiceStr(in);
            switch (choice) {
                case "Forward" -> {
                    if (roomFive.isDoorUnlocked) {
                        System.out.printf("%1$34s||The door is unlocked and you run out into the rain\n", whitespace);
                        return "Game Complete";
                    }
                    else {
                        System.out.printf("%1$30s|You bump into the door, it's cool from the pouring rain|\n", whitespace);
                    }

                }
                case "Interact" -> {
                    System.out.println("What would you like to interact with?");
                    String objectInteraction = in.next();
                    roomFive.interaction(in ,objectInteraction, player);
                }
                case "Right" -> {
                    System.out.printf("%1$40s|There is a hallway...it exists (NYI)|\n", whitespace);
                }
                case "Left" -> {
                    System.out.printf("%1$36s|There is an umbrella holder in the corner|\n", whitespace);
                    System.out.printf("%1$30s|Guess they rather you come in through the back than the front|\n", whitespace);

                }
                case "Backward" -> {    //DNT
                    System.out.printf("%1$35s|Maybe there was something left unchecked...|\n", whitespace);
                    return "Back Hallway";
                }
                //TODO: Inventory interactions (keeping this here now that room 1 is done JUST as a reminder in case I add shit that would require "global" item use
                case "Inventory" -> {  //DNT
                    player.getInventory();
                }
                case "Use" -> {
                    System.out.println("What item would you like to use?");
                    String object = in.next();
                    player.useItemGlobal(object);
                }
                case "Controls" -> { //DNT
                    printControls();
                    System.out.println();
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
        return "Back Entrance";
    }

}
