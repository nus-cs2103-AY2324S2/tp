package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.order.Date;
import seedu.address.model.order.Order;
import seedu.address.model.order.Remark;
import seedu.address.model.order.Status;

/**
 * Jackson-friendly version of {@link Order}.
 */
class JsonAdaptedOrder {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Order's %s field is missing!";

    private final String arrivalDate;
    private final String remark;
    private final String status;

    /**
     * Constructs a {@code JsonAdaptedOrder} with the given {@code order}.
     */
    @JsonCreator
    public JsonAdaptedOrder(@JsonProperty("arrivalDate") String arrivalDate,
                            @JsonProperty("remark") String remark,
                            @JsonProperty("status") String status) {
        this.arrivalDate = arrivalDate;
        this.remark = remark;
        this.status = status;
    }

    /**
     * Converts a given {@code Order} into this class for Jackson use.
     */
    public JsonAdaptedOrder(Order source) {
        this.arrivalDate = source.getDate().toString();
        this.remark = source.getRemark().toString();
        this.status = source.getStatus().toString();
    }

    /**
     * Converts this Jackson-friendly adapted order object into the model's {@code Order} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted order.
     */
    public Order toModelType() throws IllegalValueException {
        if (arrivalDate == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, "Date"));
        }

        if (remark == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, "Remark"));
        }

        if (status == null || !Status.isValidStatus(status)) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, "Status"));
        }

        final Date modelDate = new Date(arrivalDate);
        final Remark modelRemark = new Remark(remark);
        final Status modelStatus = Status.valueOf(status.toUpperCase());

        return new Order(modelDate, modelRemark, modelStatus);
    }
}
