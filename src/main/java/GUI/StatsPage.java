package GUI;

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

    public StatsPage() {}
    @Override
    public void start(Stage stage) throws Exception { }

    public void changeScene (Stage stage) throws Exception{

        BorderPane borderPane = new BorderPane();
        Label welcomeLabel = new Label("Welcome to the Stats page!");

        VBox vBox = new VBox();
        vBox.setSpacing(10);
        vBox.setPadding(new Insets(50,0,0,0));
        vBox.setAlignment(Pos.CENTER);

        vBox.getChildren().add(welcomeLabel);

        HBox hBox = new HBox();
        Button backBtn = new Button("< Menu");
        backBtn.setMinHeight(25);
        hBox.getChildren().addAll(backBtn);

        borderPane.setTop(hBox);
        borderPane.setCenter(vBox);

        Scene scene = new Scene(borderPane, 300, 300);
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
    }

}

