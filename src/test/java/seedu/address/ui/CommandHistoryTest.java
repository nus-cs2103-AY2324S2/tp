package seedu.address.ui;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CommandHistoryTest {

    @BeforeEach
    public void setUp() {
        CommandHistory commandHistory = CommandHistory.getInstance();
        commandHistory.clearCommandHistory();
    }

    @Test
    void clearCommandHistory_success() {
        String initialCommand = "addstu nn/e0123456 n/John Doe p/98765432";
        CommandHistory commandHistory = CommandHistory.getInstance();
        commandHistory.appendCommand(initialCommand);
        commandHistory.clearCommandHistory();
        assertEquals("", commandHistory.getCommandHistory("UP"));
        assertEquals("", commandHistory.getCommandHistory("DOWN"));
    }

    @Test
    void appendCommand_success() {
        String initialCommand = "addstu nn/e0123456 n/John Doe p/98765432";
        CommandHistory commandHistory = CommandHistory.getInstance();
        commandHistory.clearCommandHistory();
        commandHistory.appendCommand(initialCommand);
    }

    @Test
    void getCommandHistory_success() {
        String initialCommand = "addstu nn/e0123456 n/John Doe p/98765432";
        CommandHistory commandHistory = CommandHistory.getInstance();
        commandHistory.clearCommandHistory();
        commandHistory.appendCommand(initialCommand);
        assertEquals(initialCommand, commandHistory.getCommandHistory("UP"));
        assertEquals("", commandHistory.getCommandHistory("DOWN"));
        assertEquals(initialCommand, commandHistory.getCommandHistory("UP"));
    }

    @Test
    void getCommandHistory_rigorous_success() {
        String initialCommand = "addstu nn/e0123456 n/John Doe p/98765432";
        CommandHistory commandHistory = CommandHistory.getInstance();
        commandHistory.clearCommandHistory();
        commandHistory.appendCommand(initialCommand);
        assertEquals(initialCommand, commandHistory.getCommandHistory("UP"));
        assertEquals(initialCommand, commandHistory.getCommandHistory("UP"));
        assertEquals("", commandHistory.getCommandHistory("DOWN"));
        assertEquals("", commandHistory.getCommandHistory("DOWN"));
    }

    @Test
    void getCommandHistory_multipleCommand_success() {
        String initialCommand = "addstu nn/e0123456 n/John Doe p/98765432";
        String secondCommand = "delstu e0123457";
        String thirdCommand = "list";
        CommandHistory commandHistory = CommandHistory.getInstance();
        commandHistory.clearCommandHistory();
        commandHistory.appendCommand(initialCommand);
        commandHistory.appendCommand(secondCommand);
        commandHistory.appendCommand(thirdCommand);
        assertEquals(thirdCommand, commandHistory.getCommandHistory("UP"));
        assertEquals(secondCommand, commandHistory.getCommandHistory("UP"));
        assertEquals(thirdCommand, commandHistory.getCommandHistory("DOWN"));
        assertEquals(secondCommand, commandHistory.getCommandHistory("UP"));
        assertEquals(initialCommand, commandHistory.getCommandHistory("UP"));
        assertEquals(initialCommand, commandHistory.getCommandHistory("UP"));
        assertEquals(secondCommand, commandHistory.getCommandHistory("DOWN"));
    }

    @Test
    void getCommandHistory_noPreviousCommands_success() {
        CommandHistory commandHistory = CommandHistory.getInstance();
        commandHistory.clearCommandHistory();
        assertEquals("", commandHistory.getCommandHistory("UP"));
        assertEquals("", commandHistory.getCommandHistory("DOWN"));
    }

    @Test
    void appendCommand_nullCommand_success() {
        CommandHistory commandHistory = CommandHistory.getInstance();
        commandHistory.clearCommandHistory();
        commandHistory.appendCommand(null);
    }

}
