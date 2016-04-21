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

    /**
     * Constructor for objects of class Player
     */
    public Player()
    {
        listPlayer = new ArrayList<Item>();
    }

    /**
     * añade un Item de la room en la que se encuentra a la lista del jugador
     */
    public void addItem(Item item)
    {
        listPlayer.add(item);
   }
}
