package seedu.address.logic.commands;

import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Person;
import seedu.address.model.person.PersonContainsTagPredicate;
import seedu.address.model.tag.Tag;

import java.util.function.Predicate;

import static java.util.Objects.requireNonNull;

public class FilterCommand extends Command {

    private final Predicate<Person> predicate;
    private final Tag tag;

    public static final String COMMAND_WORD = "filter";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Filter through clients. \n"
            + "Parameters: \n"
            + "tag - The tag you wish to filter your clients by\n"
            + "Example: " + COMMAND_WORD + " family";

    public static final String MESSAGE_SUCCESS = "Displaying all clients filtered by: %s";

    public FilterCommand(Tag tag) {
        this.predicate = new PersonContainsTagPredicate(tag);
        this.tag = tag;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        model.updateFilteredPersonList(this.predicate);

        if (model.getFilteredPersonList().isEmpty()) {
            model.updateFilteredPersonList(p -> true);
            throw new CommandException(String.format(Messages.MESSAGE_INVALID_FILTER_TAG, tag));
        }

        return new CommandResult(String.format(MESSAGE_SUCCESS, this.tag));
    }
}
