package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import seedu.address.model.Model;

/**
 * No command, do nothing.
 */
public class NoCommand extends Command {
    public static final String COMMAND_WORD = "no";

    /**
     * Executes the command and returns the result message.
     *
     * @param model {@code Model} which the command should operate on.
     * @return feedback message of the operation result for display
     */
    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        return new CommandResult("");
    }
}
