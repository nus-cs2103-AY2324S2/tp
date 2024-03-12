package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.LastMetCommand.MESSAGE_SUCCESS;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.LastMet;

public class LastMetCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void equals() {
        Index index = Index.fromOneBased(1);
        LastMetCommand lastMetCommand = new LastMetCommand(index, new LastMet(LocalDate.now()));
        LastMetCommand sameMetCommand = new LastMetCommand(index, new LastMet(LocalDate.now()));

        assertTrue(lastMetCommand.equals(lastMetCommand));
        assertTrue(lastMetCommand.equals(sameMetCommand));
        assertFalse(lastMetCommand.equals(null));
        assertFalse(lastMetCommand.equals(new Object()));
        assertFalse(lastMetCommand.equals(new LastMetCommand(Index.fromOneBased(2), new LastMet(LocalDate.now()))));
    }

    @Test
    public void execute_validIndexAndDate_success() {
        Index validIndex = Index.fromOneBased(1);
        LocalDate validDate = LocalDate.now().minusDays(1); // Set a valid date (1 day ago)

        LastMetCommand lastMetCommand = new LastMetCommand(validIndex, new LastMet(validDate));

        CommandTestUtil.assertCommandSuccess(lastMetCommand, model,
                String.format(MESSAGE_SUCCESS, model.getFilteredPersonList().get(0).getName()), expectedModel);
    }

    @Test
    public void execute_invalidIndex_throwsCommandException() {
        Index invalidIndex = Index.fromOneBased(model.getFilteredPersonList().size() + 1);
        LocalDate validDate = LocalDate.now().minusDays(1);

        LastMetCommand lastMetCommand = new LastMetCommand(invalidIndex, new LastMet(validDate));

        assertThrows(CommandException.class, () -> lastMetCommand.execute(model),
                Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    @Test
    public void execute_futureDate_throwsCommandException() {
        Index validIndex = Index.fromOneBased(1);
        LocalDate futureDate = LocalDate.now().plusDays(1);

        LastMetCommand lastMetCommand = new LastMetCommand(validIndex, new LastMet(futureDate));

        assertThrows(CommandException.class, () -> lastMetCommand.execute(model),
                Messages.MESSAGE_LASTMET_FUTURE);
    }

    @Test
    public void toString_validCommand_returnsExpectedString() {
        Index index = Index.fromOneBased(1);
        LocalDate date = LocalDate.now();
        LastMetCommand lastMetCommand = new LastMetCommand(index, new LastMet(date));
        String expected = LastMetCommand.class.getCanonicalName() + "{index=" + index + ", date=" + date + "}";
        assertEquals(expected, lastMetCommand.toString());
    }
}

