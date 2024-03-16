package seedu.address.ui;

import javafx.beans.property.SimpleStringProperty;

/**
 * An item in the table of HelpWindow.
 */
public class UserGuideItem {
    private final SimpleStringProperty command;
    private final SimpleStringProperty usage;

    /**
     * Constructs an item in the user guide table.
     *
     * @param command
     * @param usage
     */
    public UserGuideItem(String command, String usage) {
        this.command = new SimpleStringProperty(command);
        this.usage = new SimpleStringProperty(usage);
    }

    public String getCommand() {
        return command.get();
    }

    public String getUsage() {
        return usage.get();
    }
}
