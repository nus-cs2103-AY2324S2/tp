package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.exam.Exam;
import seedu.address.model.person.Score;

public class SelectExamCommandTest {

    @Test
    public void execute_examExists_selectsExam() throws CommandException {
        AddressBook addressBook = new AddressBook();
        Exam exam = new Exam("Midterm", new Score(100));
        addressBook.addExam(exam);
        UserPrefs userPrefs = new UserPrefs();
        Model model = new ModelManager(addressBook, userPrefs);

        SelectExamCommand command = new SelectExamCommand(Index.fromOneBased(1));

        CommandResult result = command.execute(model);

        assertEquals(String.format(SelectExamCommand.MESSAGE_SELECT_EXAM_SUCCESS, exam), result.getFeedbackToUser());
    }

    @Test
    public void execute_examDoesNotExist_throwsCommandException() {
        AddressBook addressBook = new AddressBook();
        Exam exam = new Exam("Midterm", new Score(100));
        addressBook.addExam(exam);
        UserPrefs userPrefs = new UserPrefs();
        Model model = new ModelManager(addressBook, userPrefs);

        SelectExamCommand command = new SelectExamCommand(Index.fromOneBased(2));

        assertThrows(CommandException.class, () -> command.execute(model));
    }

    @Test
    void equals() {
        SelectExamCommand selectExamCommand1 = new SelectExamCommand(Index.fromZeroBased(1));
        SelectExamCommand selectExamCommand2 = new SelectExamCommand(Index.fromZeroBased(1));
        SelectExamCommand selectExamCommand3 = new SelectExamCommand(Index.fromZeroBased(2));

        // same object -> returns true
        assertTrue(selectExamCommand1.equals(selectExamCommand1));

        // same values -> returns true
        assertTrue(selectExamCommand1.equals(selectExamCommand2));

        // different types -> returns false
        assertFalse(selectExamCommand1.equals(1));

        // null -> returns false
        assertFalse(selectExamCommand1.equals(null));

        // different index -> returns false
        assertFalse(selectExamCommand1.equals(selectExamCommand3));
    }

    @Test
    void toStringTest() {
        SelectExamCommand selectExamCommand = new SelectExamCommand(Index.fromZeroBased(1));
        assertEquals("seedu.address.logic.commands.SelectExamCommand{targetIndex="
                     + "seedu.address.commons.core.index.Index{zeroBasedIndex=1}}",
                     selectExamCommand.toString());
    }

}
