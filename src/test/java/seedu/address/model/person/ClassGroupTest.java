package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class ClassGroupTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new ClassGroup(null));
    }

    @Test
    public void constructor_invalidClassGroup_throwsIllegalArgumentException() {
        String invalidClassGroup = "";
        assertThrows(IllegalArgumentException.class, () -> new ClassGroup(invalidClassGroup));
    }

    @Test
    public void isValidClassGroup() {
        // null ClassGroup
        assertThrows(NullPointerException.class, () -> ClassGroup.isValidClassGroup(null));

        // blank ClassGroup
        assertFalse(ClassGroup.isValidClassGroup("")); // empty string
        assertFalse(ClassGroup.isValidClassGroup(" ")); // spaces only

        // invalid parts
        assertFalse(ClassGroup.isValidClassGroup("-")); // starts with dash
        assertFalse(ClassGroup.isValidClassGroup("F14-")); // ends with dash
        assertFalse(ClassGroup.isValidClassGroup("-F14")); // begin with dash
        assertFalse(ClassGroup.isValidClassGroup("@F14")); // non alpha-numeric characters
        assertFalse(ClassGroup.isValidClassGroup(" F14")); // leading space
        assertFalse(ClassGroup.isValidClassGroup("F14 ")); // trailing space
        assertFalse(ClassGroup.isValidClassGroup("F143 3")); // space in middle

        // valid ClassGroup
        assertTrue(ClassGroup.isValidClassGroup("A-1")); // One dash
        assertTrue(ClassGroup.isValidClassGroup("A")); // no dash at all
        assertTrue(ClassGroup.isValidClassGroup("CS2103-F14-3")); // multiple dashes
    }

    @Test
    public void equals() {
        ClassGroup classGroup = new ClassGroup("A-1");

        // same values -> returns true
        assertTrue(classGroup.equals(new ClassGroup("A-1")));

        // same object -> returns true
        assertTrue(classGroup.equals(classGroup));

        // null -> returns false
        assertFalse(classGroup.equals(null));

        // different types -> returns false
        assertFalse(classGroup.equals(5.0f));

        // different values -> returns false
        assertFalse(classGroup.equals(new ClassGroup("B-2")));
    }
}
