package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.AddressBook;
import seedu.address.model.person.Classes;
import seedu.address.model.person.CourseCode;

/**
 * Jackson-friendly version of {@link Classes}.
 */
public class JsonAdaptedClass {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "CourseCode field is missing!";

    private final String courseCode;

    /**
     * Constructs a {@code JsonAdaptedClass} with the given class details.
     */
    @JsonCreator
    public JsonAdaptedClass(@JsonProperty("courseCode") String courseCode) {
        this.courseCode = courseCode;
    }

    /**
     * Converts a given {@code Classes} into this class for Jackson use.
     */
    public JsonAdaptedClass(Classes source) {
        courseCode = source.getCourseCode().getCourseCode();
    }

    /**
     * Converts this Jackson-friendly adapted classes object into the model's {@code Classes} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted class.
     */
    public Classes toModelType() throws IllegalValueException {
        if (!CourseCode.isValidClass(courseCode)) {
            throw new IllegalValueException(CourseCode.MESSAGE_CONSTRAINTS);
        }

        if (courseCode == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    CourseCode.class.getSimpleName()));
        }

        final CourseCode modelCourseCode = new CourseCode(courseCode);

        return new Classes(modelCourseCode, new AddressBook());
    }
}
