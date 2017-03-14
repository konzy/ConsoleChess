import Chess.AI.RandomAI;
import Chess.ChessGame;
import Chess.Location;
import Chess.Move;
import Console.InputHandler;

import java.util.Scanner;

/**
 * The Program class holds the main class to run the chess game in the console.
 */

@Deprecated
public class ProgramAITest {

    enum Turn {
        HUMAN,
        AI
    }

    public static void main(String args[]){
        InputHandler handler = new InputHandler();
        Scanner scanner = new Scanner(System.in);
        ChessGame game = new ChessGame();         //Sets up chess game, initial player is white, prints board to console
        Turn turn = Turn.HUMAN;

        while (game.getState() == ChessGame.GameState.PLAY){
            switch (turn) {
                case HUMAN: {
                    System.out.println("Enter move (eg. A2-A3):");
                    String input = scanner.nextLine().trim();

                    if(!handler.isValid(input)){
                        System.out.println("Invalid input!");
                        System.out.println("Valid input is in form: A2-A3");
                    } else {
                        Location from = handler.getFrom(input);  //first half of input
                        Location to = handler.getTo(input);     //second half of input
                        if (game.playMove(from, to)) {
                            turn = Turn.AI;
                        }
                    }
                }
                break;
                case AI: {
                    RandomAI randomAI = new RandomAI(game);
                    Move aiMove = randomAI.getNextMove();
                    game.playMove(aiMove.getPiece().getLocation(), aiMove.getTo());
                    turn = Turn.HUMAN;
                }
                break;
            }
        }
        System.out.println(game.getState().toString());
        System.out.println("Game has finished. Thanks for playing.");
    }

}