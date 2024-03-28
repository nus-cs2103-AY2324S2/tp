package seedu.address.model;

import javafx.collections.ObservableList;
import seedu.address.model.exam.Exam;
import seedu.address.model.person.Person;

/**
 * Unmodifiable view of an address book
 */
public interface ReadOnlyAddressBook {

    /**
     * Returns an unmodifiable view of the persons list.
     * This list will not contain any duplicate persons.
     */
    ObservableList<Person> getPersonList();

    /**
     * Returns an unmodifiable view of the exams list.
     * This list will not contain any duplicate persons.
     */
    ObservableList<Exam> getExamList();

}
