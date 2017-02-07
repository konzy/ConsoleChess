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

        Coord[] temp = board.getAllPiecesLocationForColor(currentPlayer);

        for (Coord from : board.getAllPiecesLocationForColor(currentPlayer)) {
            ArrayList<Move> tempMoves = new ArrayList<>();
            Tile tile = board.getTileFromCoordinate(from);
            ChessPiece piece = tile.getPiece();
            Move [] moveArray = projectedGame.allPossibleMovesForPiece(piece, from);

            Collections.addAll(tempMoves, moveArray);

            for (Move tempMove : tempMoves) {
                Coord to = new Coord(from.X() + tempMove.x,from.Y() + tempMove.y);
                CoordPair pair = new CoordPair(from, to);
                if (currentGame.isValidMove(pair.getFrom(), pair.getTo())) {
                    moves.add(pair);
                }
            }
        }
        return moves;
    }
}
