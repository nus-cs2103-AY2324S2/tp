package seedu.address.logic.commands;


import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import java.io.File;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Person;
import seedu.address.storage.JsonAdaptedPerson;


public class ImportCommandTest {
    private static final String ADDRESS_BOOK_PATH = "./data/addressbook.json";
    private static final String UNKNOWN_FILE_NAME = "./data/1234abcd1234efgh5678.json";

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void constructor_nullFileSet_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new ImportCommand(null));
    }

    @Test
    public void execute_fileExist_failure() {
        HashSet<File> curHashSet = new HashSet<>();
        curHashSet.add(new File(UNKNOWN_FILE_NAME));
        ImportCommand importCommand = new ImportCommand(curHashSet);

        String expectedMessage = String.format(ImportCommand.MESSAGE_FILE_NOT_FOUND, UNKNOWN_FILE_NAME);
        assertCommandFailure(importCommand, model, expectedMessage);
    }

    @Test
    public void execute_existingFile_failure() {
        HashSet<File> curHashSet = new HashSet<>();
        curHashSet.add(new File(ADDRESS_BOOK_PATH));
        ImportCommand importCommand = new ImportCommand(curHashSet);
        List<JsonAdaptedPerson> jsonAdaptedPersons = model
                .getAddressBook()
                .getPersonList()
                .stream()
                .limit(1)
                .map(JsonAdaptedPerson::new)
                .collect(Collectors.toList());
        try {
            Person person = jsonAdaptedPersons.get(0).toModelType();
            String expectedMessage = String.format(ImportCommand.MESSAGE_DUPLICATE_PERSON,
                    person.getName(), person.getAddress());
            assertCommandFailure(importCommand, model, expectedMessage);
        } catch (IllegalValueException ive) {
            assertFalse(false);
        }
    }

    @Test
    public void execute_importFile_success() {

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

    @Test
    public void retrievePersonsFromFile_fileExist_success() {

    }
}
