package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.exam.Exam;
import seedu.address.model.person.Score;

public class DeselectExamCommandTest {

    private Model model;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(new AddressBook(), new UserPrefs());
    }

    @Test
    public void execute_examIsSelected_deselectsExam() throws CommandException {
        Exam exam = new Exam("Midterm", new Score(100));
        model.addExam(exam);
        model.selectExam(exam);

        DeselectExamCommand command = new DeselectExamCommand();

        CommandResult result = command.execute(model);

        assertNull(model.getSelectedExam().getValue());
        assertEquals(String.format(DeselectExamCommand.MESSAGE_SUCCESS, "Midterm"),
                     result.getFeedbackToUser());
    }

    @Test
    public void execute_noExamSelected_throwsCommandException() {
        DeselectExamCommand command = new DeselectExamCommand();

        assertThrows(CommandException.class, () -> command.execute(model));
    }
}
