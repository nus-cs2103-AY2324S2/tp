package staffconnect.model;

import static java.util.Objects.requireNonNull;

import java.util.List;

import javafx.collections.ObservableList;
import staffconnect.commons.util.ToStringBuilder;
import staffconnect.model.person.Person;
import staffconnect.model.person.UniquePersonList;

/**
 * Wraps all data at the fundamental level
 * Duplicates are not allowed (by .isSamePerson comparison)
 */
public class StaffBook implements ReadOnlyStaffBook {

    private final UniquePersonList persons;

    /*
     * The 'unusual' code block below is a non-static initialization block, sometimes used to avoid duplication
     * between constructors. See https://docs.oracle.com/javase/tutorial/java/javaOO/initial.html
     *
     * Note that non-static init blocks are not recommended to use. There are other ways to avoid duplication
     *   among constructors.
     */
    {
        persons = new UniquePersonList();
    }

    public StaffBook() {}

    /**
     * Creates a StaffBook using the Persons in the {@code toBeCopied}
     */
    public StaffBook(ReadOnlyStaffBook toBeCopied) {
        this();
        resetData(toBeCopied);
    }

    //// list overwrite operations

    /**
     * Replaces the contents of the person list with {@code persons}.
     * {@code persons} must not contain duplicate persons.
     */
    public void setPersons(List<Person> persons) {
        this.persons.setPersons(persons);
    }

    /**
     * Resets the existing data of this {@code StaffBook} with {@code newData}.
     */
    public void resetData(ReadOnlyStaffBook newData) {
        requireNonNull(newData);

        setPersons(newData.getPersonList());
    }

    //// person-level operations

    /**
     * Returns true if a person with the same identity as {@code person} exists in the staff book.
     */
    public boolean hasPerson(Person person) {
        requireNonNull(person);
        return persons.contains(person);
    }

    /**
     * Adds a person to the staff book.
     * The person must not already exist in the staff book.
     */
    public void addPerson(Person p) {
        persons.add(p);
    }

    /**
     * Replaces the given person {@code target} in the list with {@code editedPerson}.
     * {@code target} must exist in the staff book.
     * The person identity of {@code editedPerson} must not be the same as another existing person in the staff book.
     */
    public void setPerson(Person target, Person editedPerson) {
        requireNonNull(editedPerson);

        persons.setPerson(target, editedPerson);
    }

    /**
     * Removes {@code key} from this {@code StaffBook}.
     * {@code key} must exist in the staff book.
     */
    public void removePerson(Person key) {
        persons.remove(key);
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
        if (!(other instanceof StaffBook)) {
            return false;
        }

        StaffBook otherStaffBook = (StaffBook) other;
        return persons.equals(otherStaffBook.persons);
    }

    @Override
    public int hashCode() {
        return persons.hashCode();
    }
}
