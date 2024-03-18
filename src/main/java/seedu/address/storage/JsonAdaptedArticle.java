package seedu.address.storage;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonCreator;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.article.Article;

/**
 * Jackson-friendly version of {@link Article}.
 */
public class JsonAdaptedArticle {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Person's %s field is missing!";

    private final String title;
    private final String[] authors;
    private final LocalDateTime publicationDate;
    private final String[] source;
    private final String category;
    private final Article.Status status;

    /**
     * Construct a {@code JsonAdaptedArticle} with the given {@code name}.
     *
     * @param title
     * @param authors
     * @param publicationDate
     * @param source
     * @param category
     * @param status
     */
    @JsonCreator
    public JsonAdaptedArticle(String title, String[] authors, LocalDateTime publicationDate,
                              String[] source, String category, Article.Status status) {
        this.title = title;
        this.authors = authors;
        this.publicationDate = publicationDate;
        this.source = source;
        this.category = category;
        this.status = status;
    }
    /**
     * Construct a {@code JsonAdaptedArticle} with neccessary details
     * @param sourceArticle
     */
    public JsonAdaptedArticle(Article sourceArticle) {
        title = sourceArticle.getTitle();
        authors = sourceArticle.getAuthors();
        publicationDate = sourceArticle.getPublicationDate();
        source = sourceArticle.getSource();
        category = sourceArticle.getCategory();
        status = sourceArticle.getStatus();
    }

    /**
     * Convert this object into Model's object
     * @return Model's object
     * @throws IllegalValueException if data constraints are violated
     */
    public Article toModelType() throws IllegalValueException {
        if (title == null) {
            throw new IllegalValueException("The title is missing");
        }
        if (authors == null) {
            throw new IllegalValueException("The author[s] is/are invalid");
        }
        if (publicationDate == null) {
            throw new IllegalValueException("The publication date is invalid");
        }
        if (source == null) {
            throw new IllegalValueException("The source is invalid");
        }
        if (category == null) {
            throw new IllegalValueException("The categories are invalid");
        }
        return new Article(title, authors, publicationDate, source, category, status);
    }
}
