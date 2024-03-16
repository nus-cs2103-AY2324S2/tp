package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.storage.JsonAdaptedPatient.MISSING_FIELD_MESSAGE_FORMAT;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalPatients.BENSON;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.patient.FamilyCondition;
import seedu.address.model.patient.FoodPreference;
import seedu.address.model.patient.Hobby;
import seedu.address.model.patient.Name;
import seedu.address.model.patient.PatientHospitalId;
import seedu.address.model.patient.PreferredName;

public class JsonAdaptedPatientTest {
    private static final String INVALID_ID = "-1";
    private static final String INVALID_NAME = "R@chel Lim";
    private static final String INVALID_PREFERRED_NAME = "R@chel";
    private static final String INVALID_FOOD_PREFERENCE = " ";
    private static final String INVALID_FAMILY_CONDITION = " ";
    private static final String INVALID_HOBBY = " ";
    private static final String INVALID_TAG = "#Diabetes";
    private static final String INVALID_IMPORTANT_DATETIME_STR = "Something";

    private static final String VALID_PATIENT_HOSPITAL_ID = BENSON.getPatientHospitalId().patientHospitalId;
    private static final String VALID_NAME = BENSON.getName().toString();
    private static final String VALID_PREFERRED_NAME = BENSON.getPreferredName().toString();
    private static final String VALID_FOOD_PREFERENCE = BENSON.getFoodPreference().toString();
    private static final String VALID_FAMILY_CONDITION = BENSON.getFamilyCondition().toString();
    private static final String VALID_HOBBY = BENSON.getHobby().toString();
    private static final List<JsonAdaptedTag> VALID_TAGS = BENSON.getTags().stream()
            .map(JsonAdaptedTag::new)
            .collect(Collectors.toList());
    private static final String VALID_IMPORTANT_DATETIME_NAME = "Birthday";
    private static final List<JsonAdaptedImportantDate> VALID_IMPORTANT_DATES = BENSON.getImportantDates().stream()
            .map(JsonAdaptedImportantDate::new)
            .collect(Collectors.toList());

    @Test
    public void toModelType_validPersonDetails_returnsPerson() throws Exception {
        JsonAdaptedPatient person = new JsonAdaptedPatient(BENSON);
        assertEquals(BENSON, person.toModelType());
    }

    @Test
    public void toModelType_invalidPatientHospitalId_throwsIllegalValueException() {
        JsonAdaptedPatient person =
            new JsonAdaptedPatient(INVALID_ID, VALID_NAME, VALID_PREFERRED_NAME,
                VALID_FOOD_PREFERENCE, VALID_FAMILY_CONDITION, VALID_HOBBY, VALID_TAGS, VALID_IMPORTANT_DATES);
        String expectedMessage = PatientHospitalId.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_nullPatientHospitalId_throwsIllegalValueException() {
        JsonAdaptedPatient person = new JsonAdaptedPatient(null, VALID_NAME, VALID_PREFERRED_NAME,
            VALID_FOOD_PREFERENCE, VALID_FAMILY_CONDITION, VALID_HOBBY, VALID_TAGS, VALID_IMPORTANT_DATES);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, PatientHospitalId.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_invalidName_throwsIllegalValueException() {
        JsonAdaptedPatient person =
                new JsonAdaptedPatient(VALID_PATIENT_HOSPITAL_ID, INVALID_NAME, VALID_PREFERRED_NAME,
                    VALID_FOOD_PREFERENCE, VALID_FAMILY_CONDITION, VALID_HOBBY, VALID_TAGS, VALID_IMPORTANT_DATES);
        String expectedMessage = Name.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_nullName_throwsIllegalValueException() {
        JsonAdaptedPatient person = new JsonAdaptedPatient(VALID_PATIENT_HOSPITAL_ID, null, VALID_PREFERRED_NAME,
            VALID_FOOD_PREFERENCE, VALID_FAMILY_CONDITION, VALID_HOBBY, VALID_TAGS, VALID_IMPORTANT_DATES);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_invalidPreferredName_throwsIllegalValueException() {
        JsonAdaptedPatient person =
                new JsonAdaptedPatient(VALID_PATIENT_HOSPITAL_ID, VALID_NAME, INVALID_PREFERRED_NAME,
                    VALID_FOOD_PREFERENCE, VALID_FAMILY_CONDITION, VALID_HOBBY, VALID_TAGS, VALID_IMPORTANT_DATES);
        String expectedMessage = PreferredName.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_nullPreferredName_throwsIllegalValueException() {
        JsonAdaptedPatient person = new JsonAdaptedPatient(VALID_PATIENT_HOSPITAL_ID, VALID_NAME, null,
            VALID_FOOD_PREFERENCE, VALID_FAMILY_CONDITION, VALID_HOBBY, VALID_TAGS, VALID_IMPORTANT_DATES);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, PreferredName.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_invalidFoodPreference_throwsIllegalValueException() {
        JsonAdaptedPatient person =
                new JsonAdaptedPatient(VALID_PATIENT_HOSPITAL_ID, VALID_NAME, VALID_PREFERRED_NAME,
                    INVALID_FOOD_PREFERENCE, VALID_FAMILY_CONDITION, VALID_HOBBY, VALID_TAGS, VALID_IMPORTANT_DATES);
        String expectedMessage = FoodPreference.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_nullFoodPreference_throwsIllegalValueException() {
        JsonAdaptedPatient person = new JsonAdaptedPatient(VALID_PATIENT_HOSPITAL_ID, VALID_NAME, VALID_PREFERRED_NAME,
            null, VALID_FAMILY_CONDITION, VALID_HOBBY, VALID_TAGS, VALID_IMPORTANT_DATES);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, FoodPreference.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_invalidFamilyCondition_throwsIllegalValueException() {
        JsonAdaptedPatient person =
                new JsonAdaptedPatient(VALID_PATIENT_HOSPITAL_ID, VALID_NAME, VALID_PREFERRED_NAME,
                    VALID_FOOD_PREFERENCE, INVALID_FAMILY_CONDITION, VALID_HOBBY, VALID_TAGS, VALID_IMPORTANT_DATES);
        String expectedMessage = FamilyCondition.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_nullFamilyCondition_throwsIllegalValueException() {
        JsonAdaptedPatient person = new JsonAdaptedPatient(VALID_PATIENT_HOSPITAL_ID, VALID_NAME, VALID_PREFERRED_NAME,
            VALID_FOOD_PREFERENCE, null, VALID_HOBBY, VALID_TAGS, VALID_IMPORTANT_DATES);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, FamilyCondition.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_invalidHobby_throwsIllegalValueException() {
        JsonAdaptedPatient person =
            new JsonAdaptedPatient(VALID_PATIENT_HOSPITAL_ID, VALID_NAME, VALID_PREFERRED_NAME,
                VALID_FOOD_PREFERENCE, VALID_FAMILY_CONDITION, INVALID_HOBBY, VALID_TAGS, VALID_IMPORTANT_DATES);
        String expectedMessage = Hobby.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_nullHobby_throwsIllegalValueException() {
        JsonAdaptedPatient person = new JsonAdaptedPatient(VALID_PATIENT_HOSPITAL_ID, VALID_NAME, VALID_PREFERRED_NAME,
            VALID_FOOD_PREFERENCE, VALID_FAMILY_CONDITION, null, VALID_TAGS, VALID_IMPORTANT_DATES);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Hobby.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_invalidTags_throwsIllegalValueException() {
        List<JsonAdaptedTag> invalidTags = new ArrayList<>(VALID_TAGS);
        invalidTags.add(new JsonAdaptedTag(INVALID_TAG));
        JsonAdaptedPatient person =
                new JsonAdaptedPatient(VALID_PATIENT_HOSPITAL_ID, VALID_NAME, VALID_PREFERRED_NAME,
                    VALID_FOOD_PREFERENCE, VALID_FAMILY_CONDITION, VALID_HOBBY, invalidTags, VALID_IMPORTANT_DATES);
        assertThrows(IllegalValueException.class, person::toModelType);
    }

    @Test
    public void toModelType_invalidDateTime_throwsIllegalValueException() {
        List<JsonAdaptedImportantDate> invalidImportantDates = new ArrayList<>(VALID_IMPORTANT_DATES);
        invalidImportantDates.add(new JsonAdaptedImportantDate(VALID_IMPORTANT_DATETIME_NAME,
            INVALID_IMPORTANT_DATETIME_STR));
        JsonAdaptedPatient person =
            new JsonAdaptedPatient(VALID_PATIENT_HOSPITAL_ID, VALID_NAME, VALID_PREFERRED_NAME,
                VALID_FOOD_PREFERENCE, VALID_FAMILY_CONDITION, VALID_HOBBY, VALID_TAGS, invalidImportantDates);
        assertThrows(IllegalValueException.class, person::toModelType);
    }
}
