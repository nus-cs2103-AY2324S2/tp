package staffconnect.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static staffconnect.storage.JsonAdaptedPerson.MISSING_FIELD_MESSAGE_FORMAT;
import static staffconnect.testutil.Assert.assertThrows;
import static staffconnect.testutil.TypicalPersons.BENSON;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;

import staffconnect.commons.exceptions.IllegalValueException;
import staffconnect.model.person.Email;
import staffconnect.model.person.Faculty;
import staffconnect.model.person.Module;
import staffconnect.model.person.Name;
import staffconnect.model.person.Phone;
import staffconnect.model.person.Venue;

public class JsonAdaptedPersonTest {
    private static final String INVALID_NAME = "R@chel";
    private static final String INVALID_PHONE = "+651234";
    private static final String INVALID_EMAIL = "example.com";
    private static final String INVALID_MODULE = " ";
    private static final String INVALID_FACULTY = "faculty";
    private static final String INVALID_VENUE = " ";
    private static final String INVALID_TAG = "#friend";
    private static final String INVALID_AVAILABILITY = "m0n";

    private static final String INVALID_MEETING_DESCRIPTION = "*!&@#&*@*&@*";

    private static final String INVALID_MEETING_DATE = "12-04-2023 18:00";

    private static final String VALID_NAME = BENSON.getName().toString();
    private static final String VALID_PHONE = BENSON.getPhone().toString();
    private static final String VALID_EMAIL = BENSON.getEmail().toString();
    private static final String VALID_MODULE = BENSON.getModule().toString();
    private static final String VALID_MEETING_DESCRIPTION = "Meet for midterms";

    private static final String VALID_MEETING_DATE = "12-04-2023 18:00";

    private static final JsonAdaptedMeeting VALID_MEETING_JSON =
        new JsonAdaptedMeeting(VALID_MEETING_DESCRIPTION, VALID_MEETING_DATE);
    private static final List<JsonAdaptedMeeting> VALID_MEETING =
        Stream.of(VALID_MEETING_JSON).collect(Collectors.toList());

    private static final List<JsonAdaptedTag> VALID_TAGS =
        BENSON.getTags().stream().map(JsonAdaptedTag::new).collect(Collectors.toList());
    private static final String VALID_FACULTY = BENSON.getFaculty().toString();
    private static final String VALID_VENUE = BENSON.getVenue().toString();
    private static final List<JsonAdaptedAvailability> VALID_AVAILABILITIES = BENSON.getAvailabilities()
        .stream()
        .map(JsonAdaptedAvailability::new)
        .collect(Collectors.toList());

    @Test
    public void toModelType_validPersonDetails_returnsPerson() throws Exception {
        JsonAdaptedPerson person = new JsonAdaptedPerson(BENSON);
        assertEquals(BENSON, person.toModelType());
    }

    @Test
    public void toModelType_invalidName_throwsIllegalValueException() {
        JsonAdaptedPerson person = new JsonAdaptedPerson(INVALID_NAME, VALID_PHONE, VALID_EMAIL,
                                                         VALID_MODULE, VALID_FACULTY, VALID_VENUE, VALID_TAGS,
                                                         VALID_AVAILABILITIES, VALID_MEETING);
        String expectedMessage = Name.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_nullName_throwsIllegalValueException() {
        JsonAdaptedPerson person = new JsonAdaptedPerson(null, VALID_PHONE, VALID_EMAIL,
                                                         VALID_MODULE, VALID_FACULTY, VALID_VENUE, VALID_TAGS,
                                                         VALID_AVAILABILITIES, VALID_MEETING);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_invalidPhone_throwsIllegalValueException() {
        JsonAdaptedPerson person = new JsonAdaptedPerson(VALID_NAME, INVALID_PHONE, VALID_EMAIL,
                                                         VALID_MODULE, VALID_FACULTY, VALID_VENUE, VALID_TAGS,
                                                         VALID_AVAILABILITIES, VALID_MEETING);
        String expectedMessage = Phone.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_nullPhone_throwsIllegalValueException() {
        JsonAdaptedPerson person = new JsonAdaptedPerson(VALID_NAME, null, VALID_EMAIL,
                                                         VALID_MODULE, VALID_FACULTY, VALID_VENUE, VALID_TAGS,
                                                         VALID_AVAILABILITIES, VALID_MEETING);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Phone.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_invalidEmail_throwsIllegalValueException() {
        JsonAdaptedPerson person = new JsonAdaptedPerson(VALID_NAME, VALID_PHONE, INVALID_EMAIL,
                                                         VALID_MODULE, VALID_FACULTY, VALID_VENUE, VALID_TAGS,
                                                         VALID_AVAILABILITIES, VALID_MEETING);
        String expectedMessage = Email.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_nullEmail_throwsIllegalValueException() {
        JsonAdaptedPerson person = new JsonAdaptedPerson(VALID_NAME, VALID_PHONE, null,
                                                         VALID_MODULE, VALID_FACULTY, VALID_VENUE, VALID_TAGS,
                                                         VALID_AVAILABILITIES, VALID_MEETING);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Email.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_invalidFaculty_throwsIllegalValueException() {
        JsonAdaptedPerson person = new JsonAdaptedPerson(VALID_NAME, VALID_PHONE, VALID_EMAIL,
                                                         VALID_MODULE, INVALID_FACULTY, VALID_VENUE, VALID_TAGS,
                                                         VALID_AVAILABILITIES, VALID_MEETING);
        String expectedMessage = Faculty.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_nullFaculty_throwsIllegalValueException() {
        JsonAdaptedPerson person = new JsonAdaptedPerson(VALID_NAME, VALID_PHONE, VALID_EMAIL,
                                                         VALID_MODULE, null, VALID_VENUE, VALID_TAGS,
                                                         VALID_AVAILABILITIES, VALID_MEETING);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Faculty.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_invalidVenue_throwsIllegalValueException() {
        JsonAdaptedPerson person = new JsonAdaptedPerson(VALID_NAME, VALID_PHONE, VALID_EMAIL,
                                                         VALID_MODULE, VALID_FACULTY, INVALID_VENUE, VALID_TAGS,
                                                         VALID_AVAILABILITIES, VALID_MEETING);
        String expectedMessage = Venue.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_nullVenue_throwsIllegalValueException() {
        JsonAdaptedPerson person = new JsonAdaptedPerson(VALID_NAME, VALID_PHONE, VALID_EMAIL,
                                                         VALID_MODULE, VALID_FACULTY, null, VALID_TAGS,
                                                         VALID_AVAILABILITIES, VALID_MEETING);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Venue.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_invalidModule_throwsIllegalValueException() {
        JsonAdaptedPerson person = new JsonAdaptedPerson(VALID_NAME, VALID_PHONE, VALID_EMAIL,
                                                         INVALID_MODULE, VALID_FACULTY, VALID_VENUE, VALID_TAGS,
                                                         VALID_AVAILABILITIES, VALID_MEETING);
        String expectedMessage = Module.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_nullModule_throwsIllegalValueException() {
        JsonAdaptedPerson person = new JsonAdaptedPerson(VALID_NAME, VALID_PHONE, VALID_EMAIL,
                                                         null, VALID_FACULTY, VALID_VENUE, VALID_TAGS,
                                                         VALID_AVAILABILITIES, VALID_MEETING);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Module.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_invalidTags_throwsIllegalValueException() {
        List<JsonAdaptedTag> invalidTags = new ArrayList<>(VALID_TAGS);
        invalidTags.add(new JsonAdaptedTag(INVALID_TAG));
        JsonAdaptedPerson person = new JsonAdaptedPerson(VALID_NAME, VALID_PHONE, VALID_EMAIL,
                                                         VALID_MODULE, VALID_FACULTY, VALID_VENUE, invalidTags,
                                                         VALID_AVAILABILITIES, VALID_MEETING);
        assertThrows(IllegalValueException.class, person::toModelType);
    }

    @Test
    public void toModelType_invalidMeetingDescription_throwsIllegalValueException() {
        List<JsonAdaptedMeeting> invalidMeeting = new ArrayList<>(VALID_MEETING);
        invalidMeeting.add(new JsonAdaptedMeeting(INVALID_MEETING_DESCRIPTION, VALID_MEETING_DATE));
        JsonAdaptedPerson person =
            new JsonAdaptedPerson(VALID_NAME, VALID_PHONE, VALID_EMAIL, VALID_MODULE, VALID_FACULTY, VALID_VENUE,
                                  VALID_TAGS, VALID_AVAILABILITIES, invalidMeeting);
        assertThrows(IllegalValueException.class, person::toModelType);
    }

    @Test
    public void toModelType_invalidMeetingDate_throwsIllegalValueException() {
        List<JsonAdaptedMeeting> invalidMeeting = new ArrayList<>(VALID_MEETING);
        invalidMeeting.add(new JsonAdaptedMeeting(VALID_MEETING_DESCRIPTION, INVALID_MEETING_DATE));
        JsonAdaptedPerson person =
            new JsonAdaptedPerson(VALID_NAME, VALID_PHONE, VALID_EMAIL, VALID_MODULE,
                                  VALID_FACULTY, VALID_VENUE, VALID_TAGS, VALID_AVAILABILITIES, invalidMeeting);
        assertThrows(IllegalValueException.class, person::toModelType);
    }
    @Test
    public void toModelType_invalidAvailabilities_throwsIllegalValueException() {
        List<JsonAdaptedAvailability> invalidAvailabilities = new ArrayList<>(VALID_AVAILABILITIES);
        invalidAvailabilities.add(new JsonAdaptedAvailability(INVALID_AVAILABILITY));
        JsonAdaptedPerson person = new JsonAdaptedPerson(VALID_NAME, VALID_PHONE, VALID_EMAIL, VALID_MODULE,
                                                         VALID_FACULTY, VALID_VENUE, VALID_TAGS,
                                                         invalidAvailabilities, VALID_MEETING);
        assertThrows(IllegalValueException.class, person::toModelType);
    }

}
