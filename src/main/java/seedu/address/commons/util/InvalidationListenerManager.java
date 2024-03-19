package seedu.address.commons.util;
import static java.util.Objects.requireNonNull;

import java.util.ArrayList;

import javafx.beans.InvalidationListener;

/**
 * Manages a list of {@link InvalidationListener}.
 */
public class InvalidationListenerManager {
    private final ArrayList<InvalidationListener> listeners = new ArrayList<>();

    /**
     * Adds a listener to the list of listeners.
     */
    public void add(InvalidationListener listener) {
        requireNonNull(listener);
        listeners.add(listener);
    }
}
