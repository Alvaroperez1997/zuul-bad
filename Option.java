
/**
 * Enumeration class Option - write a description of the enum class here
 * 
 * @author (your name here)
 * @version (version number or date here)
 */
public enum Option
{
    GO("ir"), QUIT("quitar"), HELP("ayuda"), LOOK("mirar"), EAT("comer"), BACK("atras"), TAKE("coger"), ITEMS("bolsa"), DROP("tirar"), UNKNOWN(" ");
    private String comando;
    
    private Option(String comando) {
        this.comando = comando;
    }
    
    public String getComando(){
        return comando;
    }
}
