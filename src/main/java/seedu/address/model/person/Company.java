package seedu.address.model.person;

import static java.util.Objects.requireNonNull;

/**
 * Represents a Person's group name in the address book.
 * Guarantees: immutable; is always valid
 */
public class Group {
    public final String value;

    public Group(String groupName) {
        requireNonNull(groupName);
        value = groupName;
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Group // instanceof handles nulls
                && value.equals(((Group) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}