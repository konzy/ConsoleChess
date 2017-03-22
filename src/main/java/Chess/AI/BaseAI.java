package Chess.AI;

import Chess.*;
import Chess.Pieces.ChessPiece;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

/**
 * Created by konzy on 2/4/2017.
 */

public abstract class BaseAI {
    protected ChessGame projectedGame;
    protected ChessGame currentGame;
    protected ChessBoard board;
    protected Random random;
    public static final long DEFAULT_SEED = 1234;

    BaseAI(ChessGame game) {
        this(game, DEFAULT_SEED);
    }

    BaseAI(ChessGame game, long seed) {
        try {
            projectedGame = (ChessGame) game.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        currentGame = game;
        board = currentGame.getBoard();
        random = new Random(seed);
    }


    public abstract Move getNextMove();

}
