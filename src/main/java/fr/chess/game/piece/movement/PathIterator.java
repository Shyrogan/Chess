package fr.chess.game.piece.movement;

import fr.chess.game.math.Position;

import java.util.Iterator;
import java.util.function.Function;

import static java.util.Objects.requireNonNull;

public class PathIterator implements Iterator<Position> {

    /**
     * @return Un nouveau {@link Builder}
     */
    public static Builder builder() {
        return new Builder();
    }

    /** Une condition simple qui vérifie si la pièce est dans l'échiquier uniquement. **/
    public final static Condition IN_BOARD_CONDITION = Position::isInBoard;
    /** Une condition qui arrête l'itération **/
    public final static Condition FALSE              = (position) -> false;

    /** La position actuelle, commence par la position de la pièce **/
    public Position current;
    /** La condition pour vérifier si l'itération doit continuer **/
    private final Condition condition;
    /** La fonction qui nous permet de récupérer le pion suivant **/
    private final Function<Position, Position> nextFunction;

    PathIterator(Position current, Condition condition, Function<Position, Position> nextFunction) {
        this.current      = current;
        this.condition    = condition;
        this.nextFunction = nextFunction;
    }

    /**
     * @return Vérifie s'il y a une future position à ce chemin...
     */
    @Override
    public boolean hasNext() {
        return condition.canPath(nextFunction.apply(current));
    }

    /**
     * @return Récupère la future position.
     */
    @Override
    public Position next() {
        return current = nextFunction.apply(current);
    }

    /**
     * Puisque chaque chemin peut-avoir des conditions spécifiques (par exemple, considérons le pion
     * qui a les chemins dit "de côté" qui n'ont lieu qu'en présence d'une pièce), il nous faut
     * une manière de stocker cette condition dans notre itérateur.
     *
     * Cette interface permet de stocker et appliquer notre condition.
     */
    @FunctionalInterface
    public interface Condition {
        /**
         * @param position La position à vérifier.
         * @return {@code true} si la pièce peut accéder à ce chemin sinon, false.
         */
        boolean canPath(Position position);
    }

    public static class Builder {
        private Position start;
        private Function<Position, Position> nextFunction;
        private Condition condition = IN_BOARD_CONDITION;

        public Builder start(Position position) {
            this.start = position;
            return this;
        }

        public Builder next(Function<Position, Position> nextFunction) {
            this.nextFunction = nextFunction;
            return this;
        }

        public Builder condition(Condition condition) {
            this.condition = condition;
            return this;
        }

        public PathIterator build() {
            return new PathIterator(requireNonNull(start), condition, requireNonNull(nextFunction));
        }
    }

}
