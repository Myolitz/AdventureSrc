import java.util.ArrayList;
import java.util.Scanner;
import java.util.ArrayList;

/*
 */

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        printMenu();
        int menuChoice = userChoice(input);

        switch (menuChoice) {
            case 1:
                System.out.println("Let's get started shall we then?");
                break;
            case 2:
                System.out.println("You are currently on version Alpha 0.1");
                break;
            case 3:
                System.out.println("Hope you'll come play again!");
                break;
            default:
                System.out.println("Oops, that's not an option!");
                printMenu();
                menuChoice = userChoice(input);
        }


        input.close();
    }

    public static void printMenu() {

        String[] menuOptions = new String[] {"1. Play", "2. Version", "3. Quit"};
        System.out.println("    Welcome to\n    " +
                           "   The Adventure    ");

        for (int i = 0; i < menuOptions.length; i++){
            System.out.println(menuOptions[i]);
        }

    }

    public static int userChoice(Scanner in) {
        int choice = in.nextInt();
        return choice;
    }
}