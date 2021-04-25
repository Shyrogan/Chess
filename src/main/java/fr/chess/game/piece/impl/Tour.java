package fr.chess.game.piece.impl;

import fr.chess.game.board.Board;
import fr.chess.game.board.piece.Piece;
import fr.chess.game.board.team.Team;
import fr.chess.game.math.Position;
import fr.chess.game.piece.PieceType;

import java.util.*;

import static fr.chess.game.board.Board.SIZE_BOARD;
import static fr.chess.game.math.Position.pos;

public class Tour implements PieceType {

    @Override
    public String name() {
        return "tour";
    }

    @Override
    public Set<Position> mouvements(Board board, Position current, Team team) {
        Set<Position> result = new HashSet<>();

        for(int x = current.x - 1; x >= 1; x--) {
            Position move = pos(x, current.y);
            if(!move.isInBoard()) {
                break;
            }
            Optional<Piece> piece = board.at(move);
            if(piece.isPresent()) {
                if(piece.get().isEnemy(team))
                    result.add(move);
                break;
            } else {
                result.add(move);
            }
        }

        for(int x = current.x + 1; x <= SIZE_BOARD; x++) {
            Position move = pos(x, current.y);
            if(!move.isInBoard()) {
                break;
            }
            Optional<Piece> piece = board.at(move);
            if(piece.isPresent()) {
                if(piece.get().isEnemy(team))
                    result.add(move);
                break;
            } else {
                result.add(move);
            }
        }

        for(int y = current.y - 1; y >= 1; y--) {
            Position move = pos(current.x, y);
            if(!move.isInBoard()) {
                break;
            }
            Optional<Piece> piece = board.at(move);
            if(piece.isPresent()) {
                if(piece.get().isEnemy(team))
                    result.add(move);
                break;
            } else {
                result.add(move);
            }
        }

        for(int y = current.y + 1; y <= SIZE_BOARD; y++) {
            Position move = pos(current.x, y);
            if(!move.isInBoard()) {
                break;
            }
            Optional<Piece> piece = board.at(move);
            if(piece.isPresent()) {
                if(piece.get().isEnemy(team))
                    result.add(move);
                break;
            } else {
                result.add(move);
            }
        }

        return result;
    }

}
