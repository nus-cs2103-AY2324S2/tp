package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class ConditionTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Condition(null));
    }

    @Test
    public void constructor_invalidAddress_throwsIllegalArgumentException() {
        String invalidCondition = "";
        assertThrows(IllegalArgumentException.class, () -> new Condition(invalidCondition));
    }

    @Test
    public void equals() {
        Condition condition = new Condition("Valid Condition");

        // same values -> returns true
        assertTrue(condition.equals(new Condition("Valid Condition")));

        // same object -> returns true
        assertTrue(condition.equals(condition));

        // null -> returns false
        assertFalse(condition.equals(null));

        // different types -> returns false
        assertFalse(condition.equals(5.0f));

        // different values -> returns false
        assertFalse(condition.equals(new Condition("Other Valid Condition")));
    }
}