package seedu.address.commons.core;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


/**
 * Note: This test is unable to cover undoing at the start and end of the lists due to the
 * undo() function playing audio, which requires a JavaFX application running
 */
class CommandHistoryTest {

    private CommandHistory commandHistory;

    @BeforeEach
    void setUp() {
        commandHistory = new CommandHistory();
    }
    @Test
    void undo_undoOnce_returnsPrevCommand() {
        commandHistory.addCommandToHistory("test1");
        commandHistory.undo();
        Assertions.assertEquals("test1", commandHistory.getCurrentCommand());
    }

    @Test
    void undo_undoConsecutively_returnsPrevCommand() {
        commandHistory.addCommandToHistory("test1");
        commandHistory.addCommandToHistory("test2");
        commandHistory.undo();
        Assertions.assertEquals("test2", commandHistory.getCurrentCommand());
        commandHistory.undo();
        Assertions.assertEquals("test1", commandHistory.getCurrentCommand());
    }

    @Test
    void undo_undoAddThenUndo_returnsPrevCommand() {
        commandHistory.addCommandToHistory("test1");
        commandHistory.addCommandToHistory("test2");
        commandHistory.addCommandToHistory("test3");
        commandHistory.undo();

        Assertions.assertEquals("test3", commandHistory.getCurrentCommand());

        commandHistory.addCommandToHistory("test4");
        commandHistory.undo();

        Assertions.assertEquals("test4", commandHistory.getCurrentCommand());
    }

    @Test
    void redo_addAddUndoUndoRedo_returnsNextCommand() {
        commandHistory.addCommandToHistory("test1");
        commandHistory.addCommandToHistory("test2");

        commandHistory.undo();
        Assertions.assertEquals("test2", commandHistory.getCurrentCommand());

        commandHistory.undo();
        Assertions.assertEquals("test1", commandHistory.getCurrentCommand());

        commandHistory.redo();
        Assertions.assertEquals("test2", commandHistory.getCurrentCommand());
    }

    @Test
    void redo_redoAtEndOfHistory_returnsEmptyString() {
        commandHistory.addCommandToHistory("test1");
        commandHistory.undo();
        Assertions.assertEquals("test1", commandHistory.getCurrentCommand());

        commandHistory.redo();
        Assertions.assertEquals("", commandHistory.getCurrentCommand());
    }

    @Test
    void redo_redoOnEmptyListBeforeAddUndo_returnsPrevCommand() {
        commandHistory.redo();
        commandHistory.addCommandToHistory("test1");
        commandHistory.undo();

        Assertions.assertEquals("test1", commandHistory.getCurrentCommand());

        commandHistory.addCommandToHistory("test2");
        commandHistory.undo();
        commandHistory.undo();
        Assertions.assertEquals("test1", commandHistory.getCurrentCommand());
        commandHistory.redo();
        Assertions.assertEquals("test2", commandHistory.getCurrentCommand());
        commandHistory.redo();
        Assertions.assertEquals("", commandHistory.getCurrentCommand());
    }
}
