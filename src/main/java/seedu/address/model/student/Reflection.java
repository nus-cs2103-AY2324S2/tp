package seedu.address.model.student;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Reflection in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidReflection(String)}
 */
public class Reflection {
    public static final String MESSAGE_CONSTRAINTS = "Reflections in the style of R1.. are accepted."
            + " The first character must be \"R\","
            + " followed by a number of digits.";
    public static final String VALIDATION_REGEX = "R\\d+";
    public final String reflection;

    /**
     * Constructs a {@code Reflection}.
     * @param reflection A valid reflection.
     */
    public Reflection(String reflection) {
        requireNonNull(reflection);
        checkArgument(isValidConstructorParam(reflection), MESSAGE_CONSTRAINTS);
        this.reflection = reflection;
    }

    /**
     * Returns true if a given string is a valid reflection for constructing a new Reflection.
     * @param test String to be tested
     * @return true if the string is a valid reflection
     */
    static boolean isValidConstructorParam(String test) {
        return isValidReflection(test) || isEmptyReflection(test);
    }

    /**
     * Returns true if a given string is a valid reflection for constructing a new Reflection.
     * @param test String to be tested
     * @return true if the string is an empty reflection
     */
    public static boolean isEmptyReflection(String test) {
        return test.isBlank();
    }

    /**
     * Returns true if a given string is a valid reflection.
     * @param test String to be tested
     * @return true if the string is a valid reflection
     */
    public static boolean isValidReflection(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public boolean equals(Object object) {
        if (object == this) {
            return true;
        }
        if (!(object instanceof Reflection)) {
            return false;
        }
        Reflection otherReflection = (Reflection) object;
        return otherReflection.reflection.equals(reflection);
    }

    /**
     * Returns the hashcode of the reflection.
     */
    @Override
    public int hashCode() {
        return reflection.hashCode();
    }

    /**
     * Returns the reflection in string format.
     */
    @Override
    public String toString() {
        return reflection;
    }
}
