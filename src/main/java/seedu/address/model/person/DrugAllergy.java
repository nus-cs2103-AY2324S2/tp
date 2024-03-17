package seedu.address.model.person;

import static java.util.Objects.requireNonNull;

/**
 * Represents a Person's drug allergy in the patient book.
 * Guarantees: immutable; is valid as declared in {@link #isValidDrugAllergy(String)}
 */
public class DrugAllergy {

    public static final String MESSAGE_CONSTRAINTS =
            "Drug Allergy can only contain alphanumerics and spaces";

    public final String drugAllergy;

    /**
     * Constructs a {@code DrugAllergy}.
     *
     * @param drugAllergy A valid drug allergy.
     */
    public DrugAllergy(String drugAllergy) {
        requireNonNull(drugAllergy);
        this.drugAllergy = drugAllergy;
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
