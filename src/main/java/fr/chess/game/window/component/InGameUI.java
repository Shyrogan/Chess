package fr.chess.game.window.component;

import fr.chess.game.Game;
import fr.chess.game.board.Board;
import fr.chess.game.board.movement.BoardMovement;
import fr.chess.game.board.piece.Piece;
import fr.chess.game.board.team.Team;
import fr.chess.game.math.Position;
import fr.chess.game.window.resource.ImageManager;

import javax.sound.sampled.Clip;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.util.Optional;

import static fr.chess.game.board.Board.SIZE_BOARD;
import static fr.chess.game.math.Position.pos;
import static java.lang.Math.min;

/**
 * Le {@link InGameUI} dessine un échiquier sur la totalité de l'écran, il s'agit de l'écran le plus important du jeu.
 * Il gère aussi les entrées/sorties du programme durant la phase de jeu.
 *
 * 24/04, plus le temps de documenter, il faut speeder donc je verrais si j'ai le temps
 * de commenter le code, je ferais au mieux.
 */
public class InGameUI extends GameComponent {

    private final Board board;

    /** Les marges qui permettent de center l'échec à gauche et en haut. **/
    public int margeX, margeY;
    /** Quelques dimensions nécessaire pour les maths. **/
    public int tailleEchiquier, tailleCase;
    /** Le gagnant **/
    public Team winner;

    public Position selection;

    public InGameUI(Board board) {
        this.board = board;
    }

    /**
     * Gère les entrées liés à la souris sur l'échiquier.
     *
     * @param e L'évènement.
     */
    @Override
    public void mousePressed(MouseEvent e) {
        Position position = new Position((e.getX() - margeX) / tailleCase + 1, (e.getY() - margeY) / tailleCase + 1);
        if(!position.isInBoard())
            return;

        // Si on clique pour la première fois sur une case, on selectionne la pièce
        if(selection == null) {
            if(board.at(position).isPresent()) {
                selection = position;
            } else {
                selection = null;
            }
            repaint();
            return;
        }
        // Si on reclique sur la même, on la déselectionne
        if(selection.equals(position)) {
            selection = null;
            repaint();
            return;
        }

        Optional<Piece> optionalPiece = board.at(selection);
        if(optionalPiece.isEmpty()) {
            selection = null;
            repaint();
            return;
        }

        if(optionalPiece.get().movements(board).contains(position)) {
            BoardMovement movement = board.move(selection, position);
            if(movement.isPossible()) {
                movement.apply();
                board.tour++;
                Game.instance.audioManager.stop();
                Game.instance.audioManager.play("move_piece.mid", 0);
            }
        }
        selection = null;
        repaint();
    }

    /**
     * Méthode général qui dessine l'échiquier dans la fenêtre.
     * Il faut noter qu'un "rendu" ne se fait que lorsqu'il est nécessaire. On ne va pas redessiner
     * l'écran s'il ne se passe rien.
     * Voici les étapes de rendu:
     * <ol>
     *     <li>L'échiquier ainsi que son fond d'écran</li>
     *     <li>Les logos correspondant aux pièces</li>
     *     <li>Les mouvements (si une pièce est sélectionnée)</li>
     *     <li>Les checks (Cas où le roi est échec)</li>
     * </ol>
     *
     * @param graph Java2D
     */
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
        // On dessine les mouvements
        drawMovements(g);
        // On dessine les checks
        drawChecks(g);
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
                final Position position = calculatePositionPixels(new Position(xCase, yCase));
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
                Position position = calculatePositionPixels(piece.position);
                g.drawImage(image, position.x, position.y, tailleCase, tailleCase, null, null);
            }
        }
    }

    public void drawMovements(Graphics2D g) {
        if(selection != null) {
            Piece piece = board.at(selection).orElse(null);
            if(piece == null) return;
            for(Position move: piece.movements(board)) {
                Position pixels = calculatePositionPixels(move);
                g.setColor(new Color(255, 50, 255, 50));
                g.fillRect(pixels.x, pixels.y, tailleCase, tailleCase);
            }
        }
    }

    public void drawChecks(Graphics2D g) {
        for(Team team: board.teams) {
            if(team.isCheck(board)) {
                if(team.isCheckmate(board)) {
                    Position pixels = calculatePositionPixels(team.roi().position);
                    g.setColor(new Color(255, 0, 0, 150));
                    g.fillRect(pixels.x, pixels.y, tailleCase, tailleCase);
                    Game.instance.audioManager.stop();
                    Game.instance.audioManager.play("mat.mid", 0);
                } else {
                    Position pixels = calculatePositionPixels(team.roi().position);
                    g.setColor(new Color(255, 0, 0, 50));
                    g.fillRect(pixels.x, pixels.y, tailleCase, tailleCase);
                }
            }
        }
    }

    /**
     * Retourne la position (en pixels) à partir de la position sur l'échiquier.
     *
     * @param position Position sur l'échiquier
     * @return La position en pixels.
     */
    public Position calculatePositionPixels(Position position) {
        return pos(margeX + (position.x - 1) * tailleCase, margeY + (position.y - 1) * tailleCase);
    }

}
