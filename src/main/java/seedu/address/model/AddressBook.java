package seedu.address.model;

import static java.util.Objects.requireNonNull;

import java.util.List;
import java.util.Objects;

import javafx.collections.ObservableList;
import seedu.address.commons.util.ToStringBuilder;
import seedu.address.model.person.Person;
import seedu.address.model.person.UniquePersonList;
import seedu.address.model.reservation.Reservation;
import seedu.address.model.reservation.UniqueReservationList;

/**
 * Wraps all data at the address-book level
 * Duplicates are not allowed (by .isSamePerson comparison)
 */
public class AddressBook implements ReadOnlyAddressBook {

    private final UniquePersonList persons;

    private final UniquePersonList archivedPersons;

    private final UniqueReservationList reservations;


    /**
     * Creates an empty AddressBook with no persons and reservations.
     */
    public AddressBook() {
        this.persons = new UniquePersonList();
        this.reservations = new UniqueReservationList();
        this.archivedPersons = new UniquePersonList();
    }

    /**
     * Creates an AddressBook using the Persons in the {@code toBeCopied}
     */
    public AddressBook(ReadOnlyAddressBook toBeCopied) {
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
     * Replaces the contents of the archivedPerons list with {@code persons}.
     * {@code persons} must not contain duplicate persons.
     */
    public void setArchivedPersons(List<Person> archivedPersons) {
        this.archivedPersons.setPersons(archivedPersons);
    }
      
    /**
     * Replaces the contents of the person list with {@code persons}.
     * {@code persons} must not contain duplicate persons.
     */
    public void setReservations(List<Reservation> reservations) {
        this.reservations.setReservations(reservations);
    }

    /**
     * Resets the existing data of this {@code AddressBook} with {@code newData}.
     */
    public void resetData(ReadOnlyAddressBook newData) {
        requireNonNull(newData);
        setPersons(newData.getPersonList());
        setArchivedPersons(newData.getArchivedPersonList());
        setReservations(newData.getReservationList());
    }

    //// person-level operations

    /**
     * Returns true if a person with the same identity as {@code person} exists in the address book.
     */
    public boolean hasPerson(Person person) {
        requireNonNull(person);
        return persons.contains(person) || archivedPersons.contains(person);
    }

    /**
     * Adds a person to the address book.
     * The person must not already exist in the address book.
     */
    public void addPerson(Person p) {
        persons.add(p);
    }

    /**
     * Adds a person to the archive list in the address book.
     * The person must not already exist in the address book.
     */
    public void addArchivedPerson(Person p) {
        archivedPersons.add(p);
    }

    /**
     * Replaces the given person {@code target} in the list with {@code editedPerson}.
     * {@code target} must exist in the address book.
     * The person identity of {@code editedPerson} must not be the same as another existing person in the address book.
     */
    public void setPerson(Person target, Person editedPerson) {
        requireNonNull(editedPerson);
        if (persons.contains(target)) {
            persons.setPerson(target, editedPerson);
        } else {
            editedPerson.setArchived(true);
            archivedPersons.setPerson(target, editedPerson);
        }
    }

    /**
     * Removes {@code key} from this {@code AddressBook}.
     * {@code key} must exist in the address book.
     */
    public void removePerson(Person key) {
        if (persons.contains(key)) {
            persons.remove(key);
        } else {
            archivedPersons.remove(key);
        }
    }

    /**
     * Archives a person by removing them from the main list of persons and adding them to the archive list.
     * The person must exist in the address book's main list but not in the archived list before this operation.
     *
     * @param person The person to be archived. This person must exist in the address book.
     */
    public void archivePerson(Person person) {
        persons.remove(person);
        archivedPersons.add(person);
    }

    /**
     * Unarchives a person by removing them from the archive list and adding them back to the main list of persons.
     * The person must exist in the address book's archived list but not in the main list before this operation.
     *
     * @param person The person to be unarchived. This person must exist in the archive list of the address book.
     */
    public void unarchivePerson(Person person) {
        archivedPersons.remove(person);
        persons.add(person);
    }

    //// reservation-level operations

    /**
     * Returns true if a reservation by the same person and has same date and time as {@code reservation}
     * exists in the address book.
     */
    public boolean hasReservation(Reservation reservation) {
        requireNonNull(reservation);
        return reservations.contains(reservation);
    }

    /**
     * Adds a reservation to the address book.
     * The reservation must not already exist in the address book.
     */
    public void addReservation(Reservation r) {
        reservations.add(r);
    }

    /**
     * Removes {@code key} from this {@code AddressBook}.
     * {@code key} must exist in the address book.
     */
    public void removeReservation(Reservation key) {
        reservations.remove(key);
    }

    //// util methods

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("persons", persons)
                .add("archivedPersons", archivedPersons)
                .toString();
    }

    @Override
    public ObservableList<Person> getPersonList() {
        return persons.asUnmodifiableObservableList();
    }

    @Override
    public ObservableList<Person> getArchivedPersonList() {
        return archivedPersons.asUnmodifiableObservableList();
    }
    
    @Override
    public ObservableList<Reservation> getReservationList() {
        return reservations.asUnmodifiableObservableList();
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
        return persons.equals(otherAddressBook.persons)
                && archivedPersons.equals(otherAddressBook.archivedPersons)
                && reservations.equals(otherAddressBook.reservations);
    }

    @Override
    public int hashCode() {
        return Objects.hash(persons, archivedPersons);
    }
}
