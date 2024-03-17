package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.testutil.Assert.assertThrows;


import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.course.Course;


public class JsonAdaptedCourseTest {

    private static final String INVALID_CODE = "R";

    private static final String VALID_CODE = "CS2103T";

    private static final Course VALID_COURSE = new Course(VALID_CODE);

    @Test
    public void toModelType_validPersonDetails_returnsPerson() throws Exception {
        JsonAdaptedCourse course = new JsonAdaptedCourse(VALID_COURSE);
        assertEquals(VALID_COURSE, course.toModelType());
    }

    @Test
    public void toModelType_invalidName_throwsIllegalValueException() {
        JsonAdaptedCourse course = new JsonAdaptedCourse(INVALID_CODE);
        String expectedMessage = Course.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, course::toModelType);
    }




}
