package seedu.realodex.model.remark;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.realodex.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class RemarkTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Remark(null)); //Remark does not take in null,
        // only empty string
    }


    @Test
    public void isValidRemarkTest() {
        // null tag name
        assertThrows(NullPointerException.class, () -> Remark.isValidRemark(null));

        // Invalid tag names
        assertTrue(Remark.isValidRemark("")); // Empty remark
        assertTrue(Remark.isValidRemark("abc123")); // Alphanumeric characters allowed
        assertTrue(Remark.isValidRemark("<>?.' [];>")); // Special characters allowed
        assertTrue(Remark.isValidRemark("      ")); // Pure whitespace remark
    }

    @Test
    public void equals() {
        Remark remark = new Remark("Valid Remark");

        // same values -> returns true
        assertTrue(remark.equals(new Remark("Valid Remark")));

        // same object -> returns true
        assertTrue(remark.equals(remark));

        // null -> returns false
        assertFalse(remark == null);

        // different types -> returns false
        assertFalse(remark.equals(5.0f));

        // different values -> returns false
        assertFalse(remark.equals(new Remark("Other Valid Name")));

        Remark emptyRemark = new Remark("");
        Remark whiteSpaceRemark = new Remark("       ");
        assertTrue(emptyRemark.equals(whiteSpaceRemark));
    }

    @Test
    public void toStringWithRepresentationTest() {
        Remark emptyRemark = new Remark(""); // Create a Remark object with an empty string
        Remark nonEmptyRemark = new Remark("This is a remark");
        Remark specialCharRemark = new Remark("<>:}{{}()}");
        Remark whiteSpaceRemark = new Remark("       ");

        //Empty remark will be represented with "No remark." instead of just a blank
        assertEquals("No remark.", emptyRemark.toStringWithRepresentation());
        assertEquals("Remark: This is a remark", nonEmptyRemark.toStringWithRepresentation());
        assertEquals("Remark: <>:}{{}()}", specialCharRemark.toStringWithRepresentation());
        assertEquals("No remark.", whiteSpaceRemark.toStringWithRepresentation());
    }
}
