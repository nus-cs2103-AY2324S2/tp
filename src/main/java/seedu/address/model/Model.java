package seedu.address.model;

import java.nio.file.Path;
import java.util.List;
import java.util.function.Predicate;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.model.coursemate.CourseMate;
import seedu.address.model.coursemate.Name;
import seedu.address.model.coursemate.QueryableCourseMate;
import seedu.address.model.group.Group;
import seedu.address.model.group.exceptions.GroupNotFoundException;

/**
 * The API of the Model component.
 */
public interface Model {
    /** {@code Predicate} that always evaluate to true */
    Predicate<CourseMate> PREDICATE_SHOW_ALL_COURSE_MATES = unused -> true;

    /** {@code Predicate} that always evaluate to true */
    Predicate<Group> PREDICATE_SHOW_ALL_GROUPS = unused -> true;

    /**
     * Replaces user prefs data with the data in {@code userPrefs}.
     */
    void setUserPrefs(ReadOnlyUserPrefs userPrefs);

    /**
     * Returns the user prefs.
     */
    ReadOnlyUserPrefs getUserPrefs();

    /**
     * Returns the user prefs' GUI settings.
     */
    GuiSettings getGuiSettings();

    /**
     * Sets the user prefs' GUI settings.
     */
    void setGuiSettings(GuiSettings guiSettings);

    /**
     * Returns the user prefs' contact list file path.
     */
    Path getContactListFilePath();

    /**
     * Sets the user prefs' contact list file path.
     */
    void setContactListFilePath(Path contactListFilePath);

    /**
     * Replaces contact list data with the data in {@code contactList}.
     */
    void setContactList(ReadOnlyContactList contactList);

    /** Returns the ContactList */
    ReadOnlyContactList getContactList();

    /**
     * Returns true if a courseMate with the same identity as {@code courseMate} exists in the contact list.
     */
    boolean hasCourseMate(CourseMate courseMate);

    /**
     * Deletes the given courseMate.
     * The courseMate must exist in the contact list.
     */
    void deleteCourseMate(CourseMate target);

    /**
     * Adds the given courseMate.
     * {@code courseMate} must not already exist in the contact list.
     */
    void addCourseMate(CourseMate courseMate);

    /**
     * Replaces the given courseMate {@code target} with {@code editedCourseMate}.
     * {@code target} must exist in the contact list.
     * The courseMate identity of {@code editedCourseMate} must not be the same as another
     * existing courseMate in the contact list.
     */
    void setCourseMate(CourseMate target, CourseMate editedCourseMate);

    /**
     * Returns the user prefs' contact list file path.
     */
    Path getGroupListFilePath();

    /**
     * Sets the user prefs' contact list file path.
     */
    void setGroupListFilePath(Path groupListFilePath);

    /**
     * Replaces contact list data with the data in {@code groupList}.
     */
    void setGroupList(ReadOnlyGroupList groupList);

    /** Returns the GroupList */
    ReadOnlyGroupList getGroupList();

    /**
     * Returns true if a courseMate with the same identity as {@code courseMate} exists in the contact list.
     */
    boolean hasGroup(Group group);

    /**
     * Deletes the given group.
     * The group must exist in the contact list.
     */
    void deleteGroup(Group target);

    /**
     * Finds the group based on name.
     * @throws GroupNotFoundException if group doesn't exist
     */
    Group findGroup(Name name) throws GroupNotFoundException;

    /**
     * Adds the given group.
     * {@code group} must not already exist in the contact list.
     */
    void addGroup(Group group);

    /**
     * Replaces the given group {@code target} with {@code editedGroup}.
     * {@code target} must exist in the contact list.
     * The group identity of {@code editedGroup} must not be the same as another
     * existing group in the contact list.
     */
    void setGroup(Group target, Group editedGroup);
    /**
     * Finds a {@code CourseMate} with the exact same name.
     */
    List<CourseMate> findCourseMate(QueryableCourseMate query);

    /** Returns an unmodifiable view of the filtered courseMate list */
    ObservableList<CourseMate> getFilteredCourseMateList();

    /**
     * Updates the filter of the filtered courseMate list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredCourseMateList(Predicate<CourseMate> predicate);

    /** Returns an unmodifiable view of the filtered group list */
    ObservableList<Group> getFilteredGroupList();

    /**
     * Updates the filter of the filtered group list to filter by the given {@code prediate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredGroupList(Predicate<Group> predicate);

    /** Returns the most recently processed course mate */
    CourseMate getRecentlyProcessedCourseMate();

    /**
     * Sets the most recently processed course mate.
     * The course mate need not exist in the contact list, for example, when the user is deleting
     * a course mate, the most recently processed course mate is set to the course mate that was deleted.
     */
    void setRecentlyProcessedCourseMate(CourseMate courseMate);
}
