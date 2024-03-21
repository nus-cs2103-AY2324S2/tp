package seedu.address.commons.core;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CommandHistoryTest {

    private CommandHistory commandHistory;
    @BeforeEach
    void setUp() {
        commandHistory = new CommandHistory();
    }
    @Test
    void addCommandToHistory() {
        commandHistory.addCommandToHistory("Test");
        Assertions.assertEquals("Test", commandHistory.undo());
    }

    @Test
    void undo_undoConsecutively_returnsPrevCommand() {
        commandHistory.addCommandToHistory("test1");
        commandHistory.addCommandToHistory("test2");
        commandHistory.addCommandToHistory("test3");
        Assertions.assertEquals("test3", commandHistory.undo());
        commandHistory.undo();
        Assertions.assertEquals("test1", commandHistory.undo());
    }

    @Test
    void undo_undoAndAddAndUndoAgain_returnsPrevCommand() {
        commandHistory.addCommandToHistory("test1");
        commandHistory.addCommandToHistory("test2");
        commandHistory.addCommandToHistory("test3");
        Assertions.assertEquals("test3", commandHistory.undo());
        commandHistory.undo();
        commandHistory.addCommandToHistory("test4");
        Assertions.assertEquals("test4", commandHistory.undo());
    }

    @Test
    void redo_undoAndRedo_returnsNextCommand() {
        commandHistory.addCommandToHistory("test1");
        commandHistory.addCommandToHistory("test2");
        commandHistory.addCommandToHistory("test3");
        Assertions.assertEquals("test3", commandHistory.undo());
        Assertions.assertEquals("test3", commandHistory.redo());
    }

    @Test
    void redo_redoAtEndOfHistory_returnsEmptyString() {
        commandHistory.addCommandToHistory("test1");
        commandHistory.addCommandToHistory("test2");
        commandHistory.addCommandToHistory("test3");
        Assertions.assertEquals("", commandHistory.redo());
    }
}
