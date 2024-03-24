package seedu.address.model;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.nio.file.Path;
import java.util.List;
import java.util.function.Predicate;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.person.Id;
import seedu.address.model.person.Payment;
import seedu.address.model.person.Person;

/**
 * Represents the in-memory model of the address book data.
 */
public class ModelManager implements Model {
    private static final Logger logger = LogsCenter.getLogger(ModelManager.class);

    private final AddressBook addressBook;
    private final UserPrefs userPrefs;
    private final FilteredList<Person> filteredPersons;

    /**
     * Initializes a ModelManager with the given addressBook and userPrefs.
     */
    public ModelManager(ReadOnlyAddressBook addressBook, ReadOnlyUserPrefs userPrefs) {
        requireAllNonNull(addressBook, userPrefs);

        logger.fine("Initializing with address book: " + addressBook + " and user prefs " + userPrefs);

        this.addressBook = new AddressBook(addressBook);
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
    public String getNextUniqueId() {
        int uniqueId = 0;
        // get max unique id with int parse

        List listToProcess = addressBook.getPersonList();
        if (!listToProcess.isEmpty()) {
            for (Person person : addressBook.getPersonList()) {
                int currentId = person.getUniqueId().getInt();
                if (currentId > uniqueId) {
                    uniqueId = currentId;
                }
            }
        }
        // return 6 digit and append leading 0 if necessary
        return String.format("%06d", uniqueId + 1);
    }

    @Override
    public Person getPersonByUniqueId(String uniqueIdStr) {
        for (Person person : addressBook.getPersonList()) {
            Id uniqueId = new Id(uniqueIdStr);
            if (person.getUniqueId().equals(uniqueId)) {
                return person;
            }
        }
        return null;
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

    @Override
    public int getTotalPersons() {
        return addressBook.getTotalPersons();
    }

    @Override
    public void addPaymentToPerson(Id uniqueId, double amount) {
        Person person = getPersonByUniqueId(uniqueId.toString());
        if (person != null) {
            Payment newPayment = new Payment(person.getPayment().getAmount() + amount);
            Person updatedPerson = new Person(person.getName(), person.getPhone(), person.getEmail(), person.getAddress(), person.getTags(), person.getSubject(), uniqueId, newPayment);
            setPerson(person, updatedPerson);
        }
    }

    @Override
    public void markPaymentAsPaid(Id uniqueId, double amount) {
        Person person = getPersonByUniqueId(uniqueId.toString());
        if (person != null) {
            double newAmount = Math.max(0, person.getPayment().getAmount() - amount);
            Payment newPayment = new Payment(newAmount);
            Person updatedPerson = new Person(person.getName(), person.getPhone(), person.getEmail(), person.getAddress(), person.getTags(), person.getSubject(), uniqueId, newPayment);
            setPerson(person, updatedPerson);
        }
    }

    @Override
    public void resetPaymentsForPerson(Id uniqueId) {
        Person person = getPersonByUniqueId(uniqueId.toString());
        if (person != null) {
            Payment newPayment = new Payment(0);
            Person updatedPerson = new Person(person.getName(), person.getPhone(), person.getEmail(), person.getAddress(), person.getTags(), person.getSubject(), uniqueId, newPayment);
            setPerson(person, updatedPerson);
        }
    }
}
