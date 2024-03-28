package seedu.edulink.model.student;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.edulink.testutil.Assert.assertThrows;

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
        assertFalse(Id.isValidId("$agsaga")); //special characters
        assertFalse(Id.isValidId("agaga")); //all alphabets
        assertFalse(Id.isValidId("a9326014")); //Missing alphabet at the end
        assertFalse(Id.isValidId("MAGKAGHAG")); //All upper case
        assertFalse(Id.isValidId("A09125K")); //Format correct but not correct length


        // valid name
        assertTrue(Id.isValidId("A0265901E")); // Correct Format
        assertTrue(Id.isValidId("A0145156k")); //Combination of lower and uppercase
        assertTrue(Id.isValidId("a0265901T")); // combination of lower and upper case
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
        assertFalse(id.equals(new Id("A3561256M")));
    }
}
