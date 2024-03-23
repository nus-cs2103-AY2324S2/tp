package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalPersons.ALICE;
import static seedu.address.testutil.TypicalPersons.CARL;
import static seedu.address.testutil.TypicalPersons.ELLE;
import static seedu.address.testutil.TypicalPersons.FIONA;
import static seedu.address.testutil.TypicalPersons.GEORGE;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;

public class FilterHighPriorityCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_filterHighPriority_success() {
        expectedModel.updateFilteredPersonList(person -> person.getPriority().value.equals("high"));
        CommandResult expectedCommandResult = new CommandResult(
                FilterHighPriorityCommand.MESSAGE_SUCCESS);
        assertCommandSuccess(new FilterHighPriorityCommand(), model, expectedCommandResult, expectedModel);
        assertEquals(Arrays.asList(ALICE, CARL, ELLE, FIONA, GEORGE), model.getFilteredPersonList());
    }

    @Test
    public void execute_filterHighPriority_noContactsFound() {
        model.deletePerson(ALICE);
        model.deletePerson(CARL);
        model.deletePerson(ELLE);
        model.deletePerson(FIONA);
        model.deletePerson(GEORGE);
        expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.updateFilteredPersonList(person -> person.getPriority().value.equals("high"));
        CommandResult expectedCommandResult = new CommandResult(
                FilterHighPriorityCommand.MESSAGE_NO_CONTACTS_FOUND);
        assertCommandSuccess(new FilterHighPriorityCommand(), model, expectedCommandResult, expectedModel);
        assertEquals(Arrays.asList(), model.getFilteredPersonList());
    }
}
