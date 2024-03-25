package seedu.address.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalPersons.ALICE;
import static seedu.address.testutil.TypicalPersons.ALICEMAINTAINER;
import static seedu.address.testutil.TypicalPersons.ALICESTAFF;
import static seedu.address.testutil.TypicalPersons.ALICESUPPLIER;
import static seedu.address.testutil.TypicalPersons.BENSON;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.GuiSettings;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.messages.EditMessages;
import seedu.address.model.person.Name;
import seedu.address.model.person.NameContainsKeywordsPredicate;
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

        // finds a valid person by name
        try {
            assertEquals(ALICE, modelManagerCopy.findPersonByName(ALICE.getName()));
        } catch (CommandException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void findPersonByName_success() {
        AddressBook addressBook = new AddressBookBuilder().withPerson(ALICE).build();
        UserPrefs userPrefs = new UserPrefs();
        modelManager = new ModelManager(addressBook, userPrefs);

        Name nameToFind = ALICE.getName();
        Name nameFound = new Name("Not Alice Pauline");
        try {
            nameFound = modelManager.findPersonByName(nameToFind).getName();
        } catch (CommandException e) {
            fail();
        }
        assertEquals(nameToFind, nameFound);
    }

    @Test
    public void findPersonByNameFailure_throwsCommandException() {
        Name nameToFind = ALICE.getName();
        assertThrows(CommandException.class,
                EditMessages.MESSAGE_INVALID_EDIT_PERSON, () -> modelManager.findPersonByName(nameToFind));
    }

    @Test
    public void findMaintainerByName_success() {
        AddressBook addressBook = new AddressBookBuilder().withPerson(ALICEMAINTAINER).build();
        UserPrefs userPrefs = new UserPrefs();
        modelManager = new ModelManager(addressBook, userPrefs);

        Name nameToFind = ALICEMAINTAINER.getName();
        Name nameFound = new Name("Not Alice Pauline");
        try {
            nameFound = modelManager.findMaintainerByName(nameToFind).getName();
        } catch (CommandException e) {
            fail();
        }
        assertEquals(nameToFind, nameFound);
    }

    @Test
    public void findMaintainerByNameFailure_throwsCommandException() {
        Name nameToFind = ALICEMAINTAINER.getName();
        assertThrows(CommandException.class,
                EditMessages.MESSAGE_INVALID_EDIT_MAINTAINER, () -> modelManager.findMaintainerByName(nameToFind));
    }

    @Test
    public void findStaffByName_success() {
        AddressBook addressBook = new AddressBookBuilder().withPerson(ALICESTAFF).build();
        UserPrefs userPrefs = new UserPrefs();
        modelManager = new ModelManager(addressBook, userPrefs);

        Name nameToFind = ALICESTAFF.getName();
        Name nameFound = new Name("Not Alice Pauline");
        try {
            nameFound = modelManager.findStaffByName(nameToFind).getName();
        } catch (CommandException e) {
            fail();
        }
        assertEquals(nameToFind, nameFound);
    }

    @Test
    public void findStaffByNameFailure_throwsCommandException() {
        Name nameToFind = ALICESTAFF.getName();
        assertThrows(CommandException.class,
                EditMessages.MESSAGE_INVALID_EDIT_STAFF, () -> modelManager.findStaffByName(nameToFind));
    }

    @Test
    public void findSupplierByName_success() {
        AddressBook addressBook = new AddressBookBuilder().withPerson(ALICESUPPLIER).build();
        UserPrefs userPrefs = new UserPrefs();
        modelManager = new ModelManager(addressBook, userPrefs);

        Name nameToFind = ALICESUPPLIER.getName();
        Name nameFound = new Name("Not Alice Pauline");
        try {
            nameFound = modelManager.findSupplierByName(nameToFind).getName();
        } catch (CommandException e) {
            fail();
        }
        assertEquals(nameToFind, nameFound);
    }

    @Test
    public void findSupplierByNameFailure_throwsCommandException() {
        Name nameToFind = ALICESUPPLIER.getName();
        assertThrows(CommandException.class,
                EditMessages.MESSAGE_INVALID_EDIT_SUPPLIER, () -> modelManager.findSupplierByName(nameToFind));
    }

}
