package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class CommandResultTest {
    public static final String DEFAULT_FEEDBACK_MESSAGE = "feedback";
    public static final String DIFFERENT_FEEDBACK_MESSAGE = "different";
    @Test
    public void equals() {
        CommandResult commandResult = new CommandResult(DEFAULT_FEEDBACK_MESSAGE);

        // same values -> returns true
        assertTrue(commandResult.equals(new CommandResult(DEFAULT_FEEDBACK_MESSAGE)));
        assertTrue(commandResult.equals(new CommandResult(DEFAULT_FEEDBACK_MESSAGE, false, false, false)));

        // same object -> returns true
        assertTrue(commandResult.equals(commandResult));

        // null -> returns false
        assertFalse(commandResult.equals(null));

        // different types -> returns false
        assertFalse(commandResult.equals(0.5f));

        // different feedbackToUser value -> returns false
        assertFalse(commandResult.equals(new CommandResult(DIFFERENT_FEEDBACK_MESSAGE)));

        // different confirmation value -> return false
        assertFalse(commandResult.equals(new CommandResult(DEFAULT_FEEDBACK_MESSAGE, true, false, false)));

        // different showHelp value -> returns false
        assertFalse(commandResult.equals(new CommandResult(DEFAULT_FEEDBACK_MESSAGE, false, true, false)));

        // different exit value -> returns false
        assertFalse(commandResult.equals(new CommandResult(DEFAULT_FEEDBACK_MESSAGE, false, false, true)));
    }

    @Test
    public void hashcode() {
        CommandResult commandResult = new CommandResult(DEFAULT_FEEDBACK_MESSAGE);

        // same values -> returns same hashcode
        assertEquals(commandResult.hashCode(), new CommandResult(DEFAULT_FEEDBACK_MESSAGE).hashCode());

        // different feedbackToUser value -> returns different hashcode
        assertNotEquals(commandResult.hashCode(), new CommandResult(DIFFERENT_FEEDBACK_MESSAGE).hashCode());

        // different showHelp value -> returns different hashcode
        assertNotEquals(commandResult.hashCode(), new CommandResult(DEFAULT_FEEDBACK_MESSAGE, false,
                true, false).hashCode());

        // different exit value -> returns different hashcode
        assertNotEquals(commandResult.hashCode(), new CommandResult(DEFAULT_FEEDBACK_MESSAGE, false,
                false, true).hashCode());
    }

    @Test
    public void toStringMethod() {
        CommandResult commandResult = new CommandResult(DEFAULT_FEEDBACK_MESSAGE);
        String expected = CommandResult.class.getCanonicalName() + "{feedbackToUser="
                + commandResult.getFeedbackToUser() + ", confirmation=" + commandResult.isConfirmation()
                + ", showHelp=" + commandResult.isShowHelp() + ", exit=" + commandResult.isExit() + "}";
        assertEquals(expected, commandResult.toString());
    }
}
