import java.util.ArrayList;
/**
 * Write a description of class Player here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Player
{
    // instance variables - replace the example below with your own
    private ArrayList<Item> listPlayer;
    public static final float limitePeso = 150;
    private float pesoBolsa;

    /**
     * Constructor for objects of class Player
     */
    public Player()
    {
        listPlayer = new ArrayList<Item>();
        pesoBolsa = 0;
    }
    
    /**
     * Fija el valor del peso del player
     */
    public void setPesoBolsa(float peso) {
        pesoBolsa += peso;
    }

    /**
     * añade un Item de la room en la que se encuentra a la lista del jugador
     */
    public void addItem(Item item)
    {
        listPlayer.add(item);
        setPesoBolsa(item.getPeso());
    }
    
    /**
     * Devuelve el peso que lleva
     */
    public float getPesoBolsa()
    {
        return pesoBolsa;
    }
    
    /**
     * Busqueda del Item que desea
     */
    public Item findItem(String description){
        Item itemEncontrado = null;
        for (Item object : listPlayer) {
            if (object.getDescriptionItem().equals(description)) {
                itemEncontrado = object;
            }
        }
        return itemEncontrado;
    }
    
    /**
     * Muestra la lista de los items de player
     */
    public String getItemDescription() {
        String itemDescription = "peso que lleva el jugador: " + getPesoBolsa() + ".\n" + "You have: " + "\n";
        System.out.print("");
        if (listPlayer.size() != 0){
            for(Item object : listPlayer){
                itemDescription += "Nombre del objeto: " + object.getDescriptionItem() + " Peso: " + object.getPeso() + ".\n";
            }
        }
        else{
            itemDescription += "La bolsa de items esta vacía" + ".\n";
        }
        return itemDescription;
    }
    
    /**
     * Borra el item de la lista de item del jugador
     */
    public Item dropItem(Item item) {
        Item itemDrop = item;
        if (item != null) {
            listPlayer.remove(item);
        }
        return itemDrop;
    }
}
