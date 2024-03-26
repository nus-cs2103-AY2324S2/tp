package seedu.address.logic.commands;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_REMARK_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_SCHEDULE_AMY;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Person;
import seedu.address.model.person.Remark;
import seedu.address.model.person.Schedule;
import seedu.address.testutil.PersonBuilder;

/**
 * Contains integration tests (interaction with the Model) and unit tests for ScheduleCommand.
 */
public class ScheduleCommandTest {

    private static final String SCHEDULE_STUB = "12-12-2012";

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void equals() {
        Person person = new PersonBuilder().build();
        final ScheduleCommand standardCommand =
                new ScheduleCommand(person.getNusId(), person.getSchedule(), person.getRemark());
        // same values -> returns true
        ScheduleCommand commandWithSameValues =
                new ScheduleCommand(person.getNusId(), person.getSchedule(), person.getRemark());
        assertTrue(standardCommand.equals(commandWithSameValues));
        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));
        // null -> returns false
        assertFalse(standardCommand.equals(null));
        // different types -> returns false
        assertFalse(standardCommand.equals(new ClearCommand()));
        // different schedule -> returns false
        assertFalse(standardCommand.equals(new ScheduleCommand(person.getNusId(),
                new Schedule(VALID_SCHEDULE_AMY), person.getRemark())));
        // different remark -> returns false
        assertFalse(standardCommand.equals(new ScheduleCommand(person.getNusId(),
                person.getSchedule(), new Remark(VALID_REMARK_AMY))));
    }
}
