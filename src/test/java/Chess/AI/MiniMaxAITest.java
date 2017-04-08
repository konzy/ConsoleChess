package Chess.AI;

import Chess.ChessGame;
import Chess.Move;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by konzy on 4/2/2017.
 */
public class MiniMaxAITest {

    private ChessGame game;

    @Before
    public void setUp() throws Exception {
        game = new ChessGame();

    }

    @After
    public void tearDown() throws Exception {
        game = null;

    }

    @Test
    public void getNextMove() throws Exception {

        for (int i = 0; i < 10; i++) {
            MiniMaxAI ai = new MiniMaxAI(game);
            Move move = ai.getNextMove();
            game.playMove(move);
        }

    }

}