package GUI;

import Chess.ChessGame;
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
    public Menu(){};
    @Override
    public void start(Stage stage) throws Exception {

        BorderPane borderPane = new BorderPane();
        /*GridPane pane = new GridPane();
        pane.setPadding(new Insets(50,50,50,50));
        pane.setHgap(5);
        pane.setVgap(4);
        pane.add(new Text("Welcome to the Chess GUI!"),1,0);*/
        Label welcomeLabel = new Label("Welcome to the Chess GUI!");

        Button onePlayerBtn = new Button("One Player");
        Button twoPlayerBtn = new Button("Two Player");
        Button statsBtn = new Button("Stats");

        //set widths of buttons to be the same
        onePlayerBtn.setMaxWidth(100);
        twoPlayerBtn.setMaxWidth(100);
        statsBtn.setMaxWidth(100);


        VBox vBox = new VBox();
        vBox.setSpacing(50);
        vBox.setAlignment(Pos.CENTER);

        //vBox.add(new Text("Welcome to the Chess GUI!"));
        vBox.getChildren().add(welcomeLabel);
        vBox.getChildren().add(onePlayerBtn);
        vBox.getChildren().add(twoPlayerBtn);
        vBox.getChildren().add(statsBtn);
        //pane.getChildren().add(onePlayerBtn);
        //pane.getChildren().add(twoPlayerBtn);

        // borderPane.setTop(pane);
        borderPane.setCenter(vBox);

        Scene scene = new Scene(borderPane, 300, 300);
        stage.setTitle("Chess Game");
        stage.setScene(scene);
        stage.show();

        onePlayerBtn.setOnAction(new EventHandler<ActionEvent>(){
            public void handle (ActionEvent e){
                ChessGame game = new ChessGame();
                GameBoard gamebrd = new GameBoard(game);
                try {
                           //Sets up chess game, initial player is white, prints board to console
                    //BoardDisplay.clearConsole();
                   // gamebrd.setBoard(game.board);
                    gamebrd.setBoard(stage);
                    //gamebrd.setBoard(stage);
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
            }
        });

        statsBtn.setOnAction(new EventHandler<ActionEvent>(){
            public void handle (ActionEvent e){
                StatsPage statsPg = new StatsPage();
                try {
                    statsPg.changeScene(stage);
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
            }
        });

    }
    public static void main(String[] args) {
        Application.launch();
    }

}
