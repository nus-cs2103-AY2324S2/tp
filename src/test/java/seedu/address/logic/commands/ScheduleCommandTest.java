package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.ScheduleCommand.MESSAGE_SUCCESS;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Schedule;

public class ScheduleCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void equals() {
        Index index = Index.fromOneBased(1);
        ScheduleCommand scheduleCommand = new ScheduleCommand(index, new Schedule(LocalDateTime.now()));
        ScheduleCommand sameScheduleCommand = new ScheduleCommand(index, new Schedule(LocalDateTime.now()));

        assertTrue(scheduleCommand.equals(scheduleCommand));
        assertTrue(scheduleCommand.equals(sameScheduleCommand));
        assertFalse(scheduleCommand.equals(null));
        assertFalse(scheduleCommand.equals(new Object()));
        assertFalse(scheduleCommand.equals(new ScheduleCommand(Index.fromOneBased(2),
                new Schedule(LocalDateTime.now()))));
    }

    @Test
    public void execute_validIndexAndFutureDate_success() {
        Index validIndex = Index.fromOneBased(1);
        LocalDateTime validDateTime = LocalDateTime.now().plusDays(1); // Set a future date (1 day later)

        ScheduleCommand scheduleCommand = new ScheduleCommand(validIndex, new Schedule(validDateTime));

        CommandTestUtil.assertCommandSuccess(scheduleCommand, model,
                String.format(MESSAGE_SUCCESS, model.getFilteredPersonList().get(0).getName()), expectedModel);
    }

    @Test
    public void execute_invalidIndex_throwsCommandException() {
        Index invalidIndex = Index.fromOneBased(model.getFilteredPersonList().size() + 1);
        LocalDateTime validDateTime = LocalDateTime.now().plusDays(1);

        ScheduleCommand scheduleCommand = new ScheduleCommand(invalidIndex, new Schedule(validDateTime));

        assertThrows(CommandException.class, () -> scheduleCommand.execute(model),
                Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    @Test
    public void execute_pastDate_throwsCommandException() {
        Index validIndex = Index.fromOneBased(1);
        LocalDateTime pastDateTime = LocalDateTime.now().minusDays(1);

        ScheduleCommand scheduleCommand = new ScheduleCommand(validIndex, new Schedule(pastDateTime));

        assertThrows(CommandException.class, () -> scheduleCommand.execute(model),
                Messages.MESSAGE_SCHEDULE_PAST);
    }

    @Test
    public void toString_validCommand_returnsExpectedString() {
        Index index = Index.fromOneBased(1);
        LocalDateTime dateTime = LocalDateTime.now();
        ScheduleCommand scheduleCommand = new ScheduleCommand(index, new Schedule(dateTime));
        String expected = ScheduleCommand.class.getCanonicalName() + "{index=" + index + ", dateTime=" + dateTime + "}";
        assertEquals(expected, scheduleCommand.toString());
    }
}

