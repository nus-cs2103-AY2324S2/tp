package seedu.address.model.applicant;

/**
 * A job role that an applicant can apply for.
 */
public class Role {
    public final String roleName;

    public Role(String roleName) {
        this.roleName = roleName;
    }

    @Override
    public String toString() {
        return roleName;
    }

    @Override
    public int hashCode() {
        return roleName == null ? 0 : roleName.hashCode();
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

        Role otherStage = (Role) other;
        return roleName.equals(otherStage.roleName);
    }
}
