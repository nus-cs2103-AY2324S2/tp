package seedu.address.model.order;

import java.util.UUID;

/**
 * Represents an Order's ID in the order book.
 * Guarantees: immutable.
 */
public class OrderId {

    public static final String MESSAGE_CONSTRAINTS = "Order ID should be a valid UUID";
    private final UUID id;

    /**
     * Constructs an {@code OrderId}.
     */
    public OrderId() {
        this.id = UUID.randomUUID();
    }

    /**
     * Constructs an {@code OrderId} from a UUID.
     *
     * @param id UUID to be used as the OrderId.
     */
    public OrderId(String id) throws IllegalArgumentException {
        this.id = UUID.fromString(id);
    }

    /**
     * Checks whether a OrderId string is valid.
     *
     * @param test String to be tested.
     * @return true if the string is a valid UUID, false otherwise.
     */
    public static boolean isValidOrderId(String test) {
        try {
            UUID.fromString(test);
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }


    @Override
    public String toString() {
        return id.toString();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (other instanceof String) {
            return id.toString().equals(other);
        }

        if (other instanceof OrderId) {
            OrderId otherOrderId = (OrderId) other;
            return id.equals(otherOrderId.id);
        }

        return false;
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }


}
