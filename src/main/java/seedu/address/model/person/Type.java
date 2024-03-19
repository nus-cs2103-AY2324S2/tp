package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;
import static seedu.address.logic.parser.CliSyntax.ALLOWED_PREAMBLES;

/**
 * Represents a Person's Type in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidType(String)}
 */
public class Type {

    public static final String MESSAGE_CONSTRAINTS = "Type can either be \"client\" or \"housekeeper\"";

    /*
     * The first character of the type must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "[^\\s].*";

    public final String value;

    /**
     * Constructs an {@code Type}.
     *
     * @param type A valid type.
     */
    public Type(String type) {
        requireNonNull(type);
        checkArgument(isValidType(type), MESSAGE_CONSTRAINTS);
        value = type;
    }

    /**
     * Returns true if a given string is a valid type.
     */
    public static boolean isValidType(String test) {
        return test.matches(VALIDATION_REGEX) && preambleIsAllowed(test);
    }

    /**
     * Checks if a given command uses a preamble that is allowed. (we define preamble as TYPE)
     * @param preamble
     * @return true if the preamble is allowed and no if it is not.
     */
    public static boolean preambleIsAllowed(String preamble) {
        for (String s : ALLOWED_PREAMBLES) {
            if (s.equals(preamble)) {
                return true;
            }
        }
        return false;
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
        if (!(other instanceof Type)) {
            return false;
        }

        Type otherType = (Type) other;
        return value.equals(otherType.value);
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}

