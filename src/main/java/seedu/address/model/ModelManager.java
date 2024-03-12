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

/**
 * Represents the in-memory model of the address book data.
 */
public class ModelManager implements Model {
    private static final Logger logger = LogsCenter.getLogger(ModelManager.class);

    private final AddressBook addressBook;
    private final UserPrefs userPrefs;
    private final FilteredList<CourseMate> filteredCourseMates;

    /**
     * Initializes a ModelManager with the given addressBook and userPrefs.
     */
    public ModelManager(ReadOnlyAddressBook addressBook, ReadOnlyUserPrefs userPrefs) {
        requireAllNonNull(addressBook, userPrefs);

        logger.fine("Initializing with address book: " + addressBook + " and user prefs " + userPrefs);

        this.addressBook = new AddressBook(addressBook);
        this.userPrefs = new UserPrefs(userPrefs);
        filteredCourseMates = new FilteredList<>(this.addressBook.getCourseMateList());
    }

    public ModelManager() {
        this(new AddressBook(), new UserPrefs());
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
    public Path getAddressBookFilePath() {
        return userPrefs.getAddressBookFilePath();
    }

    @Override
    public void setAddressBookFilePath(Path addressBookFilePath) {
        requireNonNull(addressBookFilePath);
        userPrefs.setAddressBookFilePath(addressBookFilePath);
    }

    //=========== AddressBook ================================================================================

    @Override
    public void setAddressBook(ReadOnlyAddressBook addressBook) {
        this.addressBook.resetData(addressBook);
    }

    @Override
    public ReadOnlyAddressBook getAddressBook() {
        return addressBook;
    }

    @Override
    public boolean hasCourseMate(CourseMate courseMate) {
        requireNonNull(courseMate);
        return addressBook.hasCourseMate(courseMate);
    }

    @Override
    public void deleteCourseMate(CourseMate target) {
        addressBook.removeCourseMate(target);
    }

    @Override
    public void addCourseMate(CourseMate courseMate) {
        addressBook.addCourseMate(courseMate);
        updateFilteredCourseMateList(PREDICATE_SHOW_ALL_COURSE_MATES);
    }

    @Override
    public void setCourseMate(CourseMate target, CourseMate editedCourseMate) {
        requireAllNonNull(target, editedCourseMate);

        addressBook.setCourseMate(target, editedCourseMate);
    }

    //=========== Filtered CourseMate List Accessors =============================================================

    /**
     * Returns an unmodifiable view of the list of {@code CourseMate} backed by the internal list of
     * {@code versionedAddressBook}
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
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof ModelManager)) {
            return false;
        }

        ModelManager otherModelManager = (ModelManager) other;
        return addressBook.equals(otherModelManager.addressBook)
                && userPrefs.equals(otherModelManager.userPrefs)
                && filteredCourseMates.equals(otherModelManager.filteredCourseMates);
    }

}
