package seedu.address.model;

import static java.util.Objects.requireNonNull;

import java.util.List;
import java.util.Stack;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javafx.collections.ObservableList;
import seedu.address.commons.util.ToStringBuilder;
import seedu.address.model.asset.Asset;
import seedu.address.model.exceptions.AddressBookException;
import seedu.address.model.person.Person;
import seedu.address.model.person.UniquePersonList;

/**
 * Wraps all data at the address-book level.
 * Saves states between modifications to allow undo/redo.
 * Duplicates are not allowed (by .isSamePerson comparison).
 */
public class AddressBook implements ReadOnlyAddressBook {

    @JsonIgnore
    public static final String MESSAGE_UNDO_STACK_EMPTY = "There are no previous AddressBook states to return to.";
    @JsonIgnore
    public static final String MESSAGE_REDO_STACK_EMPTY = "There are no previous undo commands to reverse.";
    @JsonIgnore
    private final Stack<UniquePersonList> undoStack = new Stack<>();
    @JsonIgnore
    private final Stack<UniquePersonList> redoStack = new Stack<>();

    private final UniquePersonList persons = new UniquePersonList();

    public AddressBook() {
    }

    /**
     * Creates an AddressBook using the Persons in the {@code toBeCopied}
     */
    public AddressBook(ReadOnlyAddressBook toBeCopied) {
        persons.setPersons(toBeCopied.getPersonList());
    }

    //// undo & redo operations

    /**
     * Makes a copy of persons, and stores it into {@code stack}.
     */
    private void savePersonsTo(Stack<UniquePersonList> stack) {
        UniquePersonList savedList = new UniquePersonList();
        savedList.setPersons(persons);
        stack.add(savedList);
    }

    /**
     * Makes a copy of persons, and stores it in personsList.
     */
    private void save() {
        redoStack.clear();
        savePersonsTo(undoStack);
    }

    /**
     * Returns true if there are states to reverse to.
     */
    public boolean canUndo() {
        return !undoStack.empty();
    }

    /**
     * Undoes the latest change to address book.
     */
    public void undo() {
        if (undoStack.empty()) {
            throw new AddressBookException(MESSAGE_UNDO_STACK_EMPTY);
        }
        savePersonsTo(redoStack);
        persons.setPersons(undoStack.pop());
    }

    /**
     * Returns true if there are undo states to reverse.
     */
    public boolean canRedo() {
        return !redoStack.empty();
    }

    /**
     * Reverses the latest undo command.
     * Does not reverse if a modifying command is executed after undo command.
     */
    public void redo() {
        if (redoStack.empty()) {
            throw new AddressBookException(MESSAGE_REDO_STACK_EMPTY);
        }
        savePersonsTo(undoStack);
        persons.setPersons(redoStack.pop());
    }

    //// list overwrite operations

    /**
     * Replaces the contents of the person list with {@code persons}.
     * {@code persons} must not contain duplicate persons.
     */
    public void setPersons(List<Person> persons) {
        save();
        this.persons.setPersons(persons);
    }

    /**
     * Resets the existing data of this {@code AddressBook} with {@code newData}.
     */
    public void resetData(ReadOnlyAddressBook newData) {
        requireNonNull(newData);

        setPersons(newData.getPersonList());
    }

    //// person-level operations

    /**
     * Returns true if a person with the same identity as {@code person} exists in the address book.
     */
    public boolean hasPerson(Person person) {
        return persons.contains(person);
    }

    /**
     * Adds a person to the address book.
     * The person must not already exist in the address book.
     */
    public void addPerson(Person p) {
        save();
        persons.add(p);
    }

    /**
     * Replaces the given person {@code target} in the list with {@code editedPerson}.
     * {@code target} must exist in the address book.
     * The person identity of {@code editedPerson} must not be the same as another existing person in the address book.
     */
    public void setPerson(Person target, Person editedPerson) {
        save();
        persons.setPerson(target, editedPerson);
    }

    /**
     * Removes {@code key} from this {@code AddressBook}.
     * {@code key} must exist in the address book.
     */
    public void removePerson(Person key) {
        save();
        persons.remove(key);
    }

    //// asset-level operations

    /**
     * Returns true if an asset with the same identity as {@code asset} exists in the address book.
     */
    public boolean hasAsset(Asset asset) {
        return persons.asUnmodifiableObservableList()
                .stream()
                .anyMatch(person -> person.hasAsset(asset));
    }

    /**
     * Changes all assets that equal target to editedAsset.
     */
    public void editAsset(Asset target, Asset editedAsset) {
        save();
        for (Person person : getPersonList()) {
            if (person.hasAsset(target)) {
                Person editedPerson = person.editAsset(target, editedAsset);
                persons.setPerson(person, editedPerson);
            }
        }
    }

    //// util methods

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("persons", persons)
                .toString();
    }

    @Override
    public ObservableList<Person> getPersonList() {
        return persons.asUnmodifiableObservableList();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof AddressBook)) {
            return false;
        }

        AddressBook otherAddressBook = (AddressBook) other;
        return persons.equals(otherAddressBook.persons);
    }

    @Override
    public int hashCode() {
        return persons.hashCode();
    }

}
