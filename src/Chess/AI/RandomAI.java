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
        ArrayList<Move> moves = availableMoves();
        return moves.get(random.nextInt() % moves.size());
    }
}
