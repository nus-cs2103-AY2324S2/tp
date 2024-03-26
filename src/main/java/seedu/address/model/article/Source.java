package seedu.address.model.article;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Source (Contributor) to an Article
 */
public class Source {
    public static final String MESSAGE_CONSTRAINTS = "Source names should be alphanumeric";
    public static final String VALIDATION_REGEX = "[\\p{Alnum} ]+";

    public final String sourceName;

    /**
     * Constructs a {@code sourceName}.
     *
     * @param sourceName A valid source name.
     */
    public Source(String sourceName) {
        requireNonNull(sourceName);
        checkArgument(isValidSourceName(sourceName), MESSAGE_CONSTRAINTS);
        this.sourceName = sourceName;
    }

    /**
     * Returns true if a given string is a valid source name.
     * TODO: Map to a valid source name in the address book
     */
    public static boolean isValidSourceName(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof Source)) {
            return false;
        }

        Source otherSource = (Source) other;
        return sourceName.equals(otherSource.sourceName);
    }

    @Override
    public int hashCode() {
        return sourceName.hashCode();
    }

    /**
     * Format state as text for viewing.
     */
    public String toString() {
        return '[' + sourceName + ']';
    }
}
