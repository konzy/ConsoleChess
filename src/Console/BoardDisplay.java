package Console;

import Chess.ChessBoard;
/*
* The primary purpose of BoardDisplay is to print the board to the console
 */
public class BoardDisplay {
    /**
     * @deprecated use ChessBoard 's built in toString()
     * printBoard prints th chess board to the console
     *
     * @param board the full board (8x8) including letters representing pieces and blank spots
     */
    // TODO: 3/14/2017 change to use board's toString() function
    @Deprecated
    public static void printBoard(ChessBoard board){
        clearConsole();
        System.out.println(board);
    }

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
