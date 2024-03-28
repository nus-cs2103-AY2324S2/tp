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
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.person.Maintainer;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Staff;
import seedu.address.model.person.Supplier;

/**
 * Represents the in-memory model of the address book data.
 */
public class ModelManager implements Model {
    private static final Logger logger = LogsCenter.getLogger(ModelManager.class);

    private final VersionedAddressBook addressBook;
    private final UserPrefs userPrefs;
    private final FilteredList<Person> filteredPersons;

    /**
     * Initializes a ModelManager with the given addressBook and userPrefs.
     */
    public ModelManager(ReadOnlyAddressBook addressBook, ReadOnlyUserPrefs userPrefs) {
        requireAllNonNull(addressBook, userPrefs);

        logger.fine("Initializing with address book: " + addressBook + " and user prefs " + userPrefs);

        this.addressBook = new VersionedAddressBook(addressBook);
        this.userPrefs = new UserPrefs(userPrefs);
        filteredPersons = new FilteredList<>(this.addressBook.getPersonList());
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
    public boolean hasPerson(Person person) {
        requireNonNull(person);
        return addressBook.hasPerson(person);
    }

    @Override
    public void deletePerson(Person target) {
        addressBook.removePerson(target);
        commitAddressBook();
    }

    @Override
    public void addPerson(Person person) {
        addressBook.addPerson(person);
        commitAddressBook();
        updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
    }

    @Override
    public void setPerson(Person target, Person editedPerson) {
        requireAllNonNull(target, editedPerson);

        addressBook.setPerson(target, editedPerson);
        commitAddressBook();
    }

    @Override
    public void commitAddressBook() {
        addressBook.commit();
        logger.fine("New commit on address book: " + addressBook + " and user prefs " + userPrefs);
    }

    @Override
    public void undoAddressBook() {
        addressBook.undo();
        logger.fine("Previous commit is retrieved to address book: "
                + addressBook + " and user prefs " + userPrefs);
    }

    @Override
    public void redoAddressBook() {
        addressBook.redo();
        logger.fine("Recent commit is retrieved to address book: "
                + addressBook + " and user prefs " + userPrefs);
    }

    @Override
    public boolean canUndo() {
        return addressBook.canUndo();
    }

    @Override
    public boolean canRedo() {
        return addressBook.canRedo();
    }

    //=========== Filtered Person List Accessors =============================================================

    /**
     * Returns an unmodifiable view of the list of {@code Person} backed by the internal list of
     * {@code versionedAddressBook}
     */
    @Override
    public ObservableList<Person> getFilteredPersonList() {
        return filteredPersons;
    }

    @Override
    public void updateFilteredPersonList(Predicate<Person> predicate) {
        requireNonNull(predicate);
        filteredPersons.setPredicate(predicate);
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
                && filteredPersons.equals(otherModelManager.filteredPersons);
    }

    /**
     * Update the person list to display pinned contacts first.
     */
    public void updatePinnedPersonList() {
        addressBook.updatePinnedList();
    }

    /**
     * Find a general contact by their name.
     * @param targetName Refers to the name identifier.
     * @param message Refers to the exception message for the specific command.
     * @return Person that matches the name.
     * @throws CommandException Handles invalid person message.
     */
    @Override
    public Person findByName(Name targetName, String message) throws CommandException {
        for (Person person: this.addressBook.getPersonList()) {
            Name name = person.getName();
            if (name.equals(targetName)) {
                return person;
            }
        }
        throw new CommandException(message);
    }

    /**
     * Find the person by their name.
     * @param targetName Refers to the name identifier.
     * @param message Refers to the exception message for the specific command.
     * @return Person that matches the name.
     * @throws CommandException Handles invalid person message.
     */
    @Override
    public Person findPersonByName(Name targetName, String message) throws CommandException {
        for (Person person: this.addressBook.getPersonList()) {
            Name name = person.getName();
            if (name.equals(targetName)) {
                if (!(person instanceof Supplier) && !(person instanceof Staff)
                        && !(person instanceof Maintainer)) {
                    return person;
                }
            }
        }
        throw new CommandException(message);
    }

    /**
     * Find the maintainer by their name.
     * @param targetName Refers to the name identifier.
     * @param message Refers to the exception message for the specific command.
     * @return Maintainer that matches the name.
     * @throws CommandException Handles invalid maintainer message.
     */
    @Override
    public Maintainer findMaintainerByName(Name targetName, String message) throws CommandException {
        for (Person person: this.addressBook.getPersonList()) {
            Name name = person.getName();
            if (name.equals(targetName) && person instanceof Maintainer) {
                return (Maintainer) person;
            }
        }
        throw new CommandException(message);
    }

    /**
     * Find the staff by their name.
     * @param targetName Refers to the name identifier.
     * @param message Refers to the exception message for the specific command.
     * @return Staff that matches the name.
     * @throws CommandException Handles invalid staff message.
     */
    @Override
    public Staff findStaffByName(Name targetName, String message) throws CommandException {
        for (Person person: this.addressBook.getPersonList()) {
            Name name = person.getName();
            if (name.equals(targetName) && person instanceof Staff) {
                return (Staff) person;
            }
        }
        throw new CommandException(message);
    }

    /**
     * Find the supplier by their name.
     * @param targetName Refers to the name identifier.
     * @param message Refers to the exception message for the specific command.
     * @return Supplier that matches the name.
     * @throws CommandException Handles invalid supplier message.
     */
    @Override
    public Supplier findSupplierByName(Name targetName, String message) throws CommandException {
        for (Person person: this.addressBook.getPersonList()) {
            Name name = person.getName();
            if (name.equals(targetName) && person instanceof Supplier) {
                return (Supplier) person;
            }
        }
        throw new CommandException(message);
    }
}
