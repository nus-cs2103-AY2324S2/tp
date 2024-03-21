package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.model.Model;
import seedu.address.model.PayBack;

/**
 * Clears PayBack.
 */
public class ClearCommand extends Command {

    public static final String COMMAND_WORD = "/clear";
    public static final String MESSAGE_SUCCESS = "Payback has been cleared!";


    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.setPayBack(new PayBack());
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
