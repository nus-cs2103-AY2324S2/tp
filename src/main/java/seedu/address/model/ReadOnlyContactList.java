package seedu.address.model;

import javafx.collections.ObservableList;
import seedu.address.model.coursemate.CourseMate;
import seedu.address.model.group.Group;

/**
 * Unmodifiable view of an contact list
 */
public interface ReadOnlyContactList {

    /**
     * Returns an unmodifiable view of the course mates list.
     * This list will not contain any duplicate course mates.
     */
    ObservableList<CourseMate> getCourseMateList();

    /**
     * Returns an unmodifiable view of the group list.
     * This list will not contain any duplicate groups.
     */
    ObservableList<Group> getGroupList();

}
