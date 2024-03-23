package seedu.address.commons.util;

/**
 * Represents a pair of values.
 *
 * @param <T> the type of the first param.
 * @param <U> the type of the second param.
 */
public class Pair<T, U> {
    private final T t;
    private final U u;

    /**
     * Constructor for Pair.
     *
     * @param t the first param.
     * @param u the second param.
     */
    public Pair(T t, U u) {
        this.t = t;
        this.u = u;
    }

    public T getFirst() {
        return t;
    }

    public U getSecond() {
        return u;
    }

}
