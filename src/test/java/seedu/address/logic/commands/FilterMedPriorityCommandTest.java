package seedu.address.logic.commands;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalPersons.BENSON;
import static seedu.address.testutil.TypicalPersons.DANIEL;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
public class FilterMedPriorityCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_filterMedPriority_success() {

        expectedModel.updateFilteredPersonList(person -> person.getPriority().value.equals("med"));
        CommandResult expectedCommandResult = new CommandResult(
                FilterMedPriorityCommand.MESSAGE_SUCCESS);
        assertCommandSuccess(new FilterMedPriorityCommand(), model, expectedCommandResult, expectedModel);
        assertEquals(Arrays.asList(BENSON, DANIEL), model.getFilteredPersonList());
    }

    @Test
    public void execute_filterMedPriority_noContactsFound() {
        model.deletePerson(BENSON);
        model.deletePerson(DANIEL);
        expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());

        expectedModel.updateFilteredPersonList(person -> person.getPriority().value.equals("med"));
        CommandResult expectedCommandResult = new CommandResult(
                FilterMedPriorityCommand.MESSAGE_NO_CONTACTS_FOUND);
        assertCommandSuccess(new FilterMedPriorityCommand(), model, expectedCommandResult, expectedModel);
        assertEquals(Arrays.asList(), model.getFilteredPersonList());
    }
}
