package seedu.address.model.applicant;

/**
 * Represents an Applicant's role in the address book.
 * Guarantees: immutable;
 */
public class Role {
    public static final String MESSAGE_CONSTRAINTS = "Role can take any values, and it should not be blank";

    public final String value;
    public Role(String role) {
        this.value = role;
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
        if (!(other instanceof Role)) {
            return false;
        }

        Role otherRole = (Role) other;
        return value.equals(otherRole.value);
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}
