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
        // null Id
        assertThrows(NullPointerException.class, () -> Id.isValidId(null));

        // invalid Id
        assertFalse(Id.isValidId("$agsaga")); // empty string
        assertFalse(Id.isValidId("%agaga"));
        assertFalse(Id.isValidId("a9326014"));
        assertFalse(Id.isValidId("lfasaha"));

        // valid name
        assertTrue(Name.isValidName("A0265841")); // alphabets only
        assertTrue(Name.isValidName("A26252R"));
        assertTrue(Name.isValidName("A0265901T")); // alphanumeric characters
    }

    @Test
    public void stringTest() {
        Id id = new Id("A0265901E");

        assertTrue(id.toString().equals("A0265901E"));
        assertFalse(id.toString().equals("A91251516E"));
    }

    @Test
    public void hashTest() {
        Id id = new Id("A0265901E");
        String valid = "A0265901E";
        String inValid = "A02621405L";

        assertTrue(id.hashCode() == valid.hashCode());
        assertFalse(id.hashCode() == inValid.hashCode());
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
