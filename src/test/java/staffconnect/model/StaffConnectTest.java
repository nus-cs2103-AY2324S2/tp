package staffconnect.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static staffconnect.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static staffconnect.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static staffconnect.testutil.Assert.assertThrows;
import static staffconnect.testutil.TypicalPersons.ALICE;
import static staffconnect.testutil.TypicalPersons.getTypicalAddressBook;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import staffconnect.model.person.Person;
import staffconnect.model.person.exceptions.DuplicatePersonException;
import staffconnect.testutil.PersonBuilder;

public class StaffConnectTest {

    private final StaffConnect staffConnect = new StaffConnect();

    @Test
    public void constructor() {
        assertEquals(Collections.emptyList(), staffConnect.getPersonList());
    }

    @Test
    public void resetData_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> staffConnect.resetData(null));
    }

    @Test
    public void resetData_withValidReadOnlyAddressBook_replacesData() {
        StaffConnect newData = getTypicalAddressBook();
        staffConnect.resetData(newData);
        assertEquals(newData, staffConnect);
    }

    @Test
    public void resetData_withDuplicatePersons_throwsDuplicatePersonException() {
        // Two persons with the same identity fields
        Person editedAlice = new PersonBuilder(ALICE).withAddress(VALID_ADDRESS_BOB).withTags(VALID_TAG_HUSBAND)
                .build();
        List<Person> newPersons = Arrays.asList(ALICE, editedAlice);
        StaffConnectStub newData = new StaffConnectStub(newPersons);

        assertThrows(DuplicatePersonException.class, () -> staffConnect.resetData(newData));
    }

    @Test
    public void hasPerson_nullPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> staffConnect.hasPerson(null));
    }

    @Test
    public void hasPerson_personNotInAddressBook_returnsFalse() {
        assertFalse(staffConnect.hasPerson(ALICE));
    }

    @Test
    public void hasPerson_personInAddressBook_returnsTrue() {
        staffConnect.addPerson(ALICE);
        assertTrue(staffConnect.hasPerson(ALICE));
    }

    @Test
    public void hasPerson_personWithSameIdentityFieldsInAddressBook_returnsTrue() {
        staffConnect.addPerson(ALICE);
        Person editedAlice = new PersonBuilder(ALICE).withAddress(VALID_ADDRESS_BOB).withTags(VALID_TAG_HUSBAND)
                .build();
        assertTrue(staffConnect.hasPerson(editedAlice));
    }

    @Test
    public void getPersonList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> staffConnect.getPersonList().remove(0));
    }

    @Test
    public void toStringMethod() {
        String expected = StaffConnect.class.getCanonicalName() + "{persons=" + staffConnect.getPersonList() + "}";
        assertEquals(expected, staffConnect.toString());
    }

    /**
     * A stub ReadOnlyStaffConnect whose persons list can violate interface constraints.
     */
    private static class StaffConnectStub implements ReadOnlyStaffConnect {
        private final ObservableList<Person> persons = FXCollections.observableArrayList();

        StaffConnectStub(Collection<Person> persons) {
            this.persons.setAll(persons);
        }

        @Override
        public ObservableList<Person> getPersonList() {
            return persons;
        }
    }

}
