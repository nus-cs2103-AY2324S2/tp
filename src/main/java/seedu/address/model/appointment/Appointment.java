package seedu.address.model.appointment;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.time.LocalDate;
import java.util.Objects;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.model.person.Name;

/**
 * Represents an Appointment in the application.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Appointment {

    // Identity fields
    private final Name name;

    // Data fields
    private final LocalDate date;
    private final TimePeriod timePeriod;
    private final AppointmentType appointmentType;
    private final Note note;

    /**
     * Every field must be present and not null.
     */
    public Appointment(Name name, LocalDate date, TimePeriod timePeriod,
                       AppointmentType appointmentType, Note note) {
        requireAllNonNull(name, date, timePeriod, appointmentType, note);
        this.name = name;
        this.date = date;
        this.timePeriod = timePeriod;
        this.appointmentType = appointmentType;
        this.note = note;
    }

    public Name getName() {
        return name;
    }

    public LocalDate getDate() {
        return date;
    }

    public TimePeriod getTimePeriod() {
        return timePeriod;
    }

    public AppointmentType getAppointmentType() {
        return appointmentType;
    }

    public Note getNote() {
        return note;
    }

    /**
     * Returns true if both appointments have the same patient, date and time periods.
     * This defines a weaker notion of equality between two appointments.
     */
    public boolean isSameAppointment(Appointment otherAppointment) {
        if (otherAppointment == this) {
            return true;
        }
        return otherAppointment != null
                && otherAppointment.name.equals(name)
                && otherAppointment.date.equals(date)
                && otherAppointment.timePeriod.equals(timePeriod);
    }

    /**
     * Returns true if both appointments have the same patient and data fields.
     * This defines a stronger notion of equality between two appointments.
     */
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
        return name.equals(otherAppointment.name)
                && date.equals(otherAppointment.date)
                && timePeriod.equals(otherAppointment.timePeriod)
                && appointmentType.equals(otherAppointment.appointmentType)
                && note.equals(otherAppointment.note);
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name, date, timePeriod, appointmentType, note);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("name", name)
                .add("date", date)
                .add("timePeriod", timePeriod)
                .add("appointmentType", appointmentType)
                .add("note", note)
                .toString();
    }
}
