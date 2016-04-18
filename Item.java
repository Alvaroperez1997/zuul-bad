
/**
 * Write a description of class Item here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Item
{
    // instance variables - replace the example below with your own
    private String descriptionItem;
    private float peso;

    /**
     * Constructor for objects of class Item
     */
    public Item(String descriptionItem, float peso)
    {
        this.descriptionItem = descriptionItem;
        this.peso = peso;
    }
    
    /**
     * Devuelve la descripcion del item
     */
    public String getDescriptionItem() {
        return descriptionItem;
    }
    
    /**
     * Devuelve el peso del item
     */
    public float getPeso() {
        return peso;
    }
}
