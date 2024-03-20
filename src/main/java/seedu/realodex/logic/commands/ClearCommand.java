package seedu.realodex.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.realodex.model.Model;
import seedu.realodex.model.Realodex;

/**
 * Clears the realodex.
 */
public class ClearCommand extends Command {

    public static final String COMMAND_WORD = "clear";
    public static final String MESSAGE_SUCCESS = "Realodex has been cleared!";
    public static final String MESSAGE_CLEAR_HELP = "Clear Command: Clears all entries in Realodex.\n"
            + "Format: clear\n";


    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.setRealodex(new Realodex());
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
