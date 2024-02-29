package seedu.address.storage;

import java.time.LocalDateTime;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;
import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.person.Name;
import seedu.address.model.person.note.Description;
import seedu.address.model.person.note.Note;

/**
 * Jackson-friendly version of {@link Note}.
 */
public class JsonAdapatedNote {
    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Note's %s field is missing!";
    private final LocalDateTime dateTime;
    private final String description;

    /**
     * Constructs a {@code JsonAdapatedNote} with the given parameters.
     */
    @JsonCreator
    public JsonAdapatedNote(@JsonProperty("dateTime") LocalDateTime dateTime,
                            @JsonProperty("description") String description) {
        this.dateTime = dateTime;
        this.description = description;
    }

    /**
     * Converts a given {@code Note} into this class for Jackson use.
     */
    public JsonAdapatedNote(Note source) {
        this.dateTime = source.getDateTime();
        this.description = source.getDescription().toString();
    }

    /**
     * Converts this Jackson-friendly adapted tag object into the model's {@code Note} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted tag.
     */
    public Note toModelType() throws IllegalValueException {
        if (this.description == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName()));
        }
        if (!Description.isValid(this.description)) {
            throw new IllegalValueException(Description.MESSAGE_CONSTRAINTS);
        }
        final Description description = new Description(this.description);

        return new Note(dateTime, description);
    }

}
