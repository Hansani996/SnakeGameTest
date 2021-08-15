import lk.bolton.SnakeGame.model.Snake;
import org.junit.Test;

import javax.swing.*;

import static lk.bolton.SnakeGame.model.WindowSettings.nubPerDiametion;
import static org.junit.Assert.assertEquals;

public class TestSnake {

    Snake snake= new Snake(new ImageIcon("images.png").getImage(),16, (int) Math.pow(nubPerDiametion, 2),  600/2);

    @Test
    public void SizeTesting() {
        assertEquals(3,snake.getSize());
    }

    @Test
    public void RatImgTesting() {
        assertEquals(new ImageIcon("images.png").getImage(),snake.getSnakeDotImage());
    }

    @Test
    public void moveLeftTesting() {
        assertEquals(true,snake.isMovingLeft());
    }

    @Test
    public void moveUpTesting() {
        assertEquals(true,snake.isMovingUp());
    }

    @Test
    public void moveRightTesting() {
        assertEquals(true,snake.isMovingRight());
    }

    @Test
    public void moveDownTesting() {
        assertEquals(true,snake.isMovingDown());
    }

}
