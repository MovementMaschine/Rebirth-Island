/**
 *  This class is the main class of the "World of Zuul" application. 
 *  "World of Zuul" is a very simple, text based adventure game.  Users 
 *  can walk around some scenery. That's all. It should really be extended 
 *  to make it more interesting!
 * 
 *  To play this game, create an instance of this class and call the "play"
 *  method.
 * 
 *  This main class creates and initialises all the others: it creates all
 *  rooms, creates the parser and starts the game.  It also evaluates and
 *  executes the commands that the parser returns.
 * 
 * @author  Michael Kolling and David J. Barnes
 * @version 1.0 (February 2002)
 */

class Game 
{
    int rounds = 0;

    // ~~~ initialization ~~~
    public static void main(String args[]) {
        Game g = new Game();
        g.play();
    }

    private Parser parser;
    private Room currentRoom;
        
    /**
     * Create the game and initialise its internal map.
     */
    public Game() 
    {
        createRooms();
        parser = new Parser();
    }

    /**
     * Create all the rooms and link their exits together.
     */
    private void createRooms()
    {
        Room Prison_Block, Hauptsitz, Helikopter_Landeplatz, Ufer, Hafen, Chemie_Labor, Fabrik, Abstelllager, Biowaffenlabor, Wohnviertel, KontrollRäume, Zelte, Sicherheitsabteilung;
      
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
        
        // initialise room exits
        Prison_Block.setExits(Ufer, Helikopter_Landeplatz, Hafen, Hauptsitz);
        Hauptsitz.setExits(KontrollRäume, Prison_Block, Fabrik, Wohnviertel);
        Helikopter_Landeplatz.setExits(Abstelllager, null, Chemie_Labor, Prison_Block);
        Ufer.setExits(null, Abstelllager, Prison_Block, KontrollRäume);
        Hafen.setExits(Prison_Block, Chemie_Labor, null, Fabrik);
        Chemie_Labor.setExits(Helikopter_Landeplatz, null, null, Hafen);
        Fabrik.setExits(Hauptsitz, Hafen, null, Wohnviertel);
        Abstelllager.setExits(null, Biowaffenlabor, Helikopter_Landeplatz, Prison_Block);
        Biowaffenlabor.setExits(null, null, Chemie_Labor, Abstelllager);
        Wohnviertel.setExits(Zelte, Hauptsitz, null, Sicherheitsabteilung);
        KontrollRäume.setExits(null, Ufer, Hauptsitz, Zelte);
        Zelte.setExits(null, KontrollRäume, Wohnviertel, Sicherheitsabteilung);
        Sicherheitsabteilung.setExits(null, Zelte, null, null);
        

        currentRoom = Prison_Block;  // start game outside
    }

    /**
     *  Main play routine.  Loops until end of play.
     */
    public void play() 
    {            
        printWelcome();

        // Enter the main command loop.  Here we repeatedly read commands and
        // execute them until the game is over.
                
        boolean finished = false;
        while (! finished) {
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
    private void printWelcome()
    {
        System.out.println();
        System.out.println("Willkommen auf Rebirth Island!");
        System.out.println("Überlebe um zu gewinnen.");
        System.out.println();
        System.out.println("Du befindest dich " + currentRoom.getDescription());
        System.out.print("Exits: ");
        if(currentRoom.northExit != null)
            System.out.print("north ");
        if(currentRoom.eastExit != null)
            System.out.print("east ");
        if(currentRoom.southExit != null)
            System.out.print("south ");
        if(currentRoom.westExit != null)
            System.out.print("west ");
        System.out.println();
    }

    /**
     * Given a command, process (that is: execute) the command.
     * If this command ends the game, true is returned, otherwise false is
     * returned.
     */
    private boolean processCommand(Command command) 
    {
        boolean wantToQuit = false;

        if(command.isUnknown()) {
            System.out.println("I don't know what you mean...");
            return false;
        }

        String commandWord = command.getCommandWord();
        if (commandWord.equals("help"))
            printHelp();
        else if (commandWord.equals("go"))
            goRoom(command);
        else if (commandWord.equals("quit"))
            wantToQuit = quit(command);
        if (rounds == 10)
        
        {
        System.out.println();    
        System.out.println();
        System.out.print("Du hast überlebt und wirst nun extrahiert!");
        return true;
        }

        return wantToQuit;
    }

   

    public int giveRounds()
    {
        return rounds;
    }

    // implementations of user commands:

    /**
     * Print out some help information.
     * Here we print some stupid, cryptic message and a list of the 
     * command words.
     */
    private void printHelp() 
    {
        System.out.println();
        System.out.print("Du hast bereits "); System.out.print(giveRounds()); System.out.print(" Runden übelebt");
        System.out.println();
        System.out.println("Your command words are:");
        System.out.println("   go quit help");
    }

    /** 
     * Try to go to one direction. If there is an exit, enter
     * the new room, otherwise print an error message.
     */
    private void goRoom(Command command) 
    {
        if(!command.hasSecondWord()) {
            // if there is no second word, we don't know where to go...
            System.out.println("Go where?");
            return;
        }

        String direction = command.getSecondWord();

        // Try to leave current room.
        Room nextRoom = null;
        if(direction.equals("north")){
            nextRoom = currentRoom.northExit;
            rounds++;}
        if(direction.equals("east")){
            nextRoom = currentRoom.eastExit;
            rounds++;}
        if(direction.equals("south")){
            nextRoom = currentRoom.southExit;
            rounds++;}
        if(direction.equals("west")){
            nextRoom = currentRoom.westExit;
            rounds++;}

        if (nextRoom == null)
            System.out.println("There is no door!");
        else {
            currentRoom = nextRoom;
            System.out.println();
            System.out.println("Du bist " + currentRoom.getDescription());
            System.out.print("Exits: ");
            if(currentRoom.northExit != null)
                System.out.print("north ");
            if(currentRoom.eastExit != null)
                System.out.print("east ");
            if(currentRoom.southExit != null)
                System.out.print("south ");
            if(currentRoom.westExit != null)
                System.out.print("west ");
            System.out.println();
        }
    }

    /** 
     * "Quit" was entered. Check the rest of the command to see
     * whether we really quit the game. Return true, if this command
     * quits the game, false otherwise.
     */
    private boolean quit(Command command) 
    {
        if(command.hasSecondWord()) {
            System.out.println("Quit what?");
            return false;
        }
        else
            return true;  // signal that we want to quit
    }
}
