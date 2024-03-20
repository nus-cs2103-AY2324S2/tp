package seedu.address.model;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.nio.file.Path;
import java.util.function.Predicate;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.coursemate.CourseMate;
import seedu.address.model.coursemate.Name;
import seedu.address.model.coursemate.QueryableCourseMate;
import seedu.address.model.coursemate.exceptions.CourseMateNotFoundException;
import seedu.address.model.group.Group;
import seedu.address.model.group.exceptions.GroupNotFoundException;

/**
 * Represents the in-memory model of the contact list data.
 */
public class ModelManager implements Model {
    private static final Logger logger = LogsCenter.getLogger(ModelManager.class);

    private final ContactList contactList;
    private final GroupList groupList;
    private final UserPrefs userPrefs;
    private final FilteredList<CourseMate> filteredCourseMates;
    private final FilteredList<Group> filteredGroups;
    private CourseMate recentlyProcessedCourseMate;

    /**
     * Initializes a ModelManager with the given contact list and userPrefs.
     */
    public ModelManager(ReadOnlyContactList contactList, ReadOnlyUserPrefs userPrefs, ReadOnlyGroupList groupList) {
        requireAllNonNull(contactList, userPrefs);

        logger.fine("Initializing with contact list: " + contactList + " and user prefs " + userPrefs);

        this.contactList = new ContactList(contactList);
        this.userPrefs = new UserPrefs(userPrefs);
        this.groupList = new GroupList(groupList);
        filteredCourseMates = new FilteredList<>(this.contactList.getCourseMateList());
        filteredGroups = new FilteredList<>(this.groupList.getGroupList());
        recentlyProcessedCourseMate = null;
    }

    public ModelManager() {
        this(new ContactList(), new UserPrefs(), new GroupList());
    }

    //=========== UserPrefs ==================================================================================

    @Override
    public void setUserPrefs(ReadOnlyUserPrefs userPrefs) {
        requireNonNull(userPrefs);
        this.userPrefs.resetData(userPrefs);
    }

    @Override
    public ReadOnlyUserPrefs getUserPrefs() {
        return userPrefs;
    }

    @Override
    public GuiSettings getGuiSettings() {
        return userPrefs.getGuiSettings();
    }

    @Override
    public void setGuiSettings(GuiSettings guiSettings) {
        requireNonNull(guiSettings);
        userPrefs.setGuiSettings(guiSettings);
    }

    @Override
    public Path getContactListFilePath() {
        return userPrefs.getContactListFilePath();
    }

    @Override
    public void setContactListFilePath(Path contactListFilePath) {
        requireNonNull(contactListFilePath);
        userPrefs.setContactListFilePath(contactListFilePath);
    }

    //=========== ContactList ================================================================================

    @Override
    public void setContactList(ReadOnlyContactList contactList) {
        this.contactList.resetData(contactList);
    }

    @Override
    public ReadOnlyContactList getContactList() {
        return contactList;
    }

    @Override
    public boolean hasCourseMate(CourseMate courseMate) {
        requireNonNull(courseMate);
        return contactList.hasCourseMate(courseMate);
    }

    @Override
    public void deleteCourseMate(CourseMate target) {
        contactList.removeCourseMate(target);
    }

    @Override
    public void addCourseMate(CourseMate courseMate) {
        contactList.addCourseMate(courseMate);
        updateFilteredCourseMateList(PREDICATE_SHOW_ALL_COURSE_MATES);
    }

    @Override
    public void setCourseMate(CourseMate target, CourseMate editedCourseMate) {
        requireAllNonNull(target, editedCourseMate);

        contactList.setCourseMate(target, editedCourseMate);
    }

    //=========== GroupList ================================================================================
    @Override
    public boolean hasGroup(Group group) {
        requireNonNull(group);
        return groupList.hasGroup(group);
    }

    @Override
    public void deleteGroup(Group target) {
        groupList.removeGroup(target);
    }

    @Override
    public Group findGroup(Name name) throws GroupNotFoundException {
        return groupList.findGroup(name);
    }

    @Override
    public void addGroup(Group group) {
        groupList.addGroup(group);
        updateFilteredGroupList(PREDICATE_SHOW_ALL_GROUPS);
    }

    @Override
    public void setGroup(Group target, Group editedGroup) {
        requireAllNonNull(target, editedGroup);

        groupList.setGroup(target, editedGroup);
    }

    @Override
    public CourseMate findCourseMate(QueryableCourseMate query) throws CourseMateNotFoundException {
        if (query.isIndex()) {
            if (query.getIndex().getZeroBased() == -1) {
                return getRecentlyProcessedCourseMate();
            } else {
                return getFilteredCourseMateList().get(query.getIndex().getZeroBased());
            }
        } else {
            return contactList.findCourseMate(query.getName());
        }
    }

    //=========== Filtered CourseMate List Accessors =============================================================

    /**
     * Returns an unmodifiable view of the list of {@code CourseMate} backed by the internal list of
     * {@code versionedContactList}
     */
    @Override
    public ObservableList<CourseMate> getFilteredCourseMateList() {
        return filteredCourseMates;
    }

    @Override
    public void updateFilteredCourseMateList(Predicate<CourseMate> predicate) {
        requireNonNull(predicate);
        filteredCourseMates.setPredicate(predicate);
    }

    @Override
    public ObservableList<Group> getFilteredGroupList() {
        return filteredGroups;
    }

    @Override
    public void updateFilteredGroupList(Predicate<Group> predicate) {
        requireNonNull(predicate);
        filteredGroups.setPredicate(predicate);
    }

    //=========== Most recently processed course mate ========================================================

    @Override
    public CourseMate getRecentlyProcessedCourseMate() {
        return recentlyProcessedCourseMate;
    }

    @Override
    public void setRecentlyProcessedCourseMate(CourseMate courseMate) {
        recentlyProcessedCourseMate = courseMate;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof ModelManager)) {
            return false;
        }

        ModelManager otherModelManager = (ModelManager) other;
        return contactList.equals(otherModelManager.contactList)
                && userPrefs.equals(otherModelManager.userPrefs)
                && filteredCourseMates.equals(otherModelManager.filteredCourseMates);
    }

}
