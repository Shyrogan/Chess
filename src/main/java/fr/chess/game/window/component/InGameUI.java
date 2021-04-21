package fr.chess.game.window.component;

import fr.chess.game.board.Board;
import fr.chess.game.board.piece.Piece;
import fr.chess.game.board.team.Team;
import fr.chess.game.math.Position;
import fr.chess.game.window.resource.ImageManager;

import java.awt.*;
import java.awt.image.BufferedImage;

import static fr.chess.game.board.Board.SIZE_BOARD;
import static fr.chess.game.math.Position.pos;
import static java.lang.Math.min;

public class InGameUI extends GameComponent {

    private final Board board;

    /** Les marges qui permettent de center l'échec à gauche et en haut. **/
    public int margeX, margeY;
    /** Quelques dimensions nécessaire pour les maths. **/
    public int tailleEchiquier, tailleCase;

    public InGameUI(Board board) {
        this.board = board;
    }

    @Override
    protected void paintComponent(Graphics graph) {
        super.paintComponent(graph);
        Graphics2D g = (Graphics2D) graph;
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        g.setRenderingHint(RenderingHints.KEY_FRACTIONALMETRICS, RenderingHints.VALUE_FRACTIONALMETRICS_ON);
        g.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);

        // On va faire ça "step by step"

        // On dessine l'échiquier
        drawBoard(g);
        // On dessine les pièces
        drawPieces(g);
    }

    public void drawBoard(Graphics2D g) {
        // On mesure tout bien
        g.setColor(new Color(49, 46, 43));
        g.fillRect(0, 0, g.getClipBounds().width, g.getClipBounds().height);

        // On veut un carré qui prend un maximum
        tailleEchiquier = min(g.getClipBounds().height, g.getClipBounds().width);
        margeX = g.getClipBounds().height == tailleEchiquier
                ? g.getClipBounds().width / 2 - tailleEchiquier / 2
                : 0;
        margeY = g.getClipBounds().width == tailleEchiquier
                ? g.getClipBounds().height / 2 - tailleEchiquier / 2
                : 0;
        // On divise par le nombre de case pour obtenir la taille par case.
        tailleCase = tailleEchiquier / SIZE_BOARD;

        // On dessine l'arrière plan
        for(int yCase = 1; yCase <= SIZE_BOARD; yCase++) {
            for(int xCase = 1; xCase <= SIZE_BOARD; xCase++) {
                final Position position = getPositionPixels(new Position(xCase, yCase));
                if((xCase + yCase) % 2 == 1) {
                    g.setColor(new Color(118, 150, 86));
                } else {
                    g.setColor(new Color(238, 238, 210));
                }
                g.fillRect(position.x, position.y, tailleCase, tailleCase);
            }
        }
    }

    public void drawPieces(Graphics2D g) {
        // On dessine les pièces
        for(Team team: board.teams) {
            for(Piece piece: team.pieces.values()) {
                BufferedImage image = ImageManager.resource(piece.type.name() + "_" + piece.team.name + ".png");
                Position position = getPositionPixels(piece.position);
                g.drawImage(image, position.x, position.y, tailleCase, tailleCase, null, null);
            }
        }
    }

    /**
     * Retourne la position (en pixels) à partir de la position sur l'échiquier.
     *
     * @param position Position sur l'échiquier
     * @return La position en pixels.
     */
    public Position getPositionPixels(Position position) {
        return pos(margeX + (position.x - 1) * tailleCase, margeY + (position.y - 1) * tailleCase);
    }

}
