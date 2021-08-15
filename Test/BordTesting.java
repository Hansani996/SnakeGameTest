import lk.bolton.SnakeGame.model.WindowSettings;
import org.junit.Test;

import static lk.bolton.SnakeGame.model.WindowSettings.*;
import static org.junit.Assert.assertEquals;

public class BordTesting {
    WindowSettings windowSettings = new WindowSettings(ratSize,
            nubPerDiametion,
            snakeImage,
            ratImage);

    @Test
    public void testRatSize() {
        assertEquals(16, windowSettings.getDotSize());
    }

    @Test
    public void windowSizeTest() {
        assertEquals(windowSettings.getDotSize()* nubPerDiametion, windowSettings.getWindowSizePerDimension());
    }

    @Test
    public void testSnakeLocation() {
        assertEquals(snakeImage, windowSettings.getSnakeDotImageLocation());
    }
    @Test
    public void testRatLocation() {
        assertEquals(ratImage, windowSettings.getAppleImageLocation());
    }
}
