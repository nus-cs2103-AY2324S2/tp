package seedu.address.ui;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Contains the command history of the user.
 */
public class CommandHistory {
    private static Logger logger = Logger.getLogger("CommandHistory");
    private static ArrayList<String> commandHistory;
    private static Integer toReturn = 0;
    private static CommandHistory instance = null;

    /**
     * Constructor for the CommandHistory.
     */
    private CommandHistory() {
        commandHistory = new ArrayList<>();
    }

    /**
     * Returns the instance of the CommandHistory.
     * @return the instance of the CommandHistory
     */
    public static CommandHistory getInstance() {
        if (instance == null) {
            logger.log(Level.INFO, "Creating new CommandHistory instance");
            instance = new CommandHistory();
        }
        return instance;
    }

    /**
     * Appends the command to the command history.
     * @param command the command to be appended
     */
    public void appendCommand(String command) {
        if (command == null) {
            return;
        }
        assert (commandHistory != null);
        logger.log(Level.INFO, "Appending command to command history: " + command);
        commandHistory.add(command);
        toReturn = commandHistory.size();
    }

    /**
     * Returns the command history based on the direction.
     * @param direction the direction of keystrone
     * @return the command in the command history records.
     */
    public String getCommandHistory(String direction) {
        assert (direction.equals("UP") || direction.equals("DOWN"));

        logger.log(Level.INFO, "Getting command history based on key press: " + direction);
        if (commandHistory.isEmpty()) {
            logger.log(Level.INFO, "No commands in the command history");
            return "";
        }

        String commandRestored = getAdjacentCommand(direction);
        logger.log(Level.INFO, "Command restored: " + commandRestored);
        return commandRestored;
    }

    /**
     * Returns the adjacent command based on the direction.
     * @param direction the direction of the keystrone
     * @return the adjacent command based on the direction
     */
    private String getAdjacentCommand(String direction) {
        if (direction.equals("UP")) {
            return getPreviousCommand();
        } else if (direction.equals("DOWN")) {
            return getFollowingCommand();
        }
        return "";
    }

    /**
     * Returns the following command in the command history.
     * @return the following command in the command history
     */
    private String getFollowingCommand() {
        if (toReturn < commandHistory.size()) {
            toReturn++;
            if (toReturn == commandHistory.size()) {
                return "";
            }
            return commandHistory.toArray()[toReturn].toString();
        }
        logger.log(Level.INFO, "No newer commands in the command history");
        return "";
    }
    /**
     * Returns the previous command in the command history.
     * @return the previous command in the command history
     */
    private String getPreviousCommand() {
        if (toReturn > 0) {
            toReturn--;
        }
        return commandHistory.toArray()[toReturn].toString();
    }

    /**
     * Clears the command history.
     */
    public void clearCommandHistory() {
        commandHistory.clear();
        toReturn = 0;
    }
}
