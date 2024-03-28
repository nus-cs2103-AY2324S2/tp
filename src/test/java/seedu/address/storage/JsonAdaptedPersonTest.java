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
import seedu.address.model.person.Name;
import seedu.address.model.person.NusId;
import seedu.address.model.person.Phone;
import seedu.address.model.person.Remark;
import seedu.address.model.person.Schedule;
import seedu.address.model.person.Tag;

public class JsonAdaptedPersonTest {
    private static final String INVALID_NAME = "R@chel";
    private static final String INVALID_NUSID = "e1234567";
    private static final String INVALID_PHONE = "+651234";
    private static final String INVALID_TAG = " ";
    private static final String INVALID_EMAIL = "example.com";
    private static final String INVALID_GROUP = "#friend";
    private static final String INVALID_SCHEDULE = "2024-12-12";

    private static final String VALID_NUSID = BENSON.getNusId().toString();
    private static final String VALID_NAME = BENSON.getName().toString();
    private static final String VALID_PHONE = BENSON.getPhone().toString();
    private static final String VALID_EMAIL = BENSON.getEmail().toString();
    private static final String VALID_TAG = BENSON.getTag().toString();
    private static final String VALID_SCHEDULE = BENSON.getSchedule().toString();
    private static final String VALID_REMARK = BENSON.getRemark().toString();

    private static final List<JsonAdaptedGroup> VALID_GROUPS = BENSON.getGroups().stream()
            .map(JsonAdaptedGroup::new)
            .collect(Collectors.toList());

    @Test
    public void toModelType_validPersonDetails_returnsPerson() throws Exception {
        JsonAdaptedPerson person = new JsonAdaptedPerson(BENSON);
        assertEquals(BENSON, person.toModelType());
    }

    @Test
    public void toModelType_invalidNusId_throwsIllegalValueException() {
        JsonAdaptedPerson person =
                new JsonAdaptedPerson(INVALID_NUSID, VALID_NAME, VALID_PHONE,
                        VALID_EMAIL, VALID_TAG, VALID_GROUPS, VALID_SCHEDULE, VALID_REMARK);
        String expectedMessage = NusId.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_nullNusId_throwsIllegalValueException() {
        JsonAdaptedPerson person =
                new JsonAdaptedPerson(null, VALID_NAME, VALID_PHONE,
                        VALID_EMAIL, VALID_TAG, VALID_GROUPS, VALID_SCHEDULE, VALID_REMARK);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, NusId.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_invalidName_throwsIllegalValueException() {
        JsonAdaptedPerson person =
                new JsonAdaptedPerson(VALID_NUSID, INVALID_NAME, VALID_PHONE,
                        VALID_EMAIL, VALID_TAG, VALID_GROUPS, VALID_SCHEDULE, VALID_REMARK);
        String expectedMessage = Name.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_nullName_throwsIllegalValueException() {
        JsonAdaptedPerson person =
                new JsonAdaptedPerson(VALID_NUSID, null, VALID_PHONE,
                        VALID_EMAIL, VALID_TAG, VALID_GROUPS, VALID_SCHEDULE, VALID_REMARK);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_invalidPhone_throwsIllegalValueException() {
        JsonAdaptedPerson person =
                new JsonAdaptedPerson(VALID_NUSID, VALID_NAME, INVALID_PHONE,
                        VALID_EMAIL, VALID_TAG, VALID_GROUPS, VALID_SCHEDULE, VALID_REMARK);
        String expectedMessage = Phone.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_nullPhone_throwsIllegalValueException() {
        JsonAdaptedPerson person =
                new JsonAdaptedPerson(VALID_NUSID, VALID_NAME, null,
                        VALID_EMAIL, VALID_TAG, VALID_GROUPS, VALID_SCHEDULE, VALID_REMARK);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Phone.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_invalidEmail_throwsIllegalValueException() {
        JsonAdaptedPerson person =
                new JsonAdaptedPerson(VALID_NUSID, VALID_NAME, VALID_PHONE,
                        INVALID_EMAIL, VALID_TAG, VALID_GROUPS, VALID_SCHEDULE, VALID_REMARK);
        String expectedMessage = Email.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_nullEmail_throwsIllegalValueException() {
        JsonAdaptedPerson person =
                new JsonAdaptedPerson(VALID_NUSID, VALID_NAME, VALID_PHONE,
                        null, VALID_TAG, VALID_GROUPS, VALID_SCHEDULE, VALID_REMARK);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Email.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_invalidTag_throwsIllegalValueException() {
        JsonAdaptedPerson person =
                new JsonAdaptedPerson(VALID_NUSID, VALID_NAME, VALID_PHONE,
                        VALID_EMAIL, INVALID_TAG, VALID_GROUPS, VALID_SCHEDULE, VALID_REMARK);
        String expectedMessage = Tag.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_nullTag_throwsIllegalValueException() {
        JsonAdaptedPerson person =
                new JsonAdaptedPerson(VALID_NUSID, VALID_NAME, VALID_PHONE,
                        VALID_EMAIL, null, VALID_GROUPS, VALID_SCHEDULE, VALID_REMARK);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Tag.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_invalidGroups_throwsIllegalValueException() {
        List<JsonAdaptedGroup> invalidGroups = new ArrayList<>(VALID_GROUPS);
        invalidGroups.add(new JsonAdaptedGroup(INVALID_GROUP));
        JsonAdaptedPerson person =
                new JsonAdaptedPerson(VALID_NUSID, VALID_NAME, VALID_PHONE,
                        VALID_EMAIL, VALID_TAG, invalidGroups, VALID_SCHEDULE, VALID_REMARK);
        assertThrows(IllegalValueException.class, person::toModelType);
    }

    @Test
    public void toModelType_invalidSchedule_throwsIllegalValueException() {
        JsonAdaptedPerson person =
                new JsonAdaptedPerson(VALID_NUSID, VALID_NAME, VALID_PHONE,
                        VALID_EMAIL, VALID_TAG, VALID_GROUPS, INVALID_SCHEDULE, VALID_REMARK);
        String expectedMessage = Schedule.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_nullSchedule_throwsIllegalValueException() {
        JsonAdaptedPerson person =
                new JsonAdaptedPerson(VALID_NUSID, VALID_NAME, VALID_PHONE,
                        VALID_EMAIL, VALID_TAG, VALID_GROUPS, null, VALID_REMARK);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Schedule.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_nullRemark_throwsIllegalValueException() {
        JsonAdaptedPerson person =
                new JsonAdaptedPerson(VALID_NUSID, VALID_NAME, VALID_PHONE,
                        VALID_EMAIL, VALID_TAG, VALID_GROUPS, VALID_SCHEDULE, null);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Remark.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }
}
