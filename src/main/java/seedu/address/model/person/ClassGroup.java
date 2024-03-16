package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Person's class/group in the TA Toolkit.
 * Guarantees: immutable; is valid as declared in {@link #isValidClassGroup(String)}
 */
public class ClassGroup {

    public static final String MESSAGE_CONSTRAINTS =
            "Class/groups should only contain alphanumeric characters delimited by a dash.\n"
                    + "It cannot contain symbols, whitespaces and it should not be blank";

    /*
     * The first character of the address must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "^(?:[\\p{Alnum}]+-?)*[\\p{Alnum}]+$";

    public final String classGroup;

    /**
     * Constructs a {@code ClassGroup}.
     *
     * @param classGroup A valid classGroup.
     */
    public ClassGroup(String classGroup) {
        requireNonNull(classGroup);
        checkArgument(isValidClassGroup(classGroup), MESSAGE_CONSTRAINTS);
        this.classGroup = classGroup;
    }

    /**
     * Returns true if a given string is a valid class/group.
     */
    public static boolean isValidClassGroup(String test) {
        return test.matches(VALIDATION_REGEX);
    }


    @Override
    public String toString() {
        return classGroup;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof ClassGroup)) {
            return false;
        }

        ClassGroup otherClassGroup = (ClassGroup) other;
        return classGroup.equals(otherClassGroup.classGroup);
    }

    @Override
    public int hashCode() {
        return classGroup.hashCode();
    }

}
