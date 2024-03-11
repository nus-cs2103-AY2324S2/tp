package seedu.address.model.classes;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a tutorial class in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidTutorialClass(String)}
 */
public class TutorialClass {

    public static final String MESSAGE_CONSTRAINTS = "Tutorial classes should consist of a 'T' followed by 2 digits (e.g., T09, T15).";

    public final String value;

    /**
     * Constructs a {@code TutorialClass}.
     *
     * @param tutorialClass A valid tutorial class.
     */
    public TutorialClass(String tutorialClass) {
        requireNonNull(tutorialClass);
        checkArgument(isValidTutorialClass(tutorialClass), MESSAGE_CONSTRAINTS);
        value = tutorialClass.toUpperCase(); // Assuming tutorial classes should be stored in uppercase
    }

    /**
     * Returns if a given string is a valid tutorial class.
     */
    public static boolean isValidTutorialClass(String test) {
        return test.matches("T\\d{2}");
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

        if (!(other instanceof TutorialClass)) {
            return false;
        }

        TutorialClass otherTutorialClass = (TutorialClass) other;
        return value.equals(otherTutorialClass.value);
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}

