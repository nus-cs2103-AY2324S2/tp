package seedu.address.logic;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.logic.Messages.MESSAGE_INVALID_COURSE_MATE_NAME;
import static seedu.address.logic.Messages.MESSAGE_UNKNOWN_COMMAND;
import static seedu.address.logic.commands.CommandTestUtil.EMAIL_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.PHONE_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalCourseMates.AMY;

import java.io.IOException;
import java.nio.file.AccessDeniedException;
import java.nio.file.Path;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import seedu.address.logic.commands.AddCommand;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.ListCommand;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.GroupList;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.ReadOnlyContactList;
import seedu.address.model.UserPrefs;
import seedu.address.model.coursemate.CourseMate;
import seedu.address.storage.JsonContactListStorage;
import seedu.address.storage.JsonGroupListStorage;
import seedu.address.storage.JsonUserPrefsStorage;
import seedu.address.storage.StorageManager;
import seedu.address.testutil.CourseMateBuilder;

public class LogicManagerTest {
    private static final IOException DUMMY_IO_EXCEPTION = new IOException("dummy IO exception");
    private static final IOException DUMMY_AD_EXCEPTION = new AccessDeniedException("dummy access denied exception");

    @TempDir
    public Path temporaryFolder;

    private Model model = new ModelManager();
    private Logic logic;

    @BeforeEach
    public void setUp() {
        JsonContactListStorage contactListStorage =
                new JsonContactListStorage(temporaryFolder.resolve("contactList.json"));
        JsonUserPrefsStorage userPrefsStorage =
                new JsonUserPrefsStorage(temporaryFolder.resolve("userPrefs.json"));
        JsonGroupListStorage groupListStorage =
                new JsonGroupListStorage(temporaryFolder.resolve("groupList.json"));
        StorageManager storage = new StorageManager(contactListStorage, userPrefsStorage, groupListStorage);
        logic = new LogicManager(model, storage);
    }

    @Test
    public void execute_invalidCommandFormat_throwsParseException() {
        String invalidCommand = "uicfhmowqewca";
        assertParseException(invalidCommand, MESSAGE_UNKNOWN_COMMAND);
    }

    @Test
    public void execute_commandExecutionError_throwsCommandException() {
        String deleteCommand = "delete #9";
        assertCommandException(deleteCommand, MESSAGE_INVALID_COURSE_MATE_NAME);
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
    public void getFilteredCourseMateList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> logic.getFilteredCourseMateList().remove(0));
    }

    @Test
    public void getFilteredGroupList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> logic.getFilteredGroupList().remove(0));
    }

    @Test
    public void getRecentlyProcessedCourseMate_newLogicManager_returnsNull() {
        assertEquals(null, logic.getRecentlyProcessedCourseMate());
    }

    @Test
    public void getRecentlyProcessedCourseMate_setToAmy_returnsAmy() {
        model.setRecentlyProcessedCourseMate(AMY);
        assertEquals(AMY, logic.getRecentlyProcessedCourseMate());
    }

    @Test
    public void setRecentlyProcessedCourseMate_setToAmy_returnsAmy() {
        logic.setRecentlyProcessedCourseMate(AMY);
        assertEquals(AMY, logic.getRecentlyProcessedCourseMate());
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
        Model expectedModel = new ModelManager(model.getContactList(), new UserPrefs(), new GroupList());
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

        // Inject LogicManager with an ContactListStorage that throws the IOException e when saving
        JsonContactListStorage contactListStorage = new JsonContactListStorage(prefPath) {
            @Override
            public void saveContactList(ReadOnlyContactList contactList, Path filePath)
                    throws IOException {
                throw e;
            }
        };

        JsonUserPrefsStorage userPrefsStorage =
                new JsonUserPrefsStorage(temporaryFolder.resolve("ExceptionUserPrefs.json"));
        JsonGroupListStorage groupListStorage =
                new JsonGroupListStorage(temporaryFolder.resolve("ExceptionGroupList.json"));
        StorageManager storage = new StorageManager(contactListStorage, userPrefsStorage, groupListStorage);

        logic = new LogicManager(model, storage);

        // Triggers the saveContactList method by executing an add command
        String addCommand = AddCommand.COMMAND_WORD + " " + VALID_NAME_AMY + " " + PHONE_DESC_AMY
                + EMAIL_DESC_AMY;
        CourseMate expectedCourseMate = new CourseMateBuilder(AMY).withSkills().build();
        ModelManager expectedModel = new ModelManager();
        expectedModel.addCourseMate(expectedCourseMate);
        assertCommandFailure(addCommand, CommandException.class, expectedMessage, expectedModel);
    }
}
