package seedu.address.model.order;

import java.util.UUID;

/**
 * Represents an Order's ID in the order book.
 * Guarantees: immutable.
 */
public class OrderId {
    private final UUID id;

    /**
     * Constructs an {@code OrderId}.
     */
    public OrderId() {
        this.id = UUID.randomUUID();
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

        if (!(other instanceof OrderId)) {
            return false;
        }

        OrderId otherOrderId = (OrderId) other;
        return id.equals(otherOrderId.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }


}
