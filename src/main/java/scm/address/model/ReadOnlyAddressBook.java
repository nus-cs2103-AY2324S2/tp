package scm.address.model;

import javafx.collections.ObservableList;
import scm.address.model.person.Person;

/**
 * Unmodifiable view of an contact manager
 */
public interface ReadOnlyAddressBook {

    /**
     * Returns an unmodifiable view of the persons list.
     * This list will not contain any duplicate persons.
     */
    ObservableList<Person> getPersonList();

}
