package seedu.address.model.command;

import java.util.Objects;

/**
 * Represents information about a single command in the application.
 * This includes the command itself and its associated description.
 */
public class CommandInfo {

    /**
     * The command keyword.
     */
    private final String command;

    /**
     * A brief description of what the command does.
     */
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

    /**
     * Returns the command keyword of this CommandInfo instance.
     *
     * @return The command keyword.
     */
    public String getCommand() {
        return command;
    }

    /**
     * Returns the description of the command for this CommandInfo instance.
     *
     * @return The description of the command.
     */
    public String getDescription() {
        return description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        CommandInfo that = (CommandInfo) o;
        return Objects.equals(command, that.command)
                && Objects.equals(description, that.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(command, description);
    }
}


