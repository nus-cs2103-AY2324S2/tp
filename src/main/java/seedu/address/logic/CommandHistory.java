package seedu.address.logic;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * A list of previously executed commands. Only the most recent {@code HISTORY_SIZE}
 * commands are kept in the list.
 */
public class CommandHistory {
    // TODO: let user specify history size in preferences.json
    private static final int HISTORY_SIZE = 100;

    /**
     * The list for storing the commands.
     */
    // TODO: (Enhancement) persist command history between app launches
    private final LinkedList<String> history = new LinkedList<>();

    /**
     * Returns a read-only list of the command history, starting with the most recent command
     * at the first index.
     *
     * @return a list of commands
     */
    public List<String> getHistory() {
        return Collections.unmodifiableList(history);
    }

    /**
     * Add a command to the history. If the list is already at capacity,
     * the oldest command will be evicted.
     *
     * @param entry the entry
     */
    public void add(String entry) {
        history.addFirst(entry);
        if (history.size() > HISTORY_SIZE) {
            history.pollLast();
        }
        assert history.size() <= HISTORY_SIZE : "Size of command history should not be over the limit";
    }
}
