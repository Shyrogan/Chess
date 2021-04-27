package fr.chess.game.main;

import fr.chess.game.Game;

import javax.swing.*;
import java.awt.*;

/**
 * L'entrée du programme ce fait par cette classe,
 * je souhtais séparer le main du reste.
 */
public class Main {

    /**
     * Démarre le programme.
     *
     * @param args Les arguments.
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Game.instance.window.setVisible(true);
            Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
            Game.instance.window.setSize((int)(dimension.width / 1.25), (int)(dimension.height / 1.25));
        });
    }

}
