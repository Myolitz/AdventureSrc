import java.util.Scanner;
//import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        printMenu();
        int menuChoice = userChoice(input);
        boolean validChoice = false;

        while (!validChoice) {
            switch (menuChoice) {
                case 1 -> {
                    System.out.println("Let's get started shall we then?");
                    validChoice = true;
                    String name = charName(input);
                    PlayerData player = new PlayerData(name);

                    System.out.println("Your player name is: " + player.getName());
                }
                case 2 -> {
                    System.out.println("You are currently on version Alpha 0.1");
                    validChoice = true;
                }
                case 3 -> {
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
    }

    public static String charName (Scanner in) {
        System.out.println("Enter your characters name (Won't affect story that much).");
        return in.next();
    }
    //TODO get working
    //public static void printInventory (ArrayList<String> inventory) {
    //    for (int i = 0; i < inventory.size(); i++) {
    //        System.out.println("Slot " + i + ": " + inventory.get(i));
    //    }
    //}

}