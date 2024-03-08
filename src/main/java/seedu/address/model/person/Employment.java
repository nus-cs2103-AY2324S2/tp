package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

public class Employment {
    public static final String MESSAGE_CONSTRAINTS =
            "Employment should be either full-time or part-time";
    public static final String VALIDATION_REGEX1 = "part-time";
    public static final String VALIDATION_REGEX2 = "full-time";
    public final String employment;

    /**
     * Constructs an {@code Salary}.
     *
     * @param employment A valid employment either part-time or full-time.
     */
    public Employment(String employment) {
        requireNonNull(employment);
        checkArgument(isValidEmployment(employment), MESSAGE_CONSTRAINTS);
        this.employment = employment;
    }

    /**
     * Returns true if a given string is a valid salary.
     */
    public static boolean isValidEmployment(String test) {
        return test.matches(VALIDATION_REGEX1) || test.matches(VALIDATION_REGEX2);
    }

    @Override
    public String toString() {
        return employment;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof Email)) {
            return false;
        }

        Employment otherEmployment = (Employment) other;
        return employment.equals(otherEmployment.employment);
    }

    @Override
    public int hashCode() {
        return employment.hashCode();
    }
}
