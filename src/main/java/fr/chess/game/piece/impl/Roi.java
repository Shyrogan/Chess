package fr.chess.game.piece.impl;

import fr.chess.game.board.Board;
import fr.chess.game.board.movement.BoardMovement;
import fr.chess.game.board.piece.Piece;
import fr.chess.game.board.team.Team;
import fr.chess.game.math.Position;
import fr.chess.game.piece.PieceType;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import static fr.chess.game.math.Position.pos;

public class Roi implements PieceType {

    @Override
    public String name() {
        return "roi";
    }

    @Override
    public Set<Position> mouvements(Board board, Position current, Team team) {
        Set<Position> result = new HashSet<>();
        for(int x = -1; x <= 1; x++) {
            for(int y = -1; y <= 1; y++) {
                if(x == 0 && y == 0)
                    continue;

                Position move = pos(current.x + x, current.y + y);
                if(board.at(move).map(piece -> piece.isEnemy(team)).orElse(true))
                    result.add(move);
            }
        }
        result.removeIf(p -> !p.isInBoard());
        return result;
    }

    /**
     * Vérifie si le roi est en échec.
     *
     * @param board Board
     * @param kingPosition Position du roi.
     * @param kingTeam Team du roi.
     * @return Si le roi est en échec.
     */
    public boolean isCheck(Board board, Position kingPosition, Team kingTeam) {
        for(Team team: board.teams) {
            if(team.equals(kingTeam)) continue;
            for(Piece piece: team.pieces.values()) {
                if(piece.movements(board).contains(kingPosition))
                    return true;
            }
        }
        return false;
    }

    /**
     * Vérifie si le roi est échec et mat.
     *
     * @param board Echiquier
     * @param kingPosition Position du roi
     * @param kingTeam Equipe du roi
     * @return Si le roi est échec et mat
     */
    public boolean isCheckmate(Board board, Position kingPosition, Team kingTeam) {
        // On récupère la pièce attaquante
        for(Position move: mouvements(board, kingPosition, kingTeam)) {
            BoardMovement movement = new BoardMovement(board, kingPosition, move);
            if(movement.isPossible()) {
                System.out.println(move);
                return false;
            }
        }
        Map<Position, Piece> pieces = new HashMap<>(kingTeam.pieces);
        for(Piece piece: pieces.values()) {
            if(piece.position == kingPosition) continue;
            for(Position move: piece.movements(board)) {
                BoardMovement movement = new BoardMovement(board, piece.position, move);
                if(movement.isPossible()) {
                    System.out.println(piece);
                    return false;
                }
            }
        }

        // Si elle peut-être attaquée alors on n'est pas échec et maths sinon on l'est!
        return true;
    }

}
