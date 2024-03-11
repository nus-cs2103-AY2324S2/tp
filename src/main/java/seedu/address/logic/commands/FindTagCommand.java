package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.model.Model;
import seedu.address.model.person.TagFoundPredicate;

/**
 * Finds and lists all persons in address book who contains the tag in the argument.
 * Tag matching is case sensitive.
 */

public class FindTagCommand extends Command {

    public static final String COMMAND_WORD = "tagfind";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all persons who contains the tag "
            + "and displays them as a list with index numbers.\n"
            + "Parameters: TAG \n"
            + "Example: " + COMMAND_WORD + " car";

    private final TagFoundPredicate predicate;

    public FindTagCommand(TagFoundPredicate predicate) {
        this.predicate = predicate;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredPersonList(predicate);
        return new CommandResult(
                String.format(Messages.MESSAGE_PERSONS_LISTED_OVERVIEW, model.getFilteredPersonList().size()));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof FindTagCommand)) {
            return false;
        }

        FindTagCommand otherFindTagCommand = (FindTagCommand) other;
        return predicate.equals(otherFindTagCommand.predicate);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("predicate", predicate)
                .toString();
    }

}

