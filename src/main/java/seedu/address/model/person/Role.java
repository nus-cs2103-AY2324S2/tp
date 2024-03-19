package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Person's role in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidRole(String)}
 */
public class Role {

    public static final String MESSAGE_CONSTRAINTS =
            "Roles should be either 'STUDENT', 'TA', or 'PROFESSOR'.";

    /**
     * Enum representing different roles in an educational system.
     * This enum defines three role types: STUDENT, TA (Teaching Assistant), and PROFESSOR.
     */
    public enum RoleType {
        STUDENT, TA, PROFESSOR
    }

    public final RoleType role;

    /**
     * Constructs a {@code role}.
     *
     * @param role A valid role.
     */
    public Role(String role) {
        requireNonNull(role);
        checkArgument(isValidRole(role), MESSAGE_CONSTRAINTS);
        this.role = RoleType.valueOf(role);
    }

    /**
     * Returns true if a given string is a valid role.
     */
    public static boolean isValidRole(String test) {
        try {
            RoleType.valueOf(test);
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }


    @Override
    public String toString() {
        return role.name();
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

        Role otherRole = (Role) other;
        return role.equals(otherRole.role);
    }

    @Override
    public int hashCode() {
        return role.hashCode();
    }

}
