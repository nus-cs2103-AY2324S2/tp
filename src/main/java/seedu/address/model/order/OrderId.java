package seedu.address.model.order;

import java.util.UUID;

/**
 * Represents an Order's ID in the order book.
 * Guarantees: immutable.
 */
public class OrderId {

    public final UUID id;

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
    public OrderId(String id) {
        this.id = UUID.fromString(id);
    }

    /**
     * Checks if the given order ID string is in a valid format.
     *
     * @param orderIdStr The order ID string to check.
     * @return True if the order ID string is in a valid format, false otherwise.
     */
    public static boolean isValidOrderId(String orderIdStr) {

        return !orderIdStr.isEmpty();
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
