package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class SymptomTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Symptom(null));
    }

    @Test
    public void constructor_invalidAddress_throwsIllegalArgumentException() {
        String invalidSymptom = "";
        assertThrows(IllegalArgumentException.class, () -> new Sex(invalidSymptom));
    }

    @Test
    public void equals() {
        Symptom symptom = new Symptom("Valid Symptom");

        // same values -> returns true
        assertTrue(symptom.equals(new Symptom("Valid Symptom")));

        // same object -> returns true
        assertTrue(symptom.equals(symptom));

        // null -> returns false
        assertFalse(symptom.equals(null));

        // different types -> returns false
        assertFalse(symptom.equals(5.0f));

        // different values -> returns false
        assertFalse(symptom.equals(new Symptom("Other Valid Symptom")));
    }
}