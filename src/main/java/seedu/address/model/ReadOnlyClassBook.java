package seedu.address.model;

import javafx.collections.ObservableList;
import seedu.address.model.person.Classes;

public interface ReadOnlyClassBook {
    /**
     * Returns an unmodifiable view of the persons list.
     * This list will not contain any duplicate persons.
     */
    ObservableList<Classes> getClassList();
}
