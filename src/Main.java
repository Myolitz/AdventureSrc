import PlayerData.PlayerData;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        //Program Initialization
        printMenu();
        int menuChoice = userChoice(input);
        boolean validChoice = false;

        //Ensures one of 3 choices, if not asks again
        while (!validChoice) {
            switch (menuChoice) {
                case 1 -> { //The start of the game, create separate class later for levels
                    System.out.println("Let's get started shall we then?");
                    validChoice = true;
                    String name = charName(input);
                    PlayerData player = new PlayerData(name);

                    //Test Lines below
                    System.out.println("Your player name is: " + player.getName());
                    player.getInventory();
                }
                case 2 -> { //Gives version, manually change this
                    System.out.println("You are currently on version Alpha 0.1.1a");
                    validChoice = true;
                }
                case 3 -> { //Quit
                    System.out.println("Hope you'll come play again!");
                    validChoice = true;
                }
                default -> {
                    System.out.println("Oops, that's not an option!");
                    printMenu();
                    menuChoice = userChoice(input);
                }
            }
        }



        input.close();
    }
    //Prints out menu using perfect size array
    public static void printMenu() {
        String[] menuOptions = new String[] {"1. Play", "2. Version", "3. Quit"};
        System.out.println("    Welcome to\n    " +
                           "   The Adventure    ");
        for (String menuOption : menuOptions) {
            System.out.println(menuOption);
        }
    }

    public static int userChoice(Scanner in) {
        return in.nextInt();
    } //@see method name

    public static String charName (Scanner in) { //Unsure if any dialogue will even happen, so method might get yeeted
        System.out.println("Enter your characters name (Won't affect story that much).");
        return in.next();
    }

}