package Chess;

import Chess.Pieces.ChessPiece;

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.util.List;

public class StatCollection{
    public static final String STATS_FILE_RELATIVE_PATH = "./chessStats.txt";

    private int whiteMoves = 0,
            blackMoves = 0,
            win = 0,
            loss = 0,
            whiteTime = 0,
            blackTime = 0,
            whiteCaptures = 0,
            blackCaptures = 0,
            games = 0,
            cpuGames = 0,
            movesUndone = 0;

    private File statsFile;

    public StatCollection() {
        statsFile = new File(STATS_FILE_RELATIVE_PATH);
        try {
            // create a new file if needed
            if (!statsFile.createNewFile()) {
                //if it already exists, load data.
                retrieveData();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void incWhiteMoves(){
        whiteMoves++;
    }

    public void decWhiteMoves(){
        whiteMoves--;
    }

    public int getWhiteMoves(){
        return whiteMoves;
    }

    public void incBlackMoves(){
        blackMoves++;
    }

    public void decBlackMoves(){
        blackMoves--;
    }

    public int getBlackMoves(){
        return blackMoves;
    }

    public void incWin(){
        win++;
    }

    public void incLoss(){
        loss++;
    }

    public int getWin(){
        return win;
    }

    public int getLoss(){
        return loss;
    }

    public void incWhiteTime(){
        whiteTime++;
    }

    public void decWhiteTime(){
        whiteTime--;
    }

    public void incWhiteTime(int time){
        whiteTime += time;
    }

    public void decWhiteTime(int time){
        whiteTime -= time;
    }

    public int getWhiteTime(){
        return whiteTime;
    }

    public void incBlackTime(){
        blackTime++;
    }

    public void decBlackTime(){
        blackTime--;
    }

    public void incBlackTime(int time){
        blackTime += time;
    }

    public void decBlackTime(int time){
        blackTime -= time;
    }

    public int getBlackTime(){
        return blackTime;
    }

    public void incWhiteCapture(){
        whiteCaptures++;
    }

    public void decWhiteCapture(){
        whiteCaptures--;
    }

    public int getWhiteCaptures(){
        return whiteCaptures;
    }

    public void incBlackCapture(){
        blackCaptures++;
    }

    public void decBlackCapture(){
        blackCaptures--;
    }

    public int getBlackCaptures(){
        return blackCaptures;
    }

    public void incGames(){
        games++;
        storeData();
    }

    public int getGames(){
        return games;
    }

    public void incCPU(){
        cpuGames++;
        storeData();
    }

    public int getCPUGames(){
        return cpuGames;
    }

    public void incUndo(){
        movesUndone++;
    }

    public int getMovesUndone(){
        return movesUndone;
    }

    public void updateFromGameState(ChessGame previousGame, ChessGame currentGame, boolean shouldDecrement){
        ChessPiece.PieceColor color = previousGame.getCurrentPlayer();
        int value = 1;

        // undo
        if (shouldDecrement) {
            //color = ChessPiece.opponentOf(color);
            value = -1;
            movesUndone++;
        }

        // move
        if (color.equals(ChessPiece.PieceColor.White)) {
            whiteMoves += value;
        } else {
            blackMoves += value;
        }

        // capture
        if (previousGame.getBoard().getBoardArrayList().size() > currentGame.getBoard().getBoardArrayList().size()) {
            if (color.equals(ChessPiece.PieceColor.White)) {
                whiteCaptures += value;
            } else {
                blackCaptures += value;
            }
        }

        // win / loss
        if (currentGame.getState().equals(ChessGame.GameState.CHECKMATE)) {
            if (color.equals(ChessPiece.PieceColor.White)) {
                win++;
            } else {
                loss++;
            }
        }
        storeData();
    }

    public double getWinPercent(){

        if(this.getWin() + this.getLoss() <= 0){
            return 100;
        }
        else {
            return 100 * ((double) this.getWin() / ((double) this.getWin() + (double) this.getLoss()));
        }
    }

    public void resetStats(){
        whiteMoves = 0;
        blackMoves = 0;
        win = 0;
        loss = 0;
        whiteTime = 0;
        blackTime = 0;
        whiteCaptures = 0;
        blackCaptures = 0;
        games = 0;
        cpuGames = 0;
        movesUndone = 0;
        storeData();
    }

    public void clearStats(){
        whiteMoves = 0;
        blackMoves = 0;
        win = 0;
        loss = 0;
        whiteTime = 0;
        blackTime = 0;
        whiteCaptures = 0;
        blackCaptures = 0;
        games = 0;
        cpuGames = 0;
        movesUndone = 0;
    }

    public void storeData(){

        try (BufferedWriter writer = Files.newBufferedWriter(statsFile.toPath())) {
            writer.write(String.valueOf(whiteMoves));
            writer.newLine();
            writer.write(String.valueOf(blackMoves));
            writer.newLine();
            writer.write(String.valueOf(win));
            writer.newLine();
            writer.write(String.valueOf(loss));
            writer.newLine();
            writer.write(String.valueOf(whiteTime));
            writer.newLine();
            writer.write(String.valueOf(blackTime));
            writer.newLine();
            writer.write(String.valueOf(whiteCaptures));
            writer.newLine();
            writer.write(String.valueOf(blackCaptures));
            writer.newLine();
            writer.write(String.valueOf(games));
            writer.newLine();
            writer.write(String.valueOf(cpuGames));
            writer.newLine();
            writer.write(String.valueOf(movesUndone));
            writer.newLine();
            writer.write("The above numbers are whiteMoves, blackMoves, win, loss, whiteTime, blackTime, whiteCaptures, blackCaptures, games, cpuGames, movesUndone");
        }
        catch(IOException e){
            e.printStackTrace();
            System.out.println("IO Exception on write to stat file");
        }
    }

    public void retrieveData(){
        try {
            List<String> lines = Files.readAllLines(statsFile.toPath());
            whiteMoves = Integer.parseInt(lines.get(0));
            blackMoves = Integer.parseInt(lines.get(1));
            win = Integer.parseInt(lines.get(2));
            loss = Integer.parseInt(lines.get(3));
            whiteTime = Integer.parseInt(lines.get(4));
            blackTime = Integer.parseInt(lines.get(5));
            whiteCaptures = Integer.parseInt(lines.get(6));
            blackCaptures = Integer.parseInt(lines.get(7));
            games = Integer.parseInt(lines.get(8));
            cpuGames = Integer.parseInt(lines.get(9));
            movesUndone = Integer.parseInt(lines.get(10));
        } catch(IOException e){
            e.printStackTrace();
            System.out.println("IO Exception on reading stat file");
        }
    }
}