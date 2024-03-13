package seedu.address.logic.commands;

import seedu.address.model.Model;

/**
 * Deletes a loan from the address book.
 */
public class DeleteLoanCommand extends Command {
    public static final String COMMAND_WORD = "deleteloan";

    @Override
    public CommandResult execute(Model model) {
        return new CommandResult("Hello from delete loan");
    }
}
