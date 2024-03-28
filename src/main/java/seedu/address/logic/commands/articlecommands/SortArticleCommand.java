package seedu.address.logic.commands.articlecommands;

import static java.util.Objects.requireNonNull;

import seedu.address.logic.Messages;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.SortArticleCommandParser;
import seedu.address.model.Model;

/**
 * Sorts all articles in the article book by an attribute of articles.
 */
public class SortArticleCommand extends ArticleCommand {

    public static final String COMMAND_WORD = "sort";

    public static final String COMMAND_PREFIX = "-a";

    public static final String MESSAGE_SUCCESS = "sorted all articles by date";

    public static final String MESSAGE_USAGE = COMMAND_WORD + " " + COMMAND_PREFIX
            + ": sorts articles according to publication date and displays the sorted article list.\n"
            + "Parameters: D/ (corresponds to prefix for article's publication date attribute)\n"
            + "Example: " + COMMAND_WORD + " " + COMMAND_PREFIX + " D/";

    private final String prefix;

    /**
     * @param prefix referring to an attribute of articles to sort by
     */
    public SortArticleCommand(String prefix) {
        requireNonNull(prefix);
        this.prefix = prefix;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (!SortArticleCommandParser.isAllowedPrefix(prefix)) {
            throw new CommandException(Messages.MESSAGE_INVALID_SORTING_PREFIX);
        }

        model.sortArticleBook(prefix);

        return new CommandResult(MESSAGE_SUCCESS);
    }
}
