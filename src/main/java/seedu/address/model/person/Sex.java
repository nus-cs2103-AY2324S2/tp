package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Person's sex in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidSex(String)}
 */
public class Sex {

    public enum ValidSex {Male, Female}

    public static final String MESSAGE_CONSTRAINTS =
            "Sex should only contain either 'M' or 'F', and it should not be blank";

    /*
     * The first character of the sex must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "[MF]";

    public final ValidSex fullSex;

    /**
     * Constructs a {@code Sex}
     *
     * @param sex A valid sex.
     */
    public Sex(String sex) {
        requireNonNull(sex);
        checkArgument(isValidSex(sex), MESSAGE_CONSTRAINTS);
        if (isFemaleString(sex)) {
            fullSex = ValidSex.Female;
        } else if (isMaleString(sex)) {
            fullSex = ValidSex.Male;
        } else {
            // This should never happen
            // Prevents linter from detecting fullSex as potentially unassigned
            fullSex = null;
        }
    }

    public boolean isMaleString(String sex) {
        return sex.equals("M");
    }

    public boolean isFemaleString(String sex) {
        return sex.equals("F");
    }

    public boolean isValidSex(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    public boolean isMale() {
        return fullSex.equals(ValidSex.Male);
    }

    public boolean isFemale() {
        return fullSex.equals(ValidSex.Female);
    }

    @Override
    public String toString() {
        return fullSex.toString();
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
        return fullSex.equals(otherSex.fullSex);
    }

    @Override
    public int hashCode() {
        return fullSex.hashCode();
    }
}
