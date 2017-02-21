package Chess;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.*;
import java.util.List;

public class StatCollection{
    int whiteMoves = 0, blackMoves = 0, win = 0, loss = 0, whiteTime = 0, blackTime = 0, whiteCaptures = 0, blackCaptures = 0, games = 0, cpuGames = 0, movesUndone = 0;

    void incWhiteMoves(){
        whiteMoves++;
    }

    void decWhiteMoves(){
        whiteMoves--;
    }

    int getWhiteMoves(){
        return whiteMoves;
    }

    void incBlackMoves(){
        blackMoves++;
    }

    void decBlackMoves(){
        blackMoves--;
    }

    int getBlackMoves(){
        return blackMoves;
    }

    void incWin(){
        win++;
    }

    void incLoss(){
        loss++;
    }

    int getWin(){
        return win;
    }

    int getLoss(){
        return loss;
    }

    void incWhiteTime(){
        whiteTime++;
    }

    void decWhiteTime(){
        whiteTime--;
    }

    void incWhiteTime(int time){
        whiteTime += time;
    }

    void decWhiteTime(int time){
        whiteTime -= time;
    }

    int getWhiteTime(){
        return whiteTime;
    }

    void incBlackTime(){
        blackTime++;
    }

    void decBlackTime(){
        blackTime--;
    }

    void incBlackTime(int time){
        blackTime += time;
    }

    void decBlackTime(int time){
        blackTime -= time;
    }

    int getBlackTime(){
        return blackTime;
    }

    void incWhiteCapture(){
        whiteCaptures++;
    }

    void decWhiteCapture(){
        whiteCaptures--;
    }

    int getWhiteCaptures(){
        return whiteCaptures;
    }

    void incBlackCapture(){
        blackCaptures++;
    }

    void decBlackCapture(){
        blackCaptures--;
    }

    int getBlackCaptures(){
        return blackCaptures;
    }

    void incGames(){
        games++;
    }

    int getGames(){
        return games;
    }

    void incCPU(){
        cpuGames++;
    }

    int getCPUGames(){
        return cpuGames;
    }

    void incUndo(){
        movesUndone++;
    }

    int getMovesUndone(){
        return movesUndone;
    }

    double getWinPercent(){
        double percent = 100 * ((double) getWin() / ((double) getWin() + (double) getLoss()));
        return percent;
    }

    void resetStats(){
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

    void storeData(){
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
            System.out.println("IO Exception");
        }
    }

    void retrieveData(){
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
            System.out.println("IO Exception");
        }
    }
}