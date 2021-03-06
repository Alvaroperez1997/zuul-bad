
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
        Room entrada, sala1, sala2, sala3, sala4, sala5, sala6, sala7, salida;
        
        // create the rooms and items
        entrada = new Room("Entrada de la cueva");
        sala1 = new Room("Primera sala");
        sala1.addItem(new Item("espada", 50, false, 0));
        sala1.addItem(new Item("armadura", 80, false, 0));
        sala2 = new Room("Segunda sala");
        sala2.addItem(new Item("escudo", 60, false, 0));
        sala3 = new Room("Tercera sala");
        sala3.addItem(new Item("corazon", 5, false, 40));
        sala4 = new Room("Cuarta sala");
        sala4.addItem(new Item("vara", 20, true, 0));
        sala5 = new Room("Quinta sala");
        sala5.addItem(new Item("pocion", 5, false, 20));
        sala5.addItem(new Item("amuleto", 6, false, 0));
        sala6 = new Room("Sexta sala");
        sala6.addItem(new Item("galleta", 5, false, 10));
        sala7 = new Room("Septima sala");
        sala7.addItem(new Item("llave", 4, false, 0));
        salida = new Room("Salida de la cueva");
        
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
        sala4.setExits("north", sala6);
        sala4.setExits("west", sala3);
        sala4.setExits("east", salida);
        sala4.setExits("southwest", sala5);
        sala5.setExits("north", sala3);
        sala5.setExits("northeast", sala4);
        sala5.setExits("northwest", sala2);
        sala6.setExits("south", sala4);
        sala6.setExits("southwest", sala3);
        sala6.setExits("north", sala7);
        sala7.setExits("south", sala6);

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
        String ultimaSala = "Salida de la cueva";
        boolean win = false;
        boolean lose = false;
                
        boolean finished = false;
        while (!finished && !win && !lose) {
            Command command = parser.getCommand();
            finished = processCommand(command);
            if (player.getCurrentRoom().getDescription().equals(ultimaSala)){
                win = true;
                System.out.println("Has acabado el juego. Enhorabuena��");
            }
            if (player.getResistencia() <= 5) {
                lose = true;
                System.out.println("Te has quedado sin resistencia. Has perdido.");
            }
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
        System.out.println("Type " + Option.HELP.getComando() + " if you need help.");
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
            case QUIT: wantToQuit = player.quit(command);
                break;
            case LOOK: System.out.print(player.getCurrentRoom().getLongDescription());
                break;
            case EAT: player.eat(command);
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
}