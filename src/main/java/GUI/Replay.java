package GUI;

import Chess.ChessBoard;
import Chess.ChessGame;
import Chess.Location;
import Chess.Pieces.*;
import Data.Save;

import java.io.*;
import java.util.ArrayList;

import static Data.FileConstants.FILE_LOCATOR;
import static Data.Load.PIECE_MOVED_REGEX;
import static Data.Load.WHITE_PIECE_REGEX;

/**
 * Replays all moves from the start of the game using the current autosave file as reference
 */
public class Replay {
    public static ChessGame undoMove(int i,ChessGame game){
        File loadFile = new File(FILE_LOCATOR.toString() +"/resources/main/AutoSave.txt");
        ArrayList<ChessPiece> pieces = new ArrayList<>();
        BufferedReader input = null;
        ChessPiece.PieceColor currentPlayer = ChessPiece.PieceColor.White;
        boolean isTwoPlayer = true;
        int count = 0;

        try {
            File tmp = File.createTempFile("tmp", ".txt");
            File redotmp = File.createTempFile("redotmp", ".txt");
            BufferedWriter bw1 = new BufferedWriter(new FileWriter(tmp));
            BufferedWriter bw2 = new BufferedWriter(new FileWriter(redotmp,true));
            /* FileInputStream to read streams */
            input = new BufferedReader(new FileReader(loadFile));
            String line;
            String[] lineArray;
            int y = 0;
            while ((line = input.readLine()) != null) {
                if (count < i * 11) {
                    bw1.write(line.toString());
                    bw1.newLine();
                    bw1.flush();
                    if (y == 0) {
                        pieces = new ArrayList<>();
                    }

                    if (y == 8) {
                        if (line.equals(ChessPiece.PieceColor.White.name())) {
                            currentPlayer = ChessPiece.PieceColor.White;
                        } else {
                            currentPlayer = ChessPiece.PieceColor.Black;
                        }
                    } else if (y == 9) {
                        isTwoPlayer = Boolean.valueOf(line);
                    } else if (y == 10) {
                        //blank on purpose
                    } else {
                        lineArray = line.split("\\]");
                        int x = 0;
                        for (String tile : lineArray) {
                            Location location = new Location(x, y);

                            ChessPiece.PieceColor color = ChessPiece.PieceColor.Black;
                            if (tile.substring(1, 2).matches(WHITE_PIECE_REGEX)) {
                                color = ChessPiece.PieceColor.White;
                            }

                            boolean hasMoved = false;
                            if (tile.substring(2, 3).matches(PIECE_MOVED_REGEX)) {
                                hasMoved = true;
                            }

                            String letter = tile.substring(1, 2).toUpperCase();

                            ChessPiece piece = null;
                            switch (letter) {
                                case Pawn.LETTER:
                                    piece = new Pawn(color, location);
                                    break;
                                case Knight.LETTER:
                                    piece = new Knight(color, location);
                                    break;
                                case Bishop.LETTER:
                                    piece = new Bishop(color, location);
                                    break;
                                case Queen.LETTER:
                                    piece = new Queen(color, location);
                                    break;
                                case King.LETTER:
                                    piece = new King(color, location);
                                    break;
                                case Rook.LETTER:
                                    piece = new Rook(color, location);
                                    break;
                            }
                            if (piece != null) {
                                piece.setHasMoved(hasMoved);
                                pieces.add(piece);
                            }
                            x++;
                        }
                    }
                    y = (y + 1) % 11;
                    count++;
                } else {
                    bw2.write(line);
                    bw2.newLine();
                    bw2.flush();
                }
            }
            bw1.flush();
            bw1.close();
            bw2.flush();
            bw2.close();

            File redo = new File(FILE_LOCATOR.toString() + "/resources/main/redo.txt");
            if(!redo.exists()){
                redo.createNewFile();
            }
            InputStream redoIn = new FileInputStream(redo);
            OutputStream redoOut = new FileOutputStream(redo,true);
            InputStream redoTmpIn = new FileInputStream(redotmp);
            OutputStream redoTmpOut = new FileOutputStream(redotmp, true);

            byte[] buf = new byte[1024];
            int bytesRead;

            while ((bytesRead = redoIn.read(buf)) > 0) {
                redoTmpOut.write(buf, 0, bytesRead);
            }
            redoTmpOut.close();
            redoIn.close();

            clearRedo();
            buf = new byte[1024];
            while ((bytesRead = redoTmpIn.read(buf)) > 0) {
                    redoOut.write(buf, 0, bytesRead);
            }
            redoOut.close();
            redoTmpIn.close();

             /* FileInputStream to read streams */
            InputStream tmpStream = new FileInputStream(tmp);
            /* FileOutputStream to write streams */
            OutputStream output = new FileOutputStream(loadFile);

            Save.clearAutoSave();
            buf = new byte[1024];

            while ((bytesRead = tmpStream.read(buf)) > 0) {
                output.write(buf, 0, bytesRead);
            }
            output.flush();
            input.close();
            output.close();

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        ChessBoard chessBoard = new ChessBoard(pieces);
        game = new ChessGame(chessBoard, isTwoPlayer);
        game.setCurrentPlayer(currentPlayer);
        game.setMoveCount(i);
        return game;
    }

    public static ChessGame redoMove(int i,ChessGame game) {
        File redo = new File(FILE_LOCATOR.toString() +"/resources/main/redo.txt");
        ArrayList<ChessPiece> pieces = new ArrayList<>();
        BufferedReader input = null;
        ChessPiece.PieceColor currentPlayer = ChessPiece.PieceColor.White;
        boolean isTwoPlayer = true;
        int count = 0;

        try {
            File tmp = File.createTempFile("tmp", ".txt");
            File loadFile = new File(FILE_LOCATOR.toString() + "/resources/main/AutoSave.txt");
            BufferedWriter bw1 = new BufferedWriter(new FileWriter(loadFile, true));
            BufferedWriter bw2 = new BufferedWriter(new FileWriter(tmp));
            /* FileInputStream to read streams */
            input = new BufferedReader(new FileReader(redo));
            String line;
            String[] lineArray;
            int y = 0;
            while ((line = input.readLine()) != null) {

                if (count < 22) {
                    bw1.write(line.toString());
                    bw1.newLine();
                    if (y == 0) {
                        pieces = new ArrayList<>();
                    }

                    if (y == 8) {
                        if (line.equals(ChessPiece.PieceColor.White.name())) {
                            currentPlayer = ChessPiece.PieceColor.White;
                        } else {
                            currentPlayer = ChessPiece.PieceColor.Black;
                        }
                    } else if (y == 9) {
                        isTwoPlayer = Boolean.valueOf(line);
                    } else if (y == 10) {
                        //blank on purpose
                    } else {
                        lineArray = line.split("\\]");
                        int x = 0;
                        for (String tile : lineArray) {
                            Location location = new Location(x, y);

                            ChessPiece.PieceColor color = ChessPiece.PieceColor.Black;
                            if (tile.substring(1, 2).matches(WHITE_PIECE_REGEX)) {
                                color = ChessPiece.PieceColor.White;
                            }

                            boolean hasMoved = false;
                            if (tile.substring(2, 3).matches(PIECE_MOVED_REGEX)) {
                                hasMoved = true;
                            }

                            String letter = tile.substring(1, 2).toUpperCase();

                            ChessPiece piece = null;
                            switch (letter) {
                                case Pawn.LETTER:
                                    piece = new Pawn(color, location);
                                    break;
                                case Knight.LETTER:
                                    piece = new Knight(color, location);
                                    break;
                                case Bishop.LETTER:
                                    piece = new Bishop(color, location);
                                    break;
                                case Queen.LETTER:
                                    piece = new Queen(color, location);
                                    break;
                                case King.LETTER:
                                    piece = new King(color, location);
                                    break;
                                case Rook.LETTER:
                                    piece = new Rook(color, location);
                                    break;
                            }
                            if (piece != null) {
                                piece.setHasMoved(hasMoved);
                                pieces.add(piece);
                            }
                            x++;
                        }
                    }
                    y = (y + 1) % 11;
                    count++;
                } else {
                    bw2.write(line);
                    bw2.newLine();
                    bw2.flush();
                }
            }
            bw1.flush();
            bw1.close();
            bw2.flush();
            bw2.close();

             /* FileInputStream to read streams */
            InputStream tmpStream = new FileInputStream(tmp);
            /* FileOutputStream to write streams */
            OutputStream output = new FileOutputStream(redo);

            clearRedo();
            byte[] buf = new byte[1024];
            int bytesRead;

            while ((bytesRead = tmpStream.read(buf)) > 0) {
                output.write(buf, 0, bytesRead);
            }
            output.flush();
            input.close();
            output.close();

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        ChessBoard chessBoard = new ChessBoard(pieces);
        game = new ChessGame(chessBoard, isTwoPlayer);
        game.setCurrentPlayer(currentPlayer);
        game.setMoveCount(i + 2);
        return game;
    }

    public static void clearRedo(){
        BufferedWriter writer;
        File redo = new File(FILE_LOCATOR.toString() + "/resources/main/redo.txt");
        try {
            if(!redo.exists()){
                redo.createNewFile();
            } else {
                writer = new BufferedWriter(new
                        FileWriter(FILE_LOCATOR.toString() + "/resources/main/redo.txt"));
                writer.append("");
                writer.flush();
                writer.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
