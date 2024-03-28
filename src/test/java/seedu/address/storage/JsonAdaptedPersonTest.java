package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.storage.JsonAdaptedPerson.MISSING_FIELD_MESSAGE_FORMAT;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalPersons.BENSON;

import java.util.Arrays;
import java.util.Collections;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.Id;
import seedu.address.model.person.Name;
import seedu.address.model.person.Phone;
import seedu.address.model.person.Products;
import seedu.address.model.tag.Tag;

public class JsonAdaptedPersonTest {
    private static final int INVALID_ID = 0;
    private static final String INVALID_NAME = "R@chel";
    private static final String INVALID_PHONE = "+651234";
    private static final String INVALID_ADDRESS = " ";
    private static final String INVALID_EMAIL = "example.com";
    private static final String INVALID_TAG = "#friend";

    private static final int VALID_ID = BENSON.getId().value;
    private static final String VALID_NAME = BENSON.getName().fullName;
    private static final String VALID_PHONE = BENSON.getPhone().value;
    private static final String VALID_EMAIL = BENSON.getEmail().value;
    private static final String VALID_ADDRESS = "Central Perk";
    private static final String VALID_ROLE_CLIENT = "client";
    private static final String VALID_ROLE_EMPLOYEE = "employee";
    private static final String VALID_ROLE_SUPPLIER = "supplier";
    private static final JsonAdaptedProducts VALID_PRODUCTS =
            new JsonAdaptedProducts(new Products(Arrays.asList("Coffee", "Cake")));
    private static final String VALID_PREFERENCES = "Cafe";
    private static final String VALID_REMARK = "Loves coffee";

    @Test
    public void toModelType_validPersonDetails_returnsPerson() throws Exception {
        JsonAdaptedPerson person = new JsonAdaptedPerson(VALID_ID, VALID_NAME, VALID_PHONE, VALID_EMAIL, VALID_ADDRESS,
                Collections.singletonList(new JsonAdaptedTag("Friend")), VALID_ROLE_CLIENT, VALID_PRODUCTS,
                VALID_PREFERENCES, null, null, null, null, VALID_REMARK);
        assertEquals(person.toModelType().getName(), new Name(VALID_NAME));
        assertEquals(person.toModelType().getPhone(), new Phone(VALID_PHONE));
        assertEquals(person.toModelType().getEmail(), new Email(VALID_EMAIL));
        assertEquals(person.toModelType().getAddress(), new Address(VALID_ADDRESS));
        assertEquals(person.toModelType().getTags().size(), 1);
        assertEquals(((Tag) person.toModelType().getTags().toArray()[0]).tagName, "Friend");
    }

    @Test
    public void toModelType_invalidId_throwsIllegalValueException() {
        JsonAdaptedPerson person =
                new JsonAdaptedPerson(INVALID_ID, VALID_NAME, VALID_PHONE, VALID_EMAIL, VALID_ADDRESS,
                Collections.emptyList(), VALID_ROLE_CLIENT, VALID_PRODUCTS, VALID_PREFERENCES, null, null,
                null, null, VALID_REMARK);
        String expectedMessage = Id.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_invalidName_throwsIllegalValueException() {
        JsonAdaptedPerson person = new JsonAdaptedPerson(VALID_ID, INVALID_NAME, VALID_PHONE, VALID_EMAIL,
                VALID_ADDRESS, Collections.emptyList(), VALID_ROLE_CLIENT, VALID_PRODUCTS, VALID_PREFERENCES, null,
                null, null, null, VALID_REMARK);
        String expectedMessage = Name.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_nullName_throwsIllegalValueException() {
        JsonAdaptedPerson person = new JsonAdaptedPerson(VALID_ID, null, VALID_PHONE, VALID_EMAIL, VALID_ADDRESS,
                Collections.emptyList(), VALID_ROLE_CLIENT, VALID_PRODUCTS, VALID_PREFERENCES, null, null,
                null, null, VALID_REMARK);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_invalidPhone_throwsIllegalValueException() {
        JsonAdaptedPerson person = new JsonAdaptedPerson(VALID_ID, VALID_NAME, INVALID_PHONE, VALID_EMAIL,
                VALID_ADDRESS, Collections.emptyList(), VALID_ROLE_CLIENT, VALID_PRODUCTS, VALID_PREFERENCES, null,
                null, null, null, VALID_REMARK);
        String expectedMessage = Phone.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_nullPhone_throwsIllegalValueException() {
        JsonAdaptedPerson person = new JsonAdaptedPerson(VALID_ID, VALID_NAME, null, VALID_EMAIL, VALID_ADDRESS,
                Collections.emptyList(), VALID_ROLE_CLIENT, VALID_PRODUCTS, VALID_PREFERENCES, null, null,
                null, null, VALID_REMARK);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Phone.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_invalidEmail_throwsIllegalValueException() {
        JsonAdaptedPerson person = new JsonAdaptedPerson(VALID_ID, VALID_NAME, VALID_PHONE, INVALID_EMAIL,
                VALID_ADDRESS, Collections.emptyList(), VALID_ROLE_CLIENT, VALID_PRODUCTS, VALID_PREFERENCES, null,
                null, null, null, VALID_REMARK);
        String expectedMessage = Email.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_nullEmail_throwsIllegalValueException() {
        JsonAdaptedPerson person = new JsonAdaptedPerson(VALID_ID, VALID_NAME, VALID_PHONE, null, VALID_ADDRESS,
                Collections.emptyList(), VALID_ROLE_CLIENT, VALID_PRODUCTS, VALID_PREFERENCES, null, null,
                null, null, VALID_REMARK);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Email.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_invalidAddress_throwsIllegalValueException() {
        JsonAdaptedPerson person = new JsonAdaptedPerson(VALID_ID, VALID_NAME, VALID_PHONE, VALID_EMAIL,
                INVALID_ADDRESS, Collections.emptyList(), VALID_ROLE_CLIENT, VALID_PRODUCTS, VALID_PREFERENCES, null,
                null, null, null, VALID_REMARK);
        String expectedMessage = Address.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_nullAddress_throwsIllegalValueException() {
        JsonAdaptedPerson person = new JsonAdaptedPerson(VALID_ID, VALID_NAME, VALID_PHONE, VALID_EMAIL, null,
                Collections.emptyList(), VALID_ROLE_CLIENT, VALID_PRODUCTS, VALID_PREFERENCES, null, null,
                null, null, VALID_REMARK);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Address.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_invalidRole_throwsIllegalValueException() {
        JsonAdaptedPerson person = new JsonAdaptedPerson(VALID_ID, VALID_NAME, VALID_PHONE, VALID_EMAIL, VALID_ADDRESS,
                Collections.emptyList(), "invalidRole", VALID_PRODUCTS, VALID_PREFERENCES, null,
                null, null, null, VALID_REMARK);
        String expectedMessage = JsonAdaptedPerson.INVALID_ROLE_MESSAGE_FORMAT;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_invalidTags_throwsIllegalValueException() {
        JsonAdaptedPerson person = new JsonAdaptedPerson(VALID_ID, VALID_NAME, VALID_PHONE, VALID_EMAIL, VALID_ADDRESS,
                Collections.singletonList(new JsonAdaptedTag(INVALID_TAG)), VALID_ROLE_CLIENT, VALID_PRODUCTS,
                VALID_PREFERENCES, null, null, null, null, VALID_REMARK);
        assertThrows(IllegalValueException.class, person::toModelType);
    }

    @Test
    public void toEmployeeModelType_invalidName_throwsIllegalValueException() {
        JsonAdaptedPerson person = new JsonAdaptedPerson(VALID_ID, INVALID_NAME, VALID_PHONE, VALID_EMAIL,
                VALID_ADDRESS, Collections.emptyList(), VALID_ROLE_CLIENT, VALID_PRODUCTS, VALID_PREFERENCES, null,
                null, null, null, VALID_REMARK);
        String expectedMessage = Name.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toEmployeeModelType);
    }

    @Test
    public void toEmployeeModelType_nullName_throwsIllegalValueException() {
        JsonAdaptedPerson person = new JsonAdaptedPerson(VALID_ID, null, VALID_PHONE, VALID_EMAIL, VALID_ADDRESS,
                Collections.emptyList(), VALID_ROLE_EMPLOYEE, VALID_PRODUCTS, VALID_PREFERENCES, null, null,
                null, null, VALID_REMARK);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, person::toEmployeeModelType);
    }

    @Test
    public void toEmployeeModelType_invalidPhone_throwsIllegalValueException() {
        JsonAdaptedPerson person = new JsonAdaptedPerson(VALID_ID, VALID_NAME, INVALID_PHONE, VALID_EMAIL,
                VALID_ADDRESS, Collections.emptyList(), VALID_ROLE_EMPLOYEE, VALID_PRODUCTS, VALID_PREFERENCES, null,
                null, null, null, VALID_REMARK);
        String expectedMessage = Phone.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toEmployeeModelType);
    }

    @Test
    public void toEmployeeModelType_nullPhone_throwsIllegalValueException() {
        JsonAdaptedPerson person = new JsonAdaptedPerson(VALID_ID, VALID_NAME, null, VALID_EMAIL, VALID_ADDRESS,
                Collections.emptyList(), VALID_ROLE_EMPLOYEE, VALID_PRODUCTS, VALID_PREFERENCES, null, null,
                null, null, VALID_REMARK);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Phone.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, person::toEmployeeModelType);
    }

    @Test
    public void toEmployeeModelType_invalidEmail_throwsIllegalValueException() {
        JsonAdaptedPerson person = new JsonAdaptedPerson(VALID_ID, VALID_NAME, VALID_PHONE, INVALID_EMAIL,
                VALID_ADDRESS, Collections.emptyList(), VALID_ROLE_EMPLOYEE, VALID_PRODUCTS, VALID_PREFERENCES, null,
                null, null, null, VALID_REMARK);
        String expectedMessage = Email.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toEmployeeModelType);
    }

    @Test
    public void toEmployeeModelType_nullEmail_throwsIllegalValueException() {
        JsonAdaptedPerson person = new JsonAdaptedPerson(VALID_ID, VALID_NAME, VALID_PHONE, null, VALID_ADDRESS,
                Collections.emptyList(), VALID_ROLE_EMPLOYEE, VALID_PRODUCTS, VALID_PREFERENCES, null, null,
                null, null, VALID_REMARK);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Email.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, person::toEmployeeModelType);
    }

    @Test
    public void toEmployeeModelType_invalidAddress_throwsIllegalValueException() {
        JsonAdaptedPerson person = new JsonAdaptedPerson(VALID_ID, VALID_NAME, VALID_PHONE, VALID_EMAIL,
                INVALID_ADDRESS, Collections.emptyList(), VALID_ROLE_EMPLOYEE, VALID_PRODUCTS, VALID_PREFERENCES, null,
                null, null, null, VALID_REMARK);
        String expectedMessage = Address.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toEmployeeModelType);
    }

    @Test
    public void toEmployeeModelType_nullAddress_throwsIllegalValueException() {
        JsonAdaptedPerson person = new JsonAdaptedPerson(VALID_ID, VALID_NAME, VALID_PHONE, VALID_EMAIL, null,
                Collections.emptyList(), VALID_ROLE_EMPLOYEE, VALID_PRODUCTS, VALID_PREFERENCES, null, null,
                null, null, VALID_REMARK);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Address.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, person::toEmployeeModelType);
    }

    @Test
    public void toEmployeeModelType_invalidTags_throwsIllegalValueException() {
        JsonAdaptedPerson person = new JsonAdaptedPerson(VALID_ID, VALID_NAME, VALID_PHONE, VALID_EMAIL, VALID_ADDRESS,
                Collections.singletonList(new JsonAdaptedTag(INVALID_TAG)), VALID_ROLE_CLIENT, VALID_PRODUCTS,
                VALID_PREFERENCES, null, null, null, null, VALID_REMARK);
        assertThrows(IllegalValueException.class, person::toEmployeeModelType);
    }

    @Test
    public void toSupplierModelType_invalidName_throwsIllegalValueException() {
        JsonAdaptedPerson person = new JsonAdaptedPerson(VALID_ID, INVALID_NAME, VALID_PHONE, VALID_EMAIL,
                VALID_ADDRESS, Collections.emptyList(), VALID_ROLE_SUPPLIER, VALID_PRODUCTS, VALID_PREFERENCES, null,
                null, null, null, VALID_REMARK);
        String expectedMessage = Name.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toSupplierModelType);
    }

    @Test
    public void toSupplierModelType_nullName_throwsIllegalValueException() {
        JsonAdaptedPerson person = new JsonAdaptedPerson(VALID_ID, null, VALID_PHONE, VALID_EMAIL, VALID_ADDRESS,
                Collections.emptyList(), VALID_ROLE_SUPPLIER, VALID_PRODUCTS, VALID_PREFERENCES, null, null,
                null, null, VALID_REMARK);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, person::toSupplierModelType);
    }

    @Test
    public void toSupplierModelType_invalidPhone_throwsIllegalValueException() {
        JsonAdaptedPerson person = new JsonAdaptedPerson(VALID_ID, VALID_NAME, INVALID_PHONE, VALID_EMAIL,
                VALID_ADDRESS, Collections.emptyList(), VALID_ROLE_SUPPLIER, VALID_PRODUCTS, VALID_PREFERENCES, null,
                null, null, null, VALID_REMARK);
        String expectedMessage = Phone.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toSupplierModelType);
    }

    @Test
    public void toSupplierModelType_nullPhone_throwsIllegalValueException() {
        JsonAdaptedPerson person = new JsonAdaptedPerson(VALID_ID, VALID_NAME, null, VALID_EMAIL, VALID_ADDRESS,
                Collections.emptyList(), VALID_ROLE_SUPPLIER, VALID_PRODUCTS, VALID_PREFERENCES, null, null,
                null, null, VALID_REMARK);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Phone.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, person::toSupplierModelType);
    }

    @Test
    public void toSupplierModelType_invalidEmail_throwsIllegalValueException() {
        JsonAdaptedPerson person = new JsonAdaptedPerson(VALID_ID, VALID_NAME, VALID_PHONE, INVALID_EMAIL,
                VALID_ADDRESS, Collections.emptyList(), VALID_ROLE_SUPPLIER, VALID_PRODUCTS, VALID_PREFERENCES, null,
                null, null, null, VALID_REMARK);
        String expectedMessage = Email.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toSupplierModelType);
    }

    @Test
    public void toSupplierModelType_nullEmail_throwsIllegalValueException() {
        JsonAdaptedPerson person = new JsonAdaptedPerson(VALID_ID, VALID_NAME, VALID_PHONE, null, VALID_ADDRESS,
                Collections.emptyList(), VALID_ROLE_SUPPLIER, VALID_PRODUCTS, VALID_PREFERENCES, null, null,
                null, null, VALID_REMARK);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Email.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, person::toSupplierModelType);
    }

    @Test
    public void toSupplierModelType_invalidAddress_throwsIllegalValueException() {
        JsonAdaptedPerson person = new JsonAdaptedPerson(VALID_ID, VALID_NAME, VALID_PHONE, VALID_EMAIL,
                INVALID_ADDRESS, Collections.emptyList(), VALID_ROLE_SUPPLIER, VALID_PRODUCTS, VALID_PREFERENCES, null,
                null, null, null, VALID_REMARK);
        String expectedMessage = Address.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toSupplierModelType);
    }

    @Test
    public void toSupplierModelType_nullAddress_throwsIllegalValueException() {
        JsonAdaptedPerson person = new JsonAdaptedPerson(VALID_ID, VALID_NAME, VALID_PHONE, VALID_EMAIL, null,
                Collections.emptyList(), VALID_ROLE_SUPPLIER, VALID_PRODUCTS, VALID_PREFERENCES, null, null,
                null, null, VALID_REMARK);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Address.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, person::toSupplierModelType);
    }

    @Test
    public void toSupplierModelType_invalidTags_throwsIllegalValueException() {
        JsonAdaptedPerson person = new JsonAdaptedPerson(VALID_ID, VALID_NAME, VALID_PHONE, VALID_EMAIL, VALID_ADDRESS,
                Collections.singletonList(new JsonAdaptedTag(INVALID_TAG)), VALID_ROLE_SUPPLIER, VALID_PRODUCTS,
                VALID_PREFERENCES, null, null, null, null, VALID_REMARK);
        assertThrows(IllegalValueException.class, person::toSupplierModelType);
    }

}
