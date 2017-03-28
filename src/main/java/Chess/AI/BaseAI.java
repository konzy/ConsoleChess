package Chess.AI;

import Chess.*;

import java.util.Random;

/**
 * Created by konzy on 2/4/2017.
 */

public abstract class BaseAI {
    protected ChessGame clonedGame;
    protected ChessGame currentGame;
    protected ChessBoard board;
    protected Random random;
    public static final long DEFAULT_SEED = 1234;

    BaseAI(ChessGame game) {
        this(game, DEFAULT_SEED);
    }

    BaseAI(ChessGame game, long seed) {
        clonedGame = (ChessGame) game.clone();
        currentGame = game;
        board = currentGame.getBoard();
        random = new Random(seed);
    }


    public abstract Move getNextMove();

}
