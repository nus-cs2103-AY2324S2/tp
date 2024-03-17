package seedu.address.model.order;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

class RemarkTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Remark(null));
    }

    @Test
    public void constructor_invalidRemark_throwsIllegalArgumentException() {
        String invalidRemark = "";
        assertThrows(IllegalArgumentException.class, () -> new Remark(invalidRemark));
    }

    @Test
    public void isValidRemark() {
        // null remark
        assertThrows(NullPointerException.class, () -> Remark.isValidRemark(null));

        // invalid remarks
        assertFalse(Remark.isValidRemark("")); // empty string
        assertFalse(Remark.isValidRemark(" ")); // spaces only

        // valid remarks
        assertTrue(Remark.isValidRemark("A valid remark.")); // Non-empty remark
        assertTrue(Remark.isValidRemark("12345")); // Numbers only
        assertTrue(Remark.isValidRemark("Remark with spaces")); // Contains spaces
        assertTrue(Remark.isValidRemark("Remark-with-dashes")); // Contains dashes
    }

    @Test
    public void equals() {
        Remark remark = new Remark("A valid remark.");

        // same values -> returns true
        assertEquals(remark, new Remark("A valid remark."));

        // same object -> returns true
        assertEquals(remark, remark);

        // null -> returns false
        assertNotEquals(null, remark);

        // different types -> returns false
        assertNotEquals(remark, 0.0);

        // different values -> returns false
        assertNotEquals(remark, new Remark("Another remark"));
    }

}
