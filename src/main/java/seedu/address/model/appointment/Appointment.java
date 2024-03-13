package seedu.address.model.appointment;

import java.util.UUID;

import seedu.address.model.person.Person;

/**
 * Represents an Appointment in the address book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Appointment {

    // Data fields
    private final UUID id;
    private final Person person;
    private final AppointmentTime appointmentTime;

    /**
     * Every field must be present and not null.
     */
    public Appointment(Person person, AppointmentTime appointmentTime) {
        this.id = UUID.randomUUID();
        this.appointmentTime = appointmentTime;
        this.person = person;
    }

    public UUID getID() {
        return id;
    }

}
