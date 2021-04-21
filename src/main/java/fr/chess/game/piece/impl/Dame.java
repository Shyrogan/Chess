package fr.chess.game.piece.impl;

import fr.chess.game.board.Board;
import fr.chess.game.board.team.Team;
import fr.chess.game.math.Position;
import fr.chess.game.piece.PieceType;
import fr.chess.game.piece.movement.PathIterator;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

import static fr.chess.game.math.Position.pos;
import static java.util.Collections.emptyList;
import static java.util.Collections.emptySet;

public class Dame implements PieceType {

    @Override
    public String name() {
        return "dame";
    }

    @Override
    public List<PathIterator> mouvements(Board board, Position current, Team team) {
        return List.of(
                // Fou
                PathIterator.builder()
                        .next(p -> pos(p.x + 1, p.y + 1))
                        .build(),
                PathIterator.builder()
                        .next(p -> pos(p.x - 1, p.y + 1))
                        .build(),
                PathIterator.builder()
                        .next(p -> pos(p.x + 1, p.y - 1))
                        .build(),
                PathIterator.builder()
                        .next(p -> pos(p.x - 1, p.y - 1))
                        .build(),
                // Tour
                PathIterator.builder()
                        .next(p -> pos(p.x + 1, p.y))
                        .build(),
                PathIterator.builder()
                        .next(p -> pos(p.x, p.y + 1))
                        .build(),
                PathIterator.builder()
                        .next(p -> pos(p.x - 1, p.y))
                        .build(),
                PathIterator.builder()
                        .next(p -> pos(p.x, p.y - 1))
                        .build()
        );
    }

}
