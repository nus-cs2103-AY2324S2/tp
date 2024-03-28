package seedu.address.model.article;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents an Author of an Article
 */
public class Author {
    public static final String MESSAGE_CONSTRAINTS = "Author names should be alphanumeric";
    public static final String VALIDATION_REGEX = "[\\p{Alnum} ]+";

    public final String authorName;

    /**
     * Constructs a {@code authorName}.
     *
     * @param authorName A valid author name.
     */
    public Author(String authorName) {
        requireNonNull(authorName);
        checkArgument(isValidAuthorName(authorName), MESSAGE_CONSTRAINTS);
        this.authorName = authorName;
    }

    /**
     * Returns true if a given string is a valid author name.
     * TODO: Map to a valid author name in the address book
     */
    public static boolean isValidAuthorName(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof Author)) {
            return false;
        }

        Author otherAuthor = (Author) other;
        return authorName.equals(otherAuthor.authorName);
    }

    @Override
    public int hashCode() {
        return authorName.hashCode();
    }

    /**
     * Format state as text for viewing.
     */
    public String toString() {
        return '[' + authorName + ']';
    }
}
