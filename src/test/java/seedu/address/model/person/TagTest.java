package seedu.address.model.person;

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
    public void constructor_invalidTag_throwsIllegalArgumentException() {
        String invalidTag = "";
        assertThrows(IllegalArgumentException.class, () -> new Tag(invalidTag));
    }

    @Test
    public void isValidTag() {
        // null tag
        assertThrows(NullPointerException.class, () -> Tag.isValidTag(null));

        // invalid tags
        assertFalse(Tag.isValidTag("")); // empty string
        assertFalse(Tag.isValidTag(" ")); // spaces only

        // valid tags
        assertTrue(Tag.isValidTag("Student"));
        assertTrue(Tag.isValidTag("Professor"));
        assertTrue(Tag.isValidTag("TA"));
    }

    @Test
    public void equals() {
        Tag tag = new Tag("Student");

        // same values -> returns true
        assertTrue(tag.equals(new Tag("Student")));

        // same object -> returns true
        assertTrue(tag.equals(tag));

        // null -> returns false
        assertFalse(tag.equals(null));

        // different types -> returns false
        assertFalse(tag.equals(5.0f));

        // different values -> returns false
        assertFalse(tag.equals(new Tag("Professor")));
    }
}
