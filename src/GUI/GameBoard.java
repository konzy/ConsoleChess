package GUI;

import Chess.ChessBoard;
import Chess.ChessGame;
import Chess.Coord;
import Chess.Pieces.ChessPiece;
import Chess.Tile;
import Console.BoardDisplay;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.scene.paint.Color;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.HashMap;
import java.util.Map;


/**
 * Created by Elizabeth on 1/26/2017.
 */
public class GameBoard extends Application {


    ChessGame game;
    public GameBoard(ChessGame g){
        game = g;
    }
    int firstClickX = -1;
    int firstClickY = -1;
    int secondClickX = -1;
    int secondClickY = -1;
    Map<String,String> pieces = new HashMap<String,String>();

    @Override
    public void start(Stage primaryStage) throws Exception {

    }

    public void setBoard (Stage stage) throws Exception {
        String picPath = "/GUI/assets/";
        pieces.put("[R]",picPath + "r.png");
        pieces.put("[K]",picPath + "k.png");
        pieces.put("[B]",picPath + "b.png");
        pieces.put("[Q]",picPath + "q.png");
        pieces.put("[P]",picPath + "p.png");
        pieces.put("[K]",picPath + "k.png");

        BorderPane borderPane = new BorderPane();
        GridPane grid = new GridPane();

        grid.setPadding(new Insets(0,0,25,0));

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                Rectangle rectangle = new Rectangle(80,80);
                setRectangleColor(rectangle,i,j);
                grid.add(rectangle,i,j);

                Tile[][] b = game.board.getBoardArray();
                String letter = b[j][i].value().toLowerCase();
                if (!letter.equals("[ ]")) {
                    letter =  Character.toString(letter.charAt(1));
                    if (b[j][i].getPiece().color() == ChessPiece.PieceColor.Black){
                        letter = "black_" + letter;
                    }
                    ImageView tmpView = new ImageView(picPath + letter + ".png");
                    tmpView.setFitHeight(80);
                    tmpView.setFitWidth(80);
                    grid.add(tmpView, i, j);
                }
            }
        }
        HBox hBox = new HBox();
        Button backBtn = new Button("< Menu");
        backBtn.setMinHeight(25);

        hBox.getChildren().add(backBtn);

        borderPane.setTop(hBox);
        borderPane.setCenter(grid);
        Scene scene = new Scene(borderPane, 640, 665);
        stage.setTitle("Chess Game");
        stage.setScene(scene);
        stage.setMaxWidth(655);
        stage.setMaxHeight(700);
        stage.show();
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
                    Coord from = new Coord(firstClickX,firstClickY);
                    Coord to = new Coord(secondClickX,secondClickY);
                    //reset first click
                    firstClickX = -1;
                    firstClickY = -1;
                    if (game.isValidMove(from, to)) {
                        game.playMove(from, to);
                        BoardDisplay.clearConsole();
                        BoardDisplay.printBoard(game.board);
                    }
                    if (!game.isFinished()) {
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
        backBtn.setOnAction(new EventHandler<ActionEvent>(){
            public void handle (ActionEvent e){
                Menu menu = new Menu();
                try {
                    menu.start(stage);
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
            }
        });
    }
    void setRectangleColor(Rectangle rectangle,int col, int row){

        if((col % 2 == 0) ^ (row % 2 == 0)){
            //set to navy if col or row is even
            rectangle.setFill(Color.NAVY);
        }else{
            //set to lightgray
            rectangle.setFill(Color.LIGHTGRAY);
        }
    }

}
