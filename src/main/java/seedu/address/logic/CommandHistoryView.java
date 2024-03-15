package seedu.address.logic;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Manager for viewing and editing the command history. It uses a buffer to keep track of changes made
 * to the command history in the current input session, but does not modify the actual command history.
 */
public class CommandHistoryView {
    private int index = 0;
    private final List<String> commandBuffer = new ArrayList<>();

    /**
     * Instantiates a new Command history view.
     *
     * @param history        the history
     */
    public CommandHistoryView(CommandHistory history) {
        if (history == null) {
            throw new IllegalArgumentException("Command history cannot be null");
        }
        commandBuffer.add("");
        commandBuffer.addAll(history.getHistory());
    }

    /**
     * Request for the next entry in the history.
     *
     * @return an {@code Optional<String>} containing next entry if it exists,
     * {@link Optional#empty()} otherwise.
     */
    public Optional<String> next() {
        if (index + 1 == commandBuffer.size()) {
            return Optional.empty();
        }
        index += 1;
        return Optional.of(commandBuffer.get(index));
    }

    /**
     * Request for the previous entry in the history.
     *
     * @return an {@code Optional<String>} containing the previous entry if it exists,
     * {@link Optional#empty()} otherwise.
     */
    public Optional<String> previous() {
        if (index == 0) {
            return Optional.empty();
        }
        index -= 1;
        return Optional.of(commandBuffer.get(index));
    }

    /**
     * Update the current command in the buffer.
     *
     * @param command the command to update.
     */
    public void updateCurrentCommand(String command) {
        if (command == null) {
            throw new IllegalArgumentException("String command cannot be null.");
        }
        commandBuffer.set(index, command);
    }
}
