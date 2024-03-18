package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class IdTest {

    @Test
    public void constructor_invalidId_throwsIllegalArgumentException() {
        int invalidId = 0;
        assertThrows(IllegalArgumentException.class, () -> new Id(invalidId));
    }

    @Test
    public void isValidId() {
        // invalid id
        assertFalse(Id.isValidId(0)); // zero
        assertFalse(Id.isValidId(-10)); // negative integer

        // valid name
        assertTrue(Id.isValidId(1));
        assertTrue(Id.isValidId(Integer.MAX_VALUE));
    }

    @Test
    public void equals() {
        Id id = new Id(1);

        // same values -> returns true
        assertEquals(id, new Id(1));

        // same object -> returns true
        assertEquals(id, id);

        // null -> returns false
        assertNotEquals(null, id);

        // different types -> returns false
        assertFalse(id.equals("hi"));

        // different values -> returns false
        assertNotEquals(id, new Id(2));
    }
}
