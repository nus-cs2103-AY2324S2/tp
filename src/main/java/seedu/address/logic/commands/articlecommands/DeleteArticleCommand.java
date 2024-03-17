package seedu.address.logic.commands.articlecommands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.article.Article;

/**
 * Deletes an article identified using it's displayed index from the article book.
 */
public class DeleteArticleCommand extends Command {

    public static final String COMMAND_WORD = "delete";

    public static final String COMMAND_PREFIX = "-a";

    // To be edited for use in test cases later on.
    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the article identified by the index number used in the displayed article list.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " " + COMMAND_PREFIX + " 1";

    public static final String MESSAGE_DELETE_ARTICLE_SUCCESS = "Deleted Article: %1$s";

    private final Index targetIndex;

    public DeleteArticleCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Article> lastShownList = model.getFilteredArticleList();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_ARTICLE_DISPLAYED_INDEX);
        }

        Article articleToDelete = lastShownList.get(targetIndex.getZeroBased());
        model.deleteArticle(articleToDelete);
        return new CommandResult(String.format(MESSAGE_DELETE_ARTICLE_SUCCESS, Messages.format(articleToDelete)));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof DeleteArticleCommand)) {
            return false;
        }

        DeleteArticleCommand otherDeleteArticleCommand = (DeleteArticleCommand) other;
        return targetIndex.equals(otherDeleteArticleCommand.targetIndex);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("targetIndex", targetIndex)
                .toString();
    }
}
