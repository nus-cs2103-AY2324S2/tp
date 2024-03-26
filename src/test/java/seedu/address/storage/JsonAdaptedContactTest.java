package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.storage.JsonAdaptedContact.MISSING_FIELD_MESSAGE_FORMAT;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalContacts.BENSON;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.contact.Address;
import seedu.address.model.contact.Email;
import seedu.address.model.contact.GitHubUsername;
import seedu.address.model.contact.Name;
import seedu.address.model.contact.Phone;

public class JsonAdaptedContactTest {
    private static final String INVALID_NAME = "R@chel";
    private static final String INVALID_PHONE = "+651234";
    private static final String INVALID_ADDRESS = " ";
    private static final String INVALID_EMAIL = "example.com";
    private static final String INVALID_GITHUB_USERNAME = "Ra_!chEl-";
    private static final String INVALID_TECH_STACK = "#jA!vA";
    private static final String INVALID_TAG = "#friend";

    private static final String VALID_NAME = BENSON.getName().toString();
    private static final String VALID_PHONE = BENSON.getPhone().toString();
    private static final String VALID_EMAIL = BENSON.getEmail().toString();
    private static final String VALID_ADDRESS = BENSON.getAddress().toString();
    private static final String VALID_GITHUB_USERNAME = BENSON.getGitHubUsername().toString();
    private static final String VALID_PROFILE_PICTURE = BENSON.getProfilePicture().get();
    private static final List<JsonAdaptedTechStack> VALID_TECH_STACK = BENSON.getTechStack().stream()
            .map(JsonAdaptedTechStack::new)
            .collect(Collectors.toList());
    private static final List<JsonAdaptedTag> VALID_TAGS = BENSON.getTags().stream()
            .map(JsonAdaptedTag::new)
            .collect(Collectors.toList());

    @Test
    public void toModelType_validContactDetails_returnsContact() throws Exception {
        JsonAdaptedContact contact = new JsonAdaptedContact(BENSON);
        assertEquals(BENSON, contact.toModelType());
    }

    @Test
    public void toModelType_invalidName_throwsIllegalValueException() {
        JsonAdaptedContact contact =
                new JsonAdaptedContact(INVALID_NAME, VALID_PHONE, VALID_EMAIL, VALID_ADDRESS, VALID_GITHUB_USERNAME, VALID_PROFILE_PICTURE,
                        VALID_TECH_STACK, VALID_TAGS);
        String expectedMessage = Name.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, contact::toModelType);
    }

    @Test
    public void toModelType_nullName_throwsIllegalValueException() {
        JsonAdaptedContact contact = new JsonAdaptedContact(null, VALID_PHONE, VALID_EMAIL, VALID_ADDRESS,
                VALID_GITHUB_USERNAME, VALID_PROFILE_PICTURE, VALID_TECH_STACK, VALID_TAGS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, contact::toModelType);
    }

    @Test
    public void toModelType_invalidPhone_throwsIllegalValueException() {
        JsonAdaptedContact contact =
                new JsonAdaptedContact(VALID_NAME, INVALID_PHONE, VALID_EMAIL, VALID_ADDRESS,
                        VALID_GITHUB_USERNAME, VALID_PROFILE_PICTURE, VALID_TECH_STACK, VALID_TAGS);
        String expectedMessage = Phone.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, contact::toModelType);
    }

    @Test
    public void toModelType_nullPhone_throwsIllegalValueException() {
        JsonAdaptedContact contact = new JsonAdaptedContact(VALID_NAME, null, VALID_EMAIL, VALID_ADDRESS,
                VALID_GITHUB_USERNAME, VALID_PROFILE_PICTURE, VALID_TECH_STACK, VALID_TAGS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Phone.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, contact::toModelType);
    }

    @Test
    public void toModelType_invalidEmail_throwsIllegalValueException() {
        JsonAdaptedContact contact =
                new JsonAdaptedContact(VALID_NAME, VALID_PHONE, INVALID_EMAIL, VALID_ADDRESS,
                        VALID_GITHUB_USERNAME, VALID_PROFILE_PICTURE, VALID_TECH_STACK, VALID_TAGS);
        String expectedMessage = Email.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, contact::toModelType);
    }

    @Test
    public void toModelType_nullEmail_throwsIllegalValueException() {
        JsonAdaptedContact contact = new JsonAdaptedContact(VALID_NAME, VALID_PHONE, null, VALID_ADDRESS,
                VALID_GITHUB_USERNAME, VALID_PROFILE_PICTURE, VALID_TECH_STACK, VALID_TAGS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Email.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, contact::toModelType);
    }

    @Test
    public void toModelType_invalidAddress_throwsIllegalValueException() {
        JsonAdaptedContact contact =
                new JsonAdaptedContact(VALID_NAME, VALID_PHONE, VALID_EMAIL, INVALID_ADDRESS,
                        VALID_GITHUB_USERNAME, VALID_PROFILE_PICTURE, VALID_TECH_STACK, VALID_TAGS);
        String expectedMessage = Address.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, contact::toModelType);
    }

    @Test
    public void toModelType_nullAddress_throwsIllegalValueException() {
        JsonAdaptedContact contact = new JsonAdaptedContact(VALID_NAME, VALID_PHONE, VALID_EMAIL, null,
                VALID_GITHUB_USERNAME, VALID_PROFILE_PICTURE, VALID_TECH_STACK, VALID_TAGS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Address.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, contact::toModelType);
    }
    @Test
    public void toModelType_invalidGitHubUsername_throwsIllegalValueException() {
        JsonAdaptedContact contact =
                new JsonAdaptedContact(VALID_NAME, VALID_PHONE, VALID_EMAIL, VALID_ADDRESS,
                        INVALID_GITHUB_USERNAME, VALID_PROFILE_PICTURE, VALID_TECH_STACK, VALID_TAGS);
        String expectedMessage = GitHubUsername.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, contact::toModelType);
    }

    @Test
    public void toModelType_nullGitHubUsername_throwsIllegalValueException() {
        JsonAdaptedContact contact = new JsonAdaptedContact(VALID_NAME, VALID_PHONE, VALID_EMAIL, VALID_ADDRESS,
                null, VALID_PROFILE_PICTURE, VALID_TECH_STACK,  VALID_TAGS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, GitHubUsername.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, contact::toModelType);
    }

    @Test
    public void toModelType_invalidTags_throwsIllegalValueException() {
        List<JsonAdaptedTag> invalidTags = new ArrayList<>(VALID_TAGS);
        invalidTags.add(new JsonAdaptedTag(INVALID_TAG));
        JsonAdaptedContact contact =
                new JsonAdaptedContact(VALID_NAME, VALID_PHONE, VALID_EMAIL, VALID_ADDRESS,
                        VALID_GITHUB_USERNAME, VALID_PROFILE_PICTURE, VALID_TECH_STACK, invalidTags);
        assertThrows(IllegalValueException.class, contact::toModelType);
    }

}
