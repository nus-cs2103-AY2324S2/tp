package seedu.address.storage;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.article.Article;
import seedu.address.model.article.Article.Status;

/**
 * Jackson-friendly version of {@link Article}.
 */
public class JsonAdaptedArticle {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Article's %s field is missing!";

    private final String title;
    private final String[] authors;
    private final LocalDateTime publicationDate;
    private final String[] source;
    private final String category;
    private final Status status;

    /**
     * Constructs a {@code JsonAdaptedArticle} with the given article details.
     */
    @JsonCreator
    public JsonAdaptedArticle(@JsonProperty("title") String title, @JsonProperty("authors") String[] authors,
                             @JsonProperty("publicationDate") LocalDateTime publicationDate,
                              @JsonProperty("source") String[] source, @JsonProperty("category") String category,
                              @JsonProperty("status") Status status) {
        this.title = title;
        this.authors = authors;
        this.publicationDate = publicationDate;
        this.source = source;
        this.category = category;
        this.status = status;
    }

    /**
     * Converts a given {@code Article} into this class for Jackson use.
     */
    public JsonAdaptedArticle(Article articleSource) {
        title = articleSource.getTitle();
        authors = articleSource.getAuthors();
        publicationDate = articleSource.getPublicationDate();
        source = articleSource.getSource();
        category = articleSource.getCategory();
        status = articleSource.getStatus();
    }

    /**
     * Converts this Jackson-friendly adapted article object into the model's {@code Article} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted article.
     */
    public Article toModelType() throws IllegalValueException {
        if (title == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, "Title"));
        }

        final String modelTitle = title;

        if (authors == null || authors.length == 0) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, "Authors"));
        }

        final String[] modelAuthors = authors;

        if (publicationDate == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, "Publication Date"));
        }

        final LocalDateTime modelPublicationDate = publicationDate;

        if (source == null || source.length == 0) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, "Source"));
        }

        final String[] modelSource = source;

        if (category == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, "Category"));
        }

        final String modelCategory = category;

        if (status == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, "Status"));
        }

        final Status modelStatus = status;

        return new Article(modelTitle, modelAuthors, modelPublicationDate, modelSource, modelCategory, modelStatus);
    }
}
