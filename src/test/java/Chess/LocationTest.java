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
    public void Constructor() throws Exception {

        Location copy = new Location(location1);
        Assert.assertEquals(copy, location1);

        Location equal1 = new Location(1, 0);
        Location equal2 = new Location(1, 0);
        Assert.assertEquals(equal1, equal2);

    }

    @Test
    public void equals() throws Exception {
        Assert.assertEquals(location1, location2);
        Assert.assertNotEquals(location1, location3);
    }

    @Test
    public void testClone() throws Exception {
        clone = (Location)location1.clone();
        Assert.assertNotSame(location1, clone);

        Assert.assertEquals(clone, location1);
        Assert.assertNotEquals(clone, location3);
        Assert.assertEquals(clone.x, location1.x);
        Assert.assertEquals(clone.y, location1.y);
    }

    @Test
    public void equals1() throws Exception {
        Assert.assertEquals(location1, location2);
        Assert.assertNotEquals(location1, location3);
    }

    @Test
    public void testToString() throws Exception {
        Assert.assertEquals(location1.toString(), "B8");
        Assert.assertEquals(location3.toString(), "A7");
    }

}