import java.util.ArrayList;

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
    Zielperson Zielperson;
    Item Bruen, Self_Revive, Self_Revive2, Kevlarweste, Kevlarweste2, Kar98k, Milano, Fernrohr, R9_0, Amax, Kilo, Mac_10, Bullfrog, Nagelpistole, Mp5;
    ArrayList<Item> inventory = new ArrayList<Item>();

    /**
     * Create the game and initialise its internal map.
     */
    public Game() {
        createRooms();
        createItems();
        Zielperson = new Zielperson("Zielperson", Biowaffen_Dach);
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

        inventory.add(new Item("Starteritem"));
    }

    private void createItems()
    {
        Bruen = new Item("Bruen");
        Prison_Dach.setItem(Bruen);
        
        Self_Revive = new Item("Self Revive");
        Sicherheitsabteilung.setItem(Self_Revive);
        
        Self_Revive2 = new Item("Self Revive");
        Biowaffen_Keller.setItem(Self_Revive2);
        
        Kevlarweste = new Item("Kevlarweste");
        Abstelllager.setItem(Kevlarweste);

        Kevlarweste2 = new Item("Kevlarweste");
        Fabrik.setItem(Kevlarweste2);

        Kar98k = new Item("Kar98k");
        Leuchtturm.setItem(Kar98k);

        Milano = new Item("Milano");
        Hauptsitz.setItem(Milano);

        Fernrohr = new Item("Fernrohr");
        Leuchtturm.setItem(Fernrohr);
 
        R9_0 =new Item("R9-0");
        Chemie_Labor.setItem(R9_0);

        Amax = new Item("Amax");
        Wohnviertel.setItem(Amax);

        Kilo = new Item("Kilo");
        Fabrik.setItem(Kilo);

        Mac_10 = new Item("Mac 10");
        KontrollRäume.setItem(Mac_10);

        Bullfrog = new Item("Bullfrog");
        Helikopter_Landeplatz.setItem(Bullfrog);

        Nagelpistole = new Item("Nagelpistole");
        Prison_Block.setItem(Nagelpistole);

        Mp5 = new Item("Mp5");
        Zelte.setItem(Mp5);
    }

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

    
    private void printWelcome() {
        System.out.println();
        System.out.println("Willkommen auf Rebirth Island!");
        System.out.println("Überlebe um zu gewinnen.");
        System.out.println("Du wirst nach 10 überlebten Runden oder nach dem Auffinden der Zielperson extrahiert.");
        System.out.println();

        System.out.println(currentRoom.getLongDescribtion());
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
        if (commandWord.equals("help")){
            printHelp();
        }
        else if (commandWord.equals("go")){
            goRoom(command);
        }
        else if (commandWord.equals("look")){
            wantToQuit = look();
        }
        else if (commandWord.equals("quit")){
            wantToQuit = quit(command);
        }
        else if (commandWord.equals("inventory")) {
            printInventory();
        }
        else if (commandWord.equals("get")) {
                getItem(command);    
        }
        else if (commandWord.equals("drop")) {
            dropItem(command);    
        }

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

    private void printInventory() {
        String output = " ";
        for (int i = 0; i < inventory.size(); i++) {
            output += inventory.get(i).getDescription() + " ";
        }
        System.out.println("Du trägst mit dir:");
        System.out.println(output);
    }

    private boolean look() {
        if (currentRoom == Zielperson.getLocation()) 
            {
            System.out.print("Du hast die Zielperson gefunden und wirst jetzt extrahiert!");
            return true;
            } 
            
            else 
            {
            System.out.println("Die Zielperson ist hier nicht aufzufinden!");
            return false;
            }

    }

    public int giveRounds() {
        return rounds;
    }

    // implementations of user commands:

   
    private void printHelp() {
        System.out.println();
        System.out.print("Du hast bereits ");
        System.out.print(giveRounds());
        System.out.print(" Runden übelebt");
        System.out.println();
        System.out.println("Deine Kommandos sind:");
        System.out.println("-> go, quit, help, inventory, look, get, drop");
        System.out.println();
    }

    /**
     * Try to go to one direction. If there is an exit, enter
     * the new room, otherwise print an error message.
     */

    private void getItem(Command command) {
        if (!command.hasSecondWord()) {
            // if there is no second word, we don't know where to go...
            System.out.println("Was aufheben?");
            return;
        }

        String item = command.getSecondWord();

        // Try to leave current room.

        Item newItem = currentRoom.getItem(item);

        if (newItem == null){
            System.out.println("Dieses Item gibt es hier nicht!");
        }
        else if (inventory.size() > 2) 
        System.out.println("Du kannst nur maximal 3 Items gleichzeitig in deinem Inventar haben!");
        else {
           inventory.add(newItem);
           currentRoom.removeItem(item);
           System.out.println("Aufgehoben:" + item);
           System.out.println();
        }
        }

        private void dropItem(Command command) {
            if (!command.hasSecondWord()) {
                // if there is no second word, we don't know where to go...
                System.out.println("Was fallenlassen?");
                return;
            }
    
            String item = command.getSecondWord();
    
            // Try to leave current room.
    
            Item newItem = null;
            int index = 0;
            for (int i = 0; i < inventory.size(); i++) {
                if (inventory.get(i).getDescription().equals(item)) {
                     newItem = inventory.get(i);
                     index = i;
                }
            }
    
            if (newItem == null){
                System.out.println("Dieses Item befindet sich nicht in deinem Inventar!");
            }
            else if (inventory.size() == 1)
            System.out.println("Du musst mindestens ein Item bei dir haben!");
            else {
               inventory.remove(index);
               currentRoom.setItem(new Item (item));
               System.out.println("Fallengelassen:" + item);
               System.out.println();
            }
            }

     private void goRoom(Command command) {
        if (!command.hasSecondWord()) {
            // if there is no second word, we don't know where to go...
            System.out.println("Wohin?");
            return;
        }

        String direction = command.getSecondWord();

        // Try to leave current room.

        Room nextRoom = currentRoom.getExit(direction);

        if (nextRoom == null)
            System.out.println("Hier geht es nicht lang!");
        else {
            currentRoom = nextRoom;
            System.out.println();
            System.out.println(currentRoom.getLongDescribtion());
            rounds++;
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

}
