package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.course.Course;

/**
 * Jackson-friendly version of {@link Course}.
 */
public class JsonAdaptedCourse {

    private String courseCode;

    /**
     * Constructs a {@code JsonAdaptedCourse} with the given {@code courseCode}.
     */
    @JsonCreator
    public JsonAdaptedCourse(String courseCode) {
        this.courseCode = courseCode;
    }

    /**
     * Converts a given {@code Course} into this class for Jackson use.
     */
    public JsonAdaptedCourse(Course source) {
        courseCode = source.toString();
    }

    @JsonValue
    public String getCourseCode() {
        return courseCode;
    }

    /**
     * Converts this Jackson-friendly adapted tag object into the model's {@code Tag} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted tag.
     */
    public Course toModelType() throws IllegalValueException {
        if (!Course.isValidCode(courseCode)) {
            throw new IllegalValueException(Course.MESSAGE_CONSTRAINTS);
        }
        return new Course(courseCode);
    }
}
