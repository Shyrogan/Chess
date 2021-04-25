package fr.chess.game.piece.impl;

import fr.chess.game.board.Board;
import fr.chess.game.board.team.Team;
import fr.chess.game.math.Position;
import fr.chess.game.piece.PieceType;

import java.util.HashSet;
import java.util.Set;

import static fr.chess.game.math.Position.pos;

public class Cavalier implements PieceType {

    @Override
    public String name() {
        return "cavalier";
    }

    @Override
    public Set<Position> mouvements(Board board, Position current, Team team) {
        Set<Position> result = new HashSet<>();
        result.add(pos(current.x + 2, current.y + 1));
        result.add(pos(current.x + 2, current.y - 1));
        result.add(pos(current.x - 2, current.y + 1));
        result.add(pos(current.x - 2, current.y - 1));
        result.add(pos(current.x + 1, current.y + 2));
        result.add(pos(current.x + 1, current.y - 2));
        result.add(pos(current.x - 1, current.y + 2));
        result.add(pos(current.x - 1, current.y - 2));

        result.removeIf(p -> !p.isInBoard() || board.at(p).map(pi -> !pi.isEnemy(team)).orElse(false));
        
        return result;
    }
}
