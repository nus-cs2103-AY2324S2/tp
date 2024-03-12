package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class DoBTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new DoB(null));
    }

    @Test
    public void constructor_invalidDoB_throwsIllegalArgumentException() {
        String invalidDoB = "";
        assertThrows(IllegalArgumentException.class, () -> new DoB(invalidDoB));
    }

    @Test
    public void isValidDoB() {
        // null name
        assertThrows(NullPointerException.class, () -> DoB.isValidDoB(null));

        // invalid name
        assertFalse(DoB.isValidDoB("")); // empty string
        assertFalse(DoB.isValidDoB(" ^"));
        assertFalse(DoB.isValidDoB("23-09-2003"));
        assertFalse(DoB.isValidDoB("2003-04-45"));

        // valid name
        assertTrue(DoB.isValidDoB("1998-01-01"));
        assertTrue(DoB.isValidDoB("2003-02-03"));
    }

    @Test
    public void equals() {
        DoB dob = new DoB("2003-04-02");

        // same values -> returns true
        assertTrue(dob.equals(new DoB("2003-04-02")));

        // same object -> returns true
        assertTrue(dob.equals(dob));

        // null -> returns false
        assertFalse(dob.equals(null));

        // different types -> returns false
        assertFalse(dob.equals(5.0f));

        // different values -> returns false
        assertFalse(dob.equals(new DoB("2003-04-12")));
    }
}
