package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.storage.JsonAdaptedPerson.MISSING_FIELD_MESSAGE_FORMAT;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalPersons.BENSON;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.person.Email;
import seedu.address.model.person.Grade;
import seedu.address.model.person.Name;
import seedu.address.model.person.StudentId;

public class JsonAdaptedPersonTest {
    private static final String INVALID_NAME = "R@chel";
    private static final String INVALID_STUDENTID = "P034&";
    private static final String INVALID_PHONE = "+651234";
    private static final String INVALID_GRADE = "F-";
    private static final String INVALID_EMAIL = "example.com";
    private static final String INVALID_GROUP = "#group";


    private static final String VALID_NAME = BENSON.getName().toString();
    private static final String VALID_STUDENTID = BENSON.getStudentId().toString();

    private static final String VALID_EMAIL = BENSON.getEmail().toString();
    private static final String VALID_GRADE = BENSON.getGrade().toString();
    private static final List<JsonAdaptedGroup> VALID_GROUPS = BENSON.getGroups().stream()
            .map(JsonAdaptedGroup::new)
            .collect(Collectors.toList());

    @Test
    public void toModelType_validPersonDetails_returnsPerson() throws Exception {
        JsonAdaptedPerson person = new JsonAdaptedPerson(BENSON);
        assertEquals(BENSON, person.toModelType());
    }

    @Test
    public void toModelType_invalidName_throwsIllegalValueException() {
        JsonAdaptedPerson person =

                new JsonAdaptedPerson(INVALID_NAME, VALID_STUDENTID, VALID_EMAIL, VALID_GRADE,
                        VALID_GROUPS);

        String expectedMessage = Name.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_nullName_throwsIllegalValueException() {

        JsonAdaptedPerson person =
                new JsonAdaptedPerson(null, VALID_STUDENTID, VALID_EMAIL, VALID_GRADE, VALID_GROUPS);

        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_invalidStudentId_throwsIllegalValueException() {
        JsonAdaptedPerson person =
                new JsonAdaptedPerson(VALID_NAME, INVALID_STUDENTID, VALID_EMAIL, VALID_GRADE,
                        VALID_GROUPS);
        String expectedMessage = StudentId.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_nullStudentId_throwsIllegalValueException() {
        JsonAdaptedPerson person = new JsonAdaptedPerson(VALID_NAME, null, VALID_EMAIL,
                VALID_GRADE, VALID_GROUPS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, StudentId.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_invalidEmail_throwsIllegalValueException() {
        JsonAdaptedPerson person =

                new JsonAdaptedPerson(VALID_NAME, VALID_STUDENTID, INVALID_EMAIL, VALID_GRADE,
                        VALID_GROUPS);

        String expectedMessage = Email.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_nullEmail_throwsIllegalValueException() {

        JsonAdaptedPerson person = new JsonAdaptedPerson(VALID_NAME, VALID_STUDENTID, null,
            VALID_GRADE, VALID_GROUPS);

        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Email.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_invalidGrade_throwsIllegalValueException() {
        JsonAdaptedPerson person =

                new JsonAdaptedPerson(VALID_NAME, VALID_STUDENTID, VALID_EMAIL, INVALID_GRADE,
                        VALID_GROUPS);
        String expectedMessage = Grade.MESSAGE_CONSTRAINTS;

        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test

    public void toModelType_nullGrade_throwsIllegalValueException() {
        JsonAdaptedPerson person = new JsonAdaptedPerson(VALID_NAME, VALID_STUDENTID, VALID_EMAIL,
                null, VALID_GROUPS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Grade.class.getSimpleName());

        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_invalidGroups_throwsIllegalValueException() {
        List<JsonAdaptedGroup> invalidGroups = new ArrayList<>(VALID_GROUPS);
        invalidGroups.add(new JsonAdaptedGroup(INVALID_GROUP));
        JsonAdaptedPerson person =

                new JsonAdaptedPerson(VALID_NAME, VALID_STUDENTID, VALID_EMAIL, VALID_GRADE,
                        invalidGroups);

        assertThrows(IllegalValueException.class, person::toModelType);
    }

}
