package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Person's gender in the patient book.
 * Guarantees: immutable; is valid as declared in {@link #isValidNric(String)} (String)}
 */
public class Gender {

    public static final String MESSAGE_CONSTRAINTS =
            "Gender must be M, F or exclude from the command for 'Prefer not to say'";

    /*
     * gender must follow the Singapore's format of M, F or Prefer not to say case-insensitive
     */
    public static final String VALIDATION_REGEX = "(?i)^(M|F|Prefer not to say)$";

    public final String gender;

    /**
     * Constructs a {@code Gender}.
     *
     * @param gender A valid nric.
     */
    public Gender(String gender) {
        requireNonNull(gender);
        checkArgument(isValidGender(gender), MESSAGE_CONSTRAINTS);
        this.gender = gender;
    }

    /**
     * Returns true if a given string is a valid gender.
     */
    public static boolean isValidGender(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return gender;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof Gender)) {
            return false;
        }

        Gender otherGender = (Gender) other;
        return gender.equals(otherGender.gender);
    }

    @Override
    public int hashCode() {
        return gender.hashCode();
    }
}
