package seedu.address.model;

import javafx.collections.ObservableList;
import seedu.address.model.group.Group;

/**
 * Unmodifiable view of a group list
 */
public interface ReadOnlyGroupList {
    /**
     * Returns an unmodifiable view of the group list.
     * This list will not contain any duplicate groups.
     */
    ObservableList<Group> getGroupList();
}
