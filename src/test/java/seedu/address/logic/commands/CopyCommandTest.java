package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.Messages.MESSAGE_EMPTY_PERSON_LIST;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;

/**
 * Contains integration tests (interaction with the Model) for {@code CopyCommand}.
 */
public class CopyCommandTest {
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    private Model expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
    private Model emptyModel = new ModelManager(new AddressBook(), new UserPrefs());
    @Test
    public void equals() {
        CopyCommand copyCommand = new CopyCommand();

        // same object -> returns true
        assertTrue(copyCommand.equals(copyCommand));


        // different types -> returns false
        assertFalse(copyCommand.equals(1));

        // null -> returns false
        assertFalse(copyCommand.equals(null));
    }

    @Test
    public void execute_emptyList_noEmailsCopied() {
        assertCommandFailure(new CopyCommand(), emptyModel, MESSAGE_EMPTY_PERSON_LIST);
    }

    @Test
    public void execute_nonEmptyList_emailsCopied() {
        try {
            CommandResult result = new CopyCommand().execute(model);
            assertEquals(new CommandResult(CopyCommand.MESSAGE_SUCCESS), result);
            assertEquals(expectedModel, model);
        } catch (CommandException ce) {
            throw new AssertionError("Execution of command should not fail.", ce);
        }
    }
}
