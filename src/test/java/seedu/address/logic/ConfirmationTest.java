package seedu.address.logic;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.TypicalPersons.ALICE;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.AddCommand;
import seedu.address.logic.commands.DeleteCommand;
import seedu.address.logic.commands.ListCommand;

public class ConfirmationTest {
    public static final int RANDOM_INDEX = 1;
    @Test
    public void assertNoConfirmation() {
        Confirmation confirmation;

        confirmation = new Confirmation(new AddCommand(ALICE));
        assertTrue(confirmation.isToProceed());

        // different command -> return true
        confirmation = new Confirmation((new ListCommand()));
        assertTrue(confirmation.isToProceed());
    }

    @Test
    public void assertConfirmation() {
        Confirmation confirmation = new Confirmation(new DeleteCommand(Index.fromOneBased(RANDOM_INDEX)));
        assertFalse(confirmation.isToProceed());
    }
}
