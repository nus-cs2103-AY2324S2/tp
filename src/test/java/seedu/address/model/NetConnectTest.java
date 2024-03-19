package seedu.address.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalPersons.ALICE;
import static seedu.address.testutil.TypicalPersons.getTypicalNetConnect;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.person.Person;
import seedu.address.model.person.exceptions.DuplicatePersonException;
import seedu.address.testutil.PersonBuilder;

public class NetConnectTest {

    private final NetConnect netConnect = new NetConnect();

    @Test
    public void constructor() {
        assertEquals(Collections.emptyList(), netConnect.getPersonList());
    }

    @Test
    public void resetData_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> netConnect.resetData(null));
    }

    @Test
    public void resetData_withValidReadOnlyNetConnect_replacesData() {
        NetConnect newData = getTypicalNetConnect();
        netConnect.resetData(newData);
        assertEquals(newData, netConnect);
    }

    @Test
    public void resetData_withDuplicatePersons_throwsDuplicatePersonException() {
        // Two persons with the same identity fields
        Person editedAlice = new PersonBuilder(ALICE).withAddress(VALID_ADDRESS_BOB).withTags(VALID_TAG_HUSBAND)
                .build();
        List<Person> newPersons = Arrays.asList(ALICE, editedAlice);
        NetConnectStub newData = new NetConnectStub(newPersons);

        assertThrows(DuplicatePersonException.class, () -> netConnect.resetData(newData));
    }

    @Test
    public void hasPerson_nullPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> netConnect.hasPerson(null));
    }

    @Test
    public void hasPerson_personNotInNetConnect_returnsFalse() {
        assertFalse(netConnect.hasPerson(ALICE));
    }

    @Test
    public void hasPerson_personInNetConnect_returnsTrue() {
        netConnect.addPerson(ALICE);
        assertTrue(netConnect.hasPerson(ALICE));
    }

    @Test
    public void hasPerson_personWithSameIdentityFieldsInNetConnect_returnsTrue() {
        netConnect.addPerson(ALICE);
        Person editedAlice = new PersonBuilder(ALICE).withAddress(VALID_ADDRESS_BOB).withTags(VALID_TAG_HUSBAND)
                .build();
        assertTrue(netConnect.hasPerson(editedAlice));
    }

    @Test
    public void getPersonList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> netConnect.getPersonList().remove(0));
    }

    @Test
    public void toStringMethod() {
        String expected = NetConnect.class.getCanonicalName() + "{persons=" + netConnect.getPersonList() + "}";
        assertEquals(expected, netConnect.toString());
    }

    /**
     * A stub ReadOnlyNetConnect whose persons list can violate interface
     * constraints.
     */
    private static class NetConnectStub implements ReadOnlyNetConnect {
        private final ObservableList<Person> persons = FXCollections.observableArrayList();

        NetConnectStub(Collection<Person> persons) {
            this.persons.setAll(persons);
        }

        @Override
        public ObservableList<Person> getPersonList() {
            return persons;
        }
    }

}
