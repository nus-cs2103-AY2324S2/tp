package seedu.address.commons.core;

import java.util.concurrent.CountDownLatch;
import javax.swing.SwingUtilities;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;

class CommandHistoryTest {

    private CommandHistory commandHistory;


    private static void initJavaFX() {
        // Initialize JavaFX toolkit if not already initialized
        if (Platform.isFxApplicationThread()) {
            return;
        }

        final CountDownLatch latch = new CountDownLatch(1);
        SwingUtilities.invokeLater(() -> {
            new JFXPanel(); // Initializes the toolkit
            latch.countDown();
        });

        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    @BeforeEach
    void setUp() {
        commandHistory = new CommandHistory();
        initJavaFX();
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
    public void undo_undoMoreThanHistoryLength_returnsCurrentCommand() {
        commandHistory.addCommandToHistory("test1");
        commandHistory.undo();
        commandHistory.undo();
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
    void undo_undoAddOnEmptyList_returnsPrevCommand() {
        commandHistory.undo();
        commandHistory.addCommandToHistory("test1");
        commandHistory.undo();

        Assertions.assertEquals("test1", commandHistory.getCurrentCommand());
    }

    @Test
    void redo_undoUndoRedo_returnsNextCommand() {
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

        redo_undoUndoRedo_returnsNextCommand();
    }
}
