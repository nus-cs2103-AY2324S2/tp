package seedu.address.model.allergen;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Allergen in the address book.
 * Guarantees: immutable; name is valid as declared in {@link #isValidAllergenName(String)}
 */
public class Allergen {

    public static final String MESSAGE_CONSTRAINTS = "Allergens names should be alphanumeric";
    public static final String VALIDATION_REGEX = "\\p{Alnum}+";

    public final String allergenName;

    /**
     * Constructs a {@code Allergen}.
     *
     * @param allergenName A valid allergen name.
     */
    public Allergen(String allergenName) {
        requireNonNull(allergenName);
        checkArgument(isValidAllergenName(allergenName), MESSAGE_CONSTRAINTS);
        this.allergenName = allergenName;
    }

    /**
     * Returns true if a given string is a valid allergen name.
     */
    public static boolean isValidAllergenName(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof Allergen)) {
            return false;
        }

        Allergen otherAllergen = (Allergen) other;
        return allergenName.equals(otherAllergen.allergenName);
    }

    @Override
    public int hashCode() {
        return allergenName.hashCode();
    }

    /**
     * Format state as text for viewing.
     */
    public String toString() {
        return '[' + allergenName + ']';
    }

}
