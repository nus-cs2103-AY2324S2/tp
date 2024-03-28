package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.storage.JsonAdaptedPerson.MISSING_FIELD_MESSAGE_FORMAT;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalPersons.BENSON;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.person.BirthDate;
import seedu.address.model.person.Email;
import seedu.address.model.person.Gender;
import seedu.address.model.person.Name;
import seedu.address.model.person.Nric;
import seedu.address.model.person.Phone;

public class JsonAdaptedPersonTest {
    private static final String INVALID_NRIC = "G3424GH";
    private static final String INVALID_NAME = "R@chel";
    private static final String INVALID_GENDER = "H";
    private static final String INVALID_BIRTHDATE = "99-99-9999";
    private static final String INVALID_PHONE = "+651234";
    private static final String INVALID_EMAIL = "example.com";
    private static final String INVALID_ILLNESS = "#illness";
    private static final String INVALID_DATE = "2024-02-19";
    private static final String INVALID_TIME = "5006";
    private static final String INVALID_DESCRIPTION = "";

    private static final String VALID_NRIC = BENSON.getNric().toString();
    private static final String VALID_NAME = BENSON.getName().toString();
    private static final String VALID_GENDER = BENSON.getGender().toString();
    private static final String VALID_BIRTHDATE = BENSON.getBirthDate().toString();
    private static final String VALID_PHONE = BENSON.getPhone().toString();
    private static final String VALID_EMAIL = BENSON.getEmail().toString();
    private static final String VALID_DRUG_ALLERGY = BENSON.getDrugAllergy().toString();
    private static final List<JsonAdaptedIllness> VALID_ILLNESSES = BENSON.getIllnesses().stream()
        .map(JsonAdaptedIllness::new)
        .collect(Collectors.toList());

    private static final ObservableList<JsonAdapatedNote> VALID_NOTES = BENSON.getNotes().stream()
        .map(JsonAdapatedNote::new)
        .collect(Collectors.toCollection(FXCollections::observableArrayList));

    @Test
    public void toModelType_validPersonDetails_returnsPerson() throws Exception {
        JsonAdaptedPerson person = new JsonAdaptedPerson(BENSON);
        assertEquals(BENSON, person.toModelType());
    }

    @Test
    public void toModelType_invalidNric_throwsIllegalValueException() {
        JsonAdaptedPerson person =
                new JsonAdaptedPerson(INVALID_NRIC, VALID_NAME, VALID_GENDER, VALID_BIRTHDATE,
                        VALID_PHONE, VALID_EMAIL, VALID_DRUG_ALLERGY, VALID_ILLNESSES, VALID_NOTES);
        String expectedMessage = Nric.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_nullNric_throwsIllegalValueException() {
        JsonAdaptedPerson person =
                new JsonAdaptedPerson(null, VALID_NAME, VALID_GENDER, VALID_BIRTHDATE,
                        VALID_PHONE, VALID_EMAIL, VALID_DRUG_ALLERGY, VALID_ILLNESSES, VALID_NOTES);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Nric.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_invalidName_throwsIllegalValueException() {
        JsonAdaptedPerson person =
            new JsonAdaptedPerson(VALID_NRIC, INVALID_NAME, VALID_GENDER, VALID_BIRTHDATE,
                    VALID_PHONE, VALID_EMAIL, VALID_DRUG_ALLERGY, VALID_ILLNESSES, VALID_NOTES);
        String expectedMessage = Name.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_nullName_throwsIllegalValueException() {
        JsonAdaptedPerson person =
            new JsonAdaptedPerson(VALID_NRIC, null, VALID_GENDER, VALID_BIRTHDATE,
                    VALID_PHONE, VALID_EMAIL, VALID_DRUG_ALLERGY, VALID_ILLNESSES, VALID_NOTES);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_invalidGender_throwsIllegalValueException() {
        JsonAdaptedPerson person =
                new JsonAdaptedPerson(VALID_NRIC, VALID_NAME, INVALID_GENDER, VALID_BIRTHDATE,
                        VALID_PHONE, VALID_EMAIL, VALID_DRUG_ALLERGY, VALID_ILLNESSES, VALID_NOTES);
        String expectedMessage = Gender.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_nullGender_throwsIllegalValueException() {
        JsonAdaptedPerson person =
                new JsonAdaptedPerson(VALID_NRIC, VALID_NAME, null, VALID_BIRTHDATE,
                        VALID_PHONE, VALID_EMAIL, VALID_DRUG_ALLERGY, VALID_ILLNESSES, VALID_NOTES);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Gender.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_invalidBirthDate_throwsIllegalValueException() {
        JsonAdaptedPerson person =
                new JsonAdaptedPerson(VALID_NRIC, VALID_NAME, VALID_GENDER, INVALID_BIRTHDATE,
                        VALID_PHONE, VALID_EMAIL, VALID_DRUG_ALLERGY, VALID_ILLNESSES, VALID_NOTES);
        String expectedMessage = BirthDate.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_nullBirthDate_throwsIllegalValueException() {
        JsonAdaptedPerson person =
                new JsonAdaptedPerson(VALID_NRIC, VALID_NAME, VALID_GENDER, null,
                        VALID_PHONE, VALID_EMAIL, VALID_DRUG_ALLERGY, VALID_ILLNESSES, VALID_NOTES);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, BirthDate.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_invalidPhone_throwsIllegalValueException() {
        JsonAdaptedPerson person =
                new JsonAdaptedPerson(VALID_NRIC, VALID_NAME, VALID_GENDER, VALID_BIRTHDATE,
                        INVALID_PHONE, VALID_EMAIL, VALID_DRUG_ALLERGY, VALID_ILLNESSES, VALID_NOTES);
        String expectedMessage = Phone.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_nullPhone_throwsIllegalValueException() {
        JsonAdaptedPerson person =
                new JsonAdaptedPerson(VALID_NRIC, VALID_NAME, VALID_GENDER, VALID_BIRTHDATE,
                        null, VALID_EMAIL, VALID_DRUG_ALLERGY, VALID_ILLNESSES, VALID_NOTES);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Phone.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_invalidEmail_throwsIllegalValueException() {
        JsonAdaptedPerson person =
                new JsonAdaptedPerson(VALID_NRIC, VALID_NAME, VALID_GENDER, VALID_BIRTHDATE,
                        VALID_PHONE, INVALID_EMAIL, VALID_DRUG_ALLERGY, VALID_ILLNESSES, VALID_NOTES);
        String expectedMessage = Email.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_nullEmail_throwsIllegalValueException() {
        JsonAdaptedPerson person =
                new JsonAdaptedPerson(VALID_NRIC, VALID_NAME, VALID_GENDER, VALID_BIRTHDATE,
                        VALID_PHONE, null, VALID_DRUG_ALLERGY, VALID_ILLNESSES, VALID_NOTES);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Email.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_invalidIllnesses_throwsIllegalValueException() {
        List<JsonAdaptedIllness> invalidIllnesses = new ArrayList<>(VALID_ILLNESSES);
        invalidIllnesses.add(new JsonAdaptedIllness(INVALID_ILLNESS));
        JsonAdaptedPerson person =
                new JsonAdaptedPerson(VALID_NRIC, VALID_NAME, VALID_GENDER, VALID_BIRTHDATE,
                        VALID_PHONE, VALID_EMAIL, VALID_DRUG_ALLERGY, invalidIllnesses, VALID_NOTES);
        assertThrows(IllegalValueException.class, person::toModelType);
    }
}
