package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.messages.SearchMessages;
import seedu.address.model.Model;
import seedu.address.model.person.RemindPredicate;

/**
 * Finds all persons with notes that have deadlines from
 * the current day onwards.
 */
public class RemindCommand extends Command {

    public static final String COMMAND_WORD = "/remind";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Searches all persons names"
            + " with notes that have deadlines from"
            + "the current day onwards.\n"
            + "Example: " + COMMAND_WORD;
    public RemindCommand() {
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredPersonList(new RemindPredicate());
        return new CommandResult(
                String.format(SearchMessages.MESSAGE_SEARCH_PERSON_SUCCESS, model.getFilteredPersonList().size()));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof RemindCommand)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .toString();
    }
}
