package scrolls.elder.model;

import javafx.collections.ObservableList;
import scrolls.elder.model.person.Person;

/**
 * Unmodifiable view of an address book
 */
public interface ReadOnlyAddressBook {
    /**
     * Returns the current ID counter.
     */
    int getGlobalId();

    /**
     * Returns an unmodifiable view of the persons list.
     * This list will not contain any duplicate persons.
     */
    ObservableList<Person> getPersonList();

}
