package seedu.address.model.person;

import java.util.Objects;

/**
 * Represents a person's pay rate in the contact book
 * A pay rate is represented as a floating-point number.
 * <p>
 * Pay rates should only contain numeric values.
 * </p>
 * Guarantees: immutable
 */
public class PayRate {

    public static final String MESSAGE_CONSTRAINTS =
            "Pay rate should only contain numbers";

    public final double value;

    /**
     * Constructs a {@code PayRate} with the specified pay rate value.
     *
     * @param payRate The pay rate value.
     */
    public PayRate(double payRate) {
        this.value = payRate;
    }

    public double getPayRate() {
        return value;
    }

    /**
     * Returns a string representation of the pay rate.
     *
     * @return A string representing the pay rate.
     */
    @Override
    public String toString() {
        return "Pay Rate: $ " + value;
    }

    /**
     * Checks if the given pay rate string is valid.
     *
     * @param payRate The pay rate string to be checked for validity.
     * @return {@code true} if the pay rate string is valid, {@code false} otherwise.
     */
    public static boolean isValidPayRate(String payRate) {
        if (payRate == null) {
            return false;
        }
        try {
            double rate = Double.parseDouble(payRate);
            return rate > 0;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    /**
     * Checks if this pay rate is equal to another object.
     *
     * @param other The object to compare with.
     * @return {@code true} if the objects are equal, {@code false} otherwise.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if (!(other instanceof PayRate)) {
            return false;
        }
        PayRate otherPayRate = (PayRate) other;
        return value == otherPayRate.value;
    }

    /**
     * Returns the hash code of the pay rate.
     *
     * @return The hash code of the pay rate.
     */
    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
