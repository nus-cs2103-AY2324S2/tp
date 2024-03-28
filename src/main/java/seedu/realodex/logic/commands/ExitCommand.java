package seedu.realodex.logic.commands;

import seedu.realodex.model.Model;

/**
 * Terminates the program.
 */
public class ExitCommand extends Command {

    public static final String COMMAND_WORD = "exit";

    public static final String MESSAGE_EXIT_ACKNOWLEDGEMENT = "Exiting Realodex as requested ...";
    public static final String MESSAGE_EXIT_HELP = "Exit Command: Exits Realodex.\n"
            + "Format: exit\n";

    @Override
    public CommandResult execute(Model model) {
        return new CommandResult(MESSAGE_EXIT_ACKNOWLEDGEMENT, false, true);
    }

}
