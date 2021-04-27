package fr.chess.game.board.piece;

import fr.chess.game.board.Board;
import fr.chess.game.board.team.Team;
import fr.chess.game.math.Position;
import fr.chess.game.piece.PieceType;

import java.util.Objects;
import java.util.Set;

/**
 * Contient les données à propos d'une pièce <b>sur l'échiquer</b>.
 */
public class Piece {

    public final Team team;
    public final Position  position;
    public final PieceType type;

    public Piece(Team team, Position position, PieceType type) {
        this.team = team;
        this.position = position;
        this.type = type;
    }

    /**
     * Déplace la pièce.
     *
     * @param position Future position.
     * @return Une nouvelle pièce à la bonne position.
     */
    public Piece moveTo(Position position) {
        return new Piece(team, position, type);
    }

    /**
     * Vérifie si la pièce indiquée est ennemie avec celle-ci.
     *
     * @param other L'autre pièce.
     * @return {@code true} si la pièce indiquée n'est pas dans la même équipe, false sinon.
     */
    public boolean isEnemy(Piece other) {
        return team != other.team;
    }

    /**
     * Vérifie si la team indiquée est ennemie avec celle de cette pièce.
     *
     * @param other L'autre team.
     * @return {@code true} si la pièce indiquée n'est pas dans la même équipe, false sinon.
     */
    public boolean isEnemy(Team other) {
        return team != other;
    }

    /**
     * Récupère les mouvements du type de la pièce.
     *
     * @param board Echiquier
     * @return Les mouvements
     */
    public Set<Position> movements(Board board) {
        return type.mouvements(board, position, team);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Piece piece = (Piece) o;
        return Objects.equals(team, piece.team) && Objects.equals(position, piece.position) && Objects.equals(type, piece.type);
    }

    @Override
    public int hashCode() {
        return Objects.hash(team, position, type);
    }

    @Override
    public String toString() {
        return "Piece{" + "team=" + team + ", position=" + position + ", type=" + type + '}';
    }
}
