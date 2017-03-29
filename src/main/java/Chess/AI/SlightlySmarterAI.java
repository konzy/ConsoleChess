package Chess.AI;

import Chess.ChessGame;
import Chess.Move;

/**
 * Created by konzy on 3/28/2017.
 */
public class SlightlySmarterAI extends BaseAI {
    SlightlySmarterAI(ChessGame game) {
        super(game);
    }

    SlightlySmarterAI(ChessGame game, long seed) {
        super(game, seed);
    }

    @Override
    public Move getNextMove() {
        return null;
    }




}
