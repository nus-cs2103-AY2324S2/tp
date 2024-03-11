package seedu.address.model.person;

import static java.util.Objects.requireNonNull;

/**
 * Represents a Person's remark in the address book.
 * Guarantees: immutable; is always valid
 */
public class Payment {
    public final boolean isPaidForTheMonth;

    /**
     * Creates a Remark for a student.
     *
     * @param paid If student has paid their monthly fees.
     */
    public Payment(boolean paid) {
        requireNonNull(paid);
        isPaidForTheMonth = paid;
    }

    @Override
    public String toString() {
        return (isPaidForTheMonth) ? "Paid" : "Not Paid";
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Payment // instanceof handles nulls
                && isPaidForTheMonth == ((Payment) other).isPaidForTheMonth); // state check
    }
}