package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalPersons.ALICE;
import static seedu.address.testutil.TypicalPersons.BENSON;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;

public class FindMeetingCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_findMeetings_success() {
        expectedModel.updateFilteredPersonList(person -> !person.getMeeting().toString().isEmpty());
        CommandResult expectedCommandResult = new CommandResult(
                FindMeetingCommand.MESSAGE_SUCCESS);
        assertCommandSuccess(new FindMeetingCommand(), model, expectedCommandResult, expectedModel);
        assertEquals(Arrays.asList(ALICE, BENSON), model.getFilteredPersonList());
    }

    @Test
    public void execute_findMeetings_noContactsFound() {
        model.deletePerson(ALICE);
        model.deletePerson(BENSON);
        expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.updateFilteredPersonList(person -> !person.getMeeting().toString().isEmpty());
        CommandResult expectedCommandResult = new CommandResult(
                FindMeetingCommand.MESSAGE_NO_CONTACTS_FOUND);
        assertCommandSuccess(new FindMeetingCommand(), model, expectedCommandResult, expectedModel);
        assertEquals(Arrays.asList(), model.getFilteredPersonList());
    }
}
