package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Person's major in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidMajor(String)}
 */
public class Major {


    public static final String MESSAGE_CONSTRAINTS =
            "Majors should be at least 2 characters long";

    /**
     * Regular expression for validating strings containing alphabetic characters and spaces with a minimum length of 2 characters.
     *
     * <p>
     * The expression ensures that the input:
     * <ul>
     *     <li>Contains only alphabetic characters (letters) and spaces.</li>
     *     <li>Has a minimum length of 2 characters.</li>
     *     <li>Does not start with whitespace.</li>
     * </ul>
     * </p>
     *
     * @see <a href="https://docs.oracle.com/javase/8/docs/api/java/util/regex/Pattern.html">java.util.regex.Pattern</a>
     */
    public static final String VALIDATION_REGEX = "(?<!\\s)[\\p{Alpha} ]{2,}";

    public final String value;

    /**
     * Constructs a {@code Major}.
     *
     * @param major A valid major.
     */
    public Major(String major) {
        requireNonNull(major);
        checkArgument(isValidMajor(major), MESSAGE_CONSTRAINTS);
        value = major;
    }

    /**
     * Returns true if a given string is a valid major number.
     */
    public static boolean isValidMajor(String test) {
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
        if (!(other instanceof Major)) {
            return false;
        }

        Major otherMajor = (Major) other;
        return value.equals(otherMajor.value);
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}
