import java.util.HashMap;
import java.util.Set;
import java.util.ArrayList;
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
    private ArrayList<Item> listaItem;

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
        listaItem = new ArrayList<Item>();
    }

    /**
     * Añade un item a el objeto Room sobre el que se invoca
     */
    public void addItem(Item item){
        listaItem.add(item);
    }

    /**
     * Elimina un Item de la room y lo devuelve
     */
    public Item removeItem(String nameItem){
        Item itemEliminado = null;
        for (int i = 0; i < listaItem.size(); i++) {
            if (listaItem.get(i).getDescriptionItem().equals(nameItem)) {
                itemEliminado = listaItem.get(i);
                listaItem.remove(i);
            }
        }
        return itemEliminado;
    }

    public Room getExit(String direccion){
        return salidas.get(direccion);
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
        Set<String> direcciones = salidas.keySet();
        String salida = "Exit: ";
        for(String direccion : direcciones){
            salida += direccion + " ";
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
        String longDescription = "You are " + description + ".\n" + getExitString() + ".\n";
        System.out.println("");
        if (listaItem.size() != 0){
            for(Item object : listaItem){
                longDescription += "Nombre del objeto: " + object.getDescriptionItem() + " Peso: " + object.getPeso() + ".\n";
            }
        }
        else{
            longDescription += "No existen items en esta sala" + ".\n";
        }
        return longDescription;
    }
}
