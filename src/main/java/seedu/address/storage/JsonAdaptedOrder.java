package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.order.Deadline;
import seedu.address.model.order.Order;
import seedu.address.model.order.OrderDate;
import seedu.address.model.order.OrderId;
import seedu.address.model.order.Price;
import seedu.address.model.order.Remark;
import seedu.address.model.order.Status;


/**
 * Jackson-friendly version of {@link Order}.
 */

public class JsonAdaptedOrder {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Order's %s field is missing!";
    private final String orderId;
    private final String orderDate;
    private final String deadline;
    private final String price;
    private final String remark;
    private final String status;

    /**
     * Constructs a {@code JsonAdaptedOrder} with the given order details.
     */
    @JsonCreator
    public JsonAdaptedOrder(@JsonProperty("orderId") String orderId, @JsonProperty("orderDate") String orderDate,
                            @JsonProperty("deadline") String deadline, @JsonProperty("price") String price,
                            @JsonProperty("remark") String remark, @JsonProperty("status") String status) {
        this.orderId = orderId;
        this.orderDate = orderDate;
        this.deadline = deadline;
        this.price = price;
        this.remark = remark;
        this.status = status;
    }

    /**
     * Converts a given {code Order} into this class for Jackson use.
     */
    public JsonAdaptedOrder(Order source) {
        orderId = source.getOrderId().toString();
        orderDate = source.getOrderDate().toString();
        deadline = source.getDeadline().toString();
        price = source.getPrice().toString();
        remark = source.getRemark().toString();
        status = source.getStatus().toString();
    }

    /**
     * Converts this Jackson-friendly adapted order object into the model's {@code Order} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted order.
     */

    public Order toModelType() throws IllegalValueException {

        if (orderId == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    OrderId.class.getSimpleName()));
        }

        if (!OrderId.isValidOrderId(orderId)) {
            throw new IllegalValueException(OrderId.MESSAGE_CONSTRAINTS);
        }
        final OrderId modelOrderId = new OrderId(orderId);

        if (orderDate == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    OrderDate.class.getSimpleName()));
        }

        if (!OrderDate.isValidOrderDate(orderDate)) {
            throw new IllegalValueException(OrderDate.MESSAGE_CONSTRAINTS);
        }
        final OrderDate modelOrderDate = new OrderDate(orderDate);

        if (deadline == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    Deadline.class.getSimpleName()));
        }

        if (!Deadline.isValidDeadline(deadline)) {
            throw new IllegalValueException(Deadline.MESSAGE_CONSTRAINTS);
        }
        final Deadline modelDeadline = new Deadline(deadline);

        if (price == null) {
            throw new NumberFormatException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Price.class.getSimpleName()));
        }

        if (!Price.isValidPrice(price)) {
            throw new NumberFormatException(Price.MESSAGE_CONSTRAINTS);
        }
        final Price modelPrice = new Price(String.valueOf(price));

        if (remark == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Remark.class.getSimpleName()));
        }

        if (!Remark.isValidRemark(remark)) {
            throw new IllegalValueException(Remark.MESSAGE_CONSTRAINTS);
        }
        final Remark modelRemarks = new Remark(remark);

        if (status == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Status.class.getSimpleName()));
        }

        if (!Status.isValidStatus(status)) {
            throw new IllegalValueException(Status.MESSAGE_CONSTRAINTS);
        }
        final Status modelStatus = new Status(status);

        return new Order(modelOrderId, modelOrderDate, modelDeadline, modelPrice, modelRemarks, modelStatus);
    }
}
