package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.SortCommandParser;
import seedu.address.model.Model;

/**
 * Sorts all persons in the address book by an attribute of persons.
 */
public class SortCommand extends PersonCommand {

    public static final String COMMAND_WORD = "sort";

    public static final String MESSAGE_SUCCESS = "sorted all persons by name";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": sorts people according to an attribute and displays the sorted person list.\n"
            + "Parameters: PREFIX (corresponds to each person's attribute e.g. n/ for Name)\n"
            + "Example: " + COMMAND_WORD + " n/";

    private final String prefix;

    /**
     * @param prefix referring to an attribute of persons to sort by
     */
    public SortCommand(String prefix) {
        requireNonNull(prefix);
        this.prefix = prefix;
    }


    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (!SortCommandParser.isAllowedPrefix(prefix)) {
            throw new CommandException(Messages.MESSAGE_INVALID_SORTING_PREFIX);
        }

        model.sortAddressBook(prefix);

        return new CommandResult(MESSAGE_SUCCESS);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof SortCommand)) {
            return false;
        }

        SortCommand otherSortCommand = (SortCommand) other;
        return prefix.equals(otherSortCommand.prefix);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("prefix", prefix)
                .toString();
    }
}
