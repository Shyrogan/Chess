package fr.chess.game.piece.impl;

import fr.chess.game.board.Board;
import fr.chess.game.board.piece.Piece;
import fr.chess.game.board.team.Team;
import fr.chess.game.math.Position;
import fr.chess.game.piece.PieceType;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static fr.chess.game.board.Board.SIZE_BOARD;
import static fr.chess.game.math.Position.pos;
import static fr.chess.game.piece.PieceType.dame;


public class Pion implements PieceType {

    @Override
    public String name() {
        return "pion";
    }

    @Override
    public Set<Position> mouvements(Board board, Position current, Team team) {
        boolean extended = team.name.equalsIgnoreCase("white") && current.y == 2
                || team.name.equalsIgnoreCase("black") && current.y == 7;
        Set<Position> result = new HashSet<>();
        Position move = pos(current.x, current.y + team.direction.y);

        if(!board.at(move).isPresent()) {
            result.add(move);
            if(extended && !board.at(pos(move.x, move.y + team.direction.y)).isPresent())
                result.add(pos(move.x, move.y + team.direction.y));
        }

        Optional<Piece> leftPiece = board.at(pos(move.x - 1, move.y));
        if(leftPiece.map(p -> p.isEnemy(team)).orElse(false))
            result.add(pos(move.x - 1, move.y));

        Optional<Piece> rightPiece = board.at(pos(move.x + 1, move.y));
        if(rightPiece.map(p -> p.isEnemy(team)).orElse(false))
            result.add(pos(move.x + 1, move.y));

        result.removeIf(p -> !p.isInBoard());

        return result;
    }

    /**
     * S'il arrive au bout, on le change par une dame.
     *
     * @param board Board
     * @param position Position
     * @param team L'équipe
     */
    @Override
    public void onMove(Board board, Position position, Team team) {
        if(team.name.equalsIgnoreCase("white") && position.y == SIZE_BOARD
            || team.name.equalsIgnoreCase("black") && position.y == 1) {
            board.set(position, new Piece(team, position, dame()));
        }
    }

}
