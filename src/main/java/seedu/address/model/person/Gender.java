package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Person's gender in the patient book.
 * Guarantees: immutable; is valid as declared in {@link #isValidNric(String)} (String)}
 */
public class Gender {

    public static final String MESSAGE_CONSTRAINTS =
            "Gender must be M, F or exclude from the command for 'Prefer not to say'";

    /*
     * gender must follow the Singapore's format of [M, F]
     */
    public static final String VALIDATION_REGEX = "^[STFG]\\d{7}[A-Z]$";

    public final String gender;

    /**
     * Constructs a {@code Nric}.
     *
     * @param nric A valid nric.
     */
    public Nric(String nric) {
        requireNonNull(nric);
        checkArgument(isValidNric(nric), MESSAGE_CONSTRAINTS);
        this.nric = nric;
    }

    /**
     * Returns true if a given string is a valid name.
     */
    public static boolean isValidNric(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return nric;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof Nric)) {
            return false;
        }

        Nric otherNric = (Nric) other;
        return nric.equals(otherNric.nric);
    }

    @Override
    public int hashCode() {
        return nric.hashCode();
    }
}
