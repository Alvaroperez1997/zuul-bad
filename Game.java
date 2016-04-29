
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
    private Player player;
        
    /**
     * Create the game and initialise its internal map.
     */
    public Game() 
    {
        createRooms();
        parser = new Parser();
    }

    /**
     * Create all the rooms and link their exits together.
     */
    private void createRooms()
    {
        Room entrada, sala1, sala2, sala3, sala4, sala5, salida;
        
        // create the rooms and items
        entrada = new Room("Entrada de la cueva");
        sala1 = new Room("Primera sala");
        sala1.addItem(new Item("espada", 50));
        sala1.addItem(new Item("armadura", 80));
        sala2 = new Room("Segunda sala");
        sala2.addItem(new Item("escudo", 60));
        sala3 = new Room("Tercera sala");
        sala4 = new Room("Cuarta sala");
        sala5 = new Room("Quinta sala");
        sala5.addItem(new Item("pocion", 5));
        sala5.addItem(new Item("amuleto", 6));
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

        player = new Player(entrada); //Crea el jugador
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
        System.out.println("Type 'aiuto' if you need help.");
        System.out.println();
        player.printLocalitationInfo();
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

        Option commandWord = command.getCommandWord();
        switch(commandWord) {
            case HELP: printHelp();
                break;
            case GO: player.goRoom(command);
                break;
            case BACK: player.goBack();
                break;
            case QUIT: wantToQuit = quit(command);
                break;
            case LOOK: System.out.print(player.getCurrentRoom().getLongDescription());
                break;
            case EAT: System.out.println("You have eaten now and you are not hungry any more");
                break;
            case TAKE: player.takeItem(command);
                break;
            case ITEMS: System.out.println(player.getItemDescription());
                break;
            case DROP: player.dropItem(command);
                break;
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
