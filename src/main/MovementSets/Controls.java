package main.MovementSets;

import java.util.HashSet;

public interface Controls {

    public class test extends Dictionaries {
    }

    //TODO: figure out if this is necessary at all

    String goForward(HashSet<String> foo);

    String goLeft(HashSet<String> foo);

    String goRight(HashSet<String> foo);

    String goBack(HashSet<String> foo);

    static boolean checkDict(HashSet<String> foo, String bar) {
        return foo.contains(bar);
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
}
