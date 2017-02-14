package Console;

import Chess.ChessBoard;
import Chess.Tile;
/*
* The primary purpose of BoardDisplay is to print the board to the console
 */
public class BoardDisplay {
    /**
     * printBoard prints th chess board to the console
     *
     * @param board the full board (8x8) including letters representing pieces and blank spots
     */
    public static void printBoard(ChessBoard board){

        clearConsole();
        Tile[][] b = board.getBoardArray();

        System.out.println("      [A][B][C][D][E][F][G][H] \n");
        for(int i = 0; i < 8; i++) { //8 represents height of board
            System.out.print("[" + (8 - i) + "]   ");

            for (int j = 0; j < 8; j++){ //8 represents width of board
                System.out.print(b[i][j].value());
            }

            System.out.println("   [" + (8 - i) + "]");
        }

        System.out.println("\n      [A][B][C][D][E][F][G][H]\n");
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
