package seedu.address.model;

import javafx.collections.ObservableList;
import seedu.address.model.person.Classes;

/**
 * Unmodifiable view of a class book
 */
public interface ReadOnlyClassBook {
    /**
     * Returns an unmodifiable view of the persons list.
     * This list will not contain any duplicate persons.
     */
    ObservableList<Classes> getClassList();
}
