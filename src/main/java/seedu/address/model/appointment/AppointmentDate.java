package seedu.address.model.appointment;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.FormatStyle;

/**
 * Class encapsulating an appointment's date and corresponding methods
 */
public class AppointmentDate {

    // Message to output in case constraints are not met
    public static final String MESSAGE_CONSTRAINTS =
            "Appointment date should be in the format of yyyy-MM-dd.";

    // Variable storing appointment date in a local date instance
    public final LocalDate appointmentDate;

    /**
     * Constructs new AppointmentDate object using an input date string in yyyy-MM-dd format
     * @param dateStr input string to be stored
     */
    public AppointmentDate(String dateStr) {
        requireNonNull(dateStr);
        checkArgument(isValidDate(dateStr), MESSAGE_CONSTRAINTS);
        this.appointmentDate = LocalDate.parse(dateStr);
    }

    /**
     * Overloaded constructor that constructs a new instance using a LocalDate rather than date string
     * @param date LocalDate instance to construct AppointmentDate around
     */
    public AppointmentDate(LocalDate date) {
        requireNonNull(date);
        this.appointmentDate = date;
    }

    /**
     * Checks if a provided input date string is in a valid format
     * @param dateStr input date string
     * @return boolean indicating if format is valid or not
     */
    public static boolean isValidDate(String dateStr) {
        try {
            LocalDate.parse(dateStr);
        } catch (DateTimeParseException e) {
            return false;
        }
        return true;
    }

    /**
     * Returns string version of appointment date for printing
     * @return String stringed appointment date
     */
    @Override
    public String toString() {
        return DateTimeFormatter.ofLocalizedDate(FormatStyle.SHORT).format(this.appointmentDate);
    }

    /**
     * Checks if input object is practically equal to this AppointmentDate object
     * @param obj input object
     * @return boolean indicating if compared objects are equal
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(obj instanceof AppointmentDate)) {
            return false;
        }

        AppointmentDate ad = (AppointmentDate) obj;
        return appointmentDate.equals(ad.appointmentDate);
    }

    /**
     * Returns hashcode of appointment date
     * @return int hashcode
     */
    @Override
    public int hashCode() {
        return appointmentDate.hashCode();
    }

    /**
     * Compares two AppointmentDate instances together
     * @param compareValue value to compare with current instance
     * @return integer reflecting whether compareValue is greater, less, or equal
     */
    public int compareTo(AppointmentDate compareValue) {
        return this.appointmentDate.compareTo(compareValue.appointmentDate);
    }

}
