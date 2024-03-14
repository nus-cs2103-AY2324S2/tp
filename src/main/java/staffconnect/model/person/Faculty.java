package staffconnect.model.person;

import static java.util.Objects.requireNonNull;
import static staffconnect.commons.util.AppUtil.checkArgument;

/**
 * Represents a Person's faculty in the staff book.
 * Guarantees: immutable; is valid as declared in
 * {@link #isValidFaculty(String)}
 */
public class Faculty {
    public static final String MESSAGE_CONSTRAINTS = "This can be any non-blank String content.";
    /*
     * The first character of the faculty must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "[^\\s].*";
    private final String value;

    /**
     * Constructs a {@code Faculty}.
     *
     * @param faculty A valid faculty.
     */
    public Faculty(String faculty) {
        requireNonNull(faculty);
        checkArgument(isValidFaculty(faculty), MESSAGE_CONSTRAINTS);
        value = faculty;
    }

    public static boolean isValidFaculty(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }

        if (!(obj instanceof Faculty)) {
            return false;
        }

        Faculty otherFaculty = (Faculty) obj;
        return this.value.equals(otherFaculty.value);
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}
