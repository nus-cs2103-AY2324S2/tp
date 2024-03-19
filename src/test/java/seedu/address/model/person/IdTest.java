package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class IdTest {

    @Test
    public void generateNextId() {
        Id id = Id.generateNextId();
        Id next = Id.generateNextId();
        assertEquals(next.value, id.value + 1);
    }

    @Test
    public void generateId() {
        int nextValue = Id.generateNextId().value + 1;
        Id id = Id.generateId(nextValue);
        Id next = Id.generateNextId();
        assertEquals(next.value, id.value + 1);
    }

    @Test
    public void factoryMethods_invalidId_throwsIllegalArgumentException() {
        // Invalid id
        int invalidId = 0;
        assertThrows(IllegalArgumentException.class, () -> Id.generateId(invalidId));
        assertThrows(IllegalArgumentException.class, () -> Id.generateTempId(invalidId));
    }

    @Test
    public void isValidId() {
        // invalid id
        assertFalse(Id.isValidId(0)); // zero
        assertFalse(Id.isValidId(-10)); // negative integer

        // valid id
        assertTrue(Id.isValidId(1));
        assertTrue(Id.isValidId(Integer.MAX_VALUE));
    }

    @Test
    public void equals() {
        Id id = Id.generateId(1);

        // same values -> returns true
        assertEquals(id, Id.generateId(1));

        // same object -> returns true
        assertEquals(id, id);

        // null -> returns false
        assertNotEquals(null, id);

        // different types -> returns false
        assertFalse(id.equals("hi"));

        // different values -> returns false
        assertNotEquals(id, Id.generateId(2));
    }
}
