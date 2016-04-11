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
    private String description;
    private Room northExit;
    private Room southExit;
    private Room northeastExit;
    private Room eastExit;
    private Room southeastExit;
    private Room westExit;

    /**
     * Create a room described "description". Initially, it has
     * no exits. "description" is something like "a kitchen" or
     * "an open court yard".
     * @param description The room's description.
     */
    public Room(String description) 
    {
        this.description = description;
    }

    public Room getExit(String direccion){
        Room currentRoom = null;
        if(direccion.equals("north")){
            currentRoom = northExit;
        }
        if(direccion.equals("northeast")){
            currentRoom = northeastExit;
        }
        if(direccion.equals("east")){
            currentRoom = eastExit;
        }
        if(direccion.equals("southeast")){
            currentRoom = southeastExit;
        }
        if(direccion.equals("south")){
            currentRoom = southExit;
        }
        if(direccion.equals("west")){
            currentRoom = westExit;
        }
        return currentRoom;
    }

    /**
     * Define the exits of this room.  Every direction either leads
     * to another room or is null (no exit there).
     * @param north The north exit.
     * @param east The east east.
     * @param south The south exit.
     * @param west The west exit.
     */
    public void setExits(Room north, Room northeast, Room east, Room southeast, Room south, Room west) 
    {
        if(north != null)
            northExit = north;
        if(northeast != null)
            northeastExit = northeast;
        if(east != null)
            eastExit = east;
        if(southeast != null)
            southeastExit = southeast;
        if(south != null)
            southExit = south;
        if(west != null)
            westExit = west;
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
        String salida = "Exit: ";
        if(northExit != null) {
            salida += "north ";
        }
        if(northeastExit != null) {
            salida += "northeast ";
        }
        if(eastExit != null) {
            salida += "east ";
        }
        if(southeastExit != null) {
            salida += "southeast ";
        }
        if(southExit != null) {
            salida += "south ";
        }
        if(westExit != null) {
            salida += "west ";
        }
        return salida;
    }
}
