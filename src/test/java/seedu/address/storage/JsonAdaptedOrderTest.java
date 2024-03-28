package seedu.address.storage;

import static seedu.address.storage.JsonAdaptedOrder.MISSING_FIELD_MESSAGE_FORMAT;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.order.Deadline;
import seedu.address.model.order.OrderDate;
import seedu.address.model.order.OrderId;
import seedu.address.model.order.Price;
import seedu.address.model.order.Remark;
import seedu.address.model.order.Status;

public class JsonAdaptedOrderTest {

    private static final String INVALID_ORDERID = "";
    private static final String INVALID_ORDERDATE = "41-15-2024 21:51";
    private static final String INVALID_DEADLINE = "41-15-2024 21:51";
    private static final String INVALID_PRICE = "0";
    private static final String INVALID_REMARK = "";
    private static final String INVALID_STATUS = "invalid";
    private static final String VALID_ORDERID = "69c25c8d-9e34-4d9d-8bad-e378f203ae73";
    private static final String VALID_ORDERDATE = "01-03-2024 23:59";
    private static final String VALID_DEADLINE = "01-04-2024 23:59";
    private static final String VALID_PRICE = "50";
    private static final String VALID_REMARK = "No remark";
    private static final String VALID_STATUS = "CANCELED";

    /*@Test
    public void toModelType_validOrderDetails_returnsOrder() throws Exception {
        JsonAdaptedOrder order = new JsonAdaptedOrder(ROSES);
        assertEquals(ROSES, order.toModelType());
    }*/

    @Test
    public void toModelType_invalidOrderId_throwsIllegalValueException() {
        JsonAdaptedOrder order =
                new JsonAdaptedOrder(INVALID_ORDERID, VALID_ORDERDATE, VALID_DEADLINE, VALID_PRICE,
                        VALID_REMARK, VALID_STATUS);
        String expectedMessage = OrderId.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, order::toModelType);
    }

    @Test
    public void toModelType_nullOrderId_throwsIllegalValueException() {
        JsonAdaptedOrder order =
                new JsonAdaptedOrder(null, VALID_ORDERDATE, VALID_DEADLINE, VALID_PRICE,
                        VALID_REMARK, VALID_STATUS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, OrderId.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, order::toModelType);
    }

    @Test
    public void toModelType_invalidOrderDate_throwsIllegalValueException() {
        JsonAdaptedOrder order =
                new JsonAdaptedOrder(VALID_ORDERID, INVALID_ORDERDATE, VALID_DEADLINE, VALID_PRICE,
                        VALID_REMARK, VALID_STATUS);
        String expectedMessage = OrderDate.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, order::toModelType);
    }

    @Test
    public void toModelType_nullOrderDate_throwsIllegalValueException() {
        JsonAdaptedOrder order =
                new JsonAdaptedOrder(VALID_ORDERID, null, VALID_DEADLINE, VALID_PRICE,
                        VALID_REMARK, VALID_STATUS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, OrderDate.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, order::toModelType);
    }

    @Test
    public void toModelType_invalidDeadline_throwsIllegalValueException() {
        JsonAdaptedOrder order =
                new JsonAdaptedOrder(VALID_ORDERID, VALID_ORDERDATE, INVALID_DEADLINE, VALID_PRICE,
                        VALID_REMARK, VALID_STATUS);
        String expectedMessage = Deadline.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, order::toModelType);
    }

    @Test
    public void toModelType_nullDeadline_throwsIllegalValueException() {
        JsonAdaptedOrder order =
                new JsonAdaptedOrder(VALID_ORDERID, VALID_ORDERDATE, null, VALID_PRICE,
                        VALID_REMARK, VALID_STATUS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Deadline.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, order::toModelType);
    }

    @Test
    public void toModelType_invalidPrice_throwsIllegalValueException() {
        JsonAdaptedOrder order =
                new JsonAdaptedOrder(VALID_ORDERID, VALID_ORDERDATE, VALID_DEADLINE, INVALID_PRICE,
                        VALID_REMARK, VALID_STATUS);
        String expectedMessage = Price.MESSAGE_CONSTRAINTS;
        assertThrows(NumberFormatException.class, expectedMessage, order::toModelType);
    }

    @Test
    public void toModelType_nullPrice_throwsIllegalValueException() {
        JsonAdaptedOrder order =
                new JsonAdaptedOrder(VALID_ORDERID, VALID_ORDERDATE, VALID_DEADLINE, null,
                        VALID_REMARK, VALID_STATUS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Price.class.getSimpleName());
        assertThrows(NumberFormatException.class, expectedMessage, order::toModelType);
    }

    @Test
    public void toModelType_invalidRemark_throwsIllegalValueException() {
        JsonAdaptedOrder order =
                new JsonAdaptedOrder(VALID_ORDERID, VALID_ORDERDATE, VALID_DEADLINE, VALID_PRICE,
                        INVALID_REMARK, VALID_STATUS);
        String expectedMessage = Remark.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, order::toModelType);
    }

    @Test
    public void toModelType_nullRemark_throwsIllegalValueException() {
        JsonAdaptedOrder order =
                new JsonAdaptedOrder(VALID_ORDERID, VALID_ORDERDATE, VALID_DEADLINE, VALID_PRICE,
                        null, VALID_STATUS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Remark.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, order::toModelType);
    }

    @Test
    public void toModelType_invalidStatus_throwsIllegalValueException() {
        JsonAdaptedOrder order =
                new JsonAdaptedOrder(VALID_ORDERID, VALID_ORDERDATE, VALID_DEADLINE, VALID_PRICE,
                        VALID_REMARK, INVALID_STATUS);
        String expectedMessage = Status.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, order::toModelType);
    }

    @Test
    public void toModelType_nullStatus_throwsIllegalValueException() {
        JsonAdaptedOrder order =
                new JsonAdaptedOrder(VALID_ORDERID, VALID_ORDERDATE, VALID_DEADLINE, VALID_PRICE,
                        VALID_REMARK, null);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Status.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, order::toModelType);
    }
}

