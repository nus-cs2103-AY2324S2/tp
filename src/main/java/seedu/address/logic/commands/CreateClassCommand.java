package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CLASS;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.person.Classes;

import java.io.IOException;


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
    public CommandResult execute(Model model) throws CommandException, IOException {
        requireNonNull(model);

        if (model.hasClass(toCreate)) {
            throw new CommandException(MESSAGE_DUPLICATE_CLASS);
        }

        model.createClass(toCreate);
        model.setAddressBook(new AddressBook());
        model.selectClass(toCreate);
        return new CommandResult(String.format(MESSAGE_SUCCESS, Messages.classFormat(toCreate)));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof CreateClassCommand)) {
            return false;
        }

        CreateClassCommand otherCreateClassCommand = (CreateClassCommand) other;
        return toCreate.equals(otherCreateClassCommand.toCreate);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).add("toCreate", toCreate).toString();
    }
}
