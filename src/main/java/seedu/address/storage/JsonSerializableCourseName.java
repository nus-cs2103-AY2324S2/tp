package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.CourseName;
import seedu.address.model.ReadOnlyCourseName;
import seedu.address.model.course.Course;

/**
 * An Immutable Course that is serializable to JSON format.
 */
@JsonRootName(value = "course")
public class JsonSerializableCourseName {

    private JsonAdaptedCourse course;

    /**
     * Constructs a {@code JsonSerializableCourse} with the given code.
     */
    @JsonCreator
    public JsonSerializableCourseName(@JsonProperty("course") JsonAdaptedCourse course) {
        this.course = course;
    }

    /**
     * Converts a given {@code ReadOnlyCourse} into this class for Jackson use.
     *
     * @param source future changes to this will not affect the created {@code JsonSerializableCourse}.
     */
    public JsonSerializableCourseName(ReadOnlyCourseName source) {
        course = new JsonAdaptedCourse(new Course("X1234Y"));
    }

    /**
     * Converts this course into the model's {@code Course} object.
     *
     * @throws IllegalValueException if there were any data constraints violated.
     */
    public CourseName toModelType() throws IllegalValueException {
        CourseName courseName = new CourseName();
        courseName.setCourse(this.course.toModelType());
        return courseName;
    }

}
