package seedu.address.logic.commands.articlecommands;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_ARTICLES;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.CollectionUtil;
import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;

/**
 * Edits the details of an existing article in the article book.
 */
public class EditArticleCommand extends Command {

    public static final String COMMAND_WORD = "edit";

    public static final String COMMAND_PREFIX = "-a";

    // To be edited for use in test cases later on.
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits the details of the article identified "
            + "by the index number used in the displayed article list. "
            + "Existing values will be overwritten by the input values.\n"
            + "Parameters: INDEX (must be a positive integer) "
            + "MORE PARAMETERS" // Add all possible parameters here.
            + "Example: " + COMMAND_WORD + " " + COMMAND_PREFIX + " 1 "
            + "PARAMETERS LISTED HERE"; // Parameters used in the example are added here.

    public static final String MESSAGE_EDIT_ARTICLE_SUCCESS = "Edited Article: %1$s";
    public static final String MESSAGE_NOT_EDITED = "At least one field to edit must be provided.";
    public static final String MESSAGE_DUPLICATE_ARTICLE = "This article already exists in the article book.";

    private final Index index;
    private final EditArticleDescriptor editArticleDescriptor;

    /**
     * @param index of the article in the filtered article list to edit
     * @param editArticleDescriptor details to edit the article with
     */
    public EditArticleCommand(Index index, EditArticleDescriptor editArticleDescriptor) {
        requireNonNull(index);
        requireNonNull(editArticleDescriptor);

        this.index = index;
        this.editArticleDescriptor = new EditArticleDescriptor(editArticleDescriptor);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Article> lastShownList = model.getFilteredArticleList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_ARTICLE_DISPLAYED_INDEX);
        }

        Article articleToEdit = lastShownList.get(index.getZeroBased());
        Article editedArticle = createEditedArticle(articleToEdit, editArticleDescriptor);

        if (!articleToEdit.isSameArticle(editedArticle) && model.hasArticle(editedArticle)) {
            throw new CommandException(MESSAGE_DUPLICATE_ARTICLE);
        }

        model.setArticle(articleToEdit, editedArticle);
        model.updateFilteredArticleList(PREDICATE_SHOW_ALL_ARTICLES);
        return new CommandResult(String.format(MESSAGE_EDIT_ARTICLE_SUCCESS, Messages.format(editedArticle)));
    }

    /**
     * Creates and returns a {@code Article} with the details of {@code articleToEdit}
     * edited with {@code editArticleDescriptor}.
     */
    private static Article createEditedArticle(Article articleToEdit, EditArticleDescriptor editArticleDescriptor) {
        assert articleToEdit != null;

        // Body to be implemented with reference to EditCommand.java class.

        return new Article(); // Include all article attributes here.
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof EditArticleCommand)) {
            return false;
        }

        EditArticleCommand otherEditArticleCommand = (EditArticleCommand) other;
        return index.equals(otherEditArticleCommand.index)
                && editArticleDescriptor.equals(otherEditArticleCommand.editArticleDescriptor);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("index", index)
                .add("editArticleDescriptor", editArticleDescriptor)
                .toString();
    }

    /**
     * Stores the details to edit the article with. Each non-empty field value will replace the
     * corresponding field value of the article.
     */
    public static class EditArticleDescriptor {

        private String title;

        // Declare article attributes here.

        public EditArticleDescriptor() {}

        /**
         * Copy constructor.
         */
        public EditArticleDescriptor(EditArticleDescriptor toCopy) {
            setTitle(toCopy.title);

            //set article attributes here.
        }

        /**
         * Returns true if at least one field is edited.
         */
        public boolean isAnyFieldEdited() {
            return CollectionUtil.isAnyNonNull(); // Add article attributes as arguments here.
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public Optional<String> getTitle() {
            return Optional.ofNullable(title);
        }

        /*
         * Add setter and getter methods for article attributes here.
         */

        @Override
        public boolean equals(Object other) {
            if (other == this) {
                return true;
            }

            // instanceof handles nulls
            if (!(other instanceof EditArticleDescriptor)) {
                return false;
            }

            EditArticleDescriptor otherEditArticleDescriptor = (EditArticleDescriptor) other;

            // Add more equality checks for article attributes below here.
            return Objects.equals(title, otherEditArticleDescriptor.title);
        }

        @Override
        public String toString() {
            return new ToStringBuilder(this)
                    .add("title", title) // Add more attributes of article here.
                    .toString();
        }
    }
}
