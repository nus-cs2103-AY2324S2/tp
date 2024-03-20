package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.storage.JsonAdaptedPerson.MISSING_FIELD_MESSAGE_FORMAT;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalPersons.BENSON;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.person.Address;
import seedu.address.model.person.DateOfBirth;
import seedu.address.model.person.Name;
import seedu.address.model.person.Nric;
import seedu.address.model.person.Phone;
import seedu.address.model.person.Sex;
import seedu.address.model.person.Status;

public class JsonAdaptedPersonTest {
    private static final String INVALID_NRIC = "T-1";
    private static final String INVALID_NAME = "R@chel";
    private static final String INVALID_PHONE = "+651234";
    private static final String INVALID_ADDRESS = " ";
    private static final String INVALID_DOB = "MARCH 10th 2021";
    private static final String INVALID_SEX = "Male";
    private static final String INVALID_STATUS = "DYING";

    private static final String VALID_NRIC = BENSON.getNric().toString();
    private static final String VALID_NAME = BENSON.getName().toString();
    private static final String VALID_PHONE = BENSON.getPhone().toString();
    private static final String VALID_ADDRESS = BENSON.getAddress().toString();
    private static final String VALID_DOB = "2022-01-01";
    private static final String VALID_SEX = BENSON.getSex().toString();
    private static final String VALID_STATUS = BENSON.getStatus().toString();
    private static final String VALID_EMAIL = "benson123@gmail.com";
    private static final String VALID_COUNTRY = "Singapore";
    private static final String VALID_ALLERGIES = "Peanuts";
    private static final String VALID_BLOODTYPE = "A+";
    private static final String VALID_CONDITION = "Diabetes";
    private static final String VALID_DOA = "2024-01-01";
    private static final String VALID_DIAGNOSIS = "Covid";

    @Test
    public void toModelType_validPersonDetails_returnsPerson() throws Exception {
        JsonAdaptedPerson person = new JsonAdaptedPerson(BENSON);
        assertEquals(BENSON, person.toModelType());
    }

    @Test
    public void toModelType_invalidNric_throwsIllegalValueException() {
        JsonAdaptedPerson person =
                new JsonAdaptedPerson(INVALID_NRIC, VALID_NAME, VALID_PHONE,
                        VALID_ADDRESS, VALID_DOB, VALID_SEX, VALID_STATUS, VALID_EMAIL, VALID_COUNTRY,
                        VALID_ALLERGIES, VALID_BLOODTYPE, VALID_CONDITION, VALID_DOA, VALID_DIAGNOSIS);
        String expectedMessage = Nric.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_nullNric_throwsIllegalValueException() {
        JsonAdaptedPerson person =
                new JsonAdaptedPerson(null, VALID_NAME, VALID_PHONE,
                        VALID_ADDRESS, VALID_DOB, VALID_SEX, VALID_STATUS, VALID_EMAIL, VALID_COUNTRY,
                        VALID_ALLERGIES, VALID_BLOODTYPE, VALID_CONDITION, VALID_DOA, VALID_DIAGNOSIS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }
    @Test
    public void toModelType_invalidName_throwsIllegalValueException() {
        JsonAdaptedPerson person =
                new JsonAdaptedPerson(VALID_NRIC, INVALID_NAME, VALID_PHONE,
                        VALID_ADDRESS, VALID_DOB, VALID_SEX, VALID_STATUS, VALID_EMAIL, VALID_COUNTRY,
                        VALID_ALLERGIES, VALID_BLOODTYPE, VALID_CONDITION, VALID_DOA, VALID_DIAGNOSIS);
        String expectedMessage = Name.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_nullName_throwsIllegalValueException() {
        JsonAdaptedPerson person =
                new JsonAdaptedPerson(VALID_NRIC, null, VALID_PHONE,
                        VALID_ADDRESS, VALID_DOB, VALID_SEX, VALID_STATUS, VALID_EMAIL, VALID_COUNTRY,
                        VALID_ALLERGIES, VALID_BLOODTYPE, VALID_CONDITION, VALID_DOA, VALID_DIAGNOSIS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_invalidPhone_throwsIllegalValueException() {
        JsonAdaptedPerson person =
                new JsonAdaptedPerson(VALID_NRIC, VALID_NAME, INVALID_PHONE,
                        VALID_ADDRESS, VALID_DOB, VALID_SEX, VALID_STATUS, VALID_EMAIL, VALID_COUNTRY,
                        VALID_ALLERGIES, VALID_BLOODTYPE, VALID_CONDITION, VALID_DOA, VALID_DIAGNOSIS);
        String expectedMessage = Phone.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_nullPhone_throwsIllegalValueException() {
        JsonAdaptedPerson person =
                new JsonAdaptedPerson(VALID_NRIC, VALID_NAME, null,
                        VALID_ADDRESS, VALID_DOB, VALID_SEX, VALID_STATUS, VALID_EMAIL, VALID_COUNTRY,
                        VALID_ALLERGIES, VALID_BLOODTYPE, VALID_CONDITION, VALID_DOA, VALID_DIAGNOSIS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Phone.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_invalidAddress_throwsIllegalValueException() {
        JsonAdaptedPerson person =
                new JsonAdaptedPerson(VALID_NRIC, VALID_NAME, VALID_PHONE,
                        INVALID_ADDRESS, VALID_DOB, VALID_SEX, VALID_STATUS, VALID_EMAIL, VALID_COUNTRY,
                        VALID_ALLERGIES, VALID_BLOODTYPE, VALID_CONDITION, VALID_DOA, VALID_DIAGNOSIS);
        String expectedMessage = Address.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_nullAddress_throwsIllegalValueException() {
        JsonAdaptedPerson person =
                new JsonAdaptedPerson(VALID_NRIC, VALID_NAME, VALID_PHONE,
                        null, VALID_DOB, VALID_SEX, VALID_STATUS, VALID_EMAIL, VALID_COUNTRY,
                        VALID_ALLERGIES, VALID_BLOODTYPE, VALID_CONDITION, VALID_DOA, VALID_DIAGNOSIS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Address.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }
    @Test
    public void toModelType_invalidDob_throwsIllegalValueException() {
        JsonAdaptedPerson person =
                new JsonAdaptedPerson(VALID_NRIC, VALID_NAME, VALID_PHONE,
                        VALID_ADDRESS, INVALID_DOB, VALID_SEX, VALID_STATUS, VALID_EMAIL, VALID_COUNTRY,
                        VALID_ALLERGIES, VALID_BLOODTYPE, VALID_CONDITION, VALID_DOA, VALID_DIAGNOSIS);
        String expectedMessage = DateOfBirth.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_nullDob_throwsIllegalValueException() {
        JsonAdaptedPerson person =
                new JsonAdaptedPerson(VALID_NRIC, VALID_NAME, VALID_PHONE,
                        VALID_ADDRESS, null, VALID_SEX, VALID_STATUS, VALID_EMAIL, VALID_COUNTRY,
                        VALID_ALLERGIES, VALID_BLOODTYPE, VALID_CONDITION, VALID_DOA, VALID_DIAGNOSIS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Address.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }
    @Test
    public void toModelType_invalidSex_throwsIllegalValueException() {
        JsonAdaptedPerson person =
                new JsonAdaptedPerson(VALID_NRIC, VALID_NAME, VALID_PHONE,
                        VALID_ADDRESS, VALID_DOB, INVALID_SEX, VALID_STATUS, VALID_EMAIL, VALID_COUNTRY,
                        VALID_ALLERGIES, VALID_BLOODTYPE, VALID_CONDITION, VALID_DOA, VALID_DIAGNOSIS);
        String expectedMessage = Sex.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_nullSex_throwsIllegalValueException() {
        JsonAdaptedPerson person =
                new JsonAdaptedPerson(VALID_NRIC, VALID_NAME, VALID_PHONE,
                        VALID_ADDRESS, VALID_DOB, null, VALID_STATUS, VALID_EMAIL, VALID_COUNTRY,
                        VALID_ALLERGIES, VALID_BLOODTYPE, VALID_CONDITION, VALID_DOA, VALID_DIAGNOSIS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Address.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }
    @Test
    public void toModelType_invalidStatus_throwsIllegalValueException() {
        JsonAdaptedPerson person =
                new JsonAdaptedPerson(VALID_NRIC, VALID_NAME, VALID_PHONE,
                        VALID_ADDRESS, VALID_DOB, VALID_SEX, INVALID_STATUS, VALID_EMAIL, VALID_COUNTRY,
                        VALID_ALLERGIES, VALID_BLOODTYPE, VALID_CONDITION, VALID_DOA, VALID_DIAGNOSIS);
        String expectedMessage = Status.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_nullStatus_throwsIllegalValueException() {
        JsonAdaptedPerson person =
                new JsonAdaptedPerson(VALID_NRIC, VALID_NAME, VALID_PHONE,
                        VALID_ADDRESS, VALID_DOB, VALID_SEX, null, VALID_EMAIL, VALID_COUNTRY,
                        VALID_ALLERGIES, VALID_BLOODTYPE, VALID_CONDITION, VALID_DOA, VALID_DIAGNOSIS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Address.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }
}
