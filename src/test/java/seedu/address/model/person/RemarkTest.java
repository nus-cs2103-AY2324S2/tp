package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class RemarkTest {
    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Remark(null));
    }

    @Test
    public void equals() {
        Remark remark = new Remark("Valid Remark");

        // same values -> returns true
        assertEquals(remark, new Remark("Valid Remark"));

        // same object -> returns true
        assertEquals(remark, remark);

        // null -> returns false
        assertNotEquals(null, remark);

        // different types -> returns false
        assertFalse(remark.equals(0.5f));

        // different values -> returns false
        assertNotEquals(remark, new Remark("Other Valid Remark"));
    }
}
