package fr.chess.game.piece.impl;

import fr.chess.game.board.Board;
import fr.chess.game.board.team.Team;
import fr.chess.game.math.Position;
import fr.chess.game.piece.PieceType;

import java.util.Set;

import static fr.chess.game.piece.PieceType.fou;
import static fr.chess.game.piece.PieceType.tour;

public class Dame implements PieceType {

    @Override
    public String name() {
        return "dame";
    }

    @Override
    public Set<Position> mouvements(Board board, Position current, Team team) {
        Set<Position> fou = fou().mouvements(board, current, team);
        Set<Position> tour = tour().mouvements(board, current, team);

        fou.addAll(tour);

        return fou;
    }

}
