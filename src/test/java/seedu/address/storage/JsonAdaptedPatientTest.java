package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.storage.JsonAdaptedPerson.MISSING_FIELD_MESSAGE_FORMAT;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalPersons.BENSON;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.person.Nric;
import seedu.address.model.person.Name;
import seedu.address.model.person.DoB;
import seedu.address.model.person.Phone;

public class JsonAdaptedPatientTest {
    private static final String INVALID_NRIC = "Z123456";
    private static final String INVALID_NAME = "R@chel";
    private static final String INVALID_DOB = "2023-02-31";
    private static final String INVALID_PHONE = "+651234";

    private static final String VALID_NRIC = BENSON.getNric().toString();
    private static final String VALID_NAME = BENSON.getName().toString();
    private static final String VALID_DOB = BENSON.getDoB().dateOfBirth.toString();
    private static final String VALID_PHONE =  BENSON.getPhone().toString();

    @Test
    public void toModelType_validPersonDetails_returnsPerson() throws Exception {
        JsonAdaptedPerson person = new JsonAdaptedPerson(BENSON);
        assertEquals(BENSON, person.toModelType());
    }

    @Test
    public void toModelType_invalidNRIC_throwsIllegalValueException() {
        JsonAdaptedPerson person =
                new JsonAdaptedPerson("PATIENT", INVALID_NRIC, VALID_NAME, VALID_DOB, VALID_PHONE);
        String expectedMessage = Nric.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_nullNRIC_throwsIllegalValueException() {
        JsonAdaptedPerson person = new JsonAdaptedPerson("PATIENT", null, VALID_NAME, VALID_DOB, VALID_PHONE);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Nric.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }
    @Test
    public void toModelType_invalidName_throwsIllegalValueException() {
        JsonAdaptedPerson person =
                new JsonAdaptedPerson("PATIENT", VALID_NRIC, INVALID_NAME, VALID_DOB, VALID_PHONE);
        String expectedMessage = Name.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_nullName_throwsIllegalValueException() {
        JsonAdaptedPerson person = new JsonAdaptedPerson("PATIENT", VALID_NRIC, null, VALID_DOB, VALID_PHONE);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_invalidDoB_throwsIllegalValueException() {
        JsonAdaptedPerson person =
                new JsonAdaptedPerson("PATIENT", VALID_NRIC, VALID_NAME, INVALID_DOB, VALID_PHONE);
        String expectedMessage = DoB.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_nullDoB_throwsIllegalValueException() {
        JsonAdaptedPerson person = new JsonAdaptedPerson("PATIENT", VALID_NRIC, VALID_NAME, null, VALID_PHONE);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, DoB.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_invalidPhone_throwsIllegalValueException() {
        JsonAdaptedPerson person =
                new JsonAdaptedPerson("PATIENT", VALID_NRIC, VALID_NAME, VALID_DOB, INVALID_PHONE);
        String expectedMessage = Phone.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_nullPhone_throwsIllegalValueException() {
        JsonAdaptedPerson person = new JsonAdaptedPerson("PATIENT", VALID_NRIC, VALID_NAME, VALID_DOB, null);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Phone.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

}
