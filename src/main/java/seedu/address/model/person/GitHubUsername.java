package seedu.address.model.person;

import java.util.Objects;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Person's GitHub username in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidGitHubUsername(String)}
 */
public class GitHubUsername {

    public static final String MESSAGE_CONSTRAINTS =
            "GitHub username should only contain alphanumeric characters and dashes, and it should not be blank";

    /*
     * The first character of the address must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "[a-zA-Z0-9-]+";

    public final String username;

    /**
     * Constructs a {@code Name}.
     *
     * @param validUsername A valid username.
     */
    public GitHubUsername(String validUsername) {
        requireNonNull(validUsername);
        checkArgument(isValidGitHubUsername(validUsername), MESSAGE_CONSTRAINTS);
        username = validUsername;
    }

    /**
     * Returns true if a given string is a valid username.
     */
    public static boolean isValidGitHubUsername(String test) {
        return test.matches(VALIDATION_REGEX);
    }


    @Override
    public String toString() {
        return username;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }

        GitHubUsername other = (GitHubUsername) obj;
        return Objects.equals(username, other.username);
    }


    @Override
    public int hashCode() {
        return username.hashCode();
    }

}
