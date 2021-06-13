import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.CacheHint;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Shape;
import javafx.stage.Stage;


public class Main extends Application {

    final static int rectSize = 20;
    private Controller con;
    private Pane root;

    private Parent createContent(){
        root = new Pane();

        con = new Controller();

        root.getChildren().add(con.getMap());

        root.setCache(true);
        root.setCacheHint(CacheHint.SPEED);


        root.setBackground(new Background(new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY)));

        return root;
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        primaryStage.setScene(new Scene(createContent()));

        primaryStage.setHeight(800);
        primaryStage.setWidth(800);




        primaryStage.show();

    }


    public static void main(String[] args) {
        launch(args);
    }
}
