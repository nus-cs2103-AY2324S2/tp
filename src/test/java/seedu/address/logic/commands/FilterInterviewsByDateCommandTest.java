package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;

public class FilterInterviewsByDateCommandTest {
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    private LocalDate firstTargetDate = LocalDate.parse("2001-03-22");
    private LocalDate secondTargetDate = LocalDate.parse("2001-04-17");

    @Test
    public void equals() {
        FilterInterviewsByDateCommand filterInterviewsByDateFirstCommand =
                new FilterInterviewsByDateCommand(firstTargetDate);
        FilterInterviewsByDateCommand filterInterviewsByDateSecondCommand =
                new FilterInterviewsByDateCommand(secondTargetDate);

        // same object -> returns true
        assertEquals(filterInterviewsByDateFirstCommand, filterInterviewsByDateFirstCommand);

        // same values -> returns true
        FilterInterviewsByDateCommand filterInterviewsByDateFirstCommandCopy =
                new FilterInterviewsByDateCommand(firstTargetDate);
        assertEquals(filterInterviewsByDateFirstCommand, filterInterviewsByDateFirstCommandCopy);

        // different types -> returns false
        assertNotEquals(1, filterInterviewsByDateFirstCommand);

        // null -> returns false
        assertNotEquals(null, filterInterviewsByDateFirstCommand);

        // different person -> returns false
        assertNotEquals(filterInterviewsByDateFirstCommand, filterInterviewsByDateSecondCommand);
    }

    @Test
    public void execute_nonExistentDate_noInterviewFound() {
        String expectedMessage = "No interviews found on " + firstTargetDate;
        FilterInterviewsByDateCommand command = new FilterInterviewsByDateCommand(firstTargetDate);
        command.execute(model);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
    }

    @Test
    public void toStringMethod() {
        FilterInterviewsByDateCommand filterInterviewsByDateCommand =
                new FilterInterviewsByDateCommand(firstTargetDate);
        String expected = FilterInterviewsByDateCommand.class.getCanonicalName() + "{targetDate=" + firstTargetDate
                + "}";
        assertEquals(expected, filterInterviewsByDateCommand.toString());
    }
}
