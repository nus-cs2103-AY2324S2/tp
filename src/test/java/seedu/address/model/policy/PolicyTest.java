package seedu.address.model.policy;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class PolicyTest {
    private static final String VALID_NAME = "Life";
    private static final String VALID_ID = "123";
    private static final String INVALID_NAME = "#Life";
    private static final String INVALID_ID = "#123";

    private static final Policy policy = new Policy(VALID_NAME, VALID_ID);

    @Test
    public void isIdTestTrue() {
        assertTrue(policy.isID(VALID_ID));
    }
    @Test
    public void isIdTestFalse() {
        assertFalse(policy.isID("124"));
    }

    @Test
    public void isValidNameTestTrue() {
        assertTrue(Policy.isValidName(VALID_NAME));
    }

    @Test
    public void isValidNameTestFalse() {
        assertFalse(Policy.isValidName(INVALID_NAME));
    }

    @Test
    public void isValidIdTestTrue() {
        assertTrue(Policy.isValidID(VALID_ID));
    }

    @Test
    public void isValidIdTestFalse() {
        assertFalse(Policy.isValidID(INVALID_ID));
    }

    @Test
    public void hasSameIdTestTrue() {
        assertTrue(policy.hasSameID(policy));
    }
    @Test
    public void hasSameIdTestFalse() {
        Policy otherPolicy = new Policy("Health", "456");
        assertFalse(policy.hasSameID(otherPolicy));
    }

    @Test
    public void toStringMethod() {
        assertEquals(policy.toString(), "Name:" + VALID_NAME + ", Type:DEFAULT, Policy ID:" + VALID_ID);
    }

    @Test
    public void equals() {
        Policy policyCopy = new Policy(VALID_NAME, VALID_ID);
        Policy differentPolicy = new Policy("Health", "456");
        Policy sameNameDifferentId = new Policy(VALID_NAME, "456");
        Policy sameIdDifferentName = new Policy("Health", VALID_ID);
        assertTrue(policy.equals(policyCopy));
        assertTrue(policy.equals(policy));
        assertFalse(policy.equals(null));
        assertFalse(policy.equals(5));
        assertFalse(policy.equals(differentPolicy));
        assertFalse(policy.equals(sameNameDifferentId));
        assertFalse(policy.equals(sameIdDifferentName));

    }
}
