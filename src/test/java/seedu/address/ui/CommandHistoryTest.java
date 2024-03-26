package seedu.address.ui;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CommandHistoryTest {

    @BeforeEach
    public void setUp() {
        CommandHistory.clearCommandHistory();
    }

    @Test
    void appendCommand_success() {
        String initialCommand = "addstu nn/e0123456 n/John Doe p/98765432";
        CommandHistory.appendCommand(initialCommand);
    }

    @Test
    void getCommandHistory_success() {
        String initialCommand = "addstu nn/e0123456 n/John Doe p/98765432";
        CommandHistory.appendCommand(initialCommand);
        assertEquals(initialCommand, CommandHistory.getCommandHistory("up"));
        assertEquals("", CommandHistory.getCommandHistory("down"));
        assertEquals(initialCommand, CommandHistory.getCommandHistory("up"));
    }

    @Test
    void getCommandHistory_rigorous_success() {
        String initialCommand = "addstu nn/e0123456 n/John Doe p/98765432";
        CommandHistory.appendCommand(initialCommand);
        assertEquals(initialCommand, CommandHistory.getCommandHistory("up"));
        assertEquals(initialCommand, CommandHistory.getCommandHistory("up"));
        assertEquals("", CommandHistory.getCommandHistory("down"));
        assertEquals("", CommandHistory.getCommandHistory("down"));
    }

    @Test
    void getCommandHistory_multipleCommand_success() {
        String initialCommand = "addstu nn/e0123456 n/John Doe p/98765432";
        String secondCommand = "delstu e0123457";
        String thirdCommand = "list";
        CommandHistory.appendCommand(initialCommand);
        CommandHistory.appendCommand(secondCommand);
        CommandHistory.appendCommand(thirdCommand);
        assertEquals(thirdCommand, CommandHistory.getCommandHistory("up"));
        assertEquals(secondCommand, CommandHistory.getCommandHistory("up"));
        assertEquals(thirdCommand, CommandHistory.getCommandHistory("down"));
        assertEquals(secondCommand, CommandHistory.getCommandHistory("up"));
        assertEquals(initialCommand, CommandHistory.getCommandHistory("up"));
        assertEquals(initialCommand, CommandHistory.getCommandHistory("up"));
        assertEquals(secondCommand, CommandHistory.getCommandHistory("down"));
    }

    @Test
    void getCommandHistory_noPreviousCommands_success() {
        assertEquals("", CommandHistory.getCommandHistory("up"));
        assertEquals("", CommandHistory.getCommandHistory("down"));
    }

}