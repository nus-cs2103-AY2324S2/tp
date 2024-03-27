package seedu.address.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.AddressBook;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.person.Person;
import seedu.address.model.reservation.Reservation;

/**
 * An Immutable AddressBook that is serializable to JSON format.
 */
@JsonRootName(value = "addressbook")
class JsonSerializableAddressBook {

    public static final String MESSAGE_DUPLICATE_PERSON = "Persons list contains duplicate person(s).";
    public static final String MESSAGE_DUPLICATE_RESERVATION = "Reservations list contains "
            + "duplicate reservation(s).";

    private final List<JsonAdaptedPerson> persons = new ArrayList<>();
    private final List<JsonAdaptedReservation> reservations = new ArrayList<>();

    private final List<JsonAdaptedPerson> archivedPersons = new ArrayList<>();


    /**
     * Constructs a {@code JsonSerializableAddressBook} with the given persons and reservations.
     */
    @JsonCreator
    public JsonSerializableAddressBook(@JsonProperty("persons") List<JsonAdaptedPerson> persons,
                                       @JsonProperty("archivedPersons") List<JsonAdaptedPerson> archivedPersons,
                                       @JsonProperty("reservations")List<JsonAdaptedReservation> reservations) {
        this.persons.addAll(persons);
        this.archivedPersons.addAll(archivedPersons);
        this.reservations.addAll(reservations);
    }

    /**
     * Converts a given {@code ReadOnlyAddressBook} into this class for Jackson use.
     *
     * @param source future changes to this will not affect the created {@code JsonSerializableAddressBook}.
     */
    public JsonSerializableAddressBook(ReadOnlyAddressBook source) {
        archivedPersons.addAll(source.getArchivedPersonList()
                .stream()
                .map(JsonAdaptedPerson::new)
                .collect(Collectors.toList()));
      
        persons.addAll(source.getPersonList()
                .stream()
                .map(JsonAdaptedPerson::new)
                .collect(Collectors.toList()));
      
        reservations.addAll(source.getReservationList()
                .stream()
                .map(JsonAdaptedReservation::new)
                .collect(Collectors.toList()));
    }

    /**
     * Converts this address book into the model's {@code AddressBook} object.
     *
     * @throws IllegalValueException if there were any data constraints violated.
     */
    public AddressBook toModelType() throws IllegalValueException {
        AddressBook addressBook = new AddressBook();
        for (JsonAdaptedPerson jsonAdaptedPerson : persons) {
            Person person = jsonAdaptedPerson.toModelType();
            if (addressBook.hasPerson(person)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_PERSON);
            }
            addressBook.addPerson(person);
        }
        for (JsonAdaptedPerson jsonAdaptedArchivedPerson : archivedPersons) {
            Person archivedPerson = jsonAdaptedArchivedPerson.toModelType();
            if (addressBook.hasPerson(archivedPerson)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_PERSON);
            }
            archivedPerson.setArchived(true);
            addressBook.addArchivedPerson(archivedPerson);
        for (JsonAdaptedReservation jsonAdaptedReservation : reservations) {
            Reservation reservation = jsonAdaptedReservation.toModelType();
            if (addressBook.hasReservation(reservation)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_RESERVATION);
            }
            addressBook.addReservation(reservation);
        }
        return addressBook;
    }

}
