package fr.chess.game.board.team;

import fr.chess.game.board.Board;
import fr.chess.game.board.piece.Piece;
import fr.chess.game.math.Position;
import fr.chess.game.piece.PieceType;
import fr.chess.game.piece.impl.Roi;

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
     * Raccourcis pour appeler la fonction {@link Roi#isCheck(Board, Position, Team)}
     *
     * @param board Echiquier
     * @return Si la team est en échec.
     */
    public boolean isCheck(Board board) {
        Piece roi = roi();
        return ((Roi)PieceType.roi()).isCheck(board, roi.position, roi.team);
    }

    /**
     * Raccourcis pour appeler la fonction {@link Roi#isCheckmate(Board, Position, Team)}
     *
     * @param board Echiquier
     * @return Si la team est en échec et mat.
     */
    public boolean isCheckmate(Board board) {
        Piece roi = roi();
        return ((Roi)PieceType.roi()).isCheckmate(board, roi.position, roi.team);
    }

    /**
     * @return La pièce correspondant au {@link Roi} de l'équipe.
     */
    public Piece roi() {
        return pieces.values().stream()
                .filter(p -> p.type.name().equalsIgnoreCase("roi"))
                .findFirst().orElse(null);
    }

    /**
     * Ajoute une pièce à la liste des pièces de l'équipe.
     *
     * @param position Position
     * @param type Type
     * @return La team.
     */
    public Team piece(Position position, PieceType type) {
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
        return Objects.equals(name, team.name);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        return Objects.hash(name, pieces);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return "Team{" + "name='" + name + "'}";
    }
}
