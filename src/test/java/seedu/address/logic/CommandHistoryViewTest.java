package seedu.address.logic;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;

public class CommandHistoryViewTest {
    @Test
    public void constructor_nullArg_throwsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> new CommandHistoryView(null));
    }

    @Test
    public void navigateToEndOfHistory_success() {
        CommandHistory history = new CommandHistory();
        List<String> first = Arrays.asList("1", "2", "3");
        for (String s : first) {
            history.add(s);
        }
        CommandHistoryView view = new CommandHistoryView(history);
        assertEquals(Optional.of("3"), view.next());
        assertEquals(Optional.of("2"), view.next());
        assertEquals(Optional.of("1"), view.next());
        assertEquals(Optional.empty(), view.next());
        assertEquals(Optional.empty(), view.next());
    }

    @Test
    public void updateCurrentCommand_success() {
        CommandHistory history = new CommandHistory();
        CommandHistoryView view = new CommandHistoryView(history);
        assertEquals(1, getCommandHistoryViewBuffer(view).size());
        assertEquals("", getCommandHistoryViewBuffer(view).get(0));
        view.updateCurrentCommand("Command");
        assertEquals(1, getCommandHistoryViewBuffer(view).size());
        assertEquals("Command", getCommandHistoryViewBuffer(view).get(0));
    }

    @Test
    public void updatePersistsAfterNavigation_success() {
        CommandHistory history = new CommandHistory();
        List<String> first = Arrays.asList("1", "2", "3");
        for (String s : first) {
            history.add(s);
        }
        CommandHistoryView view = new CommandHistoryView(history);
        assertEquals(4, getCommandHistoryViewBuffer(view).size());
        assertEquals(Arrays.asList("", "3", "2", "1"), getCommandHistoryViewBuffer(view));
        view.next();
        view.next();
        view.updateCurrentCommand("updated");
        view.previous();
        assertEquals(4, getCommandHistoryViewBuffer(view).size());
        assertEquals(Arrays.asList("", "3", "updated", "1"), getCommandHistoryViewBuffer(view));
    }

    @Test
    public void modifyingCommandHistoryAfterPassingIntoConstructor_noEffect() {
        CommandHistory history = new CommandHistory();
        List<String> first = Arrays.asList("1", "2", "3");
        for (String s : first) {
            history.add(s);
        }
        CommandHistoryView view = new CommandHistoryView(history);
        assertEquals(4, getCommandHistoryViewBuffer(view).size());
        assertEquals(Arrays.asList("", "3", "2", "1"), getCommandHistoryViewBuffer(view));
        history.add("4");
        assertEquals(4, getCommandHistoryViewBuffer(view).size());
        assertEquals(Arrays.asList("", "3", "2", "1"), getCommandHistoryViewBuffer(view));
    }

    @Test
    public void updatingCommand_nullArg_throwsIllegalArgumentException() {
        CommandHistory history = new CommandHistory();
        CommandHistoryView view = new CommandHistoryView(history);
        assertThrows(IllegalArgumentException.class, () -> view.updateCurrentCommand(null));
    }

    @SuppressWarnings("unchecked")
    private static ArrayList<String> getCommandHistoryViewBuffer(CommandHistoryView view) {
        try {
            Field privateField = CommandHistoryView.class.getDeclaredField("commandBuffer");
            privateField.setAccessible(true);
            return (ArrayList<String>) privateField.get(view);
        } catch (Exception e) {
            System.out.println("Error when using reflection to access private field in CommandHistoryView.");
        }
        return new ArrayList<>();
    }
}
