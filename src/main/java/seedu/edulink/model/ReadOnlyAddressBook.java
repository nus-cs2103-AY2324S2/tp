package seedu.edulink.model;

import javafx.collections.ObservableList;
import seedu.edulink.model.student.Student;

/**
 * Unmodifiable view of an address book
 */
public interface ReadOnlyAddressBook {

    /**
     * Returns an unmodifiable view of the persons list.
     * This list will not contain any duplicate persons.
     */
    ObservableList<Student> getPersonList();

}
