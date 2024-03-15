package vitalconnect.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static vitalconnect.logic.commands.CommandTestUtil.VALID_NRIC_BOB;
import static vitalconnect.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static vitalconnect.testutil.Assert.assertThrows;
import static vitalconnect.testutil.TypicalPersons.ALICE;
import static vitalconnect.testutil.TypicalPersons.getTypicalClinic;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import vitalconnect.model.person.Person;
import vitalconnect.model.person.exceptions.DuplicatePersonException;
import vitalconnect.testutil.PersonBuilder;

public class ClinicTest {

    private final Clinic clinic = new Clinic();

    @Test
    public void constructor() {
        assertEquals(Collections.emptyList(), clinic.getPersonList());
    }

    @Test
    public void resetData_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> clinic.resetData(null));
    }

    @Test
    public void resetData_withValidReadOnlyClinic_replacesData() {
        Clinic newData = getTypicalClinic();
        clinic.resetData(newData);
        assertEquals(newData, clinic);
    }

    @Test
    public void resetData_withDuplicatePersons_throwsDuplicatePersonException() {
        // Two persons with the same identity fields
        Person editedAlice = new PersonBuilder(ALICE).withNric(VALID_NRIC_BOB).withTags(VALID_TAG_HUSBAND)
                .build();
        List<Person> newPersons = Arrays.asList(ALICE, editedAlice);
        ClinicStub newData = new ClinicStub(newPersons);

        assertThrows(DuplicatePersonException.class, () -> clinic.resetData(newData));
    }

    @Test
    public void hasPerson_nullPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> clinic.hasPerson(null));
    }

    @Test
    public void hasPerson_personNotInClinic_returnsFalse() {
        assertFalse(clinic.hasPerson(ALICE));
    }

    @Test
    public void hasPerson_personInClinic_returnsTrue() {
        clinic.addPerson(ALICE);
        assertTrue(clinic.hasPerson(ALICE));
    }

    @Test
    public void hasPerson_personWithSameIdentityFieldsInClinic_returnsTrue() {
        clinic.addPerson(ALICE);
        Person editedAlice = new PersonBuilder(ALICE).withNric(VALID_NRIC_BOB).withTags(VALID_TAG_HUSBAND)
                .build();
        assertTrue(clinic.hasPerson(editedAlice));
    }

    @Test
    public void getPersonList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> clinic.getPersonList().remove(0));
    }

    @Test
    public void toStringMethod() {
        String expected = Clinic.class.getCanonicalName() + "{persons=" + clinic.getPersonList() + "}";
        assertEquals(expected, clinic.toString());
    }

    /**
     * A stub ReadOnlyClinic whose persons list can violate interface constraints.
     */
    private static class ClinicStub implements ReadOnlyClinic {
        private final ObservableList<Person> persons = FXCollections.observableArrayList();

        ClinicStub(Collection<Person> persons) {
            this.persons.setAll(persons);
        }

        @Override
        public ObservableList<Person> getPersonList() {
            return persons;
        }
    }

}
