package seedu.address.logic.parser.fuzzy;

/**
 * Interface for matching
 * @param <T>
 */
public interface CommandMatcher<T> {
    /**
     * Finds the closest match to the given command input by user
     * @param item the command item to find the closest match for
     * @return the closest matched command Item
     */
    T findClosestMatch(T item);
}
