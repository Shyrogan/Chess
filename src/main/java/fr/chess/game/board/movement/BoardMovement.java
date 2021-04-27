package fr.chess.game.board.movement;

import fr.chess.game.board.Board;
import fr.chess.game.board.piece.Piece;
import fr.chess.game.math.Position;

/**
 * {@link BoardMovement} est une classe utilitaire utilisée afin d'effectuer des opérations sur les mouvements:
 * <ol>
 *    <li>Vérifier s'il est possible</li>
 *    <li>Faire le mouvement</li>
 *    <li>L'annuler, potentiellement</li>
 * </ol>
 */
public class BoardMovement {

    /** L'échiquier sur lequel on travail **/
    public final Board board;
    /** Les positions de départ et d'arriver **/
    public final Position from, to;
    /** Les pièces de départ et d'arriver **/
    public final Piece fromPiece, toPiece;

    /**
     * Crée un nouveau {@link BoardMovement} et prépare les variables nécessaires à son utilisation.
     *
     * @param board L'échiquier
     * @param from La position de départ du mouvement
     * @param to La position d'arriver
     */
    public BoardMovement(Board board, Position from, Position to) {
        this.board = board;
        this.from = from;
        this.to = to;
        this.fromPiece = board.at(from).orElse(null);
        this.toPiece = board.at(to).orElse(null);
    }

    /**
     * Vérifie si le mouvement est possible en l'effectuant puis l'annulant une fois que les
     * calculs par rapport au roi ont été fait.
     *
     * @return {@code true} si le mouvement est possible, false sinon.
     */
    public boolean isPossible() {
        // On vérifie qu'il s'agit bien de l'équipe qui joue!
        if(!fromPiece.team.equals(board.teams.get(board.tour % board.teams.size())))
            return false;

        boolean isLegal = true;
        apply();
        // Si l'équipe qui bouge est check après le mouvement ce n'est pas un bon mouvement!!
        if(fromPiece.team.isCheck(board))
            isLegal = false;
        undo();

        return isLegal;
    }

    /**
     * Applique le mouvement dans l'échiquier.
     *
     * @return Le {@link BoardMovement}
     */
    public BoardMovement apply() {
        board.set(from, null);
        board.set(to, new Piece(fromPiece.team, to, fromPiece.type));
        fromPiece.type.onMove(board, to, fromPiece.team);

        return this;
    }

    /**
     * Annule le mouvement dans l'échiquier.
     *
     * @return Le {@link BoardMovement}
     */
    public BoardMovement undo() {
        board.set(from, fromPiece);
        board.set(to, toPiece);

        return this;
    }

}
