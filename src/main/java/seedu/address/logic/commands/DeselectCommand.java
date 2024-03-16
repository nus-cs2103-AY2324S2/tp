package seedu.address.logic.commands;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;

/**
 * Deselects the currently selected event.
 */
public class DeselectCommand extends Command {

    public static final String COMMAND_WORD = "desel";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deselects the currently selected event.\n"
            + "Example: " + COMMAND_WORD;

    public static final String MESSAGE_DESELECT_EVENT_SUCCESS = "Event deselected.";

    /**
     * Executes the deselect command.
     *
     * @param model {@code Model} which the command should operate on.
     * @return Result of the command execution.
     * @throws CommandException If an error occurs during command execution.
     */
    @Override
    public CommandResult execute(Model model) throws CommandException {
        model.deselectEvent();
        return new CommandResult(MESSAGE_DESELECT_EVENT_SUCCESS);
    }

    @Override
    public boolean equals(Object other) {
        return other == this || other instanceof DeselectCommand;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).toString();
    }
}
