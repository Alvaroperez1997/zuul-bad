import java.util.HashMap;
/**
 * Class Room - a room in an adventure game.
 *
 * This class is part of the "World of Zuul" application. 
 * "World of Zuul" is a very simple, text based adventure game.  
 *
 * A "Room" represents one location in the scenery of the game.  It is 
 * connected to other rooms via exits.  The exits are labelled north, 
 * east, south, west.  For each direction, the room stores a reference
 * to the neighboring room, or null if there is no exit in that direction.
 * 
 * @author  Michael Kölling and David J. Barnes
 * @version 2011.07.31
 */
public class Room 
{
    String description;
    private HashMap<String, Room> salidas;

    /**
     * Create a room described "description". Initially, it has
     * no exits. "description" is something like "a kitchen" or
     * "an open court yard".
     * @param description The room's description.
     */
    public Room(String description) 
    {
        this.description = description;
        salidas = new HashMap<String, Room>();
    }

    public Room getExit(String direccion){
        Room currentRoom = null;
        if(direccion.equals("north")){
            currentRoom = salidas.get("north");
        }
        if(direccion.equals("northeast")){
            currentRoom = salidas.get("northeast");
        }
        if(direccion.equals("east")){
            currentRoom = salidas.get("east");
        }
        if(direccion.equals("southeast")){
            currentRoom = salidas.get("southeast");
        }
        if(direccion.equals("south")){
            currentRoom = salidas.get("south");
        }
        if(direccion.equals("west")){
            currentRoom = salidas.get("west");
        }
        return currentRoom;
    }

    public void setExits(String direction, Room nextRoom){
        salidas.put(direction, nextRoom);
    }

    /**
     * @return The description of the room.
     */
    public String getDescription()
    {
        return description;
    }

    /**
     * Return a description of the room's exits.
     * For example: "Exits: north east west"
     *
     * @ return A description of the available exits.
     */
    public String getExitString(){
        String salida = "Exits: ";
        if(salidas.get("north") != null) {
            salida += "north ";
        }
        if(salidas.get("northeast") != null) {
            salida += "northeast ";
        }
        if(salidas.get("east") != null) {
            salida += "east ";
        }
        if(salidas.get("southeast") != null) {
            salida += "southeast ";
        }
        if(salidas.get("south") != null) {
            salida += "south ";
        }
        if(salidas.get("west") != null) {
            salida += "west ";
        }
        return salida;
    }

    /**
     * Return a long description of this room, of the form:
     *     You are in the 'name of room'
     *     Exits: north west southwest
     * @return A description of the room, including exits.
     */
    public String getLongDescription(){
        return "You are " + description + ".\n" + getExitString();
    }
}
