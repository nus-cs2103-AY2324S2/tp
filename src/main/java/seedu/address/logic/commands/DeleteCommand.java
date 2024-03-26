package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.messages.DeleteMessages;
import seedu.address.model.Model;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;

/**
 * Deletes a person identified using it's displayed name from the address book.
 */
public class DeleteCommand extends Command {
    public static final String COMMAND_WORD = "/delete";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the person identified by their name.\n"
            + "Parameters: "
            + PREFIX_NAME + "NAME "
            + "Example: " + COMMAND_WORD + PREFIX_NAME + "Moochie";

    private final Name targetName;

    public DeleteCommand(Name name) {
        this.targetName = name;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        Person personToDelete;

        personToDelete = model.findByName(targetName);

        if (personToDelete == null) {
            throw new CommandException(DeleteMessages.MESSAGE_DELETE_NAME_NOT_FOUND);
        }

        model.deletePerson(personToDelete);
        return new CommandResult(String.format(DeleteMessages.MESSAGE_DELETE_PERSON_SUCCESS,
                DeleteMessages.format(personToDelete)));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof DeleteCommand)) {
            return false;
        }

        DeleteCommand otherDeleteCommand = (DeleteCommand) other;
        return targetName.equals(otherDeleteCommand.targetName);

    }
    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("targetName", targetName)
                .toString();
    }
}
