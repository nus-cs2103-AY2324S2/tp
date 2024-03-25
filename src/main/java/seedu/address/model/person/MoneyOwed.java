package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.util.Comparator;

/**
 * Represents a Person's money owed in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidMoney(String)}
 */
public class MoneyOwed {

    public static final String MESSAGE_CONSTRAINTS =
            "Money Owed should be at most 2 decimal places in the following format 'xxx.xx' or '-xxx.xx'. ";
    public static final String VALIDATION_REGEX = "^(?:-)?\\d+(\\.\\d{0,2})?";

    /**
     * This comparator will sort contacts with no money owed to the back.
     * Contacts that the user owes the most money to will be put first.
     * Contacts who owes the most money will be put right after contacts that
     * the user owes money to.
     */
    public static final Comparator<Person> MONEY_COMPARATOR = (personA, personB) -> {
        if (personA.getMoneyOwed().equals(personB.getMoneyOwed())) {
            return 0;
        }
        if (personA.getMoneyOwed().moneyOwed == 0) {
            return 1;
        }
        if (personB.getMoneyOwed().moneyOwed == 0) {
            return -1;
        }
        if (personA.getMoneyOwed().userOwesMoney() != personB.getMoneyOwed().userOwesMoney()) {
            return personA.getMoneyOwed().userOwesMoney() ? -1 : 1;
        }
        if (Math.abs(personA.getMoneyOwed().moneyOwed) > Math.abs(personB.getMoneyOwed().moneyOwed)) {
            return -1;
        }
        return 1;
    };

    public final Float moneyOwed;

    /**
     * Constructs a {@code MoneyOwed}.
     *
     * @param money A valid amount of money owed.
     */
    public MoneyOwed(String money) {
        requireNonNull(money);
        checkArgument(isValidMoney(money), MESSAGE_CONSTRAINTS);
        moneyOwed = Float.parseFloat(money);
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
     * Returns true if a moneyOwed is negative.
     */
    public boolean userOwesMoney() {
        return (moneyOwed < 0);
    }

    /**
     * Returns message to display on UI in String.
     */
    public String getMessage() {
        if (moneyOwed == 0) {
            return "You don't owe each other anything";
        }
        if (userOwesMoney()) {
            return "You owe $" + toString().substring(1);
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
