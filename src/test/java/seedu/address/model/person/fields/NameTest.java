package seedu.address.model.person.fields;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class NameTest {

    private static final String WHITESPACE = " \t\r\n";
    private static final String INVALID_NAME = "R@chel";
    private static final String VALID_NAME = "Rachel Walker";

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Name(null));
    }

    @Test
    public void constructor_invalidName_throwsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> new Name("")); // empty string
        assertThrows(IllegalArgumentException.class, () -> new Name(" ")); // spaces only
        assertThrows(IllegalArgumentException.class, () -> new Name("^")); // only non-alphanumeric characters
        assertThrows(IllegalArgumentException.class, () -> new Name("peter*")); // contains non-alphanumeric characters
    }

    @Test
    public void constructor_validName_success() {
        assertDoesNotThrow(() -> new Name("peter jack")); // alphabets only
        assertDoesNotThrow(() -> new Name("12345")); // numbers only
        assertDoesNotThrow(() -> new Name("peter the 2nd")); // alphanumeric characters
        assertDoesNotThrow(() -> new Name("Capital Tan")); // with capital letters
        assertDoesNotThrow(() -> new Name("David Roger Jackson Ray Jr 2nd")); // long names
    }

    @Test
    public void of_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> Name.of((String) null));
    }

    @Test
    public void of_invalidValue_throwsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> Name.of(INVALID_NAME));
    }

    @Test
    public void of_validValueWithoutWhitespace_returnsName() throws Exception {
        Name expectedName = new Name(VALID_NAME);
        assertEquals(expectedName, Name.of(VALID_NAME));
    }

    @Test
    public void of_validValueWithWhitespace_returnsTrimmedName() throws Exception {
        String nameWithWhitespace = WHITESPACE + VALID_NAME + WHITESPACE;
        Name expectedName = new Name(VALID_NAME);
        assertEquals(expectedName, Name.of(nameWithWhitespace));
    }

    @Test
    public void equals() {
        Name name = new Name("Valid Name");

        // same values -> returns true
        Name name2 = new Name("Valid Name");
        assertTrue(name.equals(name2));
        assertEquals(name.hashCode(), name2.hashCode());

        // same object -> returns true
        assertTrue(name.equals(name));
        assertEquals(name.hashCode(), name.hashCode());

        // null -> returns false
        assertFalse(name.equals(null));

        // different types -> returns false
        assertFalse(name.equals(5.0f));

        // different values -> returns false
        Name otherName = new Name("Other Valid Name");
        assertFalse(name.equals(otherName));
    }

}
