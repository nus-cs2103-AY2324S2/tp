package seedu.address.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalPersons.ALICE;
import static seedu.address.testutil.TypicalPersons.getTypicalPatientList;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.patient.Patient;
import seedu.address.model.patient.exceptions.DuplicatePatientException;
import seedu.address.testutil.PersonBuilder;

public class PatientListTest {

    private final PatientList patientList = new PatientList();

    @Test
    public void constructor() {
        assertEquals(Collections.emptyList(), patientList.getPersonList());
    }

    @Test
    public void resetData_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> patientList.resetData(null));
    }

    @Test
    public void resetData_withValidReadOnlyPatientList_replacesData() {
        PatientList newData = getTypicalPatientList();
        patientList.resetData(newData);
        assertEquals(newData, patientList);
    }

    @Test
    public void resetData_withDuplicatePersons_throwsDuplicatePersonException() {
        // Two persons with the same identity fields
        Patient editedAlice = new PersonBuilder(ALICE).withId(13).withTags(VALID_TAG_HUSBAND)
                .build();
        List<Patient> newPatients = Arrays.asList(ALICE, editedAlice);
        PatientListStub newData = new PatientListStub(newPatients);

        assertThrows(DuplicatePatientException.class, () -> patientList.resetData(newData));
    }

    @Test
    public void hasPerson_nullPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> patientList.hasPerson(null));
    }

    @Test
    public void hasPerson_personNotInPatientList_returnsFalse() {
        assertFalse(patientList.hasPerson(ALICE));
    }

    @Test
    public void hasPerson_personInPatientList_returnsTrue() {
        patientList.addPerson(ALICE);
        assertTrue(patientList.hasPerson(ALICE));
    }

    @Test
    public void hasPerson_personWithSameIdentityFieldsInPatientList_returnsTrue() {
        patientList.addPerson(ALICE);
        Patient editedAlice = new PersonBuilder(ALICE).withId(19).withTags(VALID_TAG_HUSBAND)
                .build();
        assertTrue(patientList.hasPerson(editedAlice));
    }

    @Test
    public void getPersonList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> patientList.getPersonList().remove(0));
    }

    @Test
    public void toStringMethod() {
        String expected = PatientList.class.getCanonicalName() + "{persons=" + patientList.getPersonList() + "}";
        assertEquals(expected, patientList.toString());
    }

    /**
     * A stub ReadOnlyPatientList whose persons list can violate interface constraints.
     */
    private static class PatientListStub implements ReadOnlyPatientList {
        private final ObservableList<Patient> patients = FXCollections.observableArrayList();

        PatientListStub(Collection<Patient> patients) {
            this.patients.setAll(patients);
        }

        @Override
        public ObservableList<Patient> getPersonList() {
            return patients;
        }
    }

}
