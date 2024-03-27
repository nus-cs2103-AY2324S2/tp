package scrolls.elder.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static scrolls.elder.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static scrolls.elder.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import scrolls.elder.model.person.Person;
import scrolls.elder.model.person.exceptions.DuplicatePersonException;
import scrolls.elder.testutil.Assert;
import scrolls.elder.testutil.PersonBuilder;
import scrolls.elder.testutil.TypicalPersons;

public class PersonStoreTest {

    private final PersonStore personStore = new PersonStore();

    @Test
    public void constructor() {
        assertEquals(Collections.emptyList(), personStore.getPersonList());
    }

    @Test
    public void resetData_null_throwsNullPointerException() {
        Assert.assertThrows(NullPointerException.class, () -> personStore.resetData(null));
    }

    @Test
    public void resetData_withValidReadOnlyAddressBook_replacesData() {
        PersonStore newData = TypicalPersons.getTypicalPersonStore();
        personStore.resetData(newData);
        assertEquals(newData, personStore);
    }

    @Test
    public void resetData_withDuplicatePersons_throwsDuplicatePersonException() {
        // Two persons with the same identity fields
        Person editedAlice =
            new PersonBuilder(TypicalPersons.ALICE).withAddress(VALID_ADDRESS_BOB).withTags(VALID_TAG_HUSBAND)
                .build();
        List<Person> newPersons = Arrays.asList(TypicalPersons.ALICE, editedAlice);
        PersonStoreStub newData = new PersonStoreStub(newPersons);

        Assert.assertThrows(DuplicatePersonException.class, () -> personStore.resetData(newData));
    }

    @Test
    public void hasPerson_nullPerson_throwsNullPointerException() {
        Assert.assertThrows(NullPointerException.class, () -> personStore.hasPerson(null));
    }

    @Test
    public void hasPerson_personNotInAddressBook_returnsFalse() {
        assertFalse(personStore.hasPerson(TypicalPersons.ALICE));
    }

    @Test
    public void hasPerson_personInAddressBook_returnsTrue() {
        personStore.addPerson(TypicalPersons.ALICE);
        assertTrue(personStore.hasPerson(TypicalPersons.ALICE));
    }

    @Test
    public void hasPerson_personWithSameIdentityFieldsInAddressBook_returnsTrue() {
        personStore.addPerson(TypicalPersons.ALICE);
        Person editedAlice =
            new PersonBuilder(TypicalPersons.ALICE).withAddress(VALID_ADDRESS_BOB).withTags(VALID_TAG_HUSBAND)
                .build();
        assertTrue(personStore.hasPerson(editedAlice));
    }

    @Test
    public void getPersonList_modifyList_throwsUnsupportedOperationException() {
        Assert.assertThrows(UnsupportedOperationException.class, () -> personStore.getPersonList().remove(0));
    }

    @Test
    public void toStringMethod() {
        String expected = PersonStore.class.getCanonicalName() + "{persons=" + personStore.getPersonList() + "}";
        assertEquals(expected, personStore.toString());
    }

    /**
     * A stub ReadOnlyAddressBook whose persons list can violate interface constraints.
     */
    private static class PersonStoreStub implements ReadOnlyPersonStore {
        private final ObservableList<Person> persons = FXCollections.observableArrayList();

        PersonStoreStub(Collection<Person> persons) {
            this.persons.setAll(persons);
        }

        @Override
        public ObservableList<Person> getPersonList() {
            return persons;
        }

        @Override
        public ObservableList<Person> getFilteredPersonList() {
            return null;
        }

        @Override
        public ObservableList<Person> getFilteredVolunteerList() {
            return null;
        }

        @Override
        public ObservableList<Person> getFilteredBefriendeeList() {
            return null;
        }

        @Override
        public void updateFilteredPersonList(Predicate<Person> predicate) {

        }
    }

}
