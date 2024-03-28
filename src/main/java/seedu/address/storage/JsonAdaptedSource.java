package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.article.Source;

/**
 * Jackson-friendly version of {@link Source}.
 */
public class JsonAdaptedSource {
    private final String sourceName;

    /**
     * Constructs a {@code JsonAdaptedSource} with the given {@code sourceName}.
     */
    @JsonCreator
    public JsonAdaptedSource(String sourceName) {
        this.sourceName = sourceName;
    }

    /**
     * Converts a given {@code Source} into this class for Jackson use.
     */
    public JsonAdaptedSource(Source source) {
        sourceName = source.sourceName;
    }

    @JsonValue
    public String getSourceName() {
        return sourceName;
    }

    /**
     * Converts this Jackson-friendly adapted source object into the model's {@code Source} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted source.
     */
    public Source toModelType() throws IllegalValueException {
        if (!Source.isValidSourceName(sourceName)) {
            throw new IllegalValueException(Source.MESSAGE_CONSTRAINTS);
        }
        return new Source(sourceName);
    }
}
