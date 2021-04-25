package fr.chess.game.board;

import fr.chess.game.board.movement.BoardMovement;
import fr.chess.game.board.piece.Piece;
import fr.chess.game.board.team.Team;
import fr.chess.game.board.team.Teams;
import fr.chess.game.math.Position;

import java.util.List;
import java.util.Optional;

public class Board {

    /**
     * La taille de l'échiquier, on va considérer "dans l'échiquier" toute position qui respecte la condition
     * proposé par l'objet {@link Position}
     *
     * @see Position#isInBoard()
     */
    public static final int SIZE_BOARD = 8;

    /** Les équipes disponibles dans la partie **/
    public final List<Team> teams;
    /** Le tour actuel **/
    public int tour = 0;

    /**
     * Créer un échiquier avec les équipes indiqués.
     *
     * @param teams Les équipes.
     */
    public Board(List<Team> teams) {
        this.teams = teams;
    }

    /**
     * Créer un échiquier avec les deux équipes de bases.
     * Ses équipes son générées par la classe {@link Teams}
     */
    public Board() {
         teams = List.of(
                 Teams.createWhite(),
                 Teams.createBlack()
        );
    }

    /**
     * Créer un nouveau {@link BoardMovement}.
     *
     * @param from Position de départ
     * @param to Position d'arrivée
     * @return Un {@link BoardMovement}
     */
    public BoardMovement move(Position from, Position to) {
        return new BoardMovement(this, from, to);
    }

    /**
     * Récupère la pièce à la position indiquée.
     *
     * @param position La position.
     * @return La pièce (si elle est présente).
     */
    public Optional<Piece> at(Position position) {
        for(Team team: teams) {
            if(team.pieces.containsKey(position))
                return Optional.of(team.pieces.get(position));
        }
        return Optional.empty();
    }

    /**
     * Défini la pièce à la position indiquée.
     *
     * @param position Position
     * @param piece Piece
     * @return L'échiquier
     */
    public Board set(Position position, Piece piece) {
        for(Team team: teams) {
            if(piece != null) {
                if(team.equals(piece.team)) {
                    team.pieces.put(position, piece);
                } else {
                    team.pieces.remove(position);
                }
            } else {
                team.pieces.remove(position);
            }
        }
        return this;
    }

}
