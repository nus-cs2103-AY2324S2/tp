package seedu.address.storage;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.exam.Exam;
import seedu.address.model.person.Score;

/**
 * Jackson-friendly version of {@link Exam}.
 */
public class JsonAdaptedExamScore {
    private final String examName;
    private final int examMaxScore;
    private final int score;

    /**
     * Constructs a {@code JsonAdaptedExamScore} with the given exam details.
     */
    @JsonCreator
    public JsonAdaptedExamScore(@JsonProperty("examName") String examName,
                                @JsonProperty("examMaxScore") int examMaxScore,
                                @JsonProperty("score") int score) {
        this.examName = examName;
        this.examMaxScore = examMaxScore;
        this.score = score;
    }

    /**
     * Converts a given {@code Exam} into this class for Jackson use.
     */
    public JsonAdaptedExamScore(Exam source, Score score) {
        examName = source.getName();
        examMaxScore = source.getMaxScore().getScore();
        this.score = score.getScore();
    }

    /**
     * Converts a given {@code Exam} into this class for Jackson use.
     */
    public Exam toModelTypeExam() throws IllegalValueException {
        if (!Exam.isValidName(examName) || !Exam.isValidExamScore(score)) {
            throw new IllegalValueException(Exam.MESSAGE_CONSTRAINTS);
        }
        return new Exam(examName, new Score(examMaxScore));
    }

    /**
     * Converts a given {@code Score} into this class for Jackson use.
     */
    public Score toModelTypeScore() throws IllegalValueException {
        if (!Score.isValidScore(score)) {
            throw new IllegalValueException(Score.MESSAGE_CONSTRAINTS);
        }
        return new Score(score);
    }
}
