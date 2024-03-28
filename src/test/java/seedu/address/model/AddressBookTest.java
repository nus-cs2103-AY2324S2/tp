package seedu.address.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
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
import seedu.address.model.exam.Exam;
import seedu.address.model.exam.exceptions.DuplicateExamException;
import seedu.address.model.person.Person;
import seedu.address.model.person.Score;
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
        Person editedAlice = new PersonBuilder(ALICE).withAddress(VALID_ADDRESS_BOB).withTags(VALID_TAG_HUSBAND)
                .build();
        List<Person> newPersons = Arrays.asList(ALICE, editedAlice);
        AddressBookStub newData = new AddressBookStub(newPersons, Collections.emptyList());

        assertThrows(DuplicatePersonException.class, () -> addressBook.resetData(newData));
    }

    @Test
    public void resetData_withDuplicateExams_throwsDuplicateExamException() {
        // Two exams with the same identity fields
        Exam midterm = new Exam("Midterm", new Score(100));
        Exam editedMidterm = new Exam("Midterm", new Score(50));
        List<Exam> newExams = Arrays.asList(midterm, editedMidterm);
        AddressBookStub newData = new AddressBookStub(Collections.emptyList(), newExams);

        assertThrows(DuplicateExamException.class, () -> addressBook.resetData(newData));
    }

    @Test
    public void hasPerson_nullPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> addressBook.hasPerson(null));
    }

    @Test
    public void hasExam_nullExam_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> addressBook.hasExam(null));
    }

    @Test
    public void hasPerson_personNotInAddressBook_returnsFalse() {
        assertFalse(addressBook.hasPerson(ALICE));
    }

    @Test
    public void hasExam_examNotInAddressBook_returnsFalse() {
        assertFalse(addressBook.hasExam(new Exam("Midterm", new Score(100))));
    }

    @Test
    public void hasPerson_personInAddressBook_returnsTrue() {
        addressBook.addPerson(ALICE);
        assertTrue(addressBook.hasPerson(ALICE));
    }

    @Test
    public void hasExam_examInAddressBook_returnsTrue() {
        Exam midterm = new Exam("Midterm", new Score(100));
        addressBook.addExam(midterm);
        assertTrue(addressBook.hasExam(midterm));
    }

    @Test
    public void hasPerson_personWithSameIdentityFieldsInAddressBook_returnsTrue() {
        addressBook.addPerson(ALICE);
        Person editedAlice = new PersonBuilder(ALICE).withAddress(VALID_ADDRESS_BOB).withTags(VALID_TAG_HUSBAND)
                .build();
        assertTrue(addressBook.hasPerson(editedAlice));
    }

    @Test
    public void hasExam_examWithSameIdentityFieldsInAddressBook_returnsTrue() {
        Exam midterm = new Exam("Midterm", new Score(100));
        addressBook.addExam(midterm);
        Exam editedMidterm = new Exam("Midterm", new Score(50));
        assertTrue(addressBook.hasExam(editedMidterm));
    }

    @Test
    public void getPersonList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> addressBook.getPersonList().remove(0));
    }

    @Test
    public void getExamList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> addressBook.getExamList().remove(0));
    }

    @Test
    public void toStringMethod() {
        String expected = AddressBook.class.getCanonicalName() + "{persons=" + addressBook.getPersonList()
                                                               + ", exams=" + addressBook.getExamList() + "}";
        assertEquals(expected, addressBook.toString());
    }

    @Test
    public void equalsMethod() {
        AddressBook addressBook = new AddressBook();
        AddressBook addressBook2 = new AddressBook();
        List<Person> persons = Arrays.asList(ALICE);
        List<Exam> exams = Arrays.asList(new Exam("Midterm", new Score(100)));
        addressBook.resetData(new AddressBookStub(persons, exams));
        addressBook2.resetData(new AddressBookStub(persons, exams));

        // same object -> returns true
        assertTrue(addressBook.equals(addressBook));

        // same values -> returns true
        assertTrue(addressBook.equals(addressBook2));

        // different types -> returns false
        assertFalse(addressBook.equals(5));

        // null -> returns false
        assertFalse(addressBook.equals(null));

        // different person -> returns false
        List<Person> persons2 = Arrays.asList(new PersonBuilder(ALICE).withAddress(VALID_ADDRESS_BOB).build());
        addressBook2.resetData(new AddressBookStub(persons2, exams));
        assertFalse(addressBook.equals(addressBook2));

        // different exam -> returns false
        List<Exam> exams2 = Arrays.asList(new Exam("Midterm", new Score(50)));
        addressBook2.resetData(new AddressBookStub(persons, exams2));
        assertFalse(addressBook.equals(addressBook2));
    }

    @Test
    public void hashCodeMethod() {
        AddressBook addressBook = new AddressBook();
        AddressBook addressBook2 = new AddressBook();
        List<Person> persons = Arrays.asList(ALICE);
        List<Exam> exams = Arrays.asList(new Exam("Midterm", new Score(100)));
        addressBook.resetData(new AddressBookStub(persons, exams));
        addressBook2.resetData(new AddressBookStub(persons, exams));

        // same object -> returns true
        assertEquals(addressBook.hashCode(), addressBook.hashCode());

        // same values -> returns true
        assertEquals(addressBook.hashCode(), addressBook2.hashCode());

        // different values -> returns false
        List<Person> persons2 = Arrays.asList(new PersonBuilder(ALICE).withAddress(VALID_ADDRESS_BOB).build());
        addressBook2.resetData(new AddressBookStub(persons2, exams));
        assertFalse(addressBook.hashCode() == addressBook2.hashCode());
    }

    /**
     * A stub ReadOnlyAddressBook whose persons list can violate interface constraints.
     */
    private static class AddressBookStub implements ReadOnlyAddressBook {
        private final ObservableList<Person> persons = FXCollections.observableArrayList();
        private final ObservableList<Exam> exams = FXCollections.observableArrayList();

        AddressBookStub(Collection<Person> persons, Collection<Exam> exams) {
            this.persons.setAll(persons);
            this.exams.setAll(exams);
        }

        @Override
        public ObservableList<Person> getPersonList() {
            return persons;
        }

        @Override
        public ObservableList<Exam> getExamList() {
            return exams;
        }
    }

}
