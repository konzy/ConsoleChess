import Chess.ChessGame;
import Chess.Coord;
import Console.BoardDisplay;
import Console.InputHandler;
import Data.Load;
import Data.Replay;
import Data.Save;

import java.io.IOException;
import java.util.Scanner;

/**
 * The Program class holds the main class to run the chess game in the console.
 */

public class Program {
    public static void main(String args[]) throws IOException {
        InputHandler handler = new InputHandler();
        Scanner scanner = new Scanner(System.in);
        ChessGame game = new ChessGame();         //Sets up chess game, initial player is white, prints board to console
        Save.clearAutoSave();
        try {
            Save.AutoSave(game.getBoard());
        } catch (IOException e) {
            e.printStackTrace();
        }
        while (!game.isFinished()){
            System.out.println("Enter move (eg. A2-A3):");
            String input = scanner.nextLine().trim();
            if(input.equals("save")){
                System.out.println("What would you like to call the save?");
                String saveName = scanner.nextLine().trim();
                Save.Save("AutoSave", saveName);
            } else if(input.equals("replay")){
                Replay.replay();
            } else if(input.equals("load")){
                System.out.println("What is the name of the save?");
                String saveName = scanner.nextLine().trim();
                game.setBoard(Load.Load(saveName, game));
            } else if(!handler.isValid(input)){
                System.out.println("Invalid input!");
                System.out.println("Valid input is in form: A2-A3");
            } else {
                Coord from = handler.getFrom(input);  //first half of input
                Coord to = handler.getTo(input);     //second half of input

                if (game.isValidMove(from, to)){
                    game.playMove(from, to);
                    try {
                        Save.AutoSave(game.getBoard());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } else
                    System.out.println("Illegal move!");
            }


        }
        System.out.println("Game has finished. Thanks for playing.");
    }

}