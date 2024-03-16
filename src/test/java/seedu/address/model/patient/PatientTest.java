package seedu.address.model.patient;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_FAMILY_CONDITION_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_FOOD_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_HOBBY_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ID_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PREFERRED_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_DEPRESSION;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalPatients.ALICE;
import static seedu.address.testutil.TypicalPatients.BOB;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.PatientBuilder;

public class PatientTest {

    @Test
    public void asObservableList_modifyList_throwsUnsupportedOperationException() {
        Patient patient = new PatientBuilder().build();
        assertThrows(UnsupportedOperationException.class, () -> patient.getTags().remove(0));
    }

    @Test
    public void isSamePerson() {
        // same object -> returns true
        assertTrue(ALICE.isSamePatient(ALICE));

        // null -> returns false
        assertFalse(ALICE.isSamePatient(null));

        // same name, all other attributes different -> returns true
        Patient editedAlice = new PatientBuilder(ALICE).withPatientHospitalId(VALID_ID_BOB)
            .withPreferredName(VALID_PREFERRED_NAME_BOB).withFoodPreference(VALID_FOOD_BOB)
            .withFamilyCondition(VALID_FAMILY_CONDITION_BOB).withHobby(VALID_HOBBY_BOB).withTags(VALID_TAG_DEPRESSION)
            .build();
        assertTrue(ALICE.isSamePatient(editedAlice));

        // different name, all other attributes same -> returns false
        editedAlice = new PatientBuilder(ALICE).withName(VALID_NAME_BOB).build();
        assertFalse(ALICE.isSamePatient(editedAlice));

        // name differs in case, all other attributes same -> returns false
        Patient editedBob = new PatientBuilder(BOB).withName(VALID_NAME_BOB.toLowerCase()).build();
        assertFalse(BOB.isSamePatient(editedBob));

        // name has trailing spaces, all other attributes same -> returns false
        String nameWithTrailingSpaces = VALID_NAME_BOB + " ";
        editedBob = new PatientBuilder(BOB).withName(nameWithTrailingSpaces).build();
        assertFalse(BOB.isSamePatient(editedBob));
    }

    @Test
    public void equals() {
        // same values -> returns true
        Patient aliceCopy = new PatientBuilder(ALICE).build();
        assertTrue(ALICE.equals(aliceCopy));

        // same object -> returns true
        assertTrue(ALICE.equals(ALICE));

        // null -> returns false
        assertFalse(ALICE.equals(null));

        // different type -> returns false
        assertFalse(ALICE.equals(5));

        // different patient -> returns false
        assertFalse(ALICE.equals(BOB));

        // different name -> returns false
        Patient editedAlice = new PatientBuilder(ALICE).withName(VALID_NAME_BOB).build();
        assertFalse(ALICE.equals(editedAlice));

        // different patient hospital id -> returns false
        editedAlice = new PatientBuilder(ALICE).withPatientHospitalId(VALID_ID_BOB).build();
        assertFalse(ALICE.equals(editedAlice));

        // different preferred name -> returns false
        editedAlice = new PatientBuilder(ALICE).withPreferredName(VALID_PREFERRED_NAME_BOB).build();
        assertFalse(ALICE.equals(editedAlice));

        // different food preference -> returns false
        editedAlice = new PatientBuilder(ALICE).withFoodPreference(VALID_FOOD_BOB).build();
        assertFalse(ALICE.equals(editedAlice));

        // different family condition -> returns false
        editedAlice = new PatientBuilder(ALICE).withFamilyCondition(VALID_FAMILY_CONDITION_BOB).build();
        assertFalse(ALICE.equals(editedAlice));

        // different hobby -> returns false
        editedAlice = new PatientBuilder(ALICE).withHobby(VALID_HOBBY_BOB).build();
        assertFalse(ALICE.equals(editedAlice));
    }

    @Test
    public void toStringMethod() {
        String expected = Patient.class.getCanonicalName() + "{patientHospitalId=" + ALICE.getPatientHospitalId()
            + ", name=" + ALICE.getName() + ", preferredName=" + ALICE.getPreferredName()
            + ", foodPreference=" + ALICE.getFoodPreference() + ", familyCondition=" + ALICE.getFamilyCondition()
            + ", hobby=" + ALICE.getHobby()
            + ", tags=" + ALICE.getTags() + ", importantDates=" + ALICE.getImportantDates() + "}";;
        assertEquals(expected, ALICE.toString());
    }
}
