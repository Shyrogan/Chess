package fr.chess.game.board.team;

import static fr.chess.game.math.Position.pos;
import static fr.chess.game.piece.PieceType.*;

/**
 * Un utilitaire...
 */
public class Teams {

    /**
     * Créer la team blanche.
     * @return La team blanche.
     */
    public static Team createWhite() {
        Team t = new Team("white", pos(0, -1))
                .addPiece(pos(1, 1), tour())
                .addPiece(pos(2, 1), cavalier())
                .addPiece(pos(3, 1), fou())
                .addPiece(pos(4, 1), roi())
                .addPiece(pos(5, 1), dame())
                .addPiece(pos(6, 1), fou())
                .addPiece(pos(7, 1), cavalier())
                .addPiece(pos(8, 1), tour());

        for(int i = 1; i <= 8; i++) {
            t.addPiece(pos(i, 2), pion());
        }

        return t;
    }

    /**
     * Créer la team noir.
     * @return La team noir.
     */
    public static Team createBlack() {
        Team t = new Team("black", pos(0, -1))
                .addPiece(pos(1, 8), tour())
                .addPiece(pos(2, 8), cavalier())
                .addPiece(pos(3, 8), fou())
                .addPiece(pos(4, 8), roi())
                .addPiece(pos(5, 8), dame())
                .addPiece(pos(6, 8), fou())
                .addPiece(pos(7, 8), cavalier())
                .addPiece(pos(8, 8), tour());

        for(int i = 1; i <= 8; i++) {
            t.addPiece(pos(i, 7), pion());
        }

        return t;
    }

}
