package scrolls.elder.model.person;

import static java.util.Objects.requireNonNull;

import scrolls.elder.commons.util.AppUtil;

/**
 * Represents a Person's name in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidRole(String)}
 */
public class Role {

    public static final String MESSAGE_CONSTRAINTS =
            "Role should only be either 'volunteer' or befriendee', and it should not be blank.";

    /*
     * The first character of the address must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "^befriendee|volunteer$";

    public final String value;

    /**
     * Constructs a {@code Name}.
     *
     * @param role A valid name.
     */
    public Role(String role) {
        requireNonNull(role);
        AppUtil.checkArgument(isValidRole(role), MESSAGE_CONSTRAINTS);
        this.value = role.toLowerCase();
    }

    /**
     * Returns true if a given string is a valid name.
     */
    public static boolean isValidRole(String test) {
        return test.toLowerCase().matches(VALIDATION_REGEX);
    }

    public boolean isVolunteer() {
        return this.value.equals("volunteer");
    }

    public boolean isBefriendee() {
        return this.value.equals("befriendee");
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

        Role otherName = (Role) other;
        return value.equals(otherName.value);
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}
