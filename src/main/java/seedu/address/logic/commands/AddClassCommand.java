package seedu.address.logic.commands;

import static seedu.address.logic.parser.CliSyntax.PREFIX_MODULE_CODE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TUTORIAL_CLASS;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.module.Module;
import seedu.address.model.module.TutorialClass;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
public class AddClassCommand extends Command {

    public static final String MESSAGE_ARGUMENTS = "Module Code: %1$s, Tutorial Class: %2$s";

    private final Module module_code;
    private final TutorialClass tutorial_class;

    public static final String COMMAND_WORD = "/add_class";

    /**
     * @param module_code of the tutorial class to be added
     * @param tutorial_class the name of the tutorial class to be added.
     */
    public AddClassCommand(Module module_code, TutorialClass tutorial_class) {
        requireAllNonNull(module_code, tutorial_class);

        this.module_code = module_code;
        this.tutorial_class = tutorial_class;
    }

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a class with the module code specified\n"
            + "Parameters:" + PREFIX_MODULE_CODE + "MODULE_CODE (must be a String) "
            + PREFIX_TUTORIAL_CLASS + "TUTORIAL_CLASS (must be a String)"
            + "Example: " + COMMAND_WORD + PREFIX_MODULE_CODE + " CS2103T "
            + PREFIX_TUTORIAL_CLASS + "T09";

    public static final String MESSAGE_NOT_IMPLEMENTED_YET = "Add Class command not implemented yet";
    @Override
    public CommandResult execute(Model model) throws CommandException {
        throw new CommandException(
                String.format(MESSAGE_ARGUMENTS, module_code, tutorial_class));
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
        return module_code.equals(e.module_code)
                && tutorial_class.equals(e.tutorial_class);
    }
}
