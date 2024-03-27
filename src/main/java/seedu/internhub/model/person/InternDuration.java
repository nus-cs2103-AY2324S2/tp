package seedu.internhub.model.person;

import static seedu.internhub.commons.util.AppUtil.checkArgument;

/**
 * Represents a Person's Intern Duration in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidInternDuration(String)}
 */
public class InternDuration {

    public static final String MESSAGE_CONSTRAINTS = "Intern Duration can take any values, and it should not be blank";

    /*
     * The first character of the intern duration must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "[^\\s].*";

    public final String value;

    /**
     * Constructs an {@code internDuration}.
     *
     * @param internDuration A valid intern duration.
     */
    public InternDuration(String internDuration) {
        checkArgument(isValidInternDuration(internDuration), MESSAGE_CONSTRAINTS);
        value = internDuration;
    }

    /**
     * Returns true if a given string is a valid intern duration.
     */
    public static boolean isValidInternDuration(String test) {
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
        if (!(other instanceof InternDuration)) {
            return false;
        }

        InternDuration otherInternDuration = (InternDuration) other;
        return value.equals(otherInternDuration.value);
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}
