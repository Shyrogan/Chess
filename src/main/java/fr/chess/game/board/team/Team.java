package fr.chess.game.board.team;

import fr.chess.game.board.piece.Piece;
import fr.chess.game.math.Position;
import fr.chess.game.piece.PieceType;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * Représente une équipe ainsi que ses pièces.
 */
public class Team {

    /** Le nom de l'équipe. **/
    public final String name;
    /** La direction dans laquelle l'équipe avance **/
    public final Position direction;
    /** Ses pièces. **/
    public final Map<Position, Piece> pieces = new HashMap<>();

    public Team(String name, Position direction) {
        this.name = name;
        this.direction = direction;
    }

    /**
     * Ajoute une pièce à la liste des pièces de l'équipe.
     *
     * @param position Position
     * @param type Type
     * @return La team.
     */
    public Team addPiece(Position position, PieceType type) {
        pieces.put(position, new Piece(this, position, type));
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Team team = (Team) o;
        return Objects.equals(name, team.name) && Objects.equals(pieces, team.pieces);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        return Objects.hash(name, pieces);
    }

    @Override
    public String toString() {
        return "Team{" +
                "name='" + name + '\'' +
                ", direction=" + direction +
                ", pieces=" + pieces +
                '}';
    }
}
