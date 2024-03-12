package seedu.address.model.order;

import static java.util.Objects.requireNonNull;

/**
 * Represents an Order in the address book.
 */
public class Order {
    private final Date arrivalDate;
    private final String description;
    private final String status;

    /**
     * Constructs a {@code Order}.
     *
     * @param arrivalDate a valid date of the order.
     * @param description a valid description of the order.
     */
    public Order(Date arrivalDate, String description) {
        requireNonNull(arrivalDate);
        requireNonNull(description);

        this.arrivalDate = arrivalDate;
        this.description = description;
        this.status = "Pending";
    }

    /**
     * Constructs a {@code Order}.
     *
     * @param arrivalDate a valid date of the order.
     * @param description a valid description of the order.
     * @param status a valid status of the order.
     */
    public Order(Date arrivalDate, String description, String status) {
        requireNonNull(arrivalDate);
        requireNonNull(description);

        this.arrivalDate = arrivalDate;
        this.description = description;
        this.status = status;
    }

    public Date getDate() {
        return arrivalDate;
    }

    public String getDescription() {
        return description;
    }

    public String getStatus() {
        return status;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof Order)) {
            return false;
        }

        Order otherOrder = (Order) other;
        return arrivalDate.equals(otherOrder.arrivalDate) && description.equals(otherOrder.description)
                && status.equals(otherOrder.status);
    }

    @Override
    public int hashCode() {
        return arrivalDate.hashCode() + description.hashCode() + status.hashCode();
    }

    /**
     * Format state as text for viewing.
     */
    public String toString() {
        return String.format("[%s (by: %s, status: %s)]", description, arrivalDate, status);
    }

}
