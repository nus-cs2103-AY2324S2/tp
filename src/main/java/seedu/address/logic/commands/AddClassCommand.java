package seedu.address.logic.commands;

import static seedu.address.logic.parser.CliSyntax.PREFIX_MODULE_CODE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TUTORIAL_CLASS;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;

public class AddClassCommand extends Command {

    public static final String COMMAND_WORD = "/add_class";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a class with the module code specified\n"
            + "Parameters:" + PREFIX_MODULE_CODE + "MODULE_CODE (must be a String) "
            + PREFIX_TUTORIAL_CLASS + "TUTORIAL_CLASS (must be a String)"
            + "Example: " + COMMAND_WORD + PREFIX_MODULE_CODE + " CS2103T "
            + PREFIX_TUTORIAL_CLASS + "T09";

    public static final String MESSAGE_NOT_IMPLEMENTED_YET = "Add Class command not implemented yet";
    @Override
    public CommandResult execute(Model model) throws CommandException {
        throw new CommandException(MESSAGE_NOT_IMPLEMENTED_YET);
    }
}
