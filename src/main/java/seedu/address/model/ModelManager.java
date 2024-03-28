package seedu.address.model;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.nio.file.Path;
import java.util.Comparator;
import java.util.function.Predicate;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.person.DisplayClient;
import seedu.address.model.person.Person;
import seedu.address.model.person.PolicyList;
import seedu.address.model.policy.Policy;
import seedu.address.model.reminder.ReminderList;
import seedu.address.model.reminder.ReminderType;


/**
 * Represents the in-memory model of the address book data.
 */
public class ModelManager implements Model {
    private static final Logger logger = LogsCenter.getLogger(ModelManager.class);

    private final AddressBook addressBook;
    private final UserPrefs userPrefs;
    private FilteredList<Person> filteredPersons;
    private final DisplayClient displayClient;

    /**
     * Initializes a ModelManager with the given addressBook and userPrefs.
     */
    public ModelManager(ReadOnlyAddressBook addressBook, ReadOnlyUserPrefs userPrefs) {
        requireAllNonNull(addressBook, userPrefs);

        logger.fine("Initializing with address book: " + addressBook + " and user prefs " + userPrefs);

        this.addressBook = new AddressBook(addressBook);
        this.userPrefs = new UserPrefs(userPrefs);
        filteredPersons = new FilteredList<>(this.addressBook.getPersonList());
        displayClient = filteredPersons.isEmpty()
                ? new DisplayClient(null)
                : new DisplayClient(filteredPersons.get(0));
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
    }

    @Override
    public void addPerson(Person person) {
        addressBook.addPerson(person);
        updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
    }

    @Override
    public void setPerson(Person target, Person editedPerson) {
        requireAllNonNull(target, editedPerson);

        addressBook.setPerson(target, editedPerson);
    }

    /**
     * Adds a policy to the person in AddressBook.
     * @param target person to add policy to
     * @param policy policy to be added
     */
    public void addPolicy(Person target, Policy policy) {
        requireAllNonNull(target, policy);
        PolicyList newPolicyList = target.getPolicyList();
        newPolicyList.addPolicy(policy);
        setPerson(target, new Person(target.getName(), target.getPhone(), target.getEmail(), target.getAddress(),
                target.getBirthday(), target.getPriority(), target.getLastMet(), target.getSchedule(), target.getTags(),
                newPolicyList));
    }

    /**
     * Deletes a policy from the person in AddressBook.
     * @param target person to delete policy from
     * @param policyId policy to be deleted
     */
    public void deletePolicy(Person target, String policyId) {
        requireAllNonNull(target, policyId);
        PolicyList newPolicyList = target.getPolicyList();
        newPolicyList.deletePolicy(policyId);
        setPerson(target, new Person(target.getName(), target.getPhone(), target.getEmail(), target.getAddress(),
                target.getBirthday(), target.getPriority(), target.getLastMet(), target.getSchedule(), target.getTags(),
                newPolicyList));
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
    public void sortFilteredPersonList(Comparator<Person> comparator) {
        requireNonNull(comparator);
        ObservableList<Person> sortedList = this.addressBook.getPersonList().sorted(comparator);
        filteredPersons = new FilteredList<>(sortedList);
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

    //=========== Client Being Displayed =====================================================================
    @Override
    public Person getDisplayClient() {
        return displayClient.getDisplayClient();
    }

    @Override
    public boolean hasDisplayClient() {
        return displayClient.hasDisplayClient();
    }

    @Override
    public void clearDisplayClient() {
        displayClient.setDisplayClient(null);
    }

    @Override
    public void setDisplayClient(Person person) {
        displayClient.setDisplayClient(person);
    }

    //=========== PolicyList Displayed =====================================================================
    @Override
    public ReminderList getOverDueLastMet() {
        return new ReminderList(ReminderType.LAST_MET, addressBook.getOverDueLastMet());
    }
    @Override
    public ReminderList getSchedules() {
        return new ReminderList(ReminderType.SCHEDULES, addressBook.getSchedules());
    }

}
