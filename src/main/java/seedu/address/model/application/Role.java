package seedu.address.model.application;

/**
 * A job role that an applicant can apply for.
 */
public class Role {
    private final String roleName;

    public Role(String roleName) {
        this.roleName = roleName;
    }

    public String getRoleName() {
        return roleName;
    }

    @Override
    public String toString() {
        return getRoleName();
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
        if (!(other instanceof seedu.address.model.application.Role)) {
            return false;
        }

        seedu.address.model.application.Role otherStage = (seedu.address.model.application.Role) other;
        return roleName.equals(otherStage.roleName);
    }
}
