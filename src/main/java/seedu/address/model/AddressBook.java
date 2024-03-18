package seedu.address.model;

import static java.util.Objects.requireNonNull;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;

import javafx.collections.ObservableList;
import seedu.address.commons.util.ToStringBuilder;
import seedu.address.model.appointment.Appointment;
import seedu.address.model.appointment.UniqueAppointmentList;
import seedu.address.model.person.Person;
import seedu.address.model.person.UniquePersonList;

/**
 * Wraps all data at the address-book level
 * Duplicates are not allowed (by .isSamePerson comparison)
 */
public class AddressBook implements ReadOnlyAddressBook {

    private final UniquePersonList persons;
    private final UniqueAppointmentList appointments;
    private final Map<UUID, Person> personMap;
    private final Map<UUID, Appointment> appointmentMap;

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
    {
        persons = new UniquePersonList();
        appointments = new UniqueAppointmentList();
        personMap = new HashMap<>();
        appointmentMap = new HashMap<>();
    }

    public AddressBook() {
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
        this.personMap.clear();
        for (Person person : persons) {
            this.personMap.put(person.getId(), person);
        }
    }

    /**
     * Replaces the contents of the person list with {@code appointments}.
     * {@code appointments} must not contain duplicate persons.
     */
    public void setAppointments(List<Appointment> appointments) {
        this.appointments.setAppointments(appointments);
    }

    /**
     * Resets the existing data of this {@code AddressBook} with {@code newData}.
     */
    public void resetData(ReadOnlyAddressBook newData) {
        requireNonNull(newData);
        setPersons(newData.getPersonList());
        setAppointments(newData.getAppointmentList());
    }

    //// Person methods

    /**
     * Returns true if a person with the same identity as {@code person} exists in
     * the address book.
     * TODO: Could be O(1) if we use the personMap hashmap to check.
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
        persons.add(p);
        personMap.put(p.getId(), p);
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
        personMap.remove(target.getId());
        personMap.put(editedPerson.getId(), editedPerson);
    }

    /**
     * Removes a {@code Person} from this {@code AddressBook}.
     * {@code Person} must exist in the address book.
     */
    public void removePerson(Person person) {
        persons.remove(person);
        personMap.remove(person.getId());
    }

    /**
     * Gets a {@code Person} object from its given id.
     * {@code personId} must exist in the address book.
     * @param personId
     * @return {@code Person}
     */
    public Person getPersonById(UUID personId) {
        return personMap.get(personId);
    }

    //// Appointment methods

    /**
     * Returns true if the internal list of appointments contains the specified
     * appointment.
     *
     * @param appointment The appointment to check for existence.
     * @return True if the appointment is found in the list, false otherwise.
     * @throws NullPointerException if the given appointment is null.
     */
    public boolean hasAppointment(Appointment appointment) {
        requireNonNull(appointment);
        return appointments.contains(appointment);
    }

    /**
     * Adds an appointment to the list of appointments.
     *
     * @param appointment The appointment to be added.
     */
    public void addAppointment(Appointment appointment) {
        appointments.add(appointment);
    }

    /**
     * Replaces the given appointment {@code target} in the list with
     * {@code editedAppointment}.
     * {@code target} must exist in the address book.
     * The appointment identity of {@code editedAppointment} must not be the same as
     * another existing appointment
     * in the address book.
     */
    public void setAppointment(Appointment target, Appointment editedAppointment) {
        requireNonNull(editedAppointment);

        appointments.setAppointment(target, editedAppointment);
    }

    /**
     * Removes {@code key} from this {@code AddressBook}.
     * {@code key} must exist in the address book.
     */
    public void removeAppointment(Appointment key) {
        appointments.remove(key);
    }

    /**
     * Gets a {@code Appointment} object from its given id.
     *
     * @param appointmentId
     *                      {@code appointmentId} must exist in the address book.
     * @return {@code Appointment}
     */
    public Appointment getAppointmentById(UUID appointmentId) {
        return appointmentMap.get(appointmentId);
    }

    //// util methods

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("persons", persons)
                .add("appointments", appointments)
                .toString();
    }

    @Override
    public ObservableList<Person> getPersonList() {
        return persons.asUnmodifiableObservableList();
    }

    @Override
    public ObservableList<Appointment> getAppointmentList() {
        return appointments.asUnmodifiableObservableList();
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
                && appointments.equals(otherAddressBook.appointments);
    }

    @Override
    public int hashCode() {
        return Objects.hash(persons, appointments);
    }
}
