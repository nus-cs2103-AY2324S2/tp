package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;
import java.util.function.Predicate;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Person;


/**
 * Retrieves candidate at specified index.
 */
public class GetCommand extends Command {
    public static final String COMMAND_WORD = "get";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds a candidate at the specified "
            + "entry in the database displayed in the HireHub application\n"
            + "Parameters: INDEX\n"
            + "Example: " + COMMAND_WORD + " 3";

    public static final String MESSAGE_GET_PERSON_SUCCESS = "Candidate listed at entry number %1$d is successfully"
            + " retrieved!";

    private final Index index;

    /**
     * Creates a GetCommand to retrieve the candidate at specified index
     */
    public GetCommand(Index index) {
        requireNonNull(index);
        this.index = index;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Person> lastShownList = model.getFilteredPersonList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Person personToGet = lastShownList.get(index.getZeroBased());

        Predicate<Person> predicate = x -> personToGet.equals(x);
        model.updateFilteredPersonList(predicate);

        return new CommandResult(String.format(MESSAGE_GET_PERSON_SUCCESS,
                index.getOneBased()));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof GetCommand)) {
            return false;
        }

        GetCommand otherGetCommand = (GetCommand) other;
        return index.equals(otherGetCommand.index);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("index", index)
                .toString();
    }
}
