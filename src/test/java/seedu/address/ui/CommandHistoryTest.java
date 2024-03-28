package seedu.address.ui;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class CommandHistoryTest {

    private CommandHistory initializeCommandHistory(int currentPointer) {
        CommandHistory commandHistory = new CommandHistory();
        commandHistory.addCommand("command1");
        commandHistory.addCommand("command2");
        commandHistory.addCommand("command3");
        commandHistory.saveDraftCommand("draftCommand1");
        for (int i = 3; i > currentPointer; i--) {
            commandHistory.getPreviousCommand();
        }
        return commandHistory;
    }

    @Test
    public void addCommand_oneCommand_success() {
        CommandHistory commandHistory = new CommandHistory();
        commandHistory.addCommand("command1");
        assertEquals("command1", commandHistory.getPreviousCommand());
    }

    @Test
    public void addCommand_multipleCommands_success() {
        CommandHistory commandHistory = initializeCommandHistory(3);
        assertEquals("command3", commandHistory.getPreviousCommand());
        assertEquals("command2", commandHistory.getPreviousCommand());
        assertEquals("command1", commandHistory.getPreviousCommand());
    }

    @Test
    public void addCommand_exceedsMaxSize_success() {
        CommandHistory commandHistory = new CommandHistory();
        for (int i = 0; i < 101; i++) {
            commandHistory.addCommand("command" + i);
        }
        assertEquals("command100", commandHistory.getPreviousCommand());
    }

    @Test
    public void saveDraftCommand_oneCommand_success() {
        CommandHistory commandHistory = new CommandHistory();
        commandHistory.saveDraftCommand("draftCommand1");
        assertEquals("draftCommand1", commandHistory.getPreviousCommand());
    }

    @Test
    public void saveDraftCommand_multipleCommands_savesOnlyLatest() {
        CommandHistory commandHistory = new CommandHistory();
        commandHistory.saveDraftCommand("draftCommand1");
        commandHistory.saveDraftCommand("draftCommand2");
        commandHistory.saveDraftCommand("draftCommand3");
        assertEquals("draftCommand3", commandHistory.getPreviousCommand());
        assertEquals("draftCommand3", commandHistory.getPreviousCommand());
    }

    @Test
    public void getPreviousCommand_noCommands_returnsEmptyString() {
        CommandHistory commandHistory = new CommandHistory();
        assertEquals("", commandHistory.getPreviousCommand());
    }

    @Test
    public void getNextCommand_noCommands_returnsEmptyString() {
        CommandHistory commandHistory = new CommandHistory();
        assertEquals("", commandHistory.getNextCommand());
    }

    @Test
    public void getPreviousCommand_multipleCommands_success() {
        CommandHistory commandHistory = initializeCommandHistory(3);
        assertEquals("command3", commandHistory.getPreviousCommand());
        assertEquals("command2", commandHistory.getPreviousCommand());
        assertEquals("command1", commandHistory.getPreviousCommand());
    }

    @Test
    public void getPreviousCommand_calledManyTimes_wrapsAround() {
        CommandHistory commandHistory = initializeCommandHistory(0);
        assertEquals("draftCommand1", commandHistory.getPreviousCommand());
        assertEquals("command3", commandHistory.getPreviousCommand());
    }

    @Test
    public void getNextCommand_multipleCommands_success() {
        CommandHistory commandHistory = initializeCommandHistory(0);
        assertEquals("command2", commandHistory.getNextCommand());
        assertEquals("command3", commandHistory.getNextCommand());
    }

    @Test
    public void getNextCommand_calledManyTimes_doesNotWrapAround() {
        CommandHistory commandHistory = initializeCommandHistory(0);
        commandHistory.getNextCommand();
        commandHistory.getNextCommand();
        commandHistory.getNextCommand();
        assertEquals("draftCommand1", commandHistory.getNextCommand());
        assertEquals("draftCommand1", commandHistory.getNextCommand());
    }

    @Test
    public void isCommandEdited_sameDraftCommand_returnsFalse() {
        CommandHistory commandHistory = new CommandHistory();
        commandHistory.saveDraftCommand("command1");
        assertFalse(commandHistory.isCommandEdited("command1"));
    }

    @Test
    public void isCommandEdited_differentDraftCommand_returnsTrue() {
        CommandHistory commandHistory = new CommandHistory();
        commandHistory.saveDraftCommand("command1");
        assertTrue(commandHistory.isCommandEdited("command2"));
    }

    @Test
    public void isCommandEdited_newlyDraftedCommand_returnsTrue() {
        CommandHistory commandHistory = new CommandHistory();
        assertTrue(commandHistory.isCommandEdited("command1"));
    }

    @Test
    public void isCommandEdited_cleanState_returnsFalse() {
        CommandHistory commandHistory = new CommandHistory();
        assertFalse(commandHistory.isCommandEdited(""));
    }

    @Test
    public void isCommandEdited_sameOldCommand_returnsFalse() {
        CommandHistory commandHistory = new CommandHistory();
        commandHistory.addCommand("command1");
        commandHistory.getPreviousCommand();
        assertFalse(commandHistory.isCommandEdited("command1"));
    }

    @Test
    public void isCommandEdited_differentOldCommand_returnsTrue() {
        CommandHistory commandHistory = new CommandHistory();
        commandHistory.addCommand("command1");
        commandHistory.getPreviousCommand();
        assertTrue(commandHistory.isCommandEdited("command2"));
    }
}
