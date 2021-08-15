package lk.bolton.SnakeGame.view;

import lk.bolton.SnakeGame.model.WindowSettings;
import lk.bolton.SnakeGame.panel.WindowPanel;

import javax.swing.*;
import java.awt.*;

public class SnakeGameUI extends JFrame {
//    Open Game

    public SnakeGameUI(final WindowSettings windowSettings) {
        super("Rat & Snake");

        getContentPane().add(new WindowPanel(windowSettings));
        pack();

        setWindow();
    }
    /**
     * Open Windows Settings
     */
    private void setWindow() {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        setLocationInCenter();
        setVisible(true);
    }
    /**
     * Manage Windows Center on Screen
     */
    private void setLocationInCenter() {
        setLocationRelativeTo(null);

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(
                screenSize.width / 2 - this.getSize().width / 2,
                screenSize.height / 2 - this.getSize().height / 2);
    }
}
