package seedu.address.model.matric;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Matriculation Number in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidMatric(String)}
 */
public class Matric {
    public static final String MESSAGE_CONSTRAINTS = "Matriculation numbers in the style of A1234567Z are accepted."
            + "The first character must be an uppercase letter,"
            + "followed by 7 digits and ending with an uppercase letter.";
    public static final String VALIDATION_REGEX = "[A-Z][0-9]{7}[A-Z]";
    public final String matricNumber;

    /**
     * Constructs a {@code Matric}.
     * @param matricNumber A valid matriculation number.
     */
    public Matric(String matricNumber) {
        requireNonNull(matricNumber);
        checkArgument(isValidConstructorParam(matricNumber), MESSAGE_CONSTRAINTS);
        this.matricNumber = matricNumber;
    }

    public static boolean isValidConstructorParam(String matricNumber) {
        return isValidMatric(matricNumber) || isEmptyMatric(matricNumber);
    }

    private static boolean isEmptyMatric(String matricNumber) {
        return matricNumber.isBlank();
    }

    /**
     * Returns true if a given string is a valid matriculation number.
     * @param test String to be tested
     * @return true if the string is a valid matriculation number
     */
    static boolean isValidMatric(String test) {
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
        return isEmptyMatric(matricNumber) ? "No matriculation number" : matricNumber;
    }

}
