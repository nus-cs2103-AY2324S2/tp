package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import seedu.address.logic.Logic;
import seedu.address.logic.LogicManager;
import seedu.address.logic.commands.ImportCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.storage.ImportManager;
import seedu.address.storage.ImportUserPrefs;
import seedu.address.storage.JsonAddressBookStorage;
import seedu.address.storage.JsonUserPrefsStorage;
import seedu.address.storage.StorageManager;

public class ImportCommandParserTest {

    @TempDir
    public Path temporaryFolder;

    private Model model = new ModelManager();
    private Logic logic;

    private JsonAddressBookStorage tempAddressBookStorage;

    private JsonUserPrefsStorage tempUserPrefsStorage;
    private ImportCommandParser parser = new ImportCommandParser();

    @BeforeEach
    public void setUp() {
        JsonAddressBookStorage addressBookStorage =
                new JsonAddressBookStorage(temporaryFolder.resolve("addressBook.json"));
        this.tempAddressBookStorage = addressBookStorage;
        JsonUserPrefsStorage userPrefsStorage =
                new JsonUserPrefsStorage(temporaryFolder.resolve("userPrefs.json"));
        this.tempUserPrefsStorage = userPrefsStorage;
        StorageManager storage = new StorageManager(addressBookStorage, userPrefsStorage);
        logic = new LogicManager(model, storage);
    }

    private void createMockCsvFile() {
        try {
            Path jsonFilePath = Paths.get("./data/test.json");
            if (Files.exists(jsonFilePath)) {
                Files.delete(jsonFilePath);
            }
            Path csvDirectoryPath = Paths.get(ImportUserPrefs.IMPORTS_DIRECTORY).toAbsolutePath();
            if (Files.notExists(csvDirectoryPath)) {
                Files.createDirectories(csvDirectoryPath);
            }

            Path csvFilePath = csvDirectoryPath.resolve("test.csv");

            if (Files.exists(csvFilePath)) {
                Files.delete(csvFilePath); // Delete if exists
            }
            Files.createFile(csvFilePath); // Create a new file
        } catch (IOException e) {
            System.out.println("Error creating mock CSV file: " + e.getMessage());
        }
    }
    @Test
    public void parse_validFileName_returnsImportCommand() throws ParseException {
        createMockCsvFile();
        Path pathToImportFrom = Paths.get("./imports/test.csv");
        Path pathToImportTo = Paths.get("data/test.json");
        ImportCommand expectedCommand = new ImportCommand(
                pathToImportFrom,
                pathToImportTo,
                new ImportManager(pathToImportFrom, pathToImportTo)
        );

        ImportCommand resultCommand = parser.parse("test");

        assertEquals(expectedCommand, resultCommand);
    }

    @Test
    public void parse_emptyInput_throwsParseException() {
        ImportCommandParser parser = new ImportCommandParser();
        String emptyInput = "";

        assertThrows(ParseException.class, () -> parser.parse(emptyInput));
    }

    @Test
    public void parse_invalidFileName_throwsParseException() {
        ImportCommandParser parser = new ImportCommandParser();
        String invalidFileName = "invalid/file*name";

        assertThrows(Exception.class, () -> parser.parse(invalidFileName));
    }

    @Test
    public void parse_validFileNameWithWhitespace_returnsImportCommand() throws ParseException {
        createMockCsvFile();
        Path pathToImportFrom = Paths.get("./imports/test.csv");
        Path pathToImportTo = Paths.get("data/test.json");
        ImportCommand expectedCommand = new ImportCommand(
                pathToImportFrom,
                pathToImportTo,
                new ImportManager(pathToImportFrom, pathToImportTo)
        );

        ImportCommand resultCommand = parser.parse("     test     ");

        assertEquals(expectedCommand, resultCommand);
    }
}
