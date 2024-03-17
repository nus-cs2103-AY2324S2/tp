package seedu.address.logic.commands;


import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalPersons.JAMES;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import java.io.File;
import java.util.HashSet;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;


public class ImportCommandTest {
    private static final String UNKNOWN_FILE_NAME = "./src/test/data/ImportCommandTest/abcdefgh_abcdefgh.json";
    private static final String ADDRESS_BOOK_PATH = "./src/test/data/ImportCommandTest/addressbook.json";

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void constructor_nullFileSet_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new ImportCommand(null));
    }

//    @Test
//    public void execute_fileExist_failure() {
//        HashSet<File> curHashSet = new HashSet<>();
//        curHashSet.add(new File(UNKNOWN_FILE_NAME));
//        ImportCommand importCommand = new ImportCommand(curHashSet);
//
//        String expectedMessage = String.format(ImportCommand.MESSAGE_FILE_NOT_FOUND, UNKNOWN_FILE_NAME);
//        assertThrows(CommandException.class, expectedMessage, () -> importCommand.execute(model));
//    }

    @Test
    public void execute_existingFile_success() {
        HashSet<File> curHashSet = new HashSet<>();
        curHashSet.add(new File(ADDRESS_BOOK_PATH));
        ImportCommand importCommand = new ImportCommand(curHashSet);
        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        String expectedMessage = "Contacts from files imported";
        expectedModel.addPerson(JAMES);
        assertCommandSuccess(importCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void equals() {
        HashSet<File> curHashSet = new HashSet<>();
        curHashSet.add(new File(UNKNOWN_FILE_NAME));
        HashSet<File> otherHashSet = new HashSet<>();

        final ImportCommand importCommand = new ImportCommand(curHashSet);

        assertTrue(importCommand.equals(importCommand));

        assertFalse(importCommand.equals(null));

        assertFalse(importCommand.equals(new ClearCommand()));

        assertFalse(importCommand.equals(new ImportCommand(otherHashSet)));
    }
}
