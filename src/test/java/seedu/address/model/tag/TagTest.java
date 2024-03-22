package seedu.address.model.tag;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class TagTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Tag(null));
    }

    @Test
    public void constructor_invalidTagName_throwsIllegalArgumentException() {
        String invalidTagName = "";
        assertThrows(IllegalArgumentException.class, () -> new Tag(invalidTagName));
    }

    @Test
    public void isValidTagName() {
        // null tag name
        assertThrows(NullPointerException.class, () -> Tag.isValidTagName(null));
    }

    @Test
    public void isMatch() {
        Tag tag = new Tag("Lover");

        // Exact match -> returns true
        assertTrue(tag.isMatch("Lover"));

        // Substring partial word -> returns true
        assertTrue(tag.isMatch("Love"));

        // Additional whitespace
        assertTrue(tag.isMatch(" Lover\n"));

        // Substring mismatch
        assertFalse(tag.isMatch("invalid"));

        // Different type
        assertFalse(tag.isMatch(1));
    }

}
