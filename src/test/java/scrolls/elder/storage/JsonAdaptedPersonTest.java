package scrolls.elder.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import scrolls.elder.commons.exceptions.IllegalValueException;
import scrolls.elder.model.person.Address;
import scrolls.elder.model.person.Email;
import scrolls.elder.model.person.Name;
import scrolls.elder.model.person.Phone;
import scrolls.elder.model.person.Role;
import scrolls.elder.testutil.Assert;
import scrolls.elder.testutil.TypicalPersons;

public class JsonAdaptedPersonTest {
    private static final String INVALID_NAME = "R@chel";
    private static final String INVALID_PHONE = "+651234";
    private static final String INVALID_ADDRESS = " ";
    private static final String INVALID_EMAIL = "example.com";
    private static final String INVALID_TAG = "#friend";
    private static final String INVALID_ROLE = "friend";

    private static final String VALID_ID = String.valueOf(TypicalPersons.BENSON.getPersonId());
    private static final String VALID_NAME = TypicalPersons.BENSON.getName().toString();
    private static final String VALID_PHONE = TypicalPersons.BENSON.getPhone().toString();
    private static final String VALID_EMAIL = TypicalPersons.BENSON.getEmail().toString();
    private static final String VALID_ADDRESS = TypicalPersons.BENSON.getAddress().toString();
    private static final String VALID_ROLE = TypicalPersons.BENSON.getRole().toString();
    private static final String VALID_PAIRED_WITH = TypicalPersons.HOON.getPairedWithName().get().toString();
    private static final String VALID_PAIRED_WITH_ID = TypicalPersons.HOON.getPairedWithId().get().toString();
    private static final List<JsonAdaptedTag> VALID_TAGS = TypicalPersons.BENSON.getTags().stream()
            .map(JsonAdaptedTag::new)
            .collect(Collectors.toList());

    @Test
    public void toModelType_validPersonDetails_returnsPerson() throws Exception {
        JsonAdaptedPerson person = new JsonAdaptedPerson(TypicalPersons.BENSON);
        Assertions.assertEquals(TypicalPersons.BENSON, person.toModelType());
    }

    @Test
    public void toModelType_invalidName_throwsIllegalValueException() {
        JsonAdaptedPerson person =
                new JsonAdaptedPerson(VALID_ID, INVALID_NAME, VALID_PHONE, VALID_EMAIL, VALID_ADDRESS, VALID_ROLE,
                        VALID_TAGS, VALID_PAIRED_WITH, VALID_PAIRED_WITH_ID);
        String expectedMessage = Name.MESSAGE_CONSTRAINTS;

        Assert.assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_nullName_throwsIllegalValueException() {
        JsonAdaptedPerson person = new JsonAdaptedPerson(VALID_ID, null, VALID_PHONE, VALID_EMAIL,
                VALID_ADDRESS, VALID_ROLE, VALID_TAGS, VALID_PAIRED_WITH, VALID_PAIRED_WITH_ID);

        String expectedMessage =
                String.format(JsonAdaptedPerson.MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName());
        Assert.assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_invalidPhone_throwsIllegalValueException() {
        JsonAdaptedPerson person =
                new JsonAdaptedPerson(VALID_ID, VALID_NAME, INVALID_PHONE, VALID_EMAIL, VALID_ADDRESS,
                        VALID_ROLE, VALID_TAGS, VALID_PAIRED_WITH, VALID_PAIRED_WITH_ID);
        String expectedMessage = Phone.MESSAGE_CONSTRAINTS;
        Assert.assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_nullPhone_throwsIllegalValueException() {
        JsonAdaptedPerson person = new JsonAdaptedPerson(VALID_ID, VALID_NAME, null, VALID_EMAIL, VALID_ADDRESS,
                VALID_ROLE, VALID_TAGS, VALID_PAIRED_WITH, VALID_PAIRED_WITH_ID);
        String expectedMessage =
                String.format(JsonAdaptedPerson.MISSING_FIELD_MESSAGE_FORMAT, Phone.class.getSimpleName());
        Assert.assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_invalidEmail_throwsIllegalValueException() {
        JsonAdaptedPerson person =
                new JsonAdaptedPerson(VALID_ID, VALID_NAME, VALID_PHONE, INVALID_EMAIL, VALID_ADDRESS,
                        VALID_ROLE, VALID_TAGS, VALID_PAIRED_WITH, VALID_PAIRED_WITH_ID);
        String expectedMessage = Email.MESSAGE_CONSTRAINTS;
        Assert.assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_nullEmail_throwsIllegalValueException() {
        JsonAdaptedPerson person = new JsonAdaptedPerson(VALID_ID, VALID_NAME, VALID_PHONE, null, VALID_ADDRESS,
                VALID_ROLE, VALID_TAGS, VALID_PAIRED_WITH, VALID_PAIRED_WITH_ID);

        String expectedMessage =
                String.format(JsonAdaptedPerson.MISSING_FIELD_MESSAGE_FORMAT, Email.class.getSimpleName());
        Assert.assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_invalidAddress_throwsIllegalValueException() {
        JsonAdaptedPerson person =
                new JsonAdaptedPerson(VALID_ID, VALID_NAME, VALID_PHONE, VALID_EMAIL, INVALID_ADDRESS,
                        VALID_ROLE, VALID_TAGS, VALID_PAIRED_WITH, VALID_PAIRED_WITH_ID);

        String expectedMessage = Address.MESSAGE_CONSTRAINTS;
        Assert.assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_nullAddress_throwsIllegalValueException() {
        JsonAdaptedPerson person = new JsonAdaptedPerson(VALID_ID, VALID_NAME, VALID_PHONE, VALID_EMAIL, null,
                VALID_ROLE, VALID_TAGS, VALID_PAIRED_WITH, VALID_PAIRED_WITH_ID);
        String expectedMessage =
                String.format(JsonAdaptedPerson.MISSING_FIELD_MESSAGE_FORMAT, Address.class.getSimpleName());
        Assert.assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_invalidRole_throwsIllegalValueException() {
        JsonAdaptedPerson person = new JsonAdaptedPerson(VALID_ID, VALID_NAME, VALID_PHONE, VALID_EMAIL, VALID_ADDRESS,
                INVALID_ROLE, VALID_TAGS, VALID_PAIRED_WITH, VALID_PAIRED_WITH_ID);

        String expectedMessage = Role.MESSAGE_CONSTRAINTS;
        Assert.assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }


    @Test
    public void toModelType_nullRole_throwsIllegalValueException() {
        JsonAdaptedPerson person = new JsonAdaptedPerson(VALID_ID, VALID_NAME, VALID_PHONE, VALID_EMAIL, VALID_ADDRESS,
                null, VALID_TAGS, VALID_PAIRED_WITH, VALID_PAIRED_WITH_ID);
        String expectedMessage =
                String.format(JsonAdaptedPerson.MISSING_FIELD_MESSAGE_FORMAT, Role.class.getSimpleName());
        Assert.assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_invalidTags_throwsIllegalValueException() {
        List<JsonAdaptedTag> invalidTags = new ArrayList<>(VALID_TAGS);
        invalidTags.add(new JsonAdaptedTag(INVALID_TAG));
        JsonAdaptedPerson person =
                new JsonAdaptedPerson(VALID_ID, VALID_NAME, VALID_PHONE, VALID_EMAIL, VALID_ADDRESS,
                        VALID_ROLE, invalidTags, VALID_PAIRED_WITH, VALID_PAIRED_WITH_ID);
        Assert.assertThrows(IllegalValueException.class, person::toModelType);
    }

}
