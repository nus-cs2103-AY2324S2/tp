package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class IdTest {
    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Id(null));
    }


    @Test
    public void isValidId() {
        // null name
        assertThrows(NullPointerException.class, () -> Id.isValidId(null));

        // invalid name
        assertFalse(Id.isValidId("$agsaga")); // empty string
        assertFalse(Id.isValidId("%agaga")); // spaces only
        assertFalse(Id.isValidId("a9326014")); // only non-alphanumeric characters
        assertFalse(Id.isValidId("lfasaha")); // contains non-alphanumeric characters

        // valid name
        assertTrue(Name.isValidName("A0265841")); // alphabets only
        assertTrue(Name.isValidName("A26252R"));
        assertTrue(Name.isValidName("A0265901T")); // alphanumeric characters
    }

    @Test
    public void equals() {
        Id id = new Id("A0265901E");

        // same values -> returns true
        assertTrue(id.equals(new Id("A0265901E")));

        // same object -> returns true
        assertTrue(id.equals(id));

        // null -> returns false
        assertFalse(id.equals(null));

        // different types -> returns false
        assertFalse(id.equals(5.0f));

        // different values -> returns false
        assertFalse(id.equals(new Id("A356125M")));
    }
}
