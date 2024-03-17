package seedu.address.model.appointment;

import java.util.UUID;

/**
 * Represents an Appointment in the address book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Appointment {

    // Data fields
    private final UUID id;
    private final UUID personId;
    private final AppointmentTime appointmentTime;

    /**
     * Every field must be present and not null.
     */
    public Appointment(UUID personId, AppointmentTime appointmentTime) {
        this.id = UUID.randomUUID();
        this.appointmentTime = appointmentTime;
        this.personId = personId;
    }

    /**
     * Every field must be present and not null.
     */
    public Appointment(UUID id, UUID personId, AppointmentTime appointmentTime) {
        this.id = id;
        this.appointmentTime = appointmentTime;
        this.personId = personId;
    }


    public UUID getId() {
        return id;
    }

    public String getIdString() {
        return id.toString();
    }

    public UUID getPersonId() {
        return personId;
    }

    public String getPersonIdString() {
        return personId.toString();
    }

    public AppointmentTime getAppointmentTime() {
        return appointmentTime;
    }

    public String getAppointmentTimeString() {
        return appointmentTime.toString();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof Appointment)) {
            return false;
        }

        Appointment otherAppointment = (Appointment) other;
        boolean sameID = id.equals(otherAppointment.getId());
        boolean samePersonId = personId.equals(otherAppointment.getPersonId());
        boolean sameDate = appointmentTime.equals(otherAppointment.getAppointmentTime());
        return (sameID && samePersonId && sameDate);
    }

}
