package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Person's money owed in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidMoney(String)}
 */
public class MoneyOwed {

    public static final String MESSAGE_CONSTRAINTS =
            "Money Owed should be at most 2 decimal places in the following format 'xxx.xx' or '-xxx.xx'. ";
    public static final String VALIDATION_REGEX = "^(?:-)?\\d+(\\.\\d{0,2})?";

    public final Float moneyOwed;
    public final boolean isNegative;

    /**
     * Constructs a {@code MoneyOwed}.
     *
     * @param money A valid amount of money owed.
     */
    public MoneyOwed(String money) {
        requireNonNull(money);
        checkArgument(isValidMoney(money), MESSAGE_CONSTRAINTS);
        moneyOwed = Float.parseFloat(money);
        isNegative = money.charAt(0) == '-';
    }

    /**
     * Returns true if a given string is a valid money amount.
     */
    public static boolean isValidMoney(String test) {
        if (test == null) {
            return true;
        }
        return test.matches(VALIDATION_REGEX);
    }

    /**
     * Returns message to display on UI in String.
     */
    public String getMessage() {
        if (moneyOwed == 0) {
            return String.format("You don't owe each other anything");
        }
        if (isNegative) {
            return String.format("You owe $" + toString().substring(1));
        } else {
            return String.format("Owes you $" + this);
        }
    }

    @Override
    public String toString() {
        return String.format("%.2f", moneyOwed);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof MoneyOwed)) {
            return false;
        }

        MoneyOwed otherName = (MoneyOwed) other;
        return moneyOwed.equals(otherName.moneyOwed);
    }

    @Override
    public int hashCode() {
        return moneyOwed.hashCode();
    }
}
