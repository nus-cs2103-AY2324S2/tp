package seedu.address.model.tag;

import static org.junit.jupiter.api.Assertions.assertEquals;
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
    public void isEqualTag() {
        Tag tag1 = new Tag("Mentor");
        Tag tag2 = new Tag("Mentor");
        Tag tag3 = new Tag("SoupKitchen");
        assertTrue(tag1.equals(tag2));
        assertFalse(tag1.equals(tag3));

        assertTrue(tag1.equals(tag1));
        assertFalse(tag1.equals("test"));
    }

    @Test
    public void hashCodeTest() {
        Tag tag1 = new Tag("Mentor");
        Tag tag2 = new Tag("Mentor");
        assertEquals(tag1.hashCode(), tag2.hashCode());
    }

    @Test
    public void toStringTest() {
        Tag tag1 = new Tag("Mentor");
        Tag tag2 = new Tag("SoupKitchen");
        assertEquals(tag1.toString(), "[Mentor]");
        assertEquals(tag2.toString(), "[SoupKitchen]");
    }
}
