package seedu.address.model.appointment;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Objects;

import seedu.address.commons.core.date.Date;
import seedu.address.commons.util.ToStringBuilder;
import seedu.address.model.patient.Nric;

/**
 * Represents an Appointment in the application.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Appointment {

    // Identity fields
    private final Nric nric;

    // Data fields
    private final Date date;
    private final TimePeriod timePeriod;
    private final AppointmentType appointmentType;
    private final Note note;
    private Mark mark;

    /**
     * Every field must be present and not null.
     */
    public Appointment(Nric nric, Date date, TimePeriod timePeriod,
                       AppointmentType appointmentType, Note note, Mark mark) {
        requireAllNonNull(nric, date, timePeriod, appointmentType, note);
        this.nric = nric;
        this.date = date;
        this.timePeriod = timePeriod;
        this.appointmentType = appointmentType;
        this.note = note;
        this.mark = mark;
    }

    public Nric getNric() {
        return nric;
    }

    public Date getDate() {
        return date;
    }

    public TimePeriod getTimePeriod() {
        return timePeriod;
    }

    public Time getStartTime() {
        return timePeriod.getStartTime();
    }

    public Time getEndTime() {
        return timePeriod.getEndTime();
    }

    public AppointmentType getAppointmentType() {
        return appointmentType;
    }

    public Note getNote() {
        return note;
    }

    public Mark getMark() {
        return mark;
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
                && otherAppointment.nric.equals(nric)
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
        return nric.equals(otherAppointment.nric)
                && date.equals(otherAppointment.date)
                && timePeriod.equals(otherAppointment.timePeriod)
                && appointmentType.equals(otherAppointment.appointmentType)
                && note.equals(otherAppointment.note)
                && mark.equals(otherAppointment.mark);
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(nric, date, timePeriod, appointmentType, note, mark);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("nric", nric)
                .add("date", date)
                .add("timePeriod", timePeriod)
                .add("appointmentType", appointmentType)
                .add("note", note)
                .add("mark", mark)
                .toString();
    }
}
