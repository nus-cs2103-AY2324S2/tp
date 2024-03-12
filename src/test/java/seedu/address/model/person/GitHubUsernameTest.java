package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class GitHubUsernameTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new GitHubUsername(null));
    }

    @Test
    public void constructor_invalidGitHubUsername_throwsIllegalArgumentException() {
        String invalidGitHubUsername = "";
        assertThrows(IllegalArgumentException.class, () -> new GitHubUsername(invalidGitHubUsername));
    }

    @Test
    public void isValidGitHubUsername() {
        // null GitHubUsername
        assertThrows(NullPointerException.class, () -> GitHubUsername.isValidGitHubUsername(null));

        // invalid GitHubUsername
        assertFalse(GitHubUsername.isValidGitHubUsername("")); // empty string
        assertFalse(GitHubUsername.isValidGitHubUsername(" ")); // spaces only
        assertFalse(GitHubUsername.isValidGitHubUsername("^")); // only non-alphanumeric characters
        assertFalse(GitHubUsername.isValidGitHubUsername("user*name")); // contains non-alphanumeric characters

        // valid GitHubUsername
        assertTrue(GitHubUsername.isValidGitHubUsername("john-doe")); // dashes
        assertTrue(GitHubUsername.isValidGitHubUsername("user123")); // numbers only
        assertTrue(GitHubUsername.isValidGitHubUsername("github-user")); // alphanumeric characters and dashes
        assertTrue(GitHubUsername.isValidGitHubUsername("User")); // with capital letters
        assertTrue(GitHubUsername.isValidGitHubUsername("user123")); // with numbers
    }

    @Test
    public void equals() {
        GitHubUsername gitHubUsername = new GitHubUsername("valid-username");

        // same values -> returns true
        assertTrue(gitHubUsername.equals(new GitHubUsername("valid-username")));

        // same object -> returns true
        assertTrue(gitHubUsername.equals(gitHubUsername));

        // null -> returns false
        assertFalse(gitHubUsername.equals(null));

        // different types -> returns false
        assertFalse(gitHubUsername.equals(5.0f));

        // different values -> returns false
        assertFalse(gitHubUsername.equals(new GitHubUsername("other-username")));
    }
}
