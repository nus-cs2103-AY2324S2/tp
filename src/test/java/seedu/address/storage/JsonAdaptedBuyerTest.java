package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.storage.JsonAdaptedBuyer.MISSING_FIELD_MESSAGE_FORMAT;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalPersons.ALICE_BUYER;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Phone;

public class JsonAdaptedBuyerTest {
    private static final String INVALID_NAME = "R@chel";
    private static final String INVALID_PHONE = "+651234";
    private static final String INVALID_EMAIL = "example.com";
    private static final String INVALID_HOUSING_TYPE = "AAA";
    private static final String INVALID_TAG = "#friend";

    private static final String VALID_NAME = ALICE_BUYER.getName().toString();
    private static final String VALID_PHONE = ALICE_BUYER.getPhone().toString();
    private static final String VALID_EMAIL = ALICE_BUYER.getEmail().toString();
    private static final String VALID_HOUSING_TYPE = ALICE_BUYER.getHousingType();

    private static final List<JsonAdaptedTag> VALID_TAGS = ALICE_BUYER.getTags().stream()
            .map(JsonAdaptedTag::new)
            .collect(Collectors.toList());

    @Test
    public void toModelType_validBuyerDetails_returnsBuyer() throws Exception {
        JsonAdaptedBuyer buyer = new JsonAdaptedBuyer(ALICE_BUYER);
        assertEquals(ALICE_BUYER, buyer.toModelType());
    }

    @Test
    public void toModelType_invalidName_throwsIllegalValueException() {
        JsonAdaptedBuyer buyer =
                new JsonAdaptedBuyer(INVALID_NAME, VALID_PHONE, VALID_EMAIL, VALID_HOUSING_TYPE, VALID_TAGS);
        String expectedMessage = Name.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, buyer::toModelType);
    }

    @Test
    public void toModelType_invalidPhone_throwsIllegalValueException() {
        JsonAdaptedBuyer buyer =
                new JsonAdaptedBuyer(VALID_NAME, INVALID_PHONE, VALID_EMAIL, VALID_HOUSING_TYPE, VALID_TAGS);
        String expectedMessage = Phone.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, buyer::toModelType);
    }

    @Test
    public void toModelType_nullPhone_throwsIllegalValueException() {
        JsonAdaptedBuyer buyer =
                new JsonAdaptedBuyer(VALID_NAME, null, VALID_EMAIL, VALID_HOUSING_TYPE, VALID_TAGS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Phone.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, buyer::toModelType);
    }

    @Test
    public void toModelType_invalidEmail_throwsIllegalValueException() {
        JsonAdaptedBuyer buyer =
                new JsonAdaptedBuyer(VALID_NAME, VALID_PHONE, INVALID_EMAIL, VALID_HOUSING_TYPE, VALID_TAGS);
        String expectedMessage = Email.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, buyer::toModelType);
    }

    @Test
    public void toModelType_nullEmail_throwsIllegalValueException() {
        JsonAdaptedBuyer buyer =
                new JsonAdaptedBuyer(VALID_NAME, VALID_PHONE, null, VALID_HOUSING_TYPE, VALID_TAGS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Email.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, buyer::toModelType);
    }

    @Test
    public void toModelType_invalidHousingType_throwsIllegalValueException() {
        JsonAdaptedBuyer buyer =
                new JsonAdaptedBuyer(VALID_NAME, VALID_PHONE, VALID_EMAIL, INVALID_HOUSING_TYPE, VALID_TAGS);
        String expectedMessage = "Housing types can only be HDB, Condominium or Landed.";
        assertThrows(IllegalValueException.class, expectedMessage, buyer::toModelType);
    }

    @Test
    public void toModelType_invalidTags_throwsIllegalValueException() {
        List<JsonAdaptedTag> invalidTags = new ArrayList<>(VALID_TAGS);
        invalidTags.add(new JsonAdaptedTag(INVALID_TAG));
        JsonAdaptedBuyer buyer =
                new JsonAdaptedBuyer(VALID_NAME, VALID_PHONE, VALID_EMAIL, VALID_HOUSING_TYPE, invalidTags);
        assertThrows(IllegalValueException.class, buyer::toModelType);
    }
}

