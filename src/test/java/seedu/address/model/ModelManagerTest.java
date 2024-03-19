package seedu.address.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalPersons.ALICE;
import static seedu.address.testutil.TypicalPersons.BENSON;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.GuiSettings;
import seedu.address.model.person.NameContainsKeywordsPredicate;
import seedu.address.model.person.Person;
import seedu.address.testutil.AddressBookBuilder;
import seedu.address.testutil.PersonBuilder;
import seedu.address.testutil.TypicalPersons;

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
    public void setPerson_personInAddressBook_success() {
        // Create initial state
        ReadOnlyAddressBook initialAddressBook = modelManager.getAddressBook();
        ReadOnlyUserPrefs initialUserPrefs = modelManager.getUserPrefs();

        // Add a valid person to the address book
        Person validPerson = new PersonBuilder().build();
        modelManager.addPerson(validPerson);

        // Create an edited version of the person
        Person editedPerson = new PersonBuilder(validPerson).withName("New Name").build();

        // Set edited person in the model
        modelManager.setPerson(validPerson, editedPerson);

        // Check if the edited person is in the filteredPersons list
        assertEquals(editedPerson, modelManager.getFilteredPersonList().get(0));
        assertNotEquals(validPerson, modelManager.getFilteredPersonList().get(0));

        // Ensure that the address book's state was not changed
        assertEquals(initialAddressBook, modelManager.getAddressBook());

        // Ensure that the user prefs remain unchanged
        assertEquals(initialUserPrefs, modelManager.getUserPrefs());
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
    public void constructor_validArguments_success() {
        // Create a ReadOnlyAddressBook with typical persons
        ReadOnlyAddressBook addressBook = TypicalPersons.getTypicalAddressBook();

        // Create a ReadOnlyUserPrefs with typical preferences
        ReadOnlyUserPrefs userPrefs = new UserPrefs();

        // Create a ModelManager instance using the constructor with arguments
        ModelManager modelManager = new ModelManager(addressBook, userPrefs);

        // Verify that the versionedAddressBook is correctly initialized with the data from the ReadOnlyAddressBook
        assertEquals(addressBook, modelManager.getAddressBook());

        // Verify that the userPrefs is correctly initialized with the data from the ReadOnlyUserPrefs
        assertEquals(userPrefs, modelManager.getUserPrefs());

        assertTrue(modelManager.getFilteredPersonList().containsAll(addressBook.getPersonList()));
    }

    @Test
    public void setUserPrefs_validUserPrefs_success() {
        // Create initial user preferences
        UserPrefs initialUserPrefs = new UserPrefs();

        // Create a new set of user preferences
        UserPrefs newUserPrefs = new UserPrefs();
        GuiSettings defaultGuiSettings = new GuiSettings(800, 600, 0, 0);
        newUserPrefs.setGuiSettings(defaultGuiSettings);

        // Create a ModelManager instance
        ModelManager modelManager = new ModelManager();

        // Set the user preferences
        modelManager.setUserPrefs(newUserPrefs);

        // Verify that the user preferences in the ModelManager are updated
        ReadOnlyUserPrefs retrievedUserPrefs = modelManager.getUserPrefs();
        assertEquals(newUserPrefs, retrievedUserPrefs);
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
}
