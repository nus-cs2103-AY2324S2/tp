package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.storage.JsonAdaptedPerson.MISSING_FIELD_MESSAGE_FORMAT;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalPersons.BENSON;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Phone;

public class JsonAdaptedPersonTest {
    private static final String INVALID_NAME = "R@chel";
    private static final String INVALID_PHONE = "+651234";
    private static final String INVALID_CLASS_GROUP = " ";
    private static final String INVALID_EMAIL = "example.com";
    private static final String INVALID_TELEGRAM = "racheljames";
    private static final String INVALID_GITHUB = "github username";

    private static final String VALID_NAME = BENSON.getName().toString();
    private static final String VALID_PHONE = BENSON.getPhone().get().value;
    private static final String VALID_EMAIL = BENSON.getEmail().toString();
    private static final String VALID_CLASS_GROUP = BENSON.getClassGroup().toString();
    private static final String VALID_TELEGRAM = BENSON.getTelegram().get().telegramId;
    private static final String VALID_GITHUB = BENSON.getGithub().get().githubId;
    private static final ArrayList<String> VALID_NOTES = BENSON.getNotes().getAsStrings();

    @Test
    public void toModelType_validPersonDetails_returnsPerson() throws Exception {
        JsonAdaptedPerson person = new JsonAdaptedPerson(BENSON);
        assertEquals(BENSON, person.toModelType());
    }

    @Test
    public void toModelType_invalidName_throwsIllegalValueException() {
        JsonAdaptedPerson person =
                new JsonAdaptedPerson(INVALID_NAME, VALID_PHONE,
                        VALID_EMAIL, VALID_CLASS_GROUP, VALID_TELEGRAM, VALID_GITHUB, VALID_NOTES);
        String expectedMessage = Name.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_nullName_throwsIllegalValueException() {
        JsonAdaptedPerson person = new JsonAdaptedPerson(null, VALID_PHONE,
                VALID_EMAIL, VALID_CLASS_GROUP, VALID_TELEGRAM, VALID_GITHUB, VALID_NOTES);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_invalidPhone_throwsIllegalValueException() {
        JsonAdaptedPerson person =
                new JsonAdaptedPerson(VALID_NAME, INVALID_PHONE,
                        VALID_EMAIL, VALID_CLASS_GROUP, VALID_TELEGRAM, VALID_GITHUB, VALID_NOTES);
        String expectedMessage = Phone.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_invalidEmail_throwsIllegalValueException() {
        JsonAdaptedPerson person =
                new JsonAdaptedPerson(VALID_NAME, VALID_PHONE,
                        INVALID_EMAIL, VALID_CLASS_GROUP, VALID_TELEGRAM, VALID_GITHUB, VALID_NOTES);
        String expectedMessage = Email.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_nullEmail_throwsIllegalValueException() {
        JsonAdaptedPerson person = new JsonAdaptedPerson(VALID_NAME,
                VALID_PHONE, null, VALID_CLASS_GROUP, VALID_TELEGRAM, VALID_GITHUB, VALID_NOTES);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Email.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }
}
