package seedu.address.model.appointment;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.tag.Tag;

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

    public UUID getUUID() {
        return id;
    }

}
