package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.exam.Exam;
import seedu.address.model.person.Score;

class JsonAdaptedExam {
    private final String name;
    private final String maxScore;

    /**
     * Constructs a {@code JsonAdaptedExam} with the given {@code name}.
     */
    @JsonCreator
    public JsonAdaptedExam(@JsonProperty("name") String name, @JsonProperty("score") String maxScore) {
        this.name = name;
        this.maxScore = maxScore;
    }

    /**
     * Converts a given {@code Tag} into this class for Jackson use.
     */
    public JsonAdaptedExam(Exam source) {
        name = source.getName();
        maxScore = source.getMaxScore().toString();
    }

    public String getname() {
        return name;
    }

    public String getMaxScore() {
        return maxScore;
    }

    /**
     * Converts this Jackson-friendly adapted tag object into the model's {@code Tag} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted tag.
     */
    public Exam toModelType() throws IllegalValueException {
        if (!Exam.isValidName(name)) {
            throw new IllegalValueException(Exam.MESSAGE_CONSTRAINTS);
        }
        return new Exam(name, new Score(Integer.parseInt(maxScore)));
    }
}
