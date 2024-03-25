package seedu.address.logic.parser.fuzzy;

/**
 * Represents a function for calculating distance between two items
 * @param <T> type of item
 */
public interface DistanceFunction<T> {
    int calculateDistance(T item1, T item2);
}
