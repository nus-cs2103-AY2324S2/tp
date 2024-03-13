package seedu.address.logic;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

public class CommandHistoryTest {
    @Test
    public void constructor_default() {
        CommandHistory history = new CommandHistory();
        assertNotNull(history);
        assertEquals(Collections.emptyList(), history.getHistory());
    }

    @Test
    public void constructor_withCapacity() {
        CommandHistory history = new CommandHistory(5);
        assertNotNull(history);
        assertEquals(Collections.emptyList(), history.getHistory());
    }

    @Test
    public void constructor_withCapacity_negativeSize_throwsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> new CommandHistory(-5));
    }

    @Test
    public void execute_addAndReadSingleCommand() {
        CommandHistory history = new CommandHistory(5);
        assertEquals(Collections.emptyList(), history.getHistory());
        history.add("command");
        assertEquals(1, history.getHistory().size());
        assertEquals("command", history.getHistory().get(0));
    }

    @Test
    public void adding_nullCommand_throwsIllegalArgumentException() {
        CommandHistory history = new CommandHistory(5);
        assertThrows(IllegalArgumentException.class, () -> history.add(null));
    }

    @Test
    public void execute_addAndReadMultipleCommands() {
        CommandHistory history = new CommandHistory(10);
        assertEquals(Collections.emptyList(), history.getHistory());
        List<String> input = Arrays.asList("1", "2", "3", "4", "5");
        for (String s : input) {
            history.add(s);
        }
        assertEquals(5, history.getHistory().size());
        Collections.reverse(input);
        assertEquals(input, history.getHistory());
    }

    @Test
    public void execute_addBeyondCapacity() {
        CommandHistory history = new CommandHistory(5);
        List<String> first = Arrays.asList("1", "2", "3", "4", "5");
        for (String s : first) {
            history.add(s);
        }
        List<String> second = Arrays.asList("a", "b", "c", "d", "e");
        for (String s : second) {
            history.add(s);
        }
        assertEquals(5, history.getHistory().size());
        Collections.reverse(second);
        assertEquals(second, history.getHistory());
    }

    @Test
    public void check_zero_capacity() {
        CommandHistory history = new CommandHistory(0);
        history.add("command");
        assertEquals(Collections.emptyList(), history.getHistory());
    }

    @Test
    public void check_modifyingEntries_throwsUnsupportedOperationException() {
        CommandHistory history = new CommandHistory(1);
        history.add("command");
        assertThrows(UnsupportedOperationException.class, () -> history.getHistory().set(0, "newcommand"));
    }
}
