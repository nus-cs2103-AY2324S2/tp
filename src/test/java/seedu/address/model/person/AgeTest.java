package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class AgeTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Age(null));
    }

    @Test
    public void constructor_invalidAge_throwsIllegalArgumentException() {
        String invalidAge = "";
        assertThrows(IllegalArgumentException.class, () -> new Age(invalidAge));
    }

    @Test
    public void isValidAge() {
        // null age
        assertThrows(NullPointerException.class, () -> Age.isValidAge(null));

        // invalid age
        assertFalse(Age.isValidAge("")); // empty string
        assertFalse(Age.isValidAge(" ")); // spaces only
        assertFalse(Age.isValidAge("age")); // non-numeric
        assertFalse(Age.isValidAge("-1")); // negative number

        // valid age
        assertTrue(Age.isValidAge("1")); // one number
        assertTrue(Age.isValidAge("12")); // two numbers
        assertTrue(Age.isValidAge("123")); // three numbers
    }

    @Test
    public void equals() {
        Age age = new Age("123");

        // same values -> returns true
        assertTrue(age.equals(new Age("123")));

        // same object -> returns true
        assertTrue(age.equals(age));

        // null -> returns false
        assertFalse(age.equals(null));

        // different types -> returns false
        assertFalse(age.equals(5.0f));

        // different values -> returns false
        assertFalse(age.equals(new Age("321")));
    }

    @Test
    public void hash() {
        // same values -> returns true
        Age ageCopy = new Age("123");
        assertEquals(ageCopy.hashCode(), ageCopy.hashCode());

        // same object -> returns true
        Age age = new Age("123");
        assertEquals(age.hashCode(), age.hashCode());

        // different values -> returns false
        Age differentAge = new Age("321");
        assertNotEquals(age.hashCode(), differentAge.hashCode());
    }
}
