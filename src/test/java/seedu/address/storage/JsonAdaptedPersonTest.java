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
import seedu.address.model.person.BankDetails;
import seedu.address.model.person.EmploymentType;
import seedu.address.model.person.Name;
import seedu.address.model.person.Phone;
import seedu.address.model.person.Sex;
import seedu.address.model.person.WorkHours;

public class JsonAdaptedPersonTest {
    private static final String INVALID_FIRSTNAME = "R@chel";
    private static final String INVALID_LASTNAME = "T4.n";
    private static final String INVALID_PHONE = "+651234";
    private static final String INVALID_SEX = "h";
    private static final String INVALID_EMPLOYMENTTYPE = "ew";
    private static final String INVALID_ADDRESS = " ";

    private static final String INVALID_BANK_DETAILS = "057-3213-4123";
    private static final String INVALID_TAG = "#friend";

    private static final String VALID_FIRSTNAME = BENSON.getFirstName().toString();
    private static final String VALID_LASTNAME = BENSON.getLastName().toString();
    private static final String VALID_PHONE = BENSON.getPhone().toString();
    private static final String VALID_SEX = BENSON.getSex().toString();
    private static final String VALID_EMPLOYMENTTYPE = BENSON.getEmploymentType().toString();
    private static final String VALID_ADDRESS = BENSON.getAddress().toString();
    private static final String VALID_BANK_DETAILS = BENSON.getBankDetails().toString();
    private static final WorkHours VALID_WORK_HOURS = BENSON.getWorkHours();
    private static final List<JsonAdaptedTag> VALID_TAGS = BENSON.getTags().stream()
        .map(JsonAdaptedTag::new)
        .collect(Collectors.toList());

    @Test
    public void toModelType_validPersonDetails_returnsPerson() throws Exception {
        JsonAdaptedPerson person = new JsonAdaptedPerson(BENSON);
        assertEquals(BENSON, person.toModelType());
    }

    @Test
    public void toModelType_invalidFirstName_throwsIllegalValueException() {
        JsonAdaptedPerson person =
            new JsonAdaptedPerson(INVALID_FIRSTNAME, VALID_LASTNAME, VALID_PHONE, VALID_SEX, VALID_EMPLOYMENTTYPE,
                VALID_ADDRESS, VALID_BANK_DETAILS, VALID_WORK_HOURS, VALID_TAGS);
        String expectedMessage = Name.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_invalidLastName_throwsIllegalValueException() {
        JsonAdaptedPerson person =
            new JsonAdaptedPerson(VALID_FIRSTNAME, INVALID_LASTNAME, VALID_PHONE, VALID_SEX, VALID_EMPLOYMENTTYPE,
                VALID_ADDRESS, VALID_BANK_DETAILS, VALID_WORK_HOURS, VALID_TAGS);
        String expectedMessage = Name.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_nullFirstName_throwsIllegalValueException() {
        JsonAdaptedPerson person = new JsonAdaptedPerson(null, VALID_LASTNAME, VALID_PHONE, VALID_SEX,
            VALID_EMPLOYMENTTYPE, VALID_ADDRESS, VALID_BANK_DETAILS, VALID_WORK_HOURS, VALID_TAGS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_nullLastName_throwsIllegalValueException() {
        JsonAdaptedPerson person = new JsonAdaptedPerson(VALID_FIRSTNAME, null, VALID_PHONE, VALID_SEX,
            VALID_EMPLOYMENTTYPE, VALID_ADDRESS, VALID_BANK_DETAILS, VALID_WORK_HOURS, VALID_TAGS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_invalidPhone_throwsIllegalValueException() {
        JsonAdaptedPerson person =
            new JsonAdaptedPerson(VALID_FIRSTNAME, VALID_LASTNAME, INVALID_PHONE, VALID_SEX, VALID_EMPLOYMENTTYPE,
                    VALID_ADDRESS, VALID_BANK_DETAILS, VALID_WORK_HOURS, VALID_TAGS);
        String expectedMessage = Phone.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_nullPhone_throwsIllegalValueException() {
        JsonAdaptedPerson person = new JsonAdaptedPerson(VALID_FIRSTNAME, VALID_LASTNAME, null,
            VALID_SEX, VALID_EMPLOYMENTTYPE, VALID_ADDRESS, VALID_BANK_DETAILS, VALID_WORK_HOURS, VALID_TAGS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Phone.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_invalidSex_throwsIllegalValueException() {
        JsonAdaptedPerson person =
            new JsonAdaptedPerson(VALID_FIRSTNAME, VALID_LASTNAME, VALID_PHONE, INVALID_SEX, VALID_EMPLOYMENTTYPE,
                VALID_ADDRESS, VALID_BANK_DETAILS, VALID_WORK_HOURS, VALID_TAGS);
        String expectedMessage = Sex.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_nullSex_throwsIllegalValueException() {
        JsonAdaptedPerson person = new JsonAdaptedPerson(VALID_FIRSTNAME, VALID_LASTNAME,
            VALID_PHONE, null, VALID_EMPLOYMENTTYPE, VALID_ADDRESS, VALID_BANK_DETAILS, VALID_WORK_HOURS,
                VALID_TAGS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Sex.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_invalidEmploymentType_throwsIllegalValueException() {
        JsonAdaptedPerson person =
            new JsonAdaptedPerson(VALID_FIRSTNAME, VALID_LASTNAME, VALID_PHONE, VALID_SEX,
                INVALID_EMPLOYMENTTYPE, VALID_ADDRESS, VALID_BANK_DETAILS, VALID_WORK_HOURS, VALID_TAGS);
        String expectedMessage = EmploymentType.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_nullEmploymentType_throwsIllegalValueException() {
        JsonAdaptedPerson person = new JsonAdaptedPerson(VALID_FIRSTNAME, VALID_LASTNAME, VALID_PHONE,
            VALID_SEX, null, VALID_ADDRESS, VALID_BANK_DETAILS, VALID_WORK_HOURS, VALID_TAGS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, EmploymentType.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_nullAddress_throwsIllegalValueException() {
        JsonAdaptedPerson person = new JsonAdaptedPerson(VALID_FIRSTNAME, VALID_LASTNAME, VALID_PHONE,
            VALID_SEX, VALID_EMPLOYMENTTYPE, null, VALID_BANK_DETAILS, VALID_WORK_HOURS, VALID_TAGS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Address.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_invalidBankDetails_throwsIllegalValueException() {
        JsonAdaptedPerson person =
            new JsonAdaptedPerson(VALID_FIRSTNAME, VALID_LASTNAME, VALID_PHONE, VALID_SEX,
                VALID_EMPLOYMENTTYPE, VALID_ADDRESS, INVALID_BANK_DETAILS, VALID_WORK_HOURS, VALID_TAGS);
        String expectedMessage = BankDetails.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_nullBankDetails_throwsIllegalValueException() {
        JsonAdaptedPerson person = new JsonAdaptedPerson(VALID_FIRSTNAME, VALID_LASTNAME, VALID_PHONE,
            VALID_SEX, VALID_EMPLOYMENTTYPE, VALID_ADDRESS, null, VALID_WORK_HOURS, VALID_TAGS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, BankDetails.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_invalidTags_throwsIllegalValueException() {
        List<JsonAdaptedTag> invalidTags = new ArrayList<>(VALID_TAGS);
        invalidTags.add(new JsonAdaptedTag(INVALID_TAG));
        JsonAdaptedPerson person =
            new JsonAdaptedPerson(VALID_FIRSTNAME, VALID_LASTNAME, VALID_PHONE, VALID_SEX, VALID_EMPLOYMENTTYPE,
                    VALID_ADDRESS, VALID_BANK_DETAILS, VALID_WORK_HOURS, invalidTags);
        assertThrows(IllegalValueException.class, person::toModelType);
    }

}
