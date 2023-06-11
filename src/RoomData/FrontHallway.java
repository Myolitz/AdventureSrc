package RoomData;

public class FrontHallway {
    private String name;
    private String description;
    private boolean typeWriterInteraction;
    private boolean drawerInteraction;
    private boolean paintingObservation;

    public FrontHallway() {
        this.name = "Front Hallway";
        this.description = """
                                Going further into the house you are now in a quaint little hallway, 
                adorned with some odd paintings consisting of just single colored squares inside the frames.
                On the opposite wall you see a nice little desk with two drawers and a typewriter on top of it.
                                    How old must this place be if typewriters are still in use?""";
    }
}
