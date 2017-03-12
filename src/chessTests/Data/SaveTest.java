package Data;

import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

import static org.junit.Assert.*;

/**
 * Created by Ryan on 3/11/2017.
 */
public class SaveTest {
    @Test
    public void clearAutoSave() throws Exception {
        String expected = "";
        Save.clearAutoSave();
        File autoSaveFile = new File("C:\\Users\\Ryan\\Documents\\GitHub\\ConsoleChess\\" +
                "src\\Data\\Autosave.txt");
        InputStream inputAutosave = new FileInputStream(autoSaveFile);
        String resultStr = "";
        int bytesRead;
        while((bytesRead = inputAutosave.read(new byte[1024])) > 0) {
            resultStr = resultStr + bytesRead;
        }
        assertEquals(expected,resultStr);
    }

    @Test
    public void autoSave() throws Exception {

    }
//
//    @Test
//    public void save() throws Exception {
//
//    }

}