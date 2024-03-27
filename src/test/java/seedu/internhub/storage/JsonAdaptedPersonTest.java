package seedu.internhub.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.internhub.storage.JsonAdaptedPerson.MISSING_FIELD_MESSAGE_FORMAT;
import static seedu.internhub.testutil.Assert.assertThrows;
import static seedu.internhub.testutil.TypicalPersons.BENSON;

import org.junit.jupiter.api.Test;

import seedu.internhub.commons.exceptions.IllegalValueException;
import seedu.internhub.model.person.Address;
import seedu.internhub.model.person.Email;
import seedu.internhub.model.person.InternDuration;
import seedu.internhub.model.person.InterviewDate;
import seedu.internhub.model.person.JobDescription;
import seedu.internhub.model.person.Name;
import seedu.internhub.model.person.Person;
import seedu.internhub.model.person.Phone;
import seedu.internhub.model.person.Salary;
import seedu.internhub.model.person.Tag;

public class JsonAdaptedPersonTest {
    private static final String INVALID_NAME = "R@chel";
    private static final String INVALID_PHONE = "+651234";
    private static final String INVALID_ADDRESS = " ";
    private static final String INVALID_EMAIL = "example.com";
    private static final String INVALID_TAG = "#friend";
    private static final String INVALID_JOB_DESCRIPTION = " ";
    private static final String INVALID_INTERVIEW_DATE = "";
    private static final String INVALID_INTERN_DURATION = " ";
    private static final String INVALID_SALARY = "0";

    private static final String VALID_NAME = BENSON.getCompanyName().toString();
    private static final String VALID_PHONE = BENSON.getPhone().toString();
    private static final String VALID_EMAIL = BENSON.getEmail().toString();
    private static final String VALID_ADDRESS = BENSON.getAddress().toString();
    private static final String VALID_TAGS = BENSON.getTag().getTagShort();
    private static final String VALID_JOB_DESCRIPTION = BENSON.getJobDescription().toString();
    private static final String VALID_INTERVIEW_DATE = BENSON.getInterviewDate().toString();
    private static final String VALID_INTERN_DURATION = BENSON.getInternDuration().toString();
    private static final String VALID_SALARY = BENSON.getSalary().toString();
    private static final String VALID_NOTE = BENSON.getNote().toString();

    @Test
    public void toModelType_validPersonDetails_returnsPerson() throws Exception {
        JsonAdaptedPerson person = new JsonAdaptedPerson(BENSON);
        assertEquals(BENSON, person.toModelType());
    }

    @Test
    public void toModelType_invalidName_throwsIllegalValueException() {
        JsonAdaptedPerson person =
                new JsonAdaptedPerson(INVALID_NAME, VALID_PHONE, VALID_EMAIL, VALID_ADDRESS, VALID_TAGS,
                        VALID_JOB_DESCRIPTION, VALID_INTERVIEW_DATE, VALID_INTERN_DURATION, VALID_SALARY, VALID_NOTE);
        String expectedMessage = Name.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_nullName_throwsIllegalValueException() {
        JsonAdaptedPerson person = new JsonAdaptedPerson(null, VALID_PHONE, VALID_EMAIL, VALID_ADDRESS, VALID_TAGS,
                VALID_JOB_DESCRIPTION, VALID_INTERVIEW_DATE, VALID_INTERN_DURATION, VALID_SALARY, VALID_NOTE);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_invalidPhone_throwsIllegalValueException() {
        JsonAdaptedPerson person =
                new JsonAdaptedPerson(VALID_NAME, INVALID_PHONE, VALID_EMAIL, VALID_ADDRESS, VALID_TAGS,
                        VALID_JOB_DESCRIPTION, VALID_INTERVIEW_DATE, VALID_INTERN_DURATION, VALID_SALARY, VALID_NOTE);
        String expectedMessage = Phone.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_nullPhone_throwsIllegalValueException() {
        JsonAdaptedPerson person = new JsonAdaptedPerson(VALID_NAME, null, VALID_EMAIL, VALID_ADDRESS, VALID_TAGS,
                VALID_JOB_DESCRIPTION, VALID_INTERVIEW_DATE, VALID_INTERN_DURATION, VALID_SALARY, VALID_NOTE);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Phone.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_invalidEmail_throwsIllegalValueException() {
        JsonAdaptedPerson person =
                new JsonAdaptedPerson(VALID_NAME, VALID_PHONE, INVALID_EMAIL, VALID_ADDRESS, VALID_TAGS,
                        VALID_JOB_DESCRIPTION, VALID_INTERVIEW_DATE, VALID_INTERN_DURATION, VALID_SALARY, VALID_NOTE);
        String expectedMessage = Email.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_nullEmail_throwsIllegalValueException() {
        JsonAdaptedPerson person = new JsonAdaptedPerson(VALID_NAME, VALID_PHONE, null, VALID_ADDRESS, VALID_TAGS,
                VALID_JOB_DESCRIPTION, VALID_INTERVIEW_DATE, VALID_INTERN_DURATION, VALID_SALARY, VALID_NOTE);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Email.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_nullAddress_throwsIllegalValueException() {
        JsonAdaptedPerson person = new JsonAdaptedPerson(VALID_NAME, VALID_PHONE, VALID_EMAIL, null, VALID_TAGS,
                VALID_JOB_DESCRIPTION, VALID_INTERVIEW_DATE, VALID_INTERN_DURATION, VALID_SALARY, VALID_NOTE);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Address.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_nullTag_throwsIllegalValueException() {
        JsonAdaptedPerson person = new JsonAdaptedPerson(VALID_NAME, VALID_PHONE, VALID_EMAIL, VALID_ADDRESS, null,
                VALID_JOB_DESCRIPTION, VALID_INTERVIEW_DATE, VALID_INTERN_DURATION, VALID_SALARY, VALID_NOTE);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Tag.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_invalidTags_throwsIllegalValueException() {
        JsonAdaptedPerson person =
                new JsonAdaptedPerson(VALID_NAME, VALID_PHONE, VALID_EMAIL, VALID_ADDRESS, INVALID_TAG,
                        VALID_JOB_DESCRIPTION, VALID_INTERVIEW_DATE, VALID_INTERN_DURATION, VALID_SALARY, VALID_NOTE);
        assertThrows(IllegalValueException.class, person::toModelType);
    }

    @Test
    public void toModelType_nullJobDescription_throwsIllegalValueException() {
        JsonAdaptedPerson person = new JsonAdaptedPerson(VALID_NAME, VALID_PHONE, VALID_EMAIL,
                VALID_ADDRESS, VALID_TAGS, null, VALID_INTERVIEW_DATE, VALID_INTERN_DURATION, VALID_SALARY,
                VALID_NOTE);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, JobDescription.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_invalidJobDescription_throwsIllegalValueException() {
        JsonAdaptedPerson person =
                new JsonAdaptedPerson(VALID_NAME, VALID_PHONE, VALID_EMAIL, VALID_ADDRESS, VALID_TAGS,
                        INVALID_JOB_DESCRIPTION, VALID_INTERVIEW_DATE, VALID_INTERN_DURATION, VALID_SALARY, VALID_NOTE);
        String expectedMessage = JobDescription.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_nullInterviewDate_returnPerson() throws Exception {
        JsonAdaptedPerson person = new JsonAdaptedPerson(VALID_NAME, VALID_PHONE, VALID_EMAIL,
                VALID_ADDRESS, VALID_TAGS, VALID_JOB_DESCRIPTION, INVALID_INTERVIEW_DATE,
                VALID_INTERN_DURATION, VALID_SALARY, VALID_NOTE);
        Person person1 = new Person(BENSON.getCompanyName(), BENSON.getPhone(), BENSON.getEmail(), BENSON.getAddress(),
                BENSON.getTag(), BENSON.getJobDescription(), new InterviewDate(null),
                BENSON.getInternDuration(), BENSON.getSalary(), BENSON.getNote());
        assertEquals(person1, person.toModelType());
    }

    @Test
    public void toModelType_validPersonDetails_returnsPe() throws Exception {
        JsonAdaptedPerson person = new JsonAdaptedPerson(BENSON);
        assertEquals(BENSON, person.toModelType());
    }


    @Test
    public void toModelType_nullInternDuration_throwsIllegalValueException() {
        JsonAdaptedPerson person = new JsonAdaptedPerson(VALID_NAME, VALID_PHONE, VALID_EMAIL, VALID_ADDRESS,
                VALID_TAGS, VALID_JOB_DESCRIPTION, VALID_INTERVIEW_DATE, null, VALID_SALARY, VALID_NOTE);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, InternDuration.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_invalidInternDuration_throwsIllegalValueException() {
        JsonAdaptedPerson person =
                new JsonAdaptedPerson(VALID_NAME, VALID_PHONE, VALID_EMAIL, VALID_ADDRESS, VALID_TAGS,
                        VALID_JOB_DESCRIPTION, VALID_INTERVIEW_DATE, INVALID_INTERN_DURATION, VALID_SALARY, VALID_NOTE);
        String expectedMessage = InternDuration.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_nullSalary_throwsIllegalValueException() {
        JsonAdaptedPerson person = new JsonAdaptedPerson(VALID_NAME, VALID_PHONE, VALID_EMAIL,
                VALID_ADDRESS, VALID_TAGS, VALID_JOB_DESCRIPTION, VALID_INTERVIEW_DATE, VALID_INTERN_DURATION,
                null, VALID_NOTE);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Salary.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_invalidSalary_throwsIllegalValueException() {
        JsonAdaptedPerson person =
                new JsonAdaptedPerson(VALID_NAME, VALID_PHONE, VALID_EMAIL, VALID_ADDRESS, VALID_TAGS,
                        VALID_JOB_DESCRIPTION, VALID_INTERVIEW_DATE, VALID_INTERN_DURATION, INVALID_SALARY, VALID_NOTE);
        String expectedMessage = Salary.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

}
