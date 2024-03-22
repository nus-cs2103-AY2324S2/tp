package seedu.realodex.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.realodex.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.realodex.model.remark.Remark;

public class RemarkTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Remark(null));
    }

    //May seem redundant, but for further iterations where we may validate remarks.
    @Test
    public void isValidRemark() {
        // null name
        assertThrows(NullPointerException.class, () -> Remark.isValidRemark(null));

        // invalid name
        // to-do in the future, ideas include but not limited to length constraints, no illegal characters.

        // valid name
        assertTrue(Remark.isValidRemark("I love apples")); // alphabets only
        assertTrue(Remark.isValidRemark("12345")); // numbers only
        assertTrue(Remark.isValidRemark("James Lau the 1st")); // alphanumeric characters
        assertTrue(Remark.isValidRemark("New York")); // with capital letters
        assertTrue(Remark.isValidRemark("Tony Stark, genius playboy billionaire philanthropist")); // long remark
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
    }
}

