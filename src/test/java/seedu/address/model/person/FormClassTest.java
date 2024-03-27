package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class FormClassTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new FormClass(null));
    }

    @Test
    public void constructor_invalidFormClass_throwsIllegalArgumentException() {
        String invalidClass = "6 A A A";
        assertThrows(IllegalArgumentException.class, () -> new FormClass(invalidClass));
    }

    @Test
    void isValidFormClass() {
        assertThrows(NullPointerException.class, () -> StudentId.isValidStudentId(null));

        // invalid
        assertFalse(FormClass.isValidClassName("")); // empty string
        assertFalse(FormClass.isValidClassName(" ")); // spaces only

        // valid
        assertTrue(FormClass.isValidClassName("6 A")); // exactly 2 words
    }

    @Test
    public void equals() {
        FormClass formClass = new FormClass("00001");

        // same values -> returns true
        assertTrue(formClass.equals(new FormClass("00001")));

        // same object -> returns true
        assertTrue(formClass.equals(formClass));

        // null -> returns false
        assertFalse(formClass.equals(null));

        // different types -> returns false
        assertFalse(formClass.equals(5.0f));

        // different values -> returns false
        assertFalse(formClass.equals(new FormClass("6 B")));
    }
}
