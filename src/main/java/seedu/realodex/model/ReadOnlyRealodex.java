package seedu.realodex.model;

import javafx.collections.ObservableList;
import seedu.realodex.model.person.Person;

/**
 * Unmodifiable view of an realodex
 */
public interface ReadOnlyRealodex {

    /**
     * Returns an unmodifiable view of the persons list.
     * This list will not contain any duplicate persons.
     */
    ObservableList<Person> getPersonList();

}
