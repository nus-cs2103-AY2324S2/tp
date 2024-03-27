package seedu.address.model;

import static java.util.Objects.requireNonNull;

import java.util.List;

import javafx.collections.ObservableList;
import seedu.address.commons.util.ToStringBuilder;
import seedu.address.model.person.Client;
import seedu.address.model.person.Housekeeper;
import seedu.address.model.person.Person;
import seedu.address.model.person.UniquePersonList;

/**
 * Wraps all data at the address-book level
 * Duplicates are not allowed (by .isSamePerson comparison)
 */
public class AddressBook implements ReadOnlyAddressBook {

    private final UniquePersonList<Client> clients;
    private final UniquePersonList<Housekeeper> housekeepers;

    /*
     * The 'unusual' code block below is a non-static initialization block, sometimes used to avoid duplication
     * between constructors. See https://docs.oracle.com/javase/tutorial/java/javaOO/initial.html
     *
     * Note that non-static init blocks are not recommended to use. There are other ways to avoid duplication
     *   among constructors.
     */
    {
        clients = new UniquePersonList<Client>();
        housekeepers = new UniquePersonList<Housekeeper>();
    }

    public AddressBook() {}

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
    /*public void setPersons(List<Person> persons) {
        this.persons.setPersons(persons);
    }*/

    /**
     * Replaces the contents of the client list with {@code clients}.
     * {@code clients} must not contain duplicate clients.
     */
    public void setClients(List<Client> clients) {
        this.clients.setPersons(clients);
    }

    /**
     * Replaces the contents of the housekeeper list with {@code housekeepers}.
     * {@code housekeepers} must not contain duplicate housekeepers.
     */
    public void setHousekeepers(List<Housekeeper> housekeepers) {
        this.housekeepers.setPersons(housekeepers);
    }

    /**
     * Resets the existing data of this {@code AddressBook} with {@code newData}.
     */
    public void resetData(ReadOnlyAddressBook newData) {
        requireNonNull(newData);

        setClients(newData.getClientList());
        setHousekeepers(newData.getHousekeeperList());
        //setPersons(newData.getPersonList());
    }

    //// person-level operations

    /**
     * Returns true if a person with the same identity as {@code person} exists in the address book.
     */
    public boolean hasPerson(Person person) {
        requireNonNull(person);
        if (person.isClient()) {
            return clients.contains((Client) person);
        }
        return housekeepers.contains((Housekeeper) person);
    }

    /**
     * Adds a person to the address book.
     * The person must not already exist in the address book.
     */
    public void addPerson(Person p) {
        if (p.isClient()) {
            clients.add((Client) p);
        } else {
            housekeepers.add((Housekeeper) p);
        }
    }

    /**
     * Replaces the given person {@code target} in the list with {@code editedPerson}.
     * {@code target} must exist in the address book.
     * The person identity of {@code editedPerson} must not be the same as another existing person in the address book.
     */
    public void setPerson(Person target, Person editedPerson) {
        requireNonNull(editedPerson);

        if (editedPerson.isClient() && target.isClient()) {
            clients.setPerson((Client) target, (Client) editedPerson);
        } else if (!(editedPerson.isClient()) && !(target.isClient())) {
            housekeepers.setPerson((Housekeeper) target, (Housekeeper) editedPerson);
        } else {
            throw new IllegalArgumentException("Cannot replace a client with a housekeeper or vice versa.");
        }
    }

    /**
     * Removes {@code key} from this {@code AddressBook}.
     * {@code key} must exist in the address book.
     */
    public void removePerson(Person key) {
        if (key.isClient()) {
            clients.remove((Client) key);
        } else {
            housekeepers.remove((Housekeeper) key);
        }
    }

    //// util methods

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("clients", clients)
                .add("housekeepers", housekeepers)
                //.add("persons", persons)
                .toString();
    }

    /*@Override
    public ObservableList<Person> getPersonList() {
        return persons.asUnmodifiableObservableList();
    }*/

    @Override
    public ObservableList<Client> getClientList() {
        return clients.asUnmodifiableObservableList();
    }

    @Override
    public ObservableList<Housekeeper> getHousekeeperList() {
        return housekeepers.asUnmodifiableObservableList();
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
        return clients.equals(otherAddressBook.clients)
                && housekeepers.equals(otherAddressBook.housekeepers);
    }

    @Override
    public int hashCode() {
        int result = 17;
        result = 31 * result + clients.hashCode();
        result = 31 * result + housekeepers.hashCode();
        return result;
    }
}
