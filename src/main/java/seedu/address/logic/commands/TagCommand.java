package seedu.address.logic.commands;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;

import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

public class TagCommand extends Command {

    public static final String COMMAND_WORD = "tag";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Adds tags to the person identified "
            + "by the index number used in the last person listing. "
            + "Existing tags will not be overwritten by the input.\n"
            + "Parameters: INDEX (must be a positive integer) "
            + PREFIX_TAG + "[tag]...\n"
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_TAG + "INTERNAL " + PREFIX_TAG + "WAITLIST";
    @Override
    public CommandResult execute(Model model) throws CommandException {
        return new CommandResult("Hello from Tag");
    }
}
