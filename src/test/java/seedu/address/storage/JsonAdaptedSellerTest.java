package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.storage.JsonAdaptedSeller.MISSING_FIELD_MESSAGE_FORMAT;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalPersons.BOB_SELLER;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Phone;

public class JsonAdaptedSellerTest {
    private static final String INVALID_NAME = "R@chel";
    private static final String INVALID_PHONE = "+651234";
    private static final String INVALID_EMAIL = "example.com";
    private static final String INVALID_HOUSING_TYPE = "AAA";
    private static final String INVALID_TAG = "#friend";
    private static final String VALID_NAME = BOB_SELLER.getName().toString();
    private static final String VALID_PHONE = BOB_SELLER.getPhone().toString();
    private static final String VALID_EMAIL = BOB_SELLER.getEmail().toString();
    private static final String VALID_HOUSINGTYPE = BOB_SELLER.getHousingType();
    private static final List<JsonAdaptedHouse> VALID_HOUSES = BOB_SELLER.getHouses().stream()
            .map(JsonAdaptedHouse::new)
            .collect(Collectors.toList());
    private static final List<JsonAdaptedTag> VALID_TAGS = BOB_SELLER.getTags().stream()
            .map(JsonAdaptedTag::new)
            .collect(Collectors.toList());


    @Test
    public void toModelType_validSellerDetails_returnsSeller() throws Exception {
        JsonAdaptedSeller seller = new JsonAdaptedSeller(BOB_SELLER);
        assertEquals(BOB_SELLER, seller.toModelType());
    }

    @Test
    public void toModelType_invalidName_throwsIllegalValueException() {
        JsonAdaptedSeller seller =
                new JsonAdaptedSeller(INVALID_NAME, VALID_PHONE, VALID_EMAIL, VALID_HOUSINGTYPE,
                        VALID_HOUSES, VALID_TAGS);
        String expectedMessage = Name.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, seller::toModelType);
    }

    @Test
    public void toModelType_nullName_throwsIllegalValueException() {
        JsonAdaptedSeller seller = new JsonAdaptedSeller(null, VALID_PHONE, VALID_EMAIL,
                VALID_HOUSINGTYPE, VALID_HOUSES, VALID_TAGS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, seller::toModelType);
    }

    @Test
    public void toModelType_invalidPhone_throwsIllegalValueException() {
        JsonAdaptedSeller seller =
                new JsonAdaptedSeller(VALID_NAME, INVALID_PHONE, VALID_EMAIL, VALID_HOUSINGTYPE,
                        VALID_HOUSES, VALID_TAGS);
        String expectedMessage = Phone.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, seller::toModelType);
    }

    @Test
    public void toModelType_nullPhone_throwsIllegalValueException() {
        JsonAdaptedSeller seller = new JsonAdaptedSeller(VALID_NAME, null, VALID_EMAIL,
                VALID_HOUSINGTYPE, VALID_HOUSES, VALID_TAGS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Phone.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, seller::toModelType);
    }

    @Test
    public void toModelType_invalidEmail_throwsIllegalValueException() {
        JsonAdaptedSeller seller =
                new JsonAdaptedSeller(VALID_NAME, VALID_PHONE, INVALID_EMAIL, VALID_HOUSINGTYPE,
                        VALID_HOUSES, VALID_TAGS);
        String expectedMessage = Email.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, seller::toModelType);
    }

    @Test
    public void toModelType_nullEmail_throwsIllegalValueException() {
        JsonAdaptedSeller seller = new JsonAdaptedSeller(VALID_NAME, VALID_PHONE, null,
                VALID_HOUSINGTYPE, VALID_HOUSES, VALID_TAGS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Email.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, seller::toModelType);
    }

    @Test
    public void toModelType_invalidHousingType_throwsIllegalValueException() {
        JsonAdaptedSeller seller =
                new JsonAdaptedSeller(VALID_NAME, VALID_PHONE, VALID_EMAIL, INVALID_HOUSING_TYPE,
                        VALID_HOUSES, VALID_TAGS);
        String expectedMessage = "Invalid housing type";
        assertThrows(IllegalValueException.class, expectedMessage, seller::toModelType);
    }

    @Test
    public void toModelType_nullHousingType_throwsIllegalValueException() {
        JsonAdaptedSeller seller = new JsonAdaptedSeller(VALID_NAME, VALID_PHONE, VALID_EMAIL, null,
                VALID_HOUSES, VALID_TAGS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, "Housing Type");
        assertThrows(IllegalValueException.class, expectedMessage, seller::toModelType);
    }

    @Test
    public void toModelType_invalidTags_throwsIllegalValueException() {
        List<JsonAdaptedTag> invalidTags = new ArrayList<>(VALID_TAGS);
        invalidTags.add(new JsonAdaptedTag(INVALID_TAG));
        JsonAdaptedSeller seller =
                new JsonAdaptedSeller(VALID_NAME, VALID_PHONE, VALID_EMAIL, VALID_HOUSINGTYPE,
                        VALID_HOUSES, invalidTags);
        assertThrows(IllegalValueException.class, seller::toModelType);
    }

    @Test
    public void toModelType_invalidHouses_throwsIllegalValueException() {
        List<JsonAdaptedHouse> invalidHouses = new ArrayList<>(VALID_HOUSES);
        invalidHouses.add(new JsonAdaptedHouse("NonLanded", "InvalidBlock", "InvalidLevel",
                "123456", "Maple Street", "120A"));
        JsonAdaptedSeller seller = new JsonAdaptedSeller(VALID_NAME, VALID_PHONE, VALID_EMAIL, VALID_HOUSINGTYPE,
                invalidHouses, VALID_TAGS);
        assertThrows(IllegalValueException.class, seller::toModelType);
    }
}
