package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import seedu.address.logic.Logic;
import seedu.address.logic.LogicManager;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.storage.ImportManager;
import seedu.address.storage.ImportUserPrefs;
import seedu.address.storage.JsonAddressBookStorage;
import seedu.address.storage.JsonUserPrefsStorage;
import seedu.address.storage.StorageManager;

public class ImportCommandTest {
    @TempDir
    public Path temporaryFolder;

    private Model model = new ModelManager();
    private Logic logic;

    private JsonAddressBookStorage tempAddressBookStorage;

    private JsonUserPrefsStorage tempUserPrefsStorage;

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
    public void getPathToImportTo_returnCorrectPath() {
        Path path1 = Paths.get("path/to/file2.csv");
        Path pathJson = Paths.get("path/to/json");
        ImportManager importManager = new ImportManager(path1, pathJson);
        ImportCommand command = new ImportCommand(path1, pathJson, importManager);
        assertEquals(pathJson.toString(), command.getPathToImportTo());
    }

    @Test
    public void execute_importSuccessReturnsSuccessMessage() {
        createMockCsvFile();
        Path pathToImportFrom = Paths.get("./imports/test.csv");
        Path pathToImportTo = Paths.get("./data/test.json");
        ImportManager importManager = new ImportManager(pathToImportFrom, pathToImportTo);
        ImportCommand importCommand = new ImportCommand(pathToImportFrom, pathToImportTo, importManager);

        CommandResult result = importCommand.execute(model);
        assertEquals(ImportCommand.MESSAGE_SUCCESS, result.getFeedbackToUser());
    }

    @Test
    public void execute_importFailureReturnsFailureMessage() {
        createMockCsvFile();
        Path pathToImportFrom = Paths.get("");
        Path pathToImportTo = Paths.get("./data/test.json");
        ImportManager importManager = new ImportManager(pathToImportFrom, pathToImportTo);
        ImportCommand importCommand = new ImportCommand(pathToImportFrom, pathToImportTo, importManager);

        CommandResult result = importCommand.execute(model);
        assertEquals(ImportCommand.MESSAGE_FAILURE, result.getFeedbackToUser());
    }

    @Test
    public void equals_differentObjectsreturnsFalse() {
        Path path1 = Paths.get("path/to/file1.csv");
        Path path2 = Paths.get("path/to/file2.csv");
        Path pathJson = Paths.get("path/to/json");
        ImportManager importManager1 = new ImportManager(path1, pathJson);
        ImportManager importManager2 = new ImportManager(path2, pathJson);

        ImportCommand command1 = new ImportCommand(path1, pathJson, importManager1);
        ImportCommand command2 = new ImportCommand(path2, pathJson, importManager2);

        assertFalse(command1.equals(command2));
    }

    @Test
    public void equals_differentTypesreturnsFalse() {
        Path path1 = Paths.get("path/to/file2.csv");
        Path pathJson = Paths.get("path/to/json");
        ImportManager importManager = new ImportManager(path1, pathJson);
        ImportCommand command = new ImportCommand(path1, path1, importManager);
        String string = "String";
        assertFalse(command.equals(string));
    }

    @Test
    public void equals_sameObjectreturnsTrue() {
        Path path1 = Paths.get("path/to/file2.csv");
        Path pathJson = Paths.get("path/to/json");
        ImportManager importManager = new ImportManager(path1, pathJson);
        ImportCommand command = new ImportCommand(path1, path1, importManager);
        assertTrue(command.equals(command));
    }

    @Test
    public void equals_samePathReturnsTrue() {
        Path path1 = Paths.get("path/to/file1.csv");
        Path pathJson = Paths.get("path/to/json");
        ImportManager importManager1 = new ImportManager(path1, pathJson);
        ImportManager importManager2 = new ImportManager(path1, pathJson);

        ImportCommand command1 = new ImportCommand(path1, pathJson, importManager1);
        ImportCommand command2 = new ImportCommand(path1, pathJson, importManager2);

        assertTrue(command1.equals(command2));
    }

}
