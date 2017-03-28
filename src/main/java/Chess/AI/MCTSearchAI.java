package Chess.AI;

import Chess.ChessGame;
import Chess.Move;

import java.util.ArrayList;

/**
 * Created by konzy on 3/28/2017.
 */
public class MCTSearchAI extends BaseAI {

    private MCTNode root;



    public MCTSearchAI(ChessGame game) {
        super(game);
    }

    public MCTSearchAI(ChessGame game, long seed) {
        super(game, seed);
    }

    @Override
    public Move getNextMove() {
        root = new MCTNode(clonedGame);

        ArrayList<Move> moves = currentGame.getBoard().getAllValidMoves(currentGame.getCurrentPlayer());
        if (moves.size() == 0) {
            System.out.println("Something went Wrong. in MCTSearchAI");
            return null;
        }
        Move randomMove = moves.get(Math.abs(random.nextInt()) % moves.size());



        return null;
    }


    class MCTNode {
        private int wins = 0;
        private int losses = 0;
        private ArrayList<MCTNode> children = new ArrayList<>();
        private ChessGame currentGame;
        private ArrayList<Move> moves = new ArrayList<>();

        public MCTNode(ChessGame currentGame) {
            this.currentGame = (ChessGame) currentGame.clone();
        }

        public void incrementWins() {
            wins++;
        }

        public void incrementLosses() {
            losses--;
        }

        public boolean addMove(Move move) {
            if (!contains(move)) {
                moves.add(move);
                return true;
            }
            return false;
        }

        public boolean contains(Move move) {
            return moves.contains(move);
        }

        public int getWins() {
            return wins;
        }

        public int getLosses() {
            return losses;
        }

        public ArrayList<MCTNode> getChildren() {
            return children;
        }

        public ChessGame getCurrentGame() {
            return currentGame;
        }
    }

}
