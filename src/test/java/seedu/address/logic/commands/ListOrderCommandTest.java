package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;

public class ListOrderCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_validIndex_success() throws CommandException {
        Index validIndex = Index.fromOneBased(1);
        ListOrderCommand listOrderCommand = new ListOrderCommand(validIndex);
        CommandResult commandResult = listOrderCommand.execute(model);

        String expectedMessageStart = "Orders for the selected person:\n";
        assertTrue(commandResult.getFeedbackToUser().startsWith(expectedMessageStart));
    }

    @Test
    public void execute_validIndexNoOrders_success() throws CommandException {
        ListOrderCommand listOrderCommand = new ListOrderCommand(Index.fromOneBased(2));
        CommandResult commandResult = listOrderCommand.execute(model);

        String expectedMessage = "Orders for the selected person:\n";
        assertEquals(expectedMessage, commandResult.getFeedbackToUser());
    }

    @Test
    public void execute_invalidIndex_throwsCommandException() {
        ListOrderCommand listOrderCommand = new ListOrderCommand(Index.fromOneBased(999));
        assertThrows(CommandException.class, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX, () ->
                listOrderCommand.execute(model));
    }
}


