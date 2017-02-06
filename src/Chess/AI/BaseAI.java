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
    private ChessGame currentGame;
    protected ChessBoard board;
    protected ChessPiece.PieceColor currentPlayer;
    protected Random random;
    private static final long DEFAULT_SEED = 1234;

    public BaseAI(ChessGame game) {
        this(game, DEFAULT_SEED);
    }

    public BaseAI(ChessGame game, long seed) {
        projectedGame = game.deepCopy();
        currentGame = game;
        board = currentGame.getBoard();
        currentPlayer = currentGame.getCurrentPlayer();
        random = new Random(seed);
    }

    public abstract Move getNextMove();

    protected ArrayList<Move> availableMoves() {
        ArrayList<Move> moves = new ArrayList<>();

        for (Coord coord : board.getAllPiecesLocationForColor(currentPlayer)) {
            ArrayList<Move> tempMoves = new ArrayList<>();
            Tile tile = board.getTileFromCoordinate(coord);
            ChessPiece piece = tile.getPiece();
            Move [] moveArray = currentGame.allPossibleMovesForPiece(piece, coord);

            Collections.addAll(tempMoves, moveArray);

            for (Move tempMove : tempMoves) {
                if (currentGame.isValidMove(coord, new Coord(tempMove.x, tempMove.y))) {
                    moves.add(tempMove);
                }
            }
        }
        return moves;
    }
}
