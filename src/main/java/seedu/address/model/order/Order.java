package seedu.address.model.order;

import static java.util.Objects.requireNonNull;

/**
 * Represents an Order in the address book.
 */
public class Order {
    private final Date arrivalDate;
    private final String description;

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
    }

    public Date getDate() {
        return arrivalDate;
    }

    public String getDescription() {
        return description;
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
        return arrivalDate.equals(otherOrder.arrivalDate) && description.equals(otherOrder.description);
    }

    @Override
    public int hashCode() {
        return arrivalDate.hashCode() + description.hashCode();
    }

    /**
     * Format state as text for viewing.
     */
    public String toString() {
        return String.format("[%s (by: %s)]", description, arrivalDate);
    }

}
