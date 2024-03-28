package seedu.address.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_STUDENT_ID_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalPersons.ALICE;
import static seedu.address.testutil.TypicalPersons.AMY;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.module.ModuleCode;
import seedu.address.model.module.TutorialClass;
import seedu.address.model.person.Person;
import seedu.address.model.person.exceptions.DuplicatePersonException;
import seedu.address.testutil.PersonBuilder;

/**
 * Contains unit tests for the AddressBook class.
 */
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
        Person editedAlice = new PersonBuilder(ALICE).withEmail(VALID_EMAIL_AMY).withTags(VALID_TAG_HUSBAND)
                .build();
        List<Person> newPersons = Arrays.asList(ALICE, editedAlice);
        AddressBookStub newData = new AddressBookStub(newPersons);

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
        Person editedAlice = new PersonBuilder(ALICE).withEmail(VALID_EMAIL_AMY)
                .build();
        assertTrue(addressBook.hasPerson(editedAlice));
    }

    @Test
    public void hasPersonWithEmail_nullEmail_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> addressBook.hasPersonWithEmail(null));
    }
    @Test
    public void hasPersonWithEmail_personInAddressBook_returnsTrue() {
        addressBook.addPerson(AMY);
        assertTrue(addressBook.hasPersonWithEmail(AMY.getEmail()));
    }

    @Test
    public void hasPersonWithEmail_personNotInAddressBook_returnsFalse() {
        assertFalse(addressBook.hasPersonWithEmail(AMY.getEmail()));
    }

    @Test
    public void hasPersonWithEmail_differentPersonWithSameEmail_returnsTrue() {
        addressBook.addPerson(AMY);
        Person alice = new PersonBuilder(ALICE).withEmail(VALID_EMAIL_AMY)
                .build();
        assertTrue(addressBook.hasPersonWithEmail(alice.getEmail()));
    }

    @Test
    public void hasPersonWithStudentId_nullStudentId_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> addressBook.hasPersonWithStudentId(null));
    }
    @Test
    public void hasPersonWithStudentId_personInAddressBook_returnsTrue() {
        addressBook.addPerson(AMY);
        assertTrue(addressBook.hasPersonWithStudentId(AMY.getStudentId()));
    }

    @Test
    public void hasPersonWithStudentId_personNotInAddressBook_returnsFalse() {
        assertFalse(addressBook.hasPersonWithStudentId(AMY.getStudentId()));
    }

    @Test
    public void hasPersonWithStudentId_differentPersonWithSameStudentId_returnsTrue() {
        addressBook.addPerson(AMY);
        Person alice = new PersonBuilder(ALICE).withStudentId(VALID_STUDENT_ID_AMY)
                .build();
        assertTrue(addressBook.hasPersonWithStudentId(alice.getStudentId()));
    }

    @Test
    public void getPersonList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> addressBook.getPersonList().remove(0));
    }

    @Test
    public void getSortedPersonList_modifyList_throwsUnsupportedOperationException() {

    }

    @Test
    public void toStringMethod() {
        String expected = AddressBook.class.getCanonicalName() + "{persons=" + addressBook.getPersonList() + "}";
        assertEquals(expected, addressBook.toString());
    }

    /**
     * A stub ReadOnlyAddressBook whose persons list can violate interface constraints.
     */
    private static class AddressBookStub implements ReadOnlyAddressBook {
        private final ObservableList<Person> persons = FXCollections.observableArrayList();
        private ObservableList<Person> sortedPersons;

        AddressBookStub(Collection<Person> persons) {
            this.persons.setAll(persons);
        }

        @Override
        public ObservableList<Person> getPersonList() {
            return persons;
        }

        @Override
        public ObservableList<ModuleCode> getModuleList() {
            return null;
        }

        @Override
        public void setSortedPersonList(Comparator<Person> comparator) {
            sortedPersons = FXCollections.observableArrayList();
        }

        @Override
        public ObservableList<Person> getSortedPersonList() {
            return sortedPersons;
        }

        @Override
        public boolean hasModule(ModuleCode moduleCode) {
            return false;
        }

        @Override
        public void addModule(ModuleCode moduleCode, String description) {
        }

        @Override
        public ObservableList<TutorialClass> getTutorialList() {
            return null;
        }
    }
}
