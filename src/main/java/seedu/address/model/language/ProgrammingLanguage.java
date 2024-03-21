package seedu.address.model.language;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Programming Language in the address book.
 * Guarantees: immutable; name is valid as declared in {@link #isValidLanguageName(String)}
 */
public class ProgrammingLanguage {

    public static final String MESSAGE_CONSTRAINTS =
            "Programming Languages should be alphanumeric and may contain some special characters (+ and #)"
            + ", and must be less than 50 characters";
    public static final String VALIDATION_REGEX = "[\\p{Alnum}+#\\s]{1,50}";
    public final String languageName;

    /**
     * Constructs a {@code ProgrammingLanguage}.
     *
     * @param languageName A valid programming language name.
     */
    public ProgrammingLanguage(String languageName) {
        requireNonNull(languageName);
        checkArgument(isValidLanguageName(languageName), MESSAGE_CONSTRAINTS);
        this.languageName = languageName;
    }

    /**
     * Returns true if a given string is a valid programming language name.
     *
     * @param test The string to test for validity.
     * @return {@code true} if the string is a valid programming language name, {@code false} otherwise.
     */
    public static boolean isValidLanguageName(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof ProgrammingLanguage)) {
            return false;
        }

        ProgrammingLanguage otherLanguage = (ProgrammingLanguage) other;
        return languageName.equals(otherLanguage.languageName);
    }

    @Override
    public int hashCode() {
        return languageName.hashCode();
    }

    /**
     * Returns the string representation of this programming language.
     *
     * @return The string representation of this programming language.
     */
    @Override
    public String toString() {
        return "[Programming Language: " + languageName + "]";
    }
}
