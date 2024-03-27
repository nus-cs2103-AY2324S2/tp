package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.ImportExamCommand.addToErrorReport;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.exam.UniqueExamList;

public class ImportExamCommandTest {

    public static final String MIDTERM_NOT_FOUND_ERROR =
            "\nBelow are the errors that occurred while importing exams:\n"
            + "Midterm: Exam not found\n";
    public static final String CREATE_MIDTERM_100 = " n/Midterm s/100";
    public static final String VALID_PATH = "src/test/data/ImportExamCommandTest/testimportexam.csv";
    private ImportExamCommand importExamCommand;
    private Model model;

    @BeforeEach
    public void setUp() throws CommandException, ParseException {
        model = mock(Model.class);
        model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        UniqueExamList examList = new UniqueExamList();
    }

    @Test
    public void execute_validFilePath_success() throws CommandException {
        Path filePath = Paths.get("src/test/data/ImportExamCommandTest/valid.csv");
        String expectedMessage = String.format(ImportExamCommand.MESSAGE_SUCCESS, filePath);
        importExamCommand = new ImportExamCommand(filePath);

        assertCommandSuccess(importExamCommand, model, expectedMessage, model);
    }

    @Test
    public void execute_invalidFilePath_throwsCommandException() throws CommandException {
        Path invalidFilePath = Paths.get("invalid/path/to/file.csv");
        importExamCommand = new ImportExamCommand(invalidFilePath);

        assertThrows(CommandException.class, () -> importExamCommand.execute(model));
    }

    @Test
    public void testAddToErrorReport() {
        Path invalidFilePath = Paths.get("invalid/path/to/file.csv");
        importExamCommand = new ImportExamCommand(invalidFilePath);

        addToErrorReport("Email", "Invalid email format");
        assertEquals(
                "\nBelow are the errors that occurred while importing exams:\nEmail: Invalid email format\n",
                ImportExamCommand.generateErrorReport());
    }

    @Test
    public void testGenerateErrorReportEmpty() {
        String result = ImportExamCommand.generateErrorReport();
        assertEquals("", result);
    }

    @Test
    public void testSuccess() throws CommandException {
        Path filePath = Paths.get(VALID_PATH);
        ImportExamCommand importExamCommand = new ImportExamCommand(filePath);
        String expectedMessage = String.format(ImportExamCommand.MESSAGE_SUCCESS, filePath);
        assertCommandSuccess(importExamCommand, model, expectedMessage, model);
    }

}
