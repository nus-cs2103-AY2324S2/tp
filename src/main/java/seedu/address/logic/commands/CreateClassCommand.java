package seedu.address.logic.commands;

import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Classes;

import static seedu.address.logic.parser.CliSyntax.PREFIX_CLASS;

import static java.util.Objects.requireNonNull;

/**
 * Creates a class to the classbook.
 */
public class CreateClassCommand extends Command {

    public static final String COMMAND_WORD = "create";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Creates a class. "
            + "Parameters: "
            + PREFIX_CLASS + "CLASS_CODE";

    public static final String MESSAGE_SUCCESS = "New class added: %1$s";
    public static final String MESSAGE_DUPLICATE_CLASS = "This class already exists";

    private final Classes toCreate;

    /**
     * Creates an AddCommand to add the specified {@code Person}
     */
    public CreateClassCommand(Classes classes) {
        requireNonNull(classes);
        toCreate = classes;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (model.hasClass(toCreate)) {
            throw new CommandException(MESSAGE_DUPLICATE_CLASS);
        }

        model.createClass(toCreate);
        return new CommandResult(String.format(MESSAGE_SUCCESS, Messages.classFormat(toCreate)));
    }
}
