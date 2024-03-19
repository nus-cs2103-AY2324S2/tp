package seedu.address.model;

import static java.util.Objects.requireNonNull;

import java.util.List;

import javafx.collections.ObservableList;
import seedu.address.commons.util.ToStringBuilder;
import seedu.address.model.coursemate.Name;
import seedu.address.model.group.Group;
import seedu.address.model.group.UniqueGroupList;

/**
 * Wraps all data at the group list level
 * Duplicates are not allowed (by .isSameGroup comparison)
 */
public class GroupList implements ReadOnlyGroupList {
    private final UniqueGroupList groups = new UniqueGroupList();

    public GroupList() {}

    /**
     * Constructor that copies data from an existing {@code ReadOnlyGroupList} object.
     */
    public GroupList(ReadOnlyGroupList toBeCopied) {
        this();
        resetData(toBeCopied);
    }

    /**
     * Replaces the contents of the group list with {@code groups}.
     * {@code groups} must not contain duplicate groups.
     */
    public void setGroups(List<Group> groups) {
        this.groups.setGroups(groups);
    }

    /**
     * Resets the existing data of this {@code GroupList} with {@code newData}.
     */
    public void resetData(ReadOnlyGroupList newData) {
        requireNonNull(newData);
        setGroups(newData.getGroupList());
    }

    //// group-level operations

    /**
     * Returns true if a courseMate with the same identity as {@code courseMate} exists in the contact list.
     */
    public boolean hasGroup(Group group) {
        requireNonNull(group);
        return groups.contains(group);
    }

    /**
     * Adds a group to the contact list.
     * The group must not already exist in the contact list.
     */
    public void addGroup(Group p) {
        groups.add(p);
    }

    /**
     * Replaces the given group {@code target} in the list with {@code editedGroup}.
     * {@code target} must exist in the contact list.
     * The group identity of {@code editedGroup} must not be the same as
     * another existing group in the contact list.
     */
    public void setGroup(Group target, Group editedGroup) {
        requireNonNull(editedGroup);

        groups.setGroup(target, editedGroup);
    }

    /**
     * Removes {@code key} from this {@code GroupList}.
     * {@code key} must exist in the contact list.
     */
    public void removeGroup(Group key) {
        groups.remove(key);
    }

    /**
     * Find a group with specified {@code name} from this
     */
    public Group findGroup(Name name) {
        return groups.findGroup(name);
    }

    //// util methods

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("groups", groups)
                .toString();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof GroupList)) {
            return false;
        }

        GroupList otherContactList = (GroupList) other;
        return groups.equals(otherContactList.groups);
    }

    @Override
    public int hashCode() {
        return groups.hashCode();
    }

    @Override
    public ObservableList<Group> getGroupList() {
        return groups.asUnmodifiableObservableList();
    }
}
