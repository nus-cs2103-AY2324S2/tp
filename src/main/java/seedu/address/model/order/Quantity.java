package seedu.address.model.order;

public class Quantity {
    public static final String MESSAGE_CONSTRAINTS =
            "Product quantity should only be a number.";
    public static final String VALIDATION_REGEX = "\\d";
    public int value;

    public Quantity(int value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return Integer.toString(this.value);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof Quantity)) {
            return false;
        }

        Quantity otherQuantity = (Quantity) other;
        return this.value == otherQuantity.value;
    }

    @Override
    public int hashCode() {
        return Integer.hashCode(this.value);
    }

    public int getValue() {
        return this.value;
    }

    public void setQuantity(int newQuantity) {
        this.value = newQuantity;
    }
}
