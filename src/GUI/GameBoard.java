package GUI;

import Chess.ChessBoard;
import Chess.ChessGame;
import Chess.Location;
import Chess.Pieces.*;
import Console.BoardDisplay;
import Data.Load;
import Data.Save;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.scene.paint.Color;
import javafx.scene.image.ImageView;

import java.io.*;
import java.util.ArrayList;

/**
 * Created by Elizabeth on 1/26/2017.
 */
public class GameBoard extends Application {


    private ChessGame game;
    public GameBoard(ChessGame g){
        game = g;
    }
    private int firstClickX = -1;
    private int firstClickY = -1;
    private int secondClickX = -1;
    private int secondClickY = -1;

    @Override
    public void start(Stage primaryStage) throws Exception {

    }

    public void setBoard (Stage stage) throws Exception {
        String picPath = "/GUI/assets/";

        BorderPane borderPane = new BorderPane();
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(0,0,25,0));

        //set color of tiles
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                Rectangle rectangle = new Rectangle(80,80);
                setRectangleColor(rectangle,i,j);
                grid.add(rectangle,i,j);
            }
        }

        //set pieces
        ArrayList<ChessPiece> chessPieces = game.getBoard().getBoardArrayList();
        for (ChessPiece chessPiece : chessPieces) {
            String color = chessPiece.getColor().toString().toLowerCase();
            String letter =  chessPiece.getLetter().toLowerCase();
            ImageView tmpView = new ImageView(picPath + color + "_" + letter + ".png");
            tmpView.setFitHeight(80);
            tmpView.setFitWidth(80);
            grid.add(tmpView, chessPiece.getLocation().X(), chessPiece.getLocation().Y());
        }

        HBox hBox = new HBox();
        Button backBtn = new Button("< Menu");
        backBtn.setMinHeight(25);
        Button saveBtn = new Button("Save");
        saveBtn.setMinHeight(25);
        Button loadBtn = new Button("Load");
        loadBtn.setMinHeight(25);
        Button replayBtn = new Button("Replay");
        replayBtn.setMinHeight(25);

        hBox.getChildren().addAll(backBtn,saveBtn,loadBtn,replayBtn);

        borderPane.setTop(hBox);
        borderPane.setCenter(grid);
        Scene scene = new Scene(borderPane, 640, 665);
        stage.setTitle("Chess Game");
        stage.setScene(scene);
        stage.setMaxWidth(655);
        stage.setMaxHeight(700);
        stage.show();
        BoardDisplay.printBoard(game.getBoard());
        //highlight square when clicked
        grid.setOnMouseClicked( e -> {
            int col = (int)Math.floor((e.getSceneX())/ 80); //subtract to adjust for stroke size
            int row = (int)Math.floor((e.getSceneY()-25)/ 80);
            if (firstClickX == -1) {
                firstClickX = col;
                firstClickY = row;
            }else{
                secondClickX = col;
                secondClickY = row;

                try {
                    Location from = new Location(firstClickX,firstClickY);
                    Location to = new Location(secondClickX,secondClickY);
                    //reset first click
                    firstClickX = -1;
                    firstClickY = -1;
                    if (game.playMove(from, to)) {
                        BoardDisplay.clearConsole();
                        BoardDisplay.printBoard(game.getBoard());
                        Save.autoSave(game);
                    }
                    if (game.getState() == ChessGame.GameState.PLAY) {
                        setBoard(stage);
                    }else{
                        Menu menu = new Menu();
                        menu.start(stage);
                    }
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
            }
            Rectangle rectangle = new Rectangle(80,80);
            rectangle.setFill(Color.YELLOW);
            rectangle.setOpacity(.5);
            if (e.getSceneX() < 640 && e.getSceneY() < 665) {
                grid.add(rectangle, col, row);
            }
        });
        backBtn.setOnAction(e -> {
            Menu menu = new Menu();
            try {
                menu.start(stage);
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        });
        saveBtn.setOnAction(e -> {
            try {
                Save.save("AutoSave","save");
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        });
        loadBtn.setOnAction(e -> {
            game = Load.Load("save", game);
            try {
                setBoard(stage);
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        });
        replayBtn.setOnAction((ActionEvent e) -> {

                //Replay.replayGUI(this,stage);
            File loadFile = new File("C:\\Users\\Ryan\\Documents\\GitHub\\ConsoleChess\\src\\Data\\AutoSave.txt");
            BufferedReader input = null;
            try {
			/* FileInputStream to read streams */
                ArrayList<ChessPiece> pieces = new ArrayList<>();
                input = new BufferedReader(new FileReader(loadFile));
                String line;
                String[] lineArray;
                int y = 0;

                while((line = input.readLine()) != null) {

                    lineArray = line.split("\\]");
                    if (y == 0) {
                        pieces = new ArrayList<>();
                    }
                    for (int x = 0; x < lineArray.length; x = x + 2) {
                        ChessPiece.PieceColor color;
                        if (lineArray[x + 1].substring(1, 2).equals("B")) {
                            color = ChessPiece.PieceColor.Black;
                        } else {
                            color = ChessPiece.PieceColor.White;
                        }
                        ChessPiece piece = null;
                        Location location = new Location(x / 2, y);
                        switch (lineArray[x].substring(1, 2)) {
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
                            pieces.add(piece);
                        }
                    }
                    y = (y + 1) % 8;
                    if(y == 0){
                        ChessBoard chessBoard = new ChessBoard(pieces);
                        game = new ChessGame(chessBoard);
                        Thread.sleep(1000);
                        System.out.println("hi");
                        setBoard(stage);
                        Thread.sleep(1000);
                    }
                }
            } catch (FileNotFoundException ex) {
                ex.printStackTrace();
            } catch (IOException ex) {
                ex.printStackTrace();
            } catch (Exception ex) {
                ex.printStackTrace();
            } finally {
                if (null != input) {
                    try {
                        input.close();
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                }
            }
        });

    }

    private void setRectangleColor(Rectangle rectangle, int col, int row){

        if((col % 2 == 0) ^ (row % 2 == 0)){
            //set to navy if col or row is even
            rectangle.setFill(Color.NAVY);
        }else{
            //set to lightgray
            rectangle.setFill(Color.LIGHTGRAY);
        }
    }

    public void setGame(ChessGame game){
        this.game = game;
    }
}
