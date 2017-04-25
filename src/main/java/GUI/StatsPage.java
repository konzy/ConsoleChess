package GUI;

import Chess.StatCollection;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * Created by Elizabeth on 1/26/2017.
 */
public class StatsPage extends Application {

    public static StatCollection stats = new StatCollection();

    public StatsPage() {}
    @Override
    public void start(Stage stage) throws Exception { }

    public void changeScene (Stage stage) throws Exception{
        stats.retrieveData();
        BorderPane borderPane = new BorderPane();
        Label welcomeLabel = new Label("Welcome to the Stats page!");

        VBox vBox = new VBox();
        vBox.setSpacing(20);
        vBox.setPadding(new Insets(50,0,0,0));
        vBox.setAlignment(Pos.CENTER);

        vBox.getChildren().add(welcomeLabel);
        Button backBtn = new Button("< Menu");
        backBtn.setMinHeight(25);
        Button clearStats = new Button("Erase Stats");
        clearStats.setMinHeight(25);
        Label whiteMoves = new Label("White Moves: " + stats.getWhiteMoves());
        Label blackMoves = new Label("Black Moves: " + stats.getBlackMoves());
        Label wins = new Label("Wins: " + stats.getWin());
        Label losses = new Label("Losses: " + stats.getLoss());
        Label winPcnt = new Label("Win Percent: " + stats.getWinPercent());
        Label whiteCaptures = new Label("White Captures: " + stats.getWhiteCaptures());
        Label blackCaptures = new Label("Black Captures: " + stats.getBlackCaptures());
        Label games = new Label("Games: " + stats.getGames());
        Label CPUGames = new Label("CPU Games: " + stats.getCPUGames());
        Label undo = new Label("Moves Undone: " + stats.getMovesUndone());
        Label space = new Label(" ");

        vBox.getChildren().add(space);
        vBox.getChildren().add(whiteMoves);
        vBox.getChildren().add(blackMoves);
        vBox.getChildren().add(wins);
        vBox.getChildren().add(losses);
        vBox.getChildren().add(winPcnt);
        vBox.getChildren().add(whiteCaptures);
        vBox.getChildren().add(blackCaptures);
        vBox.getChildren().add(games);
        vBox.getChildren().add(CPUGames);
        vBox.getChildren().add(undo);

        HBox hBox = new HBox();
        hBox.setPadding(new Insets(0,0,50,0));
        hBox.setAlignment(Pos.CENTER);
        hBox.getChildren().addAll(backBtn,clearStats);

        borderPane.setTop(vBox);
        borderPane.setBottom(hBox);
        Scene scene = new Scene(borderPane, 640, 655);
        stage.setTitle("Chess Game");
        stage.setScene(scene);
        stage.show();

        backBtn.setOnAction(e -> {
            Menu menu = new Menu();
            try {
                menu.start(stage);
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        });
        clearStats.setOnAction(e -> {
            stats.resetStats();
            StatsPage statsPg = new StatsPage();
            try {
                statsPg.changeScene(stage);
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        });
    }
}

