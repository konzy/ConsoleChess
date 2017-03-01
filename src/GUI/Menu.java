package GUI;

import Chess.ChessGame;
import Chess.StatCollection;
import Console.BoardDisplay;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.scene.text.Text;
import javafx.scene.control.Button;
import javafx.geometry.Pos;

import javafx.scene.layout.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

/**
 * Created by Elizabeth on 1/25/2017.
 */
public class Menu extends Application {
    public Menu(){}
    @Override
    public void start(Stage stage) throws Exception {

        StatCollection stats = new StatCollection();
//        stats.retrieveData();

        BorderPane borderPane = new BorderPane();
        Label welcomeLabel = new Label("Welcome to the Chess GUI!");

        Button onePlayerBtn = new Button("One Player");
        Button twoPlayerBtn = new Button("Two Player");
        Button statsBtn = new Button("Stats");

        //set widths of buttons to be the same
        onePlayerBtn.setMaxWidth(200);
        twoPlayerBtn.setMaxWidth(200);
        statsBtn.setMaxWidth(200);

        //set heights of buttons to be the same
        onePlayerBtn.setMinHeight(50);
        twoPlayerBtn.setMinHeight(50);
        statsBtn.setMinHeight(50);

        VBox vBox = new VBox();
        vBox.setSpacing(50);
        vBox.setAlignment(Pos.CENTER);

        //vBox.add(new Text("Welcome to the Chess GUI!"));
        vBox.getChildren().add(welcomeLabel);
        vBox.getChildren().add(onePlayerBtn);
        vBox.getChildren().add(twoPlayerBtn);
        vBox.getChildren().add(statsBtn);

        borderPane.setCenter(vBox);

        Scene scene = new Scene(borderPane, 300, 300);
        stage.setTitle("Chess Game");
        stage.setScene(scene);
        stage.setMinWidth(655);
        stage.setMinHeight(700);
        stage.show();

        twoPlayerBtn.setOnAction(e -> {
//            stats.incGames();
//            stats.storeData();
            ChessGame game = new ChessGame();
            GameBoard gamebrd = new GameBoard(game);
            try {
                gamebrd.start(null);
                gamebrd.setIsOnePlayer(false);
                gamebrd.setBoard(stage);
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        });

        onePlayerBtn.setOnAction(e -> {
//            stats.incGames();
//            stats.incCPU();
//            stats.storeData();
            ChessGame game = new ChessGame();
            GameBoard gamebrd = new GameBoard(game);
            try {
                gamebrd.start(null);
                gamebrd.setIsOnePlayer(true);
                gamebrd.setBoard(stage);
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        });

        statsBtn.setOnAction(e -> {
//            stats.retrieveData();
            StatsPage statsPg = new StatsPage();
            try {
                statsPg.changeScene(stage);
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        });

    }
    public static void main(String[] args) {
        Application.launch();
    }

}
