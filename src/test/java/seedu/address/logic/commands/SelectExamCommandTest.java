package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

// import java.util.Collections;
// import java.util.List;
import org.junit.jupiter.api.Test;

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

        SelectExamCommand command = new SelectExamCommand("Midterm");

        CommandResult result = command.execute(model);

        assertEquals(String.format(SelectExamCommand.MESSAGE_SUCCESS, exam), result.getFeedbackToUser());
    }

    @Test
    public void execute_examDoesNotExist_throwsCommandException() {
        AddressBook addressBook = new AddressBook();
        Exam exam = new Exam("Midterm", new Score(100));
        addressBook.addExam(exam);
        UserPrefs userPrefs = new UserPrefs();
        Model model = new ModelManager(addressBook, userPrefs);

        SelectExamCommand command = new SelectExamCommand("Final");

        assertThrows(CommandException.class, () -> command.execute(model));
    }

    @Test
    public void equals_sameValues_returnsTrue() {
        SelectExamCommand command1 = new SelectExamCommand("Midterm");
        SelectExamCommand command2 = new SelectExamCommand("Midterm");

        assertEquals(command1, command2);
    }

    @Test
    public void equals_differentValues_returnsFalse() {
        SelectExamCommand command1 = new SelectExamCommand("Midterm");
        SelectExamCommand command2 = new SelectExamCommand("Final");

        assertNotEquals(command1, command2);
    }

}
