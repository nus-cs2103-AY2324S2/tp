package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;
public class IdTest {

    @Test
    public void constructor_nullId_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Id(null));
    }

    @Test
    public void constructor_invalidId_throwsIllegalArgumentException() {
        String invalidId = "123456789"; // invalid because it does not start and end with a letter
        assertThrows(IllegalArgumentException.class, () -> new Id(invalidId));
    }

    @Test
    public void isValidId() {
        // valid ids
        assertTrue(Id.isValidId("A1234567Z"));
        assertTrue(Id.isValidId("B9876543X"));

        // invalid ids
        assertFalse(Id.isValidId("123456789")); // does not start and end with a letter
        assertFalse(Id.isValidId("A123456789")); // more than 7 digits
        assertFalse(Id.isValidId("A12345X")); // less than 7 digits
        assertFalse(Id.isValidId("A12345 X")); // contains space
    }

    @Test
    public void equals() {
        Id id1 = new Id("A1234567Z");
        Id id3 = new Id("B9876543X");

        // same object -> returns true
        assertTrue(id1.equals(id1));

        // same values -> returns true
        assertTrue(id1.equals(new Id("A1234567Z")));

        // different values -> returns false
        assertFalse(id1.equals(id3));

        // null -> returns false
        assertFalse(id1.equals(null));

        // different types -> returns false
        assertFalse(id1.equals("A1234567Z"));
    }
}
