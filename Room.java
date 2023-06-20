import java.util.*;

class Room {
    private String description;
    private HashMap<String, Room> exits;
    ArrayList<Item> items = new ArrayList<Item>();

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
    public void setExit(String direction, Room neighbor) {

        exits.put(direction, neighbor);

    }

    /**
     * Return the description of the room (the one that was defined
     * in the constructor).
     */
    public String getDescription() {
        return description;
    }

    public String getLongDescribtion() {
        return "Du bist " + description + ".\n" + getExitString();
    }

    private String getExitString() {
        String returnString = "Exits:";
        Set<String> keys = exits.keySet();
        for (Iterator<String> iter = keys.iterator(); iter.hasNext();)
            returnString += " " + iter.next();
        returnString += "\nItems im Raum:\n";
        returnString += getRoomItems()/*  + " "*/;
        return returnString;
    }

    public Room getExit(String direction) {
        return (Room) exits.get(direction);
    }

    /* Items vom Room bekommen */

    public Item getItem(int index) {
        return items.get(index);
    }

    public Item getItem(String itemName){
        for (int i = 0; i < items.size(); i++){
            if (items.get(i).getDescription().equals(itemName)) {
                return items.get(i);
            }
        }
        return null;
    }

    public void removeItem(String itemName){
        for (int i = 0; i < items.size(); i++){
            if (items.get(i).getDescription().equals(itemName)){
                items.remove(i);
            }
        }
    }

    /* Ein bestimmtes Item in den Raum setzen */

    public void setItem(Item newitem) {
        items.add(newitem);
    }

    /* Die Beschreibung von Items im Raum bekommen */

    public String getRoomItems() {
        String output = " ";
        for (int i = 0; i < items.size(); i++) {
            output += items.get(i).getDescription() + " ";
        }
        return output;
    }
}
