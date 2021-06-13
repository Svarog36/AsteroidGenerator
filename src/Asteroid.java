import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Asteroid {


    private Group asteroid;
    private int size;


    Asteroid(int size, int x, int y){

        asteroid = new Group();
        this.size = size;


        int[][]grid = new int[size][size];

        initialiseGrid(grid);
        grid[(size/2)][(size/2)]++;

        for(int i = 0; i < size/2; i++)
            generateAsteroid(grid);

        asteroid.addEventHandler(MouseEvent.ANY, event -> {


            for(int i = 0; i < asteroid.getChildren().size(); i++){
                Node baseRect = asteroid.getChildren().get(i);

                if(!baseRect.isVisible()){
                    asteroid.getChildren().get(i).setVisible(true);
                    ((Rectangle)asteroid.getChildren().get(i)).setFill(Color.BLACK);
                    ((Rectangle)asteroid.getChildren().get(i)).setOpacity(1);
                }


            }

        });

        relocate(x,y);


    }


    void generateAsteroid(int grid[][]){

        List<Seed> startSeeds = new ArrayList<>();

        for(int j = 0; j < grid.length; j++){

            for(int i = 0; i < grid[j].length; i++){

                if(grid[j][i] != 0){
                    startSeeds.add(new Seed(i, j));
                }

            }

        }

        for(Seed seed : startSeeds){

            for(int i = 0; i < getRandom(8); i++){

                int rndm = getRandom(20);

                if(rndm == 0){
                    grid[seed.y][seed.x - 1]++;
                    asteroid.getChildren().add(new BaseRect(seed.y * Main.rectSize, (seed.x - 1) * Main.rectSize).getRectangle());
                }else if(rndm == 1){
                    grid[seed.y][seed.x + 1]++;
                    asteroid.getChildren().add(new BaseRect(seed.y * Main.rectSize, (seed.x + 1) * Main.rectSize).getRectangle());
                }else if(rndm == 2){
                    grid[seed.y - 1][seed.x]++;
                    asteroid.getChildren().add(new BaseRect((seed.y - 1) * Main.rectSize, seed.x * Main.rectSize).getRectangle());
                }else if(rndm == 3){
                    grid[seed.y + 1][seed.x]++;
                    asteroid.getChildren().add(new BaseRect((seed.y + 1) * Main.rectSize, seed.x * Main.rectSize).getRectangle());
                }


            }
        }

        startSeeds.clear();

    }


    static int getRandom(int possibleValues){
        return (int)(Math.random() * possibleValues);
    }


    void initialiseGrid(int[][] grid){
        for (int[] ints : grid) {
            Arrays.fill(ints, 0);
        }

    }

    Group getAsteroid(){
        return asteroid;
    }

    int getPositionY(){
        return (int)(asteroid.getTranslateY()+size/2*Main.rectSize);
    }

    void relocate(int x, int y){

        asteroid.setTranslateX(-size/2 * Main.rectSize + x);
        asteroid.setTranslateY(-size/2 * Main.rectSize + y);

    }


}

class Seed{

    int x, y;

    Seed(int x, int y){
        this.x = x;
        this.y = y;
    }

}