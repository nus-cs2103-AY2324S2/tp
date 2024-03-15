package seedu.address.model.person;

import java.time.LocalDate;

/**
 * Represents a patient's admission date in the address book.
 */
public class AdmissionDate {
    public static final String MESSAGE_CONSTRAINTS = "Admission dates can take any values, and it should not be blank";

    public final LocalDate admissionDate;

    /**
     * Constructs a {@code AdmissionDate}.
     *
     * @param admissionDate A valid admission date.
     */
    public AdmissionDate(LocalDate admissionDate) {
        this.admissionDate = admissionDate;
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
