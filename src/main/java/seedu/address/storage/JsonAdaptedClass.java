package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.person.Classes;
import seedu.address.model.person.CourseCode;

public class JsonAdaptedClass {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Class field is missing!";

    private final String courseCode;

    @JsonCreator
    public JsonAdaptedClass(@JsonProperty("courseCode") String courseCode) {
        this.courseCode = courseCode;
    }

    public JsonAdaptedClass(Classes source) {
        courseCode = source.getCourseCode().courseCode;
    }

    public Classes toModelType() throws IllegalValueException {
        if (courseCode == null || !CourseCode.isValidClass(courseCode)) {
            throw new IllegalValueException(CourseCode.MESSAGE_CONSTRAINTS);
        }

        final CourseCode modelCourseCode = new CourseCode(courseCode);

        return new Classes(modelCourseCode);
    }
}
