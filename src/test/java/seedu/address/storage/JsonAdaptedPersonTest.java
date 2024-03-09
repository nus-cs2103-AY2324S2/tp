package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.storage.JsonAdaptedPerson.MISSING_FIELD_MESSAGE_FORMAT;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalPersons.BENSON;
import static seedu.address.testutil.TypicalPersons.BENSONMAINTAINER;
import static seedu.address.testutil.TypicalPersons.BENSONSTAFF;
import static seedu.address.testutil.TypicalPersons.BENSONSUPPLIER;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.person.Address;
import seedu.address.model.person.Commission;
import seedu.address.model.person.Email;
import seedu.address.model.person.Employment;
import seedu.address.model.person.Name;
import seedu.address.model.person.Phone;
import seedu.address.model.person.Price;
import seedu.address.model.person.Product;
import seedu.address.model.person.Salary;
import seedu.address.model.person.Skill;

public class JsonAdaptedPersonTest {
    private static final String INVALID_NAME = "R@chel";
    private static final String INVALID_PHONE = "+651234";
    private static final String INVALID_ADDRESS = " ";
    private static final String INVALID_EMAIL = "example.com";
    private static final String INVALID_TAG = "#friend";
    private static final String INVALID_SALARY = "20";
    private static final String INVALID_PRODUCT = " ";
    private static final String INVALID_EMPLOYMENT = "employment";
    private static final String INVALID_PRICE = "20";
    private static final String INVALID_SKILL = " ";
    private static final String INVALID_COMMISSION = "20";
    private static final String NULL = null;
    private static final String VALID_NAME = BENSON.getName().toString();
    private static final String VALID_PHONE = BENSON.getPhone().toString();
    private static final String VALID_EMAIL = BENSON.getEmail().toString();
    private static final String VALID_ADDRESS = BENSON.getAddress().toString();
    private static final String VALID_SALARY = BENSONSTAFF.getSalary().toString();
    private static final String VALID_EMPLOYMENT = BENSONSTAFF.getEmployment().toString();
    private static final String VALID_PRODUCT = BENSONSUPPLIER.getProduct().toString();
    private static final String VALID_PRICE = BENSONSUPPLIER.getPrice().toString();
    private static final String VALID_SKILL = BENSONMAINTAINER.getSkill().toString();
    private static final String VALID_COMMISSION = BENSONMAINTAINER.getCommission().toString();
    private static final List<JsonAdaptedTag> VALID_TAGS = BENSON.getTags().stream()
            .map(JsonAdaptedTag::new)
            .collect(Collectors.toList());

    @Test
    public void toModelType_validPersonDetails_returnsPerson() throws Exception {
        JsonAdaptedPerson person = new JsonAdaptedPerson(BENSON);
        assertEquals(BENSON, person.toModelType());
    }

    /*
    @Test
    public void toModelType_validStaffDetails_returnsPerson() throws Exception {
        JsonAdaptedPerson person = new JsonAdaptedStaff(BENSONSTAFF);
        assertEquals(BENSONSTAFF, person.toModelType());
    }

    @Test
    public void toModelType_validSupplierDetails_returnsPerson() throws Exception {
        JsonAdaptedSupplier person = new JsonAdaptedSupplier(BENSONSUPPLIER);
        assertEquals(BENSONSUPPLIER, person.toModelType());
    }

    @Test
    public void toModelType_validMaintainerDetails_returnsPerson() throws Exception {
        JsonAdaptedPerson person = new JsonAdaptedMaintainer(BENSONMAINTAINER);
        assertEquals(BENSONMAINTAINER, person.toModelType());
    }
     */


    @Test
    public void toModelType_invalidName_throwsIllegalValueException() {
        JsonAdaptedPerson person =
                new JsonAdaptedPerson(INVALID_NAME, VALID_PHONE, VALID_EMAIL, VALID_ADDRESS, VALID_TAGS,
                        NULL, NULL, NULL, NULL, NULL, NULL);
        String expectedMessage = Name.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_nullName_throwsIllegalValueException() {
        JsonAdaptedPerson person = new JsonAdaptedPerson(null, VALID_PHONE, VALID_EMAIL, VALID_ADDRESS, VALID_TAGS,
                NULL, NULL, NULL, NULL, NULL, NULL);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_invalidPhone_throwsIllegalValueException() {
        JsonAdaptedPerson person =
                new JsonAdaptedPerson(VALID_NAME, INVALID_PHONE, VALID_EMAIL, VALID_ADDRESS, VALID_TAGS,
                        NULL, NULL, NULL, NULL, NULL, NULL);
        String expectedMessage = Phone.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_nullPhone_throwsIllegalValueException() {
        JsonAdaptedPerson person = new JsonAdaptedPerson(VALID_NAME, null, VALID_EMAIL, VALID_ADDRESS, VALID_TAGS,
                NULL, NULL, NULL, NULL, NULL, NULL);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Phone.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_invalidEmail_throwsIllegalValueException() {
        JsonAdaptedPerson person =
                new JsonAdaptedPerson(VALID_NAME, VALID_PHONE, INVALID_EMAIL, VALID_ADDRESS, VALID_TAGS,
                        NULL, NULL, NULL, NULL, NULL, NULL);
        String expectedMessage = Email.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_nullEmail_throwsIllegalValueException() {
        JsonAdaptedPerson person = new JsonAdaptedPerson(VALID_NAME, VALID_PHONE, null, VALID_ADDRESS, VALID_TAGS,
                NULL, NULL, NULL, NULL, NULL, NULL);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Email.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_invalidAddress_throwsIllegalValueException() {
        JsonAdaptedPerson person =
                new JsonAdaptedPerson(VALID_NAME, VALID_PHONE, VALID_EMAIL, INVALID_ADDRESS, VALID_TAGS,
                        NULL, NULL, NULL, NULL, NULL, NULL);
        String expectedMessage = Address.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_nullAddress_throwsIllegalValueException() {
        JsonAdaptedPerson person = new JsonAdaptedPerson(VALID_NAME, VALID_PHONE, VALID_EMAIL, null, VALID_TAGS,
                NULL, NULL, NULL, NULL, NULL, NULL);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Address.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_invalidSalary_throwsIllegalValueException() {
        JsonAdaptedPerson person =
                new JsonAdaptedPerson(VALID_NAME, VALID_PHONE, VALID_EMAIL, VALID_ADDRESS, VALID_TAGS,
                        INVALID_SALARY, VALID_EMPLOYMENT, NULL, NULL, NULL, NULL);
        String expectedMessage = Salary.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_invalidEmployment_throwsIllegalValueException() {
        JsonAdaptedPerson person =
                new JsonAdaptedPerson(VALID_NAME, VALID_PHONE, VALID_EMAIL, VALID_ADDRESS, VALID_TAGS,
                        VALID_SALARY, INVALID_EMPLOYMENT, NULL, NULL, NULL, NULL);
        String expectedMessage = Employment.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_invalidProduct_throwsIllegalValueException() {
        JsonAdaptedPerson person =
                new JsonAdaptedPerson(VALID_NAME, VALID_PHONE, VALID_EMAIL, VALID_ADDRESS, VALID_TAGS,
                        NULL, NULL, INVALID_PRODUCT, VALID_PRICE, NULL, NULL);
        String expectedMessage = Product.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_invalidPrice_throwsIllegalValueException() {
        JsonAdaptedPerson person =
                new JsonAdaptedPerson(VALID_NAME, VALID_PHONE, VALID_EMAIL, VALID_ADDRESS, VALID_TAGS,
                        NULL, NULL, VALID_PRODUCT, INVALID_PRICE, NULL, NULL);
        String expectedMessage = Price.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_invalidSkill_throwsIllegalValueException() {
        JsonAdaptedPerson person =
                new JsonAdaptedPerson(VALID_NAME, VALID_PHONE, VALID_EMAIL, VALID_ADDRESS, VALID_TAGS,
                        NULL, NULL, NULL, NULL, INVALID_SKILL, VALID_COMMISSION);
        String expectedMessage = Skill.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_invalidCommission_throwsIllegalValueException() {
        JsonAdaptedPerson person =
                new JsonAdaptedPerson(VALID_NAME, VALID_PHONE, VALID_EMAIL, VALID_ADDRESS, VALID_TAGS,
                        NULL, NULL, NULL, NULL, VALID_SKILL, INVALID_COMMISSION);
        String expectedMessage = Commission.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_invalidTags_throwsIllegalValueException() {
        List<JsonAdaptedTag> invalidTags = new ArrayList<>(VALID_TAGS);
        invalidTags.add(new JsonAdaptedTag(INVALID_TAG));
        JsonAdaptedPerson person =
                new JsonAdaptedPerson(VALID_NAME, VALID_PHONE, VALID_EMAIL, VALID_ADDRESS, invalidTags,
                        NULL, NULL, NULL, NULL, NULL, NULL);
        assertThrows(IllegalValueException.class, person::toModelType);
    }

}
