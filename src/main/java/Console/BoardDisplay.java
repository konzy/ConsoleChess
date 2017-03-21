package Console;

/*
* The primary purpose of BoardDisplay is to print the board to the console
 */
public class BoardDisplay {
    /**
     * Universal console clear for both Windows and Unix machines.
     */
    public static void clearConsole(){
        try
        {
            final String os = System.getProperty("os.name");

            if (os.contains("Windows"))
            {
                //ASCII escape code
                System.out.print("\033[H\033[2J");
            }
            else
            {
                Runtime.getRuntime().exec("clear");
            }
        }
        catch (final Exception e){
            System.out.println("Error while trying to clear console");
        }
    }
}
