package seedu.address.model.matric;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Matriculation Number in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidMatric(String)}
 */
public class Matric {
    public static final String MESSAGE_CONSTRAINTS = "Matriculation numbers should be alphanumeric";
    public static final String VALIDATION_REGEX = "[A-Z][0-9]{7}[A-Z]";
    public final String matricNumber;

    /**
     * Constructs a {@code Matric}.
     * @param matricNumber A valid matriculation number.
     */
    public Matric(String matricNumber) {
        requireNonNull(matricNumber);
        checkArgument(isValidMatric(matricNumber), MESSAGE_CONSTRAINTS);
        this.matricNumber = matricNumber;
    }

    /**
     * Returns true if a given string is a valid matriculation number.
     * @param test String to be tested
     * @return true if the string is a valid matriculation number
     */
    public static boolean isValidMatric(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public boolean equals(Object object) {
        if (object == this) {
            return true;
        }
        if (!(object instanceof Matric)) {
            return false;
        }
        Matric otherMatric = (Matric) object;
        return matricNumber.equals(otherMatric.matricNumber);
    }

    @Override
    public int hashCode() {
        return matricNumber.hashCode();
    }

    /**
     * Format state as text for viewing.
     * @return String representation of the matriculation number
     */
    public String toString() {
        return matricNumber;
    }

}
