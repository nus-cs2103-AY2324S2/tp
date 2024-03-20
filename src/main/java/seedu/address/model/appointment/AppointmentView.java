package seedu.address.model.appointment;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Objects;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.model.person.Name;

/**
 * Represents an AppointmentView in CLInic.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class AppointmentView {

    private final Name name;
    private final Appointment appointment;

    /**
     * Constructs an {@code AppointmentView}.
     */
    public AppointmentView(Name name, Appointment appointment) {
        requireAllNonNull(name, appointment);
        this.name = name;
        this.appointment = appointment;
    }

    public Name getName() {
        return name;
    }

    public Appointment getAppointment() {
        return appointment;
    }

    /**
     * Returns true if both appointments view have the same patient, date and time periods.
     * This defines a weaker notion of equality between two appointment views.
     */
    public boolean isSameAppointmentView(AppointmentView otherAppointment) {
        if (otherAppointment == this) {
            return true;
        }
        return otherAppointment != null
                && otherAppointment.name.equals(name)
                && otherAppointment.appointment.equals(appointment);
    }

    /**
     * Returns true if both appointment views have the same patient and data fields.
     * This defines a stronger notion of equality between two appointment views.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof AppointmentView)) {
            return false;
        }

        AppointmentView otherAppointment = (AppointmentView) other;
        return name.equals(otherAppointment.name)
                && appointment.equals(otherAppointment.appointment);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, appointment);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("name", name)
                .add("appointment", appointment)
                .toString();
    }

}
