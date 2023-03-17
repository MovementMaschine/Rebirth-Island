/**
 * This class is the main class of the "World of Zuul" application.
 * "World of Zuul" is a very simple, text based adventure game. Users
 * can walk around some scenery. That's all. It should really be extended
 * to make it more interesting!
 * 
 * To play this game, create an instance of this class and call the "play"
 * method.
 * 
 * This main class creates and initialises all the others: it creates all
 * rooms, creates the parser and starts the game. It also evaluates and
 * executes the commands that the parser returns.
 * 
 * @author Michael Kolling and David J. Barnes
 * @version 1.0 (February 2002)
 */

class Game {
    int rounds = 0;

    // ~~~ initialization ~~~
    public static void main(String args[]) {
        Game g = new Game();
        g.play();
    }

    private Parser parser;
    private Room currentRoom;
    Room Prison_Block, Hauptsitz, Helikopter_Landeplatz, Ufer, Hafen, Chemie_Labor, Fabrik, Abstelllager,
            Biowaffenlabor, Wohnviertel, Kontroll_Dach, KontrollRäume, Zelte, Sicherheitsabteilung, Prison_Dach,
            Prison_Keller, Leuchtturm, Chemie_Dach,
            Fabrik_Dach, Biowaffen_Keller, Biowaffen_Dach, Wasser;

    /**
     * Create the game and initialise its internal map.
     */
    public Game() {
        createRooms();
        parser = new Parser();
    }

    /**
     * Create all the rooms and link their exits together.
     */
    private void createRooms() {

        // create the rooms
        Prison_Block = new Room("im Haupt Gefängnisblock");
        Hauptsitz = new Room("im Hauptsitz der Gefänginsleitung");
        Helikopter_Landeplatz = new Room("auf dem Helikopter Landeplatz");
        Ufer = new Room("am Ufer des Aralsees");
        Hafen = new Room("am Hafen");
        Chemie_Labor = new Room("im Chemie Labor");
        Fabrik = new Room("in der Fabrik");
        Abstelllager = new Room("im Abstelllager");
        Biowaffenlabor = new Room("im Biowaffenlabor");
        Wohnviertel = new Room("im Wohnviertel");
        KontrollRäume = new Room("in den Kontrollräumen");
        Zelte = new Room("bei den Zelten");
        Sicherheitsabteilung = new Room("in der Sicherheitsabteilung");
        Prison_Dach = new Room("auf dem Gefängnisdach");
        Prison_Keller = new Room("im Gefängniskeller");
        Leuchtturm = new Room("im Leuchtturm");
        Chemie_Dach = new Room("auf dem Dach des Chemie Labor");
        Fabrik_Dach = new Room("auf dem Fabrikdach");
        Kontroll_Dach = new Room("auf dem Dach der Kontrollräume");
        Biowaffen_Keller = new Room("im Keller des Biowaffenlabors");
        Biowaffen_Dach = new Room("auf dem Dach des Biowaffenlabors");
        Wasser = new Room("ins Wasser gefallen");

        // initialise room exits

        Prison_Block.setExit("north", Ufer);
        Prison_Block.setExit("east", Helikopter_Landeplatz);
        Prison_Block.setExit("southeast", Hafen);
        Prison_Block.setExit("southwest", Fabrik);
        Prison_Block.setExit("west", Hauptsitz);
        Prison_Block.setExit("northwest", KontrollRäume);
        Prison_Block.setExit("up", Prison_Dach);
        Prison_Block.setExit("down", Prison_Keller);

        Hauptsitz.setExit("north", KontrollRäume);
        Hauptsitz.setExit("east", Prison_Block);
        Hauptsitz.setExit("south", Fabrik);
        Hauptsitz.setExit("west", Wohnviertel);
        Hauptsitz.setExit("northwest", Zelte);
        Hauptsitz.setExit("up", Leuchtturm);

        Helikopter_Landeplatz.setExit("north", Abstelllager);
        Helikopter_Landeplatz.setExit("south", Chemie_Labor);
        Helikopter_Landeplatz.setExit("west", Prison_Block);
        Helikopter_Landeplatz.setExit("southwest", Hafen);

        Ufer.setExit("east", Abstelllager);
        Ufer.setExit("south", Prison_Block);
        Ufer.setExit("west", KontrollRäume);
        Ufer.setExit("north", Wasser);

        Hafen.setExit("north", Prison_Block);
        Hafen.setExit("east", Chemie_Labor);
        Hafen.setExit("west", Fabrik);
        Hafen.setExit("northeast", Helikopter_Landeplatz);
        Hafen.setExit("northwest", Hauptsitz);
        Hafen.setExit("south", Wasser);

        Chemie_Labor.setExit("north", Helikopter_Landeplatz);
        Chemie_Labor.setExit("west", Hafen);
        Chemie_Labor.setExit("northeast", Biowaffenlabor);
        Chemie_Labor.setExit("northwest", Prison_Block);
        Chemie_Labor.setExit("up", Chemie_Dach);
        Chemie_Labor.setExit("south", Wasser);
        Chemie_Labor.setExit("east", Wasser);

        Chemie_Dach.setExit("down", Chemie_Labor);
        Chemie_Dach.setExit("north", Helikopter_Landeplatz);
        Chemie_Dach.setExit("west", Hafen);
        Chemie_Dach.setExit("south", Wasser);
        Chemie_Dach.setExit("east", Wasser);

        Fabrik.setExit("north", Hauptsitz);
        Fabrik.setExit("east", Hafen);
        Fabrik.setExit("west", Wohnviertel);
        Fabrik.setExit("up", Fabrik_Dach);
        Fabrik.setExit("south", Wasser);

        Fabrik_Dach.setExit("east", Hafen);
        Fabrik_Dach.setExit("down", Fabrik);
        Fabrik_Dach.setExit("south", Wasser);

        Abstelllager.setExit("east", Biowaffenlabor);
        Abstelllager.setExit("south", Helikopter_Landeplatz);
        Abstelllager.setExit("west", Ufer);
        Abstelllager.setExit("southwest", Prison_Block);
        Abstelllager.setExit("north", Wasser);

        Biowaffenlabor.setExit("south", Chemie_Labor);
        Biowaffenlabor.setExit("west", Abstelllager);
        Biowaffenlabor.setExit("up", Biowaffen_Dach);
        Biowaffenlabor.setExit("down", Biowaffen_Keller);
        Biowaffenlabor.setExit("north", Wasser);
        Biowaffenlabor.setExit("east", Wasser);

        Biowaffen_Keller.setExit("up", Biowaffenlabor);

        Biowaffen_Dach.setExit("down", Biowaffenlabor);
        Biowaffen_Dach.setExit("west", Abstelllager);
        Biowaffen_Dach.setExit("south", Chemie_Labor);
        Biowaffen_Dach.setExit("north", Wasser);
        Biowaffen_Dach.setExit("east", Wasser);

        Wohnviertel.setExit("north", Zelte);
        Wohnviertel.setExit("east", Hauptsitz);
        Wohnviertel.setExit("northwest", Sicherheitsabteilung);
        Wohnviertel.setExit("southeast", Fabrik);

        KontrollRäume.setExit("east", Ufer);
        KontrollRäume.setExit("southeast", Prison_Block);
        KontrollRäume.setExit("south", Hauptsitz);
        KontrollRäume.setExit("southwest", Zelte);
        KontrollRäume.setExit("up", Kontroll_Dach);
        KontrollRäume.setExit("north", Wasser);

        Kontroll_Dach.setExit("east", Ufer);
        Kontroll_Dach.setExit("southeast", Prison_Block);
        Kontroll_Dach.setExit("south", Hauptsitz);
        Kontroll_Dach.setExit("southwest", Zelte);
        Kontroll_Dach.setExit("down", KontrollRäume);
        Kontroll_Dach.setExit("north", Wasser);

        Zelte.setExit("northeast", KontrollRäume);
        Zelte.setExit("south", Wohnviertel);
        Zelte.setExit("west", Sicherheitsabteilung);
        Zelte.setExit("southeast", Hauptsitz);

        Sicherheitsabteilung.setExit("east", Wohnviertel);
        Sicherheitsabteilung.setExit("northeast", Zelte);
        Sicherheitsabteilung.setExit("north", Wasser);
        Sicherheitsabteilung.setExit("south", Wasser);
        Sicherheitsabteilung.setExit("west", Wasser);

        Prison_Dach.setExit("down", Prison_Block);
        Prison_Dach.setExit("north", Ufer);
        Prison_Dach.setExit("east", Helikopter_Landeplatz);
        Prison_Dach.setExit("southeast", Hafen);
        Prison_Dach.setExit("southwest", Fabrik);
        Prison_Dach.setExit("west", Hauptsitz);
        Prison_Dach.setExit("northwest", KontrollRäume);

        Prison_Keller.setExit("up", Prison_Block);
        Prison_Keller.setExit("north", Ufer);

        Leuchtturm.setExit("down", Hauptsitz);
        Leuchtturm.setExit("north", KontrollRäume);
        Leuchtturm.setExit("east", Prison_Block);
        Leuchtturm.setExit("south", Fabrik);
        Leuchtturm.setExit("west", Wohnviertel);
        Leuchtturm.setExit("nowhere", Leuchtturm);

        currentRoom = Prison_Block; // start game outside
    }

    /**
     * Main play routine. Loops until end of play.
     */
    public void play() {
        printWelcome();

        // Enter the main command loop. Here we repeatedly read commands and
        // execute them until the game is over.

        boolean finished = false;
        while (!finished) {
            Command command = parser.getCommand();
            finished = processCommand(command);
        }
        System.out.println();
        System.out.println();
        System.out.println("Listenaktualisierung...");
    }

    /**
     * Print out the opening message for the player.
     */
    private void printWelcome() {
        System.out.println();
        System.out.println("Willkommen auf Rebirth Island!");
        System.out.println("Überlebe um zu gewinnen.");
        System.out.println();
        System.out.println("Du befindest dich " + currentRoom.getDescription());
        System.out.print("Exits: ");
        if (currentRoom.getExit("north") != null)
            System.out.print("north ");
        if (currentRoom.getExit("east") != null)
            System.out.print("east ");
        if (currentRoom.getExit("south") != null)
            System.out.print("south ");
        if (currentRoom.getExit("west") != null)
            System.out.print("west ");
        if (currentRoom.getExit("northeast") != null)
            System.out.print("northeast ");
        if (currentRoom.getExit("southeast") != null)
            System.out.print("southeast ");
        if (currentRoom.getExit("southwest") != null)
            System.out.print("southwest ");
        if (currentRoom.getExit("northwest") != null)
            System.out.print("northwest ");
        if (currentRoom.getExit("up") != null)
            System.out.print("up ");
        if (currentRoom.getExit("down") != null)
            System.out.print("down ");
        System.out.println();
    }

    /**
     * Given a command, process (that is: execute) the command.
     * If this command ends the game, true is returned, otherwise false is
     * returned.
     */
    private boolean processCommand(Command command) {
        boolean wantToQuit = false;

        if (command.isUnknown()) {
            System.out.println("???");
            return false;
        }

        String commandWord = command.getCommandWord();
        if (commandWord.equals("help"))
            printHelp();
        else if (commandWord.equals("go"))
            goRoom(command);
        else if (commandWord.equals("look"))
            look();    
        else if (commandWord.equals("quit"))
            wantToQuit = quit(command);

        if (currentRoom == Wasser) {
            System.out.print("Du bist ertrunken!");
            return true;
        }

        else if (rounds == 10)

        {
            System.out.println();
            System.out.println();
            System.out.print("Du hast 10 Runden überlebt und wirst nun extrahiert!");
            return true;
        }

        return wantToQuit;
    }

    private void look()
    {
    
    }

    public int giveRounds() {
        return rounds;
    }

    // implementations of user commands:

    /**
     * Print out some help information.
     * Here we print some stupid, cryptic message and a list of the
     * command words.
     */
    private void printHelp() {
        System.out.println();
        System.out.print("Du hast bereits ");
        System.out.print(giveRounds());
        System.out.print(" Runden übelebt");
        System.out.println();
        System.out.println("Your command words are:");
        System.out.println("   go quit help");
    }

    /**
     * Try to go to one direction. If there is an exit, enter
     * the new room, otherwise print an error message.
     */
    private void goRoom(Command command) {
        if (!command.hasSecondWord()) {
            // if there is no second word, we don't know where to go...
            System.out.println("Go where?");
            return;
        }

        String direction = command.getSecondWord();

        // Try to leave current room.

        Room nextRoom = currentRoom.getExit(direction);

        if (nextRoom == null)
            System.out.println("There is no door!");
        else {
            currentRoom = nextRoom;
            System.out.println();
            System.out.println("Du bist " + currentRoom.getDescription());
            System.out.print("Exits: ");
            if (currentRoom.getExit("north") != null)
                System.out.print("north ");
            rounds++;
            if (currentRoom.getExit("east") != null)
                System.out.print("east ");
            if (currentRoom.getExit("south") != null)
                System.out.print("south ");
            if (currentRoom.getExit("west") != null)
                System.out.print("west ");
            if (currentRoom.getExit("northeast") != null)
                System.out.print("northeast ");
            if (currentRoom.getExit("southeast") != null)
                System.out.print("southeast ");
            if (currentRoom.getExit("southwest") != null)
                System.out.print("southwest ");
            if (currentRoom.getExit("northwest") != null)
                System.out.print("northwest ");
            if (currentRoom.getExit("up") != null)
                System.out.print("up ");
            if (currentRoom.getExit("down") != null)
                System.out.print("down ");
            if (currentRoom.getExit("nowhere") != null)
                System.out.print("nowhere ");    
            System.out.println();
        }
    }

    /**
     * "Quit" was entered. Check the rest of the command to see
     * whether we really quit the game. Return true, if this command
     * quits the game, false otherwise.
     */
    private boolean quit(Command command) {
        if (command.hasSecondWord()) {
            System.out.println("Quit what?");
            return false;
        } else
            return true; // signal that we want to quit
    }
   

    /**
     * private void printLocationInfo() {
     * System.out.println("You are " + currentRoom.getDescription());
     * System.out.print("Exits: ");
     * if (currentRoom.getExit("north") != null) {
     * System.out.print("north ");
     * }
     * if (currentRoom.getExit("east") != null) {
     * System.out.print("east ");
     * }
     * if (currentRoom.getExit("south") != null) {
     * System.out.print("south ");
     * }
     * if (currentRoom.getExit("west") != null) {
     * System.out.print("west ");
     * }
     * System.out.println();
     * }
     */
}
