package seedu.address.model.patient;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class PatientHospitalIdTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new PatientHospitalId(null));
    }

    @Test
    public void constructor_invalidId_throwsIllegalArgumentException() {
        String invalidId = "";
        assertThrows(IllegalArgumentException.class, () -> new PatientHospitalId(invalidId));
    }

    @Test
    public void isValidId() {
        // null email
        assertThrows(NullPointerException.class, () -> PatientHospitalId.isValidPatientHospitalId(null));

        // blank email
        assertFalse(PatientHospitalId.isValidPatientHospitalId("")); // empty string
        assertFalse(PatientHospitalId.isValidPatientHospitalId(" ")); // spaces only

        // invalid parts
        assertFalse(PatientHospitalId.isValidPatientHospitalId("@23")); // contains non-integer character
        assertFalse(PatientHospitalId.isValidPatientHospitalId("@!")); // contains non-integer character
        assertFalse(PatientHospitalId.isValidPatientHospitalId("abc")); // contains character
        assertFalse(PatientHospitalId.isValidPatientHospitalId("123ab")); // contains character and integer
        assertFalse(PatientHospitalId.isValidPatientHospitalId("-123")); // contains negative integer

        // valid patient hospital id
        assertTrue(PatientHospitalId.isValidPatientHospitalId("12345")); // contains all integers
        assertTrue(PatientHospitalId.isValidPatientHospitalId("123")); // contains all integers in different length
    }

    @Test
    public void equals() {
        PatientHospitalId patientHospitalId = new PatientHospitalId("11234");

        // same values -> returns true
        assertTrue(patientHospitalId.equals(new PatientHospitalId("11234")));

        // same object -> returns true
        assertTrue(patientHospitalId.equals(patientHospitalId));

        // null -> returns false
        assertFalse(patientHospitalId.equals(null));

        // different types -> returns false
        assertFalse(patientHospitalId.equals(5.0f));

        // different values -> returns false
        assertFalse(patientHospitalId.equals(new PatientHospitalId("11235")));
    }
}
