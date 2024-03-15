package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;


public class ExportCommandTest {

    private static final Path EMPTY_PERSONS_PATH = Paths.get("src", "test", "data",
            "JsonSerializableAddressBookTest", "emptyPersonAddressBook.json");
    private static final Path INVALID_JSON_FORMAT_PATH = Paths.get("src", "test", "data",
            "JsonAddressBookStorageTest", "notJsonFormatAddressBook.json");
    private static final Path INVALID_JSON_STRUCTURE_PATH = Paths.get("src", "test", "data",
            "JsonAddressBookStorageTest", "invalidJsonStructureAddressBook.json");
    private static final Path NO_PERSONS_ARRAY_PATH = Paths.get("src", "test", "data",
            "JsonAddressBookStorageTest", "noPersonsArrayAddressBook.json");
    private static final Path TYPICAL_PERSONS_PATH = Paths.get("src", "test", "data",
            "JsonSerializableAddressBookTest", "typicalPersonsAddressBook.json");
    private ExportCommand exportCommand = new ExportCommand();
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    private Model emptyModel = new ModelManager(new AddressBook(), new UserPrefs());
    private Model modelWithNoFilePath = new ModelManager(new AddressBook(), new UserPrefs());
    private Model modelWithInvalidJsonFormat = new ModelManager(new AddressBook(), new UserPrefs());
    private Model modelWithNoPersonsArray = new ModelManager(new AddressBook(), new UserPrefs());
    private Model modelWithInvalidStructure = new ModelManager(new AddressBook(), new UserPrefs());

    @BeforeEach
    public void setUp() {
        model.setAddressBookFilePath(TYPICAL_PERSONS_PATH);
        emptyModel.setAddressBookFilePath(EMPTY_PERSONS_PATH);
        modelWithInvalidJsonFormat.setAddressBookFilePath(INVALID_JSON_FORMAT_PATH);
        modelWithNoPersonsArray.setAddressBookFilePath(NO_PERSONS_ARRAY_PATH);
        modelWithInvalidStructure.setAddressBookFilePath(INVALID_JSON_STRUCTURE_PATH);
    }

    @Test
    public void updateCsvFilePathTest_success() {
        String newFilePath = "./src/test/data/addressbookdata/addressbooktest.csv";
        exportCommand.updateCsvFilePath(newFilePath);
        assertEquals(newFilePath, exportCommand.getCsvFilePath());
    }

    @Test
    public void execute_validAddressBookExport_success() throws Exception {
        exportCommand.updateCsvFilePath("./src/test/data/addressbookdata/addressbooktest.csv");

        CommandResult commandResult = exportCommand.execute(model);
        assertEquals(exportCommand.MESSAGE_SUCCESS, commandResult.getFeedbackToUser());

        File csvFile = new File("./src/test/data/addressbookdata/addressbooktest.csv");
        assertTrue(csvFile.exists());
    }

    @Test
    public void execute_unableToCreateCsvDirectory_throwsCommandException() {
        exportCommand.updateCsvFilePath("/nonexistent/path");

        CommandException thrown = assertThrows(CommandException.class, () -> exportCommand.execute(model));
        assertEquals("Could not create directory for CSV file.", thrown.getMessage());
    }

    @Test
    public void execute_withoutValidJsonFilePath_throwsCommandException() {
        CommandException thrown = assertThrows(CommandException.class, () -> exportCommand
                .execute(modelWithNoFilePath));
        assertEquals("Cannot find JSON file to export.", thrown.getMessage());
    }

    @Test
    public void execute_withInvalidJsonFormat_throwsCommandException() {
        CommandException thrown = assertThrows(CommandException.class, () -> exportCommand
                .execute(modelWithInvalidJsonFormat));
        assertEquals("Error parsing JSON data.", thrown.getMessage());
    }

    @Test
    public void execute_withInvalidJsonStructure_throwsCommandException() {
        CommandException thrown = assertThrows(CommandException.class, () -> exportCommand
                .execute(modelWithInvalidStructure));
        assertEquals("Error parsing JSON data.", thrown.getMessage());
    }

    @Test
    public void execute_emptyPersonsAddressBook_throwsCommandException() {
        CommandException thrown = assertThrows(CommandException.class, () -> exportCommand.execute(emptyModel));
        assertEquals("The JSON File is empty.", thrown.getMessage());
    }

    @Test
    public void execute_noPersonsArrayAddressBook_throwsCommandException() {
        CommandException thrown = assertThrows(CommandException.class, () -> exportCommand
                .execute(modelWithNoPersonsArray));
        assertEquals("The JSON File is empty.", thrown.getMessage());
    }

}
