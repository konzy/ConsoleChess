package Data;

import java.io.*;

/**
 * Replays all moves from the start of the game using the current autosave file as reference
 */
public class Replay {
    public static void replay(){
        File loadFile = new File("C:\\Users\\Ryan\\Documents\\GitHub\\ConsoleChess\\src\\Data\\AutoSave.txt");

        BufferedReader input = null;
        try {
			/* FileInputStream to read streams */
            input = new BufferedReader(new FileReader(loadFile));
            String line;
            String[] lineArray;
            int count = 1;
            while((line = input.readLine()) != null){
                lineArray = line.split("\\]");
                for(int i = 0; i < lineArray.length; i = i+2){
                    System.out.print("[" + lineArray[i].substring(1,3) + "]");
                }
                System.out.println("");
                if(count%8 == 0) {
                    try {
                        System.out.println("");
                        Thread.sleep(5000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                count++;
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (null != input) {
                try {
                    input.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
