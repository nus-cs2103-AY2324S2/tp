package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.messages.SearchMessages;
import seedu.address.model.Model;
import seedu.address.model.person.KeywordPredicate;

/**
 * Finds and lists all persons in address book whose name contains any of the argument keywords.
 * Keyword matching is case insensitive.
 */
public class SearchCommand extends Command {

    public static final String COMMAND_WORD = "/search";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Searches all persons whose names contain any of "
            + "the specified keywords (case-insensitive) and displays them as a list with index numbers.\n"
            + "Parameters: KEYWORD \n"
            + "Example: " + COMMAND_WORD
            + " /search ; name : [full/partial name]\n"
            + "/search ; phone : [full/partial phone]\n"
            + "/search ; address : [full/partial address]\n"
            + "/search ; email : [full/partial email]\n"
            + "/search ; product : [full/partial product name]\n"
            + "/search ; employment : [employment]";

    private final KeywordPredicate predicate;

    public SearchCommand(KeywordPredicate predicate) {
        this.predicate = predicate;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredPersonList(predicate);
        return new CommandResult(
                String.format(SearchMessages.MESSAGE_SEARCH_PERSON_SUCCESS, model.getFilteredPersonList().size()));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof SearchCommand)) {
            return false;
        }

        SearchCommand otherSearchCommand = (SearchCommand) other;
        return predicate.equals(otherSearchCommand.predicate);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("predicate", predicate)
                .toString();
    }
}
