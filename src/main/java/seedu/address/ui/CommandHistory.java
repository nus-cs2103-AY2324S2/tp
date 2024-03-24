package seedu.address.ui;


import java.util.ArrayList;
import java.util.logging.Logger;

import seedu.address.commons.core.LogsCenter;

/**
 * Stores the history of commands entered by the user.
 */
public class CommandHistory {

    private static final Logger logger = LogsCenter.getLogger(CommandHistory.class);

    private static final int MAX_SIZE = 100;
    private ArrayList<String> history = new ArrayList<>(MAX_SIZE);
    private int currentPointer = 0;

    /**
     * Creates a {@code CommandHistory} with an empty draft command.
     */
    public CommandHistory() {
        history.add("");
    }

    /**
     * Adds a command to the history.
     */
    public void addCommand(String command) {
        if (history.size() == MAX_SIZE) {
            history.remove(0);
        }
        history.set(history.size() - 1, command);
        history.add("");
        currentPointer = history.size() - 1;
    }

    /**
     * Saves the draft command to the history.
     */
    public void saveDraftCommand(String draftCommand) {
        assert(history.size() > 0);
        history.set(history.size() - 1, draftCommand);
        currentPointer = history.size() - 1;
    }

    /**
     * Returns the previous command from the history.
     */
    public String getPreviousCommand() {
        logger.info("Finding previous command in history: currently at item " + currentPointer);
        assert(currentPointer >= 0 && currentPointer < history.size());
        // The currentPointer wraps around the history list.
        currentPointer = currentPointer - 1;
        if (currentPointer < 0) {
            currentPointer = history.size() - 1;
        }
        return history.get(currentPointer);
    }

    /**
     * Returns the next command from the history.
     */
    public String getNextCommand() {
        logger.info("Finding next command in history: currently at item " + currentPointer);
        assert(currentPointer >= 0 && currentPointer < history.size());
        // For next command, we no longer wrap around the history list.
        currentPointer = currentPointer + 1;
        if (currentPointer >= history.size()) {
            currentPointer = history.size() - 1;
        }
        return history.get(currentPointer);
    }

    /**
     * Checks whether the currently displayed command is edited.
     */
    public boolean isCommandEdited(String command) {
        return history.get(currentPointer).compareTo(command) != 0;
    }
}
