package seedu.address.model.person;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Person's birthdate in the patient book.
 * Guarantees: immutable; is valid as declared in {@link #isValidBirthDate(String)}
 */
public class BirthDate {

    public static final String MESSAGE_CONSTRAINTS =
            "Birth Date must be in the form of DD-MM-YYYY and must not be in the future";

    public final String birthDate;

    /**
     * Constructs a {@code BirthDate}.
     *
     * @param birthDate A valid birthDate.
     */
    public BirthDate(String birthDate) {
        requireNonNull(birthDate);
        checkArgument(isValidBirthDate(birthDate), MESSAGE_CONSTRAINTS);
        this.birthDate = birthDate;
    }

    /**
     * Returns true if a given string is a valid birthDate.
     */
    public static boolean isValidBirthDate(String test) {
        try {
            LocalDate birthLocalDate = LocalDate.parse(test, DateTimeFormatter.ofPattern("dd-MM-yyyy"));
            LocalDate today = LocalDate.now();

            return !birthLocalDate.isAfter(today);
        } catch (DateTimeParseException e) {
            return false;
        }
    }

    @Override
    public String toString() {
        return birthDate;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof BirthDate)) {
            return false;
        }

        BirthDate otherBirthDate = (BirthDate) other;
        return birthDate.equals(otherBirthDate.birthDate);
    }

    @Override
    public int hashCode() {
        return birthDate.hashCode();
    }
}
