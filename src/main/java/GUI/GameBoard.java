package GUI;


import Chess.AI.RandomAI;
import Chess.ChessGame;
import Chess.Location;
import Chess.Move;
import Chess.Pieces.*;
import Console.BoardDisplay;
import Data.Load;
import Data.Save;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.scene.paint.Color;
import javafx.scene.image.ImageView;

import java.io.*;
import javax.swing.*;
import java.util.ArrayList;

/**
 *
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
    private boolean isOnePlayer = false;

    @Override
    public void start(Stage primaryStage) throws Exception {
        for (ChessPiece chessPiece : game.getBoard().getBoardArrayList()) {
            chessPiece.setImage();
        }
    }

    public void setIsOnePlayer(boolean isOnePlayer) {
        this.isOnePlayer = isOnePlayer;
    }

    public void setBoard (Stage stage) throws Exception {
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
            chessPiece.setImage();
            ImageView tmpView = chessPiece.getImage();
            tmpView.setFitHeight(80);
            tmpView.setFitWidth(80);
            grid.add(chessPiece.getImage(), chessPiece.getLocation().X(), chessPiece.getLocation().Y());
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
                //reset board if same tile is selected
                if((firstClickX == secondClickX) && (firstClickY == secondClickY)){
                    //reset first click
                    firstClickX = -1;
                    firstClickY = -1;
                    try {
                        setBoard(stage);
                    } catch (Exception e1) {
                        e1.printStackTrace();
                    }
                }else{
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

                        repaint();
                        boolean isEndOfGame = game.getBoard().getAllValidMoves(game.getCurrentPlayer()).size() == 0;
                        if (isOnePlayer && !isEndOfGame) {
                            RandomAI randomAI = new RandomAI(game);
                            Move aiMove = randomAI.getNextMove();
                            game.playMove(aiMove.getPiece().getLocation(), aiMove.getTo());
                            Save.autoSave(game);
                            repaint();
                        } else if (isEndOfGame) {
                            JOptionPane.showMessageDialog(null, game.getState().toString());
                            System.out.println(game.getState().toString());
                        }
                        if (game.getState() == ChessGame.GameState.PLAY) {
                            setBoard(stage);
                        } else {
                            Menu menu = new Menu();
                            menu.start(stage);
                        }
                    }
                    else{
                        displayAlert("Alert Message", "Invalid move!");
                        setBoard(stage);
                    }
                } catch (Exception e1) {
                        e1.printStackTrace();

                    }
                }}

                Rectangle rectangle = new Rectangle(80, 80);
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
            Replay.replayConsole();
        });

    }

    private void repaint() {
        BoardDisplay.clearConsole();
        BoardDisplay.printBoard(game.getBoard());
    }

    private void setRectangleColor(Rectangle rectangle, int col, int row){

        if((col % 2 == 0) ^ (row % 2 == 0)){
            //set to green if col or row is even
            rectangle.setFill(Color.GREEN);
        }else{
            //set to tan
            rectangle.setFill(Color.TAN);
        }
    }


    public void setGame(ChessGame game){
        this.game = game;
    }

    public static void displayAlert(String title, String message) {
        Stage window = new Stage();

        //Block events to other windows
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle(title);
        window.setMinWidth(270);
        window.setMinHeight(100);


        Label label = new Label();
        label.setText(message);
        Button closeButton = new Button("Ok");
        closeButton.setMinWidth(75);
        closeButton.setOnAction(e -> window.close());

        VBox layout = new VBox(30);
        layout.setPadding(new Insets(25,0,25,0));

        layout.getChildren().addAll(label, closeButton);
        layout.setAlignment(Pos.CENTER);

        //Display window and wait for it to be closed before returning
        Scene scene = new Scene(layout);
        window.setScene(scene);
        window.showAndWait();
    }
}
