package fr.chess.game.piece.impl;

import fr.chess.game.board.Board;
import fr.chess.game.board.team.Team;
import fr.chess.game.math.Position;
import fr.chess.game.piece.PieceType;
import fr.chess.game.piece.movement.PathIterator;

import java.util.List;

import static fr.chess.game.math.Position.pos;

public class Pion implements PieceType {

    @Override
    public String name() {
        return "pion";
    }

    @Override
    public List<PathIterator> mouvements(Board board, Position current, Team team) {
        final Position front = current.add(team.direction);
        final Position frontLeft = front.add(pos(-1, 0));
        final Position frontRight = front.add(pos(1, 0));

        return List.of(
                // Le chemin allant vers l'avant.
                PathIterator.builder()
                        .start(current)
                        .next(p -> front)
                        .condition(p -> p.isInBoard() && p.distSq(current) == 1.0)
                        .build(),
                // Le chemin s'il y a un ennemi à gauche
                PathIterator.builder()
                        .start(current)
                        .next(p -> frontLeft)
                        .condition(p -> p.isInBoard() && p.distSq(current) == 2.0)
                        .build(),
                // Puis à droite
                PathIterator.builder()
                        .start(current)
                        .next(p -> frontRight)
                        .condition(p -> p.isInBoard() && p.distSq(current) == 2.0)
                        .build()
        );
    }

}
