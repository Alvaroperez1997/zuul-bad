import java.util.Stack;
/**
 *  This class is the main class of the "World of Zuul" application. 
 *  "World of Zuul" is a very simple, text based adventure game.  Users 
 *  can walk around some scenery. That's all. It should really be extended 
 *  to make it more interesting!
 * 
 *  To play this game, create an instance of this class and call the "play"
 *  method.
 * 
 *  This main class creates and initialises all the others: it creates all
 *  rooms, creates the parser and starts the game.  It also evaluates and
 *  executes the commands that the parser returns.
 * 
 * @author  Michael Kölling and David J. Barnes
 * @version 2011.07.31
 */

public class Game 
{
    private Parser parser;
    private Room currentRoom;
    private Stack<Room> pila;
    private Player player;
        
    /**
     * Create the game and initialise its internal map.
     */
    public Game() 
    {
        createRooms();
        parser = new Parser();
        pila = new Stack<>();
        player = new Player();
    }

    /**
     * Create all the rooms and link their exits together.
     */
    private void createRooms()
    {
        Room entrada, sala1, sala2, sala3, sala4, sala5, salida;
        
        // create items
        Item item1 = new Item("espada", 50);
        Item item2 = new Item("escudo", 60);
        Item item3 = new Item("armadura", 80);
        Item item4 = new Item("pocion", 5);
        Item item5 = new Item("amuleto", 6);
        
        // create the rooms
        entrada = new Room("Entrada de la cueva");
        sala1 = new Room("Primera sala");
        sala1.addItem(item1);
        sala1.addItem(item3);
        sala2 = new Room("Segunda sala");
        sala2.addItem(item2);
        sala3 = new Room("Tercera sala");
        sala4 = new Room("Cuarta sala");
        sala5 = new Room("Quinta sala");
        sala5.addItem(item4);
        sala5.addItem(item5);
        salida = new Room("Has llegado a la ultima sala donde esta el jefe final");
        
        // initialise room exits
        entrada.setExits("north", sala1);
        entrada.setExits("southeast", sala3);
        entrada.setExits("south", sala2);
        sala1.setExits("south", entrada);
        sala2.setExits("north", entrada);
        sala2.setExits("east", sala3);
        sala2.setExits("southeast", sala5);
        sala3.setExits("northeast", salida);
        sala3.setExits("east", sala4);
        sala3.setExits("south", sala5);
        sala3.setExits("west", sala2);
        sala4.setExits("north", salida);
        sala4.setExits("west", sala3);
        sala5.setExits("north", sala3);
        sala5.setExits("northeast", sala4);
        salida.setExits("south", sala4);

        currentRoom = entrada;  // start game outside
    }

    /**
     *  Main play routine.  Loops until end of play.
     */
    public void play() 
    {            
        printWelcome();

        // Enter the main command loop.  Here we repeatedly read commands and
        // execute them until the game is over.
                
        boolean finished = false;
        while (! finished) {
            Command command = parser.getCommand();
            finished = processCommand(command);
        }
        System.out.println("Thank you for playing.  Good bye.");
    }

    /**
     * Print out the opening message for the player.
     */
    private void printWelcome()
    {
        System.out.println();
        System.out.println("Welcome to the World of Zuul!");
        System.out.println("World of Zuul is a new, incredibly boring adventure game.");
        System.out.println("Type 'help' if you need help.");
        System.out.println();
        printLocalitationInfo();
        System.out.println();
    }

    /**
     * Given a command, process (that is: execute) the command.
     * @param command The command to be processed.
     * @return true If the command ends the game, false otherwise.
     */
    private boolean processCommand(Command command) 
    {
        boolean wantToQuit = false;

        if(command.isUnknown()) {
            System.out.println("I don't know what you mean...");
            return false;
        }

        String commandWord = command.getCommandWord();
        if (commandWord.equals("help")) {
            printHelp();
        }
        else if (commandWord.equals("go")) {
            goRoom(command);
        }
        else if (commandWord.equals("back")) {
            goBack();
        }
        else if (commandWord.equals("quit")) {
            wantToQuit = quit(command);
        }
        else if (commandWord.equals("look")){
            System.out.print(currentRoom.getLongDescription());
        }
        else if (commandWord.equals("eat")) {
            System.out.println("You have eaten now and you are not hungry any more");
        }
        else if (commandWord.equals("take")) {
            takeItem(command);
        }

        return wantToQuit;
    }

    // implementations of user commands:

    /**
     * Print out some help information.
     * Here we print some stupid, cryptic message and a list of the 
     * command words.
     */
    private void printHelp() 
    {
        System.out.println("You are lost. You are alone. You wander");
        System.out.println("around at the university.");
        System.out.println();
        System.out.println("Your command words are:");
        parser.getAllCommands();
    }

    /** 
     * Try to go in one direction. If there is an exit, enter
     * the new room, otherwise print an error message.
     */
    private void goRoom(Command command) 
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
            pila.push(currentRoom);
            currentRoom = nextRoom;
            printLocalitationInfo();
            System.out.println();
        }
    }
    
    private void takeItem(Command command) {
        if(!command.hasSecondWord()) {
            // if there is no second word, we don't know where to go...
            System.out.println("Coger que?");
            return;
        }
        
        String descriptionItem = command.getSecondWord();
        
        Item item = currentRoom.removeItem(descriptionItem);
        if (item == null) {
            System.out.println("No hay ningun item con ese nombre en la room!");
        }
        else {
            player.addItem(item);
        }
    }
    
    private void goBack(){
        if (pila.empty()) {
            System.out.println("No es posible volver a la localizacion anterior");
        }
        else{
            currentRoom = pila.pop();
            printLocalitationInfo();
            System.out.println();
        }
    }
    
    /**
     * metodo que imprime la informacion
     */
    private void printLocalitationInfo(){
        System.out.println(currentRoom.getLongDescription());
    }

    /** 
     * "Quit" was entered. Check the rest of the command to see
     * whether we really quit the game.
     * @return true, if this command quits the game, false otherwise.
     */
    private boolean quit(Command command) 
    {
        if(command.hasSecondWord()) {
            System.out.println("Quit what?");
            return false;
        }
        else {
            return true;  // signal that we want to quit
        }
    }
}
