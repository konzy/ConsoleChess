package Chess;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.*;
import java.util.List;

public class StatCollection{
    private int whiteMoves = 0, blackMoves = 0, win = 0, loss = 0, whiteTime = 0, blackTime = 0, whiteCaptures = 0,
            blackCaptures = 0, games = 0, cpuGames = 0, movesUndone = 0;

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
    }

    public int getGames(){
        return games;
    }

    public void incCPU(){
        cpuGames++;
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

    public double getWinPercent(){
        return 100 * ((double) getWin() / ((double) getWin() + (double) getLoss()));
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

    public void storeData(){
        Path path = Paths.get("./chessStats.txt");
        try (BufferedWriter writer = Files.newBufferedWriter(path)) {
            writer.write(whiteMoves);
            writer.write(blackMoves);
            writer.write(win);
            writer.write(loss);
            writer.write(whiteTime);
            writer.write(blackTime);
            writer.write(whiteCaptures);
            writer.write(blackCaptures);
            writer.write(games);
            writer.write(cpuGames);
            writer.write(movesUndone);
            writer.write("The above numbers are whiteMoves, blackMoves, win, loss, whiteTime, blackTime, whiteCaptures, blackCaptures, games, cpuGames, movesUndone");
        }
        catch(IOException e){
            e.printStackTrace();
            System.out.println("IO Exception");
        }
    }

    public void retrieveData(){
        try {
            List<String> lines = Files.readAllLines(Paths.get("./chessStats.txt"));
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
        }
        catch(IOException e){
            e.printStackTrace();
            System.out.println("IO Exception");
        }
    }
}