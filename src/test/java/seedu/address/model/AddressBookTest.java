package seedu.address.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_GROUP_LAB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_GROUP_TUTORIAL;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MAJOR_BOB;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalPersons.ALICE;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.group.Group;
import seedu.address.model.group.exceptions.DuplicateGroupException;
import seedu.address.model.person.Person;
import seedu.address.model.person.exceptions.DuplicatePersonException;
import seedu.address.testutil.PersonBuilder;

public class AddressBookTest {

    private final AddressBook addressBook = new AddressBook();

    @Test
    public void constructor() {
        assertEquals(Collections.emptyList(), addressBook.getPersonList());
    }

    @Test
    public void resetData_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> addressBook.resetData(null));
    }

    @Test
    public void resetData_withValidReadOnlyAddressBook_replacesData() {
        AddressBook newData = getTypicalAddressBook();
        addressBook.resetData(newData);
        assertEquals(newData, addressBook);
    }

    @Test
    public void resetData_withDuplicatePersons_throwsDuplicatePersonException() {
        // Two persons with the same identity fields
        Person editedAlice = new PersonBuilder(ALICE).withMajor(VALID_MAJOR_BOB).withGroups(VALID_GROUP_LAB)
                .build();
        List<Person> newPersons = Arrays.asList(ALICE, editedAlice);
        List<Group> newGroups = Arrays.asList(new Group(VALID_GROUP_TUTORIAL), new Group(VALID_GROUP_LAB));
        AddressBookStub newData = new AddressBookStub(newPersons, newGroups);

        assertThrows(DuplicatePersonException.class, () -> addressBook.resetData(newData));
    }

    @Test
    public void hasPerson_nullPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> addressBook.hasPerson(null));
    }

    @Test
    public void hasPerson_personNotInAddressBook_returnsFalse() {
        assertFalse(addressBook.hasPerson(ALICE));
    }

    @Test
    public void hasPerson_personInAddressBook_returnsTrue() {
        addressBook.addPerson(ALICE);
        assertTrue(addressBook.hasPerson(ALICE));
    }

    @Test
    public void hasPerson_personWithSameIdentityFieldsInAddressBook_returnsTrue() {
        addressBook.addPerson(ALICE);
        Person editedAlice = new PersonBuilder(ALICE).withMajor(VALID_MAJOR_BOB).withGroups(VALID_GROUP_LAB)
                .build();
        assertTrue(addressBook.hasPerson(editedAlice));
    }

    @Test
    public void getPersonList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> addressBook.getPersonList().remove(0));
    }

    @Test
    public void resetData_withDuplicateGroups_throwsDuplicatePersonException() {
        // Two persons with the same identity fields
        List<Person> newPersons = Arrays.asList(ALICE);
        List<Group> newGroups = Arrays.asList(new Group(VALID_GROUP_TUTORIAL), new Group(VALID_GROUP_TUTORIAL));
        AddressBookStub newData = new AddressBookStub(newPersons, newGroups);

        assertThrows(DuplicateGroupException.class, () -> addressBook.resetData(newData));
    }

    @Test
    public void hasGroup_nullGroup_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> addressBook.hasGroup(null));
    }

    @Test
    public void hasGroup_groupNotInAddressBook_returnsFalse() {
        assertFalse(addressBook.hasGroup(new Group(VALID_GROUP_LAB)));
    }

    @Test
    public void hasGroup_groupInAddressBook_returnsTrue() {
        addressBook.addGroup(new Group(VALID_GROUP_LAB));
        assertTrue(addressBook.hasGroup(new Group(VALID_GROUP_LAB)));
    }

    @Test
    public void hasGroup_groupWithSameIdentityFieldsInAddressBook_returnsTrue() {
        addressBook.addGroup(new Group(VALID_GROUP_LAB));
        assertTrue(addressBook.hasGroup(new Group(VALID_GROUP_LAB)));
    }

    @Test
    public void getGroupList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> addressBook.getGroupList().remove(0));
    }

    @Test
    public void toStringMethod() {
        String expected = AddressBook.class.getCanonicalName() + "{persons=" + addressBook.getPersonList()
                + ", groups=" + addressBook.getGroupList() + "}";
        assertEquals(expected, addressBook.toString());
    }

    /**
     * A stub ReadOnlyAddressBook whose persons list can violate interface constraints.
     */
    private static class AddressBookStub implements ReadOnlyAddressBook {
        private final ObservableList<Person> persons = FXCollections.observableArrayList();
        private final ObservableList<Group> groups = FXCollections.observableArrayList();

        AddressBookStub(Collection<Person> persons, Collection<Group> groups) {
            this.persons.setAll(persons);
            this.groups.setAll(groups);
        }

        @Override
        public ObservableList<Person> getPersonList() {
            return persons;
        }

        @Override
        public ObservableList<Group> getGroupList() {
            return groups;
        }

    }

}
