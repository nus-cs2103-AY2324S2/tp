package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Person's identity card number in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidIdentityCardNumber(String)}
 */
public class IdentityCardNumber {

    public static final String MESSAGE_CONSTRAINTS =
            "IC number starts with one letter (S,T,F,G,M) followed by seven digits and one letter behind" +
                    " It is case insensitive. An example is S1234567A.";

    public static final String VALIDATION_REGEX = "[STFGM][0-9][A-Z]";
    public final String value;

    /**
     * Constructs a {@code IdentityCardNumber}.
     *
     * @param identityCardNumber A valid identity card number.
     */
    public IdentityCardNumber(String identityCardNumber) {
        requireNonNull(identityCardNumber);
        checkArgument(isValidIdentityCardNumber(identityCardNumber), MESSAGE_CONSTRAINTS);
        value = identityCardNumber;
    }

    /**
     * Returns true if a given string is a valid identity card number.
     */
    public static boolean isValidIdentityCardNumber(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof IdentityCardNumber)) {
            return false;
        }

        IdentityCardNumber otherIdentityCardNumber = (IdentityCardNumber) other;
        return value.equals(otherIdentityCardNumber.value);
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}
