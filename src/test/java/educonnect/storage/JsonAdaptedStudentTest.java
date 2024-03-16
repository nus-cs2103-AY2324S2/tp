package educonnect.storage;

import static educonnect.storage.JsonAdaptedStudent.MISSING_FIELD_MESSAGE_FORMAT;
import static educonnect.testutil.Assert.assertThrows;
import static educonnect.testutil.TypicalStudents.BENSON;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import educonnect.commons.exceptions.IllegalValueException;
import educonnect.model.student.Email;
import educonnect.model.student.Name;
import educonnect.model.student.StudentId;
import educonnect.model.student.TelegramHandle;
import educonnect.model.student.timetable.Timetable;
import educonnect.testutil.TypicalTimetableAndValues;

public class JsonAdaptedStudentTest {
    private static final String INVALID_NAME = "R@chel";
    private static final String INVALID_STUDENT_ID = "A1";
    private static final String INVALID_TELEGRAM_HANDLE = " ";
    private static final String INVALID_EMAIL = "example.com";
    private static final String INVALID_TAG = "#friend";

    private static final String VALID_NAME = BENSON.getName().toString();
    private static final String VALID_STUDENT_ID = BENSON.getStudentId().toString();
    private static final String VALID_EMAIL = BENSON.getEmail().toString();
    private static final String VALID_TELEGRAM_HANDLE = BENSON.getTelegramHandle().toString();
    private static final List<JsonAdaptedTag> VALID_TAGS = BENSON.getTags().stream()
            .map(JsonAdaptedTag::new)
            .collect(Collectors.toList());
    private static final Timetable VALID_DEFAULT_EMPTY_TIMETABLE = TypicalTimetableAndValues.DEFAULT_EMPTY_TIMETABLE;

    @Test
    public void toModelType_validStudentDetails_returnsStudent() throws Exception {
        JsonAdaptedStudent student = new JsonAdaptedStudent(BENSON);
        assertEquals(BENSON, student.toModelType());
    }

    @Test
    public void toModelType_invalidName_throwsIllegalValueException() {
        JsonAdaptedStudent student =
                new JsonAdaptedStudent(INVALID_NAME, VALID_STUDENT_ID, VALID_EMAIL, VALID_TELEGRAM_HANDLE,
                        VALID_TAGS, VALID_DEFAULT_EMPTY_TIMETABLE);
        String expectedMessage = Name.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, student::toModelType);
    }

    @Test
    public void toModelType_nullName_throwsIllegalValueException() {
        JsonAdaptedStudent student = new JsonAdaptedStudent(
            null, VALID_STUDENT_ID, VALID_EMAIL, VALID_TELEGRAM_HANDLE,
                VALID_TAGS, VALID_DEFAULT_EMPTY_TIMETABLE);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, student::toModelType);
    }

    @Test
    public void toModelType_invalidStudentId_throwsIllegalValueException() {
        JsonAdaptedStudent student =
                new JsonAdaptedStudent(VALID_NAME, INVALID_STUDENT_ID, VALID_EMAIL,
                        VALID_TELEGRAM_HANDLE, VALID_TAGS, VALID_DEFAULT_EMPTY_TIMETABLE);
        String expectedMessage = StudentId.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, student::toModelType);
    }

    @Test
    public void toModelType_nullStudentId_throwsIllegalValueException() {
        JsonAdaptedStudent student = new JsonAdaptedStudent(
            VALID_NAME, null, VALID_EMAIL, VALID_TELEGRAM_HANDLE,
                VALID_TAGS, VALID_DEFAULT_EMPTY_TIMETABLE);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, StudentId.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, student::toModelType);
    }

    @Test
    public void toModelType_invalidEmail_throwsIllegalValueException() {
        JsonAdaptedStudent student =
                new JsonAdaptedStudent(
                    VALID_NAME, VALID_STUDENT_ID, INVALID_EMAIL, VALID_TELEGRAM_HANDLE,
                        VALID_TAGS, VALID_DEFAULT_EMPTY_TIMETABLE);
        String expectedMessage = Email.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, student::toModelType);
    }

    @Test
    public void toModelType_nullEmail_throwsIllegalValueException() {
        JsonAdaptedStudent student = new JsonAdaptedStudent(
            VALID_NAME, VALID_STUDENT_ID, null, VALID_TELEGRAM_HANDLE, VALID_TAGS, VALID_DEFAULT_EMPTY_TIMETABLE);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Email.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, student::toModelType);
    }

    @Test
    public void toModelType_invalidTelegramHandle_throwsIllegalValueException() {
        JsonAdaptedStudent student =
                new JsonAdaptedStudent(
                    VALID_NAME, VALID_STUDENT_ID, VALID_EMAIL, INVALID_TELEGRAM_HANDLE,
                        VALID_TAGS, VALID_DEFAULT_EMPTY_TIMETABLE);
        String expectedMessage = TelegramHandle.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, student::toModelType);
    }

    @Test
    public void toModelType_nullTelegramHandle_throwsIllegalValueException() {
        JsonAdaptedStudent student = new JsonAdaptedStudent(
            VALID_NAME, VALID_STUDENT_ID, VALID_EMAIL, null,
                VALID_TAGS, VALID_DEFAULT_EMPTY_TIMETABLE);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, TelegramHandle.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, student::toModelType);
    }

    @Test
    public void toModelType_invalidTags_throwsIllegalValueException() {
        List<JsonAdaptedTag> invalidTags = new ArrayList<>(VALID_TAGS);
        invalidTags.add(new JsonAdaptedTag(INVALID_TAG));
        JsonAdaptedStudent student =
                new JsonAdaptedStudent(VALID_NAME, VALID_STUDENT_ID, VALID_EMAIL,
                        VALID_TELEGRAM_HANDLE, invalidTags, VALID_DEFAULT_EMPTY_TIMETABLE);
        assertThrows(IllegalValueException.class, student::toModelType);
    }

    @Test
    public void toModelType_nullTimetable_throwsIllegalValueException() {
        JsonAdaptedStudent student = new JsonAdaptedStudent(
                VALID_NAME, VALID_STUDENT_ID, VALID_EMAIL, VALID_TELEGRAM_HANDLE, VALID_TAGS, null);

        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Timetable.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, student::toModelType);
    }
}
