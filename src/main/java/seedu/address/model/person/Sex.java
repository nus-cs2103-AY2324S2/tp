package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Person's sex in the patient list.
 * Guarantees: immutable; is valid as declared in {@link #isValidSex(String)}
 */
public class Sex {
    public static final String MESSAGE_CONSTRAINTS = "Sex should only be Male or Female";

    public final SexOption sex;

    /**
     * Constructus a {@code Sex}.
     *
     * @param sex A valid sex.
     */
    public Sex(String sex) {
        requireNonNull(sex);
        checkArgument(isValidSex(sex), MESSAGE_CONSTRAINTS);
        this.sex = assignSex(sex);
    }

    /**
     * Returns true if a given string is a valid sex.
     */
    public static boolean isValidSex(String test) {
        return test.equals("Male") || test.equals("Female") ? true : false;
    }

    public SexOption assignSex(String sex) {
        if (sex.equals("Male")) {
            return SexOption.MALE;
        } else if (sex.equals("Female")) {
            return SexOption.FEMALE;
        } else {
            return null;
        }
    }

    @Override
    public String toString() {
        return sex.getLabel();
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
        return sex.equals(otherSex.sex);
    }

    @Override
    public int hashCode() {
        return sex.hashCode();
    }
}
