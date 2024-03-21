package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.person.Lesson;

/**
 * Jackson-friendly version of {@link Lesson}.
 */
class JsonAdaptedLesson {

    private final String lesson;

    /**
     * Constructs a {@code JsonAdaptedLesson} with the given {@code lesson}.
     */
    @JsonCreator
    public JsonAdaptedLesson(String lesson) {
        this.lesson = lesson;
    }

    /**
     * Converts a given {@code Lesson} into this class for Jackson use.
     */
    public JsonAdaptedLesson(Lesson source) {
        lesson = source.getLessonValue();
    }


    @JsonValue
    public String getLessonName() {
        return lesson;
    }

    /**
     * Converts this Jackson-friendly adapted lesson object into the model's {@code Lesson} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted lesson.
     */
    public Lesson toModelType() throws IllegalValueException {
        if (!Lesson.isValidLesson(lesson)) {
            throw new IllegalValueException(Lesson.MESSAGE_CONSTRAINTS);
        }
        return new Lesson(lesson);
    }

}
