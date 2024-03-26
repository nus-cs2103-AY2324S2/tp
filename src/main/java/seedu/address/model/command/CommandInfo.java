package seedu.address.model.command;

/**
 * Represents a CommandInfo object in the address book.
 */
public class CommandInfo {
    private final String command;
    private final String description;

    /**
     * Constructs an {@code CommandInfo}.
     *
     * @param command a valid String
     * @param description a valid String
     */
    public CommandInfo(String command, String description) {
        this.command = command;
        this.description = description;
    }

    public String getCommand() {
        return command;
    }

    public String getDescription() {
        return description;
    }
}

