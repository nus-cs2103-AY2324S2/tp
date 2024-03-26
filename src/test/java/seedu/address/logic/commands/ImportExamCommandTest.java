package seedu.address.logic.commands;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.AddExamCommandParser;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.exam.Exam;
import seedu.address.model.exam.UniqueExamList;
import seedu.address.model.person.Score;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.ImportExamCommand.addToErrorReport;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

public class ImportExamCommandTest {

    public static final String MIDTERM_NOT_FOUND_ERROR = "\nBelow are the errors that occurred while importing exams:\n" +
            "Midterm: Exam not found\n";
    public static final String CREATE_MIDTERM_100 = " n/Midterm s/100";
    public static final String VALID_PATH = "src/test/data/ImportExamCommandTest/testimportexam.csv";
    private ImportExamCommand importExamCommand;
    private Model model;

    @BeforeEach
    public void setUp() {
        model = mock(Model.class);
        StringBuilder errorReport = new StringBuilder();
        model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        UniqueExamList examList = new UniqueExamList();
        examList.add(new Exam("Midterm", new Score(100)));
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
        addToErrorReport("Email", "Invalid email format");
        assertEquals("\nBelow are the errors that occurred while importing exams:\nEmail: Invalid email format\n", ImportExamCommand.generateErrorReport());
    }

    @Test
    public void testGenerateErrorReportEmpty() {
        String result = ImportExamCommand.generateErrorReport();
        assertEquals("", result);
    }

    @Test
    public void testSuccess() throws CommandException, ParseException {
        Path filePath = Paths.get(VALID_PATH);
        AddExamCommand addExamCommand = new AddExamCommandParser().parse(CREATE_MIDTERM_100);
        addExamCommand.execute(model);
        ImportExamCommand importExamCommand = new ImportExamCommand(filePath);
        importExamCommand.execute(model);
        assertCommandSuccess(importExamCommand, model, String.format(ImportExamCommand.MESSAGE_SUCCESS, filePath), model);
    }

    @Test
    public void test_ExamNotExist_successWithErrorReport() throws CommandException {
        Path filePath = Paths.get(VALID_PATH);
        ImportExamCommand importExamCommand = new ImportExamCommand(filePath);
        String expectedMessage = String.format(ImportExamCommand.MESSAGE_SUCCESS, filePath);
        assertCommandSuccess(importExamCommand, model, expectedMessage + MIDTERM_NOT_FOUND_ERROR, model);
    }

}
