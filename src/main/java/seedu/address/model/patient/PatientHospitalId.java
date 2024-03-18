package seedu.address.model.patient;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Patient ID in hospital in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidPatientHospitalId(String)}
 */
public class PatientHospitalId {

    public static final String MESSAGE_CONSTRAINTS =
        "Patient Hospital ID should only contain numeric characters, and it should not be blank";

    /*
     * The first character of the address must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "^[0-9]+$";

    public final String patientHospitalId;

    /**
     * Constructs a {@code id}.
     *
     * @param id A valid patient hospital ID.
     */
    public PatientHospitalId(String id) {
        requireNonNull(id);
        checkArgument(isValidPatientHospitalId(id), MESSAGE_CONSTRAINTS);
        patientHospitalId = id;
    }

    /**
     * Returns true if a given string is a valid name.
     */
    public static boolean isValidPatientHospitalId(String test) {
        return test.matches(VALIDATION_REGEX);
    }


    @Override
    public String toString() {
        return patientHospitalId;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof PatientHospitalId)) {
            return false;
        }

        PatientHospitalId otherId = (PatientHospitalId) other;
        return patientHospitalId.equals(otherId.patientHospitalId);
    }

    @Override
    public int hashCode() {
        return patientHospitalId.hashCode();
    }

}
