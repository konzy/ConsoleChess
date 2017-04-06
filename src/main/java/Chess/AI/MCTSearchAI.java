package Chess.AI;

import Chess.ChessGame;
import Chess.Move;
import Chess.Pieces.ChessPiece;
import GUI.GameBoard;

import java.util.ArrayList;

/**
 * Created by konzy on 3/28/2017.
 */
public class MCTSearchAI extends BaseAI {

    private MCTNode root;
    private static final int MAX_DEPTH = 50;

    public MCTSearchAI(ChessGame game) {
        super(game);
    }

    public MCTSearchAI(ChessGame game, long seed) {
        super(game, seed);
    }

    @Override
    public Move getNextMove() {
        ChessGame clone = (ChessGame) clonedGame.clone();
        root = new MCTNode(clone, null, null);

        for (int i = 0; i < 1000; i++) {
            expand();
            System.out.println(i);
        }
        return getNextBestMove();
    }

    private void expand() {
        MCTNode bestNode = root.getHighestUpperConfidence();
        Move randomMove = selection(bestNode);

        ChessGame childGame = (ChessGame) clonedGame.clone();
        childGame.playMove(randomMove);
        MCTNode childNode = new MCTNode(childGame, bestNode, randomMove);
        bestNode.addChild(childNode);


        ChessGame.GameState state = randomDepthChargeState(childNode);
        bestNode.incrementDepthCharge(state, randomMove);

    }


    private Move selection(MCTNode node) {

        Move move = node.validMoves.get(Math.abs(random.nextInt()) % node.getRemainingMoves());

        return move;
    }

    private Move getNextBestMove() {
        MCTNode node = root.getHighestUpperConfidence();
        while(node.parent != root) {
            node = node.parent;
        }
        return node.previousMove;
    }

    private ChessGame.GameState randomDepthChargeState(MCTNode node) {
        ChessGame game = node.getCurrentGame();
        RandomAI ai = new RandomAI(game);
        ChessGame.GameState currentState;
        int turns = 0;
        Move move;

        do {
            move = ai.getNextMove();

            game.playMove(move);

            currentState = game.getState();
            turns++;
        } while (currentState == ChessGame.GameState.PLAY && turns < MAX_DEPTH && move != null);

        if (turns == MAX_DEPTH) {
            //System.out.println("max depth");
            return ChessGame.GameState.STALEMATE;
        }
        return currentState;
    }


    class MCTNode {
        private int wins = 0;
        private int total = 0;
        private int ourMoves = 0;
        private ArrayList<MCTNode> children = new ArrayList<>();
        private ChessGame currentGame;
        private ArrayList<Move> validMoves = new ArrayList<>();
        private MCTNode parent;
        private Move previousMove;

        public MCTNode(ChessGame currentGame, MCTNode parent, Move previousMove) {
            this.currentGame = (ChessGame) currentGame.clone();
            this.parent = parent;
            this.previousMove = previousMove;
            this.validMoves = currentGame.getAllValidMoves(currentGame.getCurrentPlayer());
        }

        public int getRemainingMoves() {
            return validMoves.size();
        }

        public void incrementDepthCharge(ChessGame.GameState state, Move move) {
            validMoves.remove(move);
            ourMoves++;
            ChessPiece.PieceColor pieceColor = move.getPiece().getColor();
            ChessPiece.PieceColor playerColor = currentGame.getCurrentPlayer();
            if (state.equals(ChessGame.GameState.CHECKMATE) && pieceColor == playerColor) {
                System.out.println("win");
                incrementWins();
            } else {
                incrementLosses();
            }
        }

        public MCTNode findValidNodeToExpand() {
            return findValidNodeToExpandHelper(root);
        }

        private MCTNode findValidNodeToExpandHelper(MCTNode node) {
            if (node != null && node.getRemainingMoves() == 0) {
                node = findValidNodeToExpandHelper(node.children.get(Math.abs(random.nextInt(node.children.size()))));
            }
            return node;
        }

        public MCTNode getHighestUpperConfidence() {
            MCTNode node = findValidNodeToExpand();
            MCTNode confidentNode = getHighestUpperConfidenceHelper(node, node);
            if (confidentNode == null) {
                return node;
            }
            return confidentNode;

        }

        private MCTNode getHighestUpperConfidenceHelper(MCTNode node, MCTNode bestNode) {
            if (node.children == null || node.children.isEmpty()) {
                return bestNode;
            }

            for (MCTNode child : node.children) {
                if (child.getUpperConfidence() > bestNode.getUpperConfidence()) {
                    bestNode = child;
                }
                bestNode = getHighestUpperConfidenceHelper(child, bestNode);
            }
            return bestNode;
        }

        private double getUpperConfidence() {
            return getWinPercentage() + Math.sqrt(2) * Math.sqrt(Math.log(ourMoves/(total + 1.0)));
        }

        public MCTNode getHighestPercentage() {
            return getHighestPercentageHelper(root, root);
        }

        private MCTNode getHighestPercentageHelper(MCTNode node, MCTNode bestNode) {
            if (node.children == null || node.children.isEmpty()) {
                return bestNode;
            }
            for (MCTNode child : node.children) {
                if (child.getWinPercentage() > bestNode.getWinPercentage()) {
                    bestNode = child;
                }
                bestNode = getHighestPercentageHelper(child, bestNode);
            }
            return bestNode;
        }

        private double getWinPercentage() {
            return (((double) wins) / total);
        }

        private void incrementWins() {
            wins++;
            total++;
            if (parent != null) { //backpropagation
                parent.incrementWins();
            }
        }

        private void incrementLosses() {
            total++;
            if (parent != null) { //backpropagation
                parent.incrementLosses();
            }
        }

        public void addChild(MCTNode child) {
            children.add(child);

        }

        public int getWins() {
            return wins;
        }

        public int getTotal() {
            return total;
        }

        public ArrayList<MCTNode> getChildren() {
            return children;
        }

        public ChessGame getCurrentGame() {
            return currentGame;
        }

        public MCTNode getParent() {
            return parent;
        }
    }

}
