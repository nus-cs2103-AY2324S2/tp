package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.article.Author;

/**
 * Jackson-friendly version of {@link Author}.
 */
public class JsonAdaptedAuthor {
    private final String authorName;

    /**
     * Constructs a {@code JsonAdaptedAuthor} with the given {@code authorName}.
     */
    @JsonCreator
    public JsonAdaptedAuthor(String authorName) {
        this.authorName = authorName;
    }

    /**
     * Converts a given {@code Author} into this class for Jackson use.
     */
    public JsonAdaptedAuthor(Author source) {
        authorName = source.authorName;
    }

    @JsonValue
    public String getAuthorName() {
        return authorName;
    }

    /**
     * Converts this Jackson-friendly adapted author object into the model's {@code Author} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted author.
     */
    public Author toModelType() throws IllegalValueException {
        if (!Author.isValidAuthorName(authorName)) {
            throw new IllegalValueException(Author.MESSAGE_CONSTRAINTS);
        }
        return new Author(authorName);
    }
}
