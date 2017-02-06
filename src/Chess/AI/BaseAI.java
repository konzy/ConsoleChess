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

    BaseAI(ChessGame game) {
        this(game, DEFAULT_SEED);
    }

    BaseAI(ChessGame game, long seed) {
        projectedGame = game.deepCopy();
        currentGame = game;
        board = currentGame.getBoard();
        currentPlayer = currentGame.getCurrentPlayer();
        random = new Random(seed);
    }

    public abstract CoordPair getNextMove();

    protected ArrayList<CoordPair> availableMoves() {
        ArrayList<CoordPair> moves = new ArrayList<>();

        for (Coord coord : board.getAllPiecesLocationForColor(currentPlayer)) {
            ArrayList<Move> tempMoves = new ArrayList<>();
            Tile tile = board.getTileFromCoordinate(coord);
            ChessPiece piece = tile.getPiece();
            Move [] moveArray = currentGame.allPossibleMovesForPiece(piece, coord);

            Collections.addAll(tempMoves, moveArray);

            for (Move tempMove : tempMoves) {
                CoordPair pair = new CoordPair(coord, new Coord(tempMove.x, tempMove.y));
                if (currentGame.isValidMove(pair.getFrom(), pair.getTo())) {
                    moves.add(pair);
                }
            }
        }
        return moves;
    }
}
