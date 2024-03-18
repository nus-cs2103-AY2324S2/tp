package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.model.CodeConnect;
import seedu.address.model.Model;

/**
 * Clears CodeConnect.
 */
public class ClearCommand extends Command {

    public static final String COMMAND_WORD = "clear";
    public static final String MESSAGE_SUCCESS = "CodeConnect has been cleared!";


    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.setCodeConnect(new CodeConnect());
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
