package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

public class Department {

    public static final String MESSAGE_CONSTRAINTS =
            "Department names should only contain alphanumeric characters and spaces, and it should not be blank";

    /*
     * The first character of the address must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "[\\p{Alnum}][\\p{Alnum} ]*";

    private final String departmentName;

    public Department(String departmentName) {
        requireNonNull(departmentName);
        checkArgument(isValidDepartment(departmentName), MESSAGE_CONSTRAINTS);
        this.departmentName = departmentName;
    }

    /**
     * Returns true if the given department name is valid.
     * A department name is considered valid if it matches the validation regex.
     *
     * @param test The department name to be validated.
     * @return True if the department name is valid, false otherwise.
     */
    public static boolean isValidDepartment(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    public String getDepartmentName() {
        return departmentName;
    }

    @Override
    public String toString() {
        return departmentName;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Department)) {
            return false;
        }

        Department otherDepartment = (Department) other;
        return departmentName.equals(otherDepartment.departmentName);
    }

    @Override
    public int hashCode() {
        return departmentName.hashCode();
    }
}