package staffconnect.model.meeting;

import static java.util.Objects.requireNonNull;
import static staffconnect.commons.util.AppUtil.checkArgument;

/**
 * Represents a Meeting's description in the staff book.
 * Guarantees: immutable; is valid as declared in {@link #isValidDescription(String)}
 */
public class Description {

    public static final String MESSAGE_CONSTRAINTS =
        "Description should only contain alphanumeric characters and spaces, and it should not be blank";

    /*
     * The first character of the name must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "[\\p{Alnum}][\\p{Alnum} ]*";

    public final String description;

    /**
     * Constructs a {@code Description}.
     *
     * @param textDescription A valid description.
     */
    public Description(String textDescription) {
        requireNonNull(textDescription);
        checkArgument(isValidDescription(textDescription), MESSAGE_CONSTRAINTS);
        description = textDescription;
    }

    /**
     * Returns true if a given string is a valid description.
     */
    public static boolean isValidDescription(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public int hashCode() {
        return description.hashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof staffconnect.model.meeting.Description)) {
            return false;
        }

        staffconnect.model.meeting.Description otherDecription = (staffconnect.model.meeting.Description) other;
        return description.equals(otherDecription.description);
    }

    @Override
    public String toString() {
        return description;
    }

}
