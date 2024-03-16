package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Person's sex in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidSex(String)}
 */
public class Sex {

    public static final Sex DEFAULT = new Sex("F");
    public static final String MALE = "M";
    public static final String FEMALE = "F";

    public static final String MESSAGE_CONSTRAINTS =
            "Sex should only contain either 'M' or 'F', and it should not be blank";

    /*
     * The first character of the sex must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "[MF]";

    public final String value;

    /**
     * Constructs a {@code Sex}
     *
     * @param sex A valid sex.
     */
    public Sex(String sex) {
        requireNonNull(sex);
        checkArgument(isValidSex(sex), MESSAGE_CONSTRAINTS);
        if (isFemaleString(sex)) {
            value = FEMALE;
        } else if (isMaleString(sex)) {
            value = MALE;
        } else {
            // This should never happen
            // Prevents linter from detecting fullSex as potentially unassigned
            value = "";
        }
    }

    public boolean isMaleString(String sex) {
        return sex.equals(MALE);
    }

    public boolean isFemaleString(String sex) {
        return sex.equals(FEMALE);
    }

    public static boolean isValidSex(String test) {
        return test.matches(VALIDATION_REGEX);
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

        // instanceof handles nulls
        if (!(other instanceof Sex)) {
            return false;
        }

        Sex otherSex = (Sex) other;
        return value.equals(otherSex.value);
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}
