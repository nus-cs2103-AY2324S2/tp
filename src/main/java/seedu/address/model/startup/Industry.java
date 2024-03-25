package seedu.address.model.startup;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents industry startup is from.
 */
public class Industry {

    public static final String MESSAGE_CONSTRAINTS =
        "Industry names should only contain alphanumeric characters and spaces, and it should not be blank";

    /*
     * The first character of the address must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "[\\p{Alnum}][\\p{Alnum} ]*";

    public final String value;

    /**
     * Constructor for an industry.
     * @param industry Name of the industry.
     */
    public Industry(String industry) {
        requireNonNull(industry);
        checkArgument(isValidIndustry(industry), MESSAGE_CONSTRAINTS);
        value = industry.toUpperCase();
    }

    /**
     * Returns true if a given industry is a valid industry.
     */
    public static boolean isValidIndustry(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return this.value;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof Industry)) {
            return false;
        }

        Industry otherIndustry = (Industry) other;
        return value.equals(otherIndustry.value);
    }
}
