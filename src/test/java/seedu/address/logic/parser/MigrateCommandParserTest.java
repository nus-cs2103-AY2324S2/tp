package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import seedu.address.logic.Logic;
import seedu.address.logic.LogicManager;
import seedu.address.logic.commands.MigrateCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.storage.ImportManager;
import seedu.address.storage.ImportUserPrefs;
import seedu.address.storage.JsonAddressBookStorage;
import seedu.address.storage.JsonUserPrefsStorage;
import seedu.address.storage.StorageManager;

public class MigrateCommandParserTest {

    @TempDir
    public Path temporaryFolder;

    private Model model = new ModelManager();
    private Logic logic;

    private JsonAddressBookStorage tempAddressBookStorage;

    private JsonUserPrefsStorage tempUserPrefsStorage;

    private MigrateCommandParser parser = new MigrateCommandParser();

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
    public void parse_validFileName_returnsMigrateCommand() throws ParseException {
        Path pathToImportFrom = Paths.get("imports/test.csv");
        MigrateCommand expectedCommand = new MigrateCommand(
                pathToImportFrom,
                new ImportManager(pathToImportFrom, tempAddressBookStorage.getAddressBookFilePath())
        );

        MigrateCommand resultCommand = parser.parse("test");

        assertEquals(expectedCommand, resultCommand);
    }

    @Test
    public void parse_emptyInput_throwsParseException() {
        String emptyInput = "";
        assertThrows(ParseException.class, () -> parser.parse(emptyInput));
    }

    @Test
    public void parse_invalidFileName_throwsParseException() {
        String invalidFileName = "invalid/file*name";
        assertThrows(InvalidPathException.class, () -> parser.parse(invalidFileName));
    }

    @Test
    public void parse_validFileNameWithWhitespace_returnsMigrateCommand() throws ParseException {
        Path pathToImportFrom = Paths.get("imports/test.csv");
        MigrateCommand expectedCommand = new MigrateCommand(
                pathToImportFrom,
                new ImportManager(pathToImportFrom, tempAddressBookStorage.getAddressBookFilePath())
        );

        MigrateCommand resultCommand = parser.parse("     test     ");

        assertEquals(expectedCommand, resultCommand);
    }

}
