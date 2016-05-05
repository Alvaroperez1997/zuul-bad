import java.util.ArrayList;
import java.util.Stack;
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
    private Room currentRoom;
    private Stack<Room> salasAnteriores;

    /**
     * Constructor for objects of class Player
     */
    public Player(Room currentRoom)
    {
        listPlayer = new ArrayList<Item>();
        pesoBolsa = 0;
        salasAnteriores = new Stack<>();
        this.currentRoom = currentRoom; 
    }
    
    /**
     * Devuelve la habitacion en la que esta el jugador
     */
    public Room getCurrentRoom(){
        return currentRoom;
    }
    
    /**
     * Fija el valor del peso del player
     */
    public void setPesoBolsa(float peso) {
        pesoBolsa += peso;
    }
    
    /**
     * Resta el peso del item a player
     */
    public void setPesoBolsaResto(float peso) {
        pesoBolsa -= peso;
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
     * Try to go in one direction. If there is an exit, enter
     * the new room, otherwise print an error message.
     */
    public void goRoom(Command command) 
    {
        if(!command.hasSecondWord()) {
            // if there is no second word, we don't know where to go...
            System.out.println("Go where?");
            return;
        }

        String direction = command.getSecondWord();

        // Try to leave current room.
        Room nextRoom = currentRoom.getExit(direction);

        if (nextRoom == null) {
            System.out.println("There is no door!");
        }
        else {
            salasAnteriores.push(currentRoom);
            currentRoom = nextRoom;
            printLocalitationInfo();
            System.out.println();
        }
    }
    
    /**
     * Añade un item a player y lo elimina de room
     */
    public void takeItem(Command command) {
        if(!command.hasSecondWord()) {
            // if there is no second word, we don't know where to go...
            System.out.println("Coger que?");
            return;
        }
        
        String descriptionItem = command.getSecondWord();
        
        Item item = currentRoom.findItem(descriptionItem);
        
        if (item == null) {
            System.out.println("No hay ningun item con ese nombre en la room!");
        }
        else if(item.getFijo()) {
            System.out.println("Este objeto no se puede meter en la bolsa");
        }
        else if(limitePeso >= (getPesoBolsa() + item.getPeso())) {
            addItem(currentRoom.removeItem(item));
        }
        else {
            System.out.println("El jugador lleva demasiado peso encima");
        }
    }
    
    /**
     * Elimina un elemento de player y lo añade a la room
     */
    public void dropItem(Command command) {
        if(!command.hasSecondWord()) {
            // if there is no second word, we don't know where to go...
            System.out.println("Coger que?");
            return;
        }
        
        String descriptionItem = command.getSecondWord();
        
        Item item = findItem(descriptionItem);
        
        if (item == null) {
            System.out.println("El jugador no tiene ningun item en la bolsa");
        }
        else {
            currentRoom.addItem(dropItem(item));
            setPesoBolsaResto(item.getPeso());
        }
    }
    
    /**
     * Vuelve a la habitacion anterior
     */
    public void goBack(){
        if (salasAnteriores.empty()) {
            System.out.println("No es posible volver a la localizacion anterior");
        }
        else{
            currentRoom = salasAnteriores.pop();
            printLocalitationInfo();
            System.out.println();
        }
    }
    
    /**
     * metodo que imprime la informacion
     */
    public void printLocalitationInfo(){
        System.out.println(currentRoom.getLongDescription());
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
    
    /**
     * Tamaño del stack
     */
    public int tamaño(){
        return salasAnteriores.size();
    }
    
}
