package seedu.address.model;

import javafx.collections.ObservableList;
import seedu.address.model.internship.Internship;

/**
 * Unmodifiable view of an InternshipData
 */
public interface ReadOnlyInternshipData {

    /**
     * Returns an unmodifiable view of the internships list.
     * This list will not contain any duplicate persons.
     */
    ObservableList<Internship> getInternshipList();

}
