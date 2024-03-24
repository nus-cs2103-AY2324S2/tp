package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.storage.JsonAddressBookStorage;

public class ExportCommandTest {

    private static final String TEST_CSV_FILE_PATH =
            "./src/test/data/ExportCommandTest/addressbookdata/addressbooktest.csv";
    private static final Path TEST_JSON_FILE_PATH = Paths.get("src", "test", "data", "ExportCommandTest",
            "filteredAddressBook.json");
    private static final Path TYPICAL_PERSONS_PATH = Paths.get("src", "test", "data",
            "JsonSerializableAddressBookTest", "typicalPersonsAddressBook.json");

    private ExportCommand exportCommand = new ExportCommand();
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    private Model emptyModel = new ModelManager(new AddressBook(), new UserPrefs());

    @BeforeEach
    public void setUp() {
        exportCommand.updateCsvFilePath(TEST_CSV_FILE_PATH);
        exportCommand.updateFilteredJsonFilePath(TEST_JSON_FILE_PATH);
        model.setAddressBookFilePath(TYPICAL_PERSONS_PATH);
    }

    @Test
    public void updateCsvFilePathTest_success() {
        exportCommand.updateCsvFilePath(TEST_CSV_FILE_PATH);
        assertEquals(TEST_CSV_FILE_PATH, exportCommand.getCsvFilePath());
    }

    @Test
    public void updateFilteredJsonFilePath_success() {
        exportCommand.updateFilteredJsonFilePath(TEST_JSON_FILE_PATH);
        assertEquals(TEST_JSON_FILE_PATH, exportCommand.getFilteredJsonFilePath());
    }

    @Test
    public void execute_emptyFilteredPersonList_throwsCommandException() {
        CommandException thrown = assertThrows(CommandException.class, () -> exportCommand.execute(emptyModel));
        assertEquals(exportCommand.MESSAGE_NOTHING_TO_EXPORT_FAILURE, thrown.getMessage());
    }

    @Test
    public void writeToJsonFileThrowsIoException_throwsCommandException() throws IOException {
        AddressBook addressBook = new AddressBook();
        JsonAddressBookStorage jsonAddressBookStorage = mock(JsonAddressBookStorage.class);

        doThrow(new IOException("File write error")).when(jsonAddressBookStorage).saveAddressBook(addressBook);

        CommandException exception = assertThrows(CommandException.class, () -> {
            exportCommand.writeToJsonFile(jsonAddressBookStorage, addressBook);
        });
        assertEquals(exportCommand.MESSAGE_WRITE_TO_JSON_FAILURE, exception.getMessage());
    }

    @Test
    public void nonexistentJsonFilePath_throwsCommandException() {
        File jsonFile = new File("~/nonexistentpath/nonexistentfile.json");
        CommandException thrown = assertThrows(CommandException.class, () -> {
            exportCommand.readJsonFile(jsonFile);
        });
        assertEquals(exportCommand.MESSAGE_JSON_FILE_NOT_FOUND_FAILURE, thrown.getMessage());
    }

    @Test
    public void invalidJsonFileThrowsJsonParseException_throwsCommandException() {
        File jsonFile = new File("./src/test/data/ExportCommandTest/invalidJsonFile.json");

        CommandException thrown = assertThrows(CommandException.class, () -> {
            exportCommand.readJsonFile(jsonFile);
        });
        assertEquals(exportCommand.MESSAGE_PARSE_JSON_FILE_FAILURE, thrown.getMessage());
    }

    @Test
    public void emptyPersonsArray_throwsCommandException() {
        ObjectMapper objectMapper = new ObjectMapper();
        ArrayNode emptyPersonsArray = objectMapper.createArrayNode();
        ObjectNode rootNode = objectMapper.createObjectNode();
        rootNode.set("persons", emptyPersonsArray);
        JsonNode jsonTree = rootNode;

        CommandException exception = assertThrows(CommandException.class, () -> {
            exportCommand.readPersonsArray(jsonTree);
        });
        assertEquals(exportCommand.MESSAGE_EMPTY_JSON_FILE_FAILURE, exception.getMessage());
    }

    @Test
    public void missingPersonsArray_throwsCommandException() {
        ObjectMapper objectMapper = new ObjectMapper();
        ObjectNode jsonTree = objectMapper.createObjectNode();

        CommandException exception = assertThrows(CommandException.class, () -> {
            exportCommand.readPersonsArray(jsonTree);
        });
        assertEquals(exportCommand.MESSAGE_EMPTY_JSON_FILE_FAILURE, exception.getMessage());
    }

    @Test
    public void execute_unableToCreateCsvDirectory_throwsCommandException() {
        exportCommand.updateCsvFilePath("~/nonexistent/path");

        CommandException thrown = assertThrows(CommandException.class, () -> exportCommand.execute(model));
        assertEquals(exportCommand.MESSAGE_CREATE_CSV_DIRECTORY_FAILURE, thrown.getMessage());
    }

    @Test
    public void execute_validAddressBookExport_success() throws Exception {
        CommandResult commandResult = exportCommand.execute(model);
        assertEquals(exportCommand.MESSAGE_SUCCESS, commandResult.getFeedbackToUser());

        File csvFile = new File("./src/test/data/ExportCommandTest/addressbookdata/addressbooktest.csv");
        assertTrue(csvFile.exists());
    }

}
