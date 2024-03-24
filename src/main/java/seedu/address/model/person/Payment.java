package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Person's payment in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidPayment(double)}
 */
public class Payment {

    public static final String MESSAGE_CONSTRAINTS = "Payments must be a non-negative amount";

    public static final String MESSAGE_INVALID_PAYMENT = "Payment amount must be a valid number";

    private final double amount;

    public final String value;

    /**
     * Constructs a {@code Payment}.
     *
     * @param amount A valid payment amount.
     */
    public Payment(double amount) {
        requireNonNull(amount);
        checkArgument(isValidPayment(amount), MESSAGE_CONSTRAINTS);
        this.amount = amount;
        this.value = String.format("$%.2f", amount);
    }

    public Payment(String paymentValue) {
        String numericValue = paymentValue.replaceAll("[^\\d.]", "");
        try {
            this.amount = Double.parseDouble(numericValue);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Invalid payment amount: " + paymentValue);
        }
        this.value = String.format("$%.2f", amount);
    }

    /**
     * Returns true if a given amount is a valid payment.
     */
    public static boolean isValidPayment(double test) {
        return test >= 0;
    }

    public double getAmount() {
        return amount;
    }

    @Override
    public String toString() {
        return String.format("$%.2f", amount);
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }

        if (!(other instanceof Payment)) {
            return false;
        }

        Payment otherPayment = (Payment) other;
        return Double.compare(amount, otherPayment.amount) == 0;
    }

    @Override
    public int hashCode() {
        return Double.hashCode(amount);
    }
}
