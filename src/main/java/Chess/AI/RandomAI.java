package Chess.AI;

import Chess.ChessGame;
import Chess.Move;
import java.util.ArrayList;

/**
 * Created by konzy on 2/6/2017.
 */

public class RandomAI extends BaseAI {
    public RandomAI(ChessGame game) {
        super(game);
    }

    public RandomAI(ChessGame game, long seed) {
        super(game, seed);
    }

    @Override
    public Move getNextMove() {
        ArrayList<Move> moves = currentGame.getBoard().getAllValidMoves(currentGame.getCurrentPlayer());
        if (moves.size() == 0) {
            System.out.println("The AI has no moves to do, why wasn't this caught before?");
            for (StackTraceElement ste : Thread.currentThread().getStackTrace()) {
                System.out.println(ste);
            }
            return null;
        }
        Move temp = moves.get(Math.abs(random.nextInt()) % moves.size());
        if (temp == null) {
            System.out.println("wtf null");
        }
        return temp;
    }
}
