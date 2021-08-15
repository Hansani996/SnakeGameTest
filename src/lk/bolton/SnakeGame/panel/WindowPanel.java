package lk.bolton.SnakeGame.panel;

import lk.bolton.SnakeGame.model.WindowSettings;
import lk.bolton.SnakeGame.model.Directions;
import lk.bolton.SnakeGame.model.RatObjectModel;
import lk.bolton.SnakeGame.model.Snake;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Random;

public class WindowPanel extends JPanel implements ActionListener,Runnable {

    private Thread thread;
    private final int dotSize;
    static final int SCREEN_WIDTH = 600;
    static final int WIDTH = 300;
    private final RatObjectModel ratObjectModel;
    private final Random random;

    private Snake snake;

    private boolean isPlaying;
    private Timer timer;
    private int applecount = 0;

    public WindowPanel(final WindowSettings windowSettings) {
        dotSize = windowSettings.getDotSize();
        ratObjectModel = new RatObjectModel(new ImageIcon(windowSettings.getAppleImageLocation()).getImage());
        random = new Random();
        snake = new Snake(new ImageIcon(windowSettings.getSnakeDotImageLocation()).getImage(),
                dotSize, windowSettings.getAllDotsNumber(), getWidth() / 2);
        isPlaying = true;
        timer = new Timer(100, this);

        addKeyListener(new KeyListener());


        setSize(new Dimension(windowSettings.getWindowSizePerDimension(), windowSettings.getWindowSizePerDimension()));
        setPreferredSize(getSize());
        setBackground(Color.WHITE);
        setFocusable(true);
        setVisible(true);

        startGame();
    }

    public void start() {
        try {
            thread = new Thread(this);
            thread.start();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private void startGame() {
        isPlaying = true;
        timer.start();

        snake = new Snake(
                snake.getSnakeDotImage(),
                dotSize,
                snake.getMaxDotsNumber(),
                getWidth() / 2);
        start();

    }

    private void randomDOGCoords() {
        ratObjectModel.setX(random.nextInt(getWidth() / dotSize) * dotSize);
        ratObjectModel.setY(random.nextInt(getHeight() / dotSize) * dotSize);
    }

    private boolean isBadCollision() {
        return isSnakeCollision() || windowBoderCollision();
    }

    private boolean isSnakeCollision() {
        for (int i = snake.getSize(); i > 0; i--) {
            if (i > 4
                    && snake.getX(0) == snake.getX(i)
                    && snake.getY(0) == snake.getY(i)) {

                isPlaying = false;
                return true;
            }
        }

        return false;
    }

    private boolean windowBoderCollision() {
        if (snake.getX(0) >= getWidth()
                || snake.getX(0) < 0
                || snake.getY(0) >= getHeight()
                || snake.getY(0) < 0) {

            isPlaying = false;
            return true;
        }

        return false;
    }

    private boolean isRatCollision() {
        return snake.getX(0) == ratObjectModel.getX()
                && snake.getY(0) == ratObjectModel.getY();
    }

    @Override
    protected void paintComponent(Graphics gr) {
        super.paintComponent(gr);

        if (isPlaying) {
            drawSnake(gr);
            drawApple(gr);
        } else {
            timer.stop();
            drawGameOver(gr);
        }
    }

    private void drawSnake(Graphics gr) {
        for (int snakeDot = 0; snakeDot < snake.getSize(); snakeDot++) {
            gr.drawImage(snake.getSnakeDotImage(), snake.getX(snakeDot), snake.getY(snakeDot), this);
        }
    }

    //    Display Images
    private void drawApple(Graphics gr) {
        gr.drawImage(ratObjectModel.getImage(), ratObjectModel.getX(), ratObjectModel.getY(), this);
        gr.setColor(Color.red);
        gr.setFont(new Font("Ink Free", Font.BOLD, 40));
        FontMetrics metrics1 = getFontMetrics(gr.getFont());
        gr.drawString("Score: " + applecount, (SCREEN_WIDTH - metrics1.stringWidth("Score: " + applecount)) / 2, gr.getFont().getSize());
    }

    //    END Game Display
    private void drawGameOver(Graphics gr) {
        gr.setColor(Color.BLACK);
        gr.setFont(new Font("Ink Free", Font.BOLD, 18));
        gr.drawString("PLease Try Again! Press Enter Try Again", WIDTH / 2, getHeight() / 2);

        gr.setColor(Color.red);
        gr.setFont(new Font("Ink Free", Font.BOLD, 40));
        FontMetrics metrics1 = getFontMetrics(gr.getFont());
        gr.drawString("Score: " + applecount, (SCREEN_WIDTH - metrics1.stringWidth("Score: " + applecount)) / 2, gr.getFont().getSize());
        applecount = 0;
        thread.stop();

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (isPlaying
                && !isBadCollision()) {

            if (isRatCollision()) {
                thread.stop();
                snake.incSize();
                applecount++;
                start();
            }

            snake.move();
        }

        repaint();
    }

    @Override
    public void run() {
        try {
            for(int i = 6; i > 0; i--) {
                randomDOGCoords();
                Thread.sleep(6000);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    private class KeyListener extends KeyAdapter{
        @Override
        public void keyPressed(KeyEvent ev) {
            super.keyPressed(ev);
            int key = ev.getKeyCode();

            if (key == KeyEvent.VK_LEFT && !snake.isMovingRight()) {
                snake.setMovingDirection(Directions.LEFT);
            } else if (key == KeyEvent.VK_RIGHT && !snake.isMovingLeft()) {
                snake.setMovingDirection(Directions.RIGHT);
            } else if (key == KeyEvent.VK_UP && !snake.isMovingDown()) {
                snake.setMovingDirection(Directions.UP);
            } else if (key == KeyEvent.VK_DOWN && !snake.isMovingUp()) {
                snake.setMovingDirection(Directions.DOWN);
            }else if (key == KeyEvent.VK_ENTER && !isPlaying) {
                startGame();
            }
        }
    }
}


