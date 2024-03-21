package seedu.address.storage;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.article.Article;

/**
 * Jackson-friendly version of {@link Article}.
 */
public class JsonAdaptedArticle {
    private final String title;
    private final String[] authors;
    private final LocalDateTime publicationDate;
    private final String[] sources;
    private final String category;
    private final Article.Status status;

    /**
     * Construct a {@code JsonAdaptedArticle} with the given article details.
     *
     * @param title
     * @param authors
     * @param publicationDate
     * @param sources
     * @param category
     * @param status
     */
    @JsonCreator
    public JsonAdaptedArticle(@JsonProperty("title") String title, @JsonProperty("authors") String[] authors,
                              @JsonProperty("publicationDate") LocalDateTime publicationDate,
                              @JsonProperty("sources") String[] sources, @JsonProperty("category") String category,
                              @JsonProperty("status") Article.Status status) {
        this.title = title;
        this.authors = authors;
        this.publicationDate = publicationDate;
        this.sources = sources;
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
        sources = sourceArticle.getSources();
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
        if (authors == null || authors.length == 0) {
            throw new IllegalValueException("The author[s] is/are invalid");
        }
        if (publicationDate == null) {
            throw new IllegalValueException("The publication date is invalid");
        }
        if (sources == null || sources.length == 0) {
            throw new IllegalValueException("The source is invalid");
        }
        if (category == null) {
            throw new IllegalValueException("The categories are invalid");
        }
        return new Article(title, authors, publicationDate, sources, category, status);
    }
}
