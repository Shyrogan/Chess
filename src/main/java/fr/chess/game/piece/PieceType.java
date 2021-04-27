package fr.chess.game.piece;

import fr.chess.game.Game;
import fr.chess.game.board.Board;
import fr.chess.game.board.team.Team;
import fr.chess.game.math.Position;

import java.util.Set;

/**
 * Interface représentant un type de pièce de manière général.
 */
public interface PieceType {

    /**
     * Retourne le type {@link fr.chess.game.piece.impl.Pion}.
     *
     * @return Le pion.
     */
    static PieceType cavalier() {
        return Game.instance.pieceRegistry.find("cavalier");
    }

    /**
     * Retourne le type {@link fr.chess.game.piece.impl.Pion}.
     *
     * @return Le pion.
     */
    static PieceType dame() {
        return Game.instance.pieceRegistry.find("dame");
    }

    /**
     * Retourne le type {@link fr.chess.game.piece.impl.Pion}.
     *
     * @return Le pion.
     */
    static PieceType roi() {
        return Game.instance.pieceRegistry.find("roi");
    }

    /**
     * Retourne le type {@link fr.chess.game.piece.impl.Pion}.
     *
     * @return Le pion.
     */
    static PieceType fou() {
        return Game.instance.pieceRegistry.find("fou");
    }

    /**
     * Retourne le type {@link fr.chess.game.piece.impl.Pion}.
     *
     * @return Le pion.
     */
    static PieceType pion() {
        return Game.instance.pieceRegistry.find("pion");
    }

    /**
     * Retourne le type {@link fr.chess.game.piece.impl.Pion}.
     *
     * @return Le pion.
     */
    static PieceType tour() {
        return Game.instance.pieceRegistry.find("tour");
    }

    /**
     * @return Le nom de la pièce.
     */
    String name();

    /**
     * Retourne une {@link Set} des mouvements possible afin qu'ils puissent-être
     * analysés.
     *
     * @param board L'échiquier
     * @param current La position de départ
     * @param team L'équipe
     *
     * @return La {@link Set} des positions.
     */
    Set<Position> mouvements(Board board, Position current, Team team);

    /**
     * Méthode appelée lorsque qu'une pièce de ce type est bougée.
     *
     * @param board Board
     * @param position Position
     * @param team L'équipe de la pièce.
     */
    default void onMove(Board board, Position position, Team team) {}

}
