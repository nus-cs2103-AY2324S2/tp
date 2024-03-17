package seedu.address.logic.commands;


import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalPersons.JAMES;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import java.io.File;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.DataLoadingException;
import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.storage.JsonAdaptedPerson;


public class ImportCommandTest {
    private static final String UNKNOWN_FILE_NAME = "./src/test/data/ImportCommandTest/abcdefgh_abcdefgh.json";
    private static final String ADDRESS_BOOK_PATH = "./src/test/data/ImportCommandTest/addressbook.json";

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void constructor_nullFileSet_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new ImportCommand(null));
    }

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
    public void execute_hasDuplicatePerson_failure() {
        HashSet<File> curHashSet = new HashSet<>();
        curHashSet.add(new File(ADDRESS_BOOK_PATH));
        ImportCommand importCommand = new ImportCommand(curHashSet);
        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.addPerson(JAMES);
        String expectedMessage = String.format(ImportCommand.MESSAGE_DUPLICATE_PERSON, JAMES.getName(),
                JAMES.getPhone());
        assertThrows(CommandException.class, expectedMessage, () -> importCommand.execute(expectedModel));
    }

    @Test
    public void retrievePersonsFromFile_fileNotExist_failure() {
        List<JsonAdaptedPerson> list = new ArrayList<>();
        HashSet<File> curHashSet = new HashSet<>();
        curHashSet.add(new File(UNKNOWN_FILE_NAME));
        ImportCommand importCommand = new ImportCommand(curHashSet);
        assertThrows(IllegalValueException.class, () -> importCommand.retrievePersonsFromFile(list));
    }

    @Test
    public void execute_dataLoadingException_failure() {
        HashSet<File> curHashSet = new HashSet<>();
        curHashSet.add(new File(ADDRESS_BOOK_PATH));
        assertThrows(CommandException.class, () -> new ImportCommandStubDataLoadingException(curHashSet)
                .execute(model));
    }

    @Test
    public void execute_illegalValueException_failure() {
        HashSet<File> curHashSet = new HashSet<>();
        curHashSet.add(new File(ADDRESS_BOOK_PATH));
        assertThrows(CommandException.class, () -> new ImportCommandStubIllegalValueException(curHashSet)
                .execute(model));
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

    private class ImportCommandStubDataLoadingException extends ImportCommand {
        /**
         * Constructs a new ImportCommand to add the contacts in the specified files in {@code fileSet}.
         *
         * @param fileSet A set of Files.
         */
        public ImportCommandStubDataLoadingException(Set<File> fileSet) {
            super(fileSet);
        }

        @Override
        public void retrievePersonsFromFile(List<JsonAdaptedPerson> savedPersons)
                throws IllegalValueException, DataLoadingException {
            throw new DataLoadingException(new Exception("Data loading exception."));
        }
    }

    private class ImportCommandStubIllegalValueException extends ImportCommand {

        /**
         * Constructs a new ImportCommand to add the contacts in the specified files in {@code fileSet}.
         *
         * @param fileSet A set of Files.
         */
        public ImportCommandStubIllegalValueException(Set<File> fileSet) {
            super(fileSet);
        }

        @Override
        public void retrievePersonsFromFile(List<JsonAdaptedPerson> savedPersons)
                throws IllegalValueException, DataLoadingException {
            throw new IllegalValueException("Illegal value exception.");
        }
    }
}
