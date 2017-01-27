package GUI;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.scene.paint.Color;



/**
 * Created by Elizabeth on 1/26/2017.
 */
public class GameBoard extends Application {

    public GameBoard(){}

    @Override
    public void start(Stage primaryStage) throws Exception {}

    public void setBoard (Stage stage) throws Exception {
        BorderPane borderPane = new BorderPane();
        GridPane grid = new GridPane();
        //grid.setHgap(8);
        //grid.setVgap(8);
        grid.setPadding(new Insets(0,0,25,0));

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                Button btn = new Button("button");
                Rectangle rectangle = new Rectangle(80,80);
                rectangle.setStrokeWidth(3.0);

                //if either col is even or row is even, not both
                if((i % 2 == 0) ^ (j % 2 == 0)){
                        //set to navy if col or row is even
                        rectangle.setFill(Color.NAVY);
                    rectangle.setStroke(Color.NAVY);

                }else{
                    //set to lightgray
                    rectangle.setFill(Color.LIGHTGRAY);
                    rectangle.setStroke(Color.LIGHTGRAY);

                }
                grid.add(rectangle,i,j);
            }
        }

        borderPane.setCenter(grid);
        Scene scene = new Scene(borderPane, 640, 640);
        stage.setTitle("Chess Game");
        stage.setScene(scene);
        stage.show();

        grid.setOnMouseClicked( e -> {
            int col = (int)Math.floor(e.getSceneX() / 80);
            int row = (int)Math.floor(e.getSceneY() / 80);

            Rectangle rectangle = new Rectangle(80,80);
            rectangle.setStrokeWidth(3.0);
            //if either col is even or row is even, not both
            if((col % 2 == 0) ^ (row % 2 == 0)){
                //set to navy if col or row is even
                rectangle.setFill(Color.NAVY);
                rectangle.setStroke(Color.YELLOW);
            }else{
                //set to lightgray
                rectangle.setFill(Color.LIGHTGRAY);
                rectangle.setStroke(Color.YELLOW);
            }
            grid.add(rectangle,col,row);
        });



    }
}
