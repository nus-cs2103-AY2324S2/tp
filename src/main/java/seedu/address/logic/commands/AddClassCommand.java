package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MODULECODE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TUTORIALCLASS;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.module.Module;

public class AddClassCommand extends Command {

    public static final String MESSAGE_ARGUMENTS = "Module: %1$s, Tutorial Class: %2$s";
    public static final String MESSAGE_ADD_REMARK_SUCCESS = "Added module %1$s";
    public static final String MESSAGE_DUPLICATE_CLASS = "Duplicate tutorial class!";
    public static final String MESSAGE_NOT_IMPLEMENTED_YET = "Add Class command not implemented yet";
    public static final String COMMAND_WORD = "/add_class";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a class with the module code specified\n"
            + "Parameters:" + PREFIX_MODULECODE + "MODULE_CODE (must be a String) "
            + PREFIX_TUTORIALCLASS + "TUTORIAL_CLASS (must be a String)"
            + "Example: " + COMMAND_WORD + PREFIX_MODULECODE + " CS2103T "
            + PREFIX_TUTORIALCLASS + "T09";
    private final Module module;


    /**
     * @param module of the tutorial class to be added
     */
    public AddClassCommand(Module module) {
        requireAllNonNull(module);

        this.module = module;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (model.hasModule(module)) {
            throw new CommandException(MESSAGE_DUPLICATE_CLASS);
        }

        model.addModule(module);
        return new CommandResult(MESSAGE_ADD_REMARK_SUCCESS);
    }

    /**
     * Generates a command execution success message based on whether the remark is added to or removed from
     * {@code personToEdit}.
     */
    private String generateSuccessMessage(Module module) {
        return String.format(MESSAGE_ADD_REMARK_SUCCESS, module.getName().fullName);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof AddClassCommand)) {
            return false;
        }

        AddClassCommand e = (AddClassCommand) other;
        return module.equals(e.module);
    }
}
