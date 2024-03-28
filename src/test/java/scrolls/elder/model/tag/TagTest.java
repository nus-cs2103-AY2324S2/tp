package scrolls.elder.model.tag;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.Test;

import scrolls.elder.testutil.Assert;

public class TagTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        Assert.assertThrows(NullPointerException.class, () -> new Tag(null));
    }

    @Test
    public void constructor_invalidTagName_throwsIllegalArgumentException() {
        String invalidTagName = "";
        Assert.assertThrows(IllegalArgumentException.class, () -> new Tag(invalidTagName));
    }

    @Test
    public void isValidTagName() {
        // null tag name
        Assert.assertThrows(NullPointerException.class, () -> Tag.isValidTagName(null));
    }

    @Test
    public void equalsMethod() {
        Tag tag1 = new Tag("tag1");
        Tag tag2 = new Tag("tag1");

        // same values -> returns true
        assertEquals(tag1, tag2);

        // same object -> returns true
        assertEquals(tag1, tag1);

        // null -> returns false
        assertNotEquals(tag1, null);

        // different types -> returns false
        assertNotEquals(tag1, new Object());

        // different tag -> returns false
        Tag differentTag = new Tag("tag2");
        assertNotEquals(tag1, differentTag);
    }
}
