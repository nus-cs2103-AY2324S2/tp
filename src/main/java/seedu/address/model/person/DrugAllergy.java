package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Person's drug allergy in the patient book.
 * Guarantees: immutable; is valid as declared in {@link #isValidDrugAllergy(String)}
 */
public class DrugAllergy {

    public static final String MESSAGE_CONSTRAINTS =
            "Drug Allergy must not be empty and can contain alphanumerics, spaces and special characters";
    /*
     * drugAllergy must not be empty and contain alphanumerics, spaces and special characters
     */
    public static final String VALIDATION_REGEX = "^(?!\\s*$).+";
    public final String drugAllergy;

    /**
     * Constructs a {@code DrugAllergy}.
     *
     * @param drugAllergy A valid drug allergy.
     */
    public DrugAllergy(String drugAllergy) {
        requireNonNull(drugAllergy);
        checkArgument(isValidDrugAllergy(drugAllergy), MESSAGE_CONSTRAINTS);
        this.drugAllergy = drugAllergy;
    }

    /**
     * Returns true if a given string is a valid drugAllergy.
     */
    public static boolean isValidDrugAllergy(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return drugAllergy;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof DrugAllergy)) {
            return false;
        }

        DrugAllergy otherDrugAllergy = (DrugAllergy) other;
        return drugAllergy.equals(otherDrugAllergy.drugAllergy);
    }

    @Override
    public int hashCode() {
        return drugAllergy.hashCode();
    }
}
