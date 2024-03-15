package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a patient's admission date in the address book.
 */
public class AdmissionDate {
    public static final String MESSAGE_CONSTRAINTS =
            "Admission dates can take any date, and it should be in DD/MM/YYYY";
    public static final String VALIDATION_REGEX = "^(0[1-9]|[12][0-9]|3[01])/(0[1-9]|1[0-2])/[0-9]{4}$";

    public final String admissionDate;

    /**
     * Constructs a {@code AdmissionDate}.
     *
     * @param admissionDate A valid admission date.
     */
    public AdmissionDate(String admissionDate) {
        requireNonNull(admissionDate);
        checkArgument(isValidAdmissionDate(admissionDate), MESSAGE_CONSTRAINTS);
        this.admissionDate = admissionDate;
    }
    public static boolean isValidAdmissionDate(String admissionDate) {
        return admissionDate.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return admissionDate.toString();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof AdmissionDate)) {
            return false;
        }

        AdmissionDate otherAdmissionDate = (AdmissionDate) other;
        return admissionDate.equals(otherAdmissionDate.admissionDate);
    }

    @Override
    public int hashCode() {
        return admissionDate.hashCode();
    }
}
