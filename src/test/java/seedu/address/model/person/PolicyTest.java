package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class PolicyTest {

    @Test
    public void equals() {
        Policy policy = new Policy("Hello");

        // same object -> returns true
        assertTrue(policy.equals(policy));

        // same values -> returns true
        Policy policyCopy = new Policy(policy.value);
        assertTrue(policy.equals(policyCopy));

        // different types -> returns false
        assertFalse(policy.equals(1));

        // null -> returns false
        assertFalse(policy.equals(null));

        // different policy -> returns false
        Policy differentPolicy = new Policy("Bye");
        assertFalse(policy.equals(differentPolicy));
    }
}
