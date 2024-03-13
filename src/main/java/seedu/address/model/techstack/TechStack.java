package seedu.address.model.techstack;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Tech Stack in the address book.
 * Guarantees: immutable; name is valid as declared in {@link #isValidTechStackName(String)}
 */
public class TechStack {

    public static final String MESSAGE_CONSTRAINTS = "Tech stack names should be alphanumeric";
    public static final String VALIDATION_REGEX = "\\p{Alnum}+";

    public final String techStackName;

    /**
     * Constructs a {@code TechStack}.
     *
     * @param techStackName A valid tech stack name.
     */
    public TechStack(String techStackName) {
        requireNonNull(techStackName);
        checkArgument(isValidTechStackName(techStackName), MESSAGE_CONSTRAINTS);
        this.techStackName = techStackName;
    }

    /**
     * Returns true if a given string is a valid tech stack name.
     */
    public static boolean isValidTechStackName(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof TechStack)) {
            return false;
        }

        TechStack otherTechStack = (TechStack) other;
        return techStackName.equals(otherTechStack.techStackName);
    }

    @Override
    public int hashCode() {
        return techStackName.hashCode();
    }

    /**
     * Format state as text for viewing.
     */
    public String toString() {
        return '[' + techStackName + ']';
    }

}
