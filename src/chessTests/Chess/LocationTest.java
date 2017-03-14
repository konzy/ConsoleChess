package Chess;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by Elizabeth on 2/28/2017.
 */
public class LocationTest {
    private Location location1;
    private Location location2; //same as 1
    private Location location3; //different than 1 & 2
    private Location clone; //for clone test
    @Before
    public void setUp() throws Exception {
        location1 = new Location(1,0);
        location2 = new Location(1,0);
        location3 = new Location(0,1);
    }

    @After
    public void tearDown() throws Exception {
        Location location1 = null;
        Location location2 = null;
        Location location3 = null;
        Location clone = null;
    }

    @Test
    public void equals() throws Exception {
        Assert.assertEquals(location1.equals(location2), true);
        Assert.assertEquals(location1.equals(location3), false);
    }

    @Test
    public void x() throws Exception {
        Assert.assertEquals(location1.X(),1);
        Assert.assertEquals(location3.X(), 0);
    }

    @Test
    public void y() throws Exception {
        Assert.assertEquals(location1.Y(),0);
        Assert.assertEquals(location3.Y(), 1);
    }

    @Test
    public void testClone() throws Exception {
        clone = (Location)location1.clone();
        Assert.assertNotSame(location1,clone);

        Assert.assertEquals(clone.equals(location1),true);
        Assert.assertEquals(clone.equals(location3),false);
        Assert.assertEquals(clone.X(),location1.X());
        Assert.assertEquals(clone.Y(),location1.Y());
    }

    @Test
    public void equals1() throws Exception {
        Assert.assertEquals(location1.equals(location2), true);
        Assert.assertEquals(location1.equals(location3), false);
    }

    @Test
    public void testToString() throws Exception {
        Assert.assertEquals(location1.toString(), "B8");
        Assert.assertEquals(location3.toString(), "A7");
    }

}