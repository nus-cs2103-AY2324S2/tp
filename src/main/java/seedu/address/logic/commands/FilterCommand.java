package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.util.function.Predicate;

import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Person;
import seedu.address.model.person.PersonContainsTagPredicate;
import seedu.address.model.tag.Tag;



/**
 * Filters and displays clients by a specific tag.
 */
public class FilterCommand extends Command {

    public static final String COMMAND_WORD = "filter";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Filter through clients. \n"
            + "Parameters: \n"
            + "tag - The tag you wish to filter your clients by\n"
            + "Example: " + COMMAND_WORD + " family";

    public static final String MESSAGE_SUCCESS = "Displaying all clients filtered by: %s";

    private final Predicate<Person> predicate;
    private final Tag tag;

    /**
     * Constructs a {@code FilterCommand} with the specified tag.
     *
     * @param tag The tag to filter by.
     */
    public FilterCommand(Tag tag) {
        this.predicate = new PersonContainsTagPredicate(tag);
        this.tag = tag;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (this.tag.tagName.trim().isEmpty()) {
            throw new CommandException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, FilterCommand.MESSAGE_USAGE));
        }

        model.updateFilteredPersonList(this.predicate);

        if (model.getFilteredPersonList().isEmpty()) {
            model.updateFilteredPersonList(p -> true);
            throw new CommandException(String.format(Messages.MESSAGE_INVALID_FILTER_TAG, tag));
        }

        return new CommandResult(String.format(MESSAGE_SUCCESS, this.tag));
    }

    /**
     * Compares this FilterCommand with another object to check for equality.
     *
     * @param other the other object to compare with.
     * @return true if both commands are equal, otherwise false.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof FilterCommand)) {
            return false;
        }

        FilterCommand otherFilterCommand = (FilterCommand) other;
        return tag.equals(otherFilterCommand.tag);
    }
}
