package seedu.address.model;

import static java.util.Objects.requireNonNull;

import java.util.List;
import java.util.Stack;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javafx.collections.ObservableList;
import seedu.address.commons.util.ToStringBuilder;
import seedu.address.model.asset.Asset;
import seedu.address.model.exceptions.AddressBookUndoException;
import seedu.address.model.person.Person;
import seedu.address.model.person.UniquePersonList;

/**
 * Wraps all data at the address-book level
 * Duplicates are not allowed (by .isSamePerson comparison)
 */
public class AddressBook implements ReadOnlyAddressBook {
    private final UniquePersonList persons = new UniquePersonList();
    @JsonIgnore
    private final Stack<UniquePersonList> undoList = new Stack<>();

    public AddressBook() {
    }

    /**
     * Creates an AddressBook using the Persons in the {@code toBeCopied}
     */
    public AddressBook(ReadOnlyAddressBook toBeCopied) {
        persons.setPersons(toBeCopied.getPersonList());
    }

    //// list overwrite operations

    /**
     * Makes a copy of persons, and stores it in personsList.
     */
    private void save() {
        UniquePersonList savedList = new UniquePersonList();
        savedList.setPersons(persons);
        undoList.add(savedList);
    }

    /**
     * Returns true if there are states to reverse to.
     */
    public boolean canUndo() {
        return !undoList.empty();
    }

    /**
     * Undoes the latest change to address book.
     */
    public void undo() {
        if (undoList.empty()) {
            throw new AddressBookUndoException();
        }
        persons.setPersons(undoList.pop());
    }

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
        requireNonNull(person);
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
        requireNonNull(editedPerson);
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
        requireNonNull(asset);
        return persons.asUnmodifiableObservableList().stream().anyMatch(person -> person.hasAsset(asset));
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
