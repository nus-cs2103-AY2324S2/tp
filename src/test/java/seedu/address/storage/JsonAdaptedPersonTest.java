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
import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Phone;
import seedu.address.model.person.Salary;

public class JsonAdaptedPersonTest {
    private static final String INVALID_COMPANY_NAME = "123456789 123456789 123456789 123456789 123456789 123456789 "
            + "123456789 123456789 123456789 123456789 1";
    private static final String INVALID_NAME = "R@chel";
    private static final String INVALID_PHONE = "+651234";
    private static final String INVALID_ADDRESS = " ";
    private static final String INVALID_EMAIL = "example.com";
    private static final String INVALID_SALARY = "dsadasdasd";
    private static final String INVALID_TAG = "#friend";
    private static final String INVALID_INTERVIEWTIME = "04132022";
    private static final String INVALID_PROGRAMMING_LANG = "-Java";
    private static final String INVALID_PRIORITY = "5";

    private static final String VALID_COMPANY_NAME = BENSON.getCompanyName().toString();
    private static final String VALID_NAME = BENSON.getName().toString();
    private static final String VALID_PHONE = BENSON.getPhone().toString();
    private static final String VALID_EMAIL = BENSON.getEmail().toString();
    private static final String VALID_ADDRESS = BENSON.getAddress().toString();
    private static final String VALID_INTERVIEWTIME = BENSON.getDateTime().rawToString();
    private static final String VALID_SALARY = BENSON.getSalary().toString();
    private static final String VALID_INFO = BENSON.getInfo().toString();
    private static final List<JsonAdaptedTag> VALID_TAGS = BENSON.getTags().stream()
            .map(JsonAdaptedTag::new)
            .collect(Collectors.toList());
    private static final List<JsonAdaptedProgrammingLanguage> VALID_PROGRAMMING_LANG =
            BENSON.getProgrammingLanguages().stream()
            .map(JsonAdaptedProgrammingLanguage::new)
            .collect(Collectors.toList());
    private static final String VALID_PRIORITY = String.valueOf(BENSON.getPriority());

    @Test
    public void toModelType_validPersonDetails_returnsPerson() throws Exception {
        JsonAdaptedPerson person = new JsonAdaptedPerson(BENSON);
        assertEquals(BENSON, person.toModelType());
    }

    @Test
    public void toModelType_invalidName_throwsIllegalValueException() {
        JsonAdaptedPerson person =
                new JsonAdaptedPerson(VALID_COMPANY_NAME, INVALID_NAME,
                        VALID_PHONE, VALID_EMAIL, VALID_ADDRESS,
                        VALID_INTERVIEWTIME, VALID_SALARY, VALID_INFO,
                        VALID_TAGS, VALID_PROGRAMMING_LANG, VALID_PRIORITY);
        String expectedMessage = Name.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_nullName_throwsIllegalValueException() {
        JsonAdaptedPerson person = new JsonAdaptedPerson(
                VALID_COMPANY_NAME, null, VALID_PHONE, VALID_EMAIL, VALID_ADDRESS, VALID_INTERVIEWTIME,
                VALID_SALARY, VALID_INFO, VALID_TAGS, VALID_PROGRAMMING_LANG, VALID_PRIORITY);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_invalidPhone_throwsIllegalValueException() {
        JsonAdaptedPerson person = new JsonAdaptedPerson(VALID_COMPANY_NAME, VALID_NAME, INVALID_PHONE, VALID_EMAIL,
                VALID_ADDRESS, VALID_INTERVIEWTIME, VALID_SALARY, VALID_INFO,
                VALID_TAGS, VALID_PROGRAMMING_LANG, VALID_PRIORITY);
        String expectedMessage = Phone.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_nullPhone_throwsIllegalValueException() {
        JsonAdaptedPerson person = new JsonAdaptedPerson(
                VALID_COMPANY_NAME, VALID_NAME, null, VALID_EMAIL, VALID_ADDRESS, VALID_INTERVIEWTIME,
                VALID_SALARY, VALID_INFO, VALID_TAGS, VALID_PROGRAMMING_LANG, VALID_PRIORITY);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Phone.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_invalidEmail_throwsIllegalValueException() {
        JsonAdaptedPerson person = new JsonAdaptedPerson(VALID_COMPANY_NAME, VALID_NAME, VALID_PHONE, INVALID_EMAIL,
                VALID_ADDRESS, VALID_INTERVIEWTIME, VALID_SALARY, VALID_INFO,
                VALID_TAGS, VALID_PROGRAMMING_LANG, VALID_PRIORITY);
        String expectedMessage = Email.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_nullEmail_throwsIllegalValueException() {
        JsonAdaptedPerson person = new JsonAdaptedPerson(
                VALID_COMPANY_NAME, VALID_NAME, VALID_PHONE, null, VALID_ADDRESS, VALID_INTERVIEWTIME,
                VALID_SALARY, VALID_INFO, VALID_TAGS, VALID_PROGRAMMING_LANG, VALID_PRIORITY);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Email.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_invalidAddress_throwsIllegalValueException() {
        JsonAdaptedPerson person = new JsonAdaptedPerson(VALID_COMPANY_NAME, VALID_NAME, VALID_PHONE, VALID_EMAIL,
                INVALID_ADDRESS, VALID_INTERVIEWTIME, VALID_SALARY, VALID_INFO,
                VALID_TAGS, VALID_PROGRAMMING_LANG, VALID_PRIORITY);
        String expectedMessage = Address.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_nullAddress_throwsIllegalValueException() {
        JsonAdaptedPerson person = new JsonAdaptedPerson(VALID_COMPANY_NAME, VALID_NAME, VALID_PHONE, VALID_EMAIL,
                null, VALID_INTERVIEWTIME, VALID_SALARY, VALID_INFO,
                VALID_TAGS, VALID_PROGRAMMING_LANG, VALID_PRIORITY);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Address.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }
    @Test
    public void toModelType_invalidSalary_throwsIllegalValueException() {
        JsonAdaptedPerson person = new JsonAdaptedPerson(
                VALID_COMPANY_NAME, VALID_NAME, VALID_PHONE, VALID_EMAIL, VALID_ADDRESS, VALID_INTERVIEWTIME,
                INVALID_SALARY, VALID_INFO, VALID_TAGS, VALID_PROGRAMMING_LANG, VALID_PRIORITY);
        String expectedMessage = Salary.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_nullSalary_throwsIllegalValueException() {
        JsonAdaptedPerson person = new JsonAdaptedPerson(
                VALID_COMPANY_NAME, VALID_NAME, VALID_PHONE, VALID_EMAIL, VALID_ADDRESS, VALID_INTERVIEWTIME,
                null, VALID_INFO, VALID_TAGS, VALID_PROGRAMMING_LANG, VALID_PRIORITY);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Salary.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_invalidTags_throwsIllegalValueException() {
        List<JsonAdaptedTag> invalidTags = new ArrayList<>(VALID_TAGS);
        invalidTags.add(new JsonAdaptedTag(INVALID_TAG));
        List<JsonAdaptedProgrammingLanguage> invalidLanguages = new ArrayList<>(VALID_PROGRAMMING_LANG);
        invalidLanguages.add(new JsonAdaptedProgrammingLanguage(INVALID_PROGRAMMING_LANG));
        JsonAdaptedPerson person = new JsonAdaptedPerson(VALID_COMPANY_NAME, VALID_NAME, VALID_PHONE, VALID_EMAIL,
                VALID_ADDRESS, null, VALID_SALARY, VALID_INFO, invalidTags, invalidLanguages, VALID_PRIORITY);
        assertThrows(IllegalValueException.class, person::toModelType);
    }

    @Test
    public void toModelType_validPriority_success() throws Exception {
        JsonAdaptedPerson person = new JsonAdaptedPerson(VALID_COMPANY_NAME, VALID_NAME, VALID_PHONE,
                VALID_EMAIL, VALID_ADDRESS, VALID_INTERVIEWTIME,
                VALID_SALARY, VALID_INFO, VALID_TAGS, VALID_PROGRAMMING_LANG, "1");
        assertEquals(1, person.toModelType().getPriority());

        person = new JsonAdaptedPerson(VALID_COMPANY_NAME, VALID_NAME, VALID_PHONE,
                VALID_EMAIL, VALID_ADDRESS, VALID_INTERVIEWTIME,
                VALID_SALARY, VALID_INFO, VALID_TAGS, VALID_PROGRAMMING_LANG, "2");
        assertEquals(2, person.toModelType().getPriority());

        person = new JsonAdaptedPerson(VALID_COMPANY_NAME, VALID_NAME, VALID_PHONE,
                VALID_EMAIL, VALID_ADDRESS, VALID_INTERVIEWTIME,
                VALID_SALARY, VALID_INFO, VALID_TAGS, VALID_PROGRAMMING_LANG, "3");
        assertEquals(3, person.toModelType().getPriority());
    }

    @Test
    public void toModelType_invalidPriority_throwsIllegalValueException() {
        JsonAdaptedPerson person = new JsonAdaptedPerson(VALID_COMPANY_NAME, VALID_NAME, VALID_PHONE,
                VALID_EMAIL, VALID_ADDRESS, VALID_INTERVIEWTIME,
                VALID_SALARY, VALID_INFO, VALID_TAGS, VALID_PROGRAMMING_LANG, "-1");
        assertThrows(IllegalValueException.class, person::toModelType);

        person = new JsonAdaptedPerson(VALID_COMPANY_NAME, VALID_NAME, VALID_PHONE,
                VALID_EMAIL, VALID_ADDRESS, VALID_INTERVIEWTIME,
                VALID_SALARY, VALID_INFO, VALID_TAGS, VALID_PROGRAMMING_LANG, "5");
        assertThrows(IllegalValueException.class, person::toModelType);

        person = new JsonAdaptedPerson(VALID_COMPANY_NAME, VALID_NAME, VALID_PHONE,
                VALID_EMAIL, VALID_ADDRESS, VALID_INTERVIEWTIME,
                VALID_SALARY, VALID_INFO, VALID_TAGS, VALID_PROGRAMMING_LANG, "abc");
        assertThrows(IllegalValueException.class, person::toModelType);
    }

    @Test
    public void toModelType_nullPriority_throwsIllegalValueException() {
        JsonAdaptedPerson person = new JsonAdaptedPerson(VALID_COMPANY_NAME, VALID_NAME, VALID_PHONE,
                VALID_EMAIL, VALID_ADDRESS, VALID_INTERVIEWTIME,
                VALID_SALARY, VALID_INFO, VALID_TAGS, VALID_PROGRAMMING_LANG, null);
        assertThrows(IllegalValueException.class, person::toModelType);
    }

}
