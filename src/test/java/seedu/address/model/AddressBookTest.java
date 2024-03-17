package seedu.address.model;

import static org.junit.jupiter.api.Assertions.*;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalPersons.ALICE;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;
import static seedu.address.testutil.TypicalTags.FRIEND;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableSet;
import seedu.address.model.person.Person;
import seedu.address.model.person.exceptions.DuplicatePersonException;
import seedu.address.model.person.exceptions.PersonNotFoundException;
import seedu.address.model.tag.Tag;
import seedu.address.model.tag.exceptions.DuplicateTagException;
import seedu.address.model.tag.exceptions.TagNotFoundException;
import seedu.address.testutil.PersonBuilder;

public class AddressBookTest {

    private final AddressBook addressBook = new AddressBook();

    @Test
    public void constructor() {
        assertEquals(Collections.emptyList(), addressBook.getPersonList());
        assertEquals(Collections.emptySet(), addressBook.getTagList());
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
        List<Tag> newTags = List.of();
        AddressBookStub newData = new AddressBookStub(newPersons, newTags);

        assertThrows(DuplicatePersonException.class, () -> addressBook.resetData(newData));
    }

    @Test
    public void hasPerson_nullPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> addressBook.hasPerson(null));
    }

    @Test
    public void hasTag_nullTag_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> addressBook.hasTag(null));
    }

    @Test
    public void hasPerson_personNotInAddressBook_returnsFalse() {
        assertFalse(addressBook.hasPerson(ALICE));
    }

    @Test
    public void hasTag_tagNotInAddressBook_returnsFalse() {
        assertFalse(addressBook.hasTag(FRIEND));
    }

    @Test
    public void hasPerson_personInAddressBook_returnsTrue() {
        addressBook.addPerson(ALICE);
        assertTrue(addressBook.hasPerson(ALICE));
    }

    @Test
    public void hasTag_tagInAddressBook_returnsTrue() {
        addressBook.addTag(FRIEND);
        assertTrue(addressBook.hasTag(FRIEND));
    }

    @Test
    public void hasPerson_personWithSameIdentityFieldsInAddressBook_returnsTrue() {
        addressBook.addPerson(ALICE);
        Person editedAlice = new PersonBuilder(ALICE).withAddress(VALID_ADDRESS_BOB).withTags(VALID_TAG_HUSBAND)
                .build();
        assertTrue(addressBook.hasPerson(editedAlice));
    }

    @Test
    public void hasTag_tagWithSameNameInAddressBook_returnsTrue() {
        addressBook.addTag(FRIEND);
        Tag editedFriend = new Tag("friends");
        assertTrue(addressBook.hasTag(editedFriend));
    }

    @Test
    public void addDuplicatePerson_throwsDuplicatePersonException() {
        addressBook.addPerson(ALICE);
        assertThrows(DuplicatePersonException.class, () -> addressBook.addPerson(ALICE));
    }

    @Test
    public void addDuplicateTag_throwsDuplicateTagException() {
        addressBook.addTag(FRIEND);
        assertThrows(DuplicateTagException.class, () -> addressBook.addTag(FRIEND));
    }

    @Test
    public void setPerson_withSameIdentityFields_throwsDuplicatePersonException() {
        addressBook.addPerson(ALICE);
        Person editedAlice = new PersonBuilder(ALICE).withAddress(VALID_ADDRESS_BOB).withTags(VALID_TAG_HUSBAND).build();
        assertThrows(DuplicatePersonException.class, () -> addressBook.setPerson(ALICE, editedAlice));
    }

    @Test
    public void setTags_withDuplicateTags_throwsDuplicateTagException() {
        addressBook.addTag(FRIEND);
        Set<Tag> duplicateTags = new HashSet<>(Arrays.asList(FRIEND, new Tag("friend")));
        assertThrows(DuplicateTagException.class, () -> addressBook.setTagList(duplicateTags));
    }

    @Test
    public void setPerson_withDifferentIdentityFields_success() {
        addressBook.addPerson(ALICE);
        Person editedAlice = new PersonBuilder(ALICE).withAddress(VALID_ADDRESS_BOB).withTags(VALID_TAG_HUSBAND).build();
        assertDoesNotThrow(() -> addressBook.setPerson(ALICE, editedAlice));
    }

    @Test
    public void setTags_withUniqueTags_success() {
        Set<Tag> uniqueTags = new HashSet<>(Arrays.asList(FRIEND, new Tag("colleague")));
        assertDoesNotThrow(() -> addressBook.setTagList(uniqueTags));
    }

    @Test
    public void removePerson_notExistingPerson_throwsPersonNotFoundException() {
        assertThrows(PersonNotFoundException.class, () -> addressBook.removePerson(ALICE));
    }

    @Test
    public void removeTag_notExistingTag_throwsTagNotFoundException() {
        assertThrows(TagNotFoundException.class, () -> addressBook.removeTag(FRIEND));
    }

    @Test
    public void removePerson_existingPerson_success() {
        addressBook.addPerson(ALICE);
        assertDoesNotThrow(() -> addressBook.removePerson(ALICE));
    }

    @Test
    public void removeTag_existingTag_success() {
        addressBook.addTag(FRIEND);
        assertDoesNotThrow(() -> addressBook.removeTag(FRIEND));
    }

    @Test
    public void toStringMethod() {
        String expected = AddressBook.class.getCanonicalName() + "{persons=" + addressBook.getPersonList()
                + ", tags=" + addressBook.getTagList() + "}";
        assertEquals(expected, addressBook.toString());
    }

    @Test
    public void getPersonList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> addressBook.getPersonList().remove(0));
    }

    @Test
    public void getTagList_modifySet_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> addressBook.getTagList().remove(FRIEND));
    }

    /**
     * A stub ReadOnlyAddressBook whose persons list can violate interface constraints.
     */
    private static class AddressBookStub implements ReadOnlyAddressBook {
        private final ObservableList<Person> persons = FXCollections.observableArrayList();
        private final ObservableSet<Tag> tags = FXCollections.observableSet();

        AddressBookStub(Collection<Person> persons, Collection<Tag> tags) {
            this.persons.setAll(persons);
            this.tags.addAll(tags);
        }

        @Override
        public ObservableList<Person> getPersonList() {
            return persons;
        }

        @Override
        public ObservableSet<Tag> getTagList() {
            return tags;
        }
    }

}
