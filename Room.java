/*
 * Class Room - a room in an adventure game.
 *
 * This class is the main class of the "World of Zuul" application. 
 * "World of Zuul" is a very simple, text based adventure game.  
 *
 * A "Room" represents one location in the scenery of the game.  It is 
 * connected to other rooms via exits.  The exits are labelled north, 
 * east, south, west.  For each direction, the room stores a reference
 * to the neighboring room, or null if there is no exit in that direction.
 * 
 * @author  Michael Kolling and David J. Barnes
 * @version 1.0 (February 2002)
 */

import java.util.*;

class Room {
    private String description;
    private HashMap<String, Room> exits;
    /**   
    private Room northExit;
    private Room southExit;
    private Room eastExit;
    private Room westExit;
    */
    /**
     * Create a room described "description". Initially, it has
     * no exits. "description" is something like "a kitchen" or
     * "an open court yard".
     */
    public Room(String description) {
        this.description = description;
        exits = new HashMap<>();
    }

    /**
     * Define the exits of this room. Every direction either leads
     * to another room or is null (no exit there).
     */
    public void setExit(String direction, Room neighbor) 
    {
        
            exits.put(direction, neighbor);
        
        /** 
        if (north != null)
           {exits.put("north", north);}
        if (east != null)
           {exits.put("east", east);} 
        if (south != null)
           {exits.put("south", south);}
        if (west != null)
           {exits.put("west", west);}*/

    }

    /**
     * Return the description of the room (the one that was defined
     * in the constructor).
     */
    public String getDescription() {
        return description;
    }

    public Room getExit(String direction) 
    {
        return exits.get(direction);
    }
    /**{
        if (direction == "north") {
            return this.northExit;
        }
        if (direction == "south") {
            return this.southExit;
        }
        if (direction == "east") {
            return this.eastExit;
        }
        if (direction == "west") {
            return this.westExit;
        }
        else return null;
    }*/
   
}

