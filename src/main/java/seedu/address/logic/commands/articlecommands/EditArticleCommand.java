package seedu.address.logic.commands.articlecommands;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_ARTICLES;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.CollectionUtil;
import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.article.Article;
import seedu.address.model.article.Article.Status;
import seedu.address.model.article.Author;
import seedu.address.model.article.Outlet;
import seedu.address.model.article.Source;
import seedu.address.model.tag.Tag;

/**
 * Edits the details of an existing article in the article book.
 */
public class EditArticleCommand extends ArticleCommand {

    public static final String COMMAND_WORD = "edit";

    public static final String COMMAND_PREFIX = "-a";

    // To be edited for use in test cases later on.
    public static final String MESSAGE_USAGE = COMMAND_WORD + " " + COMMAND_PREFIX
            + ": Edits the details of the article identified "
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

        String title = editArticleDescriptor.getTitle().orElse(articleToEdit.getTitle());
        Set<Author> authors = editArticleDescriptor.getAuthors().orElse(articleToEdit.getAuthors());
        Set<Source> sources = editArticleDescriptor.getSources().orElse(articleToEdit.getSources());
        Set<Tag> tags = editArticleDescriptor.getTags().orElse(articleToEdit.getTags());
        Set<Outlet> outlets = editArticleDescriptor.getOutlets().orElse(articleToEdit.getOutlets());
        LocalDateTime publicationDate = editArticleDescriptor.getPublicationDate()
                .orElse(articleToEdit.getPublicationDate());
        Status status = editArticleDescriptor.getStatus().orElse(articleToEdit.getStatus());

        return new Article(title, authors, sources, tags,
                outlets, publicationDate, status); // Include all article attributes here.
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
        private Set<Author> authors;
        private Set<Source> sources;
        private Set<Tag> tags;
        private Set<Outlet> outlets;
        private LocalDateTime publicationDate;
        private Status status;

        public EditArticleDescriptor() {}

        /**
         * Copy constructor.
         */
        public EditArticleDescriptor(EditArticleDescriptor toCopy) {
            setTitle(toCopy.title);
            setAuthors(toCopy.authors);
            setSources(toCopy.sources);
            setTags(toCopy.tags);
            setOutlets(toCopy.outlets);
            setPublicationDate(toCopy.publicationDate);
            setStatus(toCopy.status);
        }

        /**
         * Returns true if at least one field is edited.
         */
        public boolean isAnyFieldEdited() {
            return CollectionUtil.isAnyNonNull(title, authors, sources, outlets, publicationDate, tags, status);
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public Optional<String> getTitle() {
            return Optional.ofNullable(title);
        }

        public void setAuthors(Set<Author> authors) {
            this.authors = (authors != null) ? new HashSet<>(authors) : null;
        }

        public Optional<Set<Author>> getAuthors() {
            return (authors != null) ? Optional.of(Collections.unmodifiableSet(authors)) : Optional.empty();
        }

        public void setPublicationDate(LocalDateTime publicationDate) {
            this.publicationDate = publicationDate;
        }

        public Optional<LocalDateTime> getPublicationDate() {
            return Optional.ofNullable(publicationDate);
        }

        public void setSources(Set<Source> sources) {
            this.sources = (sources != null) ? new HashSet<>(sources) : null;
        }

        public Optional<Set<Source>> getSources() {
            return (sources != null) ? Optional.of(Collections.unmodifiableSet(sources)) : Optional.empty();
        }

        public void setTags(Set<Tag> tags) {
            this.tags = (tags != null) ? new HashSet<>(tags) : null;
        }

        public Optional<Set<Tag>> getTags() {
            return (tags != null) ? Optional.of(Collections.unmodifiableSet(tags)) : Optional.empty();
        }

        public void setOutlets(Set<Outlet> outlets) {
            this.outlets = (outlets != null) ? new HashSet<>(outlets) : null;
        }

        public Optional<Set<Outlet>> getOutlets() {
            return (outlets != null) ? Optional.of(Collections.unmodifiableSet(outlets)) : Optional.empty();
        }
        public void setStatus(Status status) {
            this.status = status;
        }

        public Optional<Status> getStatus() {
            return Optional.ofNullable(status);
        }

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
            return Objects.equals(title, otherEditArticleDescriptor.title)
                    && Objects.equals(authors, otherEditArticleDescriptor.authors)
                    && Objects.equals(sources, otherEditArticleDescriptor.sources)
                    && Objects.equals(outlets, otherEditArticleDescriptor.outlets)
                    && Objects.equals(publicationDate, otherEditArticleDescriptor.publicationDate)
                    && Objects.equals(tags, otherEditArticleDescriptor.tags)
                    && Objects.equals(status, otherEditArticleDescriptor.status);
        }

        @Override
        public String toString() {
            return new ToStringBuilder(this)
                    .add("title", title) // Add more attributes of article here.
                    .toString();
        }
    }
}
