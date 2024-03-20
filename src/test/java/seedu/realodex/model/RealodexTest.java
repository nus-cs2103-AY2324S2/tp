package seedu.realodex.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.realodex.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.realodex.logic.commands.CommandTestUtil.VALID_TAG_AMY;
import static seedu.realodex.testutil.Assert.assertThrows;
import static seedu.realodex.testutil.TypicalPersons.ALICE;
import static seedu.realodex.testutil.TypicalPersons.getTypicalRealodex;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.realodex.model.person.Person;
import seedu.realodex.model.person.exceptions.DuplicatePersonException;
import seedu.realodex.testutil.PersonBuilder;

public class RealodexTest {

    private final Realodex realodex = new Realodex();

    @Test
    public void constructor() {
        assertEquals(Collections.emptyList(), realodex.getPersonList());
    }

    @Test
    public void resetData_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> realodex.resetData(null));
    }

    @Test
    public void resetData_withValidReadOnlyRealodex_replacesData() {
        Realodex newData = getTypicalRealodex();
        realodex.resetData(newData);
        assertEquals(newData, realodex);
    }

    @Test
    public void resetData_withDuplicatePersons_throwsDuplicatePersonException() {
        // Two persons with the same identity fields
        Person editedAlice = new PersonBuilder(ALICE).withAddress(VALID_ADDRESS_BOB).withTags(VALID_TAG_AMY)
                .build();
        List<Person> newPersons = Arrays.asList(ALICE, editedAlice);
        RealodexStub newData = new RealodexStub(newPersons);

        assertThrows(DuplicatePersonException.class, () -> realodex.resetData(newData));
    }

    @Test
    public void hasPerson_nullPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> realodex.hasPerson(null));
    }

    @Test
    public void hasPerson_personNotInRealodex_returnsFalse() {
        assertFalse(realodex.hasPerson(ALICE));
    }

    @Test
    public void hasPerson_personInRealodex_returnsTrue() {
        realodex.addPerson(ALICE);
        assertTrue(realodex.hasPerson(ALICE));
    }

    @Test
    public void hasPerson_personWithSameIdentityFieldsInRealodex_returnsTrue() {
        realodex.addPerson(ALICE);
        Person editedAlice = new PersonBuilder(ALICE).withAddress(VALID_ADDRESS_BOB).withTags(VALID_TAG_AMY)
                .build();
        assertTrue(realodex.hasPerson(editedAlice));
    }

    @Test
    public void getPersonList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> realodex.getPersonList().remove(0));
    }

    @Test
    public void toStringMethod() {
        String expected = Realodex.class.getCanonicalName() + "{persons=" + realodex.getPersonList() + "}";
        assertEquals(expected, realodex.toString());
    }

    /**
     * A stub ReadOnlyRealodex whose persons list can violate interface constraints.
     */
    private static class RealodexStub implements ReadOnlyRealodex {
        private final ObservableList<Person> persons = FXCollections.observableArrayList();

        RealodexStub(Collection<Person> persons) {
            this.persons.setAll(persons);
        }

        @Override
        public ObservableList<Person> getPersonList() {
            return persons;
        }
    }

}
