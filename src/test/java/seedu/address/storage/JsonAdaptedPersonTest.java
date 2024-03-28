package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.storage.JsonAdaptedPerson.MISSING_FIELD_MESSAGE_FORMAT;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalPersons.BENSON;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.logic.commands.AddMemPointsCommand;
import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Phone;
import seedu.address.model.person.Points;
import seedu.address.model.person.orders.Order;

public class JsonAdaptedPersonTest {
    private static final String INVALID_NAME = "R@chel";
    private static final String INVALID_PHONE = "+651234";
    private static final String INVALID_ADDRESS = " ";
    private static final String INVALID_EMAIL = "example.com";
    private static final String INVALID_ALLERGEN = "#EG";
    private static final String INVALID_POINTS = "-50";
    private static final String INVALID_ORDERITEM = " ";
    private static final String INVALID_ORDERDATETIME = "13JUNE23";

    private static final String INVALID_MEM_POINTS1 = "T1";

    private static final String INVALID_MEM_POINTS2 = "-10";

    private static final String VALID_NAME = BENSON.getName().toString();
    private static final String VALID_PHONE = BENSON.getPhone().toString();
    private static final String VALID_EMAIL = BENSON.getEmail().toString();
    private static final String VALID_ADDRESS = BENSON.getAddress().toString();
    private static final String VALID_MEMBERSHIP = String.valueOf(BENSON.getMembershipPoints().value);
    private static final List<JsonAdaptedAllergen> VALID_ALLERGENS = BENSON.getAllergens().stream()
            .map(JsonAdaptedAllergen::new)
            .collect(Collectors.toList());
    private static final String VALID_POINTS = BENSON.getPoints().toString();
    private static final String VALID_ORDERITEM = "Cupcake x 3";
    private static final String VALID_ORDERDATETIME = "2023-01-03T15:15:15";
    private static final List<JsonAdaptedOrder> VALID_ORDERS = BENSON.getOrders().stream()
            .map(JsonAdaptedOrder::new)
            .collect(Collectors.toList());

    @Test
    public void toModelType_validPersonDetails_returnsPerson() throws Exception {
        JsonAdaptedPerson person = new JsonAdaptedPerson(BENSON);
        assertEquals(BENSON, person.toModelType());
    }

    @Test
    public void toModelType_invalidName_throwsIllegalValueException() {
        JsonAdaptedPerson person =
                new JsonAdaptedPerson(INVALID_NAME, VALID_PHONE, VALID_EMAIL, VALID_ADDRESS, VALID_MEMBERSHIP,
                        VALID_ALLERGENS, VALID_POINTS, VALID_ORDERS);
        String expectedMessage = Name.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_nullName_throwsIllegalValueException() {
        JsonAdaptedPerson person = new JsonAdaptedPerson(null, VALID_PHONE, VALID_EMAIL, VALID_ADDRESS,
                VALID_MEMBERSHIP, VALID_ALLERGENS, VALID_POINTS, VALID_ORDERS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_invalidPhone_throwsIllegalValueException() {
        JsonAdaptedPerson person =
                new JsonAdaptedPerson(VALID_NAME, INVALID_PHONE, VALID_EMAIL, VALID_ADDRESS, VALID_MEMBERSHIP,
                        VALID_ALLERGENS, VALID_POINTS, VALID_ORDERS);
        String expectedMessage = Phone.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_nullPhone_throwsIllegalValueException() {
        JsonAdaptedPerson person = new JsonAdaptedPerson(VALID_NAME, null, VALID_EMAIL, VALID_ADDRESS,
                VALID_MEMBERSHIP, VALID_ALLERGENS, VALID_POINTS, VALID_ORDERS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Phone.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_invalidEmail_throwsIllegalValueException() {
        JsonAdaptedPerson person =
                new JsonAdaptedPerson(VALID_NAME, VALID_PHONE, INVALID_EMAIL, VALID_ADDRESS, VALID_MEMBERSHIP,
                        VALID_ALLERGENS, VALID_POINTS, VALID_ORDERS);
        String expectedMessage = Email.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_nullEmail_throwsIllegalValueException() {
        JsonAdaptedPerson person = new JsonAdaptedPerson(VALID_NAME, VALID_PHONE, null, VALID_ADDRESS,
                VALID_MEMBERSHIP, VALID_ALLERGENS, VALID_POINTS, VALID_ORDERS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Email.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_invalidAddress_throwsIllegalValueException() {
        JsonAdaptedPerson person =
                new JsonAdaptedPerson(VALID_NAME, VALID_PHONE, VALID_EMAIL, INVALID_ADDRESS, VALID_MEMBERSHIP,
                        VALID_ALLERGENS, VALID_POINTS, VALID_ORDERS);
        String expectedMessage = Address.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_nullAddress_throwsIllegalValueException() {
        JsonAdaptedPerson person = new JsonAdaptedPerson(VALID_NAME, VALID_PHONE, VALID_EMAIL, null,
                VALID_MEMBERSHIP, VALID_ALLERGENS, VALID_POINTS, VALID_ORDERS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Address.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_invalidAllergens_throwsIllegalValueException() {
        List<JsonAdaptedAllergen> invalidAllergens = new ArrayList<>(VALID_ALLERGENS);
        invalidAllergens.add(new JsonAdaptedAllergen(INVALID_ALLERGEN));
        JsonAdaptedPerson person =
                new JsonAdaptedPerson(VALID_NAME, VALID_PHONE, VALID_EMAIL, VALID_ADDRESS, VALID_MEMBERSHIP,
                        invalidAllergens, VALID_POINTS, VALID_ORDERS);
        assertThrows(IllegalValueException.class, person::toModelType);
    }

    @Test
    public void toModelType_invalidPoints_throwsIllegalValueException() {
        JsonAdaptedPerson person =
                new JsonAdaptedPerson(VALID_NAME, VALID_PHONE, VALID_EMAIL, VALID_ADDRESS, VALID_MEMBERSHIP,
                        VALID_ALLERGENS, INVALID_POINTS, VALID_ORDERS);
        String expectedMessage = Points.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_nullPoints_throwsIllegalValueException() {
        JsonAdaptedPerson person = new JsonAdaptedPerson(VALID_NAME, VALID_PHONE, VALID_EMAIL, VALID_ADDRESS,
                VALID_MEMBERSHIP, VALID_ALLERGENS, null, VALID_ORDERS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Points.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_invalidOrderItem_throwsIllegalValueException() {
        List<JsonAdaptedOrder> invalidOrders = new ArrayList<>(VALID_ORDERS);
        invalidOrders.add(new JsonAdaptedOrder(INVALID_ORDERITEM, VALID_ORDERDATETIME));
        JsonAdaptedPerson person =
                new JsonAdaptedPerson(VALID_NAME, VALID_PHONE, VALID_EMAIL, VALID_ADDRESS,
                        VALID_MEMBERSHIP, VALID_ALLERGENS, VALID_POINTS, invalidOrders);
        String expectedMessage = Order.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_invalidOrderDateTime_throwsIllegalValueException() {
        List<JsonAdaptedOrder> invalidOrders = new ArrayList<>(VALID_ORDERS);
        invalidOrders.add(new JsonAdaptedOrder(VALID_ORDERITEM, INVALID_ORDERDATETIME));
        JsonAdaptedPerson person =
                new JsonAdaptedPerson(VALID_NAME, VALID_PHONE, VALID_EMAIL, VALID_ADDRESS,
                        VALID_MEMBERSHIP, VALID_ALLERGENS, VALID_POINTS, invalidOrders);
        String expectedMessage = Order.MESSAGE_INVALID_DATETIME;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_invalidMembershipPoints1_throwsIllegalValueException() {
        JsonAdaptedPerson person =
                new JsonAdaptedPerson(VALID_NAME, VALID_PHONE, VALID_EMAIL, VALID_ADDRESS, INVALID_MEM_POINTS1,
                        VALID_ALLERGENS, VALID_POINTS, VALID_ORDERS);
        String expectedMessage = AddMemPointsCommand.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_invalidMembershipPoints2_throwsIllegalValueException() {
        JsonAdaptedPerson person =
                new JsonAdaptedPerson(VALID_NAME, VALID_PHONE, VALID_EMAIL, VALID_ADDRESS, INVALID_MEM_POINTS2,
                        VALID_ALLERGENS, VALID_POINTS, VALID_ORDERS);
        String expectedMessage = AddMemPointsCommand.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }
}
