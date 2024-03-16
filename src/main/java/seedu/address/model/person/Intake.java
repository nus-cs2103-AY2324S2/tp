package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.time.Year;

/**
 * Represents a Student's Intake in the Application.
 * Guarantees: immutable; is valid as declared in {@link #isValidIntake(String)}
 */
public class Intake {

    public static final String MESSAGE_CONSTRAINTS =
            "Intake should contain Year in the form YYYY, and it should not be blank";
    public static final String VALIDATION_REGEX = "\\b\\d{4}\\b";

    public final Year intake;

    /**
     * Constructs a {@code Intake}.
     *
     * @param intake A valid Intake.
     */
    public Intake(String intake) {
        requireNonNull(intake);
        checkArgument(isValidIntake(intake), MESSAGE_CONSTRAINTS);
        this.intake = parseIntake(intake);
    }

    /**
     * Returns true if a given string is a valid Intake.
     */
    public static boolean isValidIntake(String test) {
        if (test.matches(VALIDATION_REGEX)) {
            return !Year.of(Integer.parseInt(test)).isAfter(Year.now());
        }
        return false;
    }

    /**
     * Parses the input into Java Year
     */
    public Year parseIntake(String intake) {
        return Year.of(Integer.parseInt(intake));
    }

    @Override
    public String toString() {
        return this.intake.toString();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof Intake)) {
            return false;
        }

        Intake otherIntake = (Intake) other;
        return this.intake.equals(otherIntake.intake);
    }

    @Override
    public int hashCode() {
        return this.intake.hashCode();
    }
}
