package seedu.address.logic.commands;

import seedu.address.model.InternshipModel;

/**
 * Terminates the program.
 */
public class InternshipExitCommand extends InternshipCommand {

    public static final String COMMAND_WORD = "exit";

    public static final String MESSAGE_EXIT_ACKNOWLEDGEMENT = "Exiting CareerSync as requested ...";

    @Override
    public CommandResult execute(InternshipModel model) {
        return new CommandResult(MESSAGE_EXIT_ACKNOWLEDGEMENT, false, true);
    }
}
