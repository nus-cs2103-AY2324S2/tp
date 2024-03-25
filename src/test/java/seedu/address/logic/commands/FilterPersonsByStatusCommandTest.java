package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.ApplicantStatus;
import seedu.address.model.person.enums.ApplicantState;

public class FilterPersonsByStatusCommandTest {
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    private ApplicantStatus firstTargetStatus = new ApplicantStatus(ApplicantState.STAGE_TWO.toString());
    private ApplicantStatus secondTargetStatus = new ApplicantStatus(ApplicantState.STAGE_ONE.toString());

    @Test
    public void equals() {
        FilterPersonsByStatusCommand filterPersonsByStatusFirstCommand =
                new FilterPersonsByStatusCommand(firstTargetStatus);
        FilterPersonsByStatusCommand filterInterviewsByDateSecondCommand =
                new FilterPersonsByStatusCommand(secondTargetStatus);

        // same object -> returns true
        assertEquals(filterPersonsByStatusFirstCommand, filterPersonsByStatusFirstCommand);

        // same values -> returns true
        FilterPersonsByStatusCommand filterPersonsByStatusCommandCopy =
                new FilterPersonsByStatusCommand(firstTargetStatus);
        assertEquals(filterPersonsByStatusFirstCommand, filterPersonsByStatusCommandCopy);

        // different types -> returns false
        assertNotEquals(1, filterPersonsByStatusFirstCommand);

        // null -> returns false
        assertNotEquals(null, filterPersonsByStatusFirstCommand);

        // different person -> returns false
        assertNotEquals(filterPersonsByStatusFirstCommand, filterInterviewsByDateSecondCommand);
    }

    @Test
    public void execute_existingStatus_success() {
        String expectedMessage = CommandResult.class.getCanonicalName() + "{feedbackToUser="
            + "Listed all persons with status: " + secondTargetStatus + ", showHelp=false, exit=false}";
        FilterPersonsByStatusCommand command = new FilterPersonsByStatusCommand(secondTargetStatus);
        assertEquals(expectedMessage, command.execute(model).toString());
    }

    @Test
    public void execute_nonExistentStatus_noPersonFound() {
        String expectedMessage = CommandResult.class.getCanonicalName() + "{feedbackToUser="
                + "No persons found with status: " + firstTargetStatus + ", showHelp=false, exit=false}";
        FilterPersonsByStatusCommand command = new FilterPersonsByStatusCommand(firstTargetStatus);
        assertEquals(expectedMessage, command.execute(model).toString());
    }

    @Test
    public void toStringMethod() {
        FilterPersonsByStatusCommand filterPersonsByStatusCommand =
                new FilterPersonsByStatusCommand(firstTargetStatus);
        String expected = FilterPersonsByStatusCommand.class.getCanonicalName() + "{targetStatus=" + firstTargetStatus
                + "}";
        assertEquals(expected, filterPersonsByStatusCommand.toString());
    }
}
