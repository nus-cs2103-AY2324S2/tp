package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Person's GitHub ID in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidGithub(String)}
 */
public class Github {

    public static final String MESSAGE_CONSTRAINTS =
            "GitHub ID should only contain alphanumeric characters and symbols, and it should not be blank";

    public static final Github EMPTY = new Github("", true);

    /*
     * The first character of the address must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "^[a-zA-Z0-9_.-]+$";

    public final String githubId;

    /**
     * Constructs a {@code Github}.
     *
     * @param githubId A valid Github ID.
     */
    public Github(String githubId) {
        requireNonNull(githubId);
        checkArgument(isValidGithub(githubId), MESSAGE_CONSTRAINTS);
        this.githubId = githubId;
    }

    /**
     * Constructs a {@code Github}.
     *
     * @param githubId An empty string.
     */
    private Github(String githubId, boolean isSentinel) {
        if (!isSentinel) {
            throw new IllegalArgumentException("This constructor is only for creating the EMPTY object");
        }
        this.githubId = githubId;
    }

    /**
     * Returns true if a given string is a valid GitHub ID.
     */
    public static boolean isValidGithub(String test) {
        return test.matches(VALIDATION_REGEX);
    }


    @Override
    public String toString() {
        return githubId;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof Github)) {
            return false;
        }

        Github otherGithub = (Github) other;
        return githubId.equals(otherGithub.githubId);
    }

    @Override
    public int hashCode() {
        return githubId.hashCode();
    }

}
