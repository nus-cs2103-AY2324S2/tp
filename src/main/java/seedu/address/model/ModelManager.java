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
import seedu.address.model.internship.Internship;
import seedu.address.model.person.Person;

/**
 * Represents the in-memory model of the address book data.
 */
public class ModelManager implements Model {
    private static final Logger logger = LogsCenter.getLogger(ModelManager.class);

    private final InternshipData internshipData;
    private final UserPrefs userPrefs;
    private final FilteredList<Internship> filteredInternships;

    /**
     * Initializes a ModelManager with the given addressBook and userPrefs.
     */
    public ModelManager(ReadOnlyInternshipData internshipData, ReadOnlyUserPrefs userPrefs) {
        requireAllNonNull(internshipData, userPrefs);

        logger.fine("Initializing with address book: " + internshipData + " and user prefs " + userPrefs);

        this.internshipData = new InternshipData(internshipData);
        this.userPrefs = new UserPrefs(userPrefs);
        filteredInternships = new FilteredList<>(this.internshipData.getInternshipList());
    }

    public ModelManager() {
        this(new InternshipData(), new UserPrefs());
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
    public Path getInternshipDataFilePath() {
        return userPrefs.getAddressBookFilePath();
    }

    @Override
    public void setInternshipDataFilePath(Path addressBookFilePath) {
        requireNonNull(addressBookFilePath);
        userPrefs.setAddressBookFilePath(addressBookFilePath);
    }

    //=========== AddressBook ================================================================================

    @Override
    public void setInternshipData(ReadOnlyInternshipData internshipData) {
        this.internshipData.resetData(internshipData);
    }

    @Override
    public ReadOnlyInternshipData getInternshipData() {
        return internshipData;
    }

    @Override
    public boolean hasInternship(Internship internship) {
        requireNonNull(internship);
        return internshipData.hasInternship(internship);
    }

    @Override
    public void deleteInternship(Internship target) {
        internshipData.removeInternship(target);
    }

    @Override
    public void addInternship(Internship internship) {
        internshipData.addInternship(internship);
        updateFilteredInternshipList(PREDICATE_SHOW_ALL_INTERNSHIPS);
    }

    @Override
    public void setInternship(Internship target, Internship editedPerson) {
        requireAllNonNull(target, editedPerson);

        internshipData.setInternship(target, editedPerson);
    }

    //=========== Filtered Person List Accessors =============================================================

    /**
     * Returns an unmodifiable view of the list of {@code Person} backed by the internal list of
     * {@code versionedAddressBook}
     */
    @Override
    public ObservableList<Internship> getFilteredInternshipList() {
        return filteredInternships;
    }

    @Override
    public void updateFilteredInternshipList(Predicate<Internship> predicate) {
        requireNonNull(predicate);
        filteredInternships.setPredicate(predicate);
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
        return internshipData.equals(otherModelManager.internshipData)
                && userPrefs.equals(otherModelManager.userPrefs)
                && filteredInternships.equals(otherModelManager.filteredInternships);
    }

}
