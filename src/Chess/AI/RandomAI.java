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

    @Override
    public Move getNextMove() {
        ArrayList<Move> moves = currentGame.getBoard().getAllValidMoves(currentGame.getCurrentPlayer());
        if (moves.size() == 0) {
            System.out.println("The AI has no moves to do, why wasn't this caught before?");
        }
        return moves.get(Math.abs(random.nextInt()) % moves.size());
    }
}
