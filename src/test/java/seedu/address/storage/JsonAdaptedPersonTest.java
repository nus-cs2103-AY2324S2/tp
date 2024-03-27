package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.storage.JsonAdaptedPerson.MISSING_FIELD_MESSAGE_FORMAT;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalPersons.BENSON;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.person.Dob;
import seedu.address.model.person.Ic;
import seedu.address.model.person.Name;
import seedu.address.model.person.Ward;

public class JsonAdaptedPersonTest {
    private static final String INVALID_NAME = "R@chel";
    private static final String INVALID_IC = "T11222222222222Y";
    private static final String INVALID_WARD = " ";
    private static final String INVALID_ADMISSION_DATE = "123/12/20300";
    private static final String INVALID_TAG = "#Diabetes";
    private static final String INVALID_DOB_FUTURE = LocalDate.now().plusDays(1)
            .format(DateTimeFormatter.ofPattern("dd/MM/yyyy")); // A future date
    private static final String INVALID_DOB_FORMAT = "12-31-2000"; // Wrong format

    private static final String VALID_NAME = BENSON.getName().toString();
    private static final String VALID_DOB = BENSON.getDob().toString();
    private static final String VALID_IC = BENSON.getIc().toString();
    private static final String VALID_ADMISSION_DATE = BENSON.getAdmissionDate().toString();
    private static final String VALID_WARD = BENSON.getWard().toString();

    private static final List<JsonAdaptedTag> VALID_TAGS = BENSON.getTags().stream()
            .map(JsonAdaptedTag::new)
            .collect(Collectors.toList());

    @Test
    public void toModelType_validPersonDetails_returnsPerson() throws Exception {
        JsonAdaptedPerson person = new JsonAdaptedPerson(BENSON);
        assertEquals(BENSON, person.toModelType());
    }

    @Test
    public void toModelType_invalidName_throwsIllegalValueException() {
        JsonAdaptedPerson person = new JsonAdaptedPerson(INVALID_NAME, VALID_TAGS, VALID_DOB,
                VALID_IC, VALID_ADMISSION_DATE, VALID_WARD);
        String expectedMessage = Name.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_nullName_throwsIllegalValueException() {
        JsonAdaptedPerson person = new JsonAdaptedPerson(null, VALID_TAGS, VALID_DOB,
                VALID_IC, VALID_ADMISSION_DATE, VALID_WARD);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_invalidIc_throwsIllegalValueException() {
        JsonAdaptedPerson person = new JsonAdaptedPerson(VALID_NAME, VALID_TAGS, VALID_DOB,
                INVALID_IC, VALID_ADMISSION_DATE, VALID_WARD);
        String expectedMessage = Ic.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_nullIc_throwsIllegalValueException() {
        JsonAdaptedPerson person = new JsonAdaptedPerson(VALID_NAME, VALID_TAGS, VALID_DOB,
                null, VALID_ADMISSION_DATE, VALID_WARD);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Ic.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_invalidDob_throwsIllegalValueException() {
        JsonAdaptedPerson person = new JsonAdaptedPerson(VALID_NAME, VALID_TAGS,
                INVALID_DOB_FUTURE, VALID_IC, VALID_ADMISSION_DATE, VALID_WARD);
        String expectedMessage = Dob.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }
    @Test
    public void toModelType_nullDob_throwsIllegalValueException() {
        JsonAdaptedPerson person = new JsonAdaptedPerson(VALID_NAME, VALID_TAGS,
                null, VALID_IC, VALID_ADMISSION_DATE, VALID_WARD);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Dob.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_invalidWard_throwsIllegalArgumentException() {
        JsonAdaptedPerson person =
                new JsonAdaptedPerson(VALID_NAME, VALID_TAGS,
                        VALID_DOB, VALID_IC, VALID_ADMISSION_DATE, INVALID_WARD);
        String expectedMessage = Ward.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalArgumentException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_nullWard_throwsIllegalValueException() {
        JsonAdaptedPerson person = new JsonAdaptedPerson(VALID_NAME, VALID_TAGS,
                VALID_DOB, VALID_IC, VALID_ADMISSION_DATE, null);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Ward.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_invalidTags_throwsIllegalValueException() {
        List<JsonAdaptedTag> invalidTags = new ArrayList<>(VALID_TAGS);
        invalidTags.add(new JsonAdaptedTag(INVALID_TAG));
        JsonAdaptedPerson person =
                new JsonAdaptedPerson(VALID_NAME, invalidTags,
                        VALID_DOB, VALID_IC, VALID_ADMISSION_DATE, VALID_WARD);
        assertThrows(IllegalValueException.class, person::toModelType);
    }

}
