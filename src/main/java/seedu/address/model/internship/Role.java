package seedu.address.model.internship;

import static java.util.Objects.requireNonNull;

/**
 * Represent's an internship's role in the internship book
 */
public class Role {

    public final String role;

    /**
     * Constructs a {@code Role}.
     *
     * @param role A valid role.
     */
    public Role(String role) {
        requireNonNull(role);
        this.role = role;
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
