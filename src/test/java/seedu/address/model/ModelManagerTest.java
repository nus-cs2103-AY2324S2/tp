package seedu.address.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalPersons.ALICE;
import static seedu.address.testutil.TypicalPersons.BENSON;
import static seedu.address.testutil.TypicalPersons.DOG;
import static seedu.address.testutil.TypicalPersons.HOON;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.UUID;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.GuiSettings;
import seedu.address.model.person.NameContainsKeywordsPredicate;
import seedu.address.model.person.Person;
import seedu.address.model.person.attribute.Attribute;
import seedu.address.model.person.attribute.NameAttribute;
import seedu.address.model.person.relationship.Relationship;
import seedu.address.testutil.AddressBookBuilder;

public class ModelManagerTest {

    private ModelManager modelManager = new ModelManager();
    @Test
    public void constructor() {
        assertEquals(new UserPrefs(), modelManager.getUserPrefs());
        assertEquals(new GuiSettings(), modelManager.getGuiSettings());
        assertEquals(new AddressBook(), new AddressBook(modelManager.getAddressBook()));
    }

    @Test
    public void setUserPrefs_nullUserPrefs_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.setUserPrefs(null));
    }

    @Test
    public void setUserPrefs_validUserPrefs_copiesUserPrefs() {
        UserPrefs userPrefs = new UserPrefs();
        userPrefs.setAddressBookFilePath(Paths.get("address/book/file/path"));
        userPrefs.setGuiSettings(new GuiSettings(1, 2, 3, 4));
        modelManager.setUserPrefs(userPrefs);
        assertEquals(userPrefs, modelManager.getUserPrefs());

        // Modifying userPrefs should not modify modelManager's userPrefs
        UserPrefs oldUserPrefs = new UserPrefs(userPrefs);
        userPrefs.setAddressBookFilePath(Paths.get("new/address/book/file/path"));
        assertEquals(oldUserPrefs, modelManager.getUserPrefs());
    }

    @Test
    public void setGuiSettings_nullGuiSettings_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.setGuiSettings(null));
    }

    @Test
    public void setGuiSettings_validGuiSettings_setsGuiSettings() {
        GuiSettings guiSettings = new GuiSettings(1, 2, 3, 4);
        modelManager.setGuiSettings(guiSettings);
        assertEquals(guiSettings, modelManager.getGuiSettings());
    }

    @Test
    public void setAddressBookFilePath_nullPath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.setAddressBookFilePath(null));
    }

    @Test
    public void setAddressBookFilePath_validPath_setsAddressBookFilePath() {
        Path path = Paths.get("address/book/file/path");
        modelManager.setAddressBookFilePath(path);
        assertEquals(path, modelManager.getAddressBookFilePath());
    }

    @Test
    public void hasPerson_nullPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.hasPerson(null));
    }

    @Test
    public void hasPerson_personNotInAddressBook_returnsFalse() {
        assertFalse(modelManager.hasPerson(ALICE));
    }

    @Test
    public void hasPerson_personInAddressBook_returnsTrue() {
        modelManager.addPerson(ALICE);
        assertTrue(modelManager.hasPerson(ALICE));
    }

    @Test
    public void getFilteredPersonList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> modelManager.getFilteredPersonList().remove(0));
    }

    @Test
    public void equals() {
        AddressBook addressBook = new AddressBookBuilder().withPerson(ALICE).withPerson(BENSON).build();
        AddressBook differentAddressBook = new AddressBook();
        UserPrefs userPrefs = new UserPrefs();

        // same values -> returns true
        modelManager = new ModelManager(addressBook, userPrefs);
        ModelManager modelManagerCopy = new ModelManager(addressBook, userPrefs);
        assertTrue(modelManager.equals(modelManagerCopy));

        // same object -> returns true
        assertTrue(modelManager.equals(modelManager));

        // null -> returns false
        assertFalse(modelManager.equals(null));

        // different types -> returns false
        assertFalse(modelManager.equals(5));

        // different addressBook -> returns false
        assertFalse(modelManager.equals(new ModelManager(differentAddressBook, userPrefs)));

        // different filteredList -> returns false
        String[] keywords = ALICE.getName().fullName.split("\\s+");
        modelManager.updateFilteredPersonList(new NameContainsKeywordsPredicate(Arrays.asList(keywords)));
        assertFalse(modelManager.equals(new ModelManager(addressBook, userPrefs)));

        // resets modelManager to initial state for upcoming tests
        modelManager.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);

        // different userPrefs -> returns false
        UserPrefs differentUserPrefs = new UserPrefs();
        differentUserPrefs.setAddressBookFilePath(Paths.get("differentFilePath"));
        assertFalse(modelManager.equals(new ModelManager(addressBook, differentUserPrefs)));
    }

    @Test
    void deleteAttribute_callsDeleteAttributeOnAddressBook() {
        DOG.deleteAttribute("Name");
        assertFalse(DOG.hasAttribute("Name"));
    }

    @Test
    void getFullUuid_callsGetFullUuidOnAddressBook() {
        modelManager.addPerson(ALICE);
        UUID result = modelManager.getFullUuid(ALICE.getUuidString().substring(32, 36));
        assertEquals(ALICE.getUuidString(), result.toString());
    }

    @Test
    void getPersonByUuid_callsGetPersonByUuidOnAddressBook() {
        modelManager.addPerson(HOON);
        Person result = modelManager.getPersonByUuid(HOON.getUuid());
        assertEquals(HOON, result);
    }

    @Test
    void hasAttribute_callsHasAttributeOnAddressBook() {
        modelManager.addPerson(ALICE);
        assertTrue(modelManager.hasAttribute(ALICE.getUuidString(), "Name"));
    }

    @Test
    void deleteAttribute_test() {
        modelManager.addPerson(HOON);
        modelManager.deleteAttribute(HOON.getUuidString(), "Name");
        assertFalse(HOON.hasAttribute("Name"));
        HOON.setAttribute("Name", "Hoon Meier");
    }

    @Test
    public void deleteRelationship_validRelationship_relationshipDeleted() {
        ModelManager modelManager = new ModelManager();
        Attribute name1 = new NameAttribute("Name", "John Doe");
        Attribute name2 = new NameAttribute("Name", "Jane Doe");
        Attribute[] attributes1 = new Attribute[]{name1};
        Attribute[] attributes2 = new Attribute[]{name2};

        // Adding dummy people for testing
        Person person1 = new Person(attributes1);
        Person person2 = new Person(attributes2);
        UUID uuid11 = person1.getUuid();
        UUID uuid22 = person2.getUuid();

        // Create a test relationship
        Relationship relationship = new Relationship(uuid11, uuid22, "family");

        modelManager.deleteRelationship(relationship);

        assertFalse(modelManager.hasRelationship(relationship));
    }

    @Test
    public void getExistingRelationship_validRelationship_relationshipReturned() {
        ModelManager modelManager = new ModelManager();
        Attribute name1 = new NameAttribute("Name", "John Doe");
        Attribute name2 = new NameAttribute("Name", "Jane Doe");
        Attribute[] attributes1 = new Attribute[]{name1};
        Attribute[] attributes2 = new Attribute[]{name2};

        // Adding dummy people for testing
        Person person1 = new Person(attributes1);
        Person person2 = new Person(attributes2);
        UUID uuid11 = person1.getUuid();
        UUID uuid22 = person2.getUuid();
        // Create a test relationship
        Relationship relationship = new Relationship(uuid11, uuid22, "family");

        modelManager.addRelationship(relationship);
        String result = modelManager.getExistingRelationship(relationship);

        assertEquals(relationship.toString(), result);
    }

    @Test
    public void hasRelationshipWithDescriptor_validRelationship_trueReturned() {
        // Create a new ModelManager
        ModelManager modelManager = new ModelManager();
        Attribute name1 = new NameAttribute("Name", "John Doe");
        Attribute name2 = new NameAttribute("Name", "Jane Doe");
        Attribute[] attributes1 = new Attribute[]{name1};
        Attribute[] attributes2 = new Attribute[]{name2};

        // Adding dummy people for testing
        Person person1 = new Person(attributes1);
        Person person2 = new Person(attributes2);
        UUID uuid11 = person1.getUuid();
        UUID uuid22 = person2.getUuid();
        // Create a test relationship
        Relationship relationship = new Relationship(uuid11, uuid22, "family");

        // Add the relationship to the model manager
        modelManager.addRelationship(relationship);

        // Check if the model manager has the relationship with the same descriptor
        assertTrue(modelManager.hasRelationshipWithDescriptor(relationship));
    }

    @Test
    public void setPerson_validPerson_personSet() {
        // Create a ModelManager instance
        ModelManager modelManager = new ModelManager();

        // Create a person and add it to the ModelManager
        Attribute name1 = new NameAttribute("Name", "John Doe");
        Attribute[] attributes1 = new Attribute[]{name1};
        Person person1 = new Person(attributes1);
        modelManager.addPerson(person1);

        // Create a new person with the same UUID but different attributes
        Attribute name2 = new NameAttribute("Name", "Jane Doe");
        Attribute[] attributes2 = new Attribute[]{name2};
        Person editedPerson = new Person(attributes2);

        // Set the person in the ModelManager
        modelManager.setPerson(person1, editedPerson);
    }

    @Test
    public void getFilteredRelationshipList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> modelManager.getFilteredRelationshipList().remove(0));
    }
}
