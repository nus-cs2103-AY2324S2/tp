package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class DiagnosisTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Diagnosis(null));
    }

    @Test
    public void constructor_invalidAddress_throwsIllegalArgumentException() {
        String invalidDiagnosis = "";
        assertThrows(IllegalArgumentException.class, () -> new Diagnosis(invalidDiagnosis));
    }

    @Test
    public void equals() {
        Diagnosis diagnosis = new Diagnosis("Valid Diagnosis");

        // same values -> returns true
        assertTrue(diagnosis.equals(new Diagnosis("Valid Diagnosis")));

        // same object -> returns true
        assertTrue(diagnosis.equals(diagnosis));

        // null -> returns false
        assertFalse(diagnosis.equals(null));

        // different types -> returns false
        assertFalse(diagnosis.equals(5.0f));

        // different values -> returns false
        assertFalse(diagnosis.equals(new Diagnosis("Other Valid Diagnosis")));
    }
}