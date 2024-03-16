package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NOTE_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NOTE_BOB;

import org.junit.jupiter.api.Test;

import seedu.address.model.person.IdentityCardNumber;
import seedu.address.model.person.IdentityCardNumberMatchesPredicate;
import seedu.address.model.person.Note;

/**
 * Contains integration tests (interaction with the Model) and unit tests for AddNoteCommand.
 */
public class AddNoteCommandTest {
    @Test
    public void equals() {
        IdentityCardNumberMatchesPredicate firstPredicate =
                new IdentityCardNumberMatchesPredicate(new IdentityCardNumber("S1234567A"));
        IdentityCardNumberMatchesPredicate secondPredicate =
                new IdentityCardNumberMatchesPredicate(new IdentityCardNumber("S9876543B"));

        final AddNoteCommand standardCommand = new AddNoteCommand(firstPredicate,
                new Note(VALID_NOTE_AMY), false);

        // same values -> returns true
        AddNoteCommand commandWithSameValues = new AddNoteCommand(firstPredicate,
                new Note(VALID_NOTE_AMY), false);
        assertTrue(standardCommand.equals(commandWithSameValues));

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different types -> returns false
        assertFalse(standardCommand.equals(new ClearCommand()));

        // different index -> returns false
        assertFalse(standardCommand.equals(new AddNoteCommand(secondPredicate,
                new Note(VALID_NOTE_AMY), false)));

        // different remark -> returns false
        assertFalse(standardCommand.equals(new AddNoteCommand(firstPredicate,
                new Note(VALID_NOTE_BOB), false)));
    }
}
