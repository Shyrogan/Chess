package fr.chess.game.window.component;

import fr.chess.game.Game;
import fr.chess.game.board.Board;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.geom.Rectangle2D;

/**
 * Le menu principal, il ne contient qu'un bouton néanmoins il pourrait être agrandi si nous avons le temps.
 */
public class MainMenuUI extends GameComponent {

    public MainMenuUI() {
        super();
    }

    /**
     * Dessine le menu principal.
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Game.instance.audioManager.playDefault();

        // La qualité avant tout!
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);

        g.setColor(new Color(49, 46, 43));
        g.fillRect(0, 0, g.getClipBounds().width, g.getClipBounds().height);

        int cX = getWidth() / 2;
        int cY = getHeight() / 2;
        int bSize = getWidth() / 15;
        int bHeight = 20;

        g.setColor(Color.WHITE);
        g.setFont(g.getFont().deriveFont(40F));
        Rectangle2D rect = g.getFontMetrics().getStringBounds("Chess (Échecs)", g);
        g.drawString("Chess (Échecs)", cX - (int)rect.getWidth() / 2, cY - 50 - (int)rect.getHeight() / 2);

        g.setColor(new Color(118, 150, 86));
        g.fillRect(cX - bSize, cY - bHeight, bSize * 2, bHeight * 2);

        g.setColor(Color.WHITE);
        g.setFont(g.getFont().deriveFont(24F));
        rect = g.getFontMetrics().getStringBounds("Play!", g);
        g.drawString("Play!", cX - (int)rect.getWidth() / 2, cY + (int)(rect.getHeight() / 3));
    }

    @Override
    public void mousePressed(MouseEvent e) {
        int cX = getWidth() / 2;
        int cY = getHeight() / 2;
        int bSize = getWidth() / 15;
        int bHeight = 20;

        int x = cX - bSize;
        int y = cY + bHeight;
        int width = bSize * 2;
        int height = bHeight * 2;

        if(e.getX() >= x && e.getX() <= (x + width) && e.getY() >= y && e.getY() <= (y + height)) {
            Game.instance.audioManager.stop();
            Game.instance.window.setComponent(new InGameUI(new Board()));
        }
    }
}
