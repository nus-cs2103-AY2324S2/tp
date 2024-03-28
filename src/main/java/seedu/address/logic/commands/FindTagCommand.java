package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.client.TagContainsKeywordsPredicate;


/**
 * Finds and lists all clients in address book whose tags contains any of the argument keywords.
 * Keyword matching is case insensitive.
 */
public class FindTagCommand extends Command {
    public static final String COMMAND_WORD = "find-tag";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all clients whose tags contain the specified "
            + "keyword (case-insensitive) and displays them as a list with index numbers.\n"
            + "Parameters: TAG_KEYWORD\n"
            + "Note: Please search for the entire tag and not partially\n"
            + "Example: " + COMMAND_WORD + " disabled\n"
            + "NOT: " + COMMAND_WORD + " dis";

    private final TagContainsKeywordsPredicate predicate;

    public FindTagCommand(TagContainsKeywordsPredicate predicate) {
        this.predicate = predicate;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        model.updateFilteredClientList(predicate);
        return new CommandResult(String.format(Messages.MESSAGE_CLIENTS_LISTED_OVERVIEW,
                model.getFilteredClientList().size()));
    }
}
