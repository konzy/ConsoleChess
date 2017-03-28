package Chess;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by Jakob on 3/14/2017.
 */
public class StatCollectionTest {
    private StatCollection stats = new StatCollection();

    @Before
    public void setUp() throws Exception {

    }

    @After
    public void tearDown() throws Exception {
        stats = null;
    }

    @Test
    public void getWhiteMoves() throws Exception {
        Assert.assertEquals(stats.getWhiteMoves(), 0);
    }

    @Test
    public void incWhiteMoves() throws Exception {
        stats.incWhiteMoves();
        Assert.assertEquals(stats.getWhiteMoves(), 1);
    }

    @Test
    public void decWhiteMoves() throws Exception {
        stats.incWhiteMoves();
        stats.decWhiteMoves();
        Assert.assertEquals(stats.getWhiteMoves(), 0);
    }

    @Test
    public void getBlackMoves() throws Exception {
        Assert.assertEquals(stats.getBlackMoves(), 0);
    }

    @Test
    public void incBlackMoves() throws Exception {
        stats.incBlackMoves();
        Assert.assertEquals(stats.getBlackMoves(), 1);
    }

    @Test
    public void decBlackMoves() throws Exception {
        stats.incBlackMoves();
        stats.decBlackMoves();
        Assert.assertEquals(stats.getBlackMoves(), 0);
    }

    @Test
    public void getWin() throws Exception {
        Assert.assertEquals(stats.getWin(), 0);
    }

    @Test
    public void incWin() throws Exception {
        stats.incWin();
        Assert.assertEquals(stats.getWin(), 1);
    }

    @Test
    public void getLoss() throws Exception {
        Assert.assertEquals(stats.getLoss(), 0);
    }

    @Test
    public void incLoss() throws Exception {
        stats.incLoss();
        Assert.assertEquals(stats.getLoss(), 1);
    }

    @Test
    public void getWhiteTime() throws Exception{
        Assert.assertEquals(stats.getWhiteTime(), 0);
    }

    @Test
    public void incWhiteTime() throws Exception{
        stats.incWhiteTime();
        stats.incWhiteTime(10);
        Assert.assertEquals(stats.getWhiteTime(), 11);
    }

    @Test
    public void decWhiteTime() throws Exception{
        stats.incWhiteTime(11);
        stats.decWhiteTime();
        stats.decWhiteTime(10);
        Assert.assertEquals(stats.getWhiteTime(), 0);
    }

    @Test
    public void getBlackTime() throws Exception{
        Assert.assertEquals(stats.getBlackTime(), 0);
    }

    @Test
    public void incBlackTime() throws Exception{
        stats.incBlackTime();
        stats.incBlackTime(10);
        Assert.assertEquals(stats.getBlackTime(), 11);
    }

    @Test
    public void decBlackTime() throws Exception{
        stats.incBlackTime(11);
        stats.decBlackTime();
        stats.decBlackTime(10);
        Assert.assertEquals(stats.getBlackTime(), 0);
    }

    @Test
    public void getWhiteCaptures() throws Exception{
        Assert.assertEquals(stats.getWhiteCaptures(),0);
    }

    @Test
    public void incWhiteCapture() throws Exception{
        stats.incWhiteCapture();
        Assert.assertEquals(stats.getWhiteCaptures(), 1);
    }

    @Test
    public void decWhiteCapture() throws Exception{
        stats.incWhiteCapture();
        stats.decWhiteCapture();
        Assert.assertEquals(stats.getWhiteCaptures(),0);
    }

    @Test
    public void getBlackCaptures() throws Exception{
        Assert.assertEquals(stats.getBlackCaptures(),0);
    }

    @Test
    public void incBlackCapture() throws Exception{
        stats.incBlackCapture();
        Assert.assertEquals(stats.getBlackCaptures(),1);
    }

    @Test
    public void decBlackCapture() throws Exception{
        stats.incBlackCapture();
        stats.decBlackCapture();
        Assert.assertEquals(stats.getBlackCaptures(),0);
    }

    @Test
    public void getGames() throws Exception{
        Assert.assertEquals(stats.getGames(),0);
    }

    @Test
    public void incGames() throws Exception{
        stats.incGames();
        Assert.assertEquals(stats.getGames(),1);
    }

    @Test
    public void getCPUGames() throws Exception{
        Assert.assertEquals(stats.getCPUGames(),0);
    }

    @Test
    public void incCPU() throws Exception{
        stats.incCPU();
        Assert.assertEquals(stats.getCPUGames(),1);
    }

    @Test
    public void getMovesUndone() throws Exception{
        Assert.assertEquals(stats.getMovesUndone(),0);
    }

    @Test
    public void incUndo() throws Exception{
        stats.incUndo();
        Assert.assertEquals(stats.getMovesUndone(),1);
    }

    @Test
    public void getWinPercent() throws Exception{
        Assert.assertEquals(stats.getWinPercent(),100,0);
    }

    @Test
    public void resetStats() throws Exception{
        stats.incBlackCapture();
        stats.resetStats();
        Assert.assertEquals(stats.getBlackCaptures(),0);
    }

    @Test
    public void storeRetrieveStats() throws Exception{
        stats.incWhiteTime(123);
        stats.storeData();
        stats.clearStats();
        stats.retrieveData();
        Assert.assertEquals(stats.getWhiteTime(),123);
    }

    @Test
    public void clearStats() throws Exception{
        stats.incWhiteTime(17258175);
        stats.storeData();
        stats.resetStats();
        stats.retrieveData();
        Assert.assertEquals(stats.getWhiteTime(), 0);
    }
}