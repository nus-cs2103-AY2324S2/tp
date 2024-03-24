package seedu.findvisor.model.person;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class RemarkTest {
    public static final String REMARK_VALUE = "Wants to afford a car after 5 years of working";

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Remark(null));
    }

    @Test
    public void createRemark_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> Remark.createRemark(null));
    }

    @Test
    public void createRemark_blank_returnsOptionalEmpty() {
        // Blank Remarks are still valid, although they are impossible to create through commands.
        assertTrue(Remark.createRemark("").isEmpty());
        assertTrue(Remark.createRemark("  ").isEmpty());
    }

    @Test
    public void createRemark_notBlank_returnsOptionalRemark() {
        assertEquals(Remark.createRemark(REMARK_VALUE).map(r -> r.value).orElse(""), REMARK_VALUE);

        // Leading and trailing whitespace not trimmed without ParseUtil.parseRemark
        assertNotEquals(Remark.createRemark("  " + REMARK_VALUE).map(r -> r.value).orElse(""), REMARK_VALUE);
    }

    @Test
    public void equals() {
        Remark remark = new Remark(REMARK_VALUE);

        // same values -> returns true
        assertTrue(remark.equals(new Remark(REMARK_VALUE)));

        // same object -> returns true
        assertTrue(remark.equals(remark));

        // same values but with createRemark -> returns true
        assertTrue(remark.equals(Remark.createRemark(REMARK_VALUE).get()));

        // null -> returns false
        assertFalse(remark.equals(null));

        // different types -> returns false
        assertFalse(remark.equals(5.0f));

        // different values -> returns false
        assertFalse(remark.equals(new Remark("Plans to start a family by age of 32")));
    }
}
