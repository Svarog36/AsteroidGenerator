import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.Group;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.List;

public class Controller {

    static Group map;

    private Timeline timeline;

    private long timestamp2 = 0, timestamp1;
    int offset = 0, minAsteroids = 50;

    static List<Asteroid> allAsteroids;

    Controller (){

        map = new Group();
        allAsteroids = new ArrayList<>();


        timeline = new Timeline(new KeyFrame(Duration.millis(16), event -> {

            timestamp2 = System.currentTimeMillis();

            if(timestamp1 != 0)
                update((double)(timestamp2 - timestamp1)/1000);

            timestamp1 = System.currentTimeMillis();

        }));
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();

        

    }



    void addAsteroid(int offset){

        allAsteroids.add(new Asteroid(Asteroid.getRandom(30)+15, Asteroid.getRandom(40)*Main.rectSize, -10* Main.rectSize-offset - Asteroid.getRandom(100) * Main.rectSize));
        map.getChildren().add(allAsteroids.get(allAsteroids.size()-1).getAsteroid());

    }

    void update(double elapsedTime){

        map.setTranslateY(map.getTranslateY() + elapsedTime * 300);

        offset = (int)(map.getTranslateY());


        try {
            for (Asteroid a : allAsteroids){

                if(offset - (-a.getAsteroid().getTranslateY()) > 800 ){
                    allAsteroids.remove(a);
                    map.getChildren().remove(a.getAsteroid());
                }
            }
        }catch (ConcurrentModificationException e){
            System.out.print("");
        }


        if(allAsteroids.size() < minAsteroids){
            addAsteroid(offset);
        }

    }

    void clearMap(){
        map.getChildren().clear();
        allAsteroids.clear();
    }



    public Group getMap() {
        return map;
    }
}
