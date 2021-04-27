package fr.chess.game.piece;

import fr.chess.game.piece.impl.*;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.toMap;

/**
 * Ici on a pris une approche assez "unique". En effet, vu les calculs complexes que nécessite le jeu,
 * les types de pièce sont des singletons.
 *
 * @see PieceType
 */
public class PieceRegistry {

    /** Les différents types de pièce disponibles. **/
    private final List<PieceType> pieces = Arrays.asList(
            new Pion(),
            new Cavalier(),
            new Fou(),
            new Tour(),
            new Dame(),
            new Roi()
    );

    /** Un dictionnaire qui associe le type de pièce à son nom. **/
    private final Map<String, PieceType> pieceTypeByName = pieces.stream()
            .collect(toMap(PieceType::name, o -> o));

    /**
     * Trouve le type de pièce par son nom.
     *
     * @param name Le nom.
     * @return Le type de pièce.
     */
    public PieceType find(String name) {
        return pieceTypeByName.get(name);
    }

}
