package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Person's role in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidRole(String)}
 */
public class Role {

    public static final String MESSAGE_CONSTRAINTS =
            "Roles should be either 'Student', 'TA', or 'Professor'.";

    /*
     * The role of the person should be "Student", "TA", or "Professor".
     */
    public static final String[] VALID_ROLES = {"Student", "TA", "Professor"};

    public final String role;

    /**
     * Constructs a {@code role}.
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
        for (String validRole : VALID_ROLES) {
            if (validRole.equals(test)) {
                return true;
            }
        }
        return false;
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

        Role otherrole = (Role) other;
        return role.equals(otherrole.role);
    }

    @Override
    public int hashCode() {
        return role.hashCode();
    }

}
