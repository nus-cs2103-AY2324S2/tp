package seedu.address.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalPersons.CAT;
import static seedu.address.testutil.TypicalPersonsUuid.ALICE;
import static seedu.address.testutil.TypicalPersonsUuid.HOON;
import static seedu.address.testutil.TypicalPersonsUuid.getTypicalAddressBook;

import java.util.Collection;
import java.util.Collections;
import java.util.UUID;

import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.person.Person;
import seedu.address.model.person.attribute.Attribute;
import seedu.address.model.person.attribute.NameAttribute;
import seedu.address.model.person.relationship.Relationship;

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
    public void getPersonList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> addressBook.getPersonList().remove(0));
    }

    @Test
    public void toStringMethod() {
        String expected = AddressBook.class.getCanonicalName() + "{persons=" + addressBook.getPersonList() + ","
                + " relationships=" + "}";
        assertEquals(expected, addressBook.toString());
    }

    @Test
    void deleteAttribute_existingAttribute_removesAttribute() {
        assertTrue(CAT.hasAttribute("Name"), "Attribute should exist before deletion.");

        CAT.deleteAttribute("Name");

        assertFalse(CAT.hasAttribute("Name"), "Attribute should not exist after deletion.");

        CAT.setAttribute("Name", "Cat");
    }

    @Test
    void getPersonByUuid_existingUuid_returnsPerson() {
        Person result = HOON;
        String uuidString = result.getUuid().toString();

        assertNotNull(result, "Should return a person for existing UUID.");
        assertEquals(uuidString, result.getUuid().toString(), "Returned person should have the same UUID.");
    }

    @Test
    void hasAttribute_existingAttribute_returnsTrue() {
        boolean result = HOON.hasAttribute("Name");

        assertTrue(result, "Should return true for an existing attribute.");
    }

    @Test
    void hasAttribute_nonExistentAttribute_returnsFalse() {
        boolean result = HOON.hasAttribute("Pet");

        assertFalse(result, "Should return false for a non-existent attribute.");
    }

    @Test
    void deleteAttribute_test() {
        addressBook.addPerson(HOON);
        addressBook.deleteAttribute(HOON.getUuidString(), "Name");
        assertFalse(HOON.hasAttribute("Name"));
        HOON.setAttribute("Name", "Hoon Meier");
    }

    /**
     * A stub ReadOnlyAddressBook whose persons list can violate interface constraints.
     */
    private static class AddressBookStub implements ReadOnlyAddressBook {
        private final ObservableList<Person> persons = FXCollections.observableArrayList();
        private final ObservableList<Relationship> relationships = FXCollections.observableArrayList();

        AddressBookStub(Collection<Person> persons) {
            this.persons.setAll(persons);
        }

        @Override
        public ObservableList<Person> getPersonList() {
            return persons;
        }

        @Override
        public ObservableList<Relationship> getRelationshipList() {
            return relationships;
        }
    }

    @Test
    public void hasRelationshipWithDescriptor_existingRelationship_returnsTrue() {
        Attribute name1 = new NameAttribute("Name", "John Doe");
        Attribute name2 = new NameAttribute("Name", "Jane Doe");
        Attribute[] attributes1 = new Attribute[]{name1};
        Attribute[] attributes2 = new Attribute[]{name2};

        // Adding dummy people for testing
        Person person1 = new Person(attributes1);
        Person person2 = new Person(attributes2);
        UUID uuid11 = person1.getUuid();
        UUID uuid22 = person2.getUuid();
        // Create an instance of AddressBook
        AddressBook addressBook = new AddressBook();

        // Create a test relationship
        Relationship testRelationship = new Relationship(uuid11, uuid22, "family");

        // Add the test relationship to the address book
        addressBook.addRelationship(testRelationship);

        // Check if the address book has the test relationship with the descriptor
        assertTrue(addressBook.hasRelationshipWithDescriptor(testRelationship));
    }

    @Test
    public void getExistingRelationship_existingRelationship_returnsStringRepresentation() {
        Attribute name1 = new NameAttribute("Name", "John Doe");
        Attribute name2 = new NameAttribute("Name", "Jane Doe");
        Attribute[] attributes1 = new Attribute[]{name1};
        Attribute[] attributes2 = new Attribute[]{name2};

        // Adding dummy people for testing
        Person person1 = new Person(attributes1);
        Person person2 = new Person(attributes2);
        UUID uuid11 = person1.getUuid();
        UUID uuid22 = person2.getUuid();
        // Create an instance of AddressBook
        AddressBook addressBook = new AddressBook();

        // Create a test relationship
        Relationship testRelationship = new Relationship(uuid11, uuid22, "family");

        // Add the test relationship to the address book
        addressBook.addRelationship(testRelationship);

        // Check if the address book has the test relationship with the descriptor
        assertEquals(testRelationship.toString(), addressBook.getExistingRelationship(testRelationship));
    }

    @Test
    public void hasRelationshipWithDescriptor_nonExistingRelationship_returnsFalse() {
        Attribute name1 = new NameAttribute("Name", "John Doe");
        Attribute name2 = new NameAttribute("Name", "Jane Doe");
        Attribute[] attributes1 = new Attribute[]{name1};
        Attribute[] attributes2 = new Attribute[]{name2};

        // Adding dummy people for testing
        Person person1 = new Person(attributes1);
        Person person2 = new Person(attributes2);
        UUID uuid11 = person1.getUuid();
        UUID uuid22 = person2.getUuid();
        // Create an instance of AddressBook
        AddressBook addressBook = new AddressBook();

        // Create a test relationship
        Relationship testRelationship = new Relationship(uuid11, uuid22, "family");

        // Check if the address book has the test relationship with the descriptor
        assertFalse(addressBook.hasRelationshipWithDescriptor(testRelationship));
    }

    @Test
    public void getExistingRelationship_nonExistingRelationship_throwsException() {
        // Create an instance of AddressBook
        Attribute name1 = new NameAttribute("Name", "John Doe");
        Attribute name2 = new NameAttribute("Name", "Jane Doe");
        Attribute[] attributes1 = new Attribute[]{name1};
        Attribute[] attributes2 = new Attribute[]{name2};

        // Adding dummy people for testing
        Person person1 = new Person(attributes1);
        Person person2 = new Person(attributes2);
        UUID uuid11 = person1.getUuid();
        UUID uuid22 = person2.getUuid();
        // Create an instance of AddressBook
        AddressBook addressBook = new AddressBook();

        // Create a test relationship
        Relationship testRelationship = new Relationship(uuid11, uuid22, "family");

        // Check if invoking getExistingRelationship with the non-existing relationship throws an exception
        assertThrows(IllegalArgumentException.class, () -> addressBook.getExistingRelationship(testRelationship));
    }

    @Test
    public void hashCode_sameObject_equals() {
        AddressBook addressBook1 = new AddressBook();
        addressBook1.addPerson(ALICE);
        addressBook1.addPerson(HOON);

        AddressBook addressBook2 = new AddressBook();
        addressBook2.addPerson(ALICE);
        addressBook2.addPerson(HOON);

        assertEquals(addressBook1.hashCode(), addressBook2.hashCode());
    }

    @Test
    public void hashCode_differentObject_notEquals() {
        AddressBook addressBook1 = new AddressBook();
        addressBook1.addPerson(ALICE);
        addressBook1.addPerson(HOON);

        AddressBook addressBook2 = new AddressBook();
        addressBook2.addPerson(CAT);

        assertNotEquals(addressBook1.hashCode(), addressBook2.hashCode());
    }

    @Test
    public void equals_sameObject_returnsTrue() {
        AddressBook addressBook = new AddressBook();
        assertTrue(addressBook.equals(addressBook));
    }

    @Test
    public void equals_differentObject_returnsFalse() {
        AddressBook addressBook = new AddressBook();
        assertFalse(addressBook.equals(new Object()));
    }

    @Test
    public void equals_equalAddressBooks_returnsTrue() {
        AddressBook addressBook1 = new AddressBook();
        AddressBook addressBook2 = new AddressBook();
        assertTrue(addressBook1.equals(addressBook2));
    }
}
