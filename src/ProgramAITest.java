import Chess.AI.RandomAI;
import Chess.ChessGame;
import Chess.Coord;
import Chess.CoordPair;
import Chess.Move;
import Console.InputHandler;

import java.util.Scanner;

/**
 * The Program class holds the main class to run the chess game in the console.
 */


public class ProgramAITest {

    public static void main(String args[]){
        InputHandler handler = new InputHandler();
        Scanner scanner = new Scanner(System.in);
        ChessGame game = new ChessGame();         //Sets up chess game, initial player is white, prints board to console
        while (!game.isFinished()){
            System.out.println("Enter move (eg. A2-A3):");
            String input = scanner.nextLine().trim();

            if(!handler.isValid(input)){
                System.out.println("Invalid input!");
                System.out.println("Valid input is in form: A2-A3");
            } else {
                CoordPair pair = handler.getCoordPair(input);
                if (game.isValidMove(pair.getFrom(), pair.getTo()))
                    game.playMove(pair.getFrom(), pair.getTo());
                else
                    System.out.println("Illegal move!");
            }
            RandomAI randomAI = new RandomAI(game);
            CoordPair aiMove = randomAI.getNextMove();
            game.playMove(aiMove.getFrom(), aiMove.getTo());

        }
        System.out.println("Game has finished. Thanks for playing.");
    }

}