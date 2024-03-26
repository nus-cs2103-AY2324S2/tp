package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Person's bank details in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidBankAccount(String)}
 */
public class BankDetails {

    public static final String MESSAGE_CONSTRAINTS = "Bank details can only contain numbers "
        + "and it should not contain a dash (-)";
    public static final String VALIDATION_REGEX = "^\\d*$";

    public final String value;

    /**
     * Constructs an {@code BankDetails}.
     *
     * @param bankDetails A valid bank account.
     */
    public BankDetails(String bankDetails) {
        requireNonNull(bankDetails);
        checkArgument(isValidBankAccount(bankDetails), MESSAGE_CONSTRAINTS);
        value = bankDetails;
    }

    /**
     * Returns true if a given string is a valid bank account.
     */
    public static boolean isValidBankAccount(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof BankDetails)) {
            return false;
        }

        BankDetails otherBankDetails = (BankDetails) other;
        return value.equals(otherBankDetails.value);
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}
