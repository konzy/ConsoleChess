package Chess;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**

 * Created by Jakob on 3/14/2017.

 */
public class TimerTest {
    private Timer timer = new Timer();

    @Before
    public void setUp() throws Exception{

    }

    @After
    public void tearDown() throws Exception{
        timer = null;
    }

    @Test
    public void getP1Time() throws Exception{
        Assert.assertEquals(timer.getP1Time(), 300);
    }

//    @Test
//    public void p1Timer() throws Exception{
//        timer.countDown();
//        Assert.assertNotEquals(timer.getP1Time(), 300);
//    }

    @Test
    public void getP2Time() throws Exception{
        Assert.assertEquals(timer.getP2Time(), 300);
    }

    @Test
    public void getCurrentTurn() throws Exception{
        Assert.assertEquals(timer.getCurrentTurn(), 0);
    }

    @Test
    public void turnSwitch() throws Exception{
        timer.turnSwitch();
        timer.turnSwitch();
        timer.turnSwitch();
        Assert.assertEquals(timer.getCurrentTurn(), 1);
    }

//    @Test
//    public void p2Timer() throws Exception{
//        timer.turnSwitch();
//        timer.countDown();
//        Assert.assertNotEquals(timer.getP2Time(), 300);
//    }

}