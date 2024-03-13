package vitalconnect.model;

import javafx.collections.ObservableList;
import vitalconnect.model.person.Person;

/**
 * Unmodifiable view of a clinic
 */
public interface ReadOnlyClinic {

    /**
     * Returns an unmodifiable view of the persons list.
     * This list will not contain any duplicate persons.
     */
    ObservableList<Person> getPersonList();

}
