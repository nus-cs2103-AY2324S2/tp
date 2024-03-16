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

        // Invalid tag names
        assertFalse(Tag.isValidTagName("")); // Empty tag name
        assertFalse(Tag.isValidTagName("Buyers")); // Tag names should be lowercase
        assertFalse(Tag.isValidTagName("Sellers")); // Extra space at the end
        assertFalse(Tag.isValidTagName("Invalid Tag")); // Space within the tag name

        // Valid tag names
        assertTrue(Tag.isValidTagName("buyer"));
        assertTrue(Tag.isValidTagName("seller"));
    }

}
