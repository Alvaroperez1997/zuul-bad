
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
    private boolean fijo;
    private int resistencia;

    /**
     * Constructor for objects of class Item
     */
    public Item(String descriptionItem, float peso, boolean fijo, int resistencia)
    {
        this.descriptionItem = descriptionItem;
        this.peso = peso;
        this.fijo = fijo;
        this.resistencia = resistencia;
    }
    
    /**
     * Devuelve el boolean de item
     */
    public boolean getFijo(){
        return fijo;
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
    
    /**
     * Devuelve la resistencia que aumenta a un jugador cuando se come
     */
    public int getResistencia(){
        return resistencia;
    }
}
