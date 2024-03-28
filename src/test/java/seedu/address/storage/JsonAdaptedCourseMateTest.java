package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.storage.JsonAdaptedCourseMate.MISSING_FIELD_MESSAGE_FORMAT;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalCourseMates.BENSON;

import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.coursemate.CourseMate;
import seedu.address.model.coursemate.Email;
import seedu.address.model.coursemate.Name;
import seedu.address.model.coursemate.Phone;
import seedu.address.model.coursemate.Rating;
import seedu.address.model.coursemate.TelegramHandle;

public class JsonAdaptedCourseMateTest {
    private static final String INVALID_NAME = "R@chel";
    private static final String INVALID_PHONE = "+651234";
    private static final String INVALID_EMAIL = "example.com";
    private static final String INVALID_TELEGRAM_HANDLE = " ";
    private static final String INVALID_RATING = "05";

    private static final String VALID_NAME = BENSON.getName().toString();
    private static final String VALID_PHONE = BENSON.getPhone().toString();
    private static final String VALID_EMAIL = BENSON.getEmail().toString();
    private static final String VALID_TELEGRAM_HANDLE = BENSON.getTelegramHandle().toString();
    private static final String VALID_RATING = "5";
    private static final List<JsonAdaptedSkill> VALID_SKILLS = BENSON.getSkills().stream()
            .map(JsonAdaptedSkill::new)
            .collect(Collectors.toList());

    @Test
    public void toModelType_validCourseMateDetails_returnsCourseMate() throws Exception {
        JsonAdaptedCourseMate courseMate = new JsonAdaptedCourseMate(BENSON);
        assertEquals(BENSON, courseMate.toModelType());
    }

    @Test
    public void toModelType_invalidName_throwsIllegalValueException() {
        JsonAdaptedCourseMate courseMate = new JsonAdaptedCourseMate(
                INVALID_NAME, VALID_PHONE, VALID_EMAIL, VALID_TELEGRAM_HANDLE, VALID_RATING, VALID_SKILLS);
        String expectedMessage = Name.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, courseMate::toModelType);
    }

    @Test
    public void toModelType_nullName_throwsIllegalValueException() {
        JsonAdaptedCourseMate courseMate = new JsonAdaptedCourseMate(
                null, VALID_PHONE, VALID_EMAIL, VALID_TELEGRAM_HANDLE, VALID_RATING, VALID_SKILLS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, courseMate::toModelType);
    }

    @Test
    public void toModelType_invalidPhone_throwsIllegalValueException() {
        JsonAdaptedCourseMate courseMate = new JsonAdaptedCourseMate(
                VALID_NAME, INVALID_PHONE, VALID_EMAIL, VALID_TELEGRAM_HANDLE, VALID_RATING, VALID_SKILLS);
        String expectedMessage = Phone.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, courseMate::toModelType);
    }

    @Test
    public void toModelType_nullPhone_throwsIllegalValueException() {
        JsonAdaptedCourseMate courseMate = new JsonAdaptedCourseMate(
                VALID_NAME, null, VALID_EMAIL, VALID_TELEGRAM_HANDLE, VALID_RATING, VALID_SKILLS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Phone.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, courseMate::toModelType);
    }

    @Test
    public void toModelType_invalidEmail_throwsIllegalValueException() {
        JsonAdaptedCourseMate courseMate = new JsonAdaptedCourseMate(
                VALID_NAME, VALID_PHONE, INVALID_EMAIL, VALID_TELEGRAM_HANDLE, VALID_RATING, VALID_SKILLS);
        String expectedMessage = Email.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, courseMate::toModelType);
    }

    @Test
    public void toModelType_nullEmail_throwsIllegalValueException() {
        JsonAdaptedCourseMate courseMate = new JsonAdaptedCourseMate(
                VALID_NAME, VALID_PHONE, null, VALID_TELEGRAM_HANDLE, VALID_RATING, VALID_SKILLS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Email.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, courseMate::toModelType);
    }

    @Test
    public void toModelType_invalidTelegramHandle_throwsIllegalValueException() {
        JsonAdaptedCourseMate courseMate = new JsonAdaptedCourseMate(
                VALID_NAME, VALID_PHONE, VALID_EMAIL, INVALID_TELEGRAM_HANDLE, VALID_RATING, VALID_SKILLS);
        String expectedMessage = TelegramHandle.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, courseMate::toModelType);
    }

    @Test
    public void toModelType_emptyTelegramHandle_returnsCourseMate() throws Exception {
        JsonAdaptedCourseMate courseMate = new JsonAdaptedCourseMate(
                VALID_NAME, VALID_PHONE, VALID_EMAIL, "", VALID_RATING, VALID_SKILLS);
        CourseMate expectedCourseMate = new CourseMate(BENSON.getName(), BENSON.getPhone(), BENSON.getEmail(),
                null, BENSON.getSkills(), new Rating(VALID_RATING));
        assertEquals(expectedCourseMate, courseMate.toModelType());
    }

    @Test
    public void toModelType_nullTelegramHandle_returnsCourseMate() throws Exception {
        JsonAdaptedCourseMate courseMate = new JsonAdaptedCourseMate(
                VALID_NAME, VALID_PHONE, VALID_EMAIL, null, VALID_RATING, VALID_SKILLS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, TelegramHandle.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, courseMate::toModelType);
    }

    @Test
    public void toModelType_nullRating_throwsIllegalValueException() throws Exception {
        JsonAdaptedCourseMate courseMate = new JsonAdaptedCourseMate(
                VALID_NAME, VALID_PHONE, VALID_EMAIL, VALID_TELEGRAM_HANDLE, null, VALID_SKILLS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Rating.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, courseMate::toModelType);
    }

    @Test
    public void toModelType_emptyRating_throwsIllegalValueException() throws Exception {
        JsonAdaptedCourseMate courseMate = new JsonAdaptedCourseMate(
                VALID_NAME, VALID_PHONE, VALID_EMAIL, VALID_TELEGRAM_HANDLE, "", VALID_SKILLS);
        String expectedMessage = Rating.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, courseMate::toModelType);
    }

    @Test
    public void toModelType_invalidRating_throwsIllegalValueException() throws Exception {
        JsonAdaptedCourseMate courseMate = new JsonAdaptedCourseMate(
                VALID_NAME, VALID_PHONE, VALID_EMAIL, VALID_TELEGRAM_HANDLE, INVALID_RATING, VALID_SKILLS);
        String expectedMessage = Rating.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, courseMate::toModelType);
    }

    @Test
    public void toModelType_validRating_returnsCourseMate() throws Exception {
        JsonAdaptedCourseMate courseMate = new JsonAdaptedCourseMate(
                VALID_NAME, VALID_PHONE, VALID_EMAIL, "", VALID_RATING, VALID_SKILLS);
        CourseMate expectedCourseMate = new CourseMate(BENSON.getName(), BENSON.getPhone(), BENSON.getEmail(),
                null, BENSON.getSkills(), new Rating(VALID_RATING));
        assertEquals(expectedCourseMate, courseMate.toModelType());
    }
}
