package seedu.address.model.order;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Represents the Quantity of Products in the Order.
 */
public class Quantity {
    public static final String MESSAGE_CONSTRAINTS =
            "Product quantity should only be a number.";

    /** Accept only non-negative integers*/
    public static final String VALIDATION_REGEX = "^\\d*$";

    private int value;

    /**
     * Constructs a {@code Order} with {@code value}
     * @param value value of the Quantity.
     */
    public Quantity(@JsonProperty("order") int value) {

        this.value = value;
    }

    /**
     * Returns true if a given string is a valid phone number.
     */
    public static boolean isValidQuantity(String test) {
        return test.matches(VALIDATION_REGEX);
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

    /**
     * Gets the value of the Quantity.
     * @return value of the Quantity.
     */
    public int getValue() {
        return this.value;
    }

    /**
     * Sets the value of the Quantity
     * @param newQuantity new value for the Quantity to be set to.
     */
    public void setQuantity(int newQuantity) {
        this.value = newQuantity;
    }
}
