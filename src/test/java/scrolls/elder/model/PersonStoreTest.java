package scrolls.elder.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static scrolls.elder.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static scrolls.elder.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.function.Predicate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import scrolls.elder.model.person.Person;
import scrolls.elder.model.person.Volunteer;
import scrolls.elder.model.person.exceptions.DuplicatePersonException;
import scrolls.elder.testutil.Assert;
import scrolls.elder.testutil.PersonBuilder;
import scrolls.elder.testutil.TypicalPersons;

public class PersonStoreTest {

    private PersonStore personStore;

    @BeforeEach
    public void setUp() {
        personStore = new PersonStore();
    }

    @Test
    public void emptyConstructor() {
        assertEquals(Collections.emptyList(), personStore.getPersonList());
    }

    @Test
    public void nonEmptyConstructor_createsDeepCopy() {
        PersonStore originalPersonStore = TypicalPersons.getTypicalPersonStore();
        PersonStore copiedPersonStore = new PersonStore(originalPersonStore);

        // Check if deep copied
        assertNotSame(originalPersonStore, copiedPersonStore);
        // Check if the data is the same
        assertEquals(originalPersonStore, copiedPersonStore);

        // Check if the data is independent
        originalPersonStore.addPerson(TypicalPersons.BOB);
        assertFalse(copiedPersonStore.hasPerson(TypicalPersons.BOB));
    }

    @Test
    public void resetData_null_throwsNullPointerException() {
        Assert.assertThrows(NullPointerException.class, () -> personStore.resetData(null));
    }

    @Test
    public void resetData_withValidReadOnlyPersonStore_replacesData() {
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
    public void hasPerson_personNotInPersonStore_returnsFalse() {
        assertFalse(personStore.hasPerson(TypicalPersons.ALICE));
    }

    @Test
    public void hasPerson_personInPersonStore_returnsTrue() {
        personStore.addPerson(TypicalPersons.ALICE);
        assertTrue(personStore.hasPerson(TypicalPersons.ALICE));
    }

    @Test
    public void hasPerson_personWithSameIdentityFieldsInPersonStore_returnsTrue() {
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

    @Test
    public void equalsMethod() {
        personStore.addPerson(TypicalPersons.AMY);

        // same values -> returns true
        PersonStore personStoreCopy = new PersonStore(personStore);
        assertEquals(personStore, personStoreCopy);

        // same object -> returns true
        assertEquals(personStore, personStore);

        // null -> returns false
        assertNotEquals(personStore, null);

        // different types -> returns false
        assertNotEquals(personStore, new Object());

        // different person store -> returns false
        PersonStore differentPersonStore = new PersonStore();
        differentPersonStore.addPerson(new Volunteer(-1, TypicalPersons.BOB));
        assertNotEquals(personStore, differentPersonStore);
    }

    @Test
    public void hashCodeMethod() {
        PersonStore personStore1 = new PersonStore();
        personStore1.addPerson(TypicalPersons.ALICE);

        PersonStore personStore2 = new PersonStore();
        personStore2.addPerson(TypicalPersons.ALICE);

        int hashCode1 = personStore1.hashCode();
        int hashCode2 = personStore1.hashCode();
        int hashCode3 = personStore2.hashCode();

        // Consistency: hashCode on same object returns same result
        assertEquals(hashCode1, hashCode2);

        // Equality: equal objects return same hashCode
        assertEquals(hashCode1, hashCode3);
    }

    /**
     * A stub ReadOnlyPersonStore whose persons list can violate interface constraints.
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
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Person> getFilteredVolunteerList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Person> getFilteredBefriendeeList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredPersonList(Predicate<Person> predicate) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredVolunteerList(Predicate<Person> predicate) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredBefriendeeList(Predicate<Person> predicate) {
            throw new AssertionError("This method should not be called.");
        }

    }

}
