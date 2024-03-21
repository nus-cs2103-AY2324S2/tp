package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.language.ProgrammingLanguage;

/**
 * Jackson-friendly version of {@link ProgrammingLanguage}.
 */
class JsonAdaptedProgrammingLanguage {

    private final String languageName;

    /**
     * Constructs a {@code JsonAdaptedProgrammingLanguage} with the given {@code languageName}.
     */
    @JsonCreator
    public JsonAdaptedProgrammingLanguage(String languageName) {
        this.languageName = languageName;
    }

    /**
     * Converts a given {@code ProgrammingLanguage} into this class for Jackson use.
     */
    public JsonAdaptedProgrammingLanguage(ProgrammingLanguage source) {
        languageName = source.languageName;
    }

    @JsonValue
    public String getLanguageName() {
        return languageName;
    }

    /**
     * Converts this Jackson-friendly adapted programming language object into the model's {@code ProgrammingLanguage}
     * object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted programming language.
     */
    public ProgrammingLanguage toModelType() throws IllegalValueException {
        if (!ProgrammingLanguage.isValidLanguageName(languageName)) {
            throw new IllegalValueException(ProgrammingLanguage.MESSAGE_CONSTRAINTS);
        }
        return new ProgrammingLanguage(languageName);
    }

}

