package seedu.address.logic;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.Messages.MESSAGE_CHANGED_DATA_SOURCE;
import static seedu.address.logic.Messages.MESSAGE_INVALID_STUDENT_ID;
import static seedu.address.logic.Messages.MESSAGE_UNKNOWN_COMMAND;
import static seedu.address.logic.commands.CommandTestUtil.ADDRESS_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.EMAIL_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.NAME_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.PHONE_DESC_ADD_AMY;
import static seedu.address.logic.commands.CommandTestUtil.STUDENT_ID_DESC_AMY;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalPersons.AMY;

import java.io.IOException;
import java.nio.file.AccessDeniedException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.exceptions.DataLoadingException;
import seedu.address.logic.commands.AddCommand;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.ImportCommand;
import seedu.address.logic.commands.ListCommand;
import seedu.address.logic.commands.MigrateCommand;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Person;
import seedu.address.storage.ImportUserPrefs;
import seedu.address.storage.JsonAddressBookStorage;
import seedu.address.storage.JsonUserPrefsStorage;
import seedu.address.storage.Storage;
import seedu.address.storage.StorageManager;
import seedu.address.testutil.PersonBuilder;

public class LogicManagerTest {
    private static final IOException DUMMY_IO_EXCEPTION = new IOException("dummy IO exception");
    private static final IOException DUMMY_AD_EXCEPTION = new AccessDeniedException("dummy access denied exception");

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
    public void execute_invalidCommandFormat_throwsParseException() {
        String invalidCommand = "uicfhmowqewca";
        assertParseException(invalidCommand, MESSAGE_UNKNOWN_COMMAND);
    }

    @Test
    public void execute_cdCommand_throwsDataLoadingException() {
        String cdCommand = "cd data/newab.json";
        LogicManager logicWithMockStorage =
                new LogicManager(model, new MockStorageThrowsAccessDeniedExceptionAndDataLoadingException());
        assertThrows(CommandException.class, () -> logicWithMockStorage.execute(cdCommand));
    }

    @Test
    public void execute_cdCommand() throws CommandException, ParseException {
        String cdCommand = "cd data/newab.json";
        CommandResult result = logic.execute(cdCommand);
        assertFalse(result.equals(MESSAGE_CHANGED_DATA_SOURCE));
    }

    @Test
    public void execute_importCommand() throws CommandException, ParseException {
        createMockCsvFile();
        String importCommand = "import test";
        CommandResult result = logic.execute(importCommand);
        assertTrue(result.getFeedbackToUser().equals(ImportCommand.MESSAGE_SUCCESS));
    }

    @Test
    public void execute_importCommandIoException() {
        createMockCsvFile();
        LogicManager logicWithMockStorage = new LogicManager(model, new MockStorageThrowsIoException());
        String importCommand = "import test";
        assertThrows(CommandException.class, () -> logicWithMockStorage.execute(importCommand));
    }

    @Test
    public void execute_importCommandAccessDeniedException() {
        createMockCsvFile();
        LogicManager logicWithMockStorage =
                new LogicManager(model, new MockStorageThrowsAccessDeniedExceptionAndDataLoadingException());
        String importCommand = "import test";
        assertThrows(CommandException.class, () -> logicWithMockStorage.execute(importCommand));
    }

    @Test
    public void execute_migrateCommand() throws CommandException, ParseException {
        createMockCsvFile();
        String migrateCommand = "migrate test";
        CommandResult result = logic.execute(migrateCommand);
        assertTrue(result.getFeedbackToUser().equals(MigrateCommand.MESSAGE_SUCCESS));
    }

    @Test
    public void execute_migrateCommandIoException() {
        LogicManager logicWithMockStorage = new LogicManager(model, new MockStorageThrowsIoException());
        String migrateCommand = "migrate test";
        assertThrows(CommandException.class, () -> logicWithMockStorage.execute(migrateCommand));
    }

    @Test
    public void execute_migrateCommandAccessDeniedException() {
        LogicManager logicWithMockStorage =
                new LogicManager(model, new MockStorageThrowsAccessDeniedExceptionAndDataLoadingException());
        String migrateCommand = "migrate test";
        assertThrows(CommandException.class, () -> logicWithMockStorage.execute(migrateCommand));
    }

    @Test
    public void execute_migrateCommandDataLoadingException() {
        LogicManager logicWithMockStorage =
                new LogicManager(model, new MockStorageThrowsDataLoadingException());
        String migrateCommand = "migrate test";
        try {
            logicWithMockStorage.execute(migrateCommand);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        ReadOnlyAddressBook currentData = model.getAddressBook();
        assertTrue(currentData.getPersonList().isEmpty(), "AddressBook should be empty");
    }

    @Test
    public void execute_commandExecutionError_throwsCommandException() {
        String deleteCommand = "delete 55555";
        assertCommandException(deleteCommand, MESSAGE_INVALID_STUDENT_ID);
    }

    @Test
    public void execute_validCommand_success() throws Exception {
        String listCommand = ListCommand.COMMAND_WORD;
        assertCommandSuccess(listCommand, ListCommand.MESSAGE_SUCCESS, model);
    }

    @Test
    public void execute_storageThrowsIoException_throwsCommandException() {
        assertCommandFailureForExceptionFromStorage(DUMMY_IO_EXCEPTION, String.format(
                LogicManager.FILE_OPS_ERROR_FORMAT, DUMMY_IO_EXCEPTION.getMessage()));
    }

    @Test
    public void execute_storageThrowsAdException_throwsCommandException() {
        assertCommandFailureForExceptionFromStorage(DUMMY_AD_EXCEPTION, String.format(
                LogicManager.FILE_OPS_PERMISSION_ERROR_FORMAT, DUMMY_AD_EXCEPTION.getMessage()));
    }

    @Test
    public void getFilteredPersonList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> logic.getFilteredPersonList().remove(0));
    }

    @Test
    public void getAddressBook_returnsCorrectAddressBook() {
        ReadOnlyAddressBook expectedAddressBook = model.getAddressBook();
        ReadOnlyAddressBook actualAddressBook = logic.getAddressBook();
        assertEquals(expectedAddressBook, actualAddressBook,
                "Returned address book should match the model's address book");
    }

    @Test
    public void getFilteredPersonList_returnsCorrectList() {
        ObservableList<Person> expectedList = model.getFilteredPersonList();
        ObservableList<Person> actualList = logic.getFilteredPersonList();
        assertEquals(expectedList, actualList,
                "Returned list should match the model's filtered person list");
    }

    @Test
    public void getAddressBookFilePath_returnsCorrectPath() {
        Path expectedPath = model.getAddressBookFilePath();
        Path actualPath = logic.getAddressBookFilePath();
        assertEquals(expectedPath, actualPath,
                "Returned path should match the model's address book file path");
    }

    @Test
    public void setAndGetGuiSettings_correctlyUpdatesAndReturnsSettings() {
        GuiSettings expectedSettings = new GuiSettings(1.0, 2, 3, 4);
        logic.setGuiSettings(expectedSettings);
        GuiSettings actualSettings = logic.getGuiSettings();
        assertEquals(expectedSettings, actualSettings, "Returned settings should match the set settings");
    }

    /**
     * Executes the command and confirms that
     * - no exceptions are thrown <br>
     * - the feedback message is equal to {@code expectedMessage} <br>
     * - the internal model manager state is the same as that in {@code expectedModel} <br>
     * @see #assertCommandFailure(String, Class, String, Model)
     */
    private void assertCommandSuccess(String inputCommand, String expectedMessage,
            Model expectedModel) throws CommandException, ParseException {
        CommandResult result = logic.execute(inputCommand);
        assertEquals(expectedMessage, result.getFeedbackToUser());
        assertEquals(expectedModel, model);
    }

    /**
     * Executes the command, confirms that a ParseException is thrown and that the result message is correct.
     * @see #assertCommandFailure(String, Class, String, Model)
     */
    private void assertParseException(String inputCommand, String expectedMessage) {
        assertCommandFailure(inputCommand, ParseException.class, expectedMessage);
    }

    /**
     * Executes the command, confirms that a CommandException is thrown and that the result message is correct.
     * @see #assertCommandFailure(String, Class, String, Model)
     */
    private void assertCommandException(String inputCommand, String expectedMessage) {
        assertCommandFailure(inputCommand, CommandException.class, expectedMessage);
    }

    /**
     * Executes the command, confirms that the exception is thrown and that the result message is correct.
     * @see #assertCommandFailure(String, Class, String, Model)
     */
    private void assertCommandFailure(String inputCommand, Class<? extends Throwable> expectedException,
            String expectedMessage) {
        Model expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        assertCommandFailure(inputCommand, expectedException, expectedMessage, expectedModel);
    }

    /**
     * Executes the command and confirms that
     * - the {@code expectedException} is thrown <br>
     * - the resulting error message is equal to {@code expectedMessage} <br>
     * - the internal model manager state is the same as that in {@code expectedModel} <br>
     * @see #assertCommandSuccess(String, String, Model)
     */
    private void assertCommandFailure(String inputCommand, Class<? extends Throwable> expectedException,
            String expectedMessage, Model expectedModel) {
        assertThrows(expectedException, expectedMessage, () -> logic.execute(inputCommand));
        assertEquals(expectedModel, model);
    }

    /**
     * Tests the Logic component's handling of an {@code IOException} thrown by the Storage component.
     *
     * @param e the exception to be thrown by the Storage component
     * @param expectedMessage the message expected inside exception thrown by the Logic component
     */
    private void assertCommandFailureForExceptionFromStorage(IOException e, String expectedMessage) {
        Path prefPath = temporaryFolder.resolve("ExceptionUserPrefs.json");

        // Inject LogicManager with an AddressBookStorage that throws the IOException e when saving
        JsonAddressBookStorage addressBookStorage = new JsonAddressBookStorage(prefPath) {
            @Override
            public void saveAddressBook(ReadOnlyAddressBook addressBook, Path filePath)
                    throws IOException {
                throw e;
            }
        };

        JsonUserPrefsStorage userPrefsStorage =
                new JsonUserPrefsStorage(temporaryFolder.resolve("ExceptionUserPrefs.json"));
        StorageManager storage = new StorageManager(addressBookStorage, userPrefsStorage);

        logic = new LogicManager(model, storage);

        // Triggers the saveAddressBook method by executing an add command
        String addCommand = AddCommand.COMMAND_WORD + NAME_DESC_AMY + PHONE_DESC_ADD_AMY
                + EMAIL_DESC_AMY + ADDRESS_DESC_AMY + STUDENT_ID_DESC_AMY;
        Person expectedPerson = new PersonBuilder(AMY).withTags().build();
        ModelManager expectedModel = new ModelManager();
        expectedModel.addPerson(expectedPerson);
        assertCommandFailure(addCommand, CommandException.class, expectedMessage, expectedModel);
    }

    public class MockStorageThrowsIoException implements Storage {

        @Override
        public Path getUserPrefsFilePath() {
            return tempUserPrefsStorage.getUserPrefsFilePath();
        }

        @Override
        public Optional<UserPrefs> readUserPrefs() throws DataLoadingException {
            return tempUserPrefsStorage.readUserPrefs();
        }

        @Override
        public void saveUserPrefs(ReadOnlyUserPrefs userPrefs) throws IOException {
            tempUserPrefsStorage.saveUserPrefs(userPrefs);
        }
        @Override
        public Path getAddressBookFilePath() {
            return tempAddressBookStorage.getAddressBookFilePath();
        }

        @Override
        public void setAddressBookFilePath(Path newPath) {
            tempAddressBookStorage.setAddressBookFilePath(newPath);
        }

        @Override
        public Optional<ReadOnlyAddressBook> readAddressBook() throws DataLoadingException {
            return readAddressBook(tempAddressBookStorage.getAddressBookFilePath());
        }

        @Override
        public Optional<ReadOnlyAddressBook> readAddressBook(Path filePath) throws DataLoadingException {
            return tempAddressBookStorage.readAddressBook(filePath);
        }

        @Override
        public void saveAddressBook(ReadOnlyAddressBook addressBook) throws IOException {
            throw new IOException("Mock IOException");
        }

        @Override
        public void saveAddressBook(ReadOnlyAddressBook addressBook, Path filePath) throws IOException {
            tempAddressBookStorage.saveAddressBook(addressBook, filePath);
        }
    }

    public class MockStorageThrowsAccessDeniedExceptionAndDataLoadingException implements Storage {

        @Override
        public Path getUserPrefsFilePath() {
            return tempUserPrefsStorage.getUserPrefsFilePath();
        }

        @Override
        public Optional<UserPrefs> readUserPrefs() throws DataLoadingException {
            return tempUserPrefsStorage.readUserPrefs();
        }

        @Override
        public void saveUserPrefs(ReadOnlyUserPrefs userPrefs) throws IOException {
            tempUserPrefsStorage.saveUserPrefs(userPrefs);
        }
        @Override
        public Path getAddressBookFilePath() {
            return tempAddressBookStorage.getAddressBookFilePath();
        }

        @Override
        public void setAddressBookFilePath(Path newPath) {
            tempAddressBookStorage.setAddressBookFilePath(newPath);
        }

        @Override
        public Optional<ReadOnlyAddressBook> readAddressBook() throws DataLoadingException {
            throw new DataLoadingException(new Exception("Mock DataLoadingException"));
        }

        @Override
        public Optional<ReadOnlyAddressBook> readAddressBook(Path filePath) throws DataLoadingException {
            return tempAddressBookStorage.readAddressBook(filePath);
        }

        @Override
        public void saveAddressBook(ReadOnlyAddressBook addressBook) throws AccessDeniedException {
            throw new AccessDeniedException("Mock AccessDeniedException");
        }

        @Override
        public void saveAddressBook(ReadOnlyAddressBook addressBook, Path filePath) throws IOException {
            tempAddressBookStorage.saveAddressBook(addressBook, filePath);
        }
    }

    public class MockStorageThrowsDataLoadingException implements Storage {

        @Override
        public Path getUserPrefsFilePath() {
            return tempUserPrefsStorage.getUserPrefsFilePath();
        }

        @Override
        public Optional<UserPrefs> readUserPrefs() throws DataLoadingException {
            return tempUserPrefsStorage.readUserPrefs();
        }

        @Override
        public void saveUserPrefs(ReadOnlyUserPrefs userPrefs) throws IOException {
            tempUserPrefsStorage.saveUserPrefs(userPrefs);
        }
        @Override
        public Path getAddressBookFilePath() {
            return tempAddressBookStorage.getAddressBookFilePath();
        }

        @Override
        public void setAddressBookFilePath(Path newPath) {
            tempAddressBookStorage.setAddressBookFilePath(newPath);
        }

        @Override
        public Optional<ReadOnlyAddressBook> readAddressBook() throws DataLoadingException {
            throw new DataLoadingException(new Exception("Mock DataLoadingException"));
        }

        @Override
        public Optional<ReadOnlyAddressBook> readAddressBook(Path filePath) throws DataLoadingException {
            throw new DataLoadingException(new Exception("Mock DataLoadingException"));
        }

        @Override
        public void saveAddressBook(ReadOnlyAddressBook addressBook) throws IOException {
            saveAddressBook(addressBook, tempAddressBookStorage.getAddressBookFilePath());
        }

        @Override
        public void saveAddressBook(ReadOnlyAddressBook addressBook, Path filePath) throws IOException {
            tempAddressBookStorage.saveAddressBook(addressBook, filePath);
        }
    }
}
