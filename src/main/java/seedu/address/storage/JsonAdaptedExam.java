package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.exam.Exam;
import seedu.address.model.person.Score;
import seedu.address.model.tag.Tag;

public class JsonAdaptedExam {
    private final String name;
    private final Score maxScore;

    /**
     * Constructs a {@code JsonAdaptedExam} with the given {@code name}.
     */
    @JsonCreator
    public JsonAdaptedExam(String name, Score maxScore) {
        this.name = name;
        this.maxScore = maxScore;
    }

    /**
     * Converts a given {@code Tag} into this class for Jackson use.
     */
    public JsonAdaptedExam(Exam source) {
        name = source.name;
        maxScore = source.maxScore;
    }

    @JsonValue
    public String getname() {
        return name;
    }

    /**
     * Converts this Jackson-friendly adapted tag object into the model's {@code Tag} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted tag.
     */
    public Exam toModelType() throws IllegalValueException {
        if (!Exam.isValidName(name)) {
            throw new IllegalValueException(Tag.MESSAGE_CONSTRAINTS);
        }
        return new Exam(name, maxScore);
    }
}
