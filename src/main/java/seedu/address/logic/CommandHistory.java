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
    private int capacity = 100;

    /**
     * The list for storing the commands.
     */
    // TODO: (Enhancement) persist command history between app launches
    private final LinkedList<String> history = new LinkedList<>();

    /**
     * Instantiates a new CommandHistory with the default capacity.
     */
    public CommandHistory() {
        super();
    }

    /**
     * Instantiates a new CommandHistory with the specified capacity.
     *
     * @param size A non-negative integer representing the maximum number of entries to store.
     */
    public CommandHistory(int size) {
        if (size < 0) {
            throw new IllegalArgumentException("Capacity for command history cannot be negative.");
        }
        this.capacity = size;
    }

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
        if (entry == null) {
            throw new IllegalArgumentException("String argument cannot be null");
        }
        history.addFirst(entry);
        if (history.size() > capacity) {
            history.pollLast();
        }
        assert history.size() <= capacity : "Size of command history should not be over the limit";
    }
}
