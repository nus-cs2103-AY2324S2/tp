package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.order.Amount;
import seedu.address.model.order.Deadline;
import seedu.address.model.order.Discount;
import seedu.address.model.order.Order;
import seedu.address.model.order.OrderId;
import seedu.address.model.order.OrderDate;
import seedu.address.model.order.Remarks;
import seedu.address.model.order.Status;


/**
 * Jackson-friendly version of {@link Order}.
 */

public class JsonAdaptedOrder {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Order's %s field is missing!";
    private final String orderId;
    private final String orderDate;
    private final String deadline;
    private final Double amount;
    private final String remarks;
    private final String status;
    private final Double discount;

    /**
     * Constructs a {@code JsonAdaptedOrder} with the given order details
     */
    @JsonCreator
    public JsonAdaptedOrder(@JsonProperty("orderId") String orderId, @JsonProperty("orderDate") String orderDate,
                            @JsonProperty("deadline") String deadline, @JsonProperty("amount") Double amount,
                            @JsonProperty("remarks") String remarks, @JsonProperty("status") String status,
                            @JsonProperty("discount") Double discount) {
        this.orderId = orderId;
        this.orderDate = orderDate;
        this.deadline = deadline;
        this.amount = amount;
        this.remarks = remarks;
        this.status = status;
        this.discount = discount;
    }

    /**
     * Converts a given {code Order} into this class for Jackson use.
     */
    public JsonAdaptedOrder(Order source) {
        orderId = source.getOrderId().value;
        orderDate = source.getOrderDate().value;
        deadline = source.getDeadline().value;
        amount = source.getAmount().value;
        remarks = source.getRemarks().value;
        status = source.getStatus().value;
        discount = source.getDiscount().value;
    }

    /**
     * Converts this Jackson-friendly adapted order object into the model's {@code Order} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted order.
     */

    public Order toModelType() throws IllegalValueException {
        if (orderId == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, OrderId.class.getSimpleName()));
        }

        if (!OrderId.isValidOrderId(orderId)) {
            throw new IllegalValueException(OrderId.MESSAGE_CONSTRAINTS);
        }
        final OrderId modelOrderId = new OrderId(orderId);

        if (orderDate == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, OrderDate.class.getSimpleName()));
        }

        if (!OrderDate.isValidOrderDate(orderId)) {
            throw new IllegalValueException(OrderDate.MESSAGE_CONSTRAINTS);
        }
        final OrderDate modelOrderDate = new OrderDate(orderDate);

        if (deadline == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Deadline.class.getSimpleName()));
        }

        if (!Deadline.isValidDeadline(deadline)) {
            throw new IllegalValueException(Deadline.MESSAGE_CONSTRAINTS);
        }
        final Deadline modelDeadline = new Deadline(deadline);

        if (amount == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Amount.class.getSimpleName()));
        }

        if (!Amount.isValidAmount(amount)) {
            throw new IllegalValueException(Amount.MESSAGE_CONSTRAINTS);
        }
        final Amount modelAmount = new Amount(amount);

        if (remarks == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Remarks.class.getSimpleName()));
        }

        if (!Remarks.isValidRemarks(remarks)) {
            throw new IllegalValueException(Remarks.MESSAGE_CONSTRAINTS);
        }
        final Remarks modelRemarks = new Remarks(remarks);

        if (status == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Status.class.getSimpleName()));
        }

        if (!Status.isValidStatus(status)) {
            throw new IllegalValueException(Status.MESSAGE_CONSTRAINTS);
        }
        final Status modelStatus = new Status(status);

        if (discount == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Discount.class.getSimpleName()));
        }

        if (!Discount.isValidDiscount(discount)) {
            throw new IllegalValueException(Discount.MESSAGE_CONSTRAINTS);
        }
        final Discount modelDiscount = new Discount(discount);

        return new Order(modelOrderId, modelOrderDate, modelDeadline, modelAmount, modelRemarks, modelStatus, modelDiscount);
    }
}
