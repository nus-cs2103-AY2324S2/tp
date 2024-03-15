package seedu.address.model.appointment;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents an AppointmentType in CLInic.
 * Guarantees: immutable; name is valid as declared in {@link #isValidAppointmentType(String)}
 */
public class AppointmentType {

    public static final String MESSAGE_CONSTRAINTS = "Appointment type should not be empty";
    public static final String VALIDATION_REGEX = ".+";

    public final String typeName;

    /**
     * Constructs an {@code AppointmentType}.
     *
     * @param typeName A valid appointment type name.
     */
    public AppointmentType(String typeName) {
        requireNonNull(typeName);
        checkArgument(isValidAppointmentType(typeName), MESSAGE_CONSTRAINTS);
        this.typeName = typeName;
    }

    /**
     * Returns true if a given string is a valid appointment type name.
     */
    public static boolean isValidAppointmentType(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof AppointmentType)) {
            return false;
        }

        AppointmentType otherType = (AppointmentType) other;
        return typeName.equals(otherType.typeName);
    }

    @Override
    public int hashCode() {
        return typeName.hashCode();
    }

    /**
     * Format state as text for viewing.
     */
    public String toString() {
        return '[' + typeName + ']';
    }

}