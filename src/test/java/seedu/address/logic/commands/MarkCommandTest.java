package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Person;

public class MarkCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void equals() {
        Index index = Index.fromOneBased(1);
        MarkCommand markCommand = new MarkCommand(index);
        MarkCommand sameMarkCommand = new MarkCommand(index);

        assertTrue(markCommand.equals(markCommand));
        assertTrue(markCommand.equals(sameMarkCommand));
        assertFalse(markCommand.equals(null));
        assertFalse(markCommand.equals(new Object()));
        assertFalse(markCommand.equals(new MarkCommand(Index.fromOneBased(2))));
    }

    @Test
    public void execute_invalidIndex_throwsCommandException() {
        Index invalidIndex = Index.fromOneBased(model.getFilteredPersonList().size() + 1);

        MarkCommand markCommand = new MarkCommand(invalidIndex);

        assertThrows(CommandException.class, () -> markCommand.execute(model),
                Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    @Test
    public void execute_alreadyDoneSchedule_throwsCommandException() {
        Index validIndex = Index.fromOneBased(1);
        Person personWithDoneSchedule = model.getFilteredPersonList().get(0);
        personWithDoneSchedule.getSchedule().markIsDone();

        MarkCommand markCommand = new MarkCommand(validIndex);

        assertThrows(CommandException.class, () -> markCommand.execute(model),
                Messages.MESSAGE_SCHEDULE_DONE);
    }

    @Test
    public void toString_validCommand_returnsExpectedString() {
        Index index = Index.fromOneBased(1);
        MarkCommand markCommand = new MarkCommand(index);
        String expected = MarkCommand.class.getCanonicalName() + "{index=" + index + "}";
        assertEquals(expected, markCommand.toString());
    }
}
