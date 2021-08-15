package lk.bolton.SnakeGame.model;

public final class WindowSettings {
//    normal board dot size and dimension of dot numbers
    public static final int ratSize = 16;
    public static final int nubPerDiametion = 40;
//    image location/path
    public static final String ratImage = "images.png";
    public static final String snakeImage = "snake-icon.png";
//    variable declaration for board
    private final int dotSize;
    private final int windowSizePerDimension;
    private final int allDotsNumber;

    private final String snakeDotImageLocation;
    private final String appleImageLocation;

    //constructor
    public WindowSettings(
            int dotSize,
            int dotNumberPerDimension,
            String snakeDotImageLocation,
            String appleImageLocation) {

        this.dotSize = dotSize;

        windowSizePerDimension = this.dotSize * dotNumberPerDimension;
        allDotsNumber = (int) Math.pow(dotNumberPerDimension, 2);

        this.snakeDotImageLocation = snakeDotImageLocation;
        this.appleImageLocation = appleImageLocation;
    }

    public int getDotSize() {
        return dotSize;
    }

    public int getWindowSizePerDimension() {
        return windowSizePerDimension;
    }

    public int getAllDotsNumber() {
        return allDotsNumber;
    }

    public String getSnakeDotImageLocation() {
        return snakeDotImageLocation;
    }

    public String getAppleImageLocation() {
        return appleImageLocation;
    }
}
