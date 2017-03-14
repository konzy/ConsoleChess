package Data;


import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * Created by Ryan on 3/12/2017.
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({TestLoad.class, TestSave.class})
public class SuiteData {
    @BeforeClass
    public static void setUpDataTestSuite(){

    }
}
