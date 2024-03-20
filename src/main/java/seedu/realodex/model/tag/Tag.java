package seedu.realodex.model.tag;

import static java.util.Objects.requireNonNull;
import static seedu.realodex.commons.util.AppUtil.checkArgument;

/**
 * Represents a Tag in the realodex.
 * Guarantees: immutable; name is valid as declared in {@link #isValidTagName(String)}
 */
public class Tag {

    public static final String MESSAGE_CONSTRAINTS = "Tags names should be either 'buyer' or 'seller'";
    public static final String VALIDATION_REGEX = "\\p{Alnum}+";

    public final Role tagName;

    /**
     * Enumeration of Roles that a Person can take on
     */
    public enum Role {
        BUYER("Buyer"),
        SELLER("Seller");

        private String roleName;

        // Constructor for the enum to set the custom role name
        Role(String roleName) {
            this.roleName = roleName;
        }

        // Overriding the toString method to return the custom role name
        @Override
        public String toString() {
            return this.roleName;
        }
    }

    /**
     * Constructs a {@code Tag}.
     *
     * @param tagName A valid tag name.
     */
    public Tag(String tagName) {
        requireNonNull(tagName);
        checkArgument(isValidTagName(tagName), MESSAGE_CONSTRAINTS);
        this.tagName = Role.valueOf(tagName.toUpperCase());
    }

    /**
     * Returns true if a given string is a valid tag name.
     */
    public static boolean isValidTagName(String test) {
        try {
            Role.valueOf(test.toUpperCase());
        } catch (IllegalArgumentException e) {
            return false;
        }
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof Tag)) {
            return false;
        }

        Tag otherTag = (Tag) other;
        return tagName.roleName.equals(otherTag.tagName.roleName);
    }

    @Override
    public int hashCode() {
        return tagName.hashCode();
    }

    /**
     * Format state as text for viewing.
     */
    public String toString() {
        return '[' + tagName.toString() + ']';
    }

}
