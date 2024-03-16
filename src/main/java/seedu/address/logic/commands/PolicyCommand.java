package seedu.address.logic.commands;

import static seedu.address.logic.parser.CliSyntax.PREFIX_POLICY;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;

/**
 * Changes the policy of an existing person in the address book.
 */
public class PolicyCommand extends Command {

    public static final String COMMAND_WORD = "policy";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits the policy of the person identified "
            + "by the index number used in the last person listing. "
            + "Existing policy will be overwritten by the input.\n"
            + "Parameters: INDEX (must be a positive integer) "
            + PREFIX_POLICY + "[POLICY]\n"
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_POLICY + "Likes to swim.";

    public static final String MESSAGE_NOT_IMPLEMENTED_YET = "Policy command not implemented yet";

    @Override
    public CommandResult execute(Model model) throws CommandException {
        throw new CommandException(MESSAGE_NOT_IMPLEMENTED_YET);
    }
}