package Data;

import Chess.ChessBoard;
import Chess.Pieces.ChessPiece;
import Chess.Tile;

import java.io.*;

/**
 * Save system for replaying and retaining moves.
 */

public class Save {
    /**
     *
     */

    public enum Tags{
        BLACK("[B]"),
        WHITE("[W]"),
        BLANK("[ ]");
        private String value;
        Tags(String value){
                this.value = value;
        }

        public String getValue() {
            return value;
        }
    }
    public static void clearAutoSave(){
        BufferedWriter writer;
        try {
            writer = new BufferedWriter(new
                    FileWriter("C:\\Users\\Ryan\\Documents\\GitHub\\ConsoleChess\\src\\Data\\AutoSave.txt"));
            writer.append("");
            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void AutoSave(ChessBoard board) throws IOException {
        BufferedWriter autoSaveFile = new BufferedWriter(
                new FileWriter("C:\\Users\\Ryan\\Documents\\GitHub\\ConsoleChess\\src\\Data\\AutoSave.txt",
                        true));
        Tile[][] currentBoard = board.getBoardArray();
        for(int i = 0; i < currentBoard.length; i++){
            for(int x = 0; x < currentBoard[i].length; x++) {
                autoSaveFile.append(currentBoard[i][x].toString());
                if(!currentBoard[i][x].isEmpty()) {
                    if (currentBoard[i][x].getPiece().color() == ChessPiece.PieceColor.Black) {
                        autoSaveFile.append(Tags.BLACK.getValue());
                    } else if (currentBoard[i][x].getPiece().color() == ChessPiece.PieceColor.White) {
                        autoSaveFile.append(Tags.WHITE.getValue());
                    }
                } else {
                    autoSaveFile.append(Tags.BLANK.getValue());
                }
                if(x == 7){
                    autoSaveFile.newLine();
                }
                autoSaveFile.flush();
            }
        }
        autoSaveFile.flush();
        autoSaveFile.close();
    }

    public static void Save(String fromStr,String toStr) throws IOException {
        File autoSaveFile = new File("C:\\Users\\Ryan\\Documents\\GitHub\\ConsoleChess\\src\\Data\\" + fromStr + ".txt");
        File saveFile = new File("C:\\Users\\Ryan\\Documents\\GitHub\\ConsoleChess\\src\\Data\\" + toStr + ".txt");
        if(!saveFile.exists()){
            saveFile.createNewFile();
        }
        try {
			/* FileInputStream to read streams */
            InputStream input = new FileInputStream(autoSaveFile);
			/* FileOutputStream to write streams */
            OutputStream output = new FileOutputStream(saveFile);

            byte[] buf = new byte[1024];
            int bytesRead;

            while ((bytesRead = input.read(buf)) > 0) {
                output.write(buf, 0, bytesRead);
            }
            output.flush();
            input.close();
            output.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}