package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Department in the address book.
 * Guarantees: immutable; name is valid as declared in {@link #isValidDepartment(String)}
 */
public class Department {

    public static final String MESSAGE_CONSTRAINTS =
            "Department should only contain alphanumeric characters and spaces, and it should not be blank";
    public static final String VALIDATION_REGEX = "[\\p{Alnum}][\\p{Alnum} ]*";

    public final String department;

    /**
     * Constructs a {@code Department}.
     *
     * @param department A valid department name.
     */
    public Department(String department) {
        requireNonNull(department);
        checkArgument(isValidDepartment(department), MESSAGE_CONSTRAINTS);
        this.department = department;
    }

    /**
     * Returns true if a given string is a valid department name.
     */
    public static boolean isValidDepartment(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof Department)) {
            return false;
        }

        Department otherDepartment = (Department) other;
        return department.equals(otherDepartment.department);
    }

    @Override
    public int hashCode() {
        return department.hashCode();
    }

    /**
     * Format state as text for viewing.
     */
    public String toString() {
        return '{' + department + '}';
    }

}
