package seedu.address.model;

import static java.util.Objects.requireNonNull;

import java.util.List;

import javafx.collections.ObservableList;
import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.utils.CsvExporter;
import seedu.address.model.person.Id;
import seedu.address.model.person.Person;
import seedu.address.model.person.UniquePersonList;

/**
 * Wraps all data at the address-book level
 * Duplicates are not allowed (by .isSamePerson comparison)
 */
public class NetConnect implements ReadOnlyNetConnect {

    private final UniquePersonList persons;

    /*
     * The 'unusual' code block below is a non-static initialization block,
     * sometimes used to avoid duplication
     * between constructors. See
     * https://docs.oracle.com/javase/tutorial/java/javaOO/initial.html
     *
     * Note that non-static init blocks are not recommended to use. There are other
     * ways to avoid duplication
     * among constructors.
     */
    public NetConnect() {
        persons = new UniquePersonList();
    }

    /**
     * Creates an NetConnect using the Persons in the {@code toBeCopied}
     */
    public NetConnect(ReadOnlyNetConnect toBeCopied) {
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
     * Resets the existing data of this {@code NetConnect} with {@code newData}.
     */
    public void resetData(ReadOnlyNetConnect newData) {
        requireNonNull(newData);

        setPersons(newData.getPersonList());
    }

    //// person-level operations

    /**
     * Returns true if a person with the same identity as {@code person} exists in
     * the address book.
     */
    public boolean hasPerson(Person person) {
        requireNonNull(person);
        return persons.contains(person);
    }

    /**
     * Returns true if a person with the same identity as {@code person} exists in
     * the address book.
     */
    public boolean hasId(Id id) {
        requireNonNull(id);
        return persons.hasId(id);
    }

    public Person getPersonById(Id id) {
        requireNonNull(id);
        return persons.getPersonById(id);
    }

    /**
     * Adds a person to the address book.
     * The person must not already exist in the address book.
     */
    public void addPerson(Person p) {
        persons.add(p);
    }

    /**
     * Replaces the given person {@code target} in the list with
     * {@code editedPerson}.
     * {@code target} must exist in the address book.
     * The person identity of {@code editedPerson} must not be the same as another
     * existing person in the address book.
     */
    public void setPerson(Person target, Person editedPerson) {
        requireNonNull(editedPerson);

        persons.setPerson(target, editedPerson);
    }

    /**
     * Removes {@code key} from this {@code NetConnect}.
     * {@code key} must exist in the address book.
     */
    public void removePerson(Person key) {
        persons.remove(key);
    }


    /**
     * Exports the data from the address book as a CSV file with the specified filename.
     * Returns {@code true} if the export operation is successful, {@code false} otherwise.
     *
     * @return {@code true} if the export operation is successful, {@code false} otherwise.
     */
    public boolean exportCsv(String filename) {
        CsvExporter exporter = new CsvExporter(persons, filename);
        exporter.execute();
        return exporter.getIsSuccessful();
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
        if (!(other instanceof NetConnect)) {
            return false;
        }

        NetConnect otherNetConnect = (NetConnect) other;
        return persons.equals(otherNetConnect.persons);
    }

    @Override
    public int hashCode() {
        return persons.hashCode();
    }
}
