package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class MajorTest {
    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Major(null));
    }


    @Test
    public void isValidMajor() {
        // null Id
        assertThrows(NullPointerException.class, () -> Major.isValidMajor(null));

        // invalid Id
        assertFalse(Major.isValidMajor("#!")); //special characters
        assertFalse(Major.isValidMajor("2151516")); //Numeric
        assertFalse(Major.isValidMajor("a9326014")); //Alphanumeric
        assertFalse(Major.isValidMajor("Chem1351")); //Alphanumeric


        // valid name
        assertTrue(Major.isValidMajor("Physics")); // Correct Format
        assertTrue(Major.isValidMajor("Computer Science")); //Combination of lower and uppercase
        assertTrue(Major.isValidMajor("Medicine")); // combination of lower and upper case
    }

    @Test
    public void stringTest() {
        Major major = new Major("Physics");

        assertTrue(major.toString().equals("Physics"));
        assertFalse(major.toString().equals("Chemistry"));
   }

    @Test
    public void hashTest() {
        Major major = new Major("Physics");
        String valid = "Physics";
        String inValid = "Mathematics";

        assertTrue(major.hashCode() == valid.hashCode());
        assertFalse(major.hashCode() == inValid.hashCode());
    }
    @Test
    public void equals() {
        Major major = new Major("Chemistry");

        // same values -> returns true
        assertTrue(major.equals(new Major("Chemistry")));

        // same object -> returns true
        assertTrue(major.equals(major));

        // null -> returns false
        assertFalse(major.equals(null));

        // different types -> returns false
        assertFalse(major.equals(5.0f));

        // different values -> returns false
        assertFalse(major.equals(new Major("Physics")));
    }
}
