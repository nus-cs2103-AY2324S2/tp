package seedu.address.model;

import javafx.collections.ObservableList;
import seedu.address.model.person.Person;

/**
 * Unmodifiable view of an address book
 */
public interface ReadOnlyNetConnect {

    /**
     * Returns an unmodifiable view of the persons list.
     * This list will not contain any duplicate persons and any duplicate ids.
     */
    ObservableList<Person> getPersonList();

}
