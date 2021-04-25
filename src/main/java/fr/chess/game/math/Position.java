package fr.chess.game.math;

import java.util.Objects;

import static fr.chess.game.board.Board.SIZE_BOARD;
import static java.lang.Math.PI;
import static java.lang.Math.atan2;

/**
 * Cet objet sert à stocker les informations à propos d'une position (généralement sur l'échiquier).
 * Néanmoins, il peut-être aussi utilisé pour le rendu (positionnement des cases etc...)
 *
 * Il est assez simple est non-modifiable car le positionnement d'une pièce ne dépend pas de la valeur stockée.
 */
public class Position {

    /**
     * Créer une nouvelle position et la renvoie.
     *
     * @param x X.
     * @param y Y.
     * @return La position.
     */
    public static Position pos(int x, int y) {
        return new Position(x, y);
    }

    /** Contient les valeurs des axes X et Y **/
    public final int x, y;

    /**
     * Créer la position avec les valeurs indiquées.
     *
     * @param x Position X.
     * @param y Position Y.
     */
    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * @param other L'autre position.
     * @return La distance entre les deux points (au carré).
     */
    public double distSq(Position other) {
        double diffX = x - other.x;
        double diffY = y - other.y;

        return diffX * diffX + diffY + diffY;
    }

    /**
     * @param other The other position.
     * @return This position with the other position added to it.
     */
    public Position add(Position other) {
        return pos(x + other.x, y + other.y);
    }

    /**
     * @return {@code true} si cette position est sur l'échiquier, {@code false} sinon.
     */
    public boolean isInBoard() {
        return x >= 1 && x <= SIZE_BOARD && y >= 1 && y <= SIZE_BOARD;
    }

    /**
     * Retourne l'angle entre deux positions.
     *
     * @param autre Autre position
     * @return L'angle
     */
    public double angle(Position autre) {
        double theta = atan2(autre.x - x, autre.y - y);
        if(theta < 0.0)
            theta += PI + PI;
        return theta;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Position position = (Position) o;
        return x == position.x && y == position.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    @Override
    public String toString() {
        return "Position{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}
