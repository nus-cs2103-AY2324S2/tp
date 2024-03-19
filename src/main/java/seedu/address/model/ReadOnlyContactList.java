package seedu.address.model;

import javafx.collections.ObservableList;
import seedu.address.model.coursemate.CourseMate;

/**
 * Unmodifiable view of an contact list
 */
public interface ReadOnlyContactList {

    /**
     * Returns an unmodifiable view of the course mates list.
     * This list will not contain any duplicate course mates.
     */
    ObservableList<CourseMate> getCourseMateList();

}
