package fr.chess.game;

import fr.chess.game.board.Board;
import fr.chess.game.piece.PieceRegistry;
import fr.chess.game.window.Window;

/**
 * Cette classe gère le "jeu" en lui-même outre l'aspect des échecs c'est-à-dire un potentiel menu,
 * des informations sur le jeu peut-être?..
 */
public class Game {

    /** L'instance du jeu **/
    public final static Game instance = new Game();

    /** Le registre des pièces **/
    public final PieceRegistry pieceRegistry = new PieceRegistry();

    /** L'échiquier **/
    public Board board;

    /** La fenêtre du jeu **/
    public final Window window = new Window();

}
