package seedu.address.model.internship;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represent's an internship's role in the internship book
 */
public class Role {
    public static final String MESSAGE_CONSTRAINTS =
            "Roles should not be blank!";

    /*
     * Matches any characters that are not only whitespace
     */
    public static final String VALIDATION_REGEX = "^(?!\\s*$).+";

    public final String role;

    /**
     * Constructs a {@code Role}.
     *
     * @param role A valid role.
     */
    public Role(String role) {
        requireNonNull(role);
        checkArgument(isValidRole(role), MESSAGE_CONSTRAINTS);
        this.role = role;
    }

    /**
     * Returns true if a given string is a valid role.
     */
    public static boolean isValidRole(String test) {
        return test.matches(VALIDATION_REGEX);
    }


    @Override
    public String toString() {
        return role;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof Role)) {
            return false;
        }

        Role otherName = (Role) other;
        return role.equals(otherName.role);
    }

    @Override
    public int hashCode() {
        return role.hashCode();
    }
}
