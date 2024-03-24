package seedu.address.commons.core;

import java.util.ArrayList;
import java.util.List;

import seedu.address.commons.util.AudioUtil;

enum CommandHistoryType {
    UNDO,
    REDO
}

/**
 * An abstraction for command history operations like viewing the previous and next command in history.
 * Offers a similar experience to navigating through history of commands in a shell terminal.
 */
public class CommandHistory {
    private final List<String> commandHistory;
    private int currentCommandIndex = -1;

    /**
     * Maintaining lastCommandType is required due to indexing issues when calling undo after redo and vice versa
     */
    private CommandHistoryType lastCommandType;

    /**
     * Initializes the command history with an empty string as the first command.
     */
    public CommandHistory() {
        commandHistory = new ArrayList<>();
        commandHistory.add(""); // Add an empty string to the history by default.
    }

    /**
     * Adds a command to the command history and sets the command index to the end of the list
     * @param commandText Text to add to the history
     */
    public void addCommandToHistory(String commandText) {
        commandHistory.set(commandHistory.size() - 1, commandText);
        commandHistory.add("");
        currentCommandIndex = commandHistory.size() - 1;
    }

    /**
     * Gets the command at the current index in the history
     * @return Current command
     */
    public String getCurrentCommand() {
        return commandHistory.get(currentCommandIndex);
    }
    /**
     * Returns the previous command in the history and decrements the current command index.
     * If there are no more commands to iterate through, it will play an error sound.
     */
    public void undo() {
        if (currentCommandIndex > 0) {
            currentCommandIndex--;
        } else {
            // We have reached the start of the command history, no more commands to iterate through!

            AudioUtil.playAudio(AudioUtil.ERROR_SOUND);
        }
    }

    /**
     * Returns the next command in history and increments the current command index.
     * Returns an empty string if there the currentCommandIndex is at the end of the history.
     */
    public void redo() {
        // Ensure that index is NOT out of bounds as well
        if (currentCommandIndex >= 0 && currentCommandIndex < commandHistory.size() - 1) {
            currentCommandIndex++;
        }
    }


}
