package seedu.address.model.tag;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class TagTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Tag(null));
    }

    @Test
    public void constructor_invalidTagName_throwsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> new Tag("")); // empty string
        assertThrows(IllegalArgumentException.class, () -> new Tag("Hello@World")); // not alphanumeric
        assertThrows(IllegalArgumentException.class, () -> new Tag("Testing 123")); // contains space
        assertThrows(IllegalArgumentException.class, () -> new Tag("Café123")); // contains illegal unicode character
    }

    @Test
    public void isValidTagName() {
        assertDoesNotThrow(() -> new Tag("a"));
        assertDoesNotThrow(() -> new Tag("abc"));
        assertDoesNotThrow(() -> new Tag("validTag"));
        assertDoesNotThrow(() -> new Tag("ValidTag"));
        assertDoesNotThrow(() -> new Tag("VALIDTAG"));
    }

}
