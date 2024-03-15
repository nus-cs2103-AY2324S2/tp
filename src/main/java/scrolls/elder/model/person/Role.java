package scrolls.elder.model.person;

import scrolls.elder.commons.util.AppUtil;

import static java.util.Objects.requireNonNull;

/**
 * Represents a Person's name in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidRole(String)}
 */
public class Role {

    public static final String MESSAGE_CONSTRAINTS =
            "Role should only be either 'volunteer' or befriendee', and it should not be blank";

    /*
     * The first character of the address must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "(?i)(befriendee|volunteer)";;
    // FIX THIS

    public final String roleString;

    /**
     * Constructs a {@code Name}.
     *
     * @param role A valid name.
     */
    public Role(String role) {
        requireNonNull(role);

        AppUtil.checkArgument(isValidRole(role), MESSAGE_CONSTRAINTS);
        this.roleString = role.toLowerCase();
    }

    /**
     * Returns true if a given string is a valid name.
     */
    public static boolean isValidRole(String test) {
        if (test.equalsIgnoreCase("volunteer") || test.equalsIgnoreCase("befriendee")) {
            return true;
        }
        return false;
        // return test.matches(VALIDATION_REGEX);
    }

    public static boolean isVolunteer(Role role) {
        return role.roleString.equals("volunteer");
    }

    public static boolean isBefriendee(Role role) {
        return role.roleString.equals("befriendee");
    }


    @Override
    public String toString() {
        return roleString;
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
        return roleString.equals(otherName.roleString);
    }

    @Override
    public int hashCode() {
        return roleString.hashCode();
    }

}
