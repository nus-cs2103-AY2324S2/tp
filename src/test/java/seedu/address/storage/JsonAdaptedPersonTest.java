package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.storage.JsonAdaptedPerson.MISSING_FIELD_MESSAGE_FORMAT;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalPersons.BENSON;
import static seedu.address.testutil.TypicalPersons.GEORGE;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Phone;
import seedu.address.model.student.Matric;
import seedu.address.model.student.Reflection;
import seedu.address.model.student.Studio;

public class JsonAdaptedPersonTest {
    private static final String INVALID_NAME = "R@chel";
    private static final String INVALID_PHONE = "+651234";
    private static final String INVALID_ADDRESS = " ";
    private static final String INVALID_EMAIL = "example.com";
    private static final String INVALID_TAG = "#friend";
    private static final String INVALID_MATRIC = "zz";
    private static final String INVALID_REFLECTION = "R";
    private static final String INVALID_STUDIO = "T23";

    private static final String VALID_NAME = BENSON.getName().toString();
    private static final String VALID_PHONE = BENSON.getPhone().toString();
    private static final String VALID_EMAIL = BENSON.getEmail().toString();
    private static final String VALID_ADDRESS = BENSON.getAddress().toString();
    private static final List<JsonAdaptedTag> VALID_TAGS = BENSON.getTags().stream()
            .map(JsonAdaptedTag::new)
            .collect(Collectors.toList());
    private static final String VALID_MATRIC = "A1234567M";
    private static final String VALID_REFLECTION = "R1";
    private static final String VALID_STUDIO = "S1";

    private static final String VALID_EXAM_NAME = "Midterm";
    private static final int VALID_EXAM_MAX_SCORE = 100;
    private static final int VALID_EXAM_SCORE = 70;

    private static final List<JsonAdaptedExamScore> EMPTY_SCORES = new ArrayList<>();
    private static final List<JsonAdaptedExamScore> VALID_SCORES = new ArrayList<>(List.of(
            new JsonAdaptedExamScore(VALID_EXAM_NAME, VALID_EXAM_MAX_SCORE, VALID_EXAM_SCORE)
    ));


    @Test
    public void toModelType_validPersonDetails_returnsPerson() throws Exception {
        JsonAdaptedPerson person = new JsonAdaptedPerson(BENSON);
        assertEquals(BENSON, person.toModelType());
    }

    @Test
    public void toModelType_validPersonDetailsWithScores_returnsPerson() throws Exception {
        JsonAdaptedPerson person = new JsonAdaptedPerson(GEORGE);
        assertEquals(GEORGE, person.toModelType());
    }

    @Test
    public void toModelType_invalidName_throwsIllegalValueException() {
        JsonAdaptedPerson person =
                new JsonAdaptedPerson(INVALID_NAME, VALID_PHONE, VALID_EMAIL,
                                      VALID_ADDRESS, VALID_TAGS, VALID_MATRIC, VALID_REFLECTION,
                                      VALID_STUDIO, EMPTY_SCORES);
        String expectedMessage = Name.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_nullName_throwsIllegalValueException() {
        JsonAdaptedPerson person = new JsonAdaptedPerson(null, VALID_PHONE, VALID_EMAIL, VALID_ADDRESS,
                VALID_TAGS, VALID_MATRIC, VALID_REFLECTION, VALID_STUDIO, EMPTY_SCORES);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_invalidPhone_throwsIllegalValueException() {
        JsonAdaptedPerson person =
                new JsonAdaptedPerson(VALID_NAME, INVALID_PHONE, VALID_EMAIL,
                                      VALID_ADDRESS, VALID_TAGS, VALID_MATRIC,
                                      VALID_REFLECTION, VALID_STUDIO, EMPTY_SCORES);
        String expectedMessage = Phone.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_nullPhone_throwsIllegalValueException() {
        JsonAdaptedPerson person = new JsonAdaptedPerson(VALID_NAME, null, VALID_EMAIL, VALID_ADDRESS,
                                                         VALID_TAGS, VALID_MATRIC, VALID_REFLECTION,
                                                         VALID_STUDIO, EMPTY_SCORES);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Phone.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_invalidEmail_throwsIllegalValueException() {
        JsonAdaptedPerson person =
                new JsonAdaptedPerson(VALID_NAME, VALID_PHONE, INVALID_EMAIL, VALID_ADDRESS,
                                      VALID_TAGS, VALID_MATRIC, VALID_REFLECTION, VALID_STUDIO, EMPTY_SCORES);
        String expectedMessage = Email.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_nullEmail_throwsIllegalValueException() {
        JsonAdaptedPerson person = new JsonAdaptedPerson(
                VALID_NAME, VALID_PHONE, null, VALID_ADDRESS, VALID_TAGS,
                VALID_MATRIC, VALID_REFLECTION, VALID_STUDIO, EMPTY_SCORES);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Email.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_invalidAddress_throwsIllegalValueException() {
        JsonAdaptedPerson person = new JsonAdaptedPerson(VALID_NAME, VALID_PHONE, VALID_EMAIL, INVALID_ADDRESS,
                                                        VALID_TAGS, VALID_MATRIC, VALID_REFLECTION,
                                                        VALID_STUDIO, EMPTY_SCORES);
        String expectedMessage = Address.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_nullAddress_throwsIllegalValueException() {
        JsonAdaptedPerson person = new JsonAdaptedPerson(
                VALID_NAME, VALID_PHONE, VALID_EMAIL, null, VALID_TAGS, VALID_MATRIC,
                VALID_REFLECTION, VALID_STUDIO, EMPTY_SCORES);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Address.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_invalidTags_throwsIllegalValueException() {
        List<JsonAdaptedTag> invalidTags = new ArrayList<>(VALID_TAGS);
        invalidTags.add(new JsonAdaptedTag(INVALID_TAG));
        JsonAdaptedPerson person =
                new JsonAdaptedPerson(VALID_NAME, VALID_PHONE, VALID_EMAIL, VALID_ADDRESS,
                                      invalidTags, VALID_MATRIC, VALID_REFLECTION, VALID_STUDIO, EMPTY_SCORES);
        assertThrows(IllegalValueException.class, person::toModelType);
    }

    @Test
    public void toModelType_invalidMatric_throwsIllegalValueException() {
        JsonAdaptedPerson person =
                new JsonAdaptedPerson(VALID_NAME, VALID_PHONE, VALID_EMAIL,
                        VALID_ADDRESS, VALID_TAGS, INVALID_MATRIC, VALID_REFLECTION, VALID_STUDIO, EMPTY_SCORES);
        String expectedMessage = Matric.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_invalidReflection_throwsIllegalValueException() {
        JsonAdaptedPerson person =
                new JsonAdaptedPerson(VALID_NAME, VALID_PHONE, VALID_EMAIL,
                        VALID_ADDRESS, VALID_TAGS, VALID_MATRIC, INVALID_REFLECTION, VALID_STUDIO, EMPTY_SCORES);
        String expectedMessage = Reflection.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_invalidStudio_throwsIllegalValueException() {
        JsonAdaptedPerson person =
                new JsonAdaptedPerson(VALID_NAME, VALID_PHONE, VALID_EMAIL, VALID_ADDRESS,
                                      VALID_TAGS, VALID_MATRIC, VALID_REFLECTION, INVALID_STUDIO, EMPTY_SCORES);
        String expectedMessage = Studio.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_invalidScores_throwsIllegalValueException() {
        List<JsonAdaptedExamScore> invalidScores = new ArrayList<>(VALID_SCORES);
        invalidScores.add(new JsonAdaptedExamScore(VALID_EXAM_NAME, VALID_EXAM_MAX_SCORE, -1));
        JsonAdaptedPerson person = new JsonAdaptedPerson(VALID_NAME, VALID_PHONE, VALID_EMAIL, VALID_ADDRESS,
                VALID_TAGS, VALID_MATRIC, VALID_REFLECTION, VALID_STUDIO, invalidScores);
        assertThrows(IllegalValueException.class, person::toModelType);
    }
}
