package seedu.address.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_GENDER_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ILLNESS_GENETIC;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalPersons.ALICE;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.person.Person;
import seedu.address.model.person.exceptions.DuplicatePersonException;
import seedu.address.model.person.note.Description;
import seedu.address.model.person.note.Note;
import seedu.address.testutil.PersonBuilder;

public class AddressBookTest {

    private final AddressBook addressBook = new AddressBook();

    @Test
    public void constructor() {
        assertEquals(Collections.emptyList(), addressBook.getPersonList());
    }

    @Test
    public void addressbook_copyConstuctor_copiesNotes() {
        AddressBook original = getTypicalAddressBook();
        AddressBook copied = new AddressBook(original);
        assertEquals(original.getNoteList(), copied.getNoteList());
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
        Person editedAlice = new PersonBuilder(ALICE)
                .withGender(VALID_GENDER_BOB)
                .withIllnesses(VALID_ILLNESS_GENETIC)
                .build();
        List<Person> newPersons = Arrays.asList(ALICE, editedAlice);
        AddressBookStub newData = new AddressBookStub(newPersons);

        assertThrows(DuplicatePersonException.class, () -> addressBook.resetData(newData));
    }

    @Test
    public void resetData_withValidReadOnlyAddressBook_updatesNotes() {
        AddressBook newData = getTypicalAddressBook();
        addressBook.resetData(newData);
        assertEquals(newData.getNoteList(), addressBook.getNoteList());
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
        Person editedAlice = new PersonBuilder(ALICE).withGender(VALID_GENDER_BOB)
                .withIllnesses(VALID_ILLNESS_GENETIC)
                .build();
        assertTrue(addressBook.hasPerson(editedAlice));
    }

    @Test
    public void getPersonList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> addressBook.getPersonList().remove(0));
    }

    @Test
    public void deletePerson_deleteAssociatedNotes() {
        AddressBook newData = getTypicalAddressBook();
        ObservableList<Note> notesToDelete = ALICE.getNotes();
        newData.removePerson(ALICE);
        assertFalse(newData.getNoteList().contains(notesToDelete));
    }

    @Test
    public void addNote_singleNote_addedSuccessfully() {
        AddressBook ab = new AddressBook();
        ab.addNote(new Note(LocalDateTime.now(), new Description("Test Note")));
        assertEquals(1, ab.getNoteList().size());
    }

    @Test
    public void addNote_multipleNotes_addedSuccessfully() {
        AddressBook ab = new AddressBook();
        Note firstNote = new Note(LocalDateTime.now(), new Description("First Test Note"));
        Note secondNote = new Note(LocalDateTime.now().plusDays(1), new Description("Second Test Note"));
        ab.addNote(firstNote);
        ab.addNote(secondNote);
        assertEquals(2, ab.getNoteList().size());
        assertEquals(firstNote, ab.getNoteList().get(0));
        assertEquals(secondNote, ab.getNoteList().get(1));
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
        private final ObservableList<Note> notes = FXCollections.observableArrayList();

        AddressBookStub(Collection<Person> persons) {
            this.persons.setAll(persons);
        }

        @Override
        public ObservableList<Person> getPersonList() {
            return persons;
        }

        @Override
        public ObservableList<Note> getNoteList() {
            return notes;
        }
    }

}
