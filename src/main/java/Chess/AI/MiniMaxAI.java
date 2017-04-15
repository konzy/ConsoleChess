package Chess.AI;

import Chess.ChessGame;
import Chess.Move;

/**
 * Created by konzy on 3/29/2017.
 */
public class MiniMaxAI extends BaseAI {
    public MiniMaxAI(ChessGame game) {
        super(game);
    }

    MiniMaxAI(ChessGame game, long seed) {
        super(game, seed);
    }

    @Override
    public Move getNextMove() {
        MinMaxNode node = new MinMaxNode(clonedGame, 4);
        return node.bestMove();
    }
}
