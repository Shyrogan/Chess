package fr.chess.game.window;

import fr.chess.game.window.component.GameComponent;
import fr.chess.game.window.component.MainMenuUI;

import javax.swing.*;

public class Window extends JFrame {

    /** Ce qui est affiché à l'écran actuellement. **/
    private GameComponent component;

    public Window() {
        super("Echecs");

        // On commence par le menu!
        setComponent(new MainMenuUI());

        // Petits trucs simples.
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        // Hop hop hop c'est parti
        pack();
    }

    /**
     * Défini le menu qui est affiché à l'écran.
     *
     * @param component Le menu affiché.
     */
    public void setComponent(GameComponent component) {
        if(this.component != null) {
            getContentPane().remove(this.component);
            removeMouseListener(this.component);
        }

        this.component = component;

        getContentPane().add(component);
        addMouseListener(component);

        revalidate();
        repaint();
    }

}
