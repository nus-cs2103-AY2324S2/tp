package seedu.address.logic.commands;

import seedu.address.logic.Messages;
import seedu.address.model.Model;

/**
 * Terminates the program.
 */
public class ExitCommand extends Command {

    public static final String COMMAND_WORD = "exit";

    @Override
    public String execute(Model model) {
        return Messages.MESSAGE_EXIT_ACKNOWLEDGEMENT;
    }

}
