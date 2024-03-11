package staffconnect.model;

import javafx.collections.ObservableList;
import staffconnect.model.person.Person;

/**
 * Unmodifiable view of a staff book
 */
public interface ReadOnlyStaffBook {

    /**
     * Returns an unmodifiable view of the persons list.
     * This list will not contain any duplicate persons.
     */
    ObservableList<Person> getPersonList();

}
