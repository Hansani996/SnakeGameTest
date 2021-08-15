package lk.bolton.SnakeGame.main;

import lk.bolton.SnakeGame.model.WindowSettings;
import lk.bolton.SnakeGame.view.SnakeGameUI;
import  static lk.bolton.SnakeGame.model.WindowSettings.*;
public class Main {


    public static void main(String args[]){

        new SnakeGameUI(new WindowSettings(ratSize,
                nubPerDiametion,
                snakeImage,
                ratImage));
    }
}
