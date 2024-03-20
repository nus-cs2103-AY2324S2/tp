package seedu.realodex.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.realodex.logic.parser.CliSyntax.PREFIX_NAME;

import java.util.List;

import seedu.realodex.commons.core.index.Index;
import seedu.realodex.commons.util.ToStringBuilder;
import seedu.realodex.logic.Messages;
import seedu.realodex.logic.commands.delete.DeleteCommandExecutor;
import seedu.realodex.logic.commands.exceptions.CommandException;
import seedu.realodex.model.Model;
import seedu.realodex.model.person.Name;
import seedu.realodex.model.person.Person;

/**
 * Deletes a person identified using it's displayed index from realodex.
 */
public class DeleteCommand extends Command {

    public static final String COMMAND_WORD = "delete";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the client identified by the index number or full name used in the displayed client list.\n"
            + "Parameters: INDEX (must be a positive integer) or " + PREFIX_NAME + "NAME \n"
            + "Example: " + COMMAND_WORD + " 1 or " + COMMAND_WORD + " " + PREFIX_NAME + "John Doe";


    public static final String MESSAGE_DELETE_HELP = "Delete Command: Deletes a client in Realodex by "
            + "either the client's full name or index number shown in the list.\n"
            + "Format: delete n/FULL NAME or delete INDEX\n"
            + "Example: delete n/James Lau OR delete 4\n";

    public static final String MESSAGE_DELETE_PERSON_SUCCESS = "Deleted Client: %1$s";

    private final Index targetIndex;
    private final Name targetName;

    /**
     * Creates a DeleteCommand to delete the person at the specified {@code Index}
     */
    public DeleteCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
        this.targetName = null;
    }

    /**
     * Creates a DeleteCommand to delete the person with the specified {@code Name}
     */
    public DeleteCommand(Name targetName) {
        this.targetName = targetName;
        this.targetIndex = null;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Person> lastShownList = model.getFilteredPersonList();
        Person personToDelete;

        if (targetIndex != null) {
            personToDelete = DeleteCommandExecutor.deleteByIndex(lastShownList, targetIndex);
        } else {
            personToDelete = DeleteCommandExecutor.deleteByName(lastShownList, targetName);
        }

        model.deletePerson(personToDelete);
        return new CommandResult(String.format(MESSAGE_DELETE_PERSON_SUCCESS, Messages.format(personToDelete)));
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
        if (targetIndex != null) {
            return targetIndex.equals(otherDeleteCommand.targetIndex);
        } else {
            return targetName.equals(otherDeleteCommand.targetName);
        }
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("targetIndex", targetIndex)
                .toString();
    }
}
