package main.MovementSets;

import java.util.HashSet;

public class Dictionaries {

    //Was gonna do an interface for this, still might though TBD
    //TODO maybe extend(?) a general interface for this that handles actual movement while this only checks dictionaries
    public HashSet<String> forward = new HashSet<String>();
    public HashSet<String> left = new HashSet<String>();
    public HashSet<String> backward = new HashSet<String>();
    public HashSet<String> right = new HashSet<String>();
    public HashSet<String> inventory = new HashSet<String>();

    //TODO: Ask around for possible things and maybe add an external "wiki" of sorts
    //for people to go to if they need the full available dictionary for controls

    public Dictionaries() {
        setForwardDict();
        setBackwardDict();
        setLeftDict();
        setRightDict();
        setInventoryDict();
    }

    public void setLeftDict() {
        left.add("left");
        left.add("Left");
    }

    public void setRightDict() {
        right.add("Right");
        right.add("right");
    }

    public void setBackwardDict() {
        backward.add("back");
        backward.add("Back");
        backward.add("Backward");
        backward.add("backward");
        //If game ever gets to point where there's multiple floors, remove these
        backward.add("Down");
        backward.add("down");
        backward.add("b");
        backward.add("B");
    }

    public void setForwardDict() {
        forward.add("go");
        forward.add("Go");
        forward.add("Forward");
        forward.add("forward");
        //See comment in setBackwardDict()
        forward.add("Up");
        forward.add("up");
        forward.add("F");
        forward.add("f");
    }

    public void setInventoryDict() {
        inventory.add("inventory");
        inventory.add("Inventory");
        inventory.add("pockets");
        inventory.add("Pockets");
        inventory.add("items");
        inventory.add("Items");
    }
}
