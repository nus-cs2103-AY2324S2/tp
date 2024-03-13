package seedu.address.model.student;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Student's studio in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidStudio(String)}
 */
public class Studio {
    public static final String MESSAGE_CONSTRAINTS = "Studios in the style of S12.. are accepted."
            + "The first character must be \"S\","
            + "followed by any number of digits.";
    public static final String VALIDATION_REGEX = "S\\d+";
    public final String studio;

    /**
     * Constructs a {@code Studio}.
     * @param studio A valid studio.
     */
    public Studio(String studio) {
        requireNonNull(studio);
        checkArgument(isValidConstructorParam(studio), MESSAGE_CONSTRAINTS);
        this.studio = studio;
    }

    /**
     * Returns true if a given string is a valid studio for constructing a new Studio.
     * @param test
     * @return
     */
    static boolean isValidConstructorParam(String test) {
        return isValidStudio(test) || isEmptyStudio(test);
    }

    /**
     * Returns true if a given string is an empty studio. Only used when the prefix
     * for studio is absent from a user command.
     * @param test String to be tested
     * @return true if the string is an empty studio
     */
    private static boolean isEmptyStudio(String test) {
        return test.isBlank();
    }

    /**
     * Returns true if a given string is a valid studio.
     * @param test String to be tested
     * @return true if the string is a valid studio
     */
    public static boolean isValidStudio(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public boolean equals(Object object) {
        if (object == this) {
            return true;
        }
        if (!(object instanceof Studio)) {
            return false;
        }
        Studio otherStudio = (Studio) object;
        return studio.equals(otherStudio.studio);
    }

    /**
     * Returns the hashcode of the studio
     */
    @Override
    public int hashCode() {
        return studio.hashCode();
    }

    /**
     * Returns the studio in string format.
     */
    @Override
    public String toString() {
        return studio;
    }



}
