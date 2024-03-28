package seedu.address.model.internship;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents an Internship's description in the internship book.
 */
public class Description {
    public static final String MESSAGE_CONSTRAINTS =
            "Descriptions should not be blank!";

    /*
     * Matches any characters that are not only whitespace
     */
    public static final String VALIDATION_REGEX = "^(?!\\s*$).+";

    public final String description;

    /**
     * Constructs a {@code Description}.
     *
     * @param description A valid description.
     */
    public Description(String description) {
        requireNonNull(description);
        checkArgument(isValidDescription(description), MESSAGE_CONSTRAINTS);
        this.description = description;
    }

    /**
     * Returns true if a given string is a valid Description.
     */
    public static boolean isValidDescription(String test) {
        return test.matches(VALIDATION_REGEX);
    }


    @Override
    public String toString() {
        return description;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof Description)) {
            return false;
        }

        Description otherName = (Description) other;
        return description.equals(otherName.description);
    }

    @Override
    public int hashCode() {
        return description.hashCode();
    }
}
