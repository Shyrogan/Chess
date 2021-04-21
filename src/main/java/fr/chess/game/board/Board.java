package fr.chess.game.board;

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

    // Les équipes disponibles.
    public final List<Team> teams;

    public Board(List<Team> teams) {
        this.teams = teams;
    }

    public Board() {
         teams = List.of(
                 Teams.createWhite(),
                 Teams.createBlack()
        );
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

    public Board copy() {
        return new Board();
    }

}
