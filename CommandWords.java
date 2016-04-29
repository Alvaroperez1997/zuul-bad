import java.util.HashMap;
/**
 * This class is part of the "World of Zuul" application. 
 * "World of Zuul" is a very simple, text based adventure game.  
 * 
 * This class holds an enumeration of all command words known to the game.
 * It is used to recognise commands as they are typed in.
 *
 * @author  Michael Kölling and David J. Barnes
 * @version 2011.07.31D
 */

public class CommandWords
{
    // a constant array that holds all valid command words
    private HashMap<String, Option> commands;

    /**
     * Constructor - initialise the command words.
     */
    public CommandWords()
    {
        commands = new HashMap<String, Option>();
        commands.put("andare", Option.GO);
        commands.put("uscita", Option.QUIT);
        commands.put("aiuto", Option.HELP);
        commands.put("guardare", Option.LOOK);
        commands.put("mangiare", Option.EAT);
        commands.put("ritorno", Option.BACK);
        commands.put("prendere", Option.TAKE);
        commands.put("articoli", Option.ITEMS);
        commands.put("lasciare", Option.DROP);
        commands.put("sconosciuto", Option.UNKNOWN);
    }

    /**
     * Check whether a given String is a valid command word. 
     * @return true if a given string is a valid command,
     * false if it isn't.
     */
    public boolean isCommand(String aString)
    {
        return commands.containsKey(aString);
    }

    /**
     * Print all valid commands to System.out
     */
    public void showAll(){
        for (String key : commands.keySet()) {
            System.out.println(key);
        }
    }

    /**
     * Return the object Option associated with a word.
     * @param commandWord The word to look up (as a string).
     * @return the object Option correspondng to the paramater commandWord, or the object Option.UNKNOWN
     *         if it is not a valid command word
     */
    public Option getCommandWord(String commandWord){
        Option option = Option.UNKNOWN;
        if (isCommand(commandWord)){
            option = commands.get(commandWord);
        }
        return option;
    }
}
