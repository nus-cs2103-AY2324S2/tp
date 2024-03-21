package seedu.address.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalPersons.ALICE;
import static seedu.address.testutil.TypicalPersons.getTypicalPayBack;

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

public class PayBackTest {

    private final PayBack payBack = new PayBack();

    @Test
    public void constructor() {
        assertEquals(Collections.emptyList(), payBack.getPersonList());
    }

    @Test
    public void resetData_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> payBack.resetData(null));
    }

    @Test
    public void resetData_withValidReadOnlyPayBack_replacesData() {
        PayBack newData = getTypicalPayBack();
        payBack.resetData(newData);
        assertEquals(newData, payBack);
    }

    @Test
    public void resetData_withDuplicatePersons_throwsDuplicatePersonException() {
        // Two persons with the same identity fields
        Person editedAlice = new PersonBuilder(ALICE).withAddress(VALID_ADDRESS_BOB).withTags(VALID_TAG_HUSBAND)
                .build();
        List<Person> newPersons = Arrays.asList(ALICE, editedAlice);
        PayBackStub newData = new PayBackStub(newPersons);

        assertThrows(DuplicatePersonException.class, () -> payBack.resetData(newData));
    }

    @Test
    public void hasPerson_nullPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> payBack.hasPerson(null));
    }

    @Test
    public void hasPerson_personNotInPayBack_returnsFalse() {
        assertFalse(payBack.hasPerson(ALICE));
    }

    @Test
    public void hasPerson_personInPayBack_returnsTrue() {
        payBack.addPerson(ALICE);
        assertTrue(payBack.hasPerson(ALICE));
    }

    @Test
    public void hasPerson_personWithSameIdentityFieldsInPayBack_returnsTrue() {
        payBack.addPerson(ALICE);
        Person editedAlice = new PersonBuilder(ALICE).withAddress(VALID_ADDRESS_BOB).withTags(VALID_TAG_HUSBAND)
                .build();
        assertTrue(payBack.hasPerson(editedAlice));
    }

    @Test
    public void getPersonList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> payBack.getPersonList().remove(0));
    }

    @Test
    public void toStringMethod() {
        String expected = PayBack.class.getCanonicalName() + "{persons=" + payBack.getPersonList() + "}";
        assertEquals(expected, payBack.toString());
    }

    /**
     * A stub ReadOnlyPayBack whose persons list can violate interface constraints.
     */
    private static class PayBackStub implements ReadOnlyPayBack {
        private final ObservableList<Person> persons = FXCollections.observableArrayList();

        PayBackStub(Collection<Person> persons) {
            this.persons.setAll(persons);
        }

        @Override
        public ObservableList<Person> getPersonList() {
            return persons;
        }
    }

}
