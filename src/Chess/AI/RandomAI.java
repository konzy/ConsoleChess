package Chess.AI;

import Chess.ChessGame;
import Chess.CoordPair;
import java.util.ArrayList;

/**
 * Created by konzy on 2/6/2017.
 */

public class RandomAI extends BaseAI {
    public RandomAI(ChessGame game) {
        super(game);
    }

    @Override
    public CoordPair getNextMove() {
        ArrayList<CoordPair> pairs = availableMoves();
        if (pairs.size() == 0) {
            System.out.println("wtf");
        }
        return pairs.get(random.nextInt() % pairs.size());
    }
}
