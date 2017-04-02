package Chess.AI;

import Chess.ChessGame;
import Chess.Move;
import Chess.Pieces.ChessPiece;

import java.util.ArrayList;


/**
 * konzy
 */
public class MinMaxNode {
    private ChessGame game;
    private Move move;
    private double value;
    private ArrayList<MinMaxNode> possibleMoves = new ArrayList<>();
    private ChessPiece.PieceColor maxColor;
    private int currentIterations = 0;
    private int maxIterations = 0;

    public MinMaxNode(ChessGame game, ChessPiece.PieceColor maxColor, Move move, int currentIterations) {
        this.game = game;
        this.move = move;
        this.maxColor = maxColor;
        this.currentIterations = ++currentIterations;

        if (game.getCurrentPlayer() == maxColor) {
            value = 1000;
        } else {
            value = -1000;
        }

        createTree();
    }

    public MinMaxNode(ChessGame game, int maxIterations) {
        this.game = game;
        this.maxColor = game.getCurrentPlayer();
        this.maxIterations = maxIterations;
        createTree();
    }

    public void createTree() {
        //Tree creation
        if (currentIterations < maxIterations) {
            ArrayList<Move> moves = game.getBoard().getAllValidMoves(game.getCurrentPlayer());

            for (Move move : moves) {
                ChessGame tempGame = (ChessGame)game.clone();
                tempGame.playMove(move);
                MinMaxNode node = new MinMaxNode(tempGame, maxColor, move, currentIterations);
                possibleMoves.add(node);
            }
            if (game.getCurrentPlayer() == maxColor) {
                value = Math.max(game.differenceInAdvantage() - 25, value);
            } else {
                value = Math.min(-game.differenceInAdvantage() + 25, value);
            }
        } else {
            if (currentIterations % 2 == 0) {
                value = game.differenceInAdvantage();
            } else {
                value = -game.differenceInAdvantage();
            }
        }
    }

    public Move bestMove() {
        Move result = null;
        double bestValue = -1000;
        for (MinMaxNode possibleMove : possibleMoves) {
            if (possibleMove.value > bestValue) {
                result = possibleMove.move;
                bestValue = possibleMove.value;
            }
        }
        return result;
    }
}
