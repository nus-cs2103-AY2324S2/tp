package seedu.address.model.appointment;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

public class AppointmentId {

    // Variable storing appointment id as a string object
    public final String appointmentId;
    public static final String VALIDATION_REGEX = "a\\d{8}";

    // Message to output in case constraints are not met
    public static final String MESSAGE_CONSTRAINTS =
            "Appointment id should be in the format of aXXXXXXXX.";

    /**
     * Constructs new AppointmentId object using an input appointment id string in aXXXXXXXX format
     * @param apptId input string to be stored
     */
    public AppointmentId(String apptId) {
        requireNonNull(apptId);
        checkArgument(isValidApptId(apptId), MESSAGE_CONSTRAINTS);
        this.appointmentId = apptId;
    }

    /**
     * Checks if a provided input appointment id string is in a valid format
     * @param apptId input date string
     * @return boolean indicating if format is valid or not
     */
    private boolean isValidApptId(String apptId) {
        return apptId.matches(VALIDATION_REGEX);

    }

    /**
     * Returns string version of appointment id for printing
     * @return String stringed appointment date
     */
    @Override
    public String toString() {
        return appointmentId.toString();
    }

    /**
     * Checks if input object is practically equal to this AppointmentId object
     * @param obj input object
     * @return boolean indicating if compared objects are equal
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(obj instanceof AppointmentId)) {
            return false;
        }

        AppointmentId ai = (AppointmentId) obj;
        return appointmentId.equals(ai.appointmentId);
    }

    /**
     * Returns hashcode of appointment id
     * @return int hashcode
     */
    @Override
    public int hashCode() {
        String postfix = appointmentId.substring(1,appointmentId.length());
        Integer prefix = (int) appointmentId.charAt(0);
        String prefixString = prefix.toString();

        return Integer.valueOf(prefixString + postfix);
    }
}
