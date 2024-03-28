package seedu.address.model.cca;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a CCA in the address book.
 * Guarantees: immutable; name is valid as declared in {@link #isValidCCAName(String)}
 */
public class Cca {

    public static final String MESSAGE_CONSTRAINTS = "CCA names should be alphanumeric (but can include "
        + "whitespace)";
    public static final String VALIDATION_REGEX = "[\\p{Alnum}\\s]+";

    public final String ccaName;

    /**
     * Constructs a {@code Tag}.
     *
     * @param CCAName A valid tag name.
     */
    public Cca(String ccaName) {
        requireNonNull(ccaName);
        checkArgument(isValidCcaName(ccaName), MESSAGE_CONSTRAINTS);
        this.ccaName = ccaName;
    }

    /**
     * Returns true if a given string is a valid tag name.
     */
    public static boolean isValidCcaName(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof Cca)) {
            return false;
        }

        Cca otherTag = (Cca) other;
        return ccaName.equals(otherTag.ccaName);
    }

    @Override
    public int hashCode() {
        return ccaName.hashCode();
    }

    /**
     * Format state as text for viewing.
     */
    public String toString() {
        return "[CCA: " + ccaName + ']';
    }

}
