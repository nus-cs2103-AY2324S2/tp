package seedu.address.logic.commands.articlecommands;

import static java.util.Objects.requireNonNull;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.model.Model;

/**
 * Finds and lists all articles in article book whose title contains any of the argument keywords.
 * Keyword matching is case-insensitive.
 */
public class FindArticleCommand extends Command {

    public static final String COMMAND_WORD = "find";

    public static final String COMMAND_PREFIX = "-a";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all articles whose titles contain any of "
            + "the specified keywords (case-insensitive) and displays them as a list with index numbers.\n"
            + "Parameters: KEYWORD [MORE_KEYWORDS]...\n"
            + "Example: " + COMMAND_WORD + " " + COMMAND_PREFIX + " HDB UDP TCP";

    private final TitleContainsKeywordsPredicate predicate;

    /**
     * @param TitleContainsKeywordsPredicate predicate to filter the list of articles
     *                                       with titles containing the keyword(s)
     */
    public FindArticleCommand(TitleContainsKeywordsPredicate predicate) {
        this.predicate = predicate;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredArticleList(predicate);
        return new CommandResult(
                String.format(Messages.MESSAGE_ARTICLES_LISTED_OVERVIEW, model.getFilteredArticleList().size()));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof FindArticleCommand)) {
            return false;
        }

        FindArticleCommand otherFindArticleCommand = (FindArticleCommand) other;
        return predicate.equals(otherFindArticleCommand.predicate);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("predicate", predicate)
                .toString();
    }
}
