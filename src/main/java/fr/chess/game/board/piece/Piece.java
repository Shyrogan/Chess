package fr.chess.game.board.piece;

import fr.chess.game.board.team.Team;
import fr.chess.game.math.Position;
import fr.chess.game.piece.PieceType;

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
     * @param other L'autre pièce.
     * @return {@code true} si la pièce indiquée n'est pas dans la même équipe, false sinon.
     */
    public boolean isEnemy(Piece other) {
        return team != other.team;
    }

}
