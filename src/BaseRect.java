import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

public class BaseRect {


    private Node rectangle;

    BaseRect(int y, int x){

        rectangle = new Rectangle(x,y, Main.rectSize, Main.rectSize);


        ((Rectangle)rectangle).setFill(Color.DIMGRAY);

        rectangle.setOpacity(0.1);

        rectangle.addEventHandler(MouseEvent.ANY, event -> {
           rectangle.setVisible(false);
        });


    }


    public Node getRectangle(){
        return rectangle;
    }




}
