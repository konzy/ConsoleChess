package Data;

import Chess.ChessBoard;
import Chess.ChessGame;
import Chess.Pieces.*;
import Console.BoardDisplay;

import java.io.*;

/**
 * Loads files from a static txt file, starting on the turn where the players left off on and puts the current moves
 *  into the autosave for replay purposes.
 */
public class Load {
    public static ChessBoard Load(String str, ChessGame game) {
        File loadFile = new File("C:\\Users\\Ryan\\Documents\\GitHub\\ConsoleChess\\src\\Data\\" + str + ".txt");

        BufferedReader input = null;
        ChessBoard board = null;
        try {
            /* FileInputStream to read streams */
            input = new BufferedReader(new FileReader(loadFile));
            String line;
            String[] lineArray;
            int count = 0;
            board = game.getBoard();

            while ((line = input.readLine())!=null) {
                if(count == 0){
                    for(int y = 0; y < 8; y++){
                        for(int x = 0; x < 8; x++){
                            board.board[y][x].empty();
                        }
                    }
                }

                lineArray = line.split("\\]");

                for (int i = 0; i < lineArray.length; i = i + 2) {
                    ChessPiece.PieceColor color;
                    if (lineArray[i + 1].equals("Bl")) {
                        color = ChessPiece.PieceColor.Black;
                    } else {
                        color = ChessPiece.PieceColor.White;
                    }
                    ChessPiece piece = null;
                    switch (lineArray[i].substring(1,3)) {
                        case "Pa":
                            piece = new Pawn(color);
                            break;
                        case "Kn":
                            piece = new Knight(color);
                            break;
                        case "Bi":
                            piece = new Bishop(color);
                            break;
                        case "Qu":
                            piece = new Queen(color);
                            break;
                        case "Ki":
                            piece = new King(color);
                            break;
                        case "Ro":
                            piece = new Rook(color);
                            break;
                    }
                    if (piece != null) {
                        board.board[count][i / 2].setPiece(piece);
                    }
                }
                count++;
                if(count>7){
                    count =0;
                }
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
            try {
                Save.Save(str,"AutoSave");
            } catch (IOException e) {
                e.printStackTrace();
            }
            BoardDisplay.printBoard(board);
            return board;
        }
    }
}