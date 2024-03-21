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
 * Offers a similar experience to navigating through history of c ommands in a shell terminal..
 */
public class CommandHistory {
    private final List<String> commandHistory;
    private int currentCommandIndex = -1;

    /**
     * Maintaining lastCommandType is required due to indexing issues when calling undo after redo and vice versa
     */
    private CommandHistoryType lastCommandType;
    public CommandHistory() {
        commandHistory = new ArrayList<>();
    }

    /**
     * Adds a command to the command history and sets the command index to the end of the list
     * @param commandText Text to add to the history
     */
    public void addCommandToHistory(String commandText) {
        commandHistory.add(commandText);
        currentCommandIndex = commandHistory.size() - 1;
    }

    /**
     * Returns the previous command in the history and decrements the current command index.
     * If there are no more commands to iterate through, it will play an error sound.
     */
    public String undo() {
        if (lastCommandType == CommandHistoryType.REDO && currentCommandIndex < commandHistory.size() - 1) {
            currentCommandIndex--;
        }

        lastCommandType = CommandHistoryType.UNDO;

        if (currentCommandIndex >= 0) {
            return commandHistory.get(currentCommandIndex--);
        } else {
            // We have reached the start of the command history, no more commands to iterate through!
            AudioUtil.playAudio(AudioUtil.ERROR_SOUND);
            return commandHistory.get(currentCommandIndex);
        }
    }

    /**
     * Returns the next command in history and increments the current command index.
     * Returns an empty string if there the currentCommandIndex is at the end of the history.
     */
    public String redo() {
        if (lastCommandType == CommandHistoryType.UNDO) {
            currentCommandIndex++;
        }

        lastCommandType = CommandHistoryType.REDO;

        if (currentCommandIndex < commandHistory.size() - 1) {
            return commandHistory.get(++currentCommandIndex);
        } else {
            return "";
        }
    }


}
